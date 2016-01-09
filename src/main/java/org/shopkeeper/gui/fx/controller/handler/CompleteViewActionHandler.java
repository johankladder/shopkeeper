package org.shopkeeper.gui.fx.controller.handler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.shopkeeper.gui.fx.completeviews.CompleteViewModuleFX;
import org.shopkeeper.gui.fx.controller.SubjectsCompleteViewControllerFX;
import org.shopkeeper.subjects.subjecttypes.SubjectTypes;


/**
 * Created by typhooncoaster on 29-12-15.
 */
public class CompleteViewActionHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        if(event.getSource() == SubjectsCompleteViewControllerFX.ITEM_BUTTON) {
            CompleteViewModuleFX.getCompleteview(SubjectTypes.ITEM);
        } else if (event.getSource() == SubjectsCompleteViewControllerFX.CUS_BUTTON) {
            CompleteViewModuleFX.getCompleteview(SubjectTypes.CUSTOMER);
        } else if (event.getSource() == SubjectsCompleteViewControllerFX.CAT_BUTTON) {
            CompleteViewModuleFX.getCompleteview(SubjectTypes.CATEGORY);
        } else if (event.getSource() == SubjectsCompleteViewControllerFX.HOME_BUTTON) {
            CompleteViewModuleFX.getCompleteview(null);
        }
    }
}
