package com.daimens.algorithm.june;

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

/**
 * 
 * @author DemonSong
 * 
 *         3662. Telephone Lines
 * 
 *         Description
 * 
 *         Farmer John wants to set up a telephone line at his farm.
 *         Unfortunately, the phone company is uncooperative, so he needs to pay
 *         for some of the cables required to connect his farm to the phone
 *         system.
 * 
 *         There are N (1 ≤ N ≤ 1,000) forlorn telephone poles conveniently
 *         numbered 1..N that are scattered around Farmer John's property; no
 *         cables connect any them. A total of P (1 ≤ P ≤ 10,000) pairs of poles
 *         can be connected by a cable; the rest are too far apart.
 * 
 *         The i-th cable can connect the two distinct poles Ai and Bi, with
 *         length Li (1 ≤ Li ≤ 1,000,000) units if used. The input data set
 *         never names any {Ai, Bi} pair more than once. Pole 1 is already
 *         connected to the phone system, and pole N is at the farm. Poles 1 and
 *         N need to be connected by a path of cables; the rest of the poles
 *         might be used or might not be used.
 * 
 *         As it turns out, the phone company is willing to provide Farmer John
 *         with K (0 ≤ K < N) lengths of cable for free. Beyond that he will
 *         have to pay a price equal to the length of the longest remaining
 *         cable he requires (each pair of poles is connected with a separate
 *         cable), or 0 if he does not need any additional cables.
 * 
 *         Determine the minimum amount that Farmer John must pay.
 * 
 *         Input
 * 
 *         Line 1: Three space-separated integers: N, P, and K Lines 2..P+1:
 *         Line i+1 contains the three space-separated integers: Ai, Bi, and Li
 * 
 *         Output
 * 
 *         Line 1: A single integer, the minimum amount Farmer John can pay. If
 *         it is impossible to connect the farm to the phone company, print -1.
 * 
 *         Sample Input
 * 
 *         5 7 1 1 2 5 3 1 4 2 4 8 3 2 3 5 2 9 3 4 7 4 5 6 Sample Output
 * 
 *         4
 *
 */
public class SolutionDay26_P3662 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/3662.txt";

	class Edge{
		int from;
		int to;
		int cost;
		
		public Edge(int from, int to, int cost){
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		
		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", cost=" + cost + "]";
		}
	}
	
	class Node implements Comparable<Node>{
		
		int id;
		int cost;
		
		public Node(int id, int cost){
			this.id = id;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
	
	int[] dist;
	int INF = 1 << 29;
	void solve() {
		int N = ni();
		int P = ni();
		int K = ni();
		dist = new int[N];
		List<Edge>[] graph = new ArrayList[N];
		for (int i = 0; i < N; ++i) graph[i] = new ArrayList<Edge>();
		int max = 0;
		for (int i = 0; i < P; ++i){
			int from = ni();
			int to = ni();
			int cost = ni();
			max = Math.max(max, cost);
			from--;
			to--;
			graph[from].add(new Edge(from, to, cost));
			graph[to].add(new Edge(to, from, cost));
		}
		
		int lb = 0, ub = max;
		while (lb < ub){
			int mid = lb + (ub - lb) / 2;
			if (dijkstra(graph, 0, mid) > K) lb = mid + 1;
			else ub = mid;
		}
		if (dijkstra(graph, 0, lb) <= K) out.println(lb);
		else out.println(-1);
	}
	
	public int dijkstra(List<Edge>[] graph, int i, int mid){
		int N = graph.length;
		boolean[] visited = new boolean[N];
		Queue<Node> queue = new PriorityQueue<Node>();
		Arrays.fill(dist, INF);
		dist[i] = 0;
		queue.offer(new Node(i, dist[i]));
		while (!queue.isEmpty()){
			Node min = queue.poll();
			if (visited[min.id]) continue;
			visited[min.id] = true;
			for (Edge edge : graph[min.id]){
				int newEdge = (edge.cost > mid ? 1 : 0) + dist[edge.from];
				if (newEdge <= dist[edge.to]){
					dist[edge.to] = newEdge;
					queue.offer(new Node(edge.to, dist[edge.to]));
				}
			}
		}
		return dist[N-1];
	}
	
	
	void run() throws Exception {
		is = oj ? System.in : new FileInputStream(new File(INPUT));
		out = new PrintWriter(System.out);

		long s = System.currentTimeMillis();
		solve();
		out.flush();
		tr(System.currentTimeMillis() - s + "ms");
	}

	public static void main(String[] args) throws Exception {
		new SolutionDay26_P3662().run();
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

