package org.shopkeeper.gui.swing.view.selection;

import org.shopkeeper.gui.swing.model.selection.ListSelectionModel;
import org.shopkeeper.subjects.subjecttypes.Subject;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
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
        // Subject information:
        removeAll();
        initComponents();
        repaint();

        Subject subject = model.getSelectedSubject();
        Map subject_fields = subject.getFields();


        Map map = subject.INIT_FIELD;
        Map<String, JLabel> labels = new LinkedHashMap<>();
        map.forEach((k, v) -> {
            labels.put((String) k, new JLabel((String) k));
        });

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        final Integer[] counter_y = {0};

        labels.forEach((k,v) -> {
            c.gridy = counter_y[0];
            c.gridx = 0;
            add(v,c);
            c.gridx = 1;
            add(new JLabel(subject_fields.get((k)).toString()),c);
            counter_y[0] = counter_y[0] + 1;
        } );
        revalidate();
    }

    @Override
    public void setModel(ListSelectionModel model) {
        this.model = model;
    }
}
