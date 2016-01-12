package org.shopkeeper.subjects;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by typhooncoaster on 12-1-16.
 */
public class SubjectUtils {

    /**
     * Return a boolean if a key is editable for a subject. This can be handy if creating a map for adding a subject.
     * Returns always a boolean, even when the given parameter is null. If that occurs, this method will 'of course' return
     * false.
     *
     * @param key The key who you liked to be checked
     * @return Status if this key is editable for subjects
     */
    public static boolean isEditable(String key) {
        if (key != null) {
            if (StringUtils.contains(key, "id")) {
                return false;
            } else if (StringUtils.contains(key, "dateadded")) {
                return false;
            } else if (StringUtils.contains(key, "tablename")) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }
}
