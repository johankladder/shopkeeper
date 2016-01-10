package org.shopkeeper.preferences;

import org.apache.commons.lang3.StringUtils;
import org.shopkeeper.database.modules.DatabaseTypes;
import org.shopkeeper.gui.GuiTypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * The PreferenceModule is holding all the default preferences. All the fields are final here, so they can't be changed
 * at a later point by a user. These fields are for all the application work or minimum view sizes.
 *
 * This class also holds a map with preference objects. Those can be changed by the user. Recommend is to only change
 * the values from those objects and put them back into the list if you want to adapt changes to the preferences.
 *
 * @see Preference
 */
public class PreferenceModule {

    public static Integer DATABASE_TYPE = null;
    public static Integer GUI_TYPE = null;

    public static final Integer MIN_WIDTH_APPLICATION = 600;
    public static final Integer MIN_HEIGHT_APPLICATION = 300;
    public static final Integer PREF_HEIGHT_LIST_CONTAINERS = 500;
    public static final Integer PREF_WIDTH_LIST_CONTAINERS = 400;
    public static final Integer PRELOADER_WIDTH = 600;
    public static final Integer PRELOADER_HEIGTH = 300;
    public static final Integer RELEASE_NOTES_HEIGHT = 200;
    public static final String LOGOPATH = "/images/preloader_image.png";
    public static final String HEAD_LOGO_PATH = "/images/head.png";
    public static final String RELEASE_NOTES_PATH = "/releases/releasenotes.json";
    public static final String RELEASE_NUMBER = "0.01";
    public static final String RELEASE_NOTES = "Version: " + RELEASE_NUMBER + " by Johan Kladder";

    // IDS:
    public static final String[] DATA_BASE_TYPE = {"database_type", "sqlite"};
    public static final String[] GUI_TYPE_ID = {"gui_type", "fx"};
    public static final ArrayList<String[]> IDS = new ArrayList<>(Arrays.asList(DATA_BASE_TYPE, GUI_TYPE_ID));
    public static Map<String,Preference> PREFERENCES = new HashMap<>();

    public static void setPreference(Preference preference) {
        if(StringUtils.contains(preference.getId(), "database_type")) {
            setDatabaseType(preference.getValue());
        } else if(StringUtils.contains(preference.getId(), "gui_type")) {
            setGuiType(preference.getValue());
        }
        addPreference(preference);
     }

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

    public static void addPreference(Preference preference) {
        if(preference != null) {
            PREFERENCES.put(preference.getId(), preference);
        }
    }



}
