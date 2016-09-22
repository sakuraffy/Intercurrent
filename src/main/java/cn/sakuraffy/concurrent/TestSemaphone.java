package cn.sakuraffy.concurrent;

import java.util.concurrent.Semaphore;

public class TestSemaphone implements Runnable {
	//��Ӧ�߳�һ��Ҫ��ͬһRunnable����
	private final Semaphore semaphore = new Semaphore(3);
	
	@Override
	public void run() {
		try {
			semaphore.acquire();
			Thread.sleep(2000);
			System.out.println(System.currentTimeMillis()/1000
					+ " " + Thread.currentThread().getName());
			semaphore.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		TestSemaphone test = new TestSemaphone();
		for(int i = 0; i < 20; i++) {
			new Thread(test).start();
		} 
	}
}
