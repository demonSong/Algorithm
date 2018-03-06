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

public class SolutionDay29_P3168 {
	
	String INPUT = "./data/judge/201709/P3168.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay29_P3168().run();
	}
	
	static final int MAX_N = 25000 + 16;

	class Point implements Comparable<Point>{
		int ord;         // 当前轴坐标
		int pos;         // 另一轴上的坐标
		int id;          // 对应的矩阵id
		int type;        // 起点: 0 终点: 1
		
		Point(int ord, int pos, int id, int type){
			this.ord  = ord;
			this.pos  = pos;
			this.id   = id;
			this.type = type;
		}

		@Override
		public int compareTo(Point that) {
			if (this.ord != that.ord) {
				return this.ord - that.ord;
			}
			if (this.pos != that.pos) {
				return this.pos - that.pos;
			}
			return this.type - that.type;
		}
	}
	
	int N;
	Point[] toX = new Point[4 * MAX_N];     // 以x轴方向扫描
	Point[] toY = new Point[4 * MAX_N];     // 以y轴方向扫描
	boolean[] ok = new boolean[MAX_N];
	
	void scan(Point[] ps) {
		
		for (int i = 0; i < 4 * N; ++i) {
			
			int j = i;
			while (j < 4 * N && ps[j].ord == ps[i].ord) j++;
			
			boolean invalid = false;
			int share = 0;
			
			for (int k = i; k < j; ++k) {
				Point p = ps[k];
				if (invalid) {
					ok[p.id] = false;
				}
				if (p.type == 0) {
					share ++;
					if (share >= 2) {
						invalid = true;
					}
				}
				else {
					share --;
					if (share == 0) {
						invalid = false;
					}
				}
			}
			i = j - 1;
		}
	}
	
	void solve() {
		
		Arrays.sort(toX, 0, 4 * N);
		Arrays.sort(toY, 0, 4 * N);
		
		Arrays.fill(ok, true);
		
		scan(toX);
		scan(toY);
		
		int ans = 0;
		for (int i = 0; i < N; ++i) {
			if (ok[i]) ans ++;
		}
		
		out.println(ans);
	}
	
	void read() {
		N = ni();
		for (int i = 0; i < N; ++i) {
			int A = ni(), B = ni(), C = ni(), D = ni();
			toX[4 * i]     = new Point(A, B, i, 0);
			toX[4 * i + 1] = new Point(A, D, i, 1);
			toX[4 * i + 2] = new Point(C, B, i, 0);
			toX[4 * i + 3] = new Point(C, D, i, 1);
			
			toY[4 * i]     = new Point(B, A, i, 0);
			toY[4 * i + 1] = new Point(B, C, i, 1);
			toY[4 * i + 2] = new Point(D, A, i, 0);
			toY[4 * i + 3] = new Point(D, C, i, 1);
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

