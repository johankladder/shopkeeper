package org.shopkeeper.gui.swing.view;

import org.shopkeeper.gui.swing.model.selection.ListSelectionModel;
import org.shopkeeper.gui.swing.model.subjects.AbstractModel;
import org.shopkeeper.gui.swing.view.selection.AbstractSelectionView;
import org.shopkeeper.preferences.PreferenceModule;
import org.shopkeeper.subjects.subjecttypes.Subject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by typhooncoaster on 29-12-15.
 */
public class ListView extends JScrollPane implements AbstractView{

    private AbstractModel MODEL = null;
    private JList LIST = new JList();
    private static ListSelectionModel SELECTION_MODEL = new ListSelectionModel();

    public ListView(AbstractModel model, ArrayList<AbstractSelectionView> views) {
        MODEL = model;
        setViewportView(LIST);
        setPreferredSize(new Dimension(PreferenceModule.PREF_WIDTH_LIST_CONTAINERS, PreferenceModule.PREF_HEIGHT_LIST_CONTAINERS));
        SELECTION_MODEL.setViewPackage(views);
        LIST.addListSelectionListener(SELECTION_MODEL);
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
