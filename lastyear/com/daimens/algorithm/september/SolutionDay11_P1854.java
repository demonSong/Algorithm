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

public class SolutionDay11_P1854 {
	
	String INPUT = "./data/judge/201709/P1854.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay11_P1854().run();
	}
	
	void solve() {
		int T = ni();
		while (T --> 0) {
			String s = ns();
			char[] cs = s.toCharArray();
			int n = cs.length;
			int[] map = new int[32];
			for (int i = 0; i < n; ++i) {
				map[cs[i] - 'a']++;
			}
			
			int cnt = 0;
			for (int i = 0; i < 32; ++i) {
				if ((map[i] & 1) != 0) {
					cnt++;
				}
			}
			
			if (cnt > 1) out.println("Impossible");
			else {
				out.println(divide(cs, 0, n - 1));
			}
		}
	}

	public int divide(char[] cs, int i, int j) {
		if (i >= j) return 0;
		char a = cs[i];
		char b = cs[j];
		
		if (a == b) {
			return divide(cs, i + 1, j - 1);
		}
		int k = i + 1;
		while (k < j && cs[k] != b) k++;
		int op1 = k - i;
		
		k = j - 1;
		while (k > i && cs[k] != a) k--;
		int op2 = j - k;
		
		if (op1 < op2) {
			int tt = op1 + i;
			while (tt != i) {
				swap(cs, tt, tt - 1);
				tt--;
			}
			return op1 + divide(cs, i + 1, j - 1);
		}
		else {
			int tt = j - op2;
			while (tt != j) {
				swap(cs, tt, tt + 1);
				tt++;
			}
			return op2 + divide(cs, i + 1, j - 1);
		}
	}
	
	public void swap(char[] cs, int i, int j) {
		char tmp = cs[i];
		cs[i] = cs[j];
		cs[j] = tmp;
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

