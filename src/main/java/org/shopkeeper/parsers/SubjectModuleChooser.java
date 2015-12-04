package org.shopkeeper.parsers;

import org.shopkeeper.subjects.Subject;
import org.shopkeeper.subjects.SubjectTypes;
import org.shopkeeper.subjectsmodules.CategoryModule;
import org.shopkeeper.subjectsmodules.CustomerModule;
import org.shopkeeper.subjectsmodules.ItemModule;
import org.shopkeeper.subjectsmodules.SubjectModule;

/**
 * Created by typhooncoaster on 4-12-15.
 */
public class SubjectModuleChooser {

    // Actions
    public static final int DELETE = 0;
    public static final int UPDATE = 1;
    public static final int ADD = 2;

    // Modules
    public static final ItemModule ITEMMODULE = new ItemModule();
    public static final CategoryModule CATEGORYMODULE = new CategoryModule();
    public static final CustomerModule CUSTOMER_MODULE = new CustomerModule();


    public static void parseSubject(Subject subject, Integer action) {
        if (subject.TYPE == SubjectTypes.ITEM) {
            performAction(ITEMMODULE, subject, action);
        } else if (subject.TYPE == SubjectTypes.CATEGORY) {
            performAction(CATEGORYMODULE, subject, action);
        } else if (subject.TYPE == SubjectTypes.CUSTOMER) {
            performAction(CUSTOMER_MODULE, subject, action);
        } else {
            // Do nothing -> parsed subject was not valid.
        }
    }

    private static void performAction(SubjectModule module, Subject subject, Integer action) {
        if (action == DELETE) {
            module.delete(subject);
        } else if (action == UPDATE) {
            module.update(subject);
        } else if (action == ADD) {
            module.add(subject);
        } else {
            // Do nothing -> not a valid action parameter.
        }
    }
}
