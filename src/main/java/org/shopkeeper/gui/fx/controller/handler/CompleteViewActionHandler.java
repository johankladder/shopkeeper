package org.shopkeeper.gui.fx.controller.handler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.shopkeeper.gui.fx.completeviews.CompleteViewModuleFX;
import org.shopkeeper.gui.fx.controller.SubjectsViewControllerFX;
import org.shopkeeper.gui.swing.controller.SubjectsViewController;
import org.shopkeeper.subjects.subjecttypes.SubjectTypes;


/**
 * Created by typhooncoaster on 29-12-15.
 */
public class CompleteViewActionHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        if(event.getSource() == SubjectsViewControllerFX.ITEM_BUTTON) {
            CompleteViewModuleFX.getCompleteview(SubjectTypes.ITEM);
        } else if (event.getSource() == SubjectsViewControllerFX.CUS_BUTTON) {
            System.out.println("test cus");
        } else if (event.getSource() == SubjectsViewController.CAT_BUTTON) {
            System.out.println("test cat");
        }
    }
}
