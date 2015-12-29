package org.shopkeeper.gui.swing.controller.actionlisteners;

import org.shopkeeper.gui.swing.completeviews.CompleteViewModule;
import org.shopkeeper.gui.swing.controller.SubjectsViewController;
import org.shopkeeper.subjects.subjecttypes.SubjectTypes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by typhooncoaster on 29-12-15.
 */
public class CompleteViewActionListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == SubjectsViewController.ITEM_BUTTON) {
            CompleteViewModule.getCompleteview(SubjectTypes.ITEM);
        } else if(e.getSource() == SubjectsViewController.CAT_BUTTON) {
            System.out.println("test catbutton");
        } else if(e.getSource() == SubjectsViewController.CUS_BUTTON) {
            System.out.println("test customers");
        }
    }
}
