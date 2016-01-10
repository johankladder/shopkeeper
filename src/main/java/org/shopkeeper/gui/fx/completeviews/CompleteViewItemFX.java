package org.shopkeeper.gui.fx.completeviews;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import org.shopkeeper.gui.fx.WindowBuilderFX;
import org.shopkeeper.gui.fx.view.AbstractViewFX;
import org.shopkeeper.gui.fx.view.ListViewFX;
import org.shopkeeper.gui.fx.view.selection.SelectionViewFX;
import org.shopkeeper.gui.fx.view.selection.InformationViewFX;

import java.awt.*;
import java.awt.Image;
import java.util.ArrayList;

/**
 * Created by typhooncoaster on 29-12-15.
 */
public class CompleteViewItemFX {

    public static BorderPane getView() {
        BorderPane panel = new BorderPane();

        InformationViewFX info_view = new InformationViewFX();
        ArrayList<SelectionViewFX> views_list = new ArrayList<>();
        views_list.add(info_view);
        ListViewFX list = new ListViewFX(WindowBuilderFX.ITEMMODEL, views_list);

        ArrayList<AbstractViewFX> views = new ArrayList<>();
        views.add(list);
        WindowBuilderFX.ITEMMODEL.setViewPackage(views);
        WindowBuilderFX.ITEMMODEL.updateViews();

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 0, 0, 0)); // TODO CSS
        grid.setHgap(10);
        grid.add(list,0,0,1,4);
        grid.add(info_view,1,0,1,1);

        javafx.scene.image.Image image = new javafx.scene.image.Image("/images/head.png");
        ImageView imageView = new ImageView(image);
        BorderPane imagepane = new BorderPane();
        imagepane.setCenter(imageView);
        grid.add(imagepane,1,1,1,1);
        GridPane.setVgrow(list, Priority.ALWAYS);
        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        col1.setPercentWidth(75);
        col2.setPercentWidth(25);
        grid.getColumnConstraints().addAll(col1,col2);
        panel.setCenter(grid);

        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        RowConstraints row3 = new RowConstraints();
        RowConstraints row4 = new RowConstraints();
        row1.setPercentHeight(25);
        row2.setPercentHeight(25);
        row3.setPercentHeight(25);
        row4.setPercentHeight(25);
        grid.getRowConstraints().addAll(row1,row2,row3,row4);
        return panel;

    }
}
