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

    public SubjectModule() {
        if(!started) {
            System.out.println("Starting database from module...");
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

    protected void addToList(Subject subject) {
        SUBJECTS.add(subject);
    }

    protected void removeFromList(Subject subject) {
        SUBJECTS.remove(subject);
    }

    protected DatabaseModule getDatabase() {
        return DatabaseChooser.getDatabase(DatabaseTypes.DATABASETYPE_SQLLITE);
    }

}
