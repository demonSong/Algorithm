package com.daimens.algorithm.december;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class SolutionDay21_P1573 {
	
	String INPUT = "./data/judge/201712/P1573.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay21_P1573().run();
	}
	
	int[][] dir = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}}; // E N W S
	
	void read() {
		while (true) {
			int R = ni();
			int C = ni();
			int P = ni();
			if (R + C + P == 0) break;
			char[][] grid = new char[R][C];
			for (int i = 0; i < R; ++i) {
				String line = ns();
				for (int j = 0; j < C; ++j) {
					grid[i][j] = line.charAt(j);
				}
			}
			
			int timeStamp = 0;
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			
			// simulation
			int sr = 0;
			int sc = P - 1;
			
			while (true) {
				if (sr < 0 || sr >= R || sc < 0 || sc >= C) {
					out.println(timeStamp + " step(s) to exit");
					break;
				}
				
				if (map.containsKey(sr * C + sc)) {
					out.println(map.get(sr * C + sc) + " step(s) before a loop of " + (timeStamp - map.get(sr * C + sc)) + " step(s)");
					break;
				}
				
				map.put(sr * C + sc, timeStamp++);
				
				if (grid[sr][sc] == 'E') {
					sr = sr + dir[0][0];
					sc = sc + dir[0][1];
				}
				else if (grid[sr][sc] == 'N') {
					sr = sr + dir[1][0];
					sc = sc + dir[1][1];			
				}
				else if (grid[sr][sc] == 'W') {
					sr = sr + dir[2][0];
					sc = sc + dir[2][1];
				}
				else {
					sr = sr + dir[3][0];
					sc = sc + dir[3][1];
				}
			}
			
		}
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

