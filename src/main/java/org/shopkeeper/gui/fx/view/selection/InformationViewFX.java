package org.shopkeeper.gui.fx.view.selection;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import org.shopkeeper.gui.fx.controller.UpdateSubjectController;
import org.shopkeeper.gui.fx.model.selection.ListSelectionModelFX;
import org.shopkeeper.preferences.Preference;
import org.shopkeeper.subjects.parsers.SubjectMapGenerator;
import org.shopkeeper.subjects.subjecttypes.Subject;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Created by typhooncoaster on 6-1-16.
 */
public class InformationViewFX extends BorderPane implements AbstractSelectionViewFX {

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
        GridPane total_view_box = new GridPane();
        total_view_box.setVgap(10);

        setCenter(total_view_box);
        setMaxWidth(Double.MAX_VALUE);
        Subject subject = model.getSelectedSubject();
        Map subject_fields = subject.getFields();
        subject_fields = SubjectMapGenerator.createUserViewMap(subject_fields);

        Map map = subject.INIT_FIELD;
        map = SubjectMapGenerator.createUserViewMap(map);
        Map<String, Label> labels = new LinkedHashMap<>();
        map.forEach((k, v) -> {
            labels.put((String) k, new Label((String) k));
        });

        // For usage in a lamba expression:
        final int[] counter_y = {0};

        final Map finalSubject_fields = subject_fields;
        labels.forEach((k, v) -> {
            TextField text_field = new TextField(finalSubject_fields.get((k)).toString());
            total_view_box.add(v, 0, counter_y[0]);
            total_view_box.add(text_field, 1, counter_y[0]);
            counter_y[0] = counter_y[0] + 1;
        });

        total_view_box.add(new UpdateSubjectController(model), 0, counter_y[0], 2, 1);

        ColumnConstraints col1Constraints = new ColumnConstraints();
        col1Constraints.setPercentWidth(30);
        ColumnConstraints col2Constraints = new ColumnConstraints();
        col2Constraints.setPercentWidth(70);
        total_view_box.getColumnConstraints().addAll(col1Constraints, col2Constraints);
    }
}
