package com.daimens.algorithm.september;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SolutionDay28_A2201 {
	
	String INPUT = "./data/judge/201709/A2201.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay28_A2201().run();
	}
	
	static final double PI  = Math.acos(-1);
	static final double EPS = 1E-12;
	
	class Point{
		
		double x;
		double y;
		
		Point(double x, double y){
			this.x = x;
			this.y = y;
		}
		
		Point add(Point a) {
			return new Point(x + a.x, y + a.y);
		}
		
		Point sub(Point a) {
			return new Point(x - a.x, y - a.y);
		}
		
		Point rot(double rad) {
			double a = Math.cos(rad);
			double b = Math.sin(rad);
			return new Point(x * a - b * y, x * b + a * y);
		}
		
		double abs() {
			return Math.sqrt(x * x + y * y);
		}
	}
	
	double dist(Point a, Point b) {
		double dx = a.x - b.x;
		double dy = a.y - b.y;
		return Math.sqrt(dx * dx + dy * dy);
	}
	
	double dot(Point a, Point b) {
		return a.x * b.y - a.y * b.x;
	}
	
	class Line{
		
		Point a;
		Point b;
		
		Line(Point a, Point b){
			this.a = a;
			this.b = b;
		}
		
		double distance(Point p) {
			double d = dist(a, b);
			return Math.abs(dot(p.sub(a), b.sub(a)) / d);
		}
	}
	
	class Circle{
		
		Point o;
		double r;
		
		Circle(Point o, double r){
			this.o = o;
			this.r = r;
		}
		
		// 通过点p 的两条切线
		List<Point> tangent(Point p){
			double L = dist(o, p);
			double M = Math.sqrt(L * L - r * r);
			double theta = Math.asin(r / L);
			Point v = o.sub(p);
			v.x /= L;
			v.y /= L;
			List<Point> ans = new ArrayList<>();
			Point t = v.rot(theta);
			t.x *= M;
			t.y *= M;
			ans.add(p.add(t));
			t = v.rot(-theta);
			t.x *= M;
			t.y *= M;
			ans.add(p.add(t));
			return ans;
		}
		
		// 两个半径相等圆的两条平行外切线
		List<Line> outer_tangent_parallel(Circle c){
			Point d = o.sub(c.o);
			Point v = new Point(-r / d.abs() * d.y, r / d.abs() * d.x);
			List<Line> lines = new ArrayList<>();
			lines.add(new Line(o.add(v), c.o.add(v)));
			lines.add(new Line(o.sub(v), c.o.sub(v)));
			return lines;
		}
		
		// 两圆外切线
		List<Line> outer_tangent(Circle c){
			if (cmp(r, c.r) == 0) {
				return outer_tangent_parallel(c);
			}
			if (cmp(r, c.r) == 1) {
				return c.outer_tangent(this);
			}
			Point d = o.sub(c.o);
			double fact = c.r / r - 1;
			Point base = c.o.add(d).add(new Point(d.x / fact, d.y / fact));
			List<Point> ps = tangent(base);
			List<Line> ans = new ArrayList<>();
			ans.add(new Line(base, ps.get(0)));
			ans.add(new Line(base, ps.get(1)));
			return ans;
		}
		
		// 两圆内切线
		List<Line> inner_tangent(Circle c){
			if (cmp(r, c.r) == 1) {
				return c.inner_tangent(this);
			}
			Point d = c.o.sub(o);
			double fact = c.r / r + 1;
			Point base = o.add(new Point(d.x / fact, d.y / fact));
			List<Point> ps = tangent(base);
			List<Line> ans = new ArrayList<>();
			ans.add(new Line(base, ps.get(0)));
			ans.add(new Line(base, ps.get(1)));
			return ans;
		}
		
		// 两个圆的交点
		Line intersection(Circle c) {
			double d = dist(o, c.o);
			double cos = Math.cos((d * d + r * r - c.r * c.r) / (2 * d * r));
			Point e = c.o.sub(o);
			e.x /= d;
			e.y /= d;
			Point t1 = e.rot(cos);
			t1.x *= r;
			t1.y *= r;
			Point t2 = e.rot(-cos);
			t2.x *= r;
			t2.y *= r;
			return new Line(o.add(t1), o.add(t2));
		}
		
		// 是否相离
		boolean independent(Circle c) {
			return cmp(dist(o, c.o), r + c.r) > 0;
		}
		
		// 是否包含圆c
		boolean contains(Circle c) {
			return cmp(dist(o, c.o) + c.r, r) < 0;
		}
		
		boolean intersects(Circle c) {
			return !contains(c) && !c.contains(this) && !independent(c);
		}
	}
	
	class Pair{
		
		Circle fir;
		Circle sec;
		
		Pair(Circle fir, Circle sec){
			this.fir = fir;
			this.sec = sec;
		}
	}
	
	int cmp(double a, double b) {
		double diff = a - b;
		if (Math.abs(diff) < EPS) return 0;
		else if (diff < 0) return -1;
		else return 1;
	}
	
	List<Pair> jewels;
	List<Line> lines;
	int N;
	
	void constructLine(Circle c1, Circle c2, List<Line> lines) {
		// 两圆相交 && 两圆相离
		if (c1.independent(c2)) { // 相离
			List<Line> outer = c1.outer_tangent(c2);
			lines.add(outer.get(0));
			lines.add(outer.get(1));
			List<Line> inner = c1.inner_tangent(c2);
			lines.add(inner.get(0));
			lines.add(inner.get(1));
		}
		
		if (c1.intersects(c2)) {  // 相交
			List<Line> outer = c1.outer_tangent(c2);
			lines.add(outer.get(0));
			lines.add(outer.get(1));
			Line inter = c1.intersection(c2);
			lines.add(inter);
		}
	}
	
	int count(List<Pair> jewels, Line line) {
		int cnt = 0;
		for (Pair j : jewels) {
			if (cmp(j.fir.r, line.distance(j.fir.o)) <= 0 && cmp(j.sec.r, line.distance(j.sec.o)) >= 0) {
				cnt ++;
			}
		}
		return cnt;
	}
	
	void read() {
		while (true) {
			N = ni();
			if (N == 0) break;
			
			jewels = new ArrayList<>();
			lines  = new ArrayList<>();
			
			for (int i = 0; i < N; ++i) {
				double x, y, r, m;
				x = nd();
				y = nd();
				r = nd();
				m = nd();
				Pair jewel = new Pair(new Circle(new Point(x, y), r), new Circle(new Point(x, y), r + m));
				for (Pair p : jewels) {
					constructLine(jewel.fir, p.fir, lines);
					constructLine(jewel.fir, p.sec, lines);
					constructLine(jewel.sec, p.fir, lines);
					constructLine(jewel.sec, p.sec, lines);
				}
				jewels.add(jewel);
			}
			
			int ans = 1;
			for (Line line : lines) {
				ans = Math.max(ans, count(jewels, line));
			}
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

