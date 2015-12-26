package org.shopkeeper.subjects;

import org.shopkeeper.preloader.Preloader;
import org.shopkeeper.subjects.modules.CategoryModule;
import org.shopkeeper.subjects.modules.CustomerModule;
import org.shopkeeper.subjects.modules.ItemModule;
import org.shopkeeper.subjects.modules.SubjectModule;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by johankladder on 12/23/15.
 */
public class SubjectHandler implements Runnable {

    public static Map<String, SubjectModule> MODULES = new HashMap<>();


    @Override
    public void run() {
        MODULES.put("itemmodule",new ItemModule());
        MODULES.put("categorymodule",new CategoryModule());
        MODULES.put("customermodule",new CustomerModule());

        // For each module, get all the objects from the database:


        synchronized (Preloader.ready) {
            Preloader.ready.notify();
        }
    }

    public static SubjectModule getModule(String name) {
        return MODULES.get(name);
    }
}
