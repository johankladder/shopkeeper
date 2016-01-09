package org.shopkeeper.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by johankladder on 1/10/16.
 */
public class Beatifier {

    public static String beatifyString(String input) {
        if(input != null) {
            String output = StringUtils.trim(input);
            output = StringUtils.capitalize(input);
            return output;
        }
        return null;
    }
}
