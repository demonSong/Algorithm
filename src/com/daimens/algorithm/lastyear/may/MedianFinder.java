package com.daimens.algorithm.may;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 
 * @author DemonSong
 * 
 *         295.Find Median from Data Stream
 * 
 *         Median is the middle value in an ordered integer list. If the size of
 *         the list is even, there is no middle value. So the median is the mean
 *         of the two middle value.
 * 
 *         Examples: [2,3,4] , the median is 3
 * 
 *         [2,3], the median is (2 + 3) / 2 = 2.5
 * 
 *         Design a data structure that supports the following two operations:
 * 
 *         void addNum(int num) - Add a integer number from the data stream to
 *         the data structure. double findMedian() - Return the median of all
 *         elements so far. For example:
 * 
 *         addNum(1) addNum(2) findMedian() -> 1.5 addNum(3) findMedian() -> 2
 *
 */
public class MedianFinder {
	
	Queue<Integer> lf;
	Queue<Integer> rt;
	int N = 0;
    public MedianFinder() {
    	rt = new PriorityQueue<>((a,b) -> (a - b));
    	lf = new PriorityQueue<>((a,b) -> (b - a));
    }
    
//    public void addNum(int num) {
//    	if (N == 0) {
//    		rt.offer(num);
//    		N++;
//    		return;
//    	}
//    	
//    	int right = rt.peek();
//    	if (N % 2 == 0){
//    		if (num >= right){
//    			rt.offer(num);
//    		}else{
//    		    lf.offer(num);
//    			rt.offer(lf.poll());
//    		}
//    		N++;
//    	}else{
//    		if (num >= right){
//    			lf.offer(rt.poll());
//    			rt.offer(num);
//    		}else{
//    			lf.offer(num);
//    		}
//    		N++;
//    	}
//    }
    
    public void addNum(int num) {
    	lf.offer(num);
        rt.offer(lf.poll());
        if (lf.size() < rt.size()){
            lf.offer(rt.poll());
        }
    	N++;
    }
    
    public double findMedian() {
        return N % 2 == 0 ? (rt.peek() + lf.peek()) / 2.0 : lf.peek();
    }
    
    public static void main(String[] args) {
    	MedianFinder mf = new MedianFinder();
    	mf.addNum(1);
    	mf.addNum(2);
    	System.out.println(mf.findMedian());
    	mf.addNum(3);
    	System.out.println(mf.findMedian());
	}
}
