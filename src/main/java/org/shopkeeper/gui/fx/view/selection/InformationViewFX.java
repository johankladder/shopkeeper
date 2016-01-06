package org.shopkeeper.gui.fx.view.selection;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.shopkeeper.gui.fx.model.selection.ListSelectionModelFX;
import org.shopkeeper.preferences.Preference;
import org.shopkeeper.subjects.subjecttypes.Subject;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Created by typhooncoaster on 6-1-16.
 */
public class InformationViewFX extends BorderPane implements  AbstractSelectionViewFX{

    private static ListSelectionModelFX model = null;

    public InformationViewFX() {
        initComponents();
    }


    private void initComponents() {

    }


    @Override
    public void setModel(ListSelectionModelFX model) {
        this.model = model;
    }

    @Override
    public void updateView() {
        HBox total_view_box = new HBox();
//        total_view_box.setStyle("-fx-background-color: #336699;");
        total_view_box.setAlignment(Pos.CENTER);

        setCenter(total_view_box);
        Subject subject = model.getSelectedSubject();
        Map subject_fields = subject.getFields();

        Map map = subject.INIT_FIELD;
        Map<String, Label> labels = new LinkedHashMap<>();
        map.forEach((k, v) -> {
            labels.put((String) k, new Label((String) k));
        });

        VBox value_labels = new VBox();
        value_labels.setAlignment(Pos.BASELINE_LEFT);
        VBox values = new VBox();
        values.setAlignment(Pos.BASELINE_LEFT);

        labels.forEach((k,v) -> {
            value_labels.getChildren().add(v);
            Label label = new Label(subject_fields.get((k)).toString());
            values.getChildren().add(label);
        } );

        total_view_box.getChildren().addAll(value_labels, values);

    }
}
