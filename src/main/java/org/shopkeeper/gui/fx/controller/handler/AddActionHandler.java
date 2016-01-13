package org.shopkeeper.gui.fx.controller.handler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.shopkeeper.gui.fx.model.selection.ListSelectionModelFX;
import org.shopkeeper.subjects.parsers.SubjectMapGenerator;
import org.shopkeeper.subjects.subjecttypes.Subject;

/**
 * Created by typhooncoaster on 13-1-16.
 */
public class AddActionHandler implements EventHandler<ActionEvent> {

    private ListSelectionModelFX model;
    public AddActionHandler(ListSelectionModelFX model) {
        this.model = model;
    }


    @Override
    public void handle(ActionEvent event) {
        Subject subject = SubjectMapGenerator.createSubjectFromMap(model.getSubjectType(), model.getMap());
    }
}
