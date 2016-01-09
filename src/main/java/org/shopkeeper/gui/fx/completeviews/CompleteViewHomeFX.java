package org.shopkeeper.gui.fx.completeviews;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import org.shopkeeper.gui.fx.WindowBuilderFX;
import org.shopkeeper.gui.fx.view.releases.NotesView;
import org.shopkeeper.gui.fx.view.releases.ReleaseView;
import org.shopkeeper.gui.fx.view.selection.AbstractSelectionViewFX;
import org.shopkeeper.preferences.Preference;

import java.util.ArrayList;

/**
 * Created by typhooncoaster on 29-12-15.
 */
public class CompleteViewHomeFX {
    public static BorderPane getView() {
        BorderPane panel = new BorderPane();
        Image image = new Image("/images/preloader_image.png");

        ArrayList<AbstractSelectionViewFX> release_views = new ArrayList<>();
        NotesView notes_view = new NotesView();
        release_views.add(notes_view);
        ReleaseView release_view = new ReleaseView(WindowBuilderFX.RELEASE_MODEL, release_views);

        WindowBuilderFX.RELEASE_MODEL.setReleaseView(release_view);
        WindowBuilderFX.RELEASE_MODEL.updateViews();
        ImageView imageView = new ImageView(image);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 0, 0, 0)); // TODO CSS
        grid.setHgap(10);

        grid.add(release_view,0,0,1,1);
        grid.add(notes_view,1,0,1,1);
        ColumnConstraints col1Constraints = new ColumnConstraints();
        ColumnConstraints col2Constraints = new ColumnConstraints();
        col1Constraints.setPercentWidth(30);
        col2Constraints.setPercentWidth(70);
        grid.getColumnConstraints().addAll(col1Constraints,col2Constraints);
        release_view.setMinHeight(1); // reset default min height
        notes_view.setMinHeight(1); // reset default min height
        release_view.setMaxHeight(Preference.RELEASE_NOTES_HEIGHT);
        notes_view.setMaxHeight(Preference.RELEASE_NOTES_HEIGHT);
        panel.setCenter(imageView);
        panel.setBottom(grid);
        return panel;
    }

    }
