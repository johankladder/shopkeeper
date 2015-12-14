package org.shopkeeper.database.modules.queries;

import org.apache.commons.lang3.StringUtils;
import org.shopkeeper.database.modules.DatabaseTypes;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by typhooncoaster on 14-12-15.
 */
public class QueryCreator {


    /**
     * Creates the initialization-query for a given map and database-type. If the map or database-type is not acknowledged,
     * this method will return null. This method will work the best with the 'getFields' method from the Subject's class.
     * <p>
     * A valid database-type is necessary for this method to work as it is supposed to.
     *
     * @param map          Map with the database-fields.
     * @param databaseType Type of the database
     * @return Creation-query.
     * @see DatabaseTypes
     */
    public static String createInitQuery(Map map, Integer databaseType) {
        if (!map.isEmpty() && databaseType != null) {
            if (databaseType == DatabaseTypes.DATABASETYPE_SQLLITE) {
                return createSQLLiteInit(map);
            }
        }
        return null;
    }

    /**
     * Creates the actual init-query for an sqlLite table. This method needs a map with the recommend fields.
     * The two fields that the map is obligated to have are: 'id' and 'tablename'. The two mentioned fields need to be
     * the keys in the the map. The 'id' doesn't need to have a value, because it will always be translated in an Integer
     * field -> Primary Key -> AutoIncrement. The tablename needs to have a value. If map is incomplete or doesn't meet the
     * requirements as mentioned above, this method will return null
     * @param map Map including the database-fields
     * @return Creation-query
     */
    public static String createSQLLiteInit(Map map) {
        String tableName = (String) map.get("tablename");
        if (tableName != null) {
            String prefix = "CREATE TABLE IF NOT EXISTS " + tableName + " (";
            String initString = "";
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                if (!pair.getKey().equals("tablename")) {
                    if (pair.getKey().equals("id")) {
                        initString += "id INTEGER PRIMARY KEY AUTOINCREMENT, "; // TODO Autoincrement
                    } else {
                        String type = getDatatypeSqlLite((String) pair.getValue());
                        if (StringUtils.isNotBlank(type)) {
                            initString += pair.getKey() + " " + type + ", ";
                        } else {
                            // TODO Logging
                            continue; // Continue with looping -> Maybe there are other fields to gather.
                        }
                    }
                }

                it.remove(); // avoids a ConcurrentModificationException
            }
            if (StringUtils.isNotBlank(initString)) {
                initString = StringUtils.trim(initString);  // Trim unneeded spaces
                initString = StringUtils.substringBeforeLast(initString, ","); // Remmove the last comma (from parsing)
                initString += ")";  // Complete the query

                return prefix + initString;
            } else {
                // TODO Logging
                return null;
            }
        }
        return null;
    }

    private static String getDatatypeSqlLite(String type) {
        if (type != null) {
            if (type.equals("string")) {
                return "VARCHAR";
            } else if (type.equals("date")) {
                return "DATETIME";
            } else if (type.equals("double")) {
                return "DOUBLE";
            } else if (type.equals("integer")) {
                return "INT";
            }
        }
        // TODO Logging
        return null;
    }

}
