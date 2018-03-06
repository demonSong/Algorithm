package com.daimens.algorithm.june;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Set;

public class SolutionDay26_P3320 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/3320.txt";
	
//	void solve() {
//		int n = ni();
//		int[] a = na(n);
//		Set<Integer> set = new HashSet<Integer>();
//		for (int i = 0; i < n; ++i) set.add(a[i]);
//		Map<Integer,Integer> window = new HashMap<Integer, Integer>();
//		int min = n;
//		for (int i = 0, l = 0; i < n; ++i){
//			if(!window.containsKey(a[i])) window.put(a[i],0);
//			window.put(a[i], window.get(a[i]) + 1);
//			
//			if(window.size() == set.size()){
//				min = Math.min(min, i - l + 1);
//				while (l < n && window.get(a[l]) >= 2){
//					min = Math.min(min, i - l);
//					window.put(a[l],window.get(a[l]) - 1);
//					l++;
//				}
//			}
//		}
//		out.println(min);
//	}
	
	void solve(){
		int n = ni();
		int[] a = na(n);
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < n; ++i) set.add(a[i]);
		Map<Integer,Integer> window = new HashMap<Integer, Integer>();
		int min = n;
		int l = 0, k = 0, len = set.size();
		for(;;){
			while (l < n && window.size() < len){
				if (!window.containsKey(a[l])) window.put(a[l], 0);
				window.put(a[l], window.get(a[l]) + 1);
				l++;
			}
			if (window.size() < len) break;
			min = Math.min(min, l - k);
			if(window.get(a[k]) == 1){
				window.remove(a[k]);
				k++;
			}else{
				window.put(a[k], window.get(a[k]) - 1);
				k++;
			}
		}
		out.println(min);
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
		new SolutionDay26_P3320().run();
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