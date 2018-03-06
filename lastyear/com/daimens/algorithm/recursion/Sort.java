package com.daimens.algorithm.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.daimens.algorithm.utils.LoadData;
import com.daimens.algorithm.utils.RandomUtil;

public class Sort {

	
	//递归选择排序
	public static void selectionSort(String[] elements){
		selectionSort(elements,0);
	}
	

	private static void selectionSort(String[] elements,int start){
		if (start <= elements.length-1){
			String tmp = elements[start];
			int index = -1;
			for (int i = start + 1; i < elements.length; i++){
				if (elements[i].compareTo(tmp) < 0){
					tmp = elements[i];
					index = i;
				}
			}
			if (index != -1){
				swap(elements, start, index);
			}
			selectionSort(elements, start + 1);
		}
	}
	
	public static void insertionSort(String[] elements, int lo, int hi, int d){
		for (int i = lo + 1; i <= hi; i++){
			for (int j = i; j > lo && elements[j].charAt(d) < elements[j-1].charAt(d); j--){
				swap(elements, j, j-1);
			}
		}
	}
	
	//递归插入排序
	public static void insertionSort(String[] elements){
		insertionSort(elements,elements.length-1);
	}
	
	private static void insertionSort(String[] elements, int end){
		if(end >= 1){
			insertionSort(elements,end-1); //保证前n-1个元素有序
			
			int index = end;
			String tmp = elements[end];
			for (int i = 0; i <= end -1; i++){
				if (elements[i].compareTo(elements[end]) > 0){
					index = i;
					break;
				}
			}
			
			if (index == end) return;
			
			for (int i = end; i >= index + 1; i--){
				elements[i] = elements[i-1];
			}
			
			elements[index] = tmp;
		}
	}
	
	//基数排序
	public static void radixSort(Long[] elements){
		//确保所有数的位数相等
		
		Map<Integer,List<Long>> map = new HashMap<>();
		
		for (int i = 0; i <= 9; i++){
			map.put(i, new ArrayList<>());
		}
		
		List<Long> ans = new ArrayList<>();
		for (int i = 1; i <= 10; i++){
			//取出第一位
		}
		
	}
	
//	private static void insertionSort(String[] elements, int end){
//		if(end >= 1){
//			insertionSort(elements,end-1); //保证前n-1个元素有序
//			
//			int index = end -1;
//			String tmp = elements[end];
//			
//			while (index >= 0 && elements[index].compareTo(elements[end]) > 0){
//				elements[index+1] = elements[index];
//				index --;
//			}
//			
//			elements[index+1] = tmp;
//		}
//	}
	
	private static void swap(String[] ele, int i, int j){
		String tmp = ele[i];
		ele[i] = ele[j];
		ele[j] = tmp;
	}
	
	public static<T> boolean isSorted(Comparable<T>[] elements){
		
		for (int i = 1; i < elements.length; i++){
			if (elements[i].compareTo((T) elements[i-1]) < 0){
				return false;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		String[] elements = LoadData.loadStringArray("./data/1000words.txt");
		System.out.println(isSorted(elements));
		insertionSort(elements,0,elements.length-1,1);
		//insertionSort(elements);
		System.out.println(isSorted(elements));
		
	}
		
}
