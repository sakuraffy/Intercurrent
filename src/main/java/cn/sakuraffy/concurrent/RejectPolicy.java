package cn.sakuraffy.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RejectPolicy implements Runnable {
	
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
	public static void main(String[] args) throws InterruptedException {
		RejectPolicy rp = new RejectPolicy();
		ExecutorService es = new ThreadPoolExecutor(5, 5, 
				0L, TimeUnit.SECONDS, 
				new LinkedBlockingQueue<Runnable>(10),
				Executors.defaultThreadFactory(),
				new RejectedExecutionHandler() {
					
					@Override
					public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
						System.out.println(r.toString() + "is discard");
					}
				});
		for(int i = 0; i < Integer.MAX_VALUE; i++) {
			es.submit(rp);
			Thread.sleep(100);
		}
	}
	
}
