package cn.sakuraffy.base;

import java.util.ArrayList;
import java.util.Vector;

public class ArrayListBug {
	private static ArrayList<Integer> al = new ArrayList<>(10);
	private static Vector<Integer> v = new Vector<>(10);
	
	public static class BugThread extends Thread {
		@Override
		public void run() {
			for(int i = 0; i < 100000; i++) {
				al.add(i);
				v.add(i);
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new BugThread();
		Thread t2 = new BugThread();
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println("al.size() : " + al.size());
		System.out.println("v.size() : " + v.size());
	}
}
