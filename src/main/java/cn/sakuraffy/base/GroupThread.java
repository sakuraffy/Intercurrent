package cn.sakuraffy.base;

public class GroupThread {
	
	public static class HelloWorldPrint implements Runnable {
		@Override
		public void run() {
			synchronized (this) {
				System.out.println("HelloWorld");
				System.out.println(Thread.currentThread().getThreadGroup().getName() + " "
						+ Thread.currentThread().getName());
			}
		}
	}
	
	public static class NiHaoPrint implements Runnable {
		@Override
		public void run() {
			synchronized (this) {
				System.out.println("Nihao");
				System.out.println(Thread.currentThread().getThreadGroup().getName() + " "
						+ Thread.currentThread().getName());
			}
		}
	}
	
	public static void main(String[] args) {
		ThreadGroup tg= new ThreadGroup("print");
		Thread t1 = new Thread(tg,new HelloWorldPrint(),"helloWorld");
		Thread t2 = new Thread(tg,new NiHaoPrint(),"nihao");
		t1.start();
		t2.start();
	}
}
