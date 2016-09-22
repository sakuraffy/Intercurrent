package cn.sakuraffy.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class TestAtomicArray {
	public static void main(String[] args) throws InterruptedException {
		AtomicIntegerArray aia = new AtomicIntegerArray(10);
		Thread[] threads = new Thread[10];
		for(int i = 0; i < 10; i++) {
			Thread t = new Thread() {
				@Override
				public void run() {
					for(int k = 0; k < 1000; k++) {
						aia.incrementAndGet(k % 10);
					}
				}
			};
			threads[i] = t;
			t.start();
		}
		for(int i = 0; i < 10; i++) {
			threads[i].join();
		}
		System.out.println(aia);
	}
}
