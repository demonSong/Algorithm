package com.daimens.algorithm.august;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeSet;

public class SolutionDay11_A0531 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/201708/A0531.txt";
	
	
	void solve() {
		while (true){
			int W = in.nextInt();
			int H = in.nextInt();
			if (W == 0 &&  H == 0) break;
			int N = in.nextInt();
			int MAX_N = N + 16;
			X1 = new int[MAX_N];
			X2 = new int[MAX_N];
			Y1 = new int[MAX_N];
			Y2 = new int[MAX_N];
			for (int i = 0; i < N; ++i){
				X1[i] = in.nextInt();
				Y1[i] = in.nextInt();
				X2[i] = in.nextInt();
				Y2[i] = in.nextInt();
			}
			
			W = compress(X1, X2, W);
			H = compress(Y1, Y2, H);
			
			int[][] fld = new int[2 * MAX_N][2 * MAX_N];
			
			//imos法
			for (int i = 0; i < N; ++i){
				fld[Y1[i]][X1[i]] ++;
				fld[Y1[i]][X2[i]] --;
				fld[Y2[i]][X1[i]] --;
				fld[Y2[i]][X2[i]] ++;
			}
			
			//横向积
			for (int i = 0; i < H; ++i){
				for (int j = 1; j < W; ++j){
					fld[i][j] += fld[i][j - 1];
				}
			}
			
			//纵向积
			for (int j = 0; j < W; ++j){
				for (int i = 1; i < H; ++i){
					fld[i][j] += fld[i - 1][j];
				}
			}
			
			System.out.println(bfs(fld, W, H));
			
		}
	}
	
	int[][] dir = {{1, 0},{-1, 0},{0, -1},{0, 1}};
	
	class Pair{
		int x;
		int y;
		public Pair(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	int bfs(int[][] fld, int W, int H){
		Queue<Pair> queue = new LinkedList<>();
		int cnt = 0;
		for (int i = 0; i < H; ++i){
			for (int j = 0; j < W; ++j){
				if (fld[i][j] == 0){
					cnt ++;
					queue.offer(new Pair(i, j));
					fld[i][j] = 1;
					while (!queue.isEmpty()){
						Pair now = queue.poll();
						int x = now.x;
						int y = now.y;
						//fld[x][y] = 1;
						for (int[] d : dir){
							int nx = x + d[0];
							int ny = y + d[1];
							if (nx >= 0 && nx < H && ny >= 0 && ny < W && fld[nx][ny] == 0){
								queue.offer(new Pair(nx, ny));
								fld[nx][ny] = 1;
							}
						}
					}
				}
			}
		}
		return cnt;
	}
	
	/*****************坐标离散化*******************/
	int[] X1;
	int[] X2;
	int[] Y1;
	int[] Y2;
	
	int compress(int[] x1, int[] x2, int w) {
        int n = x1.length;
        TreeSet<Integer> set = new TreeSet<Integer>();
        set.add(0);
        set.add(w);
        for (int i = 0; i < n; ++i){
        	set.add(x1[i]);
        	set.add(x2[i]);
        }
        
        Integer[] x = set.toArray(new Integer[0]);
        for (int i = 0; i < n; ++i){
        	x1[i] = Arrays.binarySearch(x, x1[i]);
        	x2[i] = Arrays.binarySearch(x, x2[i]);
        }
        
        return x.length - 1;
	}

	Scanner in;
	public SolutionDay11_A0531(){
		in = new Scanner(System.in);
	}
	
	public static void main(String[] args) throws Exception {
		new SolutionDay11_A0531().solve();
	}
}





