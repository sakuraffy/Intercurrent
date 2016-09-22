package cn.sakuraffy.concurrent;

import java.util.Random;

public class TestThreadLocal {
	private static ThreadLocal<Integer> tl = new ThreadLocal<>();
	public static class ThreadA {
		public void get() {
			int data = tl.get();
			System.out.println("A : "+ Thread.currentThread().getName() + data);
		}
	}
	
	public static class ThreadB {
		public void get() {
			int data = tl.get();
			System.out.println("B : "+ Thread.currentThread().getName() + data);
		}
	}
	
	public static void main(String[] args) {
		for(int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
	
				@Override
				public void run() {
					int data = new Random().nextInt();
					System.out.println(Thread.currentThread().getName() + " put " + data);
					tl.set(data);
					new ThreadA().get();
					new ThreadB().get();
				}
			}).start();
		}
	}
	
	
}
