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

public class SolutionDay26_P1795 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/201707/1795.txt";
	
	
	static final int INF = 1 << 29;
	static final int MAX_N = 15;
	int[][] cost = new int[MAX_N][MAX_N];
	int[][] dp = new int[1 << MAX_N][MAX_N];
	void solve() {
		int test = ni();
		for (int t = 0; t < test; ++t){
			int len = ni();
			String[] str = new String[len];
			for (int j = 0; j < len; ++j){
				str[j] = ns();
			}
			
			//预处理，去重,把包含的字符串去除
			for (int i = 0; i < len; ++i){
				for (int j = 0; j < len; ++j){
					if (i == j) continue;
					if (str[i].contains(str[j])){
						str[j] = str[i];
					}
				}
			}
			
			Set<String> set = new HashSet<String>(Arrays.asList(str));
			int N = set.size();
			String[] newStr = set.toArray(new String[0]);
			
			int[] lenStr = new int[N];
			for (int i = 0; i < N; ++i){
				lenStr[i] = newStr[i].length();
			}
			
			//i右拼接j左
			for (int i = 0; i < N; ++i){
				for (int j = 0; j < N; ++j){
					for (int l = 0; l < Math.min(lenStr[i], lenStr[j]); ++l){
						if (newStr[i].substring(lenStr[i] - l).equals(newStr[j].substring(0, l))){
							cost[i][j] = lenStr[j] - l;
						}
					}
				}
			}
			
			//进行最短距离拼接
			for (int i = 0; i < 1 << N; ++i){
				Arrays.fill(dp[i], INF);
			}
			
			//拼接i所需要的最短距离
			for (int i = 0; i < N; ++i){
				dp[0 | 1 << i][i] = lenStr[i];
			}
			
			//遍历每种状态，对每种状态进行i和j的拼接
			for (int s = 0; s < 1 << N; ++s){
				for (int i = 0; i < N; ++i){
					if (dp[s][i] != INF){
						for (int j = 0; j < N; ++j){
							if ((s & 1 << j) == 0){
								dp[s | 1 << j][j] = Math.min(dp[s | 1 << j][j], dp[s][i] + cost[i][j]);
							}
						}
					}
				}
			}
			
			int bestLen = INF;
			for (int i = 0; i < N; ++i){
				bestLen = Math.min(dp[(1 << N) - 1][i], bestLen);
			}
			
			for (int i = 0; i < N; ++i){
				if (dp[(1 << N) - 1][i] == bestLen){
					dp[(1 << N) - 1][i] = -dp[(1 << N) - 1][i];
				}
			}
			
			for (int s = (1 << N) - 1; s >= 0; --s){
				for (int i = 0; i < N; ++i){
					if (dp[s][i] < 0){
						for (int j = 0; j < N; ++j){
							if (i != j && (s & (1 << j)) != 0){
								if (dp[s & ~(1 << i)][j] + cost[j][i] == -dp[s][i]){
									dp[s & ~(1 << i)][j] = -dp[s & ~(1 << i)][j];
								}
							}
						}
					}
				}
			}

			String res = new String(new char[]{'z' + 1});
			int append = 0;
			int last = -1;
			for (int i = 0; i < N; ++i){
				if (dp[append | 1 << i][i] < 0){
					if (res.compareTo(newStr[i]) > 0){
						res = newStr[i];
						last = i;
					}
				}
			}
			append |= 1 << last;
			for (int i = 0; i < N - 1; ++i){
				String tail = new String(new char[]{'z' + 1});
				int key = -1;
				for (int j = 0; j < N; ++j){
					if ((append & 1 << j) == 0){
						if (dp[append | 1 << j][j] < 0){
							if (Math.abs(dp[append][last]) + cost[last][j] == Math.abs(dp[append | 1 << j][j])){
								if (tail.compareTo(newStr[j].substring(lenStr[j] - cost[last][j])) > 0){
									key = j;
									tail = newStr[j].substring(lenStr[j] - cost[last][j]);
								}
							}
						}
					}
				}
				last = key;
				append |= 1 << key;
				res += tail;
			}
			
			out.println("Scenario #"+(t + 1)+":");
			out.println(res);
			out.println();
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
		new SolutionDay26_P1795().run();
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


