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
    public static BorderPane LAST_ADDED = null;


    public static void getCompleteview(Integer subjectType) {
        if(subjectType != null) {
            if (subjectType == SubjectTypes.ITEM) {
                BorderPane newView = CompleteViewItemFX.getView();
                MAIN.setTop(newView);
                LAST_ADDED = newView;
            } else if (subjectType == SubjectTypes.CATEGORY) {
                BorderPane newView = CompleteViewCategoryFX.getView();
                MAIN.setTop(newView);
                LAST_ADDED = newView;
            } else if (subjectType == SubjectTypes.CUSTOMER) {
                BorderPane newView = CompleteViewCustomerFX.getView();
                MAIN.setTop(newView);
                LAST_ADDED = newView;
            } else {

            }
        } else {
            if(LAST_ADDED != null) {
                MAIN.setTop(null);
            }
            BorderPane newView = CompleteViewHomeFX.getView();
            MAIN.setCenter(newView);
        }
    }
}
