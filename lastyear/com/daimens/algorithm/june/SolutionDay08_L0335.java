package com.daimens.algorithm.june;

/**
 * 
 * @author DemonSong
 * 
 *         335.Self Crossing
 * 
 *         You are given an array x of n positive numbers. You start at point
 *         (0,0) and moves x[0] metres to the north, then x[1] metres to the
 *         west, x[2] metres to the south, x[3] metres to the east and so on. In
 *         other words, after each move your direction changes
 *         counter-clockwise.
 * 
 *         Write a one-pass algorithm with O(1) extra space to determine, if
 *         your path crosses itself, or not.
 * 
 *         Example 1: Given x = [2, 1, 1, 2] , ┌───┐ │ │ └───┼──> │
 * 
 *         Return true (self crossing) Example 2: Given x = [1, 2, 3, 4] ,
 *         ┌──────┐ │ │ │ │ └────────────>
 * 
 *         Return false (not self crossing) Example 3: Given x = [1, 1, 1, 1] ,
 *         ┌───┐ │ │ └───┼>
 * 
 *         Return true (self crossing)
 *
 */
public class SolutionDay08_L0335 {
	
//	class Line{
//		public int i;
//		public int j;
//		public int val;
//		public boolean vertical;
//		public Line(int i, int j, int val, boolean vertical){
//			this.i = i;
//			this.j = j;
//			this.val = val;
//			this.vertical = vertical;
//		}
//	}
//	
//	static final boolean VETRICAL = true;
//	static final int[][] dir = {{0,1},{-1,0},{0,-1},{1,0}};
//	public boolean isSelfCrossing(int[] x) {
//		List<Line> verticals = new ArrayList<>();
//		List<Line> horizons = new ArrayList<>();
//		List<int[]> points = new ArrayList<>();
//		int[] now = new int[]{0,0};
//		points.add(now);
//		for (int i = 0; i < x.length; ++i){
//			int[] d = dir[i % 4];
//			int[] next = new int[2];
//			next[0] = now[0] + x[i] * d[0];
//			next[1] = now[1] + x[i] * d[1];
//			
//			if (cross(points, next)) return true;
//			
//			if ((i % 4) == 0 || (i % 4) == 2){
//				int y1 = next[1], y2 = now[1];
//				int sum = y1 + y2;
//				y1 = (y1 < y2) ? y1 : sum - y1;
//				y2 = sum - y1;
//				Line line = new Line(y1, y2, next[0], VETRICAL);
//				if (cross(horizons, line)) return true;
//				verticals.add(line);
//			}else{
//				int x1 = next[0], x2 = now[0];
//				int sum = x1 + x2;
//				x1 = (x1 < x2) ? x1 : sum - x1;
//				x2 = sum - x1;
//				Line line = new Line(x1, x2, next[1], !VETRICAL);
//				if (cross(verticals, line)) return true;
//				horizons.add(line);
//			}
//			now = next;
//			points.add(now);
//		}
//		return false;
//    }
//	
//	private boolean cross(List<int[]> points, int[] p){
//		for (int[] pt : points){
//			if (pt[0] == p[0] && pt[1] == p[1]) return true;
//		}
//		return false;
//	}
//	
//	private boolean cross(List<Line> set, Line a1){
//		for (Line line : set){
//			if (selfCross(line, a1)) return true;
//		}
//		return false;
//	}
//	
//	private boolean selfCross(Line a1, Line a2){
//		if (a1.vertical == a2.vertical) return false;
//		return a1.val < a2.j && a1.val > a2.i && a2.val < a1.j && a2.val > a1.i;
//	}
	
	public boolean isSelfCrossing(int[] x) {
		if (x.length <= 3) return false;
		for (int l = 3; l < x.length; ++l){
			if (x[l] >= x[l-2] && x[l-1] <= x[l-3]) return true;
			if (l >= 4){
				if (x[l] + x[l-4] >= x[l-2] && x[l-1] == x[l-3]) return true;
			}
			if (l >= 5){
				if (x[l-2] >= x[l-4] && x[l] + x[l-4] >= x[l-2] && x[l-1] + x[l-5] >= x[l-3] && x[l-1] <= x[l-3]) return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		SolutionDay08_L0335 day = new SolutionDay08_L0335();
		int[] x = {1,1,2,2,3,1,1};
		System.out.println(day.isSelfCrossing(x));
	}
}
