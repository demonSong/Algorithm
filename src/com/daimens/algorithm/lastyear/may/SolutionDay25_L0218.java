package com.daimens.algorithm.may;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 
 * @author DemonSong
 * 
 *         218.The Skyline Problem
 * 
 *         A city's skyline is the outer contour of the silhouette formed by all
 *         the buildings in that city when viewed from a distance. Now suppose
 *         you are given the locations and height of all the buildings as shown
 *         on a cityscape photo (Figure A), write a program to output the
 *         skyline formed by these buildings collectively (Figure B).
 * 
 *         Buildings Skyline Contour The geometric information of each building
 *         is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri
 *         are the x coordinates of the left and right edge of the ith building,
 *         respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri
 *         ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all
 *         buildings are perfect rectangles grounded on an absolutely flat
 *         surface at height 0.
 * 
 *         For instance, the dimensions of all buildings in Figure A are
 *         recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ]
 *         .
 * 
 *         The output is a list of "key points" (red dots in Figure B) in the
 *         format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines
 *         a skyline. A key point is the left endpoint of a horizontal line
 *         segment. Note that the last key point, where the rightmost building
 *         ends, is merely used to mark the termination of the skyline, and
 *         always has zero height. Also, the ground in between any two adjacent
 *         buildings should be considered part of the skyline contour.
 * 
 *         For instance, the skyline in Figure B should be represented as:[ [2
 *         10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
 * 
 *         Notes:
 * 
 *         The number of buildings in any input list is guaranteed to be in the
 *         range [0, 10000]. The input list is already sorted in ascending order
 *         by the left x position Li. The output list must be sorted by the x
 *         position. There must be no consecutive horizontal lines of equal
 *         height in the output skyline. For instance, [...[2 3], [4 5], [7 5],
 *         [11 5], [12 7]...] is not acceptable; the three lines of height 5
 *         should be merged into one in the final output as such: [...[2 3], [4
 *         5], [12 7], ...]
 *
 */
public class SolutionDay25_L0218 {
	
//	private class Sky{
//		int l;
//		int r;
//		int h;
//		public Sky(int l, int r, int h){
//			this.l = l;
//			this.r = r;
//			this.h = h;
//		}
//		
//		@Override
//		public String toString() {
//			StringBuilder sb = new StringBuilder();
//			sb.append("[" + l + ", " + r + ", " + h + "]");
//			return sb.toString();
//		}
//	}
//	
//	public List<int[]> getSkyline(int[][] buildings) {
//		Sky[] skys = new Sky[buildings.length];
//		
//		PriorityQueue<Sky> lq = new PriorityQueue<>((a,b) -> (a.l !=  b.l ? a.l - b.l : b.h - a.h));
//		PriorityQueue<Sky> rq = new PriorityQueue<>((a,b) -> (a.r !=  b.r ? a.r - b.r : a.h - b.h));
//		
//		for (int i = 0; i < buildings.length; i++){
//			skys[i] = new Sky(buildings[i][0],buildings[i][1],buildings[i][2]);
//			lq.offer(skys[i]);
//			rq.offer(skys[i]);
//		}
//		
//		PriorityQueue<Sky> hq = new PriorityQueue<>((a,b) -> (b.h - a.h));
//		
//		List<int[]> ans = new ArrayList<int[]>();
//		while (!lq.isEmpty() && !rq.isEmpty()){
//			if (lq.peek().l < rq.peek().r){
//				hq.offer(lq.peek());
//				if (ans.size() == 0 || ans.get(ans.size()-1)[1] != hq.peek().h)
//					ans.add(new int[]{lq.peek().l,hq.peek().h});
//				lq.poll();
//			}else if (lq.peek().l > rq.peek().r){
//				hq.remove(rq.peek());
//				int height = hq.isEmpty() ? 0 : hq.peek().h;
//				if (ans.size() == 0 || ans.get(ans.size()-1)[1] != height)
//					ans.add(new int[]{rq.peek().r, height});
//				rq.poll();
//			}else{
//				hq.offer(lq.peek());
//				hq.remove(rq.peek());
//				if (ans.size() == 0 || ans.get(ans.size()-1)[1] != hq.peek().h)
//					ans.add(new int[]{lq.peek().l,hq.peek().h});
//				lq.poll();
//				rq.poll();
//			}
//		}
//		
//		while (!rq.isEmpty()){
//			hq.remove(rq.peek());
//			int height = hq.isEmpty() ? 0 : hq.peek().h;
//			if (ans.size() == 0 || ans.get(ans.size()-1)[1] != height)
//				ans.add(new int[]{rq.peek().r, height});
//			rq.poll();
//		}
//		
//		return ans;
//    }
	
	public List<int[]> getSkyline(int[][] buildings) {
		List<int[]> ans = new ArrayList<>();
		List<int[]> heights = new ArrayList<>();
		for (int i = 0; i < buildings.length; i++){
			heights.add(new int[]{buildings[i][0],-buildings[i][2]});
			heights.add(new int[]{buildings[i][1], buildings[i][2]});
		}
		Collections.sort(heights, (a,b) -> (a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]));
		Queue<Integer> queue = new PriorityQueue<>((a,b) -> (b-a));
		queue.offer(0);
		int prev = 0;
		for (int[] h : heights){
			if (h[1] < 0){
				queue.offer(-h[1]);
			}else{
				queue.remove(h[1]);
			}
			int curr = queue.peek();
			if (curr != prev){
				ans.add(new int[]{h[0],curr});
				prev = curr;
			}
		}
		return ans;
	}
	
	
	public static void main(String[] args) {
		SolutionDay25_L0218 day = new SolutionDay25_L0218();
		//[ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ]
		int[][] buildings = {{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
		day.getSkyline(buildings);
	}

}
