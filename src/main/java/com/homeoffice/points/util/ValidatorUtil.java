package com.homeoffice.points.util;

import java.time.Year;

public class ValidatorUtil {

    public static boolean validateYear(String year) {
        int currentYear = Year.now().getValue();

        try {
            int intYear = Integer.valueOf(year);
            if (intYear < currentYear && intYear > currentYear - 100 ) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }

    }
}
