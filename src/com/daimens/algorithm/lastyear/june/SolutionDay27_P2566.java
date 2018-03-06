package com.daimens.algorithm.june;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;

public class SolutionDay27_P2566 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/2566.txt";
	
	class Pair{
		int sum;
		int index;
		
		public Pair(int sum, int index){
			this.sum = sum;
			this.index = index;
		}
		
		@Override
		public String toString() {
			return "["+sum+", "+index+"]";
		}
	}
	
	Pair[] sums;
	void solve(){
		while (true){
			int N = ni();
			int Q = ni();
			if (N == 0 && Q == 0) break;
			int[] a = na(N);
			int[] q = na(Q);
			sums = new Pair[N+1];
			for (int i = 0; i <= N; ++i) sums[i] = new Pair(0,i);
			for (int i = 0; i < N; ++i){
				sums[i+1].sum = sums[i].sum + a[i]; 
			}
			Arrays.sort(sums, new Comparator<Pair>() {
				@Override
				public int compare(Pair o1, Pair o2) {
					return o1.sum == o2.sum ? o2.index - o1.index : o1.sum - o2.sum;
				}
			});
			
			for (int i = 0; i < Q; ++i){
				int diff = q[i];
				int lb = 0, ub = 0, sum = -1;
				res = 0x80808080;
				l = 0;
				r = 0;
				for(;;){
					while (ub < N && sum < diff){
						sum = getSum(lb, ++ub, diff);
					}
					if (sum < diff) break;
					sum = getSum(++lb, ub, diff);
				}
				out.println(res + " " + l + " " + r);
			}
		}
	}
	
	int res, l, r;
	private int getSum(int lb, int ub, int diff){
		if (lb >= ub) return -1 << 30;
		int sum = sums[ub].sum - sums[lb].sum;
		if (Math.abs(diff - sum) < Math.abs(diff - res)){
			res = sum;
			int i = sums[ub].index;
			int j = sums[lb].index;
			l = i < j ? i + 1 : j + 1;
			r = i < j ? j : i;
		}
		return sum;
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
		new SolutionDay27_P2566().run();
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