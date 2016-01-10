package org.shopkeeper.gui.swing;

import org.shopkeeper.gui.swing.completeviews.CompleteViewModule;
import org.shopkeeper.gui.swing.controller.SubjectsViewController;
import org.shopkeeper.gui.swing.model.subjects.CategoryModel;
import org.shopkeeper.gui.swing.model.subjects.CustomerModel;
import org.shopkeeper.gui.swing.model.subjects.ItemModel;
import org.shopkeeper.preferences.PreferenceModule;
import org.shopkeeper.subjects.ModuleHandler;

import javax.swing.*;
import java.awt.*;

/**
 * Goal to keep the swing gui as light as possible.
 */
public class WindowBuilder {
    public static JFrame WINDOW = new JFrame("Shopkeeper");
    public static ItemModel ITEMMODEL = new ItemModel(ModuleHandler.getModule("itemmodule"));
    public static CustomerModel CUSTOMERMODEL = new CustomerModel(ModuleHandler.getModule("customermodule"));
    public static CategoryModel CATEGORYMODEL = new CategoryModel(ModuleHandler.getModule("categorymodule"));
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
        release_notes.add(new JLabel(PreferenceModule.RELEASE_NOTES, JLabel.CENTER));
        panel_with_head_and_buttom.add(release_notes, BorderLayout.SOUTH);

        WINDOW.add(panel_with_head_and_buttom);
        WINDOW.setExtendedState(Frame.MAXIMIZED_BOTH); // Set fullscreen
        WINDOW.setMinimumSize(new Dimension(PreferenceModule.MIN_WIDTH_APPLICATION, PreferenceModule.MIN_HEIGHT_APPLICATION));
        WINDOW.setVisible(true);    // Set visible
    }
}
