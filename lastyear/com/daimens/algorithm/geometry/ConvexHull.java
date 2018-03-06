package com.daimens.algorithm.geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * @author DemonSong
 *
 */
public class ConvexHull {

	public List<Point> outerTrees(Point[] points) {
		int n = points.length;
		long[][] co = new long[n][];
		for (int i = 0; i < n; i++) {
			co[i] = new long[] { points[i].x, points[i].y };
		}
		long[][] hull = convexHull(co);
		List<Point> ans = new ArrayList<>();
		outer: for (int i = 0; i < hull.length; i++) {
			long[] h = hull[i];
			for (int j = 0; j < i; j++) {
				if (Arrays.equals(h, hull[j]))
					continue outer;
			}
			ans.add(new Point((int) h[0], (int) h[1]));
		}
		return ans;
	}

	public long[][] convexHull(long[][] co) {
		int n = co.length;
		if (n <= 1) return co;
		Arrays.sort(co, new Comparator<long[]>() {
			public int compare(long[] a, long[] b) {
				if (a[0] != b[0])
					return Long.compare(a[0], b[0]);
				return Long.compare(a[1], b[1]);
			}
		});

		int[] inds = new int[n + 2];
		int p = 0;
		for (int i = 0; i < n; i++) {
			if (p >= 1 && co[inds[p - 1]][0] == co[i][0] && co[inds[p - 1]][1] == co[i][1])
				continue;
			while (p >= 2 && ccw(co[inds[p - 2]], co[inds[p - 1]], co[i]) > 0)
				p--; // if you need point on line
			inds[p++] = i;
		}

		int inf = p + 1;
		for (int i = n - 2; i >= 0; i--) {
			if (co[inds[p - 1]][0] == co[i][0] && co[inds[p - 1]][1] == co[i][1])
				continue;
			while (p >= inf && ccw(co[inds[p - 2]], co[inds[p - 1]], co[i]) > 0)
				p--; // if you need point on line
			inds[p++] = i;
		}

		int len = Math.max(p - 1, 1);
		long[][] ret = new long[len][];
		for (int i = 0; i < len; i++)
			ret[i] = co[inds[i]];
		return ret;
	}

	public int ccw(long ax, long ay, long bx, long by, long tx, long ty) {
		return Long.signum((tx - ax) * (by - ay) - (bx - ax) * (ty - ay));
	}

	public int ccw(long[] a, long[] b, long[] t) {
		return Long.signum((t[0] - a[0]) * (b[1] - a[1]) - (b[0] - a[0]) * (t[1] - a[1]));
	}

	public int ccw(int[] a, int[] b, int[] t) {
		return Long.signum((long) (t[0] - a[0]) * (b[1] - a[1]) - (long) (b[0] - a[0]) * (t[1] - a[1]));
	}
	
	public static void main(String[] args) {
		Point[] points = new Point[8];
		points[0] = new Point(3,7);
		points[1] = new Point(6,8);
		points[2] = new Point(7,8);
		points[3] = new Point(11,10);
		points[4] = new Point(4,3);
		points[5] = new Point(8,5);
		points[6] = new Point(7,13);
		points[7] = new Point(4,13);
		ConvexHull hull = new ConvexHull();
		hull.outerTrees(points);
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
		return "[" + x + "," + y + "]";
	}

}
