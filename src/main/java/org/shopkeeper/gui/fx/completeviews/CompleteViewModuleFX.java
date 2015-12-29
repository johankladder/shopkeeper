package org.shopkeeper.gui.fx.completeviews;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import org.shopkeeper.gui.swing.completeviews.CompleteViewCustomer;
import org.shopkeeper.subjects.subjecttypes.SubjectTypes;


/**
 * Created by typhooncoaster on 29-12-15.
 */
public class CompleteViewModuleFX {
    public static BorderPane MAIN = null;


    public static void getCompleteview(Integer subjectType) {
        if (subjectType == SubjectTypes.ITEM) {
            BorderPane newView = CompleteViewItemFX.getView();
            MAIN.setTop(newView);
        } else if (subjectType == SubjectTypes.CATEGORY) {
            BorderPane newView = CompleteViewCategoryFX.getView();
            MAIN.setTop(newView);
        } else if (subjectType == SubjectTypes.CUSTOMER) {
            BorderPane newView = CompleteViewCustomerFX.getView();
            MAIN.setTop(newView);
        } else {

        }
    }
}
