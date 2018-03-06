package com.daimens.algorithm.june;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class SolutionDay28_P1222 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/1222.txt";
	
	void solve() {
		int n = ni();
		for (int k = 1; k <= n; ++k){
			int M = 5;
			int N = 6;
			int[][] board = new int[M][N];
			for (int i = 0; i < M; ++i){
				for (int j = 0; j < N; ++j){
					board[i][j] = ni();
				}
			}
			out.println("PUZZLE #" + k);
			out.println(calc(board, M, N));
		}
		
		for (int i = 0; i < 1 << 4; ++i){
			int[] c = new int[4];
			for (int j = 0; j < 4; ++j){
				c[4 - 1 - j] =  i >> j & 1;
			}
			for (int j = 0; j < 4; ++j) System.out.print(c[j] + (j + 1 == 4 ? "\n" : ""));
			
		}
	}
	
	int[][] dir = {{0,0},{1,0},{-1,0},{0,1},{0,-1}};
	String calc(int[][] board, int M, int N) {

		int[][] click = new int[M][N];
		
		int[][] ans = new int[M][N];
		for (int i = 0; i < 1 << N; ++i){
			click = new int[M][N];
			int[][] clone = clone(board);
			for (int j = 0; j < N; ++j){
				click[0][N - 1 - j] = ((i & (1 << j)) != 0) ? 1 : 0;
			}

			flip(clone, click, 0);
			for (int j = 1; j < M; ++j){
				for (int k = 0; k < N; ++k){
					click[j][k] = clone[j-1][k];
				}
				flip(clone, click, j);
			}
			
			if(!valid(clone)) continue;
			
			ans = clone(click);
			break;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; ++i){
			for (int j = 0; j < N; ++j){
				sb.append(ans[i][j] + ((j + 1 != N) ? " " : "\n"));
			}
		}
		return sb.toString().substring(0, sb.length() - 1);
	}
	
	public int cal(int[][] click){
		int M = click.length;
		int N = click[0].length;
		int cnt = 0;
		for (int i = 0; i < M; ++i){
			for (int j = 0; j < N; ++j){
				cnt += click[i][j];
			}
		}
		return cnt;
	}
	
	public boolean valid(int[][] board){
		int M = board.length;
		int N = board[0].length;
		for (int i = 0; i < M; ++i){
			for (int j = 0; j < N; ++j){
				if (board[i][j] != 0) return false;
			}
		}
		return true;
	}
	
	public void flip(int[][] board, int[][] click, int row){
		int N = click[0].length;
		int M = click.length;
		for (int i = 0; i < N; ++i){
			if (click[row][i] == 1){
				for (int[] d : dir){
					int x = d[0] + row;
					int y = d[1] + i;
					if (x >= 0 && x < M && y >= 0 & y < N){
						board[x][y] = board[x][y] == 1 ? 0 : 1;
					}
				}
			}
		}
	}
	
	public int[][] clone(int[][] board){
		int M = board.length;
		int N = board[0].length;
		int[][] clone = new int[M][N];
		for (int i = 0; i < M; ++i){
			for (int j = 0; j < N; ++j){
				clone[i][j] = board[i][j];
			}
		}
		return clone;
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
		new SolutionDay28_P1222().run();
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