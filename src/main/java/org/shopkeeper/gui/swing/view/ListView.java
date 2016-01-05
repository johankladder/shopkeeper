package org.shopkeeper.gui.swing.view;

import org.shopkeeper.gui.swing.model.selection.*;
import org.shopkeeper.gui.swing.model.selection.ListSelectionModel;
import org.shopkeeper.gui.swing.model.subjects.AbstractModel;
import org.shopkeeper.preferences.Preference;
import org.shopkeeper.subjects.subjecttypes.Subject;

import javax.swing.*;
import java.awt.*;

/**
 * Created by typhooncoaster on 29-12-15.
 */
public class ListView extends JScrollPane implements AbstractView{

    private AbstractModel MODEL = null;
    private JList LIST = new JList();

    public ListView(AbstractModel model) {
        MODEL = model;
        setViewportView(LIST);
        setPreferredSize(new Dimension(Preference.PREF_WIDTH_LIST_CONTAINERS, Preference.PREF_HEIGHT_LIST_CONTAINERS));
        LIST.addListSelectionListener(new ListSelectionModel());
    }

    @Override
    public void updateView() {
        DefaultListModel model = new DefaultListModel();
        for(Subject subject : MODEL.getSubjects()) {
           model.addElement(subject);
        }
        LIST.setModel(model);
    }
}
