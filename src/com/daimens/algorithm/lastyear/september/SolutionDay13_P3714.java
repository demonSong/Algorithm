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

public class SolutionDay13_P3714 {
	
	String INPUT = "./data/judge/201709/P3714.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay13_P3714().run();
	}
	
	static final int MAX_N = 100000 + 16;
	static final int INF   = 0x3f3f3f3f;
	
	class Pair implements Comparable<Pair>{
		double x;
		double y;
		boolean man;
		
		Pair(double x, double y, boolean man){
			this.x = x;
			this.y = y;
			this.man = man;
		}
		
		@Override
		public String toString() {
			return x + " " + y;
		}
		
		@Override
		public int compareTo(Pair o) {
			return x > o.x ? -1 : 1;
		}
	}
	
	Pair[] tmp = new Pair[2 * MAX_N];
	
	double solve(Pair[] p, int l, int r) {
		if (r - l <= 1) return INF;
		int m = (l + r) / 2;
		double x = p[m].x;
		double d = Math.min(solve(p, l, m), solve(p, m, r));
		// merge
		for (int i = l, j = m, k = l; k < r;) {
			if (j >= r || i < m && p[i].y < p[j].y) tmp[k++] = p[i++];
			else tmp[k++] = p[j++];
		}
		
		for (int i = l; i < r; ++i) p[i] = tmp[i];
		
		List<Pair> b = new ArrayList<Pair>();
		for (int i = l; i < r; ++i) {
			if (Math.abs(p[i].x - x) >= d) continue;
			for (int j = b.size() - 1; j >= 0; --j) {
				double dx = p[i].x - b.get(j).x;
				double dy = p[i].y - b.get(j).y;
				if (p[i].man ^ b.get(j).man) {
					if (Math.abs(dy) >= d) break;
					d = Math.min(d, Math.sqrt(dx * dx + dy * dy));
				}
			}
			b.add(p[i]);
		}
		return d;
	}
	
	void read() {
		int T = ni();
		while (T --> 0) {
			int N = ni();
			Pair[] points = new Pair[2 * N];
			for (int i = 0; i < N; ++i) {
				points[i] = new Pair(nd(), nd(), false);
			}
			for (int i = 0; i < N; ++i) {
				points[i + N] = new Pair(nd(), nd(), true);
			}
			
			Arrays.sort(points);
			out.printf("%.3f\n", solve(points, 0, 2 * N));
		}
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
	
	static class ArrayUtils {

		public static void fill(int[][] f, int value) {
			for (int i = 0; i < f.length; ++i) {
				Arrays.fill(f[i], value);
			}
		}
		
		public static void fill(int[][][] f, int value) {
			for (int i = 0; i < f.length; ++i) {
				fill(f[i], value);
			}
		}
		
		public static void fill(int[][][][] f, int value) {
			for (int i = 0; i < f.length; ++i) {
				fill(f[i], value);
			}
		}
	}
}

