package cn.sakuraffy.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolBug implements Runnable {
	
	public static class ThreadPoolTrace extends ThreadPoolExecutor {

		public ThreadPoolTrace(int corePoolSize, int maximumPoolSize,
				long keepAliveTime, TimeUnit unit,
				BlockingQueue<Runnable> workQueue) {
			super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		}
		
		@Override
		public void execute(Runnable command) {
			super.execute(wrap(command, cilentTrace()));
		}
		
		@Override
		public Future<?> submit(Runnable task) {
			// TODO Auto-generated method stub
			return super.submit(wrap(task, cilentTrace()));
		}
		
		private Exception cilentTrace() {
			return new Exception("client stack trace");
		}
		
		private Runnable wrap(final Runnable task, final Exception clientstack) {
			return new Runnable() {
				
				@Override
				public void run() {
					try {
						task.run();
					}catch (Exception e) {
						clientstack.printStackTrace();
						throw e;
					}
				}
			};
		}
	}
	
	private int a;
	private int b;
	
	public ThreadPoolBug(int a, int b) {
		this.a = a;
		this.b = b;
	}
	
	@Override
	public void run() {
		double ret = a / b;
		System.out.println(ret);
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService es = new ThreadPoolTrace(5, 5, 0L, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>());
		for(int i = 0; i < 5; i++) {
			es.submit(new ThreadPoolBug(100, i)).get();
			//es.execute(new ThreadPoolBug(100, i));
		}
	}
}
