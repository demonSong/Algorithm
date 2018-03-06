package com.daimens.algorithm.september;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SolutionDay28_P2932 {
	
	String INPUT = "./data/judge/201709/P2932.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay28_P2932().run();
	}
	
	static final int MAX_N = 40000 + 16;
	
	class E implements Comparable<E>{
		
		double x;
		int id;
		
		E(double x, int id){
			this.x  = x;
			this.id = id;
		}

		@Override
		public int compareTo(E o) {
			return Double.compare(x, o.x);
		}
		
		@Override
		public boolean equals(Object obj) {
			return compareTo((E)(obj)) == 0;
		}
	}
	
	class C{
		
		double x;
		double y;
		double r;
		
		C(double r, double x, double y){
			this.x = x;
			this.y = y;
			this.r = r;
		}
	}
	
	int N;
	C[] cs = new C[MAX_N];
	E[] xs = new E[2 * MAX_N];
	
	// j 是否包含于 i
	boolean inside(int i, int j) {
		double dx = cs[i].x - cs[j].x;
		double dy = cs[i].y - cs[j].y;
		return dx * dx + dy * dy <= cs[i].r * cs[i].r;
	}
	
	void solve() {
		
		for (int i = 0; i < N; ++i) {
			C c = cs[i];
			xs[2 * i]     = new E(c.x - c.r, i);
			xs[2 * i + 1] = new E(c.x + c.r, i + N);
		}
		
		Arrays.sort(xs, 0, 2 * N);
		
		TreeSet<E> set = new TreeSet<E>();
		List<Integer> ans = new ArrayList<Integer>();
		for (int i = 0; i < 2 * N; ++i) {
			E e = xs[i];
			int id = e.id % N;
			E crtC = new E(cs[id].y, id);
			if (e.id < N) {
				E up = set.ceiling(crtC);
				E dn = set.floor(crtC);
				if (up != null && inside(up.id, id)) continue;
				if (dn != null && inside(dn.id, id)) continue;
				ans.add(id);
				set.add(crtC);
			}
			else {
				set.remove(crtC);
			}
		}
		
		Collections.sort(ans);
		out.println(ans.size());
		for (int i = 0; i < ans.size(); ++i) {
			out.print((ans.get(i) + 1) + (i + 1 == ans.size() ? "\n" : " "));
		}
	}
	
	void read() {
		N = ni();
		for (int i = 0; i < N; ++i) {
			cs[i] = new C(nd(), nd(), nd());
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


