package org.shopkeeper.gui.swing.completeviews;

/**
 * Created by typhooncoaster on 29-12-15.
 */

import org.shopkeeper.gui.swing.WindowBuilder;
import org.shopkeeper.gui.swing.view.AbstractView;
import org.shopkeeper.gui.swing.view.selection.AbstractSelectionView;
import org.shopkeeper.gui.swing.view.selection.InformationView;
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


        InformationView info_view = new InformationView();
        ArrayList<AbstractSelectionView> views_list = new ArrayList<>();
        views_list.add(info_view);

        ListView list = new ListView(WindowBuilder.CUSTOMERMODEL, views_list);

        ArrayList<AbstractView> views_model = new ArrayList<>();
        views_model.add(list);
        WindowBuilder.CUSTOMERMODEL.setViewPackage(views_model);
        WindowBuilder.CUSTOMERMODEL.updateViews();
        panel.add(list, BorderLayout.CENTER);
        panel.add(info_view, BorderLayout.SOUTH);
        return panel;

    }
}

