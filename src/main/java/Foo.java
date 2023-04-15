package main.java;

import java.util.concurrent.CountDownLatch;

public class Foo {
    private final CountDownLatch cDL1 = new CountDownLatch(1);
    private final CountDownLatch cDL2 = new CountDownLatch(1);

    public void first(Runnable runnable){
        System.out.print("first");
        cDL1.countDown();
    }

    public void second(Runnable runnable){
        try {
            cDL1.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.print("second");
        cDL2.countDown();

    }

    public void third(Runnable runnable){
        try {
            cDL2.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.print("third");
    }
}
