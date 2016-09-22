package cn.sakuraffy.atomic;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class TestAtomicIntegerFieldUpdater {
	public static class User {
		volatile int score;
	}
	
	public static final AtomicIntegerFieldUpdater<User> scoreUpdater = 
			AtomicIntegerFieldUpdater.newUpdater(User.class, "score");
	
	//������֤AtomicIntegerFieldUpdater����ȷ��
	public static AtomicInteger ai = new AtomicInteger(0);
	
	public static void main(String[] args) throws InterruptedException {
		User user = new User();
		Random r = new Random();
		Thread[] threads = new Thread[100];
		for(int i = 0; i < 100; i++) {
			threads[i]  = new Thread() {
				@Override
				public void run() {
					if(r.nextInt(5) > 2) {
						ai.incrementAndGet();
						scoreUpdater.getAndIncrement(user);
					}
				}
			};
			threads[i].start();
		}
		for(int i = 0; i < 100; i++) {
			threads[i].join();
		}
		System.out.println("score : " + user.score);
		System.out.println("ai : " + ai);
	}
}
