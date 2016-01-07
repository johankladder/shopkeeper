package org.shopkeeper.database.parsers;

import org.apache.commons.lang3.StringUtils;
import org.shopkeeper.database.modules.DatabaseTypes;
import org.shopkeeper.subjects.subjecttypes.Subject;

import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by typhooncoaster on 14-12-15.
 */
public class SQLLiteQueryCreator {
    private final static Logger LOGGER = Logger.getLogger(SQLLiteQueryCreator.class.getName());

    /**
     * Creates the initialization-query for a given map and database-type. If the map or database-type is not acknowledged,
     * this method will return null. This method will work the best with the 'getFields' method from the Subject's class.
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
     *
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
                        initString += "id INTEGER PRIMARY KEY AUTOINCREMENT, ";
                    } else {
                        String type = getDatatypeSqlLite((String) pair.getValue());
                        if (StringUtils.isNotBlank(type)) {
                            initString += pair.getKey() + " " + type + ", ";
                        } else {
                            LOGGER.warning("Could'nt find a valid datatype -> Query cannot be created!");
                            return null;
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
                LOGGER.warning("The querystring was empty! -> Query cannot be created!");
                return null;
            }
        }
        return null;
    }

    /**
     * Creates a 'drop-table'-query for an SQLite database. Will return null when table-name is invalid, null or empty.
     *
     * @param tableName The name of the table who you like to 'drop'
     * @return Query for dropping the table
     */
    public static String createDropTableQuery(String tableName) {
        if (tableName != null) {
            return "DROP TABLE IF EXISTS " + tableName + ";";
        }
        return null;
    }

    /**
     * Creates an insert-query for an sqlite-database. Will first look if the subjects field map contains a table name.
     * After that the id and table name are getting skipped while creating the query. This is because the id is always
     * autoincrement in this case. Before the actual values are added to the query they will be parsed. This is because
     * for certain data- types there is another syntax for query-building.
     *
     * @param subject The subject
     * @return The insert-query bases on the above mentioned subject.
     * @see Subject
     */
    // TODO Currently not checking if value is null or empty.
    public static String createInsertQuery(Subject subject) {
        if (subject != null) {
            Map map = subject.getFields();
            if (map.containsKey("tablename")) {
                String base = "INSERT INTO " + map.get("tablename");
                String datanames = "(";
                String values = "VALUES (";
                Iterator it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    if (!pair.getKey().equals("id") && !pair.getKey().equals("tablename")) {
                        datanames += pair.getKey() + ", ";
                        // Create values
                        values += parseValue(pair, subject.INIT_FIELD) + ", ";
                    }
                }
                // Trim and normalise:
                datanames = StringUtils.trimToNull(StringUtils.substringBeforeLast(datanames, ","));
                datanames += ") ";
                values = StringUtils.trimToNull(StringUtils.substringBeforeLast(values, ","));
                values += ")";

                base += datanames + values;
                return base;
            }
        }

        return null;
    }

    public static String createSelectAllQuery(String tablename) {
        if (tablename != null && StringUtils.isNotBlank(tablename)) {
            return "SELECT * FROM " + tablename + ";";
        }
        return null;
    }

    /**
     * Provides syntax for certain fields in the database-type. When a unknown parameter was send, this method will return
     * null. This method is private because it's not interesting for the outside world.
     *
     * @param type Type of field
     * @return Syntax for provided field
     */
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
        LOGGER.warning("Could'nt find the requested data-type!");
        return null;
    }

    /**
     * Parses 'normal' String values into values in SQLite-style. For example; Varchars can not be inserted without quotes
     * around the string. This method will handles those exceptions for you.
     *
     * @param pair    The key-value pair. Given from the fields-map from the subject.
     * @param initMap The initialisation-map from the subject
     * @return Value corrected by SQLite norms.
     * @see SQLLiteQueryCreator#createInsertQuery(Subject)
     * @see Subject
     */
    private static Object parseValue(Map.Entry pair, Map initMap) {
        String datatype = (String) initMap.get(pair.getKey());
        if (datatype != null) {
            if (datatype.equals("string") || datatype.equals("date")) {
                return StringUtils.trimToNull("'" + pair.getValue() + "'");
            } else {
                return pair.getValue();
            }
        }
        return null;
    }


}
