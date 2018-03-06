package com.daimens.algorithm.may;

import com.daimens.algorithm.utils.RandomUtil;

/**
 * 
 * @author DemonSong
 * 
 * 实现基于插入排序的优先队列
 * 
 * 插入元素   O(n)
 * 删除元素   O(n)
 *
 * @param <T>
 */
public class PriorityQueue<T extends Comparable<T>> {
	
	
	T[] array;
	int N = 0;
	int SIZE;
	
	@SuppressWarnings("unchecked")
	public PriorityQueue(int SIZE){
		this.SIZE = SIZE;
		array = (T[]) new Comparable[SIZE];
	}
	
	public void offer(T key){
		if (N == 0){
			array[N++] = key;
			return;
		}
		int i = 0;
		while (i < N && key.compareTo(array[i]) > 0) i++;
		if (i == N){
			array[N++] = key;
			return;
		}
		rightShift(i);
		array[i] = key;
	}
	
	private void rightShift(int insert){
		N++;
		for (int i = N-1; i >= insert+1; --i){
			array[i] = array[i-1];
		}
	}
	
	
	public T poll(){
		if (this.isEmpty()) return null;
		T ele = array[0];
		leftShift();
		return ele;
	}
	
	private void leftShift(){
		for (int i = 1; i < N; i++){
			array[i-1] = array[i]; 
		}
		array[N-1] = null;
		N--;
	}
	
	public T peek(){
		return array[0];
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
        for (int i = 0; i < N; i++){
        	sb.append(array[i]+", ");
        }
        String res = sb.toString().substring(0, sb.length()-2);
        return res + "]";
	}
	
	public static void main(String[] args) {
		int[] test = RandomUtil.randomSet(1, 100, 10);
		PriorityQueue<Integer> queue = new PriorityQueue<>(10);
		for (int i = 0; i < test.length; i++){
			queue.offer(test[i]);
		}
		while (!queue.isEmpty())
			System.out.println(queue.poll());
	}
}
