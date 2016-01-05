package org.shopkeeper.gui.swing.view.selection;

import com.sun.javafx.collections.MappingChange;
import org.shopkeeper.gui.swing.model.selection.*;
import org.shopkeeper.gui.swing.model.selection.ListSelectionModel;
import org.shopkeeper.gui.swing.view.AbstractView;
import org.shopkeeper.subjects.subjecttypes.Subject;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Created by typhooncoaster on 5-1-16.
 */
public class InformationView extends JPanel implements AbstractSelectionView {

    private ListSelectionModel model = null;

    public InformationView() {
        initComponents();
    }


    private void initComponents() {
        setLayout(new GridBagLayout());
    }


    @Override
    public void updateView() {
        Subject subject = model.getSelectedSubject();
        Map map = subject.INIT_FIELD;
        map.forEach((k, v) -> System.out.println(k + " + " + v));

    }

    @Override
    public void setModel(ListSelectionModel model) {
        this.model = model;
    }
}
