package org.shopkeeper.preferences;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.shopkeeper.database.modules.DatabaseTypes;
import org.shopkeeper.gui.GuiTypes;

/**
 * Created by johankladder on 12/23/15.
 */
public class Preference {

    public static Integer DATABASE_TYPE = null;
    public static Integer GUI_TYPE = null;
    public static Integer MIN_WIDTH_APPLICATION = 600;
    public static Integer MIN_HEIGHT_APPLICATION = 300;
    public static Double PREF_HEIGHT_LIST_CONTAINERS = 300.00;
    public static Double PREF_HEIGHT_LISTCONTAINERS = 300.00;

    public static void setDatabaseType(String pref) {
        if(StringUtils.contains(pref, "sqlite")) {
            DATABASE_TYPE = DatabaseTypes.DATABASETYPE_SQLLITE;
        }
    }

    public static void setGuiType(String pref) {
        if(StringUtils.contains(pref,"fx")) {
            GUI_TYPE = GuiTypes.FX;
        } else {
            GUI_TYPE = GuiTypes.SWING;
        }
    }



}
