package com.daimens.algorithm.august;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class SolutionDay10_P1990 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/201708/P1990.txt";
	
	class Cow implements Comparable<Cow>{
		int pos;
		int volume;
		public Cow(int volume, int pos){
			this.pos = pos;
			this.volume = volume;
		}
		
		@Override
		public int compareTo(Cow that) {
			return this.volume - that.volume;
		}
	}
	
	static final int MAX_N = 20000 + 16;
	Cow[] cows;
	void solve() {
		int n = ni();
		init(MAX_N);
		cows = new Cow[n];
		for (int i = 0; i < n; ++i){
			cows[i] = new Cow(ni(), ni());
		}
		
		Arrays.sort(cows);
		long ans = 0;
		for (int i = 0; i < n; ++i){
			int v = cows[i].volume;
			int x = cows[i].pos;
			long left = sum(count, 1, x - 1);
			long right = sum(count, x + 1, MAX_N);
			ans += v * (left * x - sum (distance, 1, x - 1) + sum (distance, x + 1, MAX_N) - right * x);
			addCount(x, 1);
			addDistance(x, x);
		}
		out.println(ans);
	}
	
	/***********************Binary index tree********************************/
	long[] count;
	long[] distance;
	int N;
	public void init(int n){
		N = n;
		count = new long[n + 1];
		distance = new long[n + 1];
	}
	
	public void addCount(int i, int val){
		while (i <= N){
			count[i] += val;
			i += i & -i;
		}
	}
	
	public void addDistance(int i, int val){
		while (i <= N){
			distance[i] += val;
			i += i & -i;
		}
	}
	
	public long sum(long[] BIT, int i){ // [1, i]
		long sum = 0;
		while (i > 0){
			sum += BIT[i];
			i -= i & -i;
		}
		return sum;
	}
	
	public long sum(long[] BIT, int l, int r){ //[l, r]
		return sum(BIT, r) - sum(BIT, l - 1);
	}
	
	void run() throws Exception {
		is = oj ? System.in : new FileInputStream(new File(INPUT));
		out = new PrintWriter(System.out);

		long s = System.currentTimeMillis();
		solve();
		out.flush();
		tr(System.currentTimeMillis() - s + "ms");
	}

	public static void main(String[] args) throws Exception {
		new SolutionDay10_P1990().run();
	}

	private byte[] inbuf = new byte[1024];
	public int lenbuf = 0, ptrbuf = 0;

	private int readByte() {
		if (lenbuf == -1)
			throw new InputMismatchException();
		if (ptrbuf >= lenbuf) {
			ptrbuf = 0;
			try {
				lenbuf = is.read(inbuf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (lenbuf <= 0)
				return -1;
		}
		return inbuf[ptrbuf++];
	}

	private boolean isSpaceChar(int c) {
		return !(c >= 33 && c <= 126);
	}

	private int skip() {
		int b;
		while ((b = readByte()) != -1 && isSpaceChar(b))
			;
		return b;
	}

	private double nd() {
		return Double.parseDouble(ns());
	}

	private char nc() {
		return (char) skip();
	}

	private String ns() {
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b != '
									// ')
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}

	private char[] ns(int n) {
		char[] buf = new char[n];
		int b = skip(), p = 0;
		while (p < n && !(isSpaceChar(b))) {
			buf[p++] = (char) b;
			b = readByte();
		}
		return n == p ? buf : Arrays.copyOf(buf, p);
	}

	private char[][] nm(int n, int m) {
		char[][] map = new char[n][];
		for (int i = 0; i < n; i++)
			map[i] = ns(m);
		return map;
	}

	private int[] na(int n) {
		int[] a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = ni();
		return a;
	}

	private int ni() {
		int num = 0, b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
		if (b == '-') {
			minus = true;
			b = readByte();
		}

		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = readByte();
		}
	}

	private long nl() {
		long num = 0;
		int b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
		if (b == '-') {
			minus = true;
			b = readByte();
		}

		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = readByte();
		}
	}

	private boolean oj = System.getProperty("ONLINE_JUDGE") != null;

	private void tr(Object... o) {
		if (!oj)
			System.out.println(Arrays.deepToString(o));
	}
}


