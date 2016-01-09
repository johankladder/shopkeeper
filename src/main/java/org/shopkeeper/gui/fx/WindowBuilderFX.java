package org.shopkeeper.gui.fx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.shopkeeper.gui.fx.completeviews.CompleteViewModuleFX;
import org.shopkeeper.gui.fx.controller.SubjectsCompleteViewControllerFX;
import org.shopkeeper.gui.fx.model.ReleaseModel;
import org.shopkeeper.gui.fx.model.subjects.CategoryModelFX;
import org.shopkeeper.gui.fx.model.subjects.CustomerModelFX;
import org.shopkeeper.gui.fx.model.subjects.ItemModelFX;
import org.shopkeeper.preferences.Preference;
import org.shopkeeper.subjects.ModuleHandler;

/**
 * Created by typhooncoaster on 29-12-15.
 */
public class WindowBuilderFX {
    // TODO Constructs the models in a model constructor, so its more obvious where to get them.
    // TODO Minimum size frame
    public static ItemModelFX ITEMMODEL = new ItemModelFX(ModuleHandler.getModule("itemmodule"));
    public static CustomerModelFX CUSTOMER_MODEL = new CustomerModelFX(ModuleHandler.getModule("customermodule"));
    public static CategoryModelFX CATEGORY_MODEL = new CategoryModelFX(ModuleHandler.getModule("categorymodule"));
    public static ReleaseModel RELEASE_MODEL = new ReleaseModel(ModuleHandler.RELEASE_MODULE);
    public static BorderPane MAIN = new BorderPane();


    public static void start(Stage primaryStage) throws Exception {
        // Set to full screen -> Still with visible topbars:
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());


        // Init head and bottom:
        SubjectsCompleteViewControllerFX subjects_view_controller = new SubjectsCompleteViewControllerFX();
        BorderPane pane_head_and_buttom = new BorderPane();
        pane_head_and_buttom.setTop(subjects_view_controller);

        pane_head_and_buttom.setCenter(MAIN);
        CompleteViewModuleFX.MAIN = MAIN;
        MAIN.setPadding(new Insets(5,10,5,10));

        BorderPane release_pane = new BorderPane();
        Label label = new Label(Preference.RELEASE_NOTES);
        label.setAlignment(Pos.CENTER);
        release_pane.setCenter(label);
        pane_head_and_buttom.setBottom(release_pane);


        primaryStage.setScene(new Scene(pane_head_and_buttom));
        primaryStage.setTitle("Shopkeeper");

        // Set begin screen:
        CompleteViewModuleFX.getCompleteview(null);
        primaryStage.show();

    }

}
