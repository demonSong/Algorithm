package com.daimens.algorithm.september;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class SolutionDay14_P3691 {
	
	String INPUT = "./data/judge/201709/P3691.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay14_P3691().run();
	}
	
	static final int INF = 0x3f3f3f3f;
	static final char[] TAG = {'A','G','C','T'};
	Node root;
	int m;
	void AhoCorasick(char[][] ps) {
		m = ps.length;
		root = new Node();
		for (int i = 0; i < m; ++i) {
			Node t = root;
			for (char c : ps[i]) {
				if (!t.cs.containsKey(c)) t.cs.put(c, new Node());
				t = t.cs.get(c);
			}
			t.accept = true;
		}
		
		Queue<Node> que = new LinkedList<Node>();
		que.offer(root);
		while (!que.isEmpty()) {
			Node t = que.poll();
			for (Map.Entry<Character, Node> e : t.cs.entrySet()) {
				char c = e.getKey();
				Node u = e.getValue();
				que.offer(u);
				Node r = t.fail;
				while (r != null && !r.cs.containsKey(c)) {
					r = r.fail;
				}
				if (r == null) {
					u.fail = root;
				}
				else {
					u.fail = r.cs.get(c);
				}
			}
		}
		
		que.offer(root);
		while (!que.isEmpty()) {
			Node t = que.poll();
			outer : for (char[] cs : ps) {
				Node r = t;
				for (char c : cs) {
					if (r.cs.containsKey(c)) {
						r = r.cs.get(c);
					}
					else continue outer;
				}
				r.accept = true;
			}
			for (int i = 0; i < 4; ++i) {
				char c = TAG[i];
				Node r = t;
				while (r != null && !r.cs.containsKey(c)) {
					r = r.fail;
				}
				if (r == null) {
					t.next[i] = root;
				}
				else {
					t.next[i] = r.cs.get(c);
				}
			}
			
			for (Map.Entry<Character, Node> e : t.cs.entrySet()) {
				Node u = e.getValue();
				que.offer(u);
			}
		}
		
	}
	
	static int idx = 0;
	
	class Node{
		Map<Character, Node> cs = new TreeMap<Character, Node>();
		boolean accept;
		Node fail;
		Node[] next = new Node[4];
		int id;
		
		Node() {
			this.id = idx++;
		}
		
		@Override
		public String toString() {
			return id + "";
		}
	}
	
	int solve(String match, String[] p) {
		char[][] css = new char[p.length][];
		for (int i = 0; i < p.length; ++i) {
			css[i] = p[i].toCharArray();
		}
		AhoCorasick(css);
		char[] cs = match.toCharArray();
		int[][] dp = new int[cs.length + 4][idx + 4];
		dp[0][0] = 1;
		for (int i = 1; i < idx; ++i) dp[0][i] = 0;
		for (int i = 1; i <= cs.length; ++i) { //四种状态都要走一遭
			for (int j = 0; j < idx; ++j) dp[i][j] = INF;
			
		}
		
		return 0;
	}
	
	void read() {
		int cnt = 0;
		while (true) {
			int N = ni();
			if (N == 0) break;
			String[] p = new String[N];
			for (int i = 0; i < N; ++i) p[i] = ns();
			String match = ns();
			idx = 0;
			out.println("Case " + (++cnt) + ": " + solve(match, p));
		}
	}

	FastScanner in;
	PrintWriter out;
	
	void run() throws IOException {
		boolean oj;
		try {
			oj = ! System.getProperty("user.dir").equals("F:\\java_workspace\\leetcode");
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
}

