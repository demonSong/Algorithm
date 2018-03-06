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

public class SolutionDay23_P1486 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/201707/1486.txt";
	
	class Point{
		int x;
		int y;
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	class Slide{
		int minX;
		int maxX;
		int minY;
		int maxY;
		
		public Slide(int minX, int maxX, int minY, int maxY){
			this.minX = minX;
			this.maxX = maxX;
			this.minY = minY;
			this.maxY = maxY;
		}
		
		public boolean contain(Point point){
			int x = point.x;
			int y = point.y;
			return minX < x && x < maxX && minY < y && y < maxY;
		}
	}
	
	
	Slide[] slides;
	Point[] points;
	int[] sliding;
	int V;
	void solve() {
		int cnt = 0;
		while (true){
			int n = ni();
			if (n == 0) break;
			++cnt;
			V = 2 * n;
			init(V);
			
			slides = new Slide[n];
			points = new Point[n];
			
			for (int i = 0; i < n; ++i) {
				int minX = ni();
				int maxX = ni();
				int minY = ni();
				int maxY = ni();
				slides[i] = new Slide(minX, maxX, minY, maxY);
			}

			for (int i = 0; i < n; ++i) {
				int x = ni();
				int y = ni();
				points[i] = new Point(x, y);
			}
			
			sliding = new int[n];
			Arrays.fill(sliding, -1);
			for (int i = 0; i < n; ++i){
				
				for (int j = 0; j < n; ++j){
					if (slides[i].contain(points[j])){
						clear();
						for (int k = 0; k < n; ++k){
							for (int l = 0; l < n; ++l){
								if (l == j && k == i) continue;
								if (slides[k].contain(points[l])){
									addEdge(k, l + n);
								}
							}
						}
						
						if (bipartiteMatching() == n - 1){
							sliding[i] = j;
						}
					}
				}
			}
			out.println("Heap " + cnt);
			StringBuilder sb = new StringBuilder();
			boolean hasOne = false;
			for (int i = 0; i < n; ++i){
				if (sliding[i] >= 0){
					char c = (char) ('A' + i);
					sb.append("(" + c + "," + (sliding[i] + 1) + ")" + " ");
					hasOne = true;
				}
			}
			if (hasOne)
				out.println(sb.toString().substring(0, sb.length() - 1));
			else
				out.println("none");
			out.println();
		}
	}
	
	//二分图匹配
	List<Integer>[] g;
	int[] matching;
	public void init(int V){
		g = new ArrayList[V];
		for (int i = 0; i < V; ++i) g[i] = new ArrayList<Integer>();
		matching = new int[V];
	}
	
	public void clear(){
		for (int i = 0; i < V; ++i){
			g[i].clear();
		}
	}
	
	
	public void addEdge(int from, int to){
		g[from].add(to);
		g[to].add(from);
	}
	
	
	
	public boolean dfs(int v, boolean[] visited){
		visited[v] = true;
		for (int u : g[v]){
			int w = matching[u];
			if (w == -1 || !visited[w] && dfs(w, visited)){
				matching[u] = v;
				matching[v] = u;
				return true;
			}
		}
		return false;
	}
	
	public int bipartiteMatching(){
		int cnt = 0;
		Arrays.fill(matching, -1);
		for (int i = 0; i < V; ++i){
			if (matching[i] < 0){
				if (dfs(i, new boolean[V])){
					cnt ++;
				}
			}
		}
		return cnt;
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
		new SolutionDay23_P1486().run();
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


