package com.daimens.algorithm.september;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

public class SolutionDay10_L0675 {
	
//	public int cutOffTree(List<List<Integer>> forest) {
//        N = forest.size();
//        if (N == 0) return -1;
//        M = forest.get(0).size();
//        if (M == 0) return -1;
//        int[][] arra = new int[forest.size()][forest.get(0).size()];
//        for (int i = 0; i < forest.size(); ++i) {
//        	for (int j = 0; j < forest.get(i).size(); ++j) {
//        		arra[i][j] = forest.get(i).get(j);
//        	}
//        }
//        
//        arra[0][0] = 1;
//        int count = dfs(arra, 0, 0);
//        for (int i = 0; i < N; ++i) {
//        	for (int j = 0; j < M; ++j) {
//        		if (arra[i][j] > 1) return -1;
//        	}
//        }
//        return count;
//    }
//	
//	static final int[][] dir = {{1, 0},{-1, 0},{0, 1},{0, -1}};
//	int N, M;
//	
//	class Pair{
//		int[] pos;
//		int val;
//		public Pair(int[] pos, int val) {
//			this.pos = pos;
//			this.val = val;
//		}
//	}
//	public int dfs(int[][] arra, int i, int j) {
//		int count = 0;
//		List<Pair> ans = new ArrayList<Pair>();
//		for (int[] d : dir) {
//			int nx = d[0] + i;
//			int ny = d[1] + j;
//			if (nx >= 0 && nx < N && ny >= 0 && ny < M && arra[nx][ny] > 1) {
//				ans.add(new Pair(new int[] {nx, ny}, arra[nx][ny]));
//			}
//		}
//		
//		Collections.sort(ans, (a, b) -> a.val - b.val);
//		int prev = 0;
//		for (int h = 0; h < ans.size(); ++h) {
//			int[] pos = ans.get(h).pos;
//			arra[pos[0]][pos[1]] = 1;
//			int next = dfs(arra, pos[0], pos[1]);
//			count = 1 + next + prev;
//			prev = next;
//		}
//		return count;
//	}
	
//	public int cutOffTree(List<List<Integer>> forest) {
//    	int n = forest.size();
//    	if(n == 0)return 0;
//    	int m = forest.get(0).size();
//    	if(m == 0)return 0;
//        int[][] map = new int[n][m];
//        int[][] hs = new int[n*m+1][];
//        int p = 0;
//        for(int i = 0;i < n;i++){
//        	for(int j = 0;j < m;j++){
//        		map[i][j] = forest.get(i).get(j);
//        		if(map[i][j] > 1){
//        			hs[p++] = new int[]{map[i][j], i, j};
//        		}
//        	}
//        }
//        hs[p++] = new int[]{0, 0, 0};
//        
//        hs = Arrays.copyOf(hs, p);
//        Arrays.sort(hs, new Comparator<int[]>() {
//			public int compare(int[] a, int[] b) {
//				return a[0] - b[0];
//			}
//		});
//        
//        int ret = 0;
//        for(int i = 0;i < p-1;i++){
//	        int[][] d = distMap(map, hs[i][1], hs[i][2]);
//	        int v = d[hs[i+1][1]][hs[i+1][2]];
//	        if(v > 10000)return -1;
//	        ret += v;
//        }
//        return ret;
//    }
//    
//	public int[][] distMap(int[][] map, int sr, int sc)
//	{
//		int[] dr = { 1, 0, -1, 0 };
//		int[] dc = { 0, 1, 0, -1 };
//		int n = map.length;
//		if(n == 0)return new int[0][0];
//		int m = map[0].length;
//		int l = dr.length;
//		int[][] d = new int[n][m];
//		int I = 999999999;
//		for(int i = 0;i < n;i++) {
//			Arrays.fill(d[i], 999999999);
//		}
//		
//		int B = 6;
//		Queue<Integer> q = new ArrayDeque<Integer>();
//		q.add(sr<<B|sc);
//		d[sr][sc] = 0;
//		
//		while(!q.isEmpty()){
//			int cur = q.poll();
//			int r = cur>>>B, c = cur&(1<<B)-1;
//			for(int k = 0;k < l;k++) {
//				int nr = r + dr[k];
//				int nc = c + dc[k];
//				if(nr >= 0 && nr < n && nc >= 0 && nc < m && map[nr][nc] != 0 && d[nr][nc] == I) { // fix
//					d[nr][nc] = d[r][c] + 1;
//					q.add(nr<<B|nc);
//				}
//			}
//		}
//		return d;
//	}
	
	public int cutOffTree(List<List<Integer>> forest) {
		int n = forest.size();
		if (n == 0) return 0;
		int m = forest.get(0).size();
		if (m == 0) return 0;
		int[][] map = new int[n][m];
		
		TreeMap<Integer, int[]> mem = new TreeMap<>();
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				map[i][j] = forest.get(i).get(j);
				mem.put(map[i][j], new int[] {i, j});
			}
		}
		
		dist = new int[n][m];
		init();
		
		int ans = 0;
		Set<Integer> keys = mem.keySet();
		
		distMap(map, new int[] {0,0});
		for (int key : keys) {
			if (key > 1) {
				int[] now = mem.get(key);
				int v = dist[now[0]][now[1]];
				if (v == INF) return -1;
				ans += v;
				distMap(map, now);
			}
		}
		
		return ans;
	}
	
	static final int INF = 1 << 31;
	static final int[][] dir = {{1, 0},{-1, 0},{0, -1},{0, 1}};
	int[][] dist;
	
	public void init() {
		for (int i = 0; i < dist.length; ++i) Arrays.fill(dist[i], INF);
	}
	
	public void distMap(int[][] map, int[] start) {
		int n = map.length;
		int m = map[0].length;
		init();
        
		Queue<int[]> queue = new LinkedList<>();
		
		queue.offer(start);
		dist[start[0]][start[1]] = 0;
		
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; ++i) {
				int[] now = queue.poll();
				for (int[] d : dir) {
					int nx = d[0] + now[0];
					int ny = d[1] + now[1];
					if (nx >= 0 && nx < n && ny >= 0 && ny < m && map[nx][ny] >= 1 && dist[nx][ny] == INF) {
						queue.offer(new int[] {nx, ny});
						dist[nx][ny] = dist[now[0]][now[1]] + 1;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		SolutionDay10_L0675 day = new SolutionDay10_L0675();
		List<List<Integer>> data = new ArrayList<>();
//		data.add(Arrays.asList(new Integer[] {2,3,4,5}));
//		data.add(Arrays.asList(new Integer[] {6,7,8,9}));
//		data.add(Arrays.asList(new Integer[] {10,11,12,13}));
//		data.add(Arrays.asList(new Integer[] {14,15,16,17}));
		
		data.add(Arrays.asList(new Integer[] {54581641,64080174,24346381,69107959}));
		data.add(Arrays.asList(new Integer[] {86374198,61363882,68783324,79706116}));
		data.add(Arrays.asList(new Integer[] {668150,92178815,89819108,94701471}));
		data.add(Arrays.asList(new Integer[] {83920491,22724204,46281641,47531096}));
		data.add(Arrays.asList(new Integer[] {89078499,18904913,25462145,60813308}));
		
//		data.add(Arrays.asList(new Integer[] {1,2,3}));
//		data.add(Arrays.asList(new Integer[] {0,0,4}));
//		data.add(Arrays.asList(new Integer[] {7,6,5}));
		System.out.println(day.cutOffTree(data));
	}

}
