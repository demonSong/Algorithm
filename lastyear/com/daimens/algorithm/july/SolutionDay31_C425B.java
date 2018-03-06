package com.daimens.algorithm.july;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Set;

public class SolutionDay31_C425B {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/201707/425B.txt";
	
	void solve() {
		String good = ns();
		String pattern = ns();
		int n = ni();
		Set<Character> letters = new HashSet<>();
		for (char c : good.toCharArray()) letters.add(c);
		for (int i = 0; i < n; ++i){
			String query = ns();
			out.println(match(query, pattern, letters) ? "YES" : "NO");
		}
	}
	
	boolean match(String query, String pattern, Set<Character> letters){
		if (pattern.contains("*")){
			int index = pattern.indexOf("*");
			String p1 = pattern.substring(0, index);
			String p2 = pattern.substring(index + 1, pattern.length());
			boolean match = true;
			if (query.length() >= index)
				match &= match(query.substring(0, index), p1, letters);
			else return false;
			
			if (!match) return false;
			
			for (int j = index; j < query.length() - p2.length(); ++j){
				if (letters.contains(query.charAt(j))){
					return false;
				}
			}
			
			if (query.length() - p2.length() >= 0)
				match &= match(query.substring(query.length() - p2.length(), query.length()), p2, letters);
			else return false;
			
			return match;
		}
		else{
			if (query.length() != pattern.length()) return false;
			int n = query.length();
			char[] q = query.toCharArray();
			char[] p = pattern.toCharArray();
			for (int i = 0; i < n; ++i){
				if (p[i] != '?'){
					if (p[i] != q[i]) return false;
				}
				else{
					if (!letters.contains(q[i])) return false; 
				}
			}
		}
		return true;
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
		new SolutionDay31_C425B().run();
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


