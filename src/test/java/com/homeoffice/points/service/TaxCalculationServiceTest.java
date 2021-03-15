package com.homeoffice.points.service;


import com.homeoffice.points.model.TaxBracket;
import com.homeoffice.points.model.TaxCalculationResult;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;


public class TaxCalculationServiceTest {
    private static List<TaxBracket> brackets = new ArrayList<>();
    static {
        brackets.add(new TaxBracket(BigDecimal.valueOf(0), BigDecimal.valueOf(48535), BigDecimal.valueOf(0.15).setScale(3)));
        brackets.add(new TaxBracket(BigDecimal.valueOf(48535), BigDecimal.valueOf(97069), BigDecimal.valueOf(0.205).setScale(3)));
        brackets.add(new TaxBracket(BigDecimal.valueOf(97069), BigDecimal.valueOf(150473), BigDecimal.valueOf(0.26).setScale(3)));
        brackets.add(new TaxBracket(BigDecimal.valueOf(150473), BigDecimal.valueOf(214368), BigDecimal.valueOf(0.29).setScale(3)));
        brackets.add(new TaxBracket(BigDecimal.valueOf(214368), null, BigDecimal.valueOf(0.33)));
    }

    static Stream<Arguments> bracketsAndAmounts() {
        return Stream.of(
                arguments(brackets, BigDecimal.valueOf(20000), BigDecimal.valueOf(3000)),
                arguments(brackets, BigDecimal.valueOf(60000), BigDecimal.valueOf(9630.58)),
                arguments(brackets, BigDecimal.valueOf(100000), BigDecimal.valueOf(17991.78))
        );
    }

    static Stream<Arguments> bracketsAndEdgeAmounts() {
        return Stream.of(
                arguments(brackets, BigDecimal.valueOf(0), BigDecimal.valueOf(48535), Integer.valueOf(0)),
                arguments(brackets, BigDecimal.valueOf(48535), BigDecimal.valueOf(97069), Integer.valueOf(1)),
                arguments(brackets, BigDecimal.valueOf(97069), BigDecimal.valueOf(150473), Integer.valueOf(2)),
                arguments(brackets, BigDecimal.valueOf(150473), BigDecimal.valueOf(214368), Integer.valueOf(3)),
                arguments(brackets, BigDecimal.valueOf(214368), null, Integer.valueOf(4))
        );
    }

    @ParameterizedTest(name = "#{index} - Test salary {1}, total tax {2}.")
    @MethodSource("bracketsAndAmounts")
    public void test_calculateTax(List<TaxBracket> brackets, BigDecimal annualSalary, BigDecimal totalTax) {
        assertEquals(TaxCalculationService.calculate(annualSalary, brackets).getTaxAmount().compareTo(totalTax), 0);

    }

    @ParameterizedTest(name = "#{index} - Test {1}, {2} bracket boundaries.")
    @MethodSource("bracketsAndEdgeAmounts")
    public void test_calculateTax_bracketEdges(List<TaxBracket> brackets, BigDecimal annualSalaryMin, BigDecimal annualSalaryMax, int index) {
        if (annualSalaryMax == null) {
            annualSalaryMax = annualSalaryMin.add(BigDecimal.valueOf(100000));
        }
        TaxCalculationResult resultMin = TaxCalculationService.calculate(annualSalaryMin, brackets);
        TaxCalculationResult resultMax = TaxCalculationService.calculate(annualSalaryMax, brackets);

        BigDecimal taxDiff = resultMax.getTaxAmount().subtract(resultMin.getTaxAmount());
        BigDecimal salaryDiff = annualSalaryMax.subtract(annualSalaryMin);

        assertEquals(taxDiff.divide(salaryDiff, 3, RoundingMode.HALF_UP).compareTo(brackets.get(index).getRate()), 0 );
    }


}