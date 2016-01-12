package org.shopkeeper.gui.fx.controller.handler;

import javafx.scene.control.Button;

/**
 * Created by typhooncoaster on 12-1-16.
 */
public class AddSubjectController extends Button{

    private static final String BUTTON_TEXT = "ADD";

    public AddSubjectController() {
        init();
    }

    private void init() {
        setText(BUTTON_TEXT);
        setMaxWidth(Double.MAX_VALUE);
    }
}
