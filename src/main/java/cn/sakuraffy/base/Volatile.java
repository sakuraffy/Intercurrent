package cn.sakuraffy.base;

public class Volatile extends Thread {
	private  static volatile int i = 0;
	
	@Override
	public void run() {
		for(int k = 0; k < 10000; k++) {
			i++;
		}
	}
		
	public static void main(String[] args) throws InterruptedException {
		Thread[] threads = new Thread[10];
		for(int i = 0; i < 10; i++) {
			threads[i] = new Volatile();
			threads[i].start();
		}
		for(int i = 0; i < 10; i++) {
			threads[i].join();
		}
		System.out.println(i);
	}
}
