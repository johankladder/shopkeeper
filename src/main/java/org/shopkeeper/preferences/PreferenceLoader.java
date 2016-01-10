package org.shopkeeper.preferences;
import org.shopkeeper.preloader.Preloader;
import java.util.prefs.Preferences;

/**
 * Created by johankladder on 12/23/15.
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
