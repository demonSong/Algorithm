package com.daimens.algorithm.july;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class SolutionDay30_P3074 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/201707/3074.txt";
	
	class Pair implements Comparable<Pair>{
		int id;
		int value;
		public Pair(int id, int value){
			this.id = id;
			this.value = value;
		}
		@Override
		public int compareTo(Pair that) {
			return this.value - that.value;
		}
		
		@Override
		public String toString() {
			return this.value + "";
		}
	}
	
	void solve() {
		while (true){
			String line = ns();
			if (line.equals("end")) break;
			char[][] board = new char[9][9];
			char[] sudoku = line.toCharArray();
			for (int i = 0; i < sudoku.length; ++i){
				board[i / 9][i % 9] = sudoku[i];
			}
			
			int[] map = new int[27];
			for (int col = 0; col < 9; ++col){
				for (int row = 0; row < 9; ++row){
					if (board[row][col] == '.'){
						map[col]++;
						map[row + 9]++;
						int nrow = row / 3;
						int ncol = col / 3;
						int id = nrow * 3 + ncol;
						map[id + 18]++;
					}
				}
			}
			
			Pair[] pp = new Pair[27];
			for (int i = 0; i < 27; ++i){
				pp[i] = new Pair(i, map[i]);
			}
			Arrays.sort(pp);
			
			if (dfs(board, pp, 0)){
				pp(board);
			}
			
		}
	}
	
	public boolean dfs(char[][] board, Pair[] pp, int pos){
		if (pos == pp.length){
			return true;
		}
		int id = pp[pos].id;
		if (id >= 0 && id < 9){ //所在列
			for (int row = 0; row < 9; ++row){
				if (board[row][id] == '.'){
					boolean valid = false;
					for (int num = 1; num <= 9; ++num){
						if (valid(board, row, id, num)){
							valid = true;
							board[row][id] = (char)('0' + num);
							if (dfs(board, pp, pos)){
								return true;
							}
							else{
								valid = false;
								board[row][id] = '.';
							}
						}
					}
					if (!valid) return false;
				}
			}
		}
		else if (id >= 9 && id < 18){ //所在行
			int row = id - 9;
			for (int col = 0; col < 9; ++col){
				if (board[row][col] == '.'){
					boolean valid = false;
					for (int num = 1; num <= 9; ++num){
						if (valid(board, row, col, num)){
							valid = true;
							board[row][col] = (char)('0' + num);
							if (dfs(board, pp, pos)){
								return true;
							}
							else{
								valid = false;
								board[row][col] = '.';
							}
						}
					}
					if (!valid) return false;
				}
			}
		}
		else{  //所在块
			id -= 18;
			int row = id / 3 * 3;
			int col = id % 3 * 3;
			for (int i = 0; i < 3; ++i){
				for (int j = 0; j < 3; ++j){
					int nrow = row + i;
					int ncol = col + j;
					if (board[nrow][ncol] == '.'){
						boolean valid = false;
						for (int num = 1; num <= 9; ++num){
							if (valid(board, nrow, ncol, num)){
								valid = true;
								board[nrow][ncol] = (char)('0' + num);
								if (dfs(board, pp, pos)){
									return true;
								}
								else{
									valid = false;
									board[nrow][ncol] = '.';
								}
							}
						}
						if (!valid) return false;
					}
				}
			}
		}
		if (dfs(board, pp, pos + 1)) return true;
		return false;
	}
	
	public void pp(char[][] board){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 9; ++i){
			for (int j = 0; j < 9; ++j){
				sb.append(board[i][j] + (j + 1 == 9 ? "" : ""));
			}
		}
		out.println(sb.toString());
	}
	
	public boolean valid(char[][] board, int row, int col, int n){
		for (int i = 0; i < 9; ++i){
			if (board[row][i] == (char)('0' + n)) return false;
		}
		
		for (int i = 0; i < 9; ++i){
			if (board[i][col] == (char)('0' + n)) return false;
		}
		
		for (int i = 0; i < 3; ++i){
			for (int j = 0; j < 3; ++j){
				int nrow = i + row / 3 * 3;
				int ncol = j + col / 3 * 3;
				if (board[nrow][ncol] == (char)('0' + n)) return false;
			}
		}
		return true;
	}
	
	
	
	public boolean dfs(char[][] board, int cnt){
		if (cnt == 81) return true;
		int row = cnt / 9;
		int col = cnt % 9;
		if (board[row][col] != '.'){
			if (dfs(board, cnt + 1)) return true;
		}
		else{
			for (int num = 1; num <= 9; ++num){
				if (valid(board, row, col, num)){
					board[row][col] = (char)('0' + num);
					if (dfs(board, cnt + 1)) return true;
					else board[row][col] = '.';
				}
			}
		}
		return false;
	}
	
	public boolean dfs(char[][] board, int i, int j){
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
		new SolutionDay30_P3074().run();
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


