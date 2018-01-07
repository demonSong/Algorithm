package com.daimens.algorithm.december;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class SolutionDay13_P2965 {
	
	String INPUT = "./data/judge/201712/P2965.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay13_P2965().run();
	}
	
	static final int[][] MAP = {{0, 1, 2, 3}, {4, 5, 6, 7}, {8, 9, 10, 11}, {12, 13, 14, 15}};
	
	int hash(char[][] board) {
		int n = 4;
		int k = 0;
		int ans = 0;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				int bit = board[i][j] == '+' ? 0 : 1;
				ans |= bit << k;
				k ++;
			}
		}
		return ans;
	}
	
	void read() {
		int n = 4;
		char[][] board = new char[n][n];
		for (int i = 0; i < n; ++i) {
			String line = ns();
			for (int j = 0; j < n; ++j) {
				board[i][j] = line.charAt(j); 
			}
		}
		
		int END = 65535;
		int hash = hash(board);
		
		int ans = 0;
		int len = 0;
		for (int i = 0; i < 1 << 16; ++i) {
			int count = 0;
			int tmp = hash;
			for (int j = 0; j < 16; ++j) {
				if (((i >> j) & 1) == 1) {
					count ++;
					tmp = flip(tmp, j / n, j % n);
				}
			}
			
			if (tmp == END) {
				len = count;
				ans = i;
				break;
			}
		}
		
		out.println(len);
		for (int j = 0; j < 16; ++j) {
			if (((ans >> j) & 1) == 1) {
				out.println((j / n + 1) + " " + (j % n + 1));
			}
		}
		
		System.out.println(contains("abbbbbcdd", "abcdd"));
	}
	
	boolean contains(String subStr, String t){
        int[] map1 = new int[128];
        for (char c : t.toCharArray()) map1[c - 'A'] ++;
        
        int[] map2 = new int[128];
        for (char c : subStr.toCharArray()) map2[c - 'A'] ++;
    
        for (int i = 0; i < 128; ++i){
            if (map1[i] != 0){
                if (map1[i] > map2[i]) return false;
            }
        }
        
        return true;
    }
	
	int flip(int hash, int x, int y){
		int n = 4;
		int ans = hash;
		
		for (int i = 0; i < n; ++i) {
			ans ^= (1 << MAP[i][y]);
			ans ^= (1 << MAP[x][i]);
		}
		ans ^= (1 << MAP[x][y]);
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