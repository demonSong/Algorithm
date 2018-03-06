package com.daimens.algorithm.may;

import java.awt.image.RescaleOp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * 
 * @author DemonSong
 * 
 *         587.Erect the Fence
 * 
 *         There are some trees, where each tree is represented by (x,y)
 *         coordinate in a two-dimensional garden. Your job is to fence the
 *         entire garden using the minimum length of rope as it is expensive.
 *         The garden is well fenced only if all the trees are enclosed. Your
 *         task is to help find the coordinates of trees which are exactly
 *         located on the fence perimeter.
 * 
 *         Example 1: Input: [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]] Output:
 *         [[1,1],[2,0],[4,2],[3,3],[2,4]] Explanation:
 * 
 *         Example 2: Input: [[1,2],[2,2],[4,2]] Output: [[1,2],[2,2],[4,2]]
 *         Explanation:
 * 
 *         Even you only have trees in a line, you need to use rope to enclose
 *         them. Note:
 * 
 *         All trees should be enclosed together. You cannot cut the rope to
 *         enclose trees that will separate them in more than one group. All
 *         input integers will range from 0 to 100. The garden has at least one
 *         tree. All coordinates are distinct. Input points have NO order. No
 *         order required for output.
 * 
 *
 */
public class SolutionDay14_L0587 {

//	public List<Point> outerTrees(Point[] points) {
//		Point first = points[0];
//		int firstIndex = 0;
//		// Find the leftmost point
//		for (int i = 0; i < points.length; i++) {
//			Point point = points[i];
//			if (point.x < first.x) {
//				first = point;
//				firstIndex = i;
//			}
//		}
//
//		Set<Point> answer = new HashSet<>();
//		Point cur = first;
//		int curIndex = firstIndex;
//		answer.add(first);
//
//		do {
//			Point next = points[0];
//			int nextIndex = 0;
//			for (int i = 1; i < points.length; i++) {
//				if (i == curIndex)
//					continue;
//				Point p = points[i];
//				int cross = crossProductLength(p, cur, next);
//				if (nextIndex == curIndex || cross > 0 ||
//				// Handle multi points in a line
//						(cross == 0 && distance(p, cur) > distance(next, cur))) {
//					next = p;
//					nextIndex = i;
//				}
//			}
//			// Handle multi points in a line
//			for (int i = 0; i < points.length; i++) {
//				Point p = points[i];
//				int cross = crossProductLength(p, cur, next);
//				if (i != curIndex && cross == 0) {
//					answer.add(p);
//				}
//			}
//
//			cur = next;
//			curIndex = nextIndex;
//		} while (curIndex != firstIndex);
//
//		return new ArrayList<>(answer);
//	}
//
//	private int crossProductLength(Point A, Point B, Point C) {
//		// Get the vectors' coordinates.
//		int BAx = A.x - B.x;
//		int BAy = A.y - B.y;
//		int BCx = C.x - B.x;
//		int BCy = C.y - B.y;
//
//		// Calculate the Z coordinate of the cross product.
//		return (BAx * BCy - BAy * BCx);
//	}
//
//	private int distance(Point p1, Point p2) {
//		return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
//	}
	
	
	//Solution 1:  暴力破解
//	public List<Point> outerTrees(Point[] points) {
//		Set<Point> ans = new HashSet<>();
//		if (points.length == 1){
//			ans.add(points[0]);
//			return new ArrayList<>(ans);
//		}
//		for (int i = 0; i < points.length; i++){
//			for (int j = i + 1; j < points.length; j++){
//				int oneSide = 0;
//				for (int k = 0; k < points.length; k++){
//					if (k == i || k == j) continue;
//					if (calcuTriangle(points[i], points[j], points[k]) > 0){
//						oneSide++;
//					}
//				}
//				
//				if (oneSide == points.length-2 || oneSide == 0){
//					ans.add(points[i]);
//					ans.add(points[j]);
//				}
//				
//				int otherSide = 0;
//				for (int k = 0; k < points.length; k++){
//					if (k == i || k == j) continue;
//					if (calcuTriangle(points[i], points[j], points[k]) < 0){
//						otherSide++;
//					}
//				}
//				
//				if (otherSide == points.length-2 || otherSide == 0){
//					ans.add(points[i]);
//					ans.add(points[j]);
//				}
//				
//			}
//		}
//		
//		return new ArrayList<>(ans);
//	}
	
