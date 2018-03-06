package com.daimens.algorithm.august;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SolutionDay29_P1150 {
	
	String INPUT = "./data/judge/201708/P1150.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay29_P1150().run();
	}
	
	int[][] table = {
			{6, 2, 4, 8},
			{1, 3, 9, 7}, // 3	
			{1, 7, 9, 3}, // 7
			{1, 9, 1, 9}, // 9
	};
	
	void solve() {
		while (more()){
			int N = ni();
			int M = ni();
			
			// 分解质因数 2 和 5 并且分别统计 2 和 5 的个数
			int two    = fact_prime(N, 2) - fact_prime_iter(N - M, 2);
			int five   = fact_prime(N, 5) - fact_prime_iter(N - M, 5);
			int remain = two - five;
			
			// 统计 1 3 7 9 的个数
			int three  = end_with(N, 3) - end_with(N - M, 3);
			int seven  = end_with(N, 7) - end_with(N - M, 7);
			int nine   = end_with(N, 9) - end_with(N - M, 9);
			
			int ans = 1;
			ans *= remain == 0 ? 1 : table[0][remain % 4];
			ans *= three  == 0 ? 1 : table[1][three  % 4];
			ans *= seven  == 0 ? 1 : table[2][seven  % 4];
			ans *= nine   == 0 ? 1 : table[3][nine   % 4];
			ans %= 10;
			
			out.println(ans);
		}
	}
	
	public int fact_prime(int n, int x){
		if (n == 0) return 0;
		return n / x + fact_prime(n / x, x);
	}
	
	public int fact_prime_iter(int n, int x){
		int res = 0;
		while (n > 0){
			res += n / x;
			n = n / x;
		}
		return res;
	}
	
	public int odd_end_with(int n, int x){
		if (n == 0) return 0;
		int res = n / 10;
		res += (n % 10 >= x ? 1 : 0);
		return res + odd_end_with(n / 5, x);
	}
	
	public int end_with(int n, int x){
		if (n == 0) return 0;
		return end_with(n / 2, x) + odd_end_with(n, x);
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
