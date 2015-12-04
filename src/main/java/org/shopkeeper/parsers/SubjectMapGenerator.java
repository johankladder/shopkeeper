package org.shopkeeper.parsers;

import org.shopkeeper.subjects.Subject;
import org.shopkeeper.subjects.SubjectFields;
import org.shopkeeper.subjects.SubjectManipulator;
import org.shopkeeper.subjects.SubjectTypes;

import java.util.HashMap;
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


}
