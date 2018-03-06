package com.daimens.algorithm.september;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public class SolutionDay15_C025E {
	
	String INPUT = "./data/judge/201709/C025E.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay15_C025E().run();
	}
	
	static final int[][] CIR = {{0, 1, 2},{0, 2, 1},{1, 0, 2},{1, 2, 0},{2, 0, 1},{2, 1, 0}};
	static final int INF = 0x3f3f3f3f;
	
	class Hash{
		final long BL;
		final long BR;
		final long ML;
		final long MR;
		final long[] psl;
		final long[] psr;
		
		Hash(int n){
			Random r = new Random(System.nanoTime());
			BL = (long) (1e9 + r.nextInt((int)(1e9)));
			BR = (long) (1e9 + r.nextInt((int)(1e9)));
			ML = (long) (1e9 + r.nextInt((int)(1e9)));
			MR = (long) (1e9 + r.nextInt((int)(1e9)));
			psl = new long[n + 1];
			psr = new long[n + 1];
			for (int i = 0; i <= n; ++i) psl[i] = (i == 0 ? 1 : psl[i - 1] * BL) % ML;
			for (int i = 0; i <= n; ++i) psr[i] = (i == 0 ? 1 : psr[i - 1] * BR) % MR;
		}
		
		long[] build(char[] cs) {
			int n = cs.length;
			long[] hs = new long[n + 1];
			long l = 0;
			long r = 0;
			for (int i = 0; i < n; ++i) {
				l = (l * BL + cs[i]) % ML;
				r = (r * BR + cs[i]) % MR;
				if (l < 0) l += ML;
				if (r < 0) r += MR;
				hs[i + 1] = l << 32 | r;
			}
			return hs;
		}
		
		long get(long[] hs, int b, int e) {
			long el = hs[e] >>> 32;
			long er = hs[e] & 0xffffffffL;
			long bl = hs[b] >>> 32;
			long br = hs[b] & 0xffffffffL;
			long l = el - bl * psl[e - b] % ML;
			long r = er - br * psr[e - b] % MR;
			if (l < 0) l += ML;
			if (r < 0) r += MR;
			return l << 32 | r;
		}
	}
	
	int overlap(long[] ah, long[] bh, int al, int bl, Hash h) {
		int l = -1;
		for (int i = 0; i <= Math.min(al, bl); ++i) {
			if (h.get(ah, al - i, al) == h.get(bh, 0, i)) l = i;
		}
		return l == -1 ? 0 : l;
	}
	
	// al > bl
	boolean contains(long[] ah, long[] bh, int al, int bl, Hash h) {
		if (al < bl) return false;
		long key = h.get(bh, 0, bl);
		for (int i = bl; i <= al; ++i) {
			if (key == h.get(ah, i - bl, i)) return true;
		}
		return false;
	}
	
	int solveContains(long[] ah, long[] bh, long ch[], int al, int bl, int cl, Hash hash) {
		if (contains(ah, bh, al, bl, hash) && contains(bh, ch, bl, cl, hash)) {
			return al;
		}
		else if (contains(ah, bh, al, bl, hash)) {
			return Math.min(al + cl -  overlap(ah, ch, al, cl, hash),
					al + cl - overlap(ch, ah, cl, al, hash));
		}
		return INF;
	}
	
	class Pair{
		int len;
		long[] hs;
		Pair(int len, long[] hs){
			this.len = len;
			this.hs  = hs;
		}
	}
	
	
	int solve(String s1, String s2, String s3) {
		int al = s1.length();
		int bl = s2.length();
		int cl = s3.length();
		Hash hash = new Hash(Math.max(al, Math.max(bl, cl)));
		long[] ah = hash.build(s1.toCharArray());
		long[] bh = hash.build(s2.toCharArray());
		long[] ch = hash.build(s3.toCharArray());
		
		Pair[] p = new Pair[3];
		p[0] = new Pair(al, ah);
		p[1] = new Pair(bl, bh);
		p[2] = new Pair(cl, ch);
		
		int min = INF;
		for (int i = 0; i < CIR.length; ++i) {
			min = Math.min(min, solveContains(p[CIR[i][0]].hs, p[CIR[i][1]].hs, p[CIR[i][2]].hs, 
					p[CIR[i][0]].len, p[CIR[i][1]].len, p[CIR[i][2]].len, hash));
		}
		
		for (int i = 0; i < CIR.length; ++i) {
			int ans = al + bl + cl;
			ans -= overlap(p[CIR[i][0]].hs, p[CIR[i][1]].hs, p[CIR[i][0]].len, p[CIR[i][1]].len, hash);
			ans -= overlap(p[CIR[i][1]].hs, p[CIR[i][2]].hs, p[CIR[i][1]].len, p[CIR[i][2]].len, hash);
			min = Math.min(min, ans);
		}
		
		return min;
	}
	
	void read() {
		String s1 = ns();
		String s2 = ns();
		String s3 = ns();
		out.println(solve(s1, s2, s3));
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

