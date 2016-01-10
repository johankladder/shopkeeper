package org.shopkeeper.preloader;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.shopkeeper.database.DatabaseHandler;
import org.shopkeeper.database.modules.DatabaseChooser;
import org.shopkeeper.database.modules.DatabaseTypes;
import org.shopkeeper.gui.GuiChooser;
import org.shopkeeper.preferences.PreferenceModule;
import org.shopkeeper.preferences.PreferenceLoader;
import org.shopkeeper.releases.ReleaseModule;
import org.shopkeeper.subjects.ModuleHandler;
import org.shopkeeper.util.AntiLockSystem;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by johankladder on 12/22/15.
 */
public class Preloader extends Application {

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
            System.out.println("STARTED PRELOADER");
            for (Runnable tasks : MODULES) {
                Thread thread1 = new Thread(tasks);
                thread1.start();
                    try {
                        AntiLockSystem.lockAndWait();
                        System.out.println("PRELOADER JOB FINISH:" + tasks.getClass().getName());
                        JOBCOUNTER++;
                        updateProgressBar(JOBCOUNTER);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                }

            }
//                    for(int i = 0; i < 30; i++) {
//                        ModuleHandler.getModule("itemmodule").add(new Item(null, "testitem"+i, 12.31, DateTimeGenerator.generateDateTimeNow()));
////                    }
            System.out.println("PRELOADER FINISHED");
            donePreloader();

        });
        thread.start();
    }

    private static void initPreloader() {
        MODULES.add(new PreferenceLoader());
        MODULES.add(new DatabaseHandler(DatabaseChooser.getDatabase(DatabaseTypes.DATABASETYPE_SQLLITE)));
        MODULES.add(new ModuleHandler());
        MODULES.add(new ReleaseModule());
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        // Images:
        Image image = new Image(PreferenceModule.LOGOPATH);

        imageView = new ImageView(image);
        // GUI:
        stage = primaryStage;
        stage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Pre-loading all assets");
        root = new BorderPane();
        PROGRESSBAR = new ProgressBar();
        root.setTop(imageView);
        root.setCenter(PROGRESSBAR);
        BorderPane labelBorder = new BorderPane();
        Label label = new Label(PreferenceModule.RELEASE_NOTES);
        label.setAlignment(Pos.CENTER);
        labelBorder.setCenter(label);
        root.setBottom(labelBorder);
        PROGRESSBAR.setMinWidth(600);

        Scene scene = new Scene(root, PreferenceModule.PRELOADER_WIDTH, PreferenceModule.PRELOADER_HEIGTH);
        primaryStage.setScene(scene);
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

