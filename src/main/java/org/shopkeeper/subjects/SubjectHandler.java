package org.shopkeeper.subjects;

import org.shopkeeper.preloader.Preloader;
import org.shopkeeper.subjects.modules.CategoryModule;
import org.shopkeeper.subjects.modules.CustomerModule;
import org.shopkeeper.subjects.modules.ItemModule;
import org.shopkeeper.subjects.modules.SubjectModule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by johankladder on 12/23/15.
 */
public class SubjectHandler implements Runnable {

    public static ArrayList<SubjectModule> MODULES = new ArrayList<>();
    public static HashMap<String,SubjectModule> MODULESMAP = new HashMap<>();
    public static Boolean done = false;


    @Override
    public void run() {
        ItemModule itemModule = new ItemModule();
        CategoryModule categoryModule = new CategoryModule();
        CustomerModule customerModule = new CustomerModule();

        MODULESMAP.put("itemmodule",itemModule);
        MODULESMAP.put("categorymodule",categoryModule);
        MODULESMAP.put("customermodule",customerModule);
        MODULES.add(itemModule);
        MODULES.add(categoryModule);
        MODULES.add(customerModule);

        // For each module, get all the objects from the database:
        synchronized (SubjectHandler.class) {
            for (SubjectModule module : MODULES) {
                module.refresh();
                try {
                    SubjectHandler.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        synchronized (Preloader.ready) {

            Preloader.ready.notify();
        }
    }

    public static SubjectModule getModule(String name) {
        return MODULESMAP.get(name);
    }
}
