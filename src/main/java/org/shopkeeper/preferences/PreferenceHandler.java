package org.shopkeeper.preferences;
import org.shopkeeper.preloader.Preloader;
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
        String ID2 = "gui_type";

        // Create preferences:
        Preference.setDatabaseType(prefs.get(ID1, "sqlite"));
        Preference.setGuiType(prefs.get(ID2, "fx"));

        synchronized (Preloader.ready) {
            Preloader.ready.notify();
        }
    }
}
