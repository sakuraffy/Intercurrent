package cn.sakuraffy.pattern;

import java.util.Arrays;

public class OddEvenSort {
	public static void sort(int[] arr) {
		// �Ƿ񽻻�
		boolean exchFlag = true;
		int start = 0;
		//������ѭ����û�н��н�������
		while(start == 0 || exchFlag) {
			exchFlag = false;
			for(int i = start; i < arr.length - 1; i += 2) {
				if (arr[i] > arr[i+1]) {
					int temp = arr[i];
					arr[i] = arr[i+1];
					arr[i+1] = temp;
					exchFlag = true;
				}
			}
			if (start == 0) {
				start = 1;
			}else {
				start = 0;
			}
		}
	}
	
	public static void main(String[] args) {
		int[] arr = new int[]{1,2,3,6,5,4,7,8,9,0};
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}
}
