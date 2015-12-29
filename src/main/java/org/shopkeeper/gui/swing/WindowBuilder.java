package org.shopkeeper.gui.swing;

import org.shopkeeper.gui.swing.model.ItemModel;
import org.shopkeeper.gui.swing.view.AbstractView;
import org.shopkeeper.gui.swing.view.ListView;
import org.shopkeeper.subjects.SubjectHandler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by typhooncoaster on 29-12-15.
 */
public class WindowBuilder {
    public static JFrame WINDOW = new JFrame("Shopkeeper");
    private static ItemModel ITEMMODEL = new ItemModel(SubjectHandler.getModule("itemmodule"));

    public static void start() {
        ListView view = new ListView(ITEMMODEL);
        ArrayList<AbstractView> test = new ArrayList<>();
        test.add(view);
        ITEMMODEL.setViewPackage(test);

        WINDOW.add(view);
        WINDOW.setExtendedState(Frame.MAXIMIZED_BOTH); // Set fullscreen
        WINDOW.setVisible(true);    // Set visible
    }
}
