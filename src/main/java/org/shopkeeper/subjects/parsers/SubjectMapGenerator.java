package org.shopkeeper.subjects.parsers;

import org.apache.commons.lang3.StringUtils;
import org.shopkeeper.subjects.SubjectUtils;
import org.shopkeeper.subjects.subjecttypes.Subject;
import org.shopkeeper.subjects.subjecttypes.SubjectFields;
import org.shopkeeper.subjects.subjecttypes.SubjectTypes;
import org.shopkeeper.subjects.subjecttypes.categories.Category;
import org.shopkeeper.subjects.subjecttypes.customer.Customer;
import org.shopkeeper.subjects.subjecttypes.items.Item;
import org.shopkeeper.util.PriceGenerator;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;


public class SubjectMapGenerator {
    private final static Logger LOGGER = Logger.getLogger(SubjectMapGenerator.class.getName());


    /**
     * Generates a map that is filled with editable keys and null values for an subject. These fields are chosen
     * according the Subject-type, so thats the reason for the parameter. When a subject-type can't be matched to
     * the subject type in the parameter, this method will return a empty map.
     *
     * @param subjectType The subject-type for creating the map
     * @return The map filled with editable keys and null values.
     */
    public static Map generateEditableMapSubject(Integer subjectType) {
        return generateMap(subjectType);
    }


    private static Map generateMap(Integer subjectType) {
        Map<String, String> map = new LinkedHashMap<>();
        Map subjectMap = null;

        if (subjectType == SubjectTypes.CUSTOMER) {
            subjectMap = Customer.getInitFields();
        } else if (subjectType == SubjectTypes.ITEM) {
            subjectMap = Item.getInitFields();
        } else if (subjectType == SubjectTypes.CATEGORY) {
            subjectMap = Category.getInitFields();
        } else {
            return map;
        }

        // Fill the map:
        subjectMap.forEach((k, v) -> {
            if (SubjectUtils.isEditable((String) k)) {
                map.put((String) k, null);
            }
        });

        return map;
    }


    /**
     * Creates a new map from the fields-map from a subject with only the interesting values for the user. Will return
     * a empty map if the given map is null or empty. This method is handy for creating the value maps in a user-view i.e
     * a information-view for a subject.
     *
     * @param fieldsMap The 'normal' fields-map from a subject
     * @return A map with values that are interesting for the user to see.
     * @see Subject#getFields()
     */
    public static Map createUserViewMap(Map fieldsMap) {
        if (!fieldsMap.isEmpty() && fieldsMap != null) {
            Map userMap = new LinkedHashMap<>();

            fieldsMap.forEach((k, v) -> {
                if (!StringUtils.contains((String) k, "tablename")) {
                    userMap.put(k, v);
                }
            });
            return userMap;

        } else {
            return new LinkedHashMap<>();
        }
    }

    /**
     * Updates a given subject from an also given map. Most of the time this map is gathered from an updating view or add
     * panel. This method is deciding which subject type the subject is and will handle the updating logic from there on.
     * <p>
     * When problems occur when this method is trying to update a subject, the method will return the given object.
     * Make note that it really tries also to update parts from a subject, also when it fails on other parts in the map.
     * <p>
     * Will return null, when subject is null.
     *
     * @param subject The subject liked to be updated from a map
     * @param map     The map with all the given updates
     * @return The updated subject
     */
    // TODO LOG
    public static Subject updateSubjectWithMap(Subject subject, Map map) {
        if (subject != null) {
            if (subject.TYPE == SubjectTypes.ITEM) {
                return updateWithMapItem(subject, map);
            } else if (subject.TYPE == SubjectTypes.CATEGORY) {
                return updateWithMapCategory(subject, map);
            } else if (subject.TYPE == SubjectTypes.CUSTOMER) {
                return updateWithMapCustomer(subject, map);
            }
            return subject;
        } else {
            return null;
        }
    }

    private static Subject updateWithMapItem(Subject subject, Map map) {
        Item item = (Item) subject;
        item = (Item) setNameSubjectFromMap(item, map);
        if (map.containsKey(SubjectFields.ITEM_PRICE)) {
            try {
                Double price = PriceGenerator.getPriceFromString((String) map.get(SubjectFields.ITEM_PRICE));
                if (price != null) {
                    item.setPrice(price);
                } else {
                    return subject;
                }
            } catch (NumberFormatException e) {
                return subject;
            }
        }
        return subject;
    }

    private static Subject updateWithMapCustomer(Subject subject, Map map) {
        Customer cus = (Customer) subject;
        cus = (Customer) setNameSubjectFromMap(cus, map);

        if (map.containsKey(SubjectFields.CUSTOMER_ADDRESS)) {
            cus.setAddress(StringUtils.trimToNull((String) map.get(SubjectFields.CUSTOMER_ADDRESS)));
        }
        if (map.containsKey(SubjectFields.CUSTOMER_EMAIL)) {
            cus.setEmail(StringUtils.trimToNull((String) map.get(SubjectFields.CUSTOMER_EMAIL)));
        }
        if (map.containsKey(SubjectFields.CUSTOMER_PHONE)) {
            cus.setPhone(StringUtils.trimToNull((String) map.get(SubjectFields.CUSTOMER_PHONE)));
        }
        if (map.containsKey(SubjectFields.CUSTOMER_PLACE)) {
            cus.setPlaceOfLiving(StringUtils.trimToNull((String) map.get(SubjectFields.CUSTOMER_PLACE)));
        }
        if (map.containsKey(SubjectFields.CUSTOMER_ZIPCODE)) {
            cus.setZipcode(StringUtils.trimToNull((String) map.get(SubjectFields.CUSTOMER_ZIPCODE)));
        }

        return subject;
    }

    private static Subject updateWithMapCategory(Subject subject, Map map) {
        Category category = (Category) subject;
        category = (Category) setNameSubjectFromMap(category, map);
        return category;
    }

    private static Subject setNameSubjectFromMap(Subject subject, Map map) {
        if (map.containsKey(SubjectFields.IDNUMBER) && map.containsKey(SubjectFields.NAME)) {
            subject.setName(StringUtils.trimToNull((String) map.get(SubjectFields.NAME)));
        }
        return subject;
    }


}
