package com.game.utilities;

import java.util.LinkedList;

/**
 * Created by rkesavalalji on 5/7/16.
 */
public class ThreadPool extends ThreadGroup {

    private boolean isAlive = true;
    private LinkedList<Runnable> taskQueue;
    private int threadId;
    private static int threadPoolId;

    public ThreadPool(int numThreads) {
        super("ThreadPool -"+threadPoolId++);
        setDaemon(true);
        isAlive = true;
        taskQueue = new LinkedList<Runnable>();
        for(int i=0;i<numThreads;i++){
            new PooledThread().start();
        }
    }

    public synchronized void runTask(Runnable task){
        if(!isAlive){
            throw new IllegalStateException();
        }
        if(null != task){
            taskQueue.add(task);
            notify();
        }
    }

    public synchronized Runnable getTask() throws InterruptedException {
        while(taskQueue.size() ==0){
            if(!isAlive) return null;
            wait();
        }
        return taskQueue.removeFirst();
    }

    public synchronized void close(){
        if(isAlive){
            isAlive=false;
            taskQueue.clear();
            interrupt();
        }
    }

    class PooledThread extends Thread{

        public PooledThread() {
            super(ThreadPool.this,"PooledThread - "+threadId++);
        }

        public void run(){
            while(!isInterrupted()){
                Runnable task = null;
                try {
                    task = getTask();
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException "+e.getMessage());
                }

                if(null==task){
                    return;
                }
                try {

                    task.run();
                }catch (Throwable thr){
                    uncaughtException(this,thr);
                    System.out.println("PooledThread Error "+thr.getMessage());
                }
            }
        }
    }

}
