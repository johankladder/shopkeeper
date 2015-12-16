package org.shopkeeper.subjectsmodules;

import org.shopkeeper.database.modules.DatabaseChooser;
import org.shopkeeper.database.modules.DatabaseModule;
import org.shopkeeper.database.modules.DatabaseTypes;
import org.shopkeeper.subjects.Subject;

import java.util.ArrayList;

/**
 * Created by typhooncoaster on 4-12-15.
 */
public abstract class SubjectModule {

    private static ArrayList<Subject> SUBJECTS = new ArrayList<Subject>();
    private static boolean started = false;
    protected static DatabaseModule DB = null; // TODO init dbmodule here from db chooser

    public SubjectModule() {
        if(!started) {
            DatabaseChooser.start();
            started = true;
        }
    }

    public abstract void add(Subject subject);

    public abstract void update(Subject subject);

    public abstract void delete(Subject subject);

    public int getTotalSubjects() {
        return SUBJECTS.size();
    }

    public static void addToList(Subject subject) {
        SUBJECTS.add(subject);
    }

    public static void removeFromList(Subject subject) {
        SUBJECTS.remove(subject);
    }

}
