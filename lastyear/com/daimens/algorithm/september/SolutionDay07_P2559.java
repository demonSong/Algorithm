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

public class SolutionDay07_P2559 {
	
	String INPUT = "./data/judge/201709/P2559.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay07_P2559().run();
	}
	
	static final int MAX_N = 100000 + 16;
	
	long[] arra;
	int n;
	int[] stack;
	void solve() {
		while (true) {
			n = ni();
			if (n == 0) break;
			arra = new long[n];
			for (int i = 0; i < n; ++i) {
				arra[i] = nl();
			}
			out.println(area());
		}
	}
	
//	long area() {
//		long sum = 0;
//		Stack<Integer> stack = new Stack<Integer>();
//		for (int i = 0; i < n + 1; ++i) {
//			long now = (i == n) ? 0 : arra[i];
//			int cnt = 0;
//			while (!stack.isEmpty() && now < arra[stack.peek()]) {
//				int last = stack.pop();
//				sum = Math.max(sum, arra[last] * (i - last));
//				arra[last] = now;
//				cnt ++;
//			}
//			i -= cnt;
//			stack.push(i);
//		}
//		
//		return sum;
//	}
	
	int[] L;
	int[] R;
	long area() {
		long sum = 0;
		stack = new int[MAX_N];
		L     = new int[MAX_N];
		R     = new int[MAX_N];
		
		int t = 0;
		for (int i = 0; i < n; ++i) {
			long h = arra[i];
			while (t > 0 && h <= arra[stack[t - 1]]) {
				t--;
			}
			if (t == 0) L[i] = 0;
			else L[i] = stack[t - 1] + 1;
			stack[t++] = i;
		}
		
		t = 0;
		for (int i = n - 1; i >= 0; --i) {
			long h = arra[i];
			while (t > 0 && arra[stack[t - 1]] >= h) t--;
			if (t == 0) R[i] = n;
			else R[i] = stack[t - 1];
			stack[t++] = i;
		}
		
		for (int i = 0; i < n; ++i) {
			sum = Math.max(sum, arra[i] * (R[i] - L[i]));
		}
		
		return sum;
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