	//Solution 2: 分治法
//	public List<Point> outerTrees(Point[] points) {
//		List<Point> pp = Arrays.asList(points);
//		helper(pp,true);
//		helper(pp, false);
//		return new ArrayList<>(ans);
//	}
	
	Set<Point> ans = new HashSet<>();
	private void helper(List<Point> points, boolean calcuConvex){
		if (points.size() == 0) return;
		Collections.sort(points, new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				return o1.x != o2.x ? o1.x - o2.x : o1.y - o2.y;
			}
		});
		int fir = 0;
		int lst = points.size() - 1;

		ans.add(points.get(fir));
		ans.add(points.get(lst));

		if (points.size() == 2)
			return;

		// oneLine
		boolean isLine = true;
		for (int i = 0; i < points.size(); i++) {
			if (i == fir || i == lst)
				continue;
			if (calcuTriangle(points.get(fir), points.get(lst), points.get(i)) != 0) {
				isLine = false;
				break;
			}
		}
		if (isLine) {
			ans.addAll(points);
			return;
		}

		int maxIndex = -1;
		int max = 0;
		for (int i = 0; i < points.size(); i++) {
			if (i == fir || i == lst)
				continue;
			// 上凸包
			if (calcuConvex && calcuTriangle(points.get(fir), points.get(lst), points.get(i)) > max) {
				maxIndex = i;
				max = calcuTriangle(points.get(fir), points.get(lst), points.get(i));
			}
			// 下凸包
			if (!calcuConvex && -calcuTriangle(points.get(fir), points.get(lst), points.get(i)) > max) {
				maxIndex = i;
				max = -calcuTriangle(points.get(fir), points.get(lst), points.get(i));
			}
		}

		if (maxIndex == -1) {
			return;
		}

		List<Point> c1 = new ArrayList<>();
		split(fir, maxIndex, points, c1, calcuConvex);
		helper(c1,calcuConvex);

		List<Point> c2 = new ArrayList<>();
		split(lst, maxIndex, points, c2, !calcuConvex);
		helper(c2,calcuConvex);
	}
	
	private void split(int a1, int a2, List<Point> points, List<Point> part1, boolean isConvex) {
		for (int i = 0; i < points.size(); i++) {
			if (i == a1 || i == a2) {
				part1.add(points.get(i));
				continue;
			}
			if (isConvex && calcuTriangle(points.get(a1), points.get(a2), points.get(i)) >= 0) {
				part1.add(points.get(i));
			}

			if (!isConvex && calcuTriangle(points.get(a1), points.get(a2), points.get(i)) <= 0) {
				part1.add(points.get(i));
			}
		}
	}

	private int calcuTriangle(Point a1, Point a2, Point a3) {
		return a1.x * a2.y + a3.x * a1.y + a2.x * a3.y 
				- a3.x * a2.y - a2.x * a1.y - a1.x * a3.y;
	}
	
	public List<Point> outerTrees(Point[] points) {
		return boundaryScan(points);
	}
	
	private List<Point> boundaryScan(Point[] points){
		int n = points.length;
		if (n <= 3) return Arrays.asList(points);
		int minIndex = -1;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++){
			if (points[i].y < min){
				min = points[i].y;
				minIndex = i;
			}
		}
		
		Point tmp = points[0];
		points[0] = points[minIndex];
		points[minIndex] = tmp;
		
		Stack<Point> stack = new Stack<>();
		boolean[] visited = new boolean[n];
		while (stack.isEmpty() || stack.peek() != points[0]){
			if (stack.isEmpty()) stack.push(points[0]);
			visited[0] = true;
			Point vertex = stack.peek();
			Point minVertex = null;
			int index = -1;
			//每次都选择pints[0]
			for (int i = 0; i < n; i++){
				if (!visited[i]){
					minVertex = points[i];
					index = i;
					break;
				}
			}
			
			if (index == -1) {
				stack.push(points[0]);
				break;
			}
			
			//顶点还需要被使用一次
			for (int i = 0; i < n; i++){
				if (equal(vertex, points[i])) continue;
				if (!visited[i] && cross(vertex, points[i], minVertex) >= 0){
					minVertex = points[i];
					index = i;
				}
				
			}
			visited[index] = true;
 			stack.push(minVertex);
		}
		stack.pop();
		return new ArrayList<>(stack);
	}
	
	
	private List<Point> jarvisMarch(Point[] points){
		List<Point> ans = new ArrayList<>();
		if (points == null || points.length == 0) return ans;
		Set<Point> visited = new HashSet<>();
		
		Point start = points[0];
		for (int i = 1; i < points.length; i++){
			if (points[i].y > start.y || (points[i].y == start.y && points[i].x < start.x)){
				start = points[i];
			}
		}
		if(points.length == 1) return Arrays.asList(points);
		
		Point cur = start;
		while (cur != null && visited.add(cur)){
			ans.add(cur);
			for (int i = 0; i < points.length; i++){
				if (visited.contains(points[i])) continue;
				if (isBorder(cur, points[i], points)){
					cur = points[i];
					break;
				}
			}
		}
		
		for (int i = 0; i < points.length; i++){
			if (visited.contains(points[i]) || ans.contains(points[i])) continue;
			int size = ans.size();
			for (int j = 0; j < size; j++){
				Point p = ans.get(j);
				if (isBorder(p, points[i], points)){
					visited.add(points[i]);
					ans.add(points[i]);
					break;
				}
			}
		}
		
		return ans;
	}
	
	private boolean isBorder(Point p1, Point p2, Point [] points){
        int dx = p1.x-p2.x;
        int dy = p1.y-p2.y;
        int b = p1.x*dy - p1.y*dx;
        int prev = 0;
        for(int i = 0;i<points.length;i++){
            int x = points[i].x;
            int y = points[i].y;
            int sign = dx*y-dy*x+b;
            if(sign== 0) continue;
            if(sign*prev < 0) return false;
            if(sign <0) prev = -1;
            else prev = 1;
        }
        return true;
    }
	
	private int distance(Point p1, Point p2) {
		return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
	}
	
	private List<Point> GrahamScan(Point[] points){
		int n = points.length;
		if (n <= 2) return Arrays.asList(points);
		Arrays.sort(points,new Comparator<Point>(){
			@Override
			public int compare(Point o1, Point o2) {
				return o1.y != o2.y ? o1.y - o2.y : o1.x - o2.x;
			}
		});
		
		int[] stack = new int[n+2];
		int p = 0;
		for (int i = 0; i < n; i++) {
			while (p >= 2 && cross(points[stack[p - 2]], points[i], points[stack[p - 1]]) > 0)
				p--;
			stack[p++] = i;
		}
		
		int inf = p + 1;
		for (int i = n -2; i >= 0; i--){
			if (equal(points[stack[p-2]], points[i])) continue;
			while (p >= inf && cross(points[stack[p-2]], points[i], points[stack[p-1]]) > 0)
				p--;
			stack[p++] = i;
		}
		
		int len = Math.max(p - 1, 1);
		List<Point> ret = new ArrayList<>();
		for (int i = 0; i < len; i++){
			ret.add(points[stack[i]]);
		}
		return ret;
	}
	
	private int cross(Point o, Point a, Point b){
		return (a.x-o.x)*(b.y-o.y) - (a.y - o.y) * (b.x - o.x);
	}
	
	private boolean equal(Point a, Point b){
		return a.x == b.x && a.y == b.y;
	}
	
