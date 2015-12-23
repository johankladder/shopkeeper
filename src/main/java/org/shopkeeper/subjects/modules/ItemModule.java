package org.shopkeeper.subjects.modules;

import org.shopkeeper.subjects.subjecttypes.Subject;
import org.shopkeeper.subjects.subjecttypes.SubjectTypes;

/**
 * Created by typhooncoaster on 4-12-15.
 */
public class ItemModule extends SubjectModule {

    public ItemModule() {
        super(SubjectTypes.ITEM);
    }

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
