package com.daimens.algorithm.december;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class SolutionDay11_P1753 {
	
	String INPUT = "./data/judge/201712/P1753.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay11_P1753().run();
	}
	
	
	class State{
		
		boolean[][] board;
		
		State(boolean[][] board){
			this.board = board;
		}
		
		int hash() {
			int n = 4;
			int hash = 0;
			int k = 0;
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < n; ++j) {
					boolean f = board[i][j];
					int bit = f == true ? 1 : 0;
					hash |= bit << k;
					k++;
				}
			}
			return hash;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 4; ++i) {
				for (int j = 0; j < 4; ++j) {
					sb.append((board[i][j] ? 1 : 0) + (j + 1 == 4 ? "\n" : " "));
				}
			}
			return sb.toString();
		}
	}
	
	void read() {
	
		int n = 4;
		
		int START = 0;
		int END   = 65535;
		
		boolean[][] board = new boolean[n][n];
		for (int i = 0; i < n; ++i) {
			String line = ns();
			for (int j = 0; j < n; ++j) {
				char c = line.charAt(j);
				board[i][j] = c == 'b';
			}
		}
		
		Queue<State> queue = new ArrayDeque<State>();
		Set<Integer> vis   = new HashSet<Integer>();
		int[][] dir = {{0, 1},{0, -1},{1, 0},{-1, 0},{0, 0}};
		
		State hello = new State(board);
		
		queue.offer(hello);
		vis.add(hello.hash());
		
		int ans  = -1;
		int turn = 0;
		outer: while (!queue.isEmpty() && ans == -1) {
			int size = queue.size();
			for (int i = 0; i < size; ++i) {
				State cur = queue.poll();
				if (cur.hash() == START || cur.hash() == END) {
					ans = turn;
					break outer;
				}
				for (int x = 0; x < n; ++x) {
					for (int y = 0; y < n; ++y) {
						State nxt = new State(flip(cur.board, x, y, dir));
						if (!vis.contains(nxt.hash())) {
							queue.offer(nxt);
							vis.add(nxt.hash());
						}
					}
				}
			}
			turn ++;
		}
		if (ans == -1) out.println("Impossible");
		else out.println(ans);
	}
	
	boolean[][] flip(boolean[][] board, int x, int y, int[][] dir){
		int n = 4;
		boolean[][] ans = new boolean[n][n];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				ans[i][j] = board[i][j];
			}
		}
		
		for (int[] d : dir) {
			int nx = d[0] + x;
			int ny = d[1] + y;
			if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
				ans[nx][ny] = !board[nx][ny];
			}
		}
		
		return ans;
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

