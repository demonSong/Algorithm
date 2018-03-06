package com.daimens.algorithm.september;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SolutionDay29_P2187 {
	
	String INPUT = "./data/judge/201709/P2187.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay29_P2187().run();
	}
	
	static final int MAX_N = 50000;
	
	class Point implements Comparable<Point>{
		int x;
		int y;
		
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		Point sub(Point a) {
			return new Point(x - a.x, y - a.y);
		}
		
		int det(Point a) {
			return x * a.y - y * a.x;
		}

		@Override
		public int compareTo(Point o) {
			return this.x != o.x ? this.x - o.x : this.y - o.y;
		}
	}
	
	int N;
	Point[] ps;
	
	List<Point> convexHull(){
		Arrays.sort(ps);
		
		List<Point> ans = new ArrayList<Point>();
		
		int[] stack = new int[2 * N];
		int tot = 0;
		for (int i = 0; i < N; ++i) {
			while (tot > 1 && ps[stack[tot - 1]].sub(ps[stack[tot - 2]]).det(ps[i].sub(ps[stack[tot - 1]])) <= 0) tot--;
			stack[tot++] = i;
		}
		
		for (int i = N - 2, t = tot; i >= 0; --i) {
			while (tot > t && ps[stack[tot - 1]].sub(ps[stack[tot - 2]]).det(ps[i].sub(ps[stack[tot - 1]])) <= 0) tot--;
			stack[tot++] = i;
		}
		tot --;
		
		for (int i = 0; i < tot; ++i) {
			ans.add(ps[stack[i]]);
		}
		
		return ans;
	}
	
	int dist(Point a, Point b) {
		int dx = a.x - b.x;
		int dy = a.y - b.y;
		return dx * dx + dy * dy;
	}
	
	void solve() {
		
		List<Point> qs = convexHull();
		int max = 0;
		for (int i = 0; i < qs.size(); ++i) {
			for (int j = i + 1; j < qs.size(); ++j) {
				max = Math.max(max, dist(qs.get(i), qs.get(j)));
			}
		}
		out.println(max);
	}
	
	void read() {
		 N = ni();
		 ps = new Point[N];
		 for (int i = 0; i < N; ++i) {
			 ps[i] = new Point(ni(), ni());
		 }
		 solve();
	}

	FastScanner in;
	PrintWriter out;
	
	void run() throws IOException {
		boolean oj;
		try {
			oj = ! System.getProperty("user.dir").equals("F:\\java_workspace\\leetcode");
		} catch (Exception e) {
			oj = System.getProperty("ONLINE_JUDGE") != null;
		}
		
		InputStream is = oj ? System.in : new FileInputStream(new File(INPUT));
		in = new FastScanner(is);
		out = new PrintWriter(System.out);
		long s = System.currentTimeMillis();
		read();
		out.flush();
		if (!oj){
			System.out.println("[" + (System.currentTimeMillis() - s) + "ms]");
		}
	}
	
	public boolean more(){
		return in.hasNext();
	}
	
	public int ni(){
		return in.nextInt();
	}
	
	public long nl(){
		return in.nextLong();
	}
	
	public double nd(){
		return in.nextDouble();
	}
	
	public String ns(){
		return in.nextString();
	}
	
	public char nc(){
		return in.nextChar();
	}
	
	class FastScanner {
		BufferedReader br;
		StringTokenizer st;
		boolean hasNext;

		public FastScanner(InputStream is) throws IOException {
			br = new BufferedReader(new InputStreamReader(is));
			hasNext = true;
		}

		public String nextToken() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (Exception e) {
					hasNext = false;
					return "##";
				}
			}
			return st.nextToken();
		}
		
		String next = null;
		public boolean hasNext(){
			next = nextToken();
			return hasNext;
		}

		public int nextInt() {
			if (next == null){
				hasNext();
			}
			String more = next;
			next = null;
			return Integer.parseInt(more);
		}

		public long nextLong() {
			if (next == null){
				hasNext();
			}
			String more = next;
			next = null;
			return Long.parseLong(more);
		}

		public double nextDouble() {
			if (next == null){
				hasNext();
			}
			String more = next;
			next = null;
			return Double.parseDouble(more);
		}
		
		public String nextString(){
			if (next == null){
				hasNext();
			}
			String more = next;
			next = null;
			return more;
		}
		
		public char nextChar(){
			if (next == null){
				hasNext();
			}
			String more = next;
			next = null;
			return more.charAt(0);
		}
	}
}

