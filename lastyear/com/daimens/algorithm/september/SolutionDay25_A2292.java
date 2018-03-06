package com.daimens.algorithm.september;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.StringTokenizer;

public class SolutionDay25_A2292 {
	
	String INPUT = "./data/judge/201709/A2292.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay25_A2292().run();
	}
	
	class SuffixArray{
		int k = 1;
		int n;
		Integer[] sa;
		int[] rank, tmp, lcp;
		SuffixArray(char[] cs){
			n = cs.length;
			sa   = new Integer[n + 1];
			rank = new int[n + 1];
			tmp  = new int[n + 1];
			lcp  = new int[n];
			
			for (int i = 0; i <= n; ++i) {
				sa[i] = i;
				rank[i] = i < n ? cs[i] : -1;
			}
			
			for (k = 1; k <= n; k <<= 1) {
				Arrays.sort(sa, cmp);
				tmp[sa[0]] = 0;
				for (int i = 1; i <= n; ++i) {
					tmp[sa[i]] = tmp[sa[i - 1]] + (cmp.compare(sa[i - 1], sa[i - 1]) < 0 ? 1 : 0);
				}
				for (int i = 0; i <= n; ++i) {
					rank[i] = tmp[i];
				}
			}
			
			// lcp
			int h = 0;
			for (int i = 0; i <= n; ++i) rank[sa[i]] = i;
			for (int i = 0; i < n; ++i) {
				int j = sa[rank[i] - 1];
				if (h > 0) --h;
				for (;j + h < n && i + h < n; ++h) {
					if (cs[j + h] != cs[i + h]) break;
				}
				lcp[rank[i] - 1] = h;
			}
		}
		
		Comparator<Integer> cmp = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				int i = o1;
				int j = o2;
				if (rank[i] != rank[j]) return rank[i] - rank[j];
				else {
					int ri = i + k <= n ? rank[i + k] : -1;
					int rj = j + k <= n ? rank[j + k] : -1;
					return ri - rj;
				}
			}
		};
	}
	
	class BIT{
		int MAX_N = 80000;
		long[] BIT = new long[MAX_N];
		int n;
		
		BIT() {
			this.n = MAX_N;
			Arrays.fill(BIT, 0);
		}
		
		BIT(int n) {
			this.n = n;
			Arrays.fill(BIT, 0);
		}
		
		public void add(int i, int val) {
			while (i <= n) {
				BIT[i] += val;
				i += i & -i;
			}
		}
		
		public long sum(int i) {
			long s = 0;
			while (i > 0) {
				s += BIT[i];
				i -= i & -i;
			}
			return s;
		}
	}
	
	class Manacher{
		int[] rad;
		int[] RL;
		Manacher(String s){
			String t = preprocess(s);
			char[] ts = t.toCharArray();
			int n = t.length();
			rad = new int[n + 16];
			RL  = new int[n + 16];
			int id  = 0;
			int max = 0;
			for (int i = 1; i < n - 1; ++i) {
				int j = 2 * id - i;
				RL[i] = max < i ? 1 : Math.min(max - i, RL[j]);
				while (ts[i + RL[i]] == ts[i - RL[i]]) RL[i] ++;
				if (max < i + RL[i]) {
					max = i + RL[i];
					id  = i;
				}
			}
			
			for (int i = 0, k = 2; i < s.length(); ++i, ++k) {
				rad[i] = RL[i + k] - 1;
			}
		}
		
		String preprocess(String s) {
			String res = "^";
			for (char c : s.toCharArray()) {
				res += "#" + c;
			}
			res += "#$";
			return res;
		}
		
	}
	
	BIT[] sum, cnt;
	
	void read() {
		String S = ns();
		String T = ns();
		String ST = S + "$" + T;
		SuffixArray sa = new SuffixArray(ST.toCharArray());
		Manacher ma = new Manacher("aabbaa");
		
		long ans = 0;
		sum = new BIT[2];
		cnt = new BIT[2];
		for (int odd = 0; odd < 2; ++odd) {
			for (int i = 0; i < 2; ++i) {
				sum[i] = new BIT();
				cnt[i] = new BIT();
			}
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

