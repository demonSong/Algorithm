package com.daimens.algorithm.october;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SolutionDay16_P3608 {
	
	String INPUT = "./data/judge/201710/P3608.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay16_P3608().run();
	}
	
	static final int INF = 0x3f3f3f3f;
	static final double EPS = 1e-8;
	
	class P implements Comparable<P>{
		
		double x;
		double y;
		
		P(double x, double y){
			this.x = x;
			this.y = y;
		}
		
		P sub(P a) {
			return new P(x - a.x, y - a.y);
		}
		
		double det(P a) {
			return x * a.y - y * a.x;
		}
		
		@Override
		public int compareTo(P o) {
			return Double.compare(x, o.x) == 0 ? Double.compare(y, o.y) : Double.compare(x, o.x);
		}
		
		@Override
		public String toString() {
			return x + "," + y;
		}
	}
	
	int N, M;
	P[] p;
	P[] q;
	
	double dist(P a, P b) {
		double dx = a.x - b.x;
		double dy = a.y - b.y;
		return Math.sqrt(dx * dx + dy * dy);
	}
	
	// ab 垂直 ac
	double verticle(P a, P b, P c) {
		return (b.x - a.x) * (c.x - a.x) + (b.y - a.y) * (c.y - a.y);
	}
	
	double cross(P a, P b, P c) {
		return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
	}
	
	// 点c到直线ab的最短距离
	double min(P a, P b, P c) {
		if (dist(a, b) < EPS) return dist(b, c);
		if (verticle(a, b, c) < EPS) return dist(a, c);
		if (verticle(b, a, c) < EPS) return dist(b, c);
		return Math.abs(cross(a, b, c) / dist(a, b));
	}
	
	double min(P a, P b, P c, P d) {
		return Math.min(Math.min(min(a, b, c), min(a, b, d)), Math.min(min(c, d, a), min(c, d, b)));
	}
	
	double solve(P[] a, P[] b, int n, int m) {
		
		P[] ps = new P[n + 1];
		P[] qs = new P[m + 1];
		System.arraycopy(a, 0, ps, 0, n);
		System.arraycopy(b, 0, qs, 0, m);
		
		ps[n] = ps[0];
		qs[m] = qs[0];
		
		int ymin = 0;
		int ymax = 0;
		
		for (int i = 0; i < n; ++i) if (ps[i].y < ps[ymin].y) ymin = i;
		for (int i = 0; i < m; ++i) if (qs[i].y > qs[ymax].y) ymax = i;
		
		double ans = INF;
		double tmp = 0.0;
		for (int i = 0; i < n; ++i) {
			
			while ((tmp = cross(ps[ymin + 1], qs[ymax + 1], ps[ymin]) - cross(ps[ymin + 1], qs[ymax], ps[ymin])) > EPS) {
				ymax = (ymax + 1) % m;
			}
			if (tmp + EPS < 0) ans = Math.min(ans, min(ps[ymin], ps[ymin + 1], qs[ymax]));
			else ans = Math.min(ans, min(ps[ymin], ps[ymin + 1], qs[ymax], qs[ymax + 1]));
			ymin = (ymin + 1) % n;
		}
		
		return ans;
	}
	
	void read() {
		while (true) {
			N = ni();
			M = ni();
			
			if (N + M == 0) break;
			
			p = new P[N];
			q = new P[M];
			
			for (int i = 0; i < N; ++i) {
				p[i] = new P(nd(), nd());
			}
			
			for (int i = 0; i < M; ++i) {
				q[i] = new P(nd(), nd());
			}
			
			out.printf("%.5f\n", Math.min(solve(p, q, N, M), solve(q, p, M, N)));
			
		}
	}

	FastScanner in;
	PrintWriter out;
	
	void run() throws IOException {
		boolean oj;
		try {
			oj = ! System.getProperty("user.dir").equals("F:\\oxygen_workspace\\Algorithm");
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

