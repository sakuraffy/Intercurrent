package cn.sakuraffy.concurrent;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestReadWriteLock {
	private static ReentrantLock lock = new ReentrantLock();
	private static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
	
	private static Lock readLock = rwLock.readLock();
	private static Lock writeLock = rwLock.writeLock();
	
	private int value;
	
	public Object read(Lock lock) throws InterruptedException {
		try {
			lock.lock();
			Thread.sleep(100);
			return value;
		}finally {
			lock.unlock();
		}
		
	}
	
	public void write(Lock lock, int index) throws InterruptedException {
		try {
			lock.lock();
			Thread.sleep(100);
			value = index;
		}finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		TestReadWriteLock test = new TestReadWriteLock();
		long start = System.currentTimeMillis();
		for(int i = 0; i < 20; i++) {
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						test.read(lock);
						test.write(lock, new Random().nextInt());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			t.start();
			t.join();
		}
		System.out.println(System.currentTimeMillis() - start);
		long start1 = System.currentTimeMillis();
		for(int i = 0; i < 20; i++) {
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						test.read(readLock);
						test.write(writeLock, new Random().nextInt());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			t.start();
			t.join();
		}
		System.out.println(System.currentTimeMillis() - start1);
	}
}
