package org.shopkeeper.preloader;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.shopkeeper.database.DatabaseHandler;
import org.shopkeeper.database.modules.DatabaseChooser;
import org.shopkeeper.database.modules.DatabaseTypes;
import org.shopkeeper.gui.GuiChooser;
import org.shopkeeper.preferences.Preference;
import org.shopkeeper.preferences.PreferenceHandler;
import org.shopkeeper.releases.ReleaseModule;
import org.shopkeeper.subjects.ModuleHandler;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by johankladder on 12/22/15.
 */
public class Preloader extends Application {

    // Locking:
    public static final Boolean ready = false;

    // Modules:
    private static List<Runnable> MODULES = new ArrayList<>(); // The list with all the runnables:

    // Progress:
    private static Integer JOBCOUNTER = 0;
    private static ProgressBar PROGRESSBAR = null;

    // GUI:
    private static Stage stage = null;
    private static BorderPane root = null;
    private static ImageView imageView = null;

    private static void startPreloader() throws InterruptedException {
        Thread thread = new Thread(() -> {
            initPreloader();
            for (Runnable tasks : MODULES) {
                Thread thread1 = new Thread(tasks);
                thread1.start();
                synchronized (Preloader.ready) {
                    try {
                        ready.wait();
                        System.out.println("PRELOADER DONE:" + tasks.getClass().getName());
                        JOBCOUNTER++;
                        updateProgressBar(JOBCOUNTER);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }
//                    for(int i = 0; i < 30; i++) {
//                        ModuleHandler.getModule("itemmodule").add(new Item(null, "testitem"+i, 12.31, DateTimeGenerator.generateDateTimeNow()));
////                    }
            donePreloader();

        });
        thread.start();
    }

    private static void initPreloader() {
        MODULES.add(new PreferenceHandler());
        MODULES.add(new DatabaseHandler(DatabaseChooser.getDatabase(DatabaseTypes.DATABASETYPE_SQLLITE)));
        MODULES.add(new ModuleHandler());
        MODULES.add(new ReleaseModule());
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        // Images:
        Image image = new Image(Preference.LOGOPATH);
        imageView = new ImageView(image);
        // GUI:
        stage = primaryStage;
        primaryStage.setTitle("Pre-loading all assets");
        root = new BorderPane();
        PROGRESSBAR = new ProgressBar();
        root.setTop(imageView);
        root.setCenter(PROGRESSBAR);
        BorderPane labelBorder = new BorderPane();
        Label label = new Label(Preference.RELEASE_NOTES);
        label.setAlignment(Pos.CENTER);
        labelBorder.setCenter(label);
        root.setBottom(labelBorder);
        PROGRESSBAR.setMinWidth(600);
        primaryStage.setScene(new Scene(root, Preference.PRELOADER_WIDTH, Preference.PRELOADER_HEIGTH));
        primaryStage.show();
        primaryStage.setResizable(false);
        root.setPadding(new Insets(10, 0, 0, 0));

        // STARTING THE PRELOADER:
        Preloader.startPreloader();
    }

    private static void updateProgressBar(Integer procentage) {
        Platform.runLater(() -> {
            double p = new Float(procentage) / (float) MODULES.size();
            PROGRESSBAR.setProgress(p);
        });

    }

    private static void donePreloader() {
        Platform.runLater(() -> {
            root.setCenter(null);
            FadeTransition ft = new FadeTransition(Duration.millis(3000), imageView);
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.setCycleCount(1);
            ft.setAutoReverse(false);
            ft.setOnFinished(event -> {
                Platform.runLater(() -> {
                    stage.close();
                    GuiChooser.startGUI();
                });
            });
            ft.play();
        });
    }

    public static void main(String[] args) throws InterruptedException {
        launch(args);
    }

}

