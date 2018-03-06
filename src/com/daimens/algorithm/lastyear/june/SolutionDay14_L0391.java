package com.daimens.algorithm.june;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author DemonSong
 * 
 *         391. Perfect Rectangle
 * 
 *         Given N axis-aligned rectangles where N > 0, determine if they all
 *         together form an exact cover of a rectangular region.
 * 
 *         Each rectangle is represented as a bottom-left point and a top-right
 *         point. For example, a unit square is represented as [1,1,2,2].
 *         (coordinate of bottom-left point is (1, 1) and top-right point is (2,
 *         2)).
 * 
 * 
 *         Example 1:
 * 
 *         rectangles = [ [1,1,3,3], [3,1,4,2], [3,2,4,4], [1,3,2,4], [2,3,3,4]
 *         ]
 * 
 *         Return true. All 5 rectangles together form an exact cover of a
 *         rectangular region.
 * 
 *         Example 2:
 * 
 *         rectangles = [ [1,1,2,3], [1,3,2,4], [3,1,4,2], [3,2,4,4] ]
 * 
 *         Return false. Because there is a gap between the two rectangular
 *         regions.
 * 
 *         Example 3:
 * 
 *         rectangles = [ [1,1,3,3], [3,1,4,2], [1,3,2,4], [3,2,4,4] ]
 * 
 *         Return false. Because there is a gap in the top center.
 * 
 *         Example 4:
 * 
 *         rectangles = [ [1,1,3,3], [3,1,4,2], [1,3,2,4], [2,2,4,4] ]
 * 
 *         Return false. Because two of the rectangles overlap with each other.
 *
 */
public class SolutionDay14_L0391 {

	public boolean isRectangleCover(int[][] rectangles) {
		int l = Integer.MAX_VALUE, r = Integer.MIN_VALUE, b = Integer.MAX_VALUE, t = Integer.MIN_VALUE;
		int sum = 0;
		Set<String> set = new HashSet<>();
		for (int i = 0; i < rectangles.length; ++i) {
			sum += (rectangles[i][2] - rectangles[i][0]) * (rectangles[i][3] - rectangles[i][1]);
			l = Math.min(l, rectangles[i][0]);
			b = Math.min(b, rectangles[i][1]);
			r = Math.max(r, rectangles[i][2]);
			t = Math.max(t, rectangles[i][3]);
			String s1 = rectangles[i][0] + " " + rectangles[i][1];
			String s2 = rectangles[i][0] + " " + rectangles[i][3];
			String s3 = rectangles[i][2] + " " + rectangles[i][1];
			String s4 = rectangles[i][2] + " " + rectangles[i][3];
			
			if (!set.add(s1)) set.remove(s1);
			if (!set.add(s2)) set.remove(s2);
			if (!set.add(s3)) set.remove(s3);
			if (!set.add(s4)) set.remove(s4);
		}

		if (set.size() != 4 || !set.contains(l + " " + b) || !set.contains(l + " " + t) || !set.contains(r + " " + b)
				|| !set.contains(r + " " + t))
			return false;

		// 不需要 等边判断？
		return (r - l) * (t - b) == sum;
	}

	public static void main(String[] args) {
		SolutionDay14_L0391 day = new SolutionDay14_L0391();
		int[][] rectangles = { { 1, 1, 3, 3 }, { 3, 1, 4, 2 }, { 3, 2, 4, 4 }, { 1, 3, 2, 4 }, { 2, 3, 3, 4 } };
		System.out.println(day.isRectangleCover(rectangles));
	}
}
