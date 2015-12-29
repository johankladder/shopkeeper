package org.shopkeeper.util;

import org.joda.time.DateTime;

/**
 * Created by typhooncoaster on 4-12-15.
 */
public class DateTimeGenerator {

    public static DateTime generateDateTimeNow() {
        DateTime time = new DateTime();
        return time;
    }

    public static String dateTimeToString(DateTime time) {
        return null; // TODO DATE TIME TO GIVEN STRING -> Nicely formated
    }

    public static DateTime generateTimeFromSQLLite(String datetime) {
        return DateTime.parse(datetime);
    }
}
