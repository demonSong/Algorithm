package com.daimens.algorithm.september;

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
import java.util.StringTokenizer;

public class SolutionDay13_A2212 {
	
	String INPUT = "./data/judge/201709/A2212.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay13_A2212().run();
	}
	
	static class Node{
		Node[] next = new Node[4];
		long pos;
		int idx;  // AC自动机的状态
		Node (long pos) {
			this.pos = pos;
		}
	}
	
	static class Automaton{
		static final Node terminal = new Node(0L);
		Node root = new Node(0L); // 当前位置？
        HashMap<Long, Node> map = new HashMap<Long, Node>();
        ArrayList<Node> nodes = new ArrayList<Node>();
        int[][] pattern;
 
        Automaton(String[] patStr) {
            pattern = new int[patStr.length][];
            for (int i = 0; i < patStr.length; ++i) {
                pattern[i] = new int[patStr[i].length()];
                for (int j = 0; j < pattern[i].length; ++j) {
                    switch (patStr[i].charAt(j)) {
                    case 'U':
                        pattern[i][j] = 0;
                        break;
                    case 'R':
                        pattern[i][j] = 1;
                        break;
                    case 'D':
                        pattern[i][j] = 2;
                        break;
                    case 'L':
                        pattern[i][j] = 3;
                        break;
                    }
                }
            }
            build(root);
        }
 
        void build(Node parent) {
            parent.idx = nodes.size(); //  0  迭代的做法
            nodes.add(parent); // size = 1
            map.put(parent.pos, parent); // 建立 位置与nodes的索引
            for (int i = 0; i < 4; ++i) {
                long nextPos = 0;
                for (int j = 0; j < pattern.length; ++j) {
                    int curElemPos = (int) ((parent.pos >> (j * 4)) & 0xF);
                    long nextElemPos;
                    if (pattern[j][curElemPos] == i) {
                        nextElemPos = curElemPos + 1;
                    } 
                    else {
                        nextElemPos = 0;
                        for (int k = curElemPos; k >= 0; --k) {
                            if (pattern[j][k] != i) continue;
                            boolean ok = true;
                            for (int l = 1; l <= k; ++l) {
                                if (pattern[j][curElemPos - l] != pattern[j][k - l]) {
                                    ok = false;
                                    break;
                                }
                            }
                            if (ok) {
                                nextElemPos = k + 1;
                                break;
                            }
                        }
                    }
                    if (nextElemPos == pattern[j].length) {
                        parent.next[i] = terminal;
                        break;
                    } 
                    else {
                        nextPos += nextElemPos << (j * 4);
                    }
                }
                if (parent.next[i] == null) {
                    Node child = map.get(nextPos);
                    if (child == null) {
                        child = new Node(nextPos);
                        build(child);
                    }
                    parent.next[i] = child;
                }
            }
        }
	}
	
	class State{
		int r, c, i;
		
		State(int r, int c, int i){
			this.r = r;
			this.c = c;
			this.i = i;
		}
	}
	
	static int[] DR = {-1, 0, 1, 0};
	static int[] DC = {0, 1, 0, -1};
	int N, M;
	char[][] f;
	
	void read() {
		while (true) {
			N = ni();
			if (N == 0) break;
			M = ni();
			f = new char[N + 2][M + 2];
			int startR = 0;
			int startC = 0;
			Arrays.fill(f[0], '#');
			Arrays.fill(f[N + 1], '#');
			for (int i = 0; i < N; ++i) {
                String row = ns();
                f[i + 1][0] = f[i + 1][M + 1] = '#';
                for (int j = 0; j < M; ++j) {
                    char c = row.charAt(j);
                    if (c == 'S') {
                        startR = i + 1;
                        startC = j + 1;
                        c = '.';
                    }
                    f[i + 1][j + 1] = c;
                }
            }
			out.println(solve(startR, startC));
		}
	}
	
	void pp(char[][] f) {
		int n = f.length;
		int m = f[0].length;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				sb.append(f[i][j] + (j + 1 == m ? "\n" : " "));
			}
		}
		out.println(sb.toString());
	}
	
	int solve(int startR, int startC) {
        int P = ni();
        String[] pat = new String[P];
        for (int i = 0; i < P; ++i) {
            pat[i] = ns();
        }
        Automaton automaton = new Automaton(pat);
        boolean[][][] visited = new boolean[N + 2][M + 2][automaton.nodes.size()]; // 多了个状态
        State initial = new State(startR, startC, 0);
        List<State> cur = new ArrayList<State>();
        cur.add(initial);
        visited[startR][startC][0] = true;
        int turn = 1;
        while (!cur.isEmpty()) {
        	ArrayList<State> next = new ArrayList<State>();
            for (State st : cur) {
                for (int i = 0; i < 4; ++i) {
                    int nr = st.r + DR[i];
                    int nc = st.c + DC[i];
                    if (f[nr][nc] == '#') continue;
                    Node n = automaton.nodes.get(st.i).next[i];
                    if (n == Automaton.terminal) continue;
                    if (visited[nr][nc][n.idx]) continue;
                    if (f[nr][nc] == 'G') return turn;
                    visited[nr][nc][n.idx] = true;
                    next.add(new State(nr, nc, n.idx));
                }
            }
            cur = next;
            ++turn;
        }
        return -1;
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


