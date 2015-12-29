package org.shopkeeper.gui.swing.view;

import org.shopkeeper.gui.swing.model.AbstractModel;
import org.shopkeeper.subjects.subjecttypes.Subject;

import javax.swing.*;

/**
 * Created by typhooncoaster on 29-12-15.
 */
public class ListView extends JList implements AbstractView{

    private AbstractModel MODEL = null;

    public ListView(AbstractModel model) {
        MODEL = model;
    }

    @Override
    public void updateView() {
        DefaultListModel model = new DefaultListModel();
        for(Subject subject : MODEL.getSubjects()) {
           model.addElement(subject);
        }
        setModel(model);
    }
}
