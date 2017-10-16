package com.daimens.algorithm.october;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;

public class SolutionDay16_P2079 {
	
	String INPUT = "./data/judge/201710/P2079.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay16_P2079().run();
	}
	
	class P implements Comparable<P>{
		int x;
		int y;
		
		P(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		P sub(P a) {
			return new P(x - a.x, y - a.y);
		}
		
		int det(P a) {
			return x * a.y - y * a.x;
		}
		
		@Override
		public int compareTo(P o) {
			return x - o.x != 0 ? x - o.x : y - o.y;
		}
		
	}
	
	int N;
	P[] p;
	
	double area(P a, P b, P c) {
		double ans = (c.x - a.x) * (b.y - a.y) - (c.y - a.y) * (b.x - a.x);
		return 0.5 * Math.abs(ans);
	}
	
	P[] convexHull() {
		Arrays.sort(p);
		P[] qs = new P[2 * N];
		int k = 0;
		for (int i = 0; i < N; ++i) {
			while (k > 1 && qs[k - 1].sub(qs[k - 2]).det(p[i].sub(qs[k - 2])) < 0) k --;
			qs[k++] = p[i];
		}
		
		for (int i = N - 2, t = k; i >= 0; --i) {
			while (k > t && qs[k - 1].sub(qs[k - 2]).det(p[i].sub(qs[k - 2])) < 0) k--;
			qs[k++] = p[i];
		}
		
		P[] res = new P[k - 1];
		System.arraycopy(qs, 0, res, 0, k - 1);
		return res;
	}
	
	void solve() {
		P[] qs = convexHull();
		int n = qs.length;
		double ans = 0;
		
		for (int offset = 1; offset < (n + 1) / 2; ++offset) {
			int fir = 0;
			int pos = (fir + offset + 1) % n;
			do {
				int sec = (fir + offset) % n;
				while ((pos + 1) % n != fir && area(qs[fir % n], qs[sec % n], qs[(pos + 1) % n]) 
						   >= area(qs[fir % n], qs[sec % n], qs[pos % n])) {
					pos = (pos + 1) % n;
				}
				ans = Math.max(ans, area(qs[fir % n], qs[sec % n], qs[pos % n]));
				fir = (fir + 1) % n;
			}
			while (fir != 0);
		}
		
		out.printf("%.2f\n", ans);
	}
	
	void read() {
		while (true) {
			N = ni();
			if (N == -1) break;
			p = new P[N];
			
			for (int i = 0; i < N; ++i) {
				p[i] = new P(ni(), ni());
			}
			
			solve();
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
	
	static class D{
		
		public static void pp(int[][] board, int row, int col) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < row; ++i) {
				for (int j = 0; j < col; ++j) {
					sb.append(board[i][j] + (j + 1 == col ? "\n" : " "));
				}
			}
			System.out.println(sb.toString());
		}
		
		public static void pp(char[][] board, int row, int col) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < row; ++i) {
				for (int j = 0; j < col; ++j) {
					sb.append(board[i][j] + (j + 1 == col ? "\n" : " "));
				}
			}
			System.out.println(sb.toString());
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
	
	static class Num{
		public static <K> void inc(Map<K, Integer> mem, K k) {
			if (!mem.containsKey(k)) mem.put(k, 0);
			mem.put(k, mem.get(k) + 1);
		}
	}
}

