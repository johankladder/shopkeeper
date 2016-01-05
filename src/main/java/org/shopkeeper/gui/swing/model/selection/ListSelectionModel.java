package org.shopkeeper.gui.swing.model.selection;

import org.shopkeeper.gui.swing.view.AbstractView;
import org.shopkeeper.subjects.subjecttypes.Subject;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.ArrayList;

/**
 * Created by typhooncoaster on 5-1-16.
 */
public class ListSelectionModel implements ListSelectionListener {

    private static Subject SELECTED_SUBJECT = null;
    private static ArrayList<AbstractView> VIEWS = new ArrayList<>();

    public static void setViewPackage(ArrayList views) {
        VIEWS = views;
    }

    public static void setSelectedSubject(Subject subject) {
        SELECTED_SUBJECT = subject;
        updateViews();
    }

    private static void updateViews() {
        for(AbstractView view : VIEWS) {
            view.updateView();
        }
    }


    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()) {
            JList list = (JList) e.getSource();
            Subject subject = (Subject) list.getSelectedValue();
            setSelectedSubject(subject);
        }
    }
}
