package org.shopkeeper.subjects.modules;

import org.shopkeeper.subjects.Subject;

/**
 * Created by typhooncoaster on 4-12-15.
 */
public class CustomerModule extends SubjectModule{


    @Override
    public void add(Subject subject) {
            DB.add(subject);
    }

    @Override
    public void update(Subject subject) {
            DB.update(subject);
    }

    @Override
    public void delete(Subject subject) {
            DB.delete(subject);
    }
}
