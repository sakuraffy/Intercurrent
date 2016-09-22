package cn.sakuraffy.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadFactory implements Runnable {
	@Override
	public void run() {
		System.out.println("HelloWorld");
	}
	
	public static void main(String[] args) {
		TestThreadFactory ttf = new TestThreadFactory();
		ExecutorService es = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>(), 
				new ThreadFactory() {
					
					@Override
					public Thread newThread(Runnable r) {
						Thread t = new Thread(r);
						t.setDaemon(true);
						System.out.println("create " + t);
						return t;
					}
				});
		for(int i = 0; i < 5; i++) {
			es.execute(ttf);
		}
	}
}
