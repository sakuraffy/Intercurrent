package cn.sakuraffy.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestCondition implements Runnable {
	private static ReentrantLock lock = new ReentrantLock();
	private static Condition condition = lock.newCondition();
	
	@Override
	public void run() {
		try {
			lock.lock();
			condition.await(1,TimeUnit.SECONDS);
			System.out.println("thread is going on");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new TestCondition());
		t.start();
		Thread.sleep(3000);
		lock.lock();
		condition.signal();
		lock.unlock();
	}
}
