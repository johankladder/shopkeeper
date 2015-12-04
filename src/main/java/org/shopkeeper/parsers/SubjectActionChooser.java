package org.shopkeeper.parsers;

import org.shopkeeper.subjects.Subject;
import org.shopkeeper.subjects.SubjectTypes;
import org.shopkeeper.subjectsmodules.CategoryModule;
import org.shopkeeper.subjectsmodules.CustomerModule;
import org.shopkeeper.subjectsmodules.ItemModule;
import org.shopkeeper.subjectsmodules.SubjectModule;

/**
 * This class communicates with the modules from the subjects. Well, it not only communicates but also chooses when to
 * use which module with a certain subject. This method is perfectly when you've changed your subject by hand and want
 * commit actions on it, like adding, updating or maybe even deleting.
 */
public class SubjectActionChooser {

    // Actions
    public static final int DELETE = 0;
    public static final int UPDATE = 1;
    public static final int ADD = 2;

    // Modules
    private static final ItemModule ITEMMODULE = new ItemModule();
    private static final CategoryModule CATEGORYMODULE = new CategoryModule();
    private static final CustomerModule CUSTOMER_MODULE = new CustomerModule();


    /**
     * Method for performing an action with a subject. This can be all sorts of actions. Most of them are already described
     * in the class doc. When perform an action, make sure to give the right subject and action to it, as it can work out
     * very badly for you if you don't ;)!
     * <p>
     * The actions can be found as static final values in this class. (DELETE, UPDATE, ADD)
     *
     * @param subject The subject that you like to perform an action on
     * @param action  The action that you like to perform on the subject
     */
    public static void actionOnSubject(Subject subject, Integer action) {
        if (subject != null) {
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
    }

    private static void performAction(SubjectModule module, Subject subject, Integer action) {
        if (action != null) {
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
}
