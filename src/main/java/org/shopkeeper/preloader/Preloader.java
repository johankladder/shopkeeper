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
                            JOBCOUNTER++;
                            updateProgressBar(JOBCOUNTER,JOBCOUNTER + " out of " + MODULES.size() + " jobs done...");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }

                }
            }
        });
       thread.start();
    }

    public static void initPreloader() {
        MODULES.add(new DatabaseHandler(DatabaseChooser.getDatabase(DatabaseTypes.DATABASETYPE_SQLLITE)));
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        // GUI preloader
        primaryStage.setTitle("The preloader");
        BorderPane root = new BorderPane();
        PROGRESSBAR = new ProgressBar();
        root.setBottom(PROGRESSBAR);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();

        // Start the preloader
        Preloader.startPreloader();
    }

    public static void updateProgressBar(Integer procentage, String message) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                System.out.println(message);
                double p = new Float(procentage)/new Float(MODULES.size());
                PROGRESSBAR.setProgress(p);
            }
        });

    }

    public static void main(String[] args) throws InterruptedException {
        launch(args);
    }


}
