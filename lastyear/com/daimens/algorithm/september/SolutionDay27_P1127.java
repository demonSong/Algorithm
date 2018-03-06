package com.daimens.algorithm.september;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SolutionDay27_P1127 {
	
	String INPUT = "./data/judge/201709/P1127.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay27_P1127().run();
	}
	
	class P {
		
		static final double EPS = 1e-10;
		
		double x;
		double y;
		
		P(double x, double y){
			this.x = x;
			this.y = y;
		}
		
		P add(P a) {
			return new P(add(x, a.x), add(y, a.y));
		}
		
		P sub(P a) {
			return new P(add(x, -a.x), add(y, -a.y));
		}
		
		P mul(P a) {
			return new P(x * a.x, y * a.y);
		}
		
		double dot(P a) {
			return add(x * a.x,  y * a.y);
		}
		
		double det(P a) {
			return add(x * a.y, -y * a.x);
		}
		
		@Override
		public String toString() {
			return "(" + x + "," + y + ")";
		}
		
		public double add(double a, double b) {
			if (Math.abs(a + b) < EPS * (Math.abs(a) + Math.abs(b))) return 0;
			return a + b;
		}
	}
	
	boolean onSeg(P p1, P p2, P q) {
		return p1.sub(q).det(p2.sub(q)) == 0 && p1.sub(q).dot(p2.sub(q)) <= 0;
	}
	
	P intersection(P p1, P p2, P q1, P q2) {
		double fz = q2.sub(q1).det(q1.sub(p1));
		double fm = q2.sub(q1).det(p2.sub(p1));
		P q = p2.sub(p1);
		q = new P(fz / fm * q.x, fz / fm * q.y);
		return p1.add(q);
	}
	
	void read() {
		while (true) {
			int N = ni();
			if (N == 0) break;
			
			P[] p = new P[N];
			P[] q = new P[N];
			
			for (int i = 0; i < N; ++i) {
				p[i] = new P(nd(), nd());
				q[i] = new P(nd(), nd());
			}
			
			boolean[][] map = new boolean[N][N];
			for (int i = 0; i < N; ++i) {
				map[i][i] = true;
				for (int j = i + 1; j < N; ++j) {
					if (p[i].sub(q[i]).det(p[j].sub(q[j])) == 0) {
						map[i][j] = map[j][i] = onSeg(p[i], q[i], p[j])
												|| onSeg(p[i], q[i], q[j])
												|| onSeg(p[j], q[j], p[i])
												|| onSeg(p[j], q[j], q[i]);
					}
					else {
						P r = intersection(p[i], q[i], p[j], q[j]);
						map[i][j] = map[j][i] = onSeg(p[i], q[i], r) && onSeg(p[j], q[j], r);
					}
				}
			}
			
			for (int k = 0; k < N; ++k) {
				for (int i = 0; i < N; ++i) {
					for (int j = 0; j < N; ++j) {
						map[i][j] |= map[i][k] && map[k][j];
					}
				}
			}
			
			while (true) {
				int a = ni();
				int b = ni();
				if (a + b == 0) break;
				a --;
				b --;
				out.println(map[a][b] ? "CONNECTED" : "NOT CONNECTED");
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

