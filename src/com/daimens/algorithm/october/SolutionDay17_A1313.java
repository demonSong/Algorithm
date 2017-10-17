package com.daimens.algorithm.october;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class SolutionDay17_A1313 {
	
	String INPUT = "./data/judge/201710/A1313.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay17_A1313().run();
	}

	static final int INF = 0x3f3f3f3f;
	int N, M;

	double width(int[] X, int[] Y, int n, double x) {
		double lb = INF, ub = -INF;
		for (int i = 0; i < n; ++i) {
			double x1 = X[i], y1 = Y[i], x2 = X[(i + 1) % n], y2 = Y[(i + 1) % n];
			if ((x1 - x) * (x2 - x) <= 0 && x1 != x2) {
				double y = y1 + (y2 - y1) * (x - x1) / (x2 - x1);
				lb = Math.min(lb, y);
				ub = Math.max(ub, y);
			}
		}
		return Math.max(0, ub - lb);
	}
	
	int min(int[] nums, int start, int end) {
		int min = INF;
		for (int i = start; i < end; ++i) {
			min = Math.min(min, nums[i]);
		}
		return min;
	}
	
	int max(int[] nums, int start, int end) {
		int max = -INF;
		for (int i = start; i < end; ++i) {
			max = Math.max(max, nums[i]);
		}
		return max;
	}
	
	void solve(int[] X1, int[] Y1, int[] X2, int[] Y2) {
		int min1 = min(X1, 0, N), max1 = max(X1, 0, N);
		int min2 = min(X2, 0, M), max2 = max(X2, 0, M);
		
		List<Integer> xs = new ArrayList<>();
		for (int i = 0; i < N; ++i) xs.add(X1[i]);
		for (int i = 0; i < M; ++i) xs.add(X2[i]);
		
		Collections.sort(xs);
		
		double res = 0.0;
		for (int i = 0; i + 1 < xs.size(); ++i) {
			double a = xs.get(i), b = xs.get(i + 1), c = (a + b) / 2;
			if (min1 <= c && c <= max1 && min2 <= c && c <= max2) {
				double fa = width(X1, Y1, N, a) * width(X2, Y2, M, a);
				double fb = width(X1, Y1, N, b) * width(X2, Y2, M, b);
				double fc = width(X1, Y1, N, c) * width(X2, Y2, M, c);
				res += (b - a) / 6 * (fa + 4 * fc + fb);
			}
		}
		
		out.printf("%.10f\n", res);
	}
	
	void read() {
		while (true) {
			N = ni();
			M = ni();
			
			if (N + M == 0) break;
			
			int[] X1 = new int[N];
			int[] Y1 = new int[N];
			
			for (int i = 0; i < N; ++i) {
				X1[i] = ni();
				Y1[i] = ni();
			}
			
			int[] X2 = new int[M];
			int[] Y2 = new int[M];
			for (int i = 0; i < M; ++i) {
				X2[i] = ni();
				Y2[i] = ni();
			}
			
			solve(X1, Y1, X2, Y2);
		}
	}

	FastScanner in;
	PrintWriter out;
	
	void run() throws IOException {
		boolean oj;
		try {
			oj = ! System.getProperty("user.dir").equals("F:\\oxygen_workspace\\Algorithm");
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

