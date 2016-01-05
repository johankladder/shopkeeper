package org.shopkeeper.gui.swing.model.selection;

import org.shopkeeper.gui.swing.view.selection.AbstractSelectionView;
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
    private static ArrayList<AbstractSelectionView> VIEWS = new ArrayList<>();

    public void setViewPackage(ArrayList<AbstractSelectionView> views) {
        if(views != null) {
            VIEWS = views;
            for (AbstractSelectionView view : views) {
                view.setModel(this);
            }
        }

    }

    public void setSelectedSubject(Subject subject) {
        if(subject != null) {
            SELECTED_SUBJECT = subject;
            updateViews();
        }
    }

    public Subject getSelectedSubject() {
        return SELECTED_SUBJECT;
    }

    private void updateViews() {
        for(AbstractSelectionView view : VIEWS) {
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
