package com.daimens.algorithm.january;

public class SolutionDay29_L0775 {
	
    public boolean isIdealPermutation(int[] A) {
    	int local = 0;
    	int n = A.length;
    	for (int i = 0; i < n; ++i) {
    		if (i + 1 < n && A[i] > A[i + 1]) local ++;
    	}
    	count = 0;
    	num = A;
    	merge(0, n);
    	return local == count;
    }
    
    int count = 0;
    int[] num;
    
    void merge(int s, int e) {
		if (e - s <= 1) return;
		int m = (s + e) / 2;
		merge(s, m);
		merge(m, e);
		for (int i = s, j = m; i < m; ++i) {
			while (j < e && num[i] > num[j]) j ++;
			count += j - m;
		}
		
		mergeSort(s, e);
	}
	
	void mergeSort(int s, int e) {
		int m = (s + e) / 2;
		int[] aux = new int[e - s];
		int i = s;
		int j = m;
		int k = 0;
		
		while (i < m && j < e) {
			if (num[i] < num[j]) aux[k++] = num[i++];
			else aux[k++] = num[j++];
		}
		
		while (i < m) aux[k++] = num[i++];
		while (j < e) aux[k++] = num[j++];
		
		for (int t = 0, l = s; l < e; ++l) num[l] = aux[t++];
	}
	
	public static void main(String[] args) {
		SolutionDay29_L0775 day = new SolutionDay29_L0775();
		int[] nums = {0, 3, 2, 1};
		System.out.println(day.isIdealPermutation(nums));
	}
    
}
