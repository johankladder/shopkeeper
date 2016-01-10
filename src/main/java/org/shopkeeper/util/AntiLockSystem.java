package org.shopkeeper.util;

/**
 * Created by typhooncoaster on 10-1-16.
 */
public class AntiLockSystem {

    private static Boolean LOCK = false;

    public static void lockAndWait() throws InterruptedException {
        synchronized (AntiLockSystem.LOCK) {
                LOCK.wait();
        }
    }

    public static void notifyLock() {
        synchronized (LOCK) {
            LOCK.notify();
        }
    }
}