//	private List<Point> GrahamScan(Point[] points){
//		int n = points.length;
//		if (n <= 3) return Arrays.asList(points);
//		Arrays.sort(points,new Comparator<Point>(){
//			@Override
//			public int compare(Point o1, Point o2) {
//				return o1.y != o2.y ? o1.y - o2.y : o1.x - o2.x;
//			}
//		});
//		
//		Stack<Point> stack = new Stack<>();
//		stack.push(points[0]);
//		stack.push(points[1]);
//		
//		for (int i = 2; i < n; i++){
//			Point a = stack.pop();
//			Point b = points[i];
//			if (cross(stack.peek(), b, a) > 0){
//				stack.push(b);
//			}
//			else{
//				stack.push(a);
//				stack.push(b);
//			}
//		}
//		
//		for (int i = n-2; i >= 0; i--){
//			Point a = stack.pop();
//			Point b = points[i];
//			if (cross(stack.peek(), b, a) > 0){
//				stack.push(b);
//			}
//			else{
//				stack.push(a);
//				stack.push(b);
//			}
//		}
//		stack.pop();
//		return new ArrayList<>(stack);
//	}
	
//	private void calcuConvex(List<Point> points) {
//		if (points.size() == 0) return;
//		Collections.sort(points, new Comparator<Point>() {
//			@Override
//			public int compare(Point o1, Point o2) {
//				return o1.x != o2.x ? o1.x - o2.x : o1.y - o2.y;
//			}
//		});
//		int fir = 0;
//		int lst = points.size() - 1;
//
//		ans.add(points.get(fir));
//		ans.add(points.get(lst));
//
//		if (points.size() == 2)
//			return;
//
//		// oneLine
//		boolean isLine = true;
//		for (int i = 0; i < points.size(); i++) {
//			if (i == fir || i == lst)
//				continue;
//			if (calcuTriangle(points.get(fir), points.get(lst), points.get(i)) != 0) {
//				isLine = false;
//				break;
//			}
//		}
//		if (isLine) {
//			ans.addAll(points);
//			return;
//		}
//
//		// 计算上凸包
//		int maxIndex = -1;
//		int max = 0;
//		for (int i = 0; i < points.size(); i++) {
//			if (i == fir || i == lst)
//				continue;
//			// 上凸包
//			if (calcuTriangle(points.get(fir), points.get(lst), points.get(i)) > max) { // 不考虑连线的点
//				maxIndex = i;
//				max = calcuTriangle(points.get(fir), points.get(lst), points.get(i));
//			}
//		}
//
//		if (maxIndex == -1) {
//			return;
//		}
//
//		List<Point> c1 = new ArrayList<>();
//		split(fir, maxIndex, points, c1, true);
//		calcuConvex(c1);
//
//		List<Point> c2 = new ArrayList<>();
//		split(lst, maxIndex, points, c2, false);
//		calcuConvex(c2);
//	}
	
