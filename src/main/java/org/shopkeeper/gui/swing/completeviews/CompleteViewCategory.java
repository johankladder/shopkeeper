package org.shopkeeper.gui.swing.completeviews;

/**
 * Created by typhooncoaster on 29-12-15.
 */

import org.shopkeeper.gui.swing.WindowBuilder;
import org.shopkeeper.gui.swing.view.AbstractView;
import org.shopkeeper.gui.swing.view.ListView;
import org.shopkeeper.gui.swing.view.selection.AbstractSelectionView;
import org.shopkeeper.gui.swing.view.selection.InformationView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * Created by typhooncoaster on 29-12-15.
 */
public class CompleteViewCategory {

    // Add views to model

    public static JPanel getView() {
        JPanel panel = new JPanel(new BorderLayout());

        InformationView info_view = new InformationView();
        ArrayList<AbstractSelectionView> views_list = new ArrayList<>();
        views_list.add(info_view);
        ListView list = new ListView(WindowBuilder.CATEGORYMODEL, views_list);

        ArrayList<AbstractView> views = new ArrayList<>();
        views.add(list);
        WindowBuilder.CATEGORYMODEL.setViewPackage(views);
        WindowBuilder.CATEGORYMODEL.updateViews();
        panel.add(list, BorderLayout.CENTER);
        return panel;

    }
}

