package cn.sakuraffy.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomicInteger {
	private static AtomicInteger ai = new AtomicInteger(0);
	
	public static class Add extends Thread {
		@Override
		public void run() {
			for(int k = 0; k < 10000; k++) {
				ai.incrementAndGet();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread[] threads = new Thread[10];
		for(int i = 0; i < 10; i++) {
			Thread t = new Add();
			threads[i] = t;
			t.start();
		}
		for(int i = 0; i < 10; i++) {
			threads[i].join();
		}
		System.out.println(ai.get());
	}
}
