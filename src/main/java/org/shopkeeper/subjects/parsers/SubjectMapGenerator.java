package org.shopkeeper.subjects.parsers;

import org.apache.commons.lang3.StringUtils;
import org.shopkeeper.subjects.subjecttypes.Subject;
import org.shopkeeper.subjects.subjecttypes.SubjectFields;
import org.shopkeeper.subjects.SubjectManipulator;
import org.shopkeeper.subjects.subjecttypes.SubjectTypes;
import org.shopkeeper.subjects.subjecttypes.categories.Category;
import org.shopkeeper.subjects.subjecttypes.customer.Customer;
import org.shopkeeper.subjects.subjecttypes.items.Item;
import org.shopkeeper.util.PriceGenerator;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * The SubjectMapGenerator is specialised when it comes to creating new subjects from for example forms. The method generates
 * these HashMaps with pre stated keys. Just invoke the method generateMapForSubject with the subject type and of you go.
 * <p>
 * The hashmap are prefilled with key's and values. The best way to use this class is to invoke a generated HashMap and
 * put the values that the subject contains after the right key. Example:
 * <p>
 * <p>HashMap<String, String> map = generateMapForStorage(SubjectTypes.ITEM); <br>
 * map.put(SubjectFields.PRICE, "12.2"); <br>
 * etc. <br>
 * </p>
 * <p>After that the map can be passed in the create/update with map methods. This will handles the rest.</p>
 *
 * @see SubjectManipulator
 * @see SubjectMapGenerator#generateMapForStorage(Integer)
 * @see SubjectMapGenerator#createWithMap(HashMap)
 * @see SubjectMapGenerator#updateWithMap(HashMap)
 */
public class SubjectMapGenerator {
    private final static Logger LOGGER = Logger.getLogger(SubjectMapGenerator.class.getName());

    // MAP IDS:
    public static final String MAP_ITEM_ID = "item_map_id=" + SubjectTypes.ITEM;
    public static final String MAP_CUSTOMER_ID = "customer_map_id=" + SubjectTypes.CUSTOMER;
    public static final String MAP_CATEGORY_ID = "category_map_id=" + SubjectTypes.CATEGORY;

    /**
     * Gets the hashmap with keys according to the subject-type. This map is used for saving form information.
     * The program needs to now which map fits the needs of a certain subject. Make sure you only change the fields that
     * meant to be changed. Fields are avalaible in SubjectField's class.
     * Will return null if subject-type is not supported
     *
     * @see SubjectFields
     */
    public static HashMap<String, String> generateMapForStorage(Integer subjectType) {
        if (subjectType == SubjectTypes.ITEM) {
            return generateMapItem();
        } else if (subjectType == SubjectTypes.CATEGORY) {
            return generateMapCategories();
        } else if (subjectType == SubjectTypes.CUSTOMER) {
            return generateMapCustomer();
        } else {
            LOGGER.warning("Your subject type can not be found..");
            return null;
        }
    }

    // Map generations:
    private static HashMap<String, String> generateMapItem() {
        HashMap<String, String> map = new HashMap<String, String>();
        // Put the fields in it:
        map.put(SubjectFields.IDNUMBER, null);
        map.put(SubjectFields.NAME, null);
        map.put(SubjectFields.ITEM_PRICE, null);
        map.put(MAP_ITEM_ID, null);
        return map;
    }

    private static HashMap<String, String> generateMapCustomer() {
        HashMap<String, String> map = new HashMap<String, String>();
        // Put the fields in it:
        map.put(SubjectFields.IDNUMBER, null);
        map.put(SubjectFields.NAME, null);
        map.put(SubjectFields.CUSTOMER_PLACE, null);
        map.put(SubjectFields.CUSTOMER_EMAIL, null);
        map.put(SubjectFields.CUSTOMER_ADDRESS, null);
        map.put(SubjectFields.CUSTOMER_PHONE, null);
        map.put(SubjectFields.CUSTOMER_ZIPCODE, null);
        map.put(MAP_CUSTOMER_ID, null);
        return map;
    }

    private static HashMap<String, String> generateMapCategories() {
        HashMap<String, String> map = new HashMap<String, String>();
        // Put the fields in it:
        map.put(SubjectFields.IDNUMBER, null);
        map.put(SubjectFields.NAME, null);
        map.put(MAP_CATEGORY_ID, null);
        return map;
    }


    // MAP-PARSER:

