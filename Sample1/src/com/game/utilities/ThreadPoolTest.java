package com.game.utilities;

/**
 * Created by rkesavalalji on 5/7/16.
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        int numTasks = Integer.parseInt(args[0]);
        int numThreads = Integer.parseInt(args[1]);

        ThreadPool threadPool = new ThreadPool(numThreads);
        for (int i=0;i < numTasks;i++){
            threadPool.runTask(createTask(i));
        }
    }

    public static Runnable createTask(final int threadId){
        return new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread task --"+threadId+" start");

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread task --"+threadId+" end");
            }
        };
    }
}

