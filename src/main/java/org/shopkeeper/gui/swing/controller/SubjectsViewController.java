package org.shopkeeper.gui.swing.controller;

import org.shopkeeper.gui.swing.completeviews.CompleteViewModule;
import org.shopkeeper.gui.swing.controller.actionlisteners.CompleteViewActionListener;

import javax.swing.*;
import java.awt.*;

/**
 * Created by typhooncoaster on 29-12-15.
 */
public class SubjectsViewController extends JPanel {

    public static JButton ITEM_BUTTON = new JButton("Items");
    public static JButton CAT_BUTTON = new JButton("Categories");
    public static JButton CUS_BUTTON = new JButton("Customers");
    private static CompleteViewModule module = null;


    // Add action listeners:
    static {
        ITEM_BUTTON.addActionListener(new CompleteViewActionListener());
        CAT_BUTTON.addActionListener(new CompleteViewActionListener());
        CUS_BUTTON.addActionListener(new CompleteViewActionListener());
    }

    public SubjectsViewController(CompleteViewModule module) {
        this.module = module;
        init();
    }

    private void init() {
        setLayout(new GridLayout(1,3,10,0));
        add(ITEM_BUTTON);
        add(CAT_BUTTON);
        add(CUS_BUTTON);
    }
}
