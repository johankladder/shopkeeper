package org.shopkeeper.preloader;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.joda.time.DateTime;
import org.shopkeeper.database.DatabaseHandler;
import org.shopkeeper.database.modules.DatabaseChooser;
import org.shopkeeper.database.modules.DatabaseTypes;
import org.shopkeeper.gui.GuiChooser;
import org.shopkeeper.preferences.PreferenceHandler;
import org.shopkeeper.subjects.SubjectHandler;
import org.shopkeeper.subjects.subjecttypes.categories.Category;
import org.shopkeeper.subjects.subjecttypes.customer.Customer;
import org.shopkeeper.subjects.subjecttypes.items.Item;
import org.shopkeeper.util.DateTimeGenerator;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
    private static final Integer PRELOADER_WIDTH = 600;
    private static final Integer PRELOADER_HEIGTH = 300;

    // NOTES:
    private static final String RELEASE_NUMBER = "TEST";
    public static final String RELEASE_NOTES = "Version: " + RELEASE_NUMBER + " by Johan Kladder";


    private static void startPreloader() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                initPreloader();
                for (Runnable tasks : MODULES) {
                    Thread thread = new Thread(tasks);
                    thread.start();
                    synchronized (Preloader.ready) {
                        try {
                            ready.wait();
                            System.out.println("done with " + tasks.getClass().getName());
                            JOBCOUNTER++;
                            updateProgressBar(JOBCOUNTER);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }

                }
                try {
                    Thread.sleep(5000); // TODO For showing the logo and information
//                    for(int i = 0; i < 30; i++) {
//                        SubjectHandler.getModule("itemmodule").add(new Item(null, "testitem"+i, 12.31, DateTimeGenerator.generateDateTimeNow()));
//                    }
                    closePreloader();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }

    private static void initPreloader() {
        MODULES.add(new PreferenceHandler());
        MODULES.add(new DatabaseHandler(DatabaseChooser.getDatabase(DatabaseTypes.DATABASETYPE_SQLLITE)));
        MODULES.add(new SubjectHandler());
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        // Images:
        Image image = new Image("/images/preloader_image.jpg");
        ImageView imageView = new ImageView(image);
        // GUI:
        stage = primaryStage;
        primaryStage.setTitle("Shopkeeper - pre-loader");
        BorderPane root = new BorderPane();
        PROGRESSBAR = new ProgressBar();
        root.setTop(imageView);
        root.setCenter(PROGRESSBAR);
        BorderPane labelBorder = new BorderPane();
        Label label = new Label(RELEASE_NOTES);
        label.setAlignment(Pos.CENTER);
        labelBorder.setCenter(label);
        root.setBottom(labelBorder);
        PROGRESSBAR.setMinWidth(600);
        primaryStage.setScene(new Scene(root, PRELOADER_WIDTH, PRELOADER_HEIGTH));
        primaryStage.show();

        // STARTING THE PRELOADER:
        Preloader.startPreloader();
    }

    private static void updateProgressBar(Integer procentage) {
        Platform.runLater(() -> {
            double p = new Float(procentage) / new Float(MODULES.size());
            PROGRESSBAR.setProgress(p);
        });

    }


    public static void main(String[] args) throws InterruptedException {
        launch(args);
    }

    private static void closePreloader() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                stage.close();
                GuiChooser.startGUI();
                // TODO Build the application its GUI
            }
        });
    }


}
