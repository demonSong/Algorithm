package com.daimens.algorithm.august;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class SolutionDay24_P1201 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/201708/P1201.txt";
	
	class Range implements Comparable<Range>{
		int l;
		int r;
		int c;
		
		public Range(int l, int r, int c){
			this.l = l;
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Range that) {
			return this.r - that.r;
		}
		
		@Override
		public String toString() {
			return l + " " + r + " " + c;
		}
	}
	
	void solve() {
		int n = ni();
		init();
		Range[] intervals = new Range[n];
		for (int i = 0; i < n; ++i){
			intervals[i] = new Range(ni(), ni(), ni());
		}
		
		Arrays.sort(intervals);
		boolean[] visited = new boolean[MAX_N];
		int res = 0;
		for (int i = 0; i < n; ++i){
			Range now = intervals[i];
			int picked = sum(now.l, now.r);
			if (picked == 0){
				res += now.c;
				for (int j = 0; j < now.c; ++j){
					add(now.r - j, 1);
					visited[now.r - j] = true;
				}
			}
			else{
				int choose = now.c - picked;
				if (choose <= 0) continue;
				res += choose;
				int pos = now.r;
				while (choose > 0){
					if (visited[pos]){
						pos --;
					}
					else{
						add(pos, 1);
						visited[pos] = true;
						pos --;
						choose --;
					}
				}
			}
		}
		
		out.println(res);
		
	}
	
	
	/*********************BIT************************/
	int MAX_N = 2 * (50000 + 16);
	int[] BIT;
	
	public void init(){
		BIT = new int[MAX_N];
	}
	
	public void add(int i, int val){
		while (i <= MAX_N){
			BIT[i] += val;
			i += i & -i;
		}
	}
	
	public int sum(int i){
		int res = 0;
		while (i > 0){
			res += BIT[i];
			i -= i & -i;
		}
		return res;
	}
	
	//区间 [l, r]
	public int sum(int l, int r){
		return sum(r) - sum(l - 1);
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
		new SolutionDay24_P1201().run();
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



