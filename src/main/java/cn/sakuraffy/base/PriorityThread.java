package cn.sakuraffy.base;

public class PriorityThread {
	public static class HighPriority extends Thread {
		private static int i = 0;
		@Override
		public void run() {
			while(true) {
				synchronized (LowPriority.class) {
					if(i > 10000) {
						System.out.println("HighPriority complete");
						break;
					}
					i++;
				}
			}
		}
	}
	
	public static class LowPriority extends Thread {
		private static int i = 0;
		@Override
		public void run() {
			while(true) {
				synchronized (LowPriority.class) {
					if(i > 10000) {
						System.out.println("LowPriority complete");
						break;
					}
					i++;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Thread t1 = new HighPriority();
		Thread t2 = new LowPriority();
		t1.setPriority(Thread.MAX_PRIORITY);
		t2.setPriority(Thread.MIN_PRIORITY);
		t1.start();
		t2.start();
	}
}
