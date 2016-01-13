package org.shopkeeper.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by johankladder on 1/10/16.
 */
public class Beatifier {

    public static String beautifyString(String input) {
        if (input != null && !StringUtils.equals(input, "")) {
            String output = StringUtils.trim(input);
            output = StringUtils.lowerCase(output);
            output = StringUtils.capitalize(output);
            return output;
        }
        return null;
    }

    public static String beautifyWithMapKeyAndString(String key, Object value) {
        if (value instanceof String) {
            if (value == null || StringUtils.contains((String) value, "null")) {
                return "";
            } else {
                return (String) value;
            }
        } else {
            if (value == null) {
                return "";
            }

            if (StringUtils.contains(key, "price")) {
                return PriceGenerator.priceToString((Double)value);
            }
        }
        return "" + value;
    }
}
