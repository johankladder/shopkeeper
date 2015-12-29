package org.shopkeeper.gui.swing.completeviews;

import org.shopkeeper.gui.swing.WindowBuilder;
import org.shopkeeper.gui.swing.view.AbstractView;
import org.shopkeeper.gui.swing.view.ListView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by typhooncoaster on 29-12-15.
 */
public class CompleteViewItem {

    // Add views to model

    public static JPanel getView() {
        JPanel panel = new JPanel(new BorderLayout());
        ArrayList<AbstractView> views = new ArrayList<>();
        ListView list = new ListView(WindowBuilder.ITEMMODEL);
        views.add(list);
        WindowBuilder.ITEMMODEL.setViewPackage(views);
        WindowBuilder.ITEMMODEL.add(null);
        panel.add(list, BorderLayout.CENTER);
        return panel;

    }
}
