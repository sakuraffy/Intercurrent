package cn.sakuraffy.pattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class IntercurrentSearch {
	public static final int NUM = 20;
	public static final int THREAD_NUM = 5;
	
	private static int[] array;
	
	public static void makeData() {
		Random r = new Random();
		int[] arr = new int[NUM];
		for(int i = 0; i < NUM; i++) {
			arr[i] = i;
		}
		for(int i = 0; i < NUM; i++) {
			int temp = r.nextInt(NUM);
			int t = arr[i];
			arr[i] = arr[temp];
			arr[temp] = t;
		}
		array = arr;
	}
	
	public static class SearchTask implements Callable<Integer> {
		private int start;
		private int end;
		private int searchValue;
		
		public SearchTask(int start, int end, int searchValue) {
			super();
			this.start = start;
			this.end = end;
			this.searchValue = searchValue;
		}

		@Override
		public Integer call() throws Exception {
			for(int i = start; i < end; i++) {
				if (searchValue == array[i]) {
					return i;
				}
 			}
			return -1;
		}
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		makeData();
		int searchValue = 1;
		int size = NUM / THREAD_NUM;
		int ret = -1;
		ExecutorService es = Executors.newFixedThreadPool(THREAD_NUM);
		List<Future<Integer>> futures = new ArrayList<>(THREAD_NUM);
		for(int i = 0; i < NUM; i += size) {
			Future<Integer> future = es.submit(new SearchTask(i, i + size, searchValue));
			futures.add(future);
		}
		for(int i = 0; i < THREAD_NUM; i++) {
			if (futures.get(i).get() > 0) {
				ret = futures.get(i).get();
			}
		}
		System.out.println(Arrays.toString(array));
		System.out.println(ret);
	}
}
