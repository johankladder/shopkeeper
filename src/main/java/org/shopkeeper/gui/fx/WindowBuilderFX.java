package org.shopkeeper.gui.fx;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.shopkeeper.gui.fx.model.ItemModelFX;
import org.shopkeeper.gui.fx.view.AbstractViewFX;
import org.shopkeeper.gui.fx.view.ListViewFX;
import org.shopkeeper.subjects.SubjectHandler;

import java.util.ArrayList;

/**
 * Created by typhooncoaster on 29-12-15.
 */
public class WindowBuilderFX {
    private static ItemModelFX ITEMMODEL = new ItemModelFX(SubjectHandler.getModule("itemmodule"));


    public static void start(Stage primaryStage) throws Exception {
        // Set to full screen -> Still with visible topbars:
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());

        // Init testviews:
        ArrayList<AbstractViewFX> views = new ArrayList<>();
        ListViewFX list = new ListViewFX(ITEMMODEL);
        views.add(list);
        ITEMMODEL.setViewPackage(views);

        primaryStage.setScene(new Scene(list));
        primaryStage.setTitle("Shopkeeper");
        primaryStage.show();

        ITEMMODEL.add(null);
    }

}