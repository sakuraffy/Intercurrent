package cn.sakuraffy.pattern;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IcOddEvenSort {
	private static boolean exchFlag = true;

	public static class OddEvenSort implements Runnable {
		private int i;
		private CountDownLatch cdl;
		private int[] arr;
		
		public OddEvenSort(int[] arr, int i, CountDownLatch cdl) {
			super();
			this.arr = arr;
			this.i = i;
			this.cdl = cdl;
		}

		@Override
		public void run() {
			if (arr[i] > arr[i+1]) {
				int temp = arr[i];
				arr[i] = arr[i+1];
				arr[i+1] = temp;
				exchFlag = true;
			}
			cdl.countDown();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		int[] arr = new int[]{1,2,3,6,5,4,7,8,9,0};
		int start = 0;
		ExecutorService es = Executors.newCachedThreadPool();
		while(start == 1 || exchFlag) {
			exchFlag = false;
			CountDownLatch cdl = new CountDownLatch(arr.length/2 - 
					(arr.length%2 == 0? start : 0));
			for(int i = start; i < arr.length - 1; i += 2) {
				es.execute(new OddEvenSort(arr, i, cdl));
			}
			cdl.await();
			if (start == 0) {
				start = 1;
			}else {
				start = 0;
			}
		}
		System.out.println(Arrays.toString(arr));
	}
}
