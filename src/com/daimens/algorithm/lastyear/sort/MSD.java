package com.daimens.algorithm.sort;

import com.daimens.algorithm.utils.LoadData;

public class MSD {
	
	private static int R = 256;
	private static final int M = 0;
	private static String[] aux;
	
	private static int charAt(String s, int d){
		if (d < s.length()) return s.charAt(d); else return -1;
	}
	
	public static void sort(String[] data){
		int N = data.length;
		aux = new String[N];
		sort(data, 0, N - 1, 0);
	}
	
	private static void sort(String[] data, int lo, int hi, int d){
		
		if (hi <= lo + M){
			insertionSort(data, lo, hi, d);
			return;
		}
		
		int[] count = new int[R + 2];
		
		for (int i = lo; i <= hi; i++){
			count[charAt(data[i], d) + 2] ++;
		}
		
		for (int i = 1; i < count.length; i++){
			count[i] += count[i-1];
		}
		
		for (int i = lo; i <= hi; i++){
			aux[count[charAt(data[i], d) + 1] ++] = data[i];
		}
		
		for (int i = lo; i <= hi; i++){
			data[i] = aux[i - lo];
		}
		
		for (int r = 0; r < R; r++){
			sort(data,lo + count[r], lo + count[r + 1] - 1, d+1);
		}
	}
	
	public static void insertionSort(String[] elements, int lo, int hi, int d){
		for (int i = lo + 1; i <= hi; i++){
			for (int j = i; j > lo && elements[j].charAt(d) < elements[j-1].charAt(d); j--){
				swap(elements, j, j-1);
			}
		}
	}
	
	private static void swap(String[] ele, int i, int j){
		String tmp = ele[i];
		ele[i] = ele[j];
		ele[j] = tmp;
	}
	
	public static void main(String[] args) {
		String[] elements = LoadData.loadStringArray("./data/13words.txt");
		sort(elements);
		System.out.println();
	}

}
