package 线程;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

    // 普通共享变量
    private int i = 0;

    // 支持原子操作和可见性的变量
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {

        Counter counter = new Counter();

        // 起100个线程
        List<Thread> threads = Lists.newArrayList();
        for (int i = 0; i < 10000; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    counter.unsafeAdd();
                    counter.safeAdd();
                }
            });

            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 结果
        System.out.println("i : " + counter.i);
        System.out.println("atomicI : " + counter.atomicInteger.get());
    }

    private void unsafeAdd() {
        i++;
    }

    // 循环CAS操作
    private void safeAdd() {
        while (true) {
            // 现在认为的值
            int expect = atomicInteger.get();

            // 如果JVM中的值与expect一致，则对其+1
            boolean success = atomicInteger.compareAndSet(expect, ++expect);
            if (success) {
                break;
            }
        }
    }
}
