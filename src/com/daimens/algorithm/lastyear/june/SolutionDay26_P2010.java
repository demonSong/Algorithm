package com.daimens.algorithm.june;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;

public class SolutionDay26_P2010 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/2010.txt";

	class Cow {
		int rank;
		int score;
		int aid;

		@Override
		public String toString() {
			return "[" + score + ", " + aid + "]";
		}
	}

	void solve() {
		int N = ni();
		int C = ni();
		int F = ni();
		Cow[] scores = new Cow[C];
		Cow[] aids = new Cow[C];
		for (int i = 0; i < C; ++i) {
			int score = ni();
			int aid = ni();
			scores[i] = new Cow();
			scores[i].aid = aid;
			scores[i].score = score;
		}
		Arrays.sort(scores, new Comparator<Cow>() {
			@Override
			public int compare(Cow o1, Cow o2) {
				return o1.score - o2.score;
			}
		});

		for (int i = 0; i < C; ++i) {
			scores[i].rank = i;
		}
		aids = Arrays.copyOf(scores, scores.length);
		Arrays.sort(aids, new Comparator<Cow>() {
			@Override
			public int compare(Cow o1, Cow o2) {
				return o1.aid - o2.aid;
			}
		});

		int lb = 0, ub = C, ans = -1;
		while (ub - lb > 1) {
			int mid = lb + (ub - lb) / 2;
			int total = scores[mid].aid;
			int left = 0, right = 0;
			for (int i = 0; i < C; ++i) {
				if (aids[i].rank < mid && total + aids[i].aid <= F && left < N / 2) {
					total += aids[i].aid;
					left++;
				} else if (aids[i].rank > mid && total + aids[i].aid <= F && right < N / 2) {
					total += aids[i].aid;
					right++;
				}
			}
			if (left < N / 2 && right < N / 2) {
				ans = -1;
				break;
			} else if (left < N / 2) {
				lb = mid;
			} else if (right < N / 2) {
				ub = mid;
			} else { // 满足条件的尽可能让mid变大
				ans = scores[mid].score;
				lb = mid;
			}
		}
		out.println(ans);
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
		new SolutionDay26_P2010().run();
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
