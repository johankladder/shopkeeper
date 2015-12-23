package org.shopkeeper.database.parsers;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by root on 21-12-15.
 */
public class ResultParser {

    public static boolean queryWithResult(String query) {
        String capitalizedQuery = StringUtils.lowerCase(query);
        // First check
        if(StringUtils.contains(capitalizedQuery, "select * from ")) {
            return doubleCheck(capitalizedQuery);
        }
        return doubleCheck(capitalizedQuery);

    }

    private static boolean doubleCheck(String query) {
        String[] splittedString = StringUtils.split(query, " ");
        if(splittedString[0].contains("select")) {
            return true;
        }

        return false;
    }
}
