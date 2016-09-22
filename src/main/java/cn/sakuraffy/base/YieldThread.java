package cn.sakuraffy.base;

public class YieldThread implements Runnable {
	@Override
	public void run() {
		while(true) {
			System.out.println("HelloWorld");
			 //yield()�ó�CPU������������һ��ִ�еĲ����Լ�
			Thread.yield();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new YieldThread());
		t.start();
		while(true) {
			System.out.println("nihao");
		}
	}
}
