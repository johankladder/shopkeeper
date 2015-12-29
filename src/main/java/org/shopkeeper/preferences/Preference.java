package org.shopkeeper.preferences;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.shopkeeper.database.modules.DatabaseTypes;

/**
 * Created by johankladder on 12/23/15.
 */
public class Preference {

    public static Integer DATABASE_TYPE = null;

    public static void setDatabaseType(String pref) {
        if(StringUtils.contains(pref, "sqlite")) {
            DATABASE_TYPE = DatabaseTypes.DATABASETYPE_SQLLITE;
        }
    }



}
