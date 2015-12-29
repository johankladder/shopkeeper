package org.shopkeeper.gui;

import javafx.stage.Stage;
import org.shopkeeper.gui.fx.WindowBuilderFX;
import org.shopkeeper.gui.swing.WindowBuilder;

/**
 * Created by typhooncoaster on 29-12-15.
 */
public class GuiChooser {

    public static void startGUI() {
        /*
                try {
            WindowBuilderFX.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
         */
        WindowBuilder.start();
    }
}
