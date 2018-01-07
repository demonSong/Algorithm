package com.daimens.algorithm.december;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class SolutionDay21_P2996 {
	
	String INPUT = "./data/judge/201712/P2996.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay21_P2996().run();
	}
	
	void read() {
		int row = 16;
		int col = 33;
		char[][] board = new char[row][col];
		for (int i = 0; i < row; ++i) {
			String line = ns();
			for (int j = 0; j < col; ++j) {
				board[i][j] = line.charAt(j);
			}
		}
		
		Map<Character, List<int[]>> white = new HashMap<Character, List<int[]>>();
		Map<Character, List<int[]>> black = new HashMap<Character, List<int[]>>();
		
		for (int i = row - 1; i >= 0; --i) {
			for (int j = 0; j < col; ++j) {
				if (isAlphabetic(board[i][j])){
					if (isLowerCase(board[i][j])) {
					}
					else {
						char upper = board[i][j];
						if (!white.containsKey(upper)) white.put(upper, new ArrayList<int[]>());
						white.get(upper).add(new int[] {8 - i / 2, j / 4});
					}
				}
			}
		}
		
		for (int i =0; i < row; ++i) {
			for (int j = 0; j < col; ++j) {
				if (isAlphabetic(board[i][j])){
					if (isLowerCase(board[i][j])) {
						char low   = board[i][j];
						char upper = (char) (low - 'a' + 'A');
						if (!black.containsKey(upper)) black.put(upper, new ArrayList<int[]>());
						black.get(upper).add(new int[] {8 - i / 2, j / 4});
					}
					else {
					}
				}
			}
		}
		
		// answer
		out.println(format(white, "white"));
		out.println(format(black, "black"));
	}
	
	boolean isLowerCase(char c) {
		return c >= 'a' && c <= 'z';
	}
	
	boolean isAlphabetic(char c) {
		return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
	}
	
	String format(Map<Character, List<int[]>> map, String mod) {
		StringBuilder sb = new StringBuilder();
		if (mod.equals("white")) {
			sb.append("White: ");
		}
		else {
			sb.append("Black: ");
		}
		
		// K
		List<int[]> pos = map.get('K');
		sb.append('K' + "" + (char)(pos.get(0)[1] + 'a') + (char)(pos.get(0)[0] + '0') + ",");
		
		// Q
		pos = map.get('Q');
		sb.append('Q' + "" + (char)(pos.get(0)[1] + 'a') + (char)(pos.get(0)[0] + '0') + ",");
		
		// R
		pos = map.get('R');
		for (int i = 0; i < pos.size(); ++i) {
			sb.append('R' + "" + (char)(pos.get(i)[1] + 'a') + (char)(pos.get(i)[0] + '0') + ",");
		}
		
		// B
		pos = map.get('B');
		for (int i = 0; i < pos.size(); ++i) {
			sb.append('B' + "" + (char)(pos.get(i)[1] + 'a') + (char)(pos.get(i)[0] + '0') + ",");
		}
		
		// N
		if (map.containsKey('N')) {
			pos = map.get('N');
			for (int i = 0; i < pos.size(); ++i) {
				sb.append('N' + "" + (char)(pos.get(i)[1] + 'a') + (char)(pos.get(i)[0] + '0') + ",");
			}
		}
		
		// P
		if (map.containsKey('P')) {
			pos = map.get('P');
			for (int i = 0; i < pos.size(); ++i) {
				sb.append("" + (char)(pos.get(i)[1] + 'a') + (char)(pos.get(i)[0] + '0') + (i + 1 == pos.size() ? "" : ","));
			}
		}
		
		return sb.toString();
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

