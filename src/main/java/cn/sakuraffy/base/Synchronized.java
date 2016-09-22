package cn.sakuraffy.base;

public class Synchronized implements Runnable {
	private static int i = 0;
	
	public void run() {
		synchronized (this.getClass()) {
			for(int k = 0; k < 10000; k++) {
				i++;
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new Synchronized());
		Thread t2 = new Thread(new Synchronized());
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(i);
	}
}
