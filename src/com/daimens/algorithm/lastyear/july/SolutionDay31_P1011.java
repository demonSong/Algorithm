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

public class SolutionDay31_P1011 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/201707/P1011.txt";
	
	int[] in;
	int candicate;
	void solve() {
		while (true){
			int n = ni();
			if (n == 0) break;
			in = new int[51];
			finish = false;
			int sum = 0;
			candicate = 0;
			int max = 0;
			for (int i = 0; i < n; ++i){
				int len = ni();
				max = Math.max(max, len);
				sum += len;
				in[len] ++;
			}
			candicate = max;
			while (true){
				if (sum % candicate == 0){
					check(sum / candicate, candicate, max);
				}
				if (finish) break;
				++candicate;
			}
			out.println(candicate);
		}
	}
	
	boolean finish;
	public void check(int count, int len, int plen){
		--in[plen];
		if (count == 0){
			finish = true;
		}
		if (!finish){
			len -= plen; //剩余长度
			if (len != 0){
				int nextPlen = Math.min(len, plen);
				for (; nextPlen > 0; --nextPlen){
					if (in[nextPlen] != 0){
						check(count, len, nextPlen);
					}
				}
			}
			else{
				int max = 50;
				while (max > 0 && in[max] == 0) --max;
				check(count - 1, candicate, max); //当前剩余棒子的最大长度
			}
		}
		++in[plen];
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
		new SolutionDay31_P1011().run();
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


//void solve() {
//	while (true){
//		int n = ni();
//		if (n == 0) break;
//		int[] sticks = new int[n];
//		int min = 0;
//		int sum = 0;
//		int max = 0;
//		for (int i = 0; i < n; ++i){
//			int len = ni();
//			max = Math.max(max, len);
//			sum += len;
//			sticks[i] = len;
//		}
//		Arrays.sort(sticks);
//		Set<Integer> mem = new HashSet<Integer>();
//		for (int i = n; i >= 1; --i){
//			if (sum % i == 0){
//				min = sum / i;
//				if (min >= max && dfs(sticks, min, 0, new boolean[n], mem)) break;
//			}
//		}
//		out.println(min);
//	}
//}
//
//public boolean dfs(int[] sticks, int min, int sum, boolean[] visited, Set<Integer> mem){
//	if (mem.size() == sticks.length){
//		return min == sum;
//	}
//	if (sum > min) return false;
//	if (sum == min){
//		if (dfs(sticks, min, 0, visited, mem)) return true;
//		else return false;
//	}
//	for (int i = sticks.length - 1; i >= 0; --i){
//		int rem = min - sum;
//		if (!visited[i] && sticks[i] <= rem){
//			visited[i] = true;
//			mem.add(i);
//			if (dfs(sticks, min, sum + sticks[i], visited, mem)){
//				return true;
//			}
//			else{
//				visited[i] = false;
//				mem.remove(i);
//			}
//		}
//	}
//	return false;
//}


