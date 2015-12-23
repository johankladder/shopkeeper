package org.shopkeeper.subjects.modules;

import org.shopkeeper.database.DatabaseHandler;
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
    protected static DatabaseModule DB = null;
    private Integer SUBJECTTYPE = null;

    public SubjectModule(Integer subjecttype) {
        SUBJECTTYPE = subjecttype;
<<<<<<< HEAD
=======
        DB = DatabaseChooser.getDatabase(DatabaseTypes.DATABASETYPE_SQLLITE); // TODO From preferences.
        DatabaseHandler.start(DB);
>>>>>>> 4ba6fda71f582aa17654196ccb17249bcac14485
    }

    public abstract void add(Subject subject);

    public abstract void update(Subject subject);

    public abstract void delete(Subject subject);

    public void refresh() {
        SUBJECTS.clear();
        DB.showAll(SUBJECTTYPE);
    }

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
