package com.daimens.algorithm.december;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;

public class SolutionDay21_P2993 {
	
	String INPUT = "./data/judge/201712/P2993.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay21_P2993().run();
	}
	
	char[][] board;
	int row = 17;
	int col = 33;
	
	void init() {
		board = new char[row][col];
		for (int i = 0; i < row; i += 2) {
			String line = "+---+---+---+---+---+---+---+---+";
			for (int j = 0; j < col; ++j) {
				board[i][j] = line.charAt(j);
			}
		}
		
		for (int i = 3; i < row; i += 4) {
			String line = "|:::|...|:::|...|:::|...|:::|...|";
			for (int j = 0; j < col; ++j) {
				board[i][j] = line.charAt(j);
			}
		}
		
		for (int i = 1; i < row; i += 4) {
			String line = "|...|:::|...|:::|...|:::|...|:::|";
			for (int j = 0; j < col; ++j) {
				board[i][j] = line.charAt(j);
			}
		}
	}
	
	void print() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < row; ++i) {
			for (int j = 0; j < col; ++j) {
				sb.append(board[i][j] + (j + 1 == col ? "\n" : ""));
			}
		}
		
		out.print(sb.toString());
	}
	
	void chessOnBoard(String[] pos, boolean white) {
		int n = pos.length;
		for (int i = 0; i < n; ++i) {
			char c = pos[i].charAt(0);
			if (notPawn(c)) {
				int col = pos[i].charAt(1) - 'a';
				int row = 8 - (pos[i].charAt(2) - '0');
				int nr = 2 * row + 1;
				int nc = 4 * col + 2;
				if (white)
					board[nr][nc] = c;
				else board[nr][nc] = (char) (c - 'A' + 'a');
			}
			else {
				int col = pos[i].charAt(0) - 'a';
				int row = 8 - (pos[i].charAt(1) - '0');
				int nr = 2 * row + 1;
				int nc = 4 * col + 2;
				if (white)
					board[nr][nc] = 'P';
				else board[nr][nc] = 'p';
			}
		}
	}
	
	boolean notPawn(char c) {
		if (c == 'K' || c == 'Q' || c == 'R' || c == 'B' || c== 'N') return true;
		return false;
	}
	
	
	void read() {
		ns();
		String white = ns();
		ns();
		String black = ns();

		init();
		chessOnBoard(white.split(","), true);
		chessOnBoard(black.split(","), false);
		print();
	}
	

	FastScanner in;
	PrintWriter out;
	
	void run() throws IOException {
		boolean oj;
		try {
			oj = ! System.getProperty("user.dir").equals("F:\\oxygen_workspace\\Algorithm");
		} catch (Exception e) {
			oj = System.getProperty("ONLINE_JUDGE") != null;
		}
		
		InputStream is = oj ? System.in : new FileInputStream(new File(INPUT));
		in = new FastScanner(is);
		out = new PrintWriter(System.out);
		long s = System.currentTimeMillis();
		read();
		out.flush();
		if (!oj){
			System.out.println("[" + (System.currentTimeMillis() - s) + "ms]");
		}
	}
	
	public boolean more(){
		return in.hasNext();
	}
	
	public int ni(){
		return in.nextInt();
	}
	
	public long nl(){
		return in.nextLong();
	}
	
	public double nd(){
		return in.nextDouble();
	}
	
	public String ns(){
		return in.nextString();
	}
	
	public char nc(){
		return in.nextChar();
	}
	
	class FastScanner {
		BufferedReader br;
		StringTokenizer st;
		boolean hasNext;

		public FastScanner(InputStream is) throws IOException {
			br = new BufferedReader(new InputStreamReader(is));
			hasNext = true;
		}

		public String nextToken() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (Exception e) {
					hasNext = false;
					return "##";
				}
			}
			return st.nextToken();
		}
		
		String next = null;
		public boolean hasNext(){
			next = nextToken();
			return hasNext;
		}

		public int nextInt() {
			if (next == null){
				hasNext();
			}
			String more = next;
			next = null;
			return Integer.parseInt(more);
		}

		public long nextLong() {
			if (next == null){
				hasNext();
			}
			String more = next;
			next = null;
			return Long.parseLong(more);
		}

		public double nextDouble() {
			if (next == null){
				hasNext();
			}
			String more = next;
			next = null;
			return Double.parseDouble(more);
		}
		
		public String nextString(){
			if (next == null){
				hasNext();
			}
			String more = next;
			next = null;
			return more;
		}
		
		public char nextChar(){
			if (next == null){
				hasNext();
			}
			String more = next;
			next = null;
			return more.charAt(0);
		}
	}
	
	static class D{
		
		public static void pp(int[][] board, int row, int col) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < row; ++i) {
				for (int j = 0; j < col; ++j) {
					sb.append(board[i][j] + (j + 1 == col ? "\n" : " "));
				}
			}
			System.out.println(sb.toString());
		}
		
		public static void pp(char[][] board, int row, int col) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < row; ++i) {
				for (int j = 0; j < col; ++j) {
					sb.append(board[i][j] + (j + 1 == col ? "\n" : " "));
				}
			}
			System.out.println(sb.toString());
		}
	}
	
	static class ArrayUtils {

		public static void fill(int[][] f, int value) {
			for (int i = 0; i < f.length; ++i) {
				Arrays.fill(f[i], value);
			}
		}
		
		public static void fill(int[][][] f, int value) {
			for (int i = 0; i < f.length; ++i) {
				fill(f[i], value);
			}
		}
		
		public static void fill(int[][][][] f, int value) {
			for (int i = 0; i < f.length; ++i) {
				fill(f[i], value);
			}
		}
	}
	
	static class Num{
		public static <K> void inc(Map<K, Integer> mem, K k) {
			if (!mem.containsKey(k)) mem.put(k, 0);
			mem.put(k, mem.get(k) + 1);
		}
	}
}

