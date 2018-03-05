package com.daimens.algorithm.january;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class SolutionDay19_P2002 {
	
	String INPUT = "./data/judge/201801/P2002.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay19_P2002().run();
	}
	
	static final int MOD = 100007;
	
	class P{
		int x;
		int y;
		P(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	List<P>[] ps;
	
	void hash(int x, int y) {
		int dist = (x * x + y * y) % MOD;
		if (ps[dist] == null) ps[dist] = new ArrayList<P>();
		ps[dist].add(new P(x, y));
	}
	
	boolean search(P a) {
		int dist = (a.x * a.x + a.y * a.y) % MOD;
		if (ps[dist] == null) return false;
		int size = ps[dist].size();
		for (int i = 0; i < size; ++i) {
			P b = ps[dist].get(i);
			if (b.x == a.x && b.y == a.y) return true;
		}
		return false;
	}
	
	
	void read() {
		while(true) {
			int n = ni();
			if (n == 0) break;
			ps = new ArrayList[MOD];
			int[][] points = new int[n][2];
			for (int i = 0; i < n; ++i) {
				points[i] = new int[] {ni(), ni()};
				hash(points[i][0], points[i][1]);
			}
			
			int ans = 0;
			for (int i = 0; i < n; ++i) {
				for (int j = i + 1; j < n; ++j) {
					int x1 = points[i][0];
					int y1 = points[i][1];
					int x2 = points[j][0];
					int y2 = points[j][1];
					
					P pt3 = new P(x1 + y1 - y2, y1 + x2 - x1);
					P pt4 = new P(x2 + y1 - y2, y2 + x2 - x1);
					P pt5 = new P(x1 + y2 - y1, y1 + x1 - x2);
					P pt6 = new P(x2 + y2 - y1, y2 + x1 - x2);
					
					ans += search(pt3) && search(pt4) ? 1 : 0;
					ans += search(pt5) && search(pt6) ? 1 : 0;
				}
			}
			
			out.println(ans / 4);
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

