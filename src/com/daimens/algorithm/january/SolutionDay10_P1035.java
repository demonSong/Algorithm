package com.daimens.algorithm.january;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class SolutionDay10_P1035 {
	
	String INPUT = "./data/judge/201801/P1035.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay10_P1035().run();
	}
	
	class Node implements Comparable<Node>{
		String ans;
		int key;
		
		Node(String ans, int key){
			this.ans = ans;
			this.key = key;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.key - o.key;
		}
	}
	
	void read() {
		Map<String, Integer> dicts = new HashMap<String, Integer>();
		int tot = 0;
		while (true) {
			String dict = ns();
			if (dict.equals("#")) break;
			dicts.put(dict, tot++);
		}
		while (true) {
			String query = ns();
			if (query.equals("#")) break;
			if (dicts.containsKey(query)) {
				out.println(query + " is correct");
			}
			else {
				Set<String> unique = new HashSet<String>();
				Queue<Node> ans = new PriorityQueue<Node>();
				// 删除
				int n = query.length();
				for (int i = 0; i < n; ++i) {
					String cand = query.substring(0, i) + query.substring(i + 1);
					if (dicts.containsKey(cand)) ans.add(new Node(cand, dicts.get(cand)));
				}
				// 替换
				for (int i = 0; i < n; ++i) {
					for (char c = 'a'; c <= 'z'; ++c) {
						String cand = query.substring(0, i) + c + query.substring(i + 1);
						if (dicts.containsKey(cand)) ans.add(new Node(cand, dicts.get(cand)));
					}
				}
				// 插入
				for (int i = 0; i <= n; ++i) {
					for (char c = 'a'; c <= 'z'; ++c) {
						String cand = query.substring(0, i) + c + query.substring(i);
						if (dicts.containsKey(cand)) ans.add(new Node(cand, dicts.get(cand)));
					}
				}
				
				out.print(query + ":");
				while (!ans.isEmpty()) {
					Node now = ans.poll();
					if (unique.contains(now.ans)) continue;
					unique.add(now.ans);
					out.print(" " + now.ans);
				}
				out.println();
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

