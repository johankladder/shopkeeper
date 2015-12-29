package org.shopkeeper.gui;

import javafx.stage.Stage;
import org.shopkeeper.gui.fx.WindowBuilderFX;
import org.shopkeeper.gui.swing.WindowBuilder;
import org.shopkeeper.preferences.Preference;

/**
 * Created by typhooncoaster on 29-12-15.
 */
public class GuiChooser {

    public static void startGUI() {

        if (Preference.GUI_TYPE == GuiTypes.FX) {
            try {
                WindowBuilderFX.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (Preference.GUI_TYPE == GuiTypes.SWING) {
            WindowBuilder.start();
        } else {

        }
    }
}
