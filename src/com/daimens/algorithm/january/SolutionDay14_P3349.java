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
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class SolutionDay14_P3349 {
	
	String INPUT = "./data/judge/201801/P3349.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay14_P3349().run();
	}
	
	List<Integer>[] set;
	int[][] nums;
	int mlen = 100007;
	
	void hash(int i, int[] num) {
		long sum = 0;
		for (int j = 0; j < 6; ++j) {
			sum += (long)num[j] * (long)num[j];
			sum %= mlen;
		}
		int key = (int)sum;
		set[key].add(i);
	}
	
	boolean judge(int i, int j) {
		int[] num1 = nums[i];
		int[] num2 = nums[j];
		StringBuilder sb = new StringBuilder();
		for (int x = 0; x < 6; ++x) sb.append(num1[x]);
		String cmp = sb.toString();
		for (int x = 0; x < 6; ++x) {
			int y = x;
			int z = x;
			StringBuilder lf = new StringBuilder();
			StringBuilder rt = new StringBuilder();
			do {
				lf.append(num2[y]);
				rt.append(num2[z]);
				y = (y + 1) % 6;
				z = (z - 1 + 6) % 6;
			}
			while(y != x);
			if (cmp.equals(lf.toString()) || cmp.equals(rt.toString())) return true;
		}
		return false;
	}
	
	void read() {
		int n = ni();
		set = new ArrayList[mlen];
		for (int i = 0; i < mlen; ++i) set[i] = new ArrayList<Integer>();
		
		nums = new int[n][6];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < 6; ++j) {
				nums[i][j] = ni();
			}
			hash(i, nums[i]);
		}
		for (int i = 0; i < mlen; ++i) {
			if (set[i].size() > 1) {
				int len = set[i].size();
				for (int x = 0; x < len - 1; ++x) {
					for (int y = x + 1; y < len; ++y) {
						if (judge(set[i].get(x), set[i].get(y))) {
							out.println("Twin snowflakes found.");
							return;
						}
					}
				}
			}
		}
		out.println("No two snowflakes are alike.");
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