    /**
     * Creates a new subject with a map -> When its done it will send a request to the module, who will eventually put
     * this subject in a database or something like that. The method returns the requested item and will request null when
     * there where any errors on the parsing site. This method communicates with the manipulator and so it will only give
     * and request to the module when there where no errors.
     *
     * @param map Map containing information about the subject.
     * @return The requested Subject class or null when there was a error.
     */
    public static Subject createWithMap(HashMap<String, String> map) {
        if (map.containsKey(MAP_ITEM_ID)) {
            Subject subject = SubjectManipulator.createItem(map);
            SubjectManipulator.add(subject);
            return subject;
        } else if (map.containsKey(MAP_CUSTOMER_ID)) {
            Subject subject = SubjectManipulator.createCustomer(map);
            SubjectManipulator.add(subject);
            return subject;
        } else if (map.containsKey(MAP_CATEGORY_ID)) {
            Subject subject = SubjectManipulator.createCategory(map);
            SubjectManipulator.add(subject);
            return subject;
        } else {
            LOGGER.warning("The map you gave was not valid, please make sure you haven't edited the map_id fields.");
            return null;
        }
    }

    /**
     * This method updates a subject when it is given the right map. If not, or if there where errors, it will return
     * null, else it will return the newly updated subject. This method works almost the same a creating a new subject
     * with a map, but communicates a little bit different with the subjects manipulator.
     *
     * @param map Map including information about the subject.
     * @return The updated subject.
     */
    public static Subject updateWithMap(HashMap<String, String> map) {
        if (map.containsKey(MAP_ITEM_ID)) {
            Subject subject = SubjectManipulator.createItem(map);
            SubjectManipulator.update(subject);
            return subject;
        } else if (map.containsKey(MAP_CUSTOMER_ID)) {
            Subject subject = SubjectManipulator.createCustomer(map);
            SubjectManipulator.update(subject);
            return subject;
        } else if (map.containsKey(MAP_CATEGORY_ID)) {
            Subject subject = SubjectManipulator.createCategory(map);
            SubjectManipulator.update(subject);
            return subject;
        } else {
            LOGGER.warning("The map you gave was not valid, please make sure you haven't edited the map_id fields.");
            return null;
        }
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
     *
     * When problems occur when this method is trying to update a subject, the method will return the given object.
     * Make note that it really tries also to update parts from a subject, also when it fails on other parts in the map.
     *
     * Will return null, when subject is null.
     * @param subject The subject liked to be updated from a map
     * @param map The map with all the given updates
     * @return The updated subject
     */
    // TODO LOG
    public static Subject updateSubjectWithMap(Subject subject, Map map) {
        if(subject != null) {
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
            item.setName(StringUtils.trimToNull((String) map.get(SubjectFields.NAME)));

            try {
                Double price = PriceGenerator.getPriceFromString((String) map.get(SubjectFields.ITEM_PRICE));
                if(price != null) {
                    item.setPrice(price);
                } else {
                    return subject;
                }
            }catch (NumberFormatException e) {
                return subject;
            }
        }
        return subject;
    }

    private static Subject updateWithMapCustomer(Subject subject, Map map) {
        Customer cus = (Customer) subject;
        cus = (Customer) setNameSubjectFromMap(cus, map);

        if(map.containsKey(SubjectFields.CUSTOMER_ADDRESS)) {
            cus.setAddress(StringUtils.trimToNull((String) map.get(SubjectFields.CUSTOMER_ADDRESS)));
        }
        if(map.containsKey(SubjectFields.CUSTOMER_EMAIL)) {
            cus.setEmail(StringUtils.trimToNull((String) map.get(SubjectFields.CUSTOMER_EMAIL)));
        }
        if(map.containsKey(SubjectFields.CUSTOMER_PHONE)) {
            cus.setPhone(StringUtils.trimToNull((String) map.get(SubjectFields.CUSTOMER_PHONE)));
        }
        if(map.containsKey(SubjectFields.CUSTOMER_PLACE)) {
            cus.setPlaceOfLiving(StringUtils.trimToNull((String) map.get(SubjectFields.CUSTOMER_PLACE)));
        }
        if(map.containsKey(SubjectFields.CUSTOMER_ZIPCODE)) {
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
        if (map.containsKey(SubjectFields.IDNUMBER) && map.containsKey(SubjectFields.NAME))
        {
            subject.setName(StringUtils.trimToNull((String) map.get(SubjectFields.NAME)));
        }
        return subject;
    }


}