//	private void calcuConcave(List<Point> points) {
//		if (points.size() == 0)
//			return;
//		Collections.sort(points, new Comparator<Point>() {
//			@Override
//			public int compare(Point o1, Point o2) {
//				return o1.x != o2.x ? o1.x - o2.x : o1.y - o2.y;
//			}
//		});
//
//		int fir = 0;
//		int lst = points.size() - 1;
//
//		ans.add(points.get(fir));
//		ans.add(points.get(lst));
//
//		if (points.size() == 2)
//			return;
//
//		// oneLine
//		boolean isLine = true;
//		for (int i = 0; i < points.size(); i++) {
//			if (i == fir || i == lst)
//				continue;
//			if (calcuTriangle(points.get(fir), points.get(lst), points.get(i)) != 0) {
//				isLine = false;
//				break;
//			}
//		}
//		if (isLine) {
//			ans.addAll(points);
//			return;
//		}
//
//		// 计算下凸包
//		int maxIndex = -1;
//		int max = 0;
//		for (int i = 0; i < points.size(); i++) {
//			if (i == fir || i == lst)
//				continue;
//			// 下凸包
//			if (-calcuTriangle(points.get(fir), points.get(lst), points.get(i)) > max) { // 不考虑连线的点
//				maxIndex = i;
//				max = -calcuTriangle(points.get(fir), points.get(lst), points.get(i));
//			}
//		}
//
//		if (maxIndex == -1) {
//			return;
//		}
//
//		List<Point> c3 = new ArrayList<>();
//		split(fir, maxIndex, points, c3, false);
//		calcuConcave(c3);
//
//		List<Point> c4 = new ArrayList<>();
//		split(lst, maxIndex, points, c4, true);
//		calcuConcave(c4);
//	}

	public static void main(String[] args) {
		SolutionDay14_L0587 day = new SolutionDay14_L0587();
		Point[] points = new Point[6];
		points[0] = new Point(1,1);
		points[1] = new Point(2,2);
		points[2] = new Point(2,0);
		points[3] = new Point(2,4);
		points[4] = new Point(3,3);
		points[5] = new Point(4,2);
//		Point[] points = new Point[3];
//		points[0] = new Point(1,2);
//		points[1] = new Point(2,2);
//		points[2] = new Point(4,2);
		
//		Point[] points = new Point[8];
//		points[0] = new Point(3,7);
//		points[1] = new Point(6,8);
//		points[2] = new Point(7,8);
//		points[3] = new Point(11,10);
//		points[4] = new Point(4,3);
//		points[5] = new Point(8,5);
//		points[6] = new Point(7,13);
//		points[7] = new Point(4,13);
		
//		Point[] points = new Point[5];
//		points[0] = new Point(0,1);
//		points[1] = new Point(1,0);
//		points[2] = new Point(100,99);
//		points[3] = new Point(100,100);
//		points[4] = new Point(99,100);
		
		
		day.outerTrees(points);
	}

}

class Point {
	int x;
	int y;

	Point() {
		x = 0;
		y = 0;
	}

	Point(int a, int b) {
		x = a;
		y = b;
	}
	
	@Override
	public String toString() {
		return "["+x+","+y+"]";
	}

}
