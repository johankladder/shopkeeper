package org.shopkeeper.releases;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.shopkeeper.preloader.Preloader;

import java.io.File;
import java.io.FileReader;
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

            ClassLoader classLoader = ReleaseModule.class.getClassLoader();
            File file = new File(classLoader.getResource("releases/releasenotes.json").getFile());
            Object obj = parser.parse(new FileReader(file));

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

                RELEASES.add(release_object);

            }


        } catch (Exception e) {
            e.printStackTrace();
            synchronized (Preloader.ready) {
                Preloader.ready.notify();
            }
        } finally {
            synchronized (Preloader.ready) {
                Preloader.ready.notify();
            }
        }
    }
}
