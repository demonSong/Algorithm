package com.daimens.algorithm.january;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class SolutionDay03_P3026 {
	
	static Scanner in;
	public static void main(String[] args) throws IOException {
		in = new Scanner(System.in);
		new SolutionDay03_P3026().run();
	}
	
	static final int INF = 0x3f3f3f3f;
	
	char[][] map;
	int[][] dist;
	
	void run() {
		int T = in.nextInt();
		while (T --> 0) {
			String s = in.nextLine();
			while (s.isEmpty()) s = in.nextLine();
			String[] ss = s.split(" ");
			int x = Integer.parseInt(ss[0]);
			int y = Integer.parseInt(ss[1]);
			map = new char[y][x];
			int i = 0;
			while(true) {
				if (i == y) break;
				String ns = in.nextLine();
				if (ns.isEmpty()) continue;
				if (i == 0 || i == y - 1) {
					for (int j = 0; j < x; ++j) {
						map[i][j] = '#';
					}
				}
				else {
					for (int j = 0; j < x; ++j) {
						map[i][j] = ns.charAt(j);
					}
				}
				i ++;
			}
			int V = x * y;
			dist = new int[V][V];
			for (int j = 0; j < V; ++j) Arrays.fill(dist[j], INF);
			
			int S = -1;
			Set<Integer> isV = new HashSet<Integer>();
			for (i = 0; i < y; ++i) {
				for (int j = 0; j < x; ++j) {
					if (map[i][j] == 'S') {
						S = i * x + j;
						isV.add(S);
						bfs(S, y, x);
					}
					else if (map[i][j] == 'A'){
						bfs(i * x + j, y, x);
						isV.add(i * x + j);
					}
				}
			}
			
			// prime
			int[] mincost = new int[V];
			Arrays.fill(mincost, INF);
			mincost[S] = 0;
			boolean[] vis = new boolean[V];
			for (i = 0; i < V; ++i) {
				if (!isV.contains(i)) vis[i] = true;
			}
			
			int sum = 0;
			while (true) {
				int v = -1;
				for (int u = 0; u < V; ++u) {
					if (!vis[u] && (v == -1 || mincost[v] > mincost[u])) v = u;
				}
				if (v == -1) break;
				sum += mincost[v];
				vis[v] = true;
				for (int u = 0; u < V; ++u) {
					mincost[u] = Math.min(mincost[u], dist[v][u]);
				}
			}
			
			System.out.println(sum);
		}
	}
	
	void bfs(int S, int row, int col) {
		Queue<Integer> queue = new ArrayDeque<Integer>();
		Set<Integer> vis = new HashSet<Integer>();
		queue.offer(S);
		vis.add(S);
		
		int[][] dir = {{1, 0},{-1, 0},{0, 1},{0, -1}};
		int diff = 0;
		
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; ++i) {
				int now = queue.poll();
				int x = now / col;
				int y = now % col;
				if (map[x][y] == 'A') {
					dist[S][now] = diff;
					dist[now][S] = diff;
				}
				for (int[] d : dir) {
					int nx = x + d[0];
					int ny = y + d[1];
					if (nx >= 0 && nx < row && ny >= 0 && ny < col && !vis.contains(nx * col + ny) && map[nx][ny] != '#') {
						queue.offer(nx * col + ny);
						vis.add(nx * col + ny);
					}
				}
			}
			diff ++;
		}
	}
}

