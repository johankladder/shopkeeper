package org.shopkeeper.gui.fx.completeviews;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import org.shopkeeper.gui.fx.WindowBuilderFX;
import org.shopkeeper.gui.fx.view.AbstractViewFX;
import org.shopkeeper.gui.fx.view.ListViewFX;
import org.shopkeeper.gui.fx.view.selection.SelectionViewFX;
import org.shopkeeper.gui.fx.view.selection.InformationViewFX;

import java.util.ArrayList;

/**
 * Created by typhooncoaster on 29-12-15.
 */
public class CompleteViewCategoryFX {

    public static BorderPane getView() {
        BorderPane panel = new BorderPane();

        InformationViewFX info_view = new InformationViewFX();
        ArrayList<SelectionViewFX> views_list = new ArrayList<>();
        views_list.add(info_view);
        ListViewFX list = new ListViewFX(WindowBuilderFX.CATEGORY_MODEL, views_list);

        ArrayList<AbstractViewFX> views = new ArrayList<>();
        views.add(list);
        WindowBuilderFX.CATEGORY_MODEL.setViewPackage(views);
        WindowBuilderFX.CATEGORY_MODEL.updateViews();

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 0, 0, 0)); // TODO CSS
        grid.setHgap(10);
        grid.add(list,0,0,1,1);
        grid.add(info_view,1,0,1,1);
        ColumnConstraints col1Constraints = new ColumnConstraints();
        ColumnConstraints col2Constraints = new ColumnConstraints();
        col1Constraints.setPercentWidth(75);
        col2Constraints.setPercentWidth(25);
        grid.getColumnConstraints().addAll(col1Constraints,col2Constraints);
        panel.setCenter(grid);

        return panel;

    }

}
