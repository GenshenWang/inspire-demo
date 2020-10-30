package com.wgs.parrot.lock;


public class SynchronizedTest {

    private Ticket ticket = new Ticket();

    public static void main(String[] args) {
        SynchronizedTest demo = new SynchronizedTest();



        // not safe
        demo.sell("售票员1", 200);
        demo.sell("售票员2", 300);
        demo.sell("售票员3", 500);

    }

    public void sell(String sellName, int num) {
        for (int i = 1; i <= num; i++) {
            new Thread(sellName){
                @Override
                public void run() {
                    // ticket.sellNotThreadSafe();
                    // ticket.sellThreadSafeWithSynchronized();
                    ticket.sellThreadSafeWithLock();
                }
            }.start();
        }
    }
}
