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

public class SolutionDay21_P2632 {
	
	String INPUT = "./data/judge/201712/P2632.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay21_P2632().run();
	}
	
	int R, C;
	int N, M;
	
	int[][] dir = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}}; // E N W S
	
	void read() {
		int t = ni();
		while (t --> 0) {
			C = ni();
			R = ni();
			N = ni();
			M = ni();
			
			int[][] pos = new int[N][2]; 
			int[] direction = new int[N];
			
			int[][] grid = new int[R][C];
			
			for (int i = 0; i < N; ++i) {
				int x = ni();
				int y = ni();
				pos[i] = new int[] {R - y, x - 1};
				grid[R - y][x - 1] = i + 1;
				char d = nc();
				if (d == 'E') {
					direction[i] = 0;
				}
				if (d == 'W') {
					direction[i] = 2;
				}
				if (d == 'N') {
					direction[i] = 1;
				}
				if (d == 'S') {
					direction[i] = 3;
				}
			}
			
			boolean valid = true;
			for (int i = 0; i < M; ++i) {
				int robot  = ni();
				char inst  = nc();
				int repeat = ni();
				
				if (!valid) continue;
				
				if (inst == 'L') {
					direction[robot - 1] = (direction[robot - 1] + repeat) % 4; 
				}
				else if (inst == 'R') {
					direction[robot - 1] = (direction[robot - 1] - repeat % 4 + 4) % 4; 
				}
				else {
					// simulation
					int[] move = dir[direction[robot - 1]];
					grid[pos[robot - 1][0]][pos[robot - 1][1]] = 0;
					for (int j = 0; j < repeat; ++j) {
						int nr = pos[robot - 1][0] + move[0];
						int nc = pos[robot - 1][1] + move[1];
						if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
							if (valid) out.println("Robot " + robot + " crashes into the wall");
							valid = false;
							break;
						}
						
						if (grid[nr][nc] != 0) {
							if (valid) out.println("Robot " + robot + " crashes into robot " + grid[nr][nc]);
							valid = false;
							break;
						}
						
						pos[robot - 1][0] = nr;
						pos[robot - 1][1] = nc;
					}
					
					grid[pos[robot - 1][0]][pos[robot - 1][1]] = robot;
				}
			}
			
			if (valid) out.println("OK");
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

