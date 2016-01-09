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
    public static final Integer MIN_WIDTH_APPLICATION = 600;
    public static final Integer MIN_HEIGHT_APPLICATION = 300;
    public static final Integer PREF_HEIGHT_LIST_CONTAINERS = 500;
    public static final Integer PREF_WIDTH_LIST_CONTAINERS = 400;
    public static final Integer PRELOADER_WIDTH = 600;
    public static final Integer PRELOADER_HEIGTH = 300;
    public static final String LOGOPATH = "/images/preloader_image.png";
    public static final String HEAD_LOGO_PATH = "/images/head.png";
    public static final String RELEASE_NUMBER = "TEST";
    public static final String RELEASE_NOTES = "Version: " + RELEASE_NUMBER + " by Johan Kladder";
    public static final String START_TEXT = "Press me to enter Shopkeeper";

    public static void setDatabaseType(String pref) {
        if(StringUtils.contains(pref, "sqlite")) {
            DATABASE_TYPE = DatabaseTypes.DATABASETYPE_SQLLITE;
        }
    }

    public static void setGuiType(String pref) {
        if(StringUtils.contains(pref,"fx")) {
            GUI_TYPE = GuiTypes.FX;
        } else if (StringUtils.contains(pref, "swing")){
            GUI_TYPE = GuiTypes.SWING;
        } else if (StringUtils.contains(pref, "api")) {
            GUI_TYPE = GuiTypes.API;
        }
    }



}
