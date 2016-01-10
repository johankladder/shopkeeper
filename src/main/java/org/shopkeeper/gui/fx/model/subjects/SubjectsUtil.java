package org.shopkeeper.gui.fx.model.subjects;

import org.apache.commons.lang3.StringUtils;
import org.shopkeeper.subjects.parsers.SubjectMapGenerator;
import org.shopkeeper.subjects.subjecttypes.Subject;
import java.util.Map;


public class SubjectsUtil {

    /**
     * Creates a subject with the values that where gathered using the informationview (when user tries to update a subject)
     * Please read documentation from other method mentioned below for more information about this parse.
     * @param subject The 'normal' subject, whom liked to be updated
     * @param map The map with updated values
     * @return The subject with the new updated values.
     * @see SubjectMapGenerator#updateSubjectWithMap(Subject, Map)
     * @see org.shopkeeper.gui.fx.view.selection.InformationViewFX
     */
    public static Subject createSubjectFromUpdateSubjectMap(Subject subject, Map map) {
        Map subjectMap = subject.getFields();
        map.forEach((k,v)-> {
            if(!StringUtils.contains((String)k, "id")) {
                if (v instanceof String) {
                    subjectMap.put(k, v);
                }
                if (v instanceof javafx.scene.control.TextField) {
                    javafx.scene.control.TextField valueField = (javafx.scene.control.TextField) v;
                    subjectMap.put(k, valueField.getText());
                }
            }
        });
        return SubjectMapGenerator.updateSubjectWithMap(subject, subjectMap);
    }


}
