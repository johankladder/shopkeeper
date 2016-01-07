package org.shopkeeper.gui.fx.completeviews;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import org.shopkeeper.gui.fx.WindowBuilderFX;
import org.shopkeeper.gui.fx.view.AbstractViewFX;
import org.shopkeeper.gui.fx.view.ListViewFX;
import org.shopkeeper.gui.fx.view.selection.AbstractSelectionViewFX;
import org.shopkeeper.gui.fx.view.selection.InformationViewFX;

import java.util.ArrayList;

/**
 * Created by typhooncoaster on 29-12-15.
 */
public class CompleteViewItemFX {

    public static BorderPane getView() {
        BorderPane panel = new BorderPane();

        InformationViewFX info_view = new InformationViewFX();
        ArrayList<AbstractSelectionViewFX> views_list = new ArrayList<>();
        views_list.add(info_view);
        ListViewFX list = new ListViewFX(WindowBuilderFX.ITEMMODEL, views_list);

        ArrayList<AbstractViewFX> views = new ArrayList<>();
        views.add(list);
        WindowBuilderFX.ITEMMODEL.setViewPackage(views);
        WindowBuilderFX.ITEMMODEL.updateViews();

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 0, 0, 0)); // TODO CSS
        grid.setHgap(10);
        grid.add(list,0,0,2,1);
        grid.add(info_view,2,0,1,1);
        ColumnConstraints col1Constraints = new ColumnConstraints();
        col1Constraints.setPercentWidth(75);
        grid.getColumnConstraints().addAll(col1Constraints);
        panel.setCenter(grid);

        return panel;

    }
}
