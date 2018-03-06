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

public class SolutionDay29_P1912 {
	
	String INPUT = "./data/judge/201709/P1912.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay29_P1912().run();
	}
	
	static final int MAX_N = 100000 + 16;
	static final double EPS = 1E-10;
	static final double PI  = Math.acos(-1.0);
	
	class P implements Comparable<P>{
		
		double x;
		double y;
		
		P (double x, double y){
			this.x = x;
			this.y = y;
		}
		
		double add(double a, double b) {
			if (Math.abs(a + b) < EPS * (Math.abs(a) + Math.abs(b))) return 0;
			return a + b;	
		}
		
		P add(P a) {
			return new P(add(x, a.x), add(y, a.y));
		}
		
		P sub(P a) {
			return new P(add(x, -a.x), add(y, -a.y));
		}
		
		double dot(P a) {
			return a.x * x + a.y * y;
		}
		
		double det(P a) {
			return x * a.y - y * a.x;
		}

		@Override
		public int compareTo(P o) {
			int cmp = Double.compare(x, o.x);
			return cmp != 0 ? cmp : Double.compare(y, o.y);
		}
	}
	
	int N;
	P[] ps;
	
	P[] convexHull() {
		Arrays.sort(ps);
		if (N <= 1) return ps;
		
		P[] qs = new P[N * 2];
		int k = 0;
		for (int i = 0; i < N; qs[k++] = ps[i++]) {
			while (k > 1 && qs[k - 1].sub(qs[k - 2]).det(ps[i].sub(qs[k - 1])) < EPS) k--;
		}
		
		for (int i = N - 2, t = k; i >= 0; qs[k++] = ps[i--]) {
			while (k > t && qs[k - 1].sub(qs[k - 2]).det(ps[i].sub(qs[k - 1])) < EPS) k--;
		}
		
		P[] res = new P[k - 1];
		System.arraycopy(qs, 0, res, 0, k - 1);
		return res;
	}
	
	double angle(P a, P b) {
		P t = b.sub(a);
		double ang = Math.atan2(t.y, t.x);
		if (ang < -PI / 2 + EPS) ang += 2 * PI;
		return ang;
	}
	
	int lowerBound(double[] ds, double v) {
		int l = 0, r = ds.length;
		while (l < r) {
			int m = (r + l) >> 1;
			if (ds[m] < v) l = m + 1;
			else r = m;
		}
		return l;
	}
	
	//a, b是否在直线st的两侧
	boolean check(P a, P b, P s, P t) {
		return a.sub(s).det(t.sub(s)) * b.sub(s).det(t.sub(s)) <= 0;
	}
	
	boolean cross(P[] qs, double[] ds, P p1, P p2) {
		int i = lowerBound(ds, angle(p1, p2));
		int j = lowerBound(ds, angle(p2, p1));
		i %= qs.length;
		j %= qs.length;
		
		return check(qs[i], qs[j], p1, p2);
	}
	
	void solve() {
		
		P[] qs = convexHull();
		int n = qs.length;
		double[] ds = new double[n];
		
		for (int i = 0; i < n; ++i) {
			ds[i] = angle(qs[i], qs[(i + 1) % n]);
		}
		
		while (more()) {
			P p1 = new P(nd(), nd());
			P p2 = new P(nd(), nd());
			if (N <= 1 || !cross(qs, ds, p1, p2)) out.println("GOOD");
			else out.println("BAD");
		}
	}
	
	void read() {
		N = ni();
		ps = new P[N];
		for (int i = 0; i < N; ++i) {
			ps[i] = new P(nd(), nd());
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

