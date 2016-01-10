package org.shopkeeper.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by johankladder on 1/10/16.
 */
public class Beatifier {

    public static String beautifyString(String input) {
        if(input != null && !StringUtils.equals(input, "")) {
            String output = StringUtils.trim(input);
            output = StringUtils.lowerCase(output);
            output = StringUtils.capitalize(output);
            return output;
        }
        return null;
    }

    public static String beautifyWithMapKeyAndString(String key, String value) {
        if(StringUtils.contains(key, "price")) {
            return PriceGenerator.priceToString(Double.parseDouble(value));
        } else {
            return value;
        }
    }
}
