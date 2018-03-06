package com.daimens.algorithm.july;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class SolutionDay30_P2918 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/201707/2918.txt";
	
	void solve() {
		int T = ni();
		for (int t = 0; t < T; ++t) {
			out.println("Scenario #"+(t + 1)+":");
			boolean hasAnswer = false;
			char[][] board = nm(9, 9);
			for (int i = 8; i >= 0; --i) {
				for (int j = 8; j >= 0; --j) {
					if (board[i][j] == '0') {
						if (dfs(board, i, j)){
							pp(board);
							out.println();
							hasAnswer = true;
							j = 9;
							i = 9;
							break;
						}
					}
				}
			}
			if (!hasAnswer){
				pp(board);
				out.println();
			}
		}
	}
	
	public void pp(char[][] board){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 9; ++i){
			for (int j = 0; j < 9; ++j){
				sb.append(board[i][j] + (j + 1 == 9 ? "\n" : ""));
			}
		}
		out.print(sb.toString());
	}
	
	public boolean valid(char[][] board, int i, int j){
		int[] cnt = new int[10];
		for (int row = 0; row < 9; ++row){
			if (board[row][j] == '0') continue;
			cnt[board[row][j] - '0']++;
			if (cnt[board[row][j] - '0'] > 1) return false;
		}
		cnt = new int[10];
		for (int col = 0; col < 9; ++col){
			if (board[i][col] == '0') continue;
			cnt[board[i][col] - '0']++;
			if (cnt[board[i][col] - '0'] > 1) return false;
		}
		
		cnt = new int[10];
		for (int row = 0; row < 3; ++row){
			for (int col = 0; col < 3; ++col){
				int nrow = row + 3 * (i / 3);
				int ncol = col + 3 * (j / 3);
				if (board[nrow][ncol] == '0') continue;
				cnt[board[nrow][ncol] - '0']++;
				if (cnt[board[nrow][ncol] - '0'] > 1) return false;
			}
		}
		return true;
	}
	
	public boolean dfs(char[][] board, int i, int j){
		for (int num = 1; num <= 9; ++num){
			board[i][j] = (char)('0' + num);
			if (valid(board, i, j)){
				int res = 9 * i + j;
				while (board[res / 9][res % 9] != '0'){
					res--;
					if (res == -1) return true;
				}
				if (dfs(board, res / 9, res % 9)) return true;
				else board[i][j] = '0';
			}
			else{
				board[i][j] = '0';
			}
		}
		return false;
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
		new SolutionDay30_P2918().run();
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
