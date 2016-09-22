package cn.sakuraffy.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author 	deadlyBoy
 * @date 	2016��7��26��
 * @describe ����ReentrantLock �Ļ����÷�
 */
public class TestReentrantLock1 implements Runnable {
	private static int i = 0;
	private static ReentrantLock lock = new ReentrantLock();
	
	@Override
	public void run() {
		for(int k = 0; k < 10000; k++) {
			lock.lock();
			lock.lock();
			try {
				i++;
			}finally {
				lock.unlock();
				lock.unlock();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new TestReentrantLock1());
		Thread t2 = new Thread(new TestReentrantLock1());
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(i);
	}
}
