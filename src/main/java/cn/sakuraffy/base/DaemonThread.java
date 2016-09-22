package cn.sakuraffy.base;

public class DaemonThread extends Thread {
	@Override
	public void run() {
		while(true) {
			System.out.println("HelloWorld");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t = new DaemonThread();
		t.start();
		t.setDaemon(true);
		Thread.sleep(1000);
		System.out.println("MainThread end");
	}
}
