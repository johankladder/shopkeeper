package org.shopkeeper.gui.fx.completeviews;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.shopkeeper.gui.fx.WindowBuilderFX;
import org.shopkeeper.gui.fx.view.AbstractViewFX;
import org.shopkeeper.gui.fx.view.ListViewFX;
import org.shopkeeper.gui.fx.view.selection.AbstractSelectionViewFX;
import org.shopkeeper.gui.fx.view.selection.InformationViewFX;

import java.util.ArrayList;

/**
 * Created by typhooncoaster on 29-12-15.
 */
public class CompleteViewCustomerFX {

    public static BorderPane getView() {
        BorderPane panel = new BorderPane();

        InformationViewFX info_view = new InformationViewFX();
        ArrayList<AbstractSelectionViewFX> views_list = new ArrayList<>();
        views_list.add(info_view);
        ListViewFX list = new ListViewFX(WindowBuilderFX.CUSTOMER_MODEL, views_list);

        ArrayList<AbstractViewFX> views = new ArrayList<>();
        views.add(list);
        WindowBuilderFX.CUSTOMER_MODEL.setViewPackage(views);
        WindowBuilderFX.CUSTOMER_MODEL.updateViews();

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
