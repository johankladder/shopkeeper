package org.shopkeeper.subjects.modules;

import org.shopkeeper.subjects.Subject;
import org.shopkeeper.subjects.SubjectTypes;
import org.shopkeeper.subjects.items.Item;
import org.shopkeeper.util.DateTimeGenerator;

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
