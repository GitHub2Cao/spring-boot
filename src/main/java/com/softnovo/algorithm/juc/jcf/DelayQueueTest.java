package com.softnovo.algorithm.juc.jcf;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueTest {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<Job> jobs = new DelayQueue<>();
        jobs.put(new Job("job1", System.currentTimeMillis() + 1000));
        jobs.put(new Job("job2", System.currentTimeMillis() + 2000));
        jobs.put(new Job("job3", System.currentTimeMillis() + 3000));
        Thread t1 = new Thread(new JobRunnable(jobs));
        //Thread t2 = new Thread(new JobRunnable(jobs));
        t1.start();
        //t2.start();
        t1.join();
        //t2.join();
        System.out.println("t1 finished");
    }

    private static class JobRunnable implements Runnable {
        private DelayQueue<Job> jobs;

        public JobRunnable(DelayQueue<Job> jobs) {
            this.jobs = jobs;
        }

        @Override
        public void run() {
            try {
                while (!jobs.isEmpty()) {
                    Job job = jobs.take();
                    job.run();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    static class Job implements Delayed {
        private String name;
        private long scheduleTime; //millisecond

        public Job(String name, long scheduleTime) {
            this.name = name;
            this.scheduleTime = scheduleTime;
        }

        public void run() {
            System.out.println("I am " + name + ", time is " + scheduleTime);
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long delayTime = scheduleTime - System.currentTimeMillis();
            return unit.convert(delayTime, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return (int) (this.getDelay(TimeUnit.NANOSECONDS) -
                    o.getDelay(TimeUnit.NANOSECONDS));
        }
    }
}
