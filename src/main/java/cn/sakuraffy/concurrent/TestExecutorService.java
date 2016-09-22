package cn.sakuraffy.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestExecutorService implements Runnable {
	@Override
	public void run() {
		System.out.println(System.currentTimeMillis()/1000 + " " 
				+ Thread.currentThread().getName());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		ExecutorService es1 = Executors.newFixedThreadPool(2);
		ExecutorService es2 = Executors.newSingleThreadExecutor();
		ExecutorService es3 = Executors.newCachedThreadPool();
		
		TestExecutorService tp = new TestExecutorService();
		
		for(int i = 0; i < 3; i++) {
			//es1.execute(tp);
			//es2.execute(tp);
			es3.execute(tp);
		}
	}
}
