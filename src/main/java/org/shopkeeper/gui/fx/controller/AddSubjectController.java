package org.shopkeeper.gui.fx.controller;

import javafx.scene.control.Button;
import org.shopkeeper.gui.fx.controller.handler.AddActionHandler;
import org.shopkeeper.gui.fx.model.selection.ListSelectionModelFX;

/**
 * Created by typhooncoaster on 12-1-16.
 */
public class AddSubjectController extends Button{

    private static final String BUTTON_TEXT = "ADD";
    private ListSelectionModelFX model;
    public AddSubjectController(ListSelectionModelFX model) {
        this.model = model;
        init();
    }

    private void init() {
        setText(BUTTON_TEXT);
        setMaxWidth(Double.MAX_VALUE);
        setOnAction(new AddActionHandler(model));
    }
}
