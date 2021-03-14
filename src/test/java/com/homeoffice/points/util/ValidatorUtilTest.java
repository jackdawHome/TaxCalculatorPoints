package com.homeoffice.points.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorUtilTest {
    private static final String CURRENT_YEAR = String.valueOf(Year.now().getValue());

    @ParameterizedTest(name = "#{index} - Test {0} year")
    @ValueSource(strings = {"2010", "1996","2020"})
    void test_validateYear_valid(String yearValid) {
        assertTrue(ValidatorUtil.validateYear(yearValid));
    }

    @ParameterizedTest(name = "#{index} - Test [{0}] year")
    @ValueSource(strings = {"abcd", "20","", "    "})
    public void test_validateYear_invalid(String yearInvalid) {
        assertFalse(ValidatorUtil.validateYear(yearInvalid));
    }

    @Test
    public void test_validateYear_currentYear() {
        assertFalse(ValidatorUtil.validateYear(String.valueOf(Year.now().getValue())));
    }
}
