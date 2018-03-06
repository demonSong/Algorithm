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

public class SolutionDay30_P2720 {
	
	String INPUT = "./data/judge/201708/P2720.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay30_P2720().run();
	}
	
	static final int MAX_N = 101;
	int[][] fbx = new int[MAX_N][MAX_N];
	int[] ten_power = new int[8];
	
	public int power_of(int x, int y){
		if (y < 0) return -1;
		int res = 1;
		while (y > 0){
			if ((y & 1) != 0){
				res = res * x;
				if (res >= ten_power[7] || res <= 0) return -1;
			}
			y >>= 1;
			x = x * x;
		}
		return res;
	}
	
	public void prepare(){
		ten_power[0] = 1;
		for (int i = 1; i < 8; ++i){
			ten_power[i] = ten_power[i - 1] * 10;
		}
		
		for (int i = 0; i < MAX_N; ++i) Arrays.fill(fbx[i], -1);
		for (int i = 1; i < MAX_N; ++i){
			fbx[i][0] = 1;
			for (int j = 1; j < MAX_N; ++j){
				fbx[i][j] = power_of(i, fbx[i][j - 1]);
			}
		}
	}
	
	
	int[][] cache = new int[MAX_N][MAX_N];
	void solve() {
		
		prepare();
		for (int i = 0; i < MAX_N; ++i) Arrays.fill(cache[i], -1);
		
		while (true){
			int b = ni();
			if (b == 0) break;
			int i = ni();
			int n = ni();
			int ans = 0;
			if (cache[b][i] < 0){
				if (b == 1){
					ans = 1;
				}
				else ans = (int) f(b, i, ten_power[7]);
				cache[b][i] = ans;
			}
			ans = cache[b][i] % ten_power[n];
			
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < n - String.valueOf(ans).length(); ++j){
				sb.append("0");
			}
			out.println(sb.toString() + ans);
		}
	}
	
	public long euler_phi(int m){
		long res = m;
		for (int i = 2; i <= m / i; ++i){
			if (m % i == 0){
				res = res / i * (i - 1);
				for (; m % i == 0; m /= i);
			}
		}
		if (m != 1){
			res = res / m * (m - 1);
		}
		return res;
	}
	
	public long f(int b, int i, int mod){
		if (i == 0) return 1;
		else if (mod == 1) return 0;
		else if (fbx[b][i] < 0){
			int euler = (int) euler_phi(mod);
			return pow(b, f(b, i - 1, euler) + euler, mod);
		}
		else{
			return fbx[b][i] % mod;
		}
	}
	
	public long pow(long base, long n, long mod){
		long res = 1;
		while (n > 0){
			if ((n & 1) != 0){
				res = res * base % mod;
			}
			n >>= 1;
			base = base * base % mod;
		}
		return res;
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
