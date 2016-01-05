package org.shopkeeper.gui.swing;

import org.shopkeeper.gui.swing.completeviews.CompleteViewModule;
import org.shopkeeper.gui.swing.controller.SubjectsViewController;
import org.shopkeeper.gui.swing.model.subjects.CategoryModel;
import org.shopkeeper.gui.swing.model.subjects.CustomerModel;
import org.shopkeeper.gui.swing.model.subjects.ItemModel;
import org.shopkeeper.preferences.Preference;
import org.shopkeeper.preloader.Preloader;
import org.shopkeeper.subjects.SubjectHandler;

import javax.swing.*;
import java.awt.*;

/**
 * Goal to keep the swing gui as light as possible.
 */
public class WindowBuilder {
    public static JFrame WINDOW = new JFrame("Shopkeeper");
    public static ItemModel ITEMMODEL = new ItemModel(SubjectHandler.getModule("itemmodule"));
    public static CustomerModel CUSTOMERMODEL = new CustomerModel(SubjectHandler.getModule("customermodule"));
    public static CategoryModel CATEGORYMODEL = new CategoryModel(SubjectHandler.getModule("categorymodule"));
    public static JPanel MAIN_PANEL = new JPanel();


    public static void start() {

        // Init head and buttom:
        SubjectsViewController subjects_view_controller = new SubjectsViewController(null);
        JPanel panel_with_head_and_buttom = new JPanel(new BorderLayout());
        panel_with_head_and_buttom.add(subjects_view_controller, BorderLayout.NORTH);

        // Init the 'Main"- panel -> For projecting the complete views.
        panel_with_head_and_buttom.add(MAIN_PANEL, BorderLayout.CENTER);
        CompleteViewModule.MAIN = MAIN_PANEL;

        JPanel release_notes = new JPanel(new BorderLayout());
        release_notes.add(new JLabel(Preloader.RELEASE_NOTES, JLabel.CENTER));
        panel_with_head_and_buttom.add(release_notes, BorderLayout.SOUTH);

        WINDOW.add(panel_with_head_and_buttom);
        WINDOW.setExtendedState(Frame.MAXIMIZED_BOTH); // Set fullscreen
        WINDOW.setMinimumSize(new Dimension(Preference.MIN_WIDTH_APPLICATION, Preference.MIN_HEIGHT_APPLICATION));
        WINDOW.setVisible(true);    // Set visible
    }
}
