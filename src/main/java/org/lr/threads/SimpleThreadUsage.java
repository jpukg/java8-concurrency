package org.lr.threads;

import java.util.concurrent.TimeUnit;

public class SimpleThreadUsage {
    public static void main(String[] args) {
        Thread t = new Thread(threadSleepUsingTimeUnit());
        t.start();
    }

    private static Runnable threadSleepUsingTimeUnit() {
        return () -> {
            System.out.println("Before sleep 1 sec");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println("Finished sleep");
            }
        };
    }
}
