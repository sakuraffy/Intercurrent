package cn.sakuraffy.concurrent;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrier {
	public static class BarrierThread implements Runnable {		
		private static CyclicBarrier cb = new CyclicBarrier(5);
		private boolean flag = true;
		
		@Override
		public void run() {
			try {
				doWork(flag);
				cb.await();
				flag = false;
				doWork(flag);
				cb.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		private void doWork(boolean flag) throws InterruptedException {
			Thread.sleep(1000 * new Random().nextInt(5));
			if (flag) {
				System.out.println(System.currentTimeMillis()/1000 + " "
						+ Thread.currentThread().getName() + " ready");	
			}else {
				System.out.println(System.currentTimeMillis()/1000 + " "
						+ Thread.currentThread().getName() + " finsh");	
			}
		}
		
	}
	
	public static void main(String[] args) {
		BarrierThread barrier = new BarrierThread();
		for(int i = 0; i < 5; i++) {
			new Thread(barrier).start();
		}
	}
}
