package org.shopkeeper.subjects.modules;

import org.shopkeeper.database.modules.DatabaseModule;
import org.shopkeeper.database.modules.DatabaseStarter;
import org.shopkeeper.subjects.subjecttypes.Subject;

import java.util.ArrayList;

/**
 * Created by typhooncoaster on 4-12-15.
 */
public abstract class SubjectModule {

    private ArrayList<Subject> SUBJECTS = new ArrayList<Subject>();
    private static boolean started = false;
    public static DatabaseModule DB = null;
    private Integer SUBJECTTYPE = null;

    public SubjectModule(Integer subjecttype) {
        SUBJECTTYPE = subjecttype;
    }

    public abstract void add(Subject subject);

    public void update(Subject subject) throws InterruptedException {
        synchronized (DatabaseStarter.DBTHREAD) {
            DB.update(subject);
            DatabaseStarter.DBTHREAD.wait();
        }
    }

    public abstract void delete(Subject subject);

    public void refresh() throws InterruptedException {
        synchronized (DatabaseStarter.DBTHREAD) {
            SUBJECTS.clear();
            DB.showAll(SUBJECTTYPE);
            DatabaseStarter.DBTHREAD.wait();
        }
    }

    public int getTotalSubjects() {
        return SUBJECTS.size();
    }

    public ArrayList<Subject> getModuleSubjects() {
        return SUBJECTS;
    }

    public void addToList(Subject subject) {
        SUBJECTS.add(subject);
    }

    public void removeFromList(Subject subject) {
        SUBJECTS.remove(subject);
    }

    public Integer getSubjectType() {
        return SUBJECTTYPE;
    }


}
