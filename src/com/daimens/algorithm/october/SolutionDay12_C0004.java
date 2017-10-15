package com.daimens.algorithm.october;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SolutionDay12_C0004 {
	
	String INPUT = "./data/judge/201710/C0004.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay12_C0004().run();
	}
	
	void swap(int[] arra, int i, int j) {
		int tmp = arra[i];
		arra[i] = arra[j];
		arra[j] = tmp;
	}
	
	void unsort(int[] arra, int l, int r) {
		if (l >= r - 1)	return;
		if (cnt == k) return;
		int mid = (l + r) / 2;
		swap(arra, mid, mid - 1);
		cnt += 2;
		if (cnt != k) unsort(arra, l, mid);
		if (cnt != k) unsort(arra, mid, r);
	}
	
	int cnt = 1, k;
	void read() {
		int n = ni();
		k = ni();
		int[] arra = new int[n];
		for (int i = 1; i <= n; ++i) {
			arra[i - 1] = i;
		}
		
		if (k % 2 == 0) {
			out.println(-1);
			return;
		}
		
		unsort(arra, 0, n);
		if (k != cnt) {
			out.println(-1);
		}
		else {
			for (int i = 0; i < n; ++i) {
				out.print(arra[i] + (i + 1 == n ? "\n" : " "));
			}
		}
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


