package org.shopkeeper.preferences;

import org.shopkeeper.preloader.Preloader;

import java.util.prefs.Preferences;

/**
 * This class loads all the given preferences. It will also look for default preferences values. This class is made to be
 * invoked in the preloader class and will not fully work if it is invoked at a later point. This causes that the user needs
 * to, if it want to see the changes in the newly added preference, first needs to restart the application so that the preloader
 * is invoking this runnable again.
 */
public class PreferenceLoader implements Runnable {

    @Override
    public void run() {
        // Look if preferences exist:
        Preferences prefs = Preferences.userRoot().node(this.getClass().getName());

        for(String[] pref_array : PreferenceModule.IDS) {
            String value = prefs.get(pref_array[0], pref_array[1]);

            PreferenceModule.setPreference(new Preference(pref_array[0], value));
        }
        synchronized (Preloader.ready) {
            Preloader.ready.notify();
        }
    }
}
