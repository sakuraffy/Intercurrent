package cn.sakuraffy.base;

import java.util.HashMap;

public class HashMapBug {
	private static HashMap<String,Integer> map = new HashMap<>();
	
	public static class BugThread extends Thread {
		private int start = 0;
		public BugThread(int start) {
			this.start = start;
		}
		
		@Override
		public void run() {
			for(int i = start; i < 100000; i += 2) {
				map.put(Integer.toString(i), i);
			}
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new BugThread(0);
		Thread t2 = new BugThread(1);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(map.size());
	}
}
