package org.shopkeeper.gui.fx.model.subjects;

import org.shopkeeper.gui.fx.WindowBuilderFX;
import org.shopkeeper.subjects.subjecttypes.SubjectTypes;

/**
 * Created by typhooncoaster on 9-1-16.
 */
public class ModelUtil {

    public static AbstractModelFX getModelFromSubjectType(Integer subjectType) {
        if(subjectType == SubjectTypes.ITEM) {
            return WindowBuilderFX.ITEMMODEL;
        } else if(subjectType == SubjectTypes.CATEGORY) {
            return WindowBuilderFX.CATEGORY_MODEL;
        } else if(subjectType == SubjectTypes.CUSTOMER) {
            return WindowBuilderFX.CUSTOMER_MODEL;
        } else {
            return null;
        }
    }

}
