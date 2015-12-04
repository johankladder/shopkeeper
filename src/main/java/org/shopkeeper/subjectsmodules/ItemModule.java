package org.shopkeeper.subjectsmodules;

import org.shopkeeper.subjects.Subject;

/**
 * Created by typhooncoaster on 4-12-15.
 */
public class ItemModule extends SubjectModule {
    @Override
    void add(Subject subject) {
        // Perform action -> ADD TO DATABASE OR SOMETHING
        addToList(subject);
    }

    @Override
    void update(Subject subject) {

    }

    @Override
    void delete(Subject subject) {
        // Perform action
        removeFromList(subject);
    }
}
