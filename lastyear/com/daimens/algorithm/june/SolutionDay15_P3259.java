package com.daimens.algorithm.june;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author DemonSong
 * 
 *         3259.WormHoles
 * 
 *         Description
 * 
 *         While exploring his many farms, Farmer John has discovered a number
 *         of amazing wormholes. A wormhole is very peculiar because it is a
 *         one-way path that delivers you to its destination at a time that is
 *         BEFORE you entered the wormhole! Each of FJ's farms comprises N (1 ≤
 *         N ≤ 500) fields conveniently numbered 1..N, M (1 ≤ M ≤ 2500) paths,
 *         and W (1 ≤ W ≤ 200) wormholes.
 * 
 *         As FJ is an avid time-traveling fan, he wants to do the following:
 *         start at some field, travel through some paths and wormholes, and
 *         return to the starting field a time before his initial departure.
 *         Perhaps he will be able to meet himself :) .
 * 
 *         To help FJ find out whether this is possible or not, he will supply
 *         you with complete maps to F (1 ≤ F ≤ 5) of his farms. No paths will
 *         take longer than 10,000 seconds to travel and no wormhole can bring
 *         FJ back in time by more than 10,000 seconds.
 * 
 *         Input
 * 
 *         Line 1: A single integer, F. F farm descriptions follow. Line 1 of
 *         each farm: Three space-separated integers respectively: N, M, and W
 *         Lines 2..M+1 of each farm: Three space-separated numbers (S, E, T)
 *         that describe, respectively: a bidirectional path between S and E
 *         that requires T seconds to traverse. Two fields might be connected by
 *         more than one path. Lines M+2..M+W+1 of each farm: Three
 *         space-separated numbers (S, E, T) that describe, respectively: A one
 *         way path from S to E that also moves the traveler back T seconds.
 *         Output
 * 
 *         Lines 1..F: For each farm, output "YES" if FJ can achieve his goal,
 *         otherwise output "NO" (do not include the quotes). Sample Input
 * 
 *         2 3 3 1 1 2 2 1 3 4 2 3 1 3 1 3 3 2 1 1 2 3 2 3 4 3 1 8 Sample Output
 * 
 *         NO YES
 *
 */
public class SolutionDay15_P3259 {
	
	static class Edge{
		int from;
		int to;
		int cost;
		
		public String toString(){
			return from + "->" + to + " (" + cost + ")";
		}
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int F = in.nextInt();
		while (F-- != 0){
			int V = in.nextInt();
			int p = in.nextInt();
			int w = in.nextInt();
			Edge[] edges = new Edge[2 * p + w];
			int E = 0;
			for (int i = 0; i < p; ++i){
				int from = in.nextInt();
				int to = in.nextInt();
				int cost = in.nextInt();
				from--;
				to--;
				
				Edge edge = new Edge();
				edge.from = from;
				edge.to = to;
				edge.cost = cost;
				edges[E++] = edge;
				
				edge = new Edge();
				edge.from = to;
				edge.to = from;
				edge.cost = cost;
				edges[E++] = edge;
			}
			
			for (int i = 0; i < w; ++i){
				int from = in.nextInt();
				int to = in.nextInt();
				int cost = in.nextInt();
				from--;
				to--;
				cost = -cost;
				
				Edge edge = new Edge();
				edge.from = from;
				edge.to = to;
				edge.cost = cost;
				edges[E++] = edge;
			}
			
			System.out.println(findNegativeCycle(edges, V) ? "YES" : "NO");
		}
	}
	
	static final int INF = 1 << 29;
	private static boolean findNegativeCycle(Edge[] edges, int V){
		int[] d = new int[V];
		for (int i = 0; i < V; ++i){
			for (Edge e : edges){
				if(e.cost + d[e.from] < d[e.to]){
					d[e.to] = e.cost + d[e.from];
					if (i == V - 1) return true;
				}
			}
		}
		return false;
	}
	
	static class Scanner {

		private BufferedReader br;
		private StringTokenizer tok;

		public Scanner(InputStream is) throws IOException {
			br = new BufferedReader(new InputStreamReader(is));
			getLine();
		}

		private void getLine() throws IOException {
			while (tok == null || !tok.hasMoreTokens()) {
				tok = new StringTokenizer(br.readLine());
			}
		}

		private boolean hasNext() {
			return tok.hasMoreTokens();
		}

		public String next() throws IOException {
			if (hasNext()) {
				return tok.nextToken();
			} else {
				getLine();
				return tok.nextToken();
			}
		}

		public int nextInt() throws IOException {
			if (hasNext()) {
				return Integer.parseInt(tok.nextToken());
			} else {
				getLine();
				return Integer.parseInt(tok.nextToken());
			}
		}

		public long nextLong() throws IOException {
			if (hasNext()) {
				return Long.parseLong(tok.nextToken());
			} else {
				getLine();
				return Long.parseLong(tok.nextToken());
			}
		}

		public double nextDouble() throws IOException {
			if (hasNext()) {
				return Double.parseDouble(tok.nextToken());
			} else {
				getLine();
				return Double.parseDouble(tok.nextToken());
			}
		}
	}
}
