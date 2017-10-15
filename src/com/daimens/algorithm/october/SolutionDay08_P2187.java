package com.daimens.algorithm.october;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SolutionDay08_P2187 {
	
	String INPUT = "./data/judge/201710/P2187.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay08_P2187().run();
	}
	
	static final int MAX_N = 50000 + 16;
	int N;
	
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

		@Override
		public int compareTo(P o) {
			return x != o.x ? x - o.x : y - o.y;
		}
	}
	
	P[] ps;
	
	int dist(P a, P b) {
		int dx = a.x - b.x;
		int dy = a.y - b.y;
		return dx * dx + dy * dy;
	}
	
	P[] convexHull() {
		P[] qs = new P[2 * N];
		Arrays.sort(ps);
		int k = 0;
		for (int i = 0; i < N; ++i) {
			while (k > 1 && qs[k - 1].sub(qs[k - 2]).det(ps[i].sub(qs[k - 1])) <= 0) k--;
			qs[k++] = ps[i];
		}
		
		for (int i = N - 2, t = k; i >= 0; --i) {
			while (k > t && qs[k - 1].sub(qs[k - 2]).det(ps[i].sub(qs[k - 1])) <= 0) k--;
			qs[k++] = ps[i];
		}
		
		k --;
		P[] res = new P[k];
		System.arraycopy(qs, 0, res, 0, k);
		return res;
	}

	void solve() {
		P[] qs = convexHull();
		int n = qs.length;
		if (n == 2) {
			out.println(dist(qs[0], qs[1]));
			return;
		}
		
		int i = 0;
		int j = 0;
		for (int k = 1; k < n; ++k) {
			if (qs[k].x < qs[i].x) i = k;
			if (qs[k].x > qs[j].x) j = k;
		}
		
		int si = i;
		int sj = j;
		
		int max = 0;
		while (i != sj || j != si) {
			max = Math.max(max, dist(qs[i], qs[j]));
			if (qs[(i + 1) % n].sub(qs[i]).det(qs[(j + 1) % n].sub(qs[j])) < 0) {
				i = (i + 1) % n;
			}
			else {
				j = (j + 1) % n;
			}
		}
		out.println(max);
	}	
	
	void read() {
		N = ni();
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

