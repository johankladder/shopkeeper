package org.shopkeeper.gui.swing.completeviews;

import org.shopkeeper.gui.swing.WindowBuilder;
import org.shopkeeper.gui.swing.view.AbstractView;
import org.shopkeeper.gui.swing.view.ListView;
import org.shopkeeper.gui.swing.view.selection.AbstractSelectionView;
import org.shopkeeper.gui.swing.view.selection.InformationView;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by typhooncoaster on 29-12-15.
 */
public class CompleteViewItem {

    // Add views to model

    public static JPanel getView() {
        JPanel panel = new JPanel(new BorderLayout());

        InformationView info_view = new InformationView();
        ArrayList<AbstractSelectionView> views_list = new ArrayList<>();
        views_list.add(info_view);
        ListView list = new ListView(WindowBuilder.ITEMMODEL, views_list);

        ArrayList<AbstractView> views = new ArrayList<>();
        views.add(list);
        WindowBuilder.ITEMMODEL.setViewPackage(views);
        WindowBuilder.ITEMMODEL.updateViews();
        panel.add(list, BorderLayout.CENTER);
        panel.add(info_view, BorderLayout.SOUTH);
        return panel;

    }
}
