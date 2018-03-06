package com.daimens.algorithm.august;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SolutionDay29_P1284 {
	
	String INPUT = "./data/judge/201708/P1284.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay29_P1284().run();
	}
	
	void solve() {
		euler_phi2();
		while (more()){
			int P = ni();
			out.println(euler[P - 1]);
		}
	}
	
//	public int euler_phi(int n){  // n * (pi - 1) / pi = n / pi * (pi - 1)
//		int res = n;
//		while (n > 1){
//			for (int i = 2; i <= n; ++i){
//				if (n % i == 0){
//					res = res / i * (i - 1);
//					while (n % i == 0){
//						n /= i;
//					}
//				}
//			}
//		}
//		return res;
//	}
	
	public int euler_phi1(int n){  // n * (pi - 1) / pi = n / pi * (pi - 1)
		int res = n;
		for (int i = 2; i <= n / i; ++i){
			if (n % i == 0){
				res = res / i * (i - 1);
				while (n % i == 0) n /= i;
			}
		}
		
		if (n != 1) res = res / n * (n - 1);
		return res;
	}
	
	int MAX_N = 65536;
	int[] euler;
	public void euler_phi2(){  //如果是素数 欧拉函数为 p - 1
		euler = new int[MAX_N];
		for (int i = 0; i < MAX_N; ++i) euler[i] = i;
		for (int i = 2; i < MAX_N; ++i){
			if (euler[i] == i){
				for (int j = i; j < MAX_N; j += i){
					euler[j] = euler[j] / i * (i - 1);  // 
				}
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
