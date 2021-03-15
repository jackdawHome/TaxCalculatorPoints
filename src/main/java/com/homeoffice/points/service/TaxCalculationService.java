package com.homeoffice.points.service;

import com.homeoffice.points.model.TaxBracket;
import com.homeoffice.points.model.TaxCalculationResult;
import com.homeoffice.points.model.TaxPerBracket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class TaxCalculationService {
    /**
     * Calculates total tax salary along with tax amounts per bracket
     * @param salary - annual salary
     * @param brackets - list of tax brackets
     * @return TaxCalculationResult
     */
    public static TaxCalculationResult calculate(BigDecimal salary, List<TaxBracket> brackets) {

        TaxCalculationResult result;
        BigDecimal zero = BigDecimal.valueOf(0L,2);
        List<TaxPerBracket> list = new ArrayList<>();
        if (salary == null || salary.compareTo(zero) < 0) {
            throw new IllegalArgumentException("Salary has incorrect value.");
        }

        if (salary.compareTo(zero) == 0 || brackets.size() == 0) {
            return new TaxCalculationResult(zero, zero, list);
        }

        BigDecimal totalTax = zero;

        for (TaxBracket bracket : brackets) {
            // checks if salary fits into the bracket
            if (salary.compareTo(bracket.getMin()) >= 0) {
                // calculates bracket tax portion
                BigDecimal tax = ((salary.subtract(bracket.getMin()))
                        .min(bracket.getMax().subtract(bracket.getMin()) ))
                        .multiply(bracket.getRate()).setScale(2, RoundingMode.HALF_UP);
                // calculates total tax
                totalTax = totalTax.add(tax);
                list.add(new TaxPerBracket(bracket, tax));
            } else {
                list.add(new TaxPerBracket(bracket, zero));
            }
        }
        result = new TaxCalculationResult(totalTax, totalTax.divide(salary, 3, RoundingMode.HALF_UP), list);

        return result;
    }

}
