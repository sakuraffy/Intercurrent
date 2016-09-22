package cn.sakuraffy.base;

public class CreateThread {
	//���ַ�ʽtarger = null
	public static class Thread1 extends Thread{
		@Override
		public void run() {
			while(true){
				System.out.println("hello world");
			}
		}
	}
	
	private static class Runnable1 implements Runnable{
		@Override
		public void run() {
			while(true){
				System.out.println("nihao");
			}
		} 
	}
	
	public static void main(String[] args) {
		Thread1 t1 = new Thread1();
		Thread t2 = new Thread(new Runnable1());
		t1.start();
		t2.start();
	}
}
