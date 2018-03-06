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

public class SolutionDay28_P1981 {
	
	String INPUT = "./data/judge/201709/P1981.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay28_P1981().run();
	}
	
	static final int MAX_N = 302;
	
	class P {
		double x;
		double y;
		P(double x, double y){
			this.x = x;
			this.y = y;
		}
	}
	
	class PolarAngle implements Comparable<PolarAngle>{
	
		double  angle;
		boolean flag;
		PolarAngle(double angle, boolean flag){
			this.angle = angle;
			this.flag  = flag;
		}
		@Override
		public int compareTo(PolarAngle that) {
			return Double.compare(this.angle, that.angle);
		}
	}
	
	P[] points = new P[MAX_N];
	PolarAngle[] pa = new PolarAngle[2 * MAX_N];
	
	int N;
	int ans;
	
	double dist(P a, P b) {
		double dx = a.x - b.x;
		double dy = a.y - b.y;
		return Math.sqrt(dx * dx + dy * dy);
	}
	
	void solve() {
		ans = 1;
		for (int i = 0; i < N; ++i) {
			int m = 0;
			for (int j = 0; j < N; ++j) {
				double d = 0;
				if (i != j && (d = dist(points[i], points[j])) <= 2.0) {
					double theta = Math.acos(d / 2);
					double phi   = Math.atan2(points[j].y - points[i].y, points[j].x - points[i].x);
					pa[m++] = new PolarAngle(phi - theta, true);  // start
					pa[m++] = new PolarAngle(phi + theta, false); // end;
				}
			}
			
			Arrays.sort(pa, 0, m);
			
			int sum = 1;
			for (int j = 0; j < m; ++j) {
				if (pa[j].flag) {
					sum ++;
				}
				else {
					sum --;
				}
				ans = Math.max(ans, sum);
			}
		}
	}
	
	void read() {
		while (true) {
			N = ni();
			if (N == 0) break;
			for (int i = 0; i < N; ++i) {
				points[i] = new P(nd(), nd());
			}
			ans = 0;
			solve();
			out.println(ans);
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

