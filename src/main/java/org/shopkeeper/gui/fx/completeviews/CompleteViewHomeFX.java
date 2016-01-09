package org.shopkeeper.gui.fx.completeviews;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import org.shopkeeper.gui.fx.WindowBuilderFX;
import org.shopkeeper.gui.fx.view.AbstractViewFX;
import org.shopkeeper.gui.fx.view.ListViewFX;
import org.shopkeeper.gui.fx.view.ReleaseView;

import java.util.ArrayList;

/**
 * Created by typhooncoaster on 29-12-15.
 */
public class CompleteViewHomeFX {
    public static BorderPane getView() {
        BorderPane panel = new BorderPane();
        Image image = new Image("/images/preloader_image.png");
        ImageView imageView = new ImageView(image);
        panel.setCenter(imageView);
        panel.setBottom(new ReleaseView());
        return panel;
    }

    }
