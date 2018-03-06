package com.daimens.algorithm.september;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SolutionDay29_P1113 {
	
	String INPUT = "./data/judge/201709/P1113.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay29_P1113().run();
	}
	
	static final int MAX_N = 1000 + 16;
	static final double PI = Math.acos(-1.0);
	
	class P implements Comparable<P>{
		int x;
		int y;
		
		P(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		P sub(P a) {
			return new P(x - a.x, y - a.y);
		}
		
		int det(P a) {
			return x * a.y - y * a.x;
		}
		
		int dot(P a) {
			return x * a.x + y * a.y;
		}
		
		@Override
		public int compareTo(P o) {
			return this.x != o.x ? this.x - o.x : this.y - o.y;
		}
	}
	
	int N, L;
	P[] ps;
	
	double dist(P a, P b) {
		return Math.sqrt(a.sub(b).dot(a.sub(b)));
	}
	
	P[] convexHull() {
		
		Arrays.sort(ps);
		P[] qs = new P[N * 2];
		int k = 0;
		for (int i = 0; i < N; qs[k++] = ps[i++]) {
			while (k > 1 && qs[k - 1].sub(qs[k - 2]).det(ps[i].sub(qs[k - 1])) <= 0) k --;
		}
		
		for (int i = N - 2, t = k; i >= 0; qs[k++] = ps[i--]) {
			while (k > t && qs[k - 1].sub(qs[k - 2]).det(ps[i].sub(qs[k - 1])) <= 0) k --;
		}
		
		P[] res = new P[k - 1];
		System.arraycopy(qs, 0, res, 0, k - 1);
		return res;
	}
	
	void solve() {
		P[] qs = convexHull();
		double res = 0;
		int n = qs.length;
		for (int i = 0; i < n; ++i) {
			res += dist(qs[i], qs[(i + 1) % n]);
		}
		
		out.printf("%.0f\n", 2 * PI * L + res);
	}
	
	
	void read() {
		N = ni();
		L = ni();
		
		ps = new P[N];
		for (int i = 0; i < N; ++i) {
			ps[i] = new P(ni(), ni());
		}
		
		solve();
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
		read();
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

