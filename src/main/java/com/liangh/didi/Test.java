package com.liangh.didi;

public class Test {

	public static void main(String[] args) {

		Test test = new Test();

		int [] arr = {1,0,2,0,4,4,3};

		int num = test.score(arr);

		System.out.println("num: " + num);
	}

	private int score(int[] arr) {

		// 先找到最小的索引
		int index = findMinIndex(arr);

		int m = 1;
		int count = 1;

		for (int i = 0; i < arr.length - 1; i++) {

			if( arr[i + 1] > arr[i]){
				m++;
			}else {
				m--;
				if(m == 0){
					m = 1;
				}
			}
			count += m;
		}
		return count;
	}

	private int findMinIndex(int[] arr) {

		int minIndex = 0;
		for (int i = 0; i < arr.length; i++) {

		}


		return 0;
	}


}
