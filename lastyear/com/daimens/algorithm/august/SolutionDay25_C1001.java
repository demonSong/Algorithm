package com.daimens.algorithm.august;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SolutionDay25_C1001{
	String INPUT = "./data/judge/201708/T1003.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay25_C1001().run();
	}
	
	class Pair {
		int val, id;

		public Pair(int val, int id) {
			super();
			this.val = val;
			this.id = id;
		}
		
	}
	
	void solve() {
		while (more()){
			int n = ni();
			Pair[] a=  new Pair[n];
			for (int i = 0; i < n; i++) {
				a[i] = new Pair(ni(), i);
			}
			Arrays.sort(a, (o1, o2) -> Integer.compare(o1.val, o2.val));
			
			boolean[] used = new boolean[n];
			List<List<Integer>> ans = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				if (!used[i]) {
					int cur = i;
					List<Integer> cycle = new ArrayList<>();
					do {
						cycle.add(cur);
						used[cur] = true;
						cur = a[cur].id;
					} while (cur != i);
					ans.add(cycle);
				}
			}
			out.println(ans.size());
			for (List<Integer> c : ans) {
				out.print(c.size());
				for (int x : c) {
					out.print(" " + (x + 1));
				}
				out.println();
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
		solve();
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
