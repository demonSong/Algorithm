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

public class SolutionDay28_P1418 {
	
	String INPUT = "./data/judge/201709/P1418.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay28_P1418().run();
	}
	
	static final double PI  = Math.acos(-1);
	static final double EPS = 5E-13;
	static final int MAX_N  = 102;
	
	class P{
		
		double x;
		double y;
		
		P(double x, double y){
			this.x = x;
			this.y = y;
		}
	}
	
	P[]   o = new P[MAX_N];                 // 圆心坐标
	double[] r = new double[MAX_N];         // 圆半径
 	boolean[] visible = new boolean[MAX_N]; // 对应圆是否可见
	int N;
	
	double[] angle = new double[2 * MAX_N];
	int tot;
	
	double distance(P a, P b) {
		double dx = a.x - b.x;
		double dy = a.y - b.y;
		return Math.sqrt(dx * dx + dy * dy);
	}
	
	double norm(double ang) {
		while (ang < 0) {
			ang += 2 * PI;
		}
		while (ang > 2 * PI) {
			ang -= 2 * PI;
		}
		return ang;
	}
	
	void solve() {
		Arrays.fill(visible, false);
		for (int i = 0; i < N; ++i) {
			tot = 0;
			angle[tot++] = 0;
			angle[tot++] = 2 * PI;
			
			for (int j = 0; j < N; ++j) {
				if (i == j) continue;
				double d = distance(o[i], o[j]);
				if (r[i] + r[j] < d || d < r[i] - r[j] || d < r[j] - r[i]) continue; // 包含或者不相交
				double phi = Math.atan2(o[j].y - o[i].y, o[j].x - o[i].x);
				double the = Math.acos((r[i] * r[i] + d * d - r[j] * r[j]) / (2 * r[i] * d));
				angle[tot++] = norm(phi - the);
				angle[tot++] = norm(phi + the);
			}
			
			Arrays.sort(angle, 0, tot);
			
			for (int j = 0; j < tot - 1; ++j) {
				double mid = (angle[j] + angle[j + 1]) / 2;
				
				double nx = 0; 
				double ny = 0;
				
				for (int t = -1; t < 2; t += 2) {
					nx = o[i].x + (r[i] + t * EPS) * Math.cos(mid);
					ny = o[i].y + (r[i] + t * EPS) * Math.sin(mid);
					
					P np = new P(nx, ny);
					
					int k = 0;
					for (k = N - 1; k >= 0; --k) {
						if (distance(np, o[k]) < r[k]) {
							break;
						}
					}
					if (k != -1)
						visible[k] = true;
				}
			}
		}
		
		int ans = 0;
		for (int i = 0; i < N; ++i) {
			if (visible[i]) ans ++;
		}
		out.println(ans);
	}
	
	void read() {
		while (true) {
			N = ni();
			if (N == 0) break;
			
			for (int i = 0; i < N; ++i) {
				o[i] = new P(nd(), nd());
				r[i] = nd();
			}
			
			solve();
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

