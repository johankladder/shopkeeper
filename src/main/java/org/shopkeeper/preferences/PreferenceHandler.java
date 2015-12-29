package org.shopkeeper.preferences;

import org.json.simple.JSONObject;
import org.shopkeeper.preloader.Preloader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.prefs.Preferences;

/**
 * Created by johankladder on 12/23/15.
 */
public class PreferenceHandler implements Runnable {



    @Override
    public void run() {
        // Look if preferences exist:
        Preferences prefs = Preferences.userRoot().node(this.getClass().getName());

        String ID1 = "database_type";

        // Create preferences:
        Preference.setDatabaseType(prefs.get(ID1, "sqlite"));


        synchronized (Preloader.ready) {
            Preloader.ready.notify();
        }
    }
}
