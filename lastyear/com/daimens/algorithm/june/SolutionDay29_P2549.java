package com.daimens.algorithm.june;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

public class SolutionDay29_P2549 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/2549.txt";
	
//	void solve() {
//		while (true){
//			int n = ni();
//			if (n == 0) break;
//			long[] arra = new long[n];
//			Map<Long, Integer> map = new HashMap<Long, Integer>();
//			for (int i = 0; i < n; ++i){
//				arra[i] = nl();
//				if (!map.containsKey(arra[i])){
//					map.put(arra[i], 0);
//				}
//				map.put(arra[i], map.get(arra[i]) + 1);
//			}
//			
//			TwoSum[] sums = new TwoSum[n * (n - 1) / 2];
//			for (int i = 0, k = 0; i < n; ++i){
//				for (int j = i + 1; j < n; ++j){
//					sums[k] = new TwoSum();
//					sums[k].sum = arra[i] + arra[j];
//					sums[k].i = i;
//					sums[k].j = j;
//					k++;
//				}
//			}
//			Arrays.sort(sums);
//			
//			long max = Long.MIN_VALUE;
//			for (int i = 0; i < n; ++i){
//				long d = arra[i];
//				if (map.get(d) != 1) continue;
//				for (int j = 0; j < n; ++j){
//					if (j == i) continue;
//					long key = d - arra[j];
//					int lo = loBound(sums, key);
//					int hi = upBound(sums, key);
//					if (lo == -1 || hi == -1) continue;
//					for (int l = lo; l <= hi; ++l){
//						TwoSum sum = sums[l];
//						if (sum.i == i || sum.j == i || sum.i ==j || sum.j == j) continue;
//						max = Math.max(d, max);
//					}
//				}
//			}
//			
//			if (max == Long.MIN_VALUE) out.println("no solution");
//			else out.println(max);
//			
//		}
//	}
	
	void solve() {
		while (true){
			int n = ni();
			if (n == 0) break;
			long[] arra = new long[n];
			Map<Long, Integer> map = new HashMap<Long, Integer>();
			for (int i = 0; i < n; ++i){
				arra[i] = nl();
				if (!map.containsKey(arra[i])){
					map.put(arra[i], 0);
				}
				map.put(arra[i], map.get(arra[i]) + 1);
			}
			
			TwoSum[] sums = new TwoSum[n * n];
			for (int i = 0; i < sums.length; ++i){
				sums[i] = new TwoSum(Long.MAX_VALUE, -1, -1);
			}
			
			int N = 0;
			for (int i = 0; i < n; ++i){
				if (map.get(arra[i]) != 1) continue;
				for (int j = 0; j < n; ++j){
					if (i == j) continue;
					sums[N++] = new TwoSum(arra[i] - arra[j], i, j);
				}
			}
			Arrays.sort(sums);
			long max = Long.MIN_VALUE;
			
			for (int i = 0; i < n; ++i){
				for (int j = i + 1; j < n; ++j){
					long key = arra[i] + arra[j];
					int lo = loBound(sums, N, key);
					int hi = upBound(sums, N, key);
					if (lo == -1 || hi == -1) continue;
					for (int l = lo; l <= hi; ++l){
						TwoSum sum = sums[l];
						if (sum.i == i || sum.j == i || sum.i ==j || sum.j == j) continue;
						max = Math.max(arra[sum.i], max);
					}
				}
			}
			
			if (max == Long.MIN_VALUE) out.println("no solution");
			else out.println(max);
			
		}
	}
	
	public int upBound(TwoSum[] sums, int len, long key){
		int lf = 0, rt = len - 1;
		while (lf < rt){
			int mid = lf + (rt - lf + 1) / 2;
			if (sums[mid].sum > key) rt = mid - 1;
			else lf = mid;
		}
		if (sums[lf].sum == key) return lf;
		return -1;
	}
	
	public int loBound(TwoSum[] sums, int len, long key){
		int lf = 0, rt = len - 1;
		while (lf < rt){
			int mid = lf + (rt - lf) / 2;
			if (sums[mid].sum < key) lf = mid + 1;
			else rt = mid;
		}
		if (sums[rt].sum == key) return rt;
		return -1;
	}
	
	class TwoSum implements Comparable<TwoSum>{
		long sum;
		int i;
		int j;
		
		public TwoSum(long sum, int i, int j) {
			this.sum = sum;
			this.i = i;
			this.j = j;
		}
		
		@Override
		public String toString() {
			return "" + sum;
		}

		@Override
		public int compareTo(TwoSum o) {
			return this.sum < o.sum ? -1 : 1;
		}
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
		new SolutionDay29_P2549().run();
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
