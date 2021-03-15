package com.homeoffice.points;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.homeoffice.points.model.TaxBrackets;
import com.homeoffice.points.model.TaxCalculationResult;
import com.homeoffice.points.model.WebResponse;
import com.homeoffice.points.network.TaxBracketRetriever;
import com.homeoffice.points.model.ErrorMessages;
import com.homeoffice.points.service.JsonStringConverter;
import com.homeoffice.points.service.TaxCalculationService;
import com.homeoffice.points.util.ValidatorUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TaxCalculator {
    private static final Logger log = LogManager.getLogger(TaxCalculator.class);
    public static String url = "http://localhost:5000/tax-calculator/brackets";

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        boolean correctInput = false;

        BigDecimal amount = BigDecimal.valueOf(0).setScale(2);
        while (!correctInput) {
            System.out.println("Please, enter the annual salary: ");

            try {
                amount = scan.nextBigDecimal().setScale(2);
                if (amount.compareTo(BigDecimal.valueOf(0)) >=  0) {
                    correctInput = true;
                } else {
                    System.out.println("Incorrect value. Salary cannot have negative value.");
                }
            } catch (ArithmeticException e ) {
                System.out.println("Incorrect value. Only 2 digits after decimal point permitted.");
            } catch ( InputMismatchException e) {
                System.out.println("Incorrect value.");
                scan.nextLine();
            }
        }
        scan.nextLine();
        correctInput = false;
        String year = "";
        while (!correctInput) {
            System.out.println("Please, enter the year: ");
            year = scan.nextLine().trim();
            if (year.length() == 0 || ValidatorUtil.validateYear(year)) {
                correctInput = true;
            } else {
                System.out.printf("%s is not correct value. \r\n", year);
            }
        }
        log.info("Annual salary: " + amount);
        log.info("Year: [" + year + "]");
        try {
            WebResponse response = TaxBracketRetriever.get(url + "/" + year.trim());
            if (response.getReturnCode() == 200) {
                TaxBrackets tb = JsonStringConverter.convert(response.getBody(), TaxBrackets.class);
                TaxCalculationResult result = TaxCalculationService.calculate(amount, tb.getTax_brackets());

                System.out.printf("Total Tax = %.2f  \r\n", result.getTaxAmount());
                result.getTaxPerBracket().stream().forEach(s -> System.out.printf("%s \r\n", s.toString()));
                System.out.printf("Effective Tax Rate = %.3f%% \r\n", result.getEffectiveTaxRate());

            } else if (response.getReturnCode() == 500) {
                System.out.println("Not your fault! Try again later.");
            } else {
                ErrorMessages error = JsonStringConverter.convert(response.getBody(), ErrorMessages.class);
                System.out.println(error.getErrors().get(0).getMessage());
            }
        } catch (JsonProcessingException e) {
            System.out.println("Tax brackets data unavailable. Try again later.");
            log.error(e.getMessage(), e);
        } catch (Exception e) {
            System.out.println("Unexpected error. Send log file to support");
            log.error(e.getMessage(), e);
        }
    }
}
