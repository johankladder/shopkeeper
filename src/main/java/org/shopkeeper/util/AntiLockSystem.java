package org.shopkeeper.util;

/**
 * Created by typhooncoaster on 10-1-16.
 */
public class AntiLockSystem {

    private static Boolean LOCKHEAD = false;
    private static Boolean LOCKDATABASE = false;

    public static void lockAndWait() throws InterruptedException {
        synchronized (AntiLockSystem.LOCKHEAD) {
                LOCKHEAD.wait();
        }
    }

    public static void notifyLock() {
        synchronized (LOCKHEAD) {
            LOCKHEAD.notify();
        }
    }

    public static void lockAndWaitDatabase() throws InterruptedException {
        synchronized (AntiLockSystem.LOCKDATABASE) {
            LOCKDATABASE.wait();
        }
    }

    public static void notifyLockDatabase() {
        synchronized (LOCKDATABASE) {
            LOCKDATABASE.notify();
        }
    }
}
