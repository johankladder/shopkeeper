package org.shopkeeper.gui.fx.view;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import org.shopkeeper.gui.fx.controller.handler.AddSubjectController;
import org.shopkeeper.gui.fx.model.subjects.AbstractModelFX;
import org.shopkeeper.subjects.parsers.SubjectMapGenerator;
import org.shopkeeper.util.Beatifier;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by typhooncoaster on 12-1-16.
 */
public class AddSubjectView extends BorderPane {

    private AbstractModelFX model;

    public AddSubjectView(AbstractModelFX model) {
        this.model = model;
        init();
    }

    // TODO Look for duplicates.
    private void init() {
        GridPane total_view_box = new GridPane();
        total_view_box.setVgap(10);

        setCenter(total_view_box);
        setMaxWidth(Double.MAX_VALUE);
        Map subject_fields = SubjectMapGenerator.generateEditableMapSubject(model.getSubjectTypeModel());
        subject_fields = SubjectMapGenerator.createUserViewMap(subject_fields);

        Map<String, Label> labels = new LinkedHashMap<>();
        subject_fields.forEach((k, v) -> {
            labels.put((String) k, new Label((String) k));
        });

        // For usage in a lamba expression:
        final int[] counter_y = {0};

        Map map_prepared_for_sending = new HashMap<>();

        labels.forEach((k, v) -> {
            TextField text_field = new TextField();
            map_prepared_for_sending.put(v.getText(), text_field);
            v.setText(Beatifier.beautifyString(v.getText()));
            total_view_box.add(v, 0, counter_y[0]);
            total_view_box.add(text_field, 1, counter_y[0]);
            counter_y[0] = counter_y[0] + 1;
        });
        //model.sendMap(map_prepared_for_sending);

        total_view_box.add(new AddSubjectController(), 0, counter_y[0], 2, 1);

        ColumnConstraints col1Constraints = new ColumnConstraints();
        col1Constraints.setPercentWidth(30);
        ColumnConstraints col2Constraints = new ColumnConstraints();
        col2Constraints.setPercentWidth(70);
        total_view_box.getColumnConstraints().addAll(col1Constraints, col2Constraints);

    }

}
