package org.shopkeeper.subjects;

import org.apache.commons.lang3.StringUtils;
import org.shopkeeper.parsers.SubjectActionChooser;
import org.shopkeeper.subjects.categories.Category;
import org.shopkeeper.subjects.customer.Customer;
import org.shopkeeper.subjects.items.Item;
import org.shopkeeper.subjectsmodules.SubjectModule;
import org.shopkeeper.util.DateTimeGenerator;

import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Created by typhooncoaster on 4-12-15.
 */
public class SubjectManipulator {
    private final static Logger LOGGER = Logger.getLogger(SubjectManipulator.class.getName());

    /**
     * Creates item and sends it to its module. After the item was send to the module, the method returns the mentioned
     * item. This method will check if name and id are filled in (those are necessary), if not, this method will not push
     * this item through the module and will return null. Same with parsing errors (double).
     *
     * @param map Map with info about the Item
     * @return Item or null when errors
     * @see org.shopkeeper.parsers.SubjectMapGenerator
     */
    // TODO Create a price parse (for . and ,) so that both can be entered
    public static Item createItem(HashMap<String, String> map) {
        if (map.containsKey(SubjectFields.IDNUMBER) && map.containsKey(SubjectFields.NAME)) {
            try {
                Long identificationNumber = Long.parseLong(StringUtils.trimToNull(map.get(SubjectFields.IDNUMBER)));
                String name = map.get(SubjectFields.NAME);
                Double price = Double.parseDouble(StringUtils.trimToNull(map.get(SubjectFields.ITEM_PRICE)));
                if (StringUtils.isBlank(name) || identificationNumber == null) {
                    LOGGER.warning("The name or id was empty -> cannot create an subject :(!");
                    return null;
                } else {
                    name = StringUtils.trimToNull(name);
                    return new Item(identificationNumber, name, price, DateTimeGenerator.generateDateTimeNow());
                }

            } catch (NumberFormatException ex) {
                LOGGER.warning("Can't parse the given price -> Make sure you use a digit! :)");
                return null;
            }


        } else {
            LOGGER.warning("The map you've given was not valid! Maybe the wrong map for right subject? :)");
            return null;
        }
    }

    public static Customer createCustomer(HashMap<String, String> map) {
        if (map.containsKey(SubjectFields.IDNUMBER) && map.containsKey(SubjectFields.NAME)) {
            try {
                Long identificationNumber = Long.parseLong(StringUtils.trimToNull(map.get(SubjectFields.IDNUMBER)));
                String name = map.get(SubjectFields.NAME);
                if (StringUtils.isBlank(name) || identificationNumber == null) {
                    LOGGER.warning("The name or id was empty -> cannot create an subject :(!");
                    return null;
                } else {
                    name = StringUtils.trimToNull(name);
                    String placeOfLiving = StringUtils.trimToNull(map.get(SubjectFields.CUSTOMER_PLACE));
                    String address = StringUtils.trimToNull(map.get(SubjectFields.CUSTOMER_ADDRESS));
                    String phone = StringUtils.trimToNull(map.get(SubjectFields.CUSTOMER_PHONE));
                    String zipcode = StringUtils.trimToNull(map.get(SubjectFields.CUSTOMER_ZIPCODE));
                    String email = StringUtils.trimToNull(map.get(SubjectFields.CUSTOMER_EMAIL));
                    return new Customer(identificationNumber, name, DateTimeGenerator.generateDateTimeNow(),
                            placeOfLiving, address, zipcode, phone, email);
                }

            } catch (NumberFormatException ex) {
                LOGGER.warning("Can't parse the given number(s) -> Make sure you use a digit! :)");
                return null;
            }


        } else {
            LOGGER.warning("The map you've given was not valid! Maybe the wrong map for right subject? :)");
            return null;

        }
    }

    public static Category createCategory(HashMap<String, String> map) {
        if (map.containsKey(SubjectFields.IDNUMBER) && map.containsKey(SubjectFields.NAME)) {
            try {
                Long identificationNumber = Long.parseLong(StringUtils.trimToNull(map.get(SubjectFields.IDNUMBER)));
                String name = map.get(SubjectFields.NAME);
                if (StringUtils.isBlank(name) || identificationNumber == null) {
                    LOGGER.warning("The name or id was empty -> cannot create an subject :(!");
                    return null;
                } else {
                    name = StringUtils.trimToNull(name);
                    return new Category(identificationNumber, name, DateTimeGenerator.generateDateTimeNow());
                }

            } catch (NumberFormatException ex) {
                LOGGER.warning("Can't parse the given number(s) -> Make sure you use a digit! :)");
                return null;
            }

        } else {
            LOGGER.warning("The map you've given was not valid! Maybe the wrong map for right subject? :)");
            return null;
        }
    }


    public static void add(Subject subject) {
        SubjectActionChooser.actionOnSubject(subject, SubjectActionChooser.ADD);
    }

    public static void update(Subject subject) {
        SubjectActionChooser.actionOnSubject(subject, SubjectActionChooser.UPDATE);
    }

    public static void delete(Subject subject) {
        SubjectActionChooser.actionOnSubject(subject, SubjectActionChooser.DELETE);
    }

}
