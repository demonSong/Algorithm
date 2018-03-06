package com.daimens.algorithm.september;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class SolutionDay01_C431B {
	
	String INPUT = "./data/judge/201709/C431B.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay01_C431B().run();
	}
	
	static final double EPS = 1E-8;
	
	void solve() {
		int n = ni();
		int[] points = new int[n];
		
		for (int i = 0; i < n; ++i){
			points[i] = ni();
		}
		
		Set<Double> ans = new HashSet<>();
		for (int i = 0; i < n; ++i){
			for (int j = i + 1; j < n; ++j){
				double k = (points[j] - points[i]) / (1.0 * (j - i));
				ans.add(k);
			}
		}
		
		boolean valid = true;
		boolean[] visited = new boolean[n + 16];
		
		int cnt = 0;
		for (Double k : ans){
			visited[0] = true;
			for (int j = 1; j < n; ++j){
				if (Math.abs((points[j] - points[0]) / (1.0 * j) - k) <= EPS){
					visited[j] = true;
				}
			}
			
			int nxt = -1;
			for (int j = 0; j < n; ++j){
				if (!visited[j]){
					nxt = j;
					break;
				}
			}
			
			if (nxt == -1){
				valid = false;
			}
			else{
				visited[nxt] = true;
				for (int j = 0; j < n; ++j){
					if (!visited[j]){
						if (Math.abs((points[j] - points[nxt]) / (1.0 * (j - nxt)) - k) > EPS){
							valid = false;
							break;
						}
					}
				}
				
				if (valid) break;
				
				valid = true;
				Arrays.fill(visited, false);
				cnt ++;
			}
		}
		if (valid && cnt != ans.size()) out.println("Yes");
		else out.println("No");
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
