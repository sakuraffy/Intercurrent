package cn.sakuraffy.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExpandThreadPoolExecutor implements Runnable {
	private String name;
	public ExpandThreadPoolExecutor(String name) {
		this.name = name;
	}
	@Override
	public void run() {
		System.out.println("HelloWorld" + name);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		ExecutorService es = new ThreadPoolExecutor(2, 2, 0L, 
				TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>() ) {
			@Override
			protected void beforeExecute(Thread t, Runnable r) {
				// TODO Auto-generated method stub
				System.out.println("before " + ((ExpandThreadPoolExecutor) r).name);
			}
			
			@Override
			protected void afterExecute(Runnable r, Throwable t) {
				// TODO Auto-generated method stub
				System.out.println("after " + ((ExpandThreadPoolExecutor) r).name);
			}
			
			@Override
			protected void terminated() {
				System.out.println("thread pool exit");
			}
		};

		for(int i = 0; i < 2; i++) {
			es.execute(new ExpandThreadPoolExecutor("thread_" + i));
		}
		es.shutdown();
	}
}
