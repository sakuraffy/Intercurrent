package cn.sakuraffy.pattern;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;


public class TestFuture {
	public static class RelData implements Callable<String>{
		private String para;
		
		public RelData(String para) {
			super();
			this.para = para;
		}

		@Override
		public String call() throws Exception {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < 3; i++) {
				sb.append(para);
			}
			Thread.sleep(500);
			return sb.toString();
		}
	}
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService es = Executors.newCachedThreadPool();
		FutureTask<String> future = new FutureTask<>(new RelData("hello"));
		es.execute(future);
		System.out.println("request finsh");
		Thread.sleep(2000);
		System.out.println(future.get());
	}
}
