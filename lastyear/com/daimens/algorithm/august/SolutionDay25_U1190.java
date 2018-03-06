package com.daimens.algorithm.august;

import java.util.Arrays;
import java.util.Scanner;

public class SolutionDay25_U1190 {
	
	static final int MAX_N = 200000 + 16;
	static final int MAX_M = 200000 + 16;
	static final int BUCKET_SIZE = 450;
	
	static int[] A;
	static int[] POS;
	static int N, M;
	
	static class Bucket{
		int count;
		int prefix_sum;
	}
	static Bucket[][] buckets;
	
	static class Space{
		int[] X;
		int[] Y;
		
		public Space(){
			X = new int[MAX_N];
			Y = new int[MAX_N];
		
			Arrays.fill(X, -1);
			Arrays.fill(Y, -1);
		}
		
		
		public void add(int x, int y){
			X[y] = x;
			Y[x] = y;
		}
		
		public void remove(int x, int y){
			X[y] = -1;
			Y[x] = -1;
		}
		
		public int getX(int y){
			return X[y];
		}
		
		public int getY(int x){
			return Y[x];
		}
	}
	static Space space;
	
	public static void update_prefix_sum(int bx, int by){
		int len = buckets[0].length;
		int sum = (bx > 0 ? buckets[bx - 1][by].prefix_sum : 0);
		for (int i = bx; i < len; ++i){
			sum += buckets[i][by].count;
			buckets[i][by].prefix_sum = sum;
		}
	}
	
	public static void add(int x, int y){
		space.add(x, y);
		int bx = x / BUCKET_SIZE;
		int by = y / BUCKET_SIZE;
		++buckets[bx][by].count;
		update_prefix_sum(bx, by);
	}
	
	public static void remove(int x, int y){
		space.remove(x, y);
		int bx = x / BUCKET_SIZE;
		int by = y / BUCKET_SIZE;
		--buckets[bx][by].count;
		update_prefix_sum(bx, by);
	}
	
	// 统计区间 [0,0] 到 [x, y] 的点的个数
	public static int sum(int x, int y){
		int bx = x / BUCKET_SIZE;
		int by = y / BUCKET_SIZE;
		
		int count = 0;
		for (int i = 0; i < by; ++i){
			if (bx > 0)
				count += buckets[bx - 1][i].prefix_sum;
		}
		
		for (int py = by * BUCKET_SIZE; py < y; ++py){
			if (space.getX(py) != -1 && space.getX(py) < x) count++;
		}
		
		for (int px = bx * BUCKET_SIZE; px < x; ++px){
			if (space.getY(px) != -1 && space.getY(px) < by * BUCKET_SIZE) count++;
		}
		return count;
	}
	
	public static int sum_inversion(int x, int y){
		int res = 0;
		int intersection = sum(x, y);
		res += sum(x, N) - intersection;
		res += sum(N, y) - intersection;
		return res;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()){
			N = in.nextInt();
			M = in.nextInt();
			A = new int[N];
			POS = new int[N];
			for (int i = 0; i < N; ++i){
				A[i] = in.nextInt();
				A[i]--;
				POS[A[i]] = i;
			}
			
			long res = 0;
			space = new Space();
			buckets = new Bucket[MAX_N / BUCKET_SIZE + 1][MAX_N / BUCKET_SIZE + 1];
			int len = buckets.length;
			for (int i = 0; i < len; ++i){
				for (int j = 0; j < len; ++j){
					buckets[i][j] = new Bucket();
				}
			}
			
			for (int i = 0; i < N; ++i){
				add(i, A[i]);
				res += sum_inversion(i, A[i]);
			}
			
			for (int i = 0; i < M; ++i){
				int m = in.nextInt();
				m--;
				System.out.println(res);
				res -= sum_inversion(POS[m], m);
				remove(POS[m], m);
			}
		}
		in.close();
	}

}

