package cn.sakuraffy.pattern;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ProducerAndConsumer {
	public static final int SLEEPTIME = 1000;
	public static class Data {
		private static int count = 0;
		private int id;
		public Data() {
			id = count++ ;
		}
		public final int getId() {
			return id;
		}
		public final void setId(int id) {
			this.id = id;
		}
		
	}
	
	public static class Producer implements Runnable {
		private BlockingQueue<Data> bq;
		
		public Producer(BlockingQueue<Data> bq) {
			super();
			this.bq = bq;
		}

		@Override
		public void run() {
			Random r = new Random();
			while(true) {
				try {
					Thread.sleep(r.nextInt(SLEEPTIME));
					Data data = new Data();
					if(!bq.offer(data, 1, TimeUnit.SECONDS)) {
						System.out.println("produce fail");
					}else {
						System.out.println(Thread.currentThread().getName() + 
								" produce " + data.getId());
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Thread.currentThread().interrupt();
				}
			}
		}
	}

	public static class Consumer implements Runnable {
		private BlockingQueue<Data> bq;
		
		public Consumer(BlockingQueue<Data> bq) {
			super();
			this.bq = bq;
		}

		@Override
		public void run() {
			Random r = new Random();
			while(true) {
				try {
					Thread.sleep(r.nextInt(SLEEPTIME));
					Data data = bq.take();
					if (data != null) {
						System.out.println(Thread.currentThread().getName() + " consume "
								+ data.getId());
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		BlockingQueue<Data> bq = new ArrayBlockingQueue<>(10);
		ExecutorService es = Executors.newCachedThreadPool();
		for(int i = 0; i < 5; i++) {
			es.execute(new Producer(bq));
			es.execute(new Consumer(bq));
		}
	}
}
