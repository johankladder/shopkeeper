package org.shopkeeper.gui.swing.completeviews;

/**
 * Created by typhooncoaster on 29-12-15.
 */

import org.shopkeeper.gui.swing.WindowBuilder;
import org.shopkeeper.gui.swing.view.AbstractView;
import org.shopkeeper.gui.swing.view.ListView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * Created by typhooncoaster on 29-12-15.
 */
public class CompleteViewCustomer {

    // Add views to model

    public static JPanel getView() {
        JPanel panel = new JPanel(new BorderLayout());
        ArrayList<AbstractView> views = new ArrayList<>();
        ListView list = new ListView(WindowBuilder.CUSTOMERMODEL);
        views.add(list);
        WindowBuilder.CUSTOMERMODEL.setViewPackage(views);
        WindowBuilder.CUSTOMERMODEL.add(null);
        panel.add(list, BorderLayout.CENTER);
        return panel;

    }
}

