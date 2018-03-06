package com.daimens.algorithm.september;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SolutionDay07_P2823 {
	
	String INPUT = "./data/judge/201709/P2823.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay07_P2823().run();
	}
	
	static final int MAX_N = 1000000 + 16;
	
	int N, K;
	int[] nums = new int[MAX_N];
	int[] deq  = new int[MAX_N];
	void solve() {
		N = ni();
		K = ni();
		for (int i = 0; i < N; ++i) nums[i] = ni();
		int[] val = new int[MAX_N];
		int s = 0, t = 0;
		for (int i = 0; i < N; ++i) {
			while ((t - s) > 0 && nums[i] <= nums[deq[t - 1]]) {
				t--;
			}
			deq[t++] = i;
			
			if (i - K + 1 >= 0) {
				val[i - K + 1] = nums[deq[s]];
				if (deq[s] == i - K + 1) {
					s++;
				}
			}
		}
		
		for (int i = 0; i < N - K + 1; ++i) {
			out.print(val[i] + (i + 1 == N - K + 1 ? "\n" : " "));
		}
		
		s = 0;
		t = 0;
		for (int i = 0; i < N; ++i) {
			while (t > s && nums[i] >= nums[deq[t - 1]]) {
				t--;
			}
			deq[t++] = i;
			
			if (i - K + 1 >= 0) {
				val[i - K + 1] = nums[deq[s]];
				if (deq[s] == i - K + 1) {
					s++;
				}
			}
		}
		
		for (int i = 0; i < N - K + 1; ++i) {
			out.print(val[i] + (i + 1 == N - K + 1 ? "\n" : " "));
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
		solve();
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

//void solve() {
//	N = ni();
//	K = ni();
//	nums = new int[N];
//	for (int i = 0; i < N; ++i) nums[i] = ni();
//	StringBuilder sb = new StringBuilder();
//	int MOD = K + 2;
//	deq = new int[MOD];
//	int s = 0, t = 0;
//	for (int i = 0; i < N; ++i) {
//		while ((t - s + MOD) % MOD > 0 && nums[i] <= nums[deq[(t - 1 + MOD) % MOD]]) {
//			t--;
//			t = (t + MOD) % MOD;
//		}
//		deq[t] = i;
//		t++;
//		t %= MOD;
//		
//		if (i - K + 1 >= 0) {
//			sb.append(" " + nums[deq[s % MOD]]);
//			if (deq[s % MOD] == i - K + 1) {
//				s++;
//				s %= MOD;
//			}
//		}
//	}
//	
//	sb.deleteCharAt(0);
//	sb.append("\n");
//	
//	s = 0;
//	t = 0;
//	deq = new int[MOD];
//	for (int i = 0; i < N; ++i) {
//		while ((t - s + MOD) % MOD > 0 && nums[i] >= nums[deq[(t - 1 + MOD) % MOD]]) {
//			t--;
//			t = (t + MOD) % MOD;
//		}
//		deq[t] = i;
//		t++;
//		t %= MOD;
//		
//		if (i - K + 1 >= 0) {
//			sb.append(nums[deq[s % MOD]] + " ");
//			if (deq[s % MOD] == i - K + 1) {
//				s++;
//				s %= MOD;
//			}
//		}
//	}
//	
//	sb.deleteCharAt(sb.length() - 1);
//	out.println(sb.toString());
//}

