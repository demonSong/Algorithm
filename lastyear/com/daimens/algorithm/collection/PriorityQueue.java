package com.daimens.algorithm.collection;

import java.util.Comparator;
import com.daimens.algorithm.utils.RandomUtil;

public class PriorityQueue<Key extends Comparable<Key>> {
	
	Key[] array;
	int N = 0;
	int SIZE;
	private Comparator<? super Key> comparator;
	
	public PriorityQueue(int SIZE){
		this.SIZE = SIZE;
		array = (Key[]) new Comparable[SIZE+1];
	}
	
	public PriorityQueue(int SIZE, Comparator<? super Key> comparator){
		this(SIZE);
		this.comparator = comparator;
	}
	
	public void offer(Key key){
		array[++N] = key;
		swin(N);
	}
	
	
	private void swin(int k){
		while (k > 1 && less(k, k / 2)){
			swap(k, k / 2);
			k = k / 2;
		}
	}
	
	private boolean less(int i, int j){
		if (comparator != null) 
			return comparator.compare(array[i], array[j]) < 0;
		else 
			return array[i].compareTo(array[j]) < 0;
	}
	
	private void swap(int i, int j){
		Key tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
	
	public Key poll(){
		Key key= array[1];
		swap(1, N);
		array[N] = null;
		N--;
		sink(1);
		return key;
	}
	
	private void sink(int k){
		while (2*k <= N){
			int j = 2 * k;
			if (j < N && less(j+1, j)) j++;
			if(!less(j, k)) break;
			swap(k, j);
			k = j;
		}
	}
	
	public Key peek(){
		return array[1];
	}
	
	public boolean isEmpty(){
		return N == 0;
	}
	
	@Override
	public String toString() {
        if (this.isEmpty())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 1; i <= N; i++){
        	sb.append(array[i]+", ");
        }
        String res = sb.toString().substring(0, sb.length()-2);
        return res + "]";
	}
	
	public static void main(String[] args) {
		int[] test = RandomUtil.randomSet(0, 10, 9);
		PriorityQueue<Integer> queue = new PriorityQueue<>(10, (a, b) -> (b-a));
		for (int i = 0; i < test.length; i++){
			queue.offer(test[i]);
		}
		while (!queue.isEmpty()){
			System.out.println(queue.poll());
		}
	}
	
	static final int INF = 1 << 30;
	private static void solve(int[] nums){
		for (int i = 0; i < nums.length; i++){
			int index = -1, min = INF;
			for (int j = 0; j < nums.length; j++){
				if (nums[j] != INF && nums[j] < min){
					index = j;
					min = nums[j];
				}
			}
			System.out.println(min);
			nums[index] = INF;
		}
	}

}
