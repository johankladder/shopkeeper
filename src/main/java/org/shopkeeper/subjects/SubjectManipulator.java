package org.shopkeeper.subjects;

import org.apache.commons.lang3.StringUtils;
import org.shopkeeper.subjects.categories.Category;
import org.shopkeeper.subjects.customer.Customer;
import org.shopkeeper.subjects.items.Item;
import org.shopkeeper.util.DateTimeGenerator;

import java.util.HashMap;

/**
 * Created by typhooncoaster on 4-12-15.
 */
public class SubjectManipulator {

    /**
     * Creates item and sends it to its module. After the item was send to the module, the method returns the mentioned
     * item. This method will check if name and id are filled in (those are necessary), if not, this method will not push
     * this item through the module and will return null. Same with parsing errors (double).
     *
     * @param map Map with info about the Item
     * @return Item or null when errors
     */
    public static Item createItem(HashMap<String, String> map) {
        if (map.containsKey(SubjectFields.IDNUMBER) && map.containsKey(SubjectFields.NAME)) {
            try {
                Long identificationNumber = Long.parseLong(StringUtils.trimToNull(map.get(SubjectFields.IDNUMBER)));
                String name = map.get(SubjectFields.NAME);
                Double price = Double.parseDouble(StringUtils.trimToNull(map.get(SubjectFields.ITEM_PRICE)));
                if (StringUtils.isBlank(name) || identificationNumber == null) {
                    return null;
                } else {
                    name = StringUtils.trimToNull(name);
                    return new Item(identificationNumber, name, price, DateTimeGenerator.generateDateTimeNow());
                }

            } catch (NumberFormatException ex) {
                return null;
            }


        } else {
            return null;
        }
    }

    public static Customer createCustomer() {
        return null;
    }

    public static Category createCategory() {
        return null;
    }


    public static void add(Subject subject) {

    }

}
