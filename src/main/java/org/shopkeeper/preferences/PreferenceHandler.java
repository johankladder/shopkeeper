package org.shopkeeper.preferences;

import org.shopkeeper.preloader.Preloader;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by johankladder on 12/23/15.
 */
public class PreferenceHandler implements Runnable {

    private static Map<String, PreferenceModule> MODULES = new HashMap<>();
    private static String PREFERENCESPATH = "";


    @Override
    public void run() {
        // Look if preferences exist:

        // If not create pre-made preferences:

        // Read from the preferences:

        // Create preferences:
        Preference preference = new Preference();

        // Create preferencesmodules with created preference:
        MODULES.put("preferencemodule", new PreferenceModule(preference));

        synchronized (Preloader.ready) {
            Preloader.ready.notify();
        }
    }
}
