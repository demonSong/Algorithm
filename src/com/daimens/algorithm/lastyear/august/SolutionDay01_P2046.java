package com.daimens.algorithm.august;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class SolutionDay01_P2046 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/201708/P2046.txt";
	
	class Game{
		int[][] board = new int[4][8];
		int turn;
		public Game(int[][] board){
			this.board = board;
			this.turn = 0;
			int[] y = find(11);
			swap(new int[]{0, 0}, y);
			y = find(21);
			swap(new int[]{1, 0}, y);
			y = find(31);
			swap(new int[]{2, 0}, y);
			y = find(41);
			swap(new int[]{3, 0}, y);
		}
		
		public Game(Game newGame){
			for (int i = 0; i < 4; ++i){
				for (int j = 0; j < 8; ++j){
					this.board[i][j] = newGame.board[i][j];
				}
			}
			this.turn = newGame.turn;
		}
		
		public boolean canFill(int i, int j){
			if (board[i][j] != 0) return false;
			if (board[i][j - 1] != 0 && (board[i][j - 1] % 10) != 7) return true;
			return false;
		}
		
		public boolean done(){
			for (int i = 0; i < 4; ++i){
				if (board[i][7] != 0) return false;
			}
			for (int i = 0; i < 4; ++i){
				for (int j = 0; j < 7; ++j){
					if (board[i][j] != (i + 1) * 10 + (j + 1)) return false;
				}
			}
			return true;
		}
		
		public void fillGap(int i, int j){
			int key = board[i][j - 1] + 1;
			int[] pos = find(key);
			swap(new int[]{i, j}, pos);
			this.turn ++;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Game){
				Game that = (Game)obj;
				for (int i = 0; i < 4; ++i){
					for (int j = 0; j < 8; ++j){
						if (board[i][j] != that.board[i][j]) return false;
					}
				}
				return true;
			}
			else return false;
		}
		
		public int[] find(int key){
			for (int i = 0; i < 4; ++i){
				for (int j = 0; j < 8; ++j){
					if (board[i][j] == key) return new int[]{i, j};
				}
			}
			return new int[]{-1, -1};
		}
		
		public void swap(int[] x, int[] y){
			int tmp = board[x[0]][x[1]];
			board[x[0]][x[1]] = board[y[0]][y[1]];
			board[y[0]][y[1]] = tmp;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 4; ++i){
				for (int j = 0; j < 8; ++j){
					sb.append(board[i][j] + (j + 1 == 8 ? "\n" : " "));
				}
			}
			return sb.toString();
		}
		
		@Override
		public int hashCode() {
			int hash = 0;
			for (int i = 0; i < 4; ++i){
				for (int j = 1; j < 8; ++j){
					hash += board[i][j];
					hash <<= 1;
				}
			}
			return hash;
		}
	}
	
	void solve() {
		int T = ni();
		while (T --> 0){
			int[][] board = new int[4][8];
			for (int i = 0; i < 4; ++i){
				for (int j = 1; j < 8; ++j){
					board[i][j] = ni();
				}
			}
			Game game = new Game(board);
			Queue<Game> queue = new LinkedList<Game>();
			Set<Game> visited = new HashSet<Game>();
			if (game.done()){
				out.println(0);
				continue;
			}
			queue.offer(game);
			int ans = -1;
			boolean end = false;
			outer: while (!queue.isEmpty() && !end){
				Game gg = queue.poll();
				if (visited.contains(gg)) continue;
				visited.add(gg);
				for (int i = 0; i < 4; ++i){
					for (int j = 1; j < 8; ++j){
						if (gg.canFill(i, j)){
							Game tmp = new Game(gg);
							tmp.fillGap(i, j);
							if (tmp.done()){
								ans = tmp.turn;
								end = true;
								continue outer;
							}
							else queue.offer(tmp);
						}
					}
				}
			}
			out.println(ans);
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
		new SolutionDay01_P2046().run();
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
