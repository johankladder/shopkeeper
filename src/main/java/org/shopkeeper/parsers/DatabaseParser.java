package org.shopkeeper.parsers;

import org.shopkeeper.database.modules.DatabaseModule;
import org.shopkeeper.database.modules.DatabaseTypes;
import org.shopkeeper.database.modules.SQLLiteModule;

import java.util.logging.Logger;

/**
 * Created by typhooncoaster on 4-12-15.
 */
public class DatabaseParser {

    private final static Logger LOGGER = Logger.getLogger(DatabaseParser.class.getName());

    public static Integer parseTypeFromSubject(DatabaseModule subject) {
        if (subject instanceof SQLLiteModule) {
            return DatabaseTypes.DATABASETYPE_SQLLITE;
        } else {
            LOGGER.warning("Subject can not be found!");
            return null;
        }
    }
}
