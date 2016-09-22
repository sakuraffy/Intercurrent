package cn.sakuraffy.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch implements Runnable {
	private static CountDownLatch cdl = new CountDownLatch(10);
	@Override
	public void run() {
		try {
			Thread.sleep(1000 * new Random().nextInt(5));
			System.out.println(System.currentTimeMillis() /1000 + " "
				+ Thread.currentThread().getName() + " complete");
			cdl.countDown();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		TestCountDownLatch  test = new TestCountDownLatch();
		for(int i = 0; i < 10; i++) {
			new Thread(test).start();
		}
		cdl.await();
		System.out.println("fire");
	}
}
