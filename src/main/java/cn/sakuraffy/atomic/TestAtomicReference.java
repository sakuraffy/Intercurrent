package cn.sakuraffy.atomic;

import java.util.concurrent.atomic.AtomicReference;

public class TestAtomicReference {
	private static AtomicReference<Integer> ar = new AtomicReference<>(250);
	public static class RechargeThread extends Thread {
		@Override
		public void run() {
			while(true) {
				while(true) {
					Integer value = ar.get();
					if(value < 100) {
						if (ar.compareAndSet(value, value + 200)) {
							System.out.println("recharge 200,remaining �� " + ar.get());
							break;	
						}
					}else {
						break;
					}
				}
			}
		}
	}
	
	public static class ConsumeThread extends Thread {
		@Override
		public void run() {
			for(int i = 0; i < 10; i++) {
				while(true) {
					Integer value = ar.get();
					if(value > 100) {
						if(ar.compareAndSet(value, value - 100)) {
							System.out.println("consume 100,remaining �� " + ar.get());
							break;
						}
					}else {
						System.out.println("consume fail");
						break;
					}
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		for(int i = 0; i < 10; i++) {
			RechargeThread rt = new RechargeThread();
			rt.start();
		}
		ConsumeThread ct = new ConsumeThread();
		ct.start();
	}
}
