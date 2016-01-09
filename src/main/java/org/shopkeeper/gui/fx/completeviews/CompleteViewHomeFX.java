package org.shopkeeper.gui.fx.completeviews;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import org.shopkeeper.gui.fx.WindowBuilderFX;
import org.shopkeeper.gui.fx.view.releases.ReleaseView;

/**
 * Created by typhooncoaster on 29-12-15.
 */
public class CompleteViewHomeFX {
    public static BorderPane getView() {
        BorderPane panel = new BorderPane();
        Image image = new Image("/images/preloader_image.png");
        ReleaseView release_view = new ReleaseView(WindowBuilderFX.RELEASE_MODEL);
        WindowBuilderFX.RELEASE_MODEL.setReleaseView(release_view);
        WindowBuilderFX.RELEASE_MODEL.updateViews();
        ImageView imageView = new ImageView(image);
        panel.setCenter(imageView);
        panel.setBottom(release_view);
        return panel;
    }

    }
