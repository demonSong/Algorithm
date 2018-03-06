package com.daimens.algorithm.august;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;

public class SolutionDay23_P2104 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/201708/P2104.txt";
	
	static final int SIZE = (1 << 18) - 1;
	List<Integer>[] dat = new ArrayList[SIZE];
	int[] A;

	void solve(){
		int N = ni();
		int M = ni();
		A = new int[N];
		int[] sort = new int[N];
		for (int i = 0; i < N; ++i){
			A[i] = ni();
			sort[i] = A[i];
		}
		for (int i = 0; i < dat.length; ++i) dat[i] = new ArrayList<Integer>();
		
		Arrays.sort(sort);
		init(0, 0, N);
		
		for (int t = 0; t < M; ++t){
			int i = ni();
			int j = ni();
			int k = ni();
			i--;
//			int lf = -1, rt = N - 1;
//			while (rt - lf > 1){
//				int mid = (lf + rt) / 2;
//				int query = query(0, i, j, sort[mid], 0, N);
//				if (query < k){
//					lf = mid;
//				}
//				else{
//					rt = mid;
//				}
//			}
			
			long lf = Integer.MIN_VALUE, rt = Integer.MAX_VALUE;
			while (rt - lf > 1){
				long mid = (lf + rt) / 2;
				int query = query(0, i, j, mid, 0, N);
				if (query < k){
					lf = mid;
				}
				else{
					rt = mid;
				}
			}
			
			out.println(rt);
		}
	}
	
	
	/******************以下是线段树******************/
	
	/***
	 * 区间 [l, r)
	 * @param k
	 * @param l
	 * @param r
	 */
	public void init(int k, int l, int r){
		if (r - l == 1){
			dat[k].add(A[l]);
		}
		else{
			int lch = 2 * k + 1;
			int rch = 2 * k + 2;
			init(lch, l, (l + r) / 2); //为了能够准确的划分区间
			init(rch, (l + r) / 2, r);
			
			merge (dat[lch], dat[rch], dat[k]);
		}
	}
	
	/**
	 * 查询区间 [i, j)
	 * 线段树区间 [l, r)
	 * @param k
	 * @param i
	 * @param j
	 * @param x
	 * @param l
	 * @param r
	 * @return
	 */
	public int query(int k, int i, int j, long x, int l, int r){
		if (j <= l || i >= r) return 0;
		else if (i <= l && j >= r){
			return binarySearch(dat[k], x) + 1;
		}else{
			int ans = 0;
			ans += query(2 * k + 1, i, j, x, l, (l + r) / 2);
			ans += query(2 * k + 2, i, j, x, (l + r) / 2, r);
			return ans;
		}
	}
	
	public void merge(List<Integer> lch, List<Integer> rch, List<Integer> k){
		int l = 0, r = 0;
		while (l < lch.size() && r < rch.size()){
			if (lch.get(l) <= rch.get(r)){
				k.add(lch.get(l));
				l++;
			}
			else{
				k.add(rch.get(r));
				r++;
			}
		}
		
		while (l < lch.size()) k.add(lch.get(l++));
		while (r < rch.size()) k.add(rch.get(r++));
	}
	
	
	public int binarySearch(List<Integer> aux, long key){
		int lf = 0, rt = aux.size() - 1;
		while (lf < rt){
			int mid = lf + (rt - lf + 1) / 2;
			if (aux.get(mid) > key){
				rt = mid - 1;
			}
			else lf = mid;
		}
		if (aux.get(lf) <= key) return lf;
		return -1;
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
		new SolutionDay23_P2104().run();
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

//static final int MAX_N = 100000 + 16;
//static final int B = 1000;
//List<Integer>[] bucket = new ArrayList[MAX_N / B + 1];
//
//void solve() {
//	int n = ni();
//	int m = ni();
//	int[] sort = new int[n];
//	int[] arra = new int[n];
//	
//	for (int i = 0; i <= n / B; ++i) bucket[i] = new ArrayList<Integer>();
//	
//	for (int i = 0; i < n; ++i){
//		arra[i] = ni();
//		bucket[i / B].add(arra[i]);
//		sort[i] = arra[i];
//	}
//	
//	for (int i = 0; i < n / B; ++i){
//		Collections.sort(bucket[i]);
//	}
//	
//	Arrays.sort(sort);
//	
//	for (int t = 0; t < m; ++t){
//		int i = ni();
//		int j = ni();
//		int k = ni();
//		i--;
//		j--;
//		
//		int lf = -1, rt = n - 1;
//		while (rt - lf > 1){
//			int l = i;
//			int r = j;
//			
//			int s = l / B;
//			int e = r / B;
//			int mid = (lf + rt) / 2;
//			
//			int key = sort[mid];
//			
//			int x = 0;
//			if (e - s <= 1){
//				for (int y = l; y <= r; ++y){
//					if (arra[y] <= key) x++;
//				}
//			}
//			else{
//				
//				while (l < n && l / B == s){
//					if (arra[l] <= key) x++;
//					l++;
//				}
//				
//				while (r >= 0 && r / B == e){
//					if (arra[r] <= key) x++;
//					r--;
//				}
//				
//				for (int y = s + 1; y < e; ++y){
//					x += binarySearch(bucket[y], key) + 1;
//				}
//			}
//			if (x < k){
//				lf = mid;
//			}
//			else{
//				rt = mid;
//			}
//		}
//		out.println(sort[rt]);
//	}
//	
//}

