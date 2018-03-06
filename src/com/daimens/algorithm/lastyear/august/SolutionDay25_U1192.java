package com.daimens.algorithm.august;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SolutionDay25_U1192 {
	String INPUT = "./data/judge/201708/U1190.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay25_U1192().run();
	}
	
	static final int MAX_N = 200000 + 16;
	static final int MAX_M = 200000 + 16;
	static final int BUCKET_SIZE = 450;
	
	int[] A;
	int[] POS;
	int N, M;
	
	class Bucket{
		int count;
		int prefix_sum;
	}
	Bucket[][] buckets;
	
	class Space{
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
	Space space;
	
	public void update_prefix_sum(int bx, int by){
		int len = buckets[0].length;
		int sum = (bx > 0 ? buckets[bx - 1][by].prefix_sum : 0);
		for (int i = bx; i < len; ++i){
			sum += buckets[i][by].count;
			buckets[i][by].prefix_sum = sum;
		}
	}
	
	public void add(int x, int y){
		space.add(x, y);
		int bx = x / BUCKET_SIZE;
		int by = y / BUCKET_SIZE;
		++buckets[bx][by].count;
		update_prefix_sum(bx, by);
	}
	
	public void remove(int x, int y){
		space.remove(x, y);
		int bx = x / BUCKET_SIZE;
		int by = y / BUCKET_SIZE;
		--buckets[bx][by].count;
		update_prefix_sum(bx, by);
	}
	
	// 统计区间 [0,0] 到 [x, y] 的点的个数
	public int sum(int x, int y){
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
	
	public int sum_inversion(int x, int y){
		int res = 0;
		int intersection = sum(x, y);
		res += sum(x, N) - intersection;
		res += sum(N, y) - intersection;
		return res;
	}
	
	void solve() {
		while (more()){
			N = ni();
			M = ni();
			A = new int[N];
			POS = new int[N];
			for (int i = 0; i < N; ++i){
				A[i] = ni();
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
				int m = ni();
				m--;
				System.out.println(res);
				res -= sum_inversion(POS[m], m);
				remove(POS[m], m);
			}
		}
	}

	FastScanner in;
	PrintWriter out;
	
	void run() throws IOException {
		boolean oj;
		try {
			oj = ! System.getProperty("user.dir").equals("F:\\java_workspace\\leetcode");
		} catch (Exception e) {
			oj = System.getProperty("ONLINE_JUDGE") != null;
		}
		InputStream is = oj ? System.in : new FileInputStream(new File(INPUT));
		in = new FastScanner(is);
		out = new PrintWriter(System.out);
		long s = System.currentTimeMillis();
		solve();
		out.flush();
		if (!oj){
			System.out.println("[" + (System.currentTimeMillis() - s) + "ms]");
		}
	}
	
	public boolean more(){
		return in.hasNext();
	}
	
	public int ni(){
		return in.nextInt();
	}
	
	public long nl(){
		return in.nextLong();
	}
	
	public double nd(){
		return in.nextDouble();
	}
	
	public String ns(){
		return in.nextString();
	}
	
	public char nc(){
		return in.nextChar();
	}
	
	class FastScanner {
		BufferedReader br;
		StringTokenizer st;
		boolean hasNext;

		public FastScanner(InputStream is) throws IOException {
			br = new BufferedReader(new InputStreamReader(is));
			hasNext = true;
		}

		public String nextToken() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (Exception e) {
					hasNext = false;
					return "##";
				}
			}
			return st.nextToken();
		}
		
		String next = null;
		public boolean hasNext(){
			next = nextToken();
			return hasNext;
		}

		public int nextInt() {
			if (next == null){
				hasNext();
			}
			String more = next;
			next = null;
			return Integer.parseInt(more);
		}

		public long nextLong() {
			if (next == null){
				hasNext();
			}
			String more = next;
			next = null;
			return Long.parseLong(more);
		}

		public double nextDouble() {
			if (next == null){
				hasNext();
			}
			String more = next;
			next = null;
			return Double.parseDouble(more);
		}
		
		public String nextString(){
			if (next == null){
				hasNext();
			}
			String more = next;
			next = null;
			return more;
		}
		
		public char nextChar(){
			if (next == null){
				hasNext();
			}
			String more = next;
			next = null;
			return more.charAt(0);
		}
	}
}

