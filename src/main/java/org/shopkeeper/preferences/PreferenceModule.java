package org.shopkeeper.preferences;

import org.shopkeeper.preloader.Preloader;

import java.util.Map;

/**
 * Created by johankladder on 12/23/15.
 */
public class PreferenceModule {

    private static Preference PREFERENCE = null;

    public PreferenceModule(Preference preference) {
        this.PREFERENCE = preference;
    }
}
