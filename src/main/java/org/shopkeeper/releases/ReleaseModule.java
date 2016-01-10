package org.shopkeeper.releases;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.shopkeeper.preferences.PreferenceModule;
import org.shopkeeper.preloader.Preloader;
import org.shopkeeper.util.AntiLockSystem;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by typhooncoaster on 9-1-16.
 */
public class ReleaseModule implements Runnable {

    public static ArrayList<Release> RELEASES = new ArrayList<>();

    @Override
    public void run() {
        parseJSON();
    }

    private static void parseJSON() {
        JSONParser parser = new JSONParser();

        try {
            InputStream is = ReleaseModule.class.getResourceAsStream(PreferenceModule.RELEASE_NOTES_PATH);
            InputStreamReader isr = new InputStreamReader(is);
            Object obj = parser.parse(isr);

            JSONObject jsonObject = (JSONObject) obj; // Create the json object
            JSONArray releases = (JSONArray) jsonObject.get("releases"); // Get all the releases
            // Loop through all the releases:

            Iterator<JSONObject> release = releases.iterator();
            while (release.hasNext()) {
                JSONObject object = release.next();
                Release release_object = new Release();
                release_object.PREFIX = (String) object.get("prefix");
                JSONArray notes = (JSONArray) object.get("notes");

                ArrayList<String> release_notes = new ArrayList<>();

                Iterator<String> note = notes.iterator();
                while (note.hasNext()) {
                    release_notes.add(note.next());
                }
                release_object.notes = release_notes;
                RELEASES.add(release_object);

            }


        } catch (Exception e) {
            e.printStackTrace();
            AntiLockSystem.notifyLock();
        } finally {
            AntiLockSystem.notifyLock();
        }
    }
}
