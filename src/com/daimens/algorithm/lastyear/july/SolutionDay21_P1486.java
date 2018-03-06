package com.daimens.algorithm.july;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SolutionDay21_P1486 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/201707/1486.txt";
	
	void solve() {
		int cnt = 0;
		while (true){
			int n = ni();
			if (n == 0) break;
			cnt ++;
			int[][] pos = new int[n][4];
			for (int i = 0; i < n; ++i){
				for (int j = 0; j < 4; ++j){
					pos[i][j] = ni();
				}
			}
			
			int[][] pages = new int[n][2];
			for (int i = 0; i < n; ++i){
				for (int j = 0; j < 2; ++j){
					pages[i][j] = ni();
				}
			}
			out.println("Heap " + cnt);
			solve(pos, pages);
		}
	}
	
	List<Integer>[] g;
	public void solve(int[][] pos, int[][] pages){
		int V = 2 * pos.length;
		g = new ArrayList[V];
		for (int i = 0; i < V; ++i){
			g[i] = new ArrayList<Integer>();
		}
		
		int[] degree = new int[pos.length];
		for (int i = 0; i < pos.length; ++i){
			int minX = pos[i][0];
			int maxX = pos[i][1];
			int minY = pos[i][2];
			int maxY = pos[i][3];
			for (int j = 0; j < pages.length; ++j){
				int x = pages[j][0];
				int y = pages[j][1];
				if (x > minX &&  x < maxX && y > minY && y < maxY){
					addEdge(i, j + pos.length);
					degree[i]++;
				}
			}
		}
		
		//图构造完毕,统计出度为1的边，加入队列
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < pos.length; ++i){
			if (degree[i] == 1){
				queue.offer(i);
			}
		}
		
		if (queue.isEmpty()) out.println("none");
		else{ 
			int[] matching = new int[V];
			Arrays.fill(matching, -1);
			while (!queue.isEmpty()){
				int size = queue.size();
				for (int i = 0; i < size; ++i){
					int v = queue.poll();
					for (int u : g[v]){
						if (matching[v] < 0 && matching[u] < 0){
							matching[v] = u;
							matching[u] = v;
						}
					}
				}

				degree = new int[pos.length];
				for (int i = 0; i < pos.length; ++i){
					for (int u : g[i]){
						if (matching[u] < 0) degree[i] ++;
					}
					if (degree[i] == 1 && matching[i] < 0) queue.offer(i);
				}
			}
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < pos.length; ++i){
				if (matching[i] < 0) continue;
				char c = (char) ('A' + i);
				sb.append("(" + c + "," + (matching[i] - pos.length + 1) + ")" + " ");
			}
			out.println(sb.toString().substring(0, sb.length() - 1));
		}
		out.println();
	}
	
	public void addEdge(int from, int to){
		g[from].add(to);
		g[to].add(from);
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
		new SolutionDay21_P1486().run();
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


