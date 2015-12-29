package org.shopkeeper.gui.fx;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.shopkeeper.gui.fx.controller.SubjectsViewControllerFX;
import org.shopkeeper.gui.fx.model.ItemModelFX;
import org.shopkeeper.gui.fx.view.AbstractViewFX;
import org.shopkeeper.gui.fx.view.ListViewFX;
import org.shopkeeper.preloader.Preloader;
import org.shopkeeper.subjects.SubjectHandler;

import javax.swing.*;
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

        // Init Item model:
        ArrayList<AbstractViewFX> views = new ArrayList<>();
        ListViewFX list = new ListViewFX(ITEMMODEL);
        views.add(list);
        ITEMMODEL.setViewPackage(views);

        // Init head and bottom:
        SubjectsViewControllerFX subjects_view_controller = new SubjectsViewControllerFX(null);
        BorderPane pane_head_and_buttom = new BorderPane();
        pane_head_and_buttom.setTop(subjects_view_controller);

        BorderPane release_pane = new BorderPane();
        Label label = new Label(Preloader.RELEASE_NOTES);
        label.setAlignment(Pos.CENTER);
        release_pane.setCenter(label);
        pane_head_and_buttom.setBottom(release_pane);


        primaryStage.setScene(new Scene(pane_head_and_buttom));
        primaryStage.setTitle("Shopkeeper");
        primaryStage.show();

        ITEMMODEL.add(null);
    }

}
