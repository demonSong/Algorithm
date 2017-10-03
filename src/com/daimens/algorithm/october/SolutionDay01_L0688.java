package com.daimens.algorithm.october;

public class SolutionDay01_L0688 {
	
//	int[][] dir = {{2, 1},{2, -1},{-2, -1},{-2, 1},{1, 2},{1, -2},{-1, 2},{-1, -2}};
//	
//	double[][][] mem = new double[102][32][32];
//	public double f(int N, int K, int R, int C) {
//		if (K == 0) {
//			return 1;
//		}
//		if (mem[K][R][C] > 0) return mem[K][R][C];
//		double res = 0.0;
//		for (int[] d : dir) {
//			int nx = R + d[0];
//			int ny = C + d[1];
//			if (check(nx, ny, N)) {
//				res += 1 / 8.0 * f(N, K - 1, nx, ny);
//			}
//		}
//		
//		return mem[K][R][C] = res;
//	}
//	
//	public void fill(double[][][] mem) {
//		for (int i = 0; i < mem.length; ++i) {
//			for (int j = 0; j < mem[i].length; ++j) {
//				for (int k = 0; k < mem[i][j].length; ++k) {
//					mem[i][j][k] = -1;
//				}
//			}
//		}
//	}
//	
//	boolean check(int i, int j, int N) {
//		return i >= 0 && i < N && j >= 0 && j < N;
//	}
//	
//	
//	public double knightProbability(int N, int K, int r, int c) {
//		fill(mem);
//		return f(N, K, r, c);
//	}
	
	int[][] dir = {{2, 1},{2, -1},{-2, -1},{-2, 1},{1, 2},{1, -2},{-1, 2},{-1, -2}};
	
	boolean check(int i, int j, int N) {
		return i >= 0 && i < N && j >= 0 && j < N;
	}
	
	public double knightProbability(int N, int K, int r, int c) {
		double[][][] mem = new double[102][32][32];
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				mem[0][i][j] = 1;
			}
		}
		
		for (int k = 1; k <= K; ++k) {
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					for (int[] d : dir) {
						int nx = i + d[0];
						int ny = j + d[1];
						if (check(nx, ny, N)) {
							mem[k][i][j] += 1 / 8.0 * mem[k - 1][nx][ny];
						}
					}
				}
			}
		}
		return mem[K][r][c];
	}
	
//	int[][] dir = {{2, 1},{2, -1},{-2, -1},{-2, 1},{1, 2},{1, -2},{-1, 2},{-1, -2}};
//
//	class Pair{
//		int id;
//		double pro;
//		
//		Pair(int id, double pro){
//			this.id = id;
//			this.pro = pro;
//		}
//	}
//	
//	public double knightProbability(int N, int K, int r, int c) {
//		
//		Queue<Pair> queue = new ArrayDeque<>();
//		queue.offer(new Pair(r * N + c, 1));
//		
//		int turn = 0;
//		while (!queue.isEmpty()) {
//			int size = queue.size();
//			for (int i = 0; i < size; ++i) {
//				Pair p = queue.poll();
//				int cnt = 0;
//				List<Integer> tmp = new ArrayList<>();	
//				for (int[] d : dir) {
//					int nx = d[0] + p.id / N;
//					int ny = d[1] + p.id % N;
//					if (check(nx, ny, N)) {
//						cnt ++;
//						tmp.add(nx * N + ny);
//					}
//				}
//				for (int j = 0; j < tmp.size(); ++j) {
//					queue.offer(new Pair(tmp.get(j), p.pro * cnt / 8));
//				}
//			}
//			turn ++;
//			if (turn == K) break; 
//		}
//		
//		double ans = 0;
//		int size = queue.size();
//		while (!queue.isEmpty()) {
//			ans += (queue.poll().pro / size);
//		}
//		
//		return ans;
//	}
//	
//	boolean check(int i, int j, int N) {
//		return i >= 0 && i < N && j >= 0 && j < N;
//	}
	
	public static void main(String[] args) {
		SolutionDay01_L0688 day = new SolutionDay01_L0688();
		System.out.println(day.knightProbability(8, 32, 6, 4));
	}
}
