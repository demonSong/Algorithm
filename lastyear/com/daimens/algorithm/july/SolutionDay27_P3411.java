package com.daimens.algorithm.july;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class SolutionDay27_P3411 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/201707/3411.txt";
	
	class Road{
		int a;
		int b;
		int c;
		int p;
		int r;
		public Road(int a, int b, int c, int p, int r){
			this.a = a;
			this.b = b;
			this.c = c;
			this.p = p;
			this.r = r;
		}
	}
	
	class Node implements Comparable<Node>{
		int v;
		int dist;
		int S;
		
		public Node(int v, int dist, int S){
			this.v = v;
			this.dist = dist;
			this.S = S;
		}
		
		@Override
		public int compareTo(Node that) {
			return this.dist - that.dist;
		}
		
		@Override
		public String toString() {
			return v + " " + dist + " " + S;
		}
	}
	
	static final int INF = 1 << 29;
	void solve() {
		int N = ni();
		int M = ni();
		Road[] roads = new Road[M];
		List<Road>[] g = new ArrayList[N];
		for (int i = 0; i < N; ++i) g[i] = new ArrayList<Road>();
		
		for (int i = 0; i < M; ++i){
			int a = ni() - 1;
			int b = ni() - 1;
			int c = ni() - 1;
			int p = ni();
			int r = ni();
			roads[i] = new Road(a, b, c, p, r);
			g[a].add(roads[i]);
		}
		
		boolean[][] visited = new boolean[1 << N][N];
		Node start = new Node(0, 0, 0); //顶点0 ， 在状态0下的，最短距离为0
		Queue<Node> queue = new PriorityQueue<Node>();
		queue.offer(start);
		
		int ans = INF;
		while (!queue.isEmpty()){
			Node r = queue.poll();
			if (visited[r.S][r.v]) continue;
			if (r.v == N - 1){
				ans = r.dist;
				break;
			}
			visited[r.S][r.v] = true;
			int v = r.v;
			for (Road edge : g[v]){
				int ns = r.S | 1 << edge.c | 1 << edge.a | 1 << edge.b;
				int cost = 0;
				if ((r.S & 1 << edge.c) == 0) cost = edge.r;
				else cost = Math.min(edge.r, edge.p);
				int to = edge.b;
				queue.offer(new Node(to, cost + r.dist, ns));
			}
		}
		
		if (ans == INF){
			out.println("impossible");
		}
		else{
			out.println(ans);
		}
		
	}
	
	
//	void solve() {
//		int N = ni();
//		int M = ni();
//		Road[] roads = new Road[M];
//		List<Road>[] g = new ArrayList[N];
//		for (int i = 0; i < N; ++i) g[i] = new ArrayList<Road>();
//		
//		for (int i = 0; i < M; ++i){
//			int a = ni() - 1;
//			int b = ni() - 1;
//			int c = ni() - 1;
//			int p = ni();
//			int r = ni();
//			roads[i] = new Road(a, b, c, p, r);
//			g[a].add(roads[i]);
//		}
//		int[][] distance = new int[1 << N][N];
//		for (int i = 0; i < 1 << N; ++i) Arrays.fill(distance[i], INF);
//		
//		distance[0][0] = 0;
//		for (int i = 0; i < N; ++i){
//			for (int s = 0; s < 1 << N; ++s){
//				for (int v = 0; v < N; ++v){
//					for (Road r : g[v]){
//						int ns = s | 1 << r.a | 1 << r.b | 1 << r.c;
//						int cost = 0;
//						if ((s & (1 << r.c)) == 0) cost = r.r;
//						else cost = Math.min(r.r, r.p);
//						if (distance[s][v] != INF && distance[ns][r.b] > distance[s][v] + cost){
//							distance[ns][r.b] = distance[s][v] + cost;
//						}
//					}
//				}
//			}
//		}
//		int min = INF;
//		for (int s = 0; s < 1 << N; ++s){
//			min = Math.min(min, distance[s][N - 1]);
//		}
//		if (min == INF) out.println("impossible");
//		else out.println(min);
//	}
	
	void run() throws Exception {
		is = oj ? System.in : new FileInputStream(new File(INPUT));
		out = new PrintWriter(System.out);

		long s = System.currentTimeMillis();
		solve();
		out.flush();
		tr(System.currentTimeMillis() - s + "ms");
	}

	public static void main(String[] args) throws Exception {
		new SolutionDay27_P3411().run();
	}

	private byte[] inbuf = new byte[1024];
	public int lenbuf = 0, ptrbuf = 0;

	private int readByte() {
		if (lenbuf == -1)
			throw new InputMismatchException();
		if (ptrbuf >= lenbuf) {
			ptrbuf = 0;
			try {
				lenbuf = is.read(inbuf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (lenbuf <= 0)
				return -1;
		}
		return inbuf[ptrbuf++];
	}

	private boolean isSpaceChar(int c) {
		return !(c >= 33 && c <= 126);
	}

	private int skip() {
		int b;
		while ((b = readByte()) != -1 && isSpaceChar(b))
			;
		return b;
	}

	private double nd() {
		return Double.parseDouble(ns());
	}

	private char nc() {
		return (char) skip();
	}

	private String ns() {
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b != '
									// ')
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}

	private char[] ns(int n) {
		char[] buf = new char[n];
		int b = skip(), p = 0;
		while (p < n && !(isSpaceChar(b))) {
			buf[p++] = (char) b;
			b = readByte();
		}
		return n == p ? buf : Arrays.copyOf(buf, p);
	}

	private char[][] nm(int n, int m) {
		char[][] map = new char[n][];
		for (int i = 0; i < n; i++)
			map[i] = ns(m);
		return map;
	}

	private int[] na(int n) {
		int[] a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = ni();
		return a;
	}

	private int ni() {
		int num = 0, b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
		if (b == '-') {
			minus = true;
			b = readByte();
		}

		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = readByte();
		}
	}

	private long nl() {
		long num = 0;
		int b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
		if (b == '-') {
			minus = true;
			b = readByte();
		}

		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = readByte();
		}
	}

	private boolean oj = System.getProperty("ONLINE_JUDGE") != null;

	private void tr(Object... o) {
		if (!oj)
			System.out.println(Arrays.deepToString(o));
	}
}
