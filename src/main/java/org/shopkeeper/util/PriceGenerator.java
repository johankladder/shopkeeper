package org.shopkeeper.util;

import org.apache.commons.lang3.StringUtils;

public class PriceGenerator {

    public static final String CURRENCY_EURO = ""; // TODO Get currency symbol from preferences.

    /**
     * This method tries to parse a given string to a double number. It will check on comma and points and will return
     * the double value of the given string when it is possible to parse it into a double. If not, it will return null.
     * @param priceString The String object liked to be parsed to an Double
     * @return the double value of the String
     */
    public static Double getPriceFromString(String priceString) {
        if(priceString != null) {
            if (StringUtils.contains(priceString, ",")) {
                return parseStringWithComma(priceString);
            }
            else if (StringUtils.contains(priceString, ".")) {
                return parseStringWithPoint(priceString);
            }
            else {
                return parseStringWithoutPointOrComma(priceString);
            }

        }
        return null;
    }

    private static Double parseStringWithComma(String priceString) {
        if(StringUtils.countMatches(priceString, ",") > 1) {
            return null;
        } else {
            priceString = StringUtils.replaceChars(priceString, ",", ".");
            try {
                return Double.parseDouble(StringUtils.trim(priceString));
            } catch (NumberFormatException ex) {
                return null;
            }
        }
    }


    private static Double parseStringWithPoint(String priceString) {
        if(StringUtils.countMatches(priceString, ".") > 1) {
            return null;
        } else {
            try {
                return Double.parseDouble(StringUtils.trim(priceString));
            } catch (NumberFormatException ex) {
                return null;
            }
        }
    }

    private static Double parseStringWithoutPointOrComma(String priceString) {
        try {
            return Double.parseDouble(StringUtils.trim(priceString));
        } catch (NumberFormatException ex) {
            return null;
        }
    }


}
