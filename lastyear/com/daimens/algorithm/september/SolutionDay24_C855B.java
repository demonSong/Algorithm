package com.daimens.algorithm.september;

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

public class SolutionDay24_C855B {
	
	String INPUT = "./data/judge/201709/C855B.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay24_C855B().run();
	}
	
	static final int INF = 0x3f3f3f3f;
	
	class Pair implements Comparable<Pair>{
		int idx;
		int val;
		Pair(int idx, int val){
			this.idx = idx;
			this.val = val;
		}
		@Override
		public int compareTo(Pair o) {
			return this.val - o.val;
		}
	}
	
	void read() {
		int n = ni();
		int p = ni();
		int q = ni();
		int r = ni();
		int[] a = new int[n];
		for (int i = 0; i < n; ++i) a[i] = ni();
		Pair[] ps = new Pair[n];
		for (int i = 0; i < n; ++i) {
			ps[i] = new Pair(i, a[i]);
		}
		Arrays.sort(ps);
		long max = 0;
		if (p >= 0 && q >= 0 && r >= 0) max = (p + q + r) * ps[n - 1].val;
		else if (p < 0 && q < 0 && r < 0) max = (p + q + r) * ps[0].val;
		else if (p > 0 && q > 0 && r < 0) {
			max = (p + q) * ps[n - 1].val;
			int j = ps[n - 1].idx;
			int i = 0;
			while (i < n && ps[i].idx < j) {
				i ++;
			}
			max += r * ps[i].val;
			
			long sum = 0;
			sum = r * ps[0].val;
			j = ps[0].idx;
			i = n - 1;
			while (i >= 0 && ps[i].idx > j) {
				i --;
			}
			sum += (p + q) * ps[i].val;
			max = Math.max(max, sum);
		}
		else if (p < 0 && q < 0 && r > 0) {
			max = -1 * (p + q) * ps[n - 1].val;
			int j = ps[n - 1].idx;
			int i = 0;
			while (i < n && ps[i].idx < j) {
				i ++;
			}
			max += -1 * r * ps[i].val;
			
			long sum = 0;
			sum = -1 * r * ps[0].val;
			j = ps[0].idx;
			i = n - 1;
			while (i >= 0 && ps[i].idx > j) {
				i --;
			}
			sum += -1 * (p + q) * ps[i].val;
			max = Math.max(max, sum);
		}
		else if (p > 0 && q < 0 && r > 0) {
			int[] lf = new int[n];
			int[] rt = new int[n];
			Arrays.fill(lf, -INF);
			Arrays.fill(rt, -INF);
			int ma = -INF;
			for (int i = 0; i < n; ++i) {
				ma = Math.max(ma, a[i]);
				lf[i] = ma;
			}
			ma = -INF;
			for (int i = n - 1; i >= 0; --i) {
				ma = Math.max(ma, a[i]);
				rt[i] = ma;
			}
			for (int i = 0; i < n; ++i) {
				max = Math.max(max, p * lf[i] + q * a[i] + r * rt[i]);
			}
		}
		else {
			int[] lf = new int[n];
			int[] rt = new int[n];
			Arrays.fill(lf, -INF);
			Arrays.fill(rt, -INF);
			int ma = -INF;
			for (int i = 0; i < n; ++i) {
				ma = Math.max(ma, -a[i]);
				lf[i] = ma;
			}
			ma = -INF;
			for (int i = n - 1; i >= 0; --i) {
				ma = Math.max(ma, -a[i]);
				rt[i] = ma;
			}
			for (int i = 0; i < n; ++i) {
				max = Math.max(max, p * lf[i] - q * a[i] + r * rt[i]);
			}
		}
		out.println(max);
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

