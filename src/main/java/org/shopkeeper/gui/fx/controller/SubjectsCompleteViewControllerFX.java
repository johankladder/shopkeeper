package org.shopkeeper.gui.fx.controller;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import org.shopkeeper.gui.fx.controller.handler.CompleteViewActionHandler;

/**
 * Inspired by: http://stackoverflow.com/questions/12830402/javafx-2-buttons-size-fill-width-and-are-each-same-width
 * Answered by: jewelsea
 * Found: 29-12-2015
 */
public class SubjectsCompleteViewControllerFX extends HBox {

    public static Button ITEM_BUTTON = new Button("Items");
    public static Button CAT_BUTTON = new Button("Categories");
    public static Button CUS_BUTTON = new Button("Customers");
    public static Button HOME_BUTTON = new Button("Home");

    // Listeners:
    static {
        CompleteViewActionHandler handler = new CompleteViewActionHandler();
        ITEM_BUTTON.setOnAction(handler);
        CUS_BUTTON.setOnAction(handler);
        CAT_BUTTON.setOnAction(handler);
        HOME_BUTTON.setOnAction(handler);
    }

    public SubjectsCompleteViewControllerFX() {
        super(10);
        Button[] buttons = {HOME_BUTTON, ITEM_BUTTON, CAT_BUTTON, CUS_BUTTON};
        getChildren().addAll(buttons);
        for (Button b : buttons) {
            HBox.setHgrow(b, Priority.ALWAYS);
            b.setMaxWidth(Double.MAX_VALUE);
        }
    }


    @Override
    protected void layoutChildren() {
        double minPrefWidth = calculatePrefChildWidth();
        getChildren().stream().filter(n -> n instanceof Button).forEach(n -> ((Button) n).setMinWidth(minPrefWidth));
        super.layoutChildren();
    }

    private double calculatePrefChildWidth() {
        double minPrefWidth = 0;
        for (Node n : getChildren()) {
            minPrefWidth = Math.max(minPrefWidth, n.prefWidth(-1));
        }
        return minPrefWidth;
    }
}


