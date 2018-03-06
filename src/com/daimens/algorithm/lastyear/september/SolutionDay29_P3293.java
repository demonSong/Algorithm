package com.daimens.algorithm.september;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SolutionDay29_P3293 {
	
	String INPUT = "./data/judge/201709/P3293.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay29_P3293().run();
	}
	
	static final int MAX_N = 100000 + 16;
	
	class Point {
		int x;
		int y;
		Point[] nxt;
		int tot;
		
		Point(int x, int y){
			this.x = x;
			this.y = y;
			this.tot = 0;
			this.nxt = new Point[2];
		}
		
		void add(Point a) {
			nxt[tot++] = a;
		}
		
		@Override
		public boolean equals(Object obj) {
			Point p = (Point) obj;
			return p.x == x && p.y == y;
		}
		
		@Override
		public String toString() {
			return "(" + x + " " + y + ")";
		}
	}
	
	class Line implements Comparable<Line>{
		int x;
		int y0;
		int y1;
		
		Point[] ps;
		
		Line (int x, int y0, int y1){
			this.x = x;
			this.y0 = y0;
			this.y1 = y1;
			ps = new Point[2];
		}

		int distance() {
			int dx = ps[0].x - ps[1].x;
			int dy = ps[0].y - ps[1].y;
			return Math.abs(dx) + Math.abs(dy);
		}
			
		@Override
		public int compareTo(Line o) {
			return this.x - o.x;
		}
		
		@Override
		public boolean equals(Object obj) {
			Line o = (Line) obj;
			return o.x == x && o.y0 == y0 && o.y1 == y1;
		}
		
		@Override
		public String toString() {
			return ps[0].toString() + "->" + ps[1].toString();
		}
	}
	
	int N;
	Point[] ps;
	
	boolean intersect(Point a, Point b, Point c, Point d) {  // 左 a 右 b 下 c 上 d
		return a.x < c.x && b.x > c.x && c.y < a.y && d.y > a.y;
	}
	
	long solve() {
		
		TreeSet<Line> lines = new TreeSet<Line>();
		long ans = 0;
		
		Arrays.sort(ps, 0, N, new Comparator<Point>() {
			
			@Override
			public int compare(Point o1, Point o2) {
				return o1.x != o2.x ? o1.x - o2.x : o1.y - o2.y;
			}
		});
		
		for (int i = 0; i < N;) {  // 按x轴方向扫描
			
			int j = i;
			while (j < N && ps[j].x == ps[i].x) ++j;
			if (((j - i) & 1) != 0) return -1;
			else {
				for (int k = i; k < j; k += 2) {
					ps[k].add(ps[k + 1]);
					ps[k + 1].add(ps[k]);
					Line line = new Line(ps[k].x, ps[k].y, ps[k + 1].y);
					line.ps[0] = ps[k];
					line.ps[1] = ps[k + 1];
					ans += line.distance();
					lines.add(line);
				}
			}
			i = j;
		}
		
		Arrays.sort(ps, 0, N, new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				return o1.y != o2.y ? o1.y - o2.y : o1.x - o2.x;
			}
		});
		
		for (int i = 0; i < N;) { //按y轴方向扫描
			int j = i;
			while (j < N && ps[j].y == ps[i].y) ++j;
			if (((j - i) & 1) != 0) return -1;
			else {
				for (int k = i; k < j; k +=2) {
					ps[k].add(ps[k + 1]);
					ps[k + 1].add(ps[k]);
					
					Line l1 = new Line(ps[k].x, 0, 0);
					Line l2 = new Line(ps[k + 1].x, 0, 0);
					l1.ps[0] = ps[k];
					l1.ps[1] = ps[k + 1];
					
					for (Line line : lines.subSet(l1, l2)) {
						if (intersect(l1.ps[0], l1.ps[1], line.ps[0], line.ps[1])) return -1;
					}
					ans += l1.distance();
				}
			}
			i = j;
		}
		
		Point crt = ps[0];
		Point pre = null;
		int cnt = 0;
		do {
			cnt ++;
			Point tmp = crt;
			if (pre == crt.nxt[0]) {
				crt = crt.nxt[1];
			}
			else{
				crt = crt.nxt[0];
			}
			pre = tmp;
		}
		while (!crt.equals(ps[0]));
		return cnt == N ? ans : -1;
	}
	
	void read() {
		int T = ni();
		while (T --> 0) {
			N = ni();
			ps= new Point[N];
			
			for (int i = 0; i < N; ++i) {
				int x = ni();
				int y = ni();
				ps[i] = new Point(x, y);
			}
			out.println(solve());
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

