package com.daimens.algorithm.october;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class SolutionDay12_C0002 {
	
	String INPUT = "./data/judge/201710/C0002.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay12_C0002().run();
	}
	
//	void read() {
//		int n = ni();
//		String s = ns();
//		char[] cs = s.toCharArray();
//		int max = 0;
//		
//		int[][] dp = new int[n][n];  // 区间内0的个数
//		for (int i = 0; i < n; ++i) {
//			dp[i][i] = cs[i] == '0' ? 1 : 0;
//		}
//		
//		for (int i = 0; i < n; ++i) {
//			for (int j = i - 1; j >= 0; --j) {
//				if ((i - j + 1 & 1) != 0) {
//					dp[j][i] = dp[j + 1][i - 1];
//					if (cs[i] == '0') dp[j][i] += 1;
//					if (cs[j] == '0') dp[j][i] += 1;
//				}
//				else {
//					if (i - j + 1 == 2) {
//						if (cs[i] == '0') dp[j][i] += 1;
//						if (cs[j] == '0') dp[j][i] += 1;
//						if (dp[j][i] == (i - j + 1) / 2) max = Math.max(max, i - j + 1);
//					}
//					else {
//						dp[j][i] = dp[j + 1][i - 1];
//						if (cs[i] == '0') dp[j][i] += 1;
//						if (cs[j] == '0') dp[j][i] += 1;
//						if (dp[j][i] == (i - j + 1) / 2) max = Math.max(max, i - j + 1);
//					}
//				}
//			}
//		}
//		out.println(max);
//	}
	
//	void read() {
//		int n = ni();
//		String s = ns();
//		char[] cs = s.toCharArray();
//		int max = 0;
//		
//		int[] dp = new int[n];
//		for (int i =0; i < n; ++i) {
//			dp[i] = cs[i] == '1' ? 1 : 0;
//		}
//		
//		int[] sum = new int[n + 1];
//		for (int i = 0; i < n; ++i) {
//			sum[i + 1] = sum[i] + dp[i];
//		}
//		
//		for (int i = 2; i <= n; ++i) {
//			for (int j = i - 2; j >= 0; j -=2) {
//				int cc = sum[i] - sum[j];
//				if (cc == (i - j) / 2) max = Math.max(max, i - j);
//			}
//		}
//		out.println(max);
//	}
	
	void solve(char[] cs, int i, int j) {
		
	}
	
	void read() {
		int n = ni();
		String s = ns();
		char[] cs = s.toCharArray();
		int max = 0;
		
		int[] num = new int[n];
		for (int i = 0; i < n; ++i) {
			num[i] = cs[i] == '0' ? -1 : 1;
		}
		
		int[] sum = new int[n + 1];
		for (int i = 0; i < n; ++i) {
			sum[i + 1] = sum[i] + num[i];
		}
		
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i <= n; ++i) {
			if (!map.containsKey(sum[i])) {
				map.put(sum[i], i);
			}
			else {
				int j = map.get(sum[i]);
				max = Math.max(max, i - j);
			}
		}
		out.println(max);
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

