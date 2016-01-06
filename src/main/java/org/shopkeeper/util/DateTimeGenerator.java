package org.shopkeeper.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by typhooncoaster on 4-12-15.
 */
public class DateTimeGenerator {

    public static DateTime generateDateTimeNow() {
        DateTime time = new DateTime();
        return time;
    }

    public static String dateTimeToString(DateTime time) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        return fmt.print(time);
    }

    public static DateTime generateTimeFromSQLLite(String datetime) {
        return DateTime.parse(datetime);
    }
}
