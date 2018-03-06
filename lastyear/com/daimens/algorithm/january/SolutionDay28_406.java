package com.daimens.algorithm.january;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Demon Song
 * 406.Queue Reconstruction by Height
 * Suppose you have a random list of people standing in a queue. Each person is described
 * by a pair of integers (h,k),where h is the height of the person and k is the number of people 
 * in front of this person who have a height greater than or equal to h. Write an algorithm to 
 * reconstruct the queue.
 * Note:
 * The number of people is less than 1100.
 * 
 * Example
 * Input:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * Output:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 *
 */
public class SolutionDay28_406 {
	//随机Queue
	public int[][] reconstructQueue(int[][] people){
		//根据身高进行排序，然后进行向后操作，但要注意一个问题，如果遇到身高一致的情况，把k值大的排在前面
		
		//quickSort(people,0,people.length-1);
		Arrays.sort(people, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] != o2[0] ? o1[0] - o2[0] : -o1[1] + o2[1];
			}
		});
		
//		for(int i = people.length-2; i>=0; i--){
//			int[] temp = people[i];
//			//所有元素前移一格,这种做法 速度相当的慢嘛？
//			for(int j = i+1; j <= temp[1] + i; j++){
//				people[j-1] = people[j];
//			}
//			people[temp[1] +i] = temp;
//		}
		
		//这里可以用list做插入，速度要快得多
		List<int[]> res = new LinkedList<int[]>();
		for(int i = people.length-1; i >=0;i--){
			res.add(people[i][1],people[i]);
		}
	
		return res.toArray(new int[people.length][]);
	}
	
	public void quickSort(int[][] a,int low,int high){
		if(low < high){
			int middle = getMiddle(a, low, high);
			quickSort(a,0,middle-1);
			quickSort(a,middle+1,high);
		}
	}
	
	public int getMiddle(int[][] a,int low,int high){
		int height = a[low][0];
		int count = a[low][1];
		while(low < high){
			while(low < high && (a[high][0] > height || (a[high][0] == height && a[high][1] < count ))){
				high--;
			}
			a[low][0] = a[high][0];
			a[low][1] = a[high][1];
			while(low < high && (a[low][0] < height || (a[low][0] == height && a[low][1] > count))){
				low ++;
			}
			a[high][0] = a[low][0];
			a[high][1] = a[low][1];
		}
		a[low][0] = height;
		a[low][1] = count;
		return low;
	}
	
	public static void main(String[] args) {
		SolutionDay28_406 day28_406 = new SolutionDay28_406();
		
		int[][] people = {{8,2},{4,2},{4,5},{2,0},{7,2},{1,4},{9,1},{3,1},{9,0},{1,0}};
		day28_406.reconstructQueue(people);
	}
	//quick sort
}
