package com.daimens.algorithm.october;

public class BinarySearch {
	
	int search(int[] arra, int key) {
		int n=arra.length;
		int l=0;
		int r=n-1;//12
		while(l<r) {
			int mid=l + (r - l + 1) / 2; // 向下取整
			if(arra[mid]>key) {
				r=mid - 1;
			}else {
				l=mid;
			}
		}
		return l;
	}
	
	public static void main(String[] args) {
		BinarySearch bs = new BinarySearch();
		int[] arra = {1, 2, 3, 4, 4, 5, 6, 6, 6, 7, 8, 9, 10};
		for (int i = 1; i <= 9; ++i) {
			System.out.println(bs.search(arra, i));
		}
	}

}
