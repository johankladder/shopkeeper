package org.shopkeeper.util;

/**
 * Created by typhooncoaster on 10-1-16.
 */
public class AntiLockSystem {

    private static Boolean LOCKHEAD = false;
    private static Boolean LOCKDATABASE = false;
    private static final Integer LOCK_TIMEOUT = 60; // 60 seconds

    public static void lockAndWait() throws InterruptedException {
        synchronized (AntiLockSystem.LOCKHEAD) {
                LOCKHEAD.wait(60*1000);
        }
    }

    public static void notifyLock() {
        synchronized (LOCKHEAD) {
            LOCKHEAD.notify();
        }
    }

    public static void lockAndWaitDatabase() throws InterruptedException {
        synchronized (AntiLockSystem.LOCKDATABASE) {
            LOCKDATABASE.wait(60*1000);
        }
    }

    public static void notifyLockDatabase() {
        synchronized (LOCKDATABASE) {
            LOCKDATABASE.notify();
        }
    }
}
