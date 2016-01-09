package org.shopkeeper.subjects.parsers;

import org.shopkeeper.subjects.ModuleHandler;
import org.shopkeeper.subjects.subjecttypes.Subject;
import org.shopkeeper.subjects.subjecttypes.SubjectTypes;
import org.shopkeeper.subjects.modules.SubjectModule;

import java.util.logging.Logger;

/**
 * This class communicates with the modules from the subjects. Well, it not only communicates but also chooses when to
 * use which module with a certain subject. This method is perfectly when you've changed your subject by hand and want
 * commit actions on it, like adding, updating or maybe even deleting.
 */
public class SubjectActionChooser {
    private final static Logger LOGGER = Logger.getLogger(SubjectActionChooser.class.getName());

    // Actions
    public static final int DELETE = 0;
    public static final int UPDATE = 1;
    public static final int ADD = 2;


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
                performAction(ModuleHandler.getModule("itemmodule"), subject, action);
            } else if (subject.TYPE == SubjectTypes.CATEGORY) {
                performAction(ModuleHandler.getModule("categorymodule"), subject, action);
            } else if (subject.TYPE == SubjectTypes.CUSTOMER) {
                performAction(ModuleHandler.getModule("customermodule"), subject, action);
            } else {
                LOGGER.warning("There is no module for a NULL object ;)!");
            }
        }
    }

    private static void performAction(SubjectModule module, Subject subject, Integer action) {
        if(module != null) {
            if (action != null) {
                if (action == DELETE) {
                    module.delete(subject);
                } else if (action == UPDATE) {
                    module.update(subject);
                } else if (action == ADD) {
                    module.add(subject);
                } else {
                    LOGGER.warning("The action can't be found! Do you want more actions? :)");
                }
            }
        }
    }
}
