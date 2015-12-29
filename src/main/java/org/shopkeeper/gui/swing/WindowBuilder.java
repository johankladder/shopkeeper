package org.shopkeeper.gui.swing;

import org.shopkeeper.gui.swing.controller.SubjectsViewController;
import org.shopkeeper.gui.swing.model.ItemModel;
import org.shopkeeper.gui.swing.view.AbstractView;
import org.shopkeeper.gui.swing.view.ListView;
import org.shopkeeper.preloader.Preloader;
import org.shopkeeper.subjects.SubjectHandler;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by typhooncoaster on 29-12-15.
 */
public class WindowBuilder {
    public static JFrame WINDOW = new JFrame("Shopkeeper");
    private static ItemModel ITEMMODEL = new ItemModel(SubjectHandler.getModule("itemmodule"));
    public static JPanel MAIN_PANEL = new JPanel();


    public static void start() {
        // Itemmodel init:
        ArrayList<AbstractView> itemViews = new ArrayList<>();
        ListView view = new ListView(ITEMMODEL);
        itemViews.add(view);
        ITEMMODEL.setViewPackage(itemViews);

        // Init head and buttom:
        SubjectsViewController subjects_view_controller = new SubjectsViewController(null);
        JPanel panel_with_head_and_buttom = new JPanel(new BorderLayout());
        panel_with_head_and_buttom.add(subjects_view_controller, BorderLayout.NORTH);

            // Init the 'Main"- panel -> For projecting the complete views.
            panel_with_head_and_buttom.add(MAIN_PANEL, BorderLayout.CENTER);

        JPanel release_notes = new JPanel(new BorderLayout());
        release_notes.add(new JLabel(Preloader.RELEASE_NOTES, JLabel.CENTER));
        panel_with_head_and_buttom.add(release_notes, BorderLayout.SOUTH);

        WINDOW.add(panel_with_head_and_buttom);
        WINDOW.setExtendedState(Frame.MAXIMIZED_BOTH); // Set fullscreen
        WINDOW.setVisible(true);    // Set visible
    }
}
