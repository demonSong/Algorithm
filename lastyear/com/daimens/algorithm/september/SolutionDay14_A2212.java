package com.daimens.algorithm.september;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class SolutionDay14_A2212 {
	
	String INPUT = "./data/judge/201709/A2212.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay14_A2212().run();
	}
	
	static final char[] TAG = {'D','U','L','R'};
	static final int[] R = {1, -1, 0, 0};
	static final int[] C = {0, 0, -1, 1};
	static final int INF = 0x3f3f3f3f;
	
	static int idx = 0;
	
	class State{
		int i;
		int j;
		Node s;
		State(int i, int j, Node s){
			this.i = i;
			this.j = j;
			this.s = s;
		}
		
		@Override
		public int hashCode() {
			return i * 31 + j * 17 + s.id * 11;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj == null ||obj.getClass() != getClass()) return false;
			State o = (State) obj;
			return o.i == i && o.j == j && o.s.id == s.id;
		}
	}
	
	class Node{
		Map<Character, Node> cs = new TreeMap<Character, Node>();
		Node[] next = new Node[4];
		Node fail;
		boolean accept;
		int id;
		
		Node() {id = idx++;}
	}
	
	Node root;
	void AhoCorasick(char[][] p) {
		int n = p.length;
		root = new Node();
		for (int i = 0; i < n; ++i) {
			Node t = root;
			for (char c : p[i]) {
				if (!t.cs.containsKey(c)) t.cs.put(c, new Node());
				t = t.cs.get(c);
			}
			t.accept = true;
		}
		
		// fail
		Queue<Node> que = new LinkedList<Node>();
		que.offer(root);
		while (!que.isEmpty()) {
			Node r = que.poll();
			for (Map.Entry<Character, Node> entry : r.cs.entrySet()) {
				char c = entry.getKey();
				Node v = entry.getValue();
				que.offer(v);
				Node f = r.fail;
				while (f != null && !f.cs.containsKey(c)) {
					f = f.fail;
				}
				if (f == null) {
					v.fail = root;
				}
				else {
					v.fail = f.cs.get(c);
				}
			}
		}
		
		// next
		que.offer(root);
		while (!que.isEmpty()) {
			Node t = que.poll();
			outer : for (char[] cs : p) {
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
			for (Node r : t.cs.values()) {
				que.offer(r);
			}
		}
	}
	
	int startR;
	int startC;
	int endR, endC;
	
	int solve(char[][] f) {
		int N = f.length;
		int M = f[0].length;
		int n = ni();
		char[][] p = new char[n][];
		for (int i = 0; i < n; ++i) {
			p[i] = ns().toCharArray();
		}
		
		AhoCorasick(p);
		
		State state = new State(startR, startC, root);
		Queue<State> queue = new LinkedList<>();
		Set<State> vis = new HashSet<>();
		queue.offer(state);
		vis.add(state);
		
		int turn = 0;
		int ans  = INF;
		boolean find = false;
		outer: while (!queue.isEmpty() && !find) {
			int size = queue.size();
			for (int i = 0; i < size; ++i) {
				State now = queue.poll();
				if (now.i == endR && now.j == endC) {
					ans = turn;
					find = true;
					break outer;
				}
				for (int j = 0; j < 4; ++j) {
					int nx = now.i + R[j];
					int ny = now.j + C[j];
					if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
					if (f[nx][ny] == '#') continue;
					Node next = now.s.next[j];
					if (next.accept) continue;
					State ns = new State(nx, ny, next);
					if (vis.contains(ns)) continue;
					queue.offer(ns);
					vis.add(ns);
				}
			}
			turn++;
		}
		return ans == INF ? -1 : ans;
	}
	
	void read() {
		while (true) {
			int N = ni();
			if (N == 0) break;
			int M = ni();
			char[][] f = new char[N][M];
			for (int i = 0; i < N; ++i) {
				char[] cs = ns().toCharArray();
				for (int j = 0; j < M; ++j) {
					f[i][j] = cs[j];
					if (cs[j] == 'S') {
						startR = i;
						startC = j;
					}
					if (cs[j] == 'G') {
						endR = i;
						endC = j;
					}
				}
			}
			idx = 0;
			out.println(solve(f));
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


