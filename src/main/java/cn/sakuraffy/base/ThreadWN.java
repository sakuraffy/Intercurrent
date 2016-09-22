package cn.sakuraffy.base;

public class ThreadWN {
	private static Object obj = new Object();
	
	public static class WaitThread extends Thread {
		@Override
		public void run() {
			synchronized (obj) {
				System.out.println(System.currentTimeMillis() + " WaitThread start");
				try {
					obj.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(System.currentTimeMillis() + " WaitThread end");
			}
		}
	}
	
	public static class NotifyThread extends Thread {
		@Override
		public void run() {
			synchronized (obj) {
				System.out.println(System.currentTimeMillis() + " NotifyThread start");
				obj.notify();
				System.out.println(System.currentTimeMillis() + " NotifyThread end");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		WaitThread wt = new WaitThread();
		wt.start();
		NotifyThread nt = new NotifyThread();
		nt.start();
	}
	
}
