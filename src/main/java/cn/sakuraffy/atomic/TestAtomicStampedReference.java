package cn.sakuraffy.atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

public class TestAtomicStampedReference {
	private static AtomicStampedReference<Integer> asr = new AtomicStampedReference<Integer>(50, 0);
	
	public static class RechargeThread extends Thread {
		private int stamp = asr.getStamp();
		@Override
		public void run() {
			while(true) {
				while(true) {
					Integer value = asr.getReference();
					if(value < 100) {
						if (asr.compareAndSet(value, value + 200,stamp,stamp + 1)) {
							System.out.println("recharge 200,remaining �� " + asr.getReference());
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
					Integer value = asr.getReference();
					int stamp = asr.getStamp();
					if(value > 100) {
						if(asr.compareAndSet(value, value - 100,stamp,stamp + 1)) {
							System.out.println("consume 100,remaining �� " + asr.getReference());
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
