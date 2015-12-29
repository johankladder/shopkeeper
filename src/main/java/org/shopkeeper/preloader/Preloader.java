package org.shopkeeper.preloader;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.shopkeeper.database.DatabaseHandler;
import org.shopkeeper.database.modules.DatabaseChooser;
import org.shopkeeper.database.modules.DatabaseTypes;
import org.shopkeeper.preferences.PreferenceHandler;
import org.shopkeeper.subjects.SubjectHandler;
import org.shopkeeper.subjects.subjecttypes.categories.Category;
import org.shopkeeper.subjects.subjecttypes.customer.Customer;
import org.shopkeeper.util.DateTimeGenerator;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by johankladder on 12/22/15.
 */
public class Preloader extends Application {

    public static Boolean ready = false;
    private static List<Runnable> MODULES = new ArrayList<>(); // The list with all the runnables:
    private static Integer JOBCOUNTER = 0;
    private static ProgressBar PROGRESSBAR = null;
    private static Stage stage = null;

    public static void startPreloader() throws InterruptedException {
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
                    Thread.sleep(2000); // TODO For showing the logo and information
                    closePreloader();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }

    public static void initPreloader() {
        MODULES.add(new PreferenceHandler());
        MODULES.add(new DatabaseHandler(DatabaseChooser.getDatabase(DatabaseTypes.DATABASETYPE_SQLLITE)));
        MODULES.add(new SubjectHandler());
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        // GUI:
        stage = primaryStage;
        primaryStage.setTitle("Shopkeeper");
        BorderPane root = new BorderPane();
        PROGRESSBAR = new ProgressBar();
        root.setBottom(PROGRESSBAR);
        PROGRESSBAR.setMinWidth(600);
        primaryStage.setScene(new Scene(root, 600, 250));
        primaryStage.show();

        // STARTING THE PRELOADER:
        Preloader.startPreloader();
    }

    public static void updateProgressBar(Integer procentage) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                double p = new Float(procentage) / new Float(MODULES.size());
                PROGRESSBAR.setProgress(p);
            }
        });

    }


    public static void main(String[] args) throws InterruptedException {
        launch(args);
    }

    public static void closePreloader() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                stage.close();
                // TODO Build the application its GUI
            }
        });
    }


}
