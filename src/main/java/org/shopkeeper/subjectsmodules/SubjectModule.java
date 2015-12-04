package org.shopkeeper.subjectsmodules;

import org.shopkeeper.subjects.Subject;

import java.util.ArrayList;

/**
 * Created by typhooncoaster on 4-12-15.
 */
public abstract class SubjectModule {

    private static ArrayList<Subject> SUBJECTS = new ArrayList<Subject>();

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


}
