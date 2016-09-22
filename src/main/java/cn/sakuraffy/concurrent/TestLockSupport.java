package cn.sakuraffy.concurrent;

import java.util.concurrent.locks.LockSupport;

public class TestLockSupport {
	private static Object object = new Object();
	
	public static class ChangObjectThread extends Thread {
		public ChangObjectThread(String name) {
			super.setName(name);
		}
		
		@Override
		public void run() {
			synchronized (object) {
				System.out.println("in " + getName());
				LockSupport.park();
				if (interrupted()) {
					System.out.println(getName() + " interrupted");
				}
				System.out.println(getName() + " exit");
			}
		}
	}
	
	public static void main(String[] args) {
		ChangObjectThread t1 = new ChangObjectThread("t1");
		ChangObjectThread t2 = new ChangObjectThread("t2");
		t1.start();
		t2.start();
		t1.interrupt();
		LockSupport.unpark(t2);
	}
}
