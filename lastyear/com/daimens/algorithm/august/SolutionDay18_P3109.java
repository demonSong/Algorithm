package com.daimens.algorithm.august;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.TreeSet;

public class SolutionDay18_P3109 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/201708/P3109.txt";
	
	int[] x;
	int[] y;
	class Pair implements Comparable<Pair>{
		int x;
		int y;
		public Pair(int x, int y){
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Pair that) {
			int thiz = this.y;
			int thaz = that.y;
			return thiz == thaz ? this.x - that.x : thiz - thaz;
		}
	}
	
	Pair[] ps;
	int N;
	void solve() {
		N = ni();
		x = new int[N];
		y = new int[N];
		
		for (int i = 0; i < N; ++i){
			x[i] = ni();
			y[i] = ni();
		}
		
		compress(x);
		compress(y);
		
		ps = new Pair[N];
		for (int i = 0; i < N; ++i){
			ps[i] = new Pair(x[i], y[i]);
		}
		
		Arrays.sort(ps);
		init();
		doSolve();
	}
	
	public int compress(int[] arra){
		TreeSet<Integer> set = new TreeSet<Integer>();
		for (int i : arra) set.add(i);
		Integer[] find = set.toArray(new Integer[0]);
		for (int i = 0; i < arra.length; ++i){
			arra[i] = Arrays.binarySearch(find, arra[i]) + 1;
		}
		return find.length;
	}
	
	int[] l;  //ps[i] 的上方有黑点则为0，否则为1
	int[] r;  //ps[i] 的下方有黑点则为0，否则为1
	static final int MAX_N = 200000 + 16;
	public void init(){
		l = new int[N];
		r = new int[N];
		boolean[] visited = new boolean[MAX_N];
		for (int i = 0; i < N; ++i){
			if (!visited[ps[i].x]){
				l[i] = 1;
				visited[ps[i].x] = true;
			}
			else{
				l[i] = 0;
			}
		}
		
		visited = new boolean[MAX_N];
		for (int i = N - 1; i >= 0; --i){
			if (!visited[ps[i].x]){
				r[i] = 1;
				visited[ps[i].x] = true;
			}
			else{
				r[i] = 0;
			}
		}
	}
	
	public void doSolve(){
		//扫面线算法
		long ans = 0;
		for (int i = 0; i < N; ++i){
			int j = i;
			while (j + 1 < N && ps[j].y == ps[j + 1].y) j++; // ps[j].y != ps[j + 1].y
			//j 指向的是同一行最后一个点
			for (int k = i; k <= j; ++k){ //每一个点的上方没有点，则从累积和中删除
				if (r[k] == 1 && l[k] == 0){ //下方有黑点，且上方没有黑点了，则此条线已经不存在了
					add(ps[k].x, -1);
				}
			}
			
			if (i == j);
			else{
				ans += sum(ps[j].x - 1) - sum(ps[i].x);
				for (int k = i + 1; k < j; ++k){
					if (l[k] == 0 && r[k] == 0){ //上方和下方均有黑点的
						ans --;
					}
//					else{
//						ans --;
//					}
				}
			}
			
			//开始标记
			for (int k = i; k <= j; ++k){
				if (r[k] == 0 && l[k] == 1){  //上方有黑点，且下方没有黑点（避免重复计算）
					add(ps[k].x, 1);
				}
			}
			i = j;
		}
		out.println(ans + N);
	}
	
	/************************binary indexed tree***************************/
	long[] BIT = new long[MAX_N];
	
	public void add(int i, int val){
		while (i < MAX_N){
			BIT[i] += val;
			i += i & -i;
		}
	}
	
	public long sum(int i){
		int res = 0;
		while (i > 0){
			res += BIT[i];
			i -= i & -i;
		}
		return res;
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
		new SolutionDay18_P3109().run();
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


