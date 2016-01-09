package org.shopkeeper.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateTimeGenerator {

    /**
     * Generate a DateTime object from the moment of method-calling.
     *
     * @return The generated DateTime object
     * @see DateTime
     */
    public static DateTime generateDateTimeNow() {
        DateTime time = new DateTime();
        return time;
    }

    /**
     * Converts given DateTime object to string in format of 'yyyy-MM-dd HH:mm:ss'
     *
     * @param time The DateTime object liked to be parsed to string
     * @return The parsed string from te given DateTime object
     * @see DateTime
     */
    public static String dateTimeToString(DateTime time) {
        if (time != null) {
           // DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
            //return fmt.print(time);

            // FIXME: Clashed with Subjectresultparser!
            return time.toString();
        } else {
            return null;
        }
    }

}
