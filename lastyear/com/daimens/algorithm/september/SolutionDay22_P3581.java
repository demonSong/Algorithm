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

public class SolutionDay22_P3581 {
	
	String INPUT = "./data/judge/201709/P3581.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay22_P3581().run();
	}
	
	void read() {
		int n = ni();
		int[] a = new int[n];
		for (int i = 0; i < n; ++i) a[i] = ni();
		int[] rev = reverse(a, 0, n - 1);
		SuffixArray sa = new SuffixArray(rev);
		int p1 = 0;
		for (int i = 0; i < n; ++i) {
			p1 = n - sa.sa[i];
			if (p1 >= 1 && n - p1 >= 2) break;
		}
		
		int m = n - p1;
		rev = reverse(merge(a, p1, n - 1), 0, 2 * m - 1);
		sa = new SuffixArray(rev);
		int p2 = 0;
		for (int i = 0; i <= 2 * m; ++i) {
			p2 = p1 + m - sa.sa[i];
			if (p2 - p1 >= 1 && n - p2 >= 1) break;
		}
		
		int[] rev1 = reverse(a, 0, p1 - 1);
		int[] rev2 = reverse(a, p1, p2 - 1);
		int[] rev3 = reverse(a, p2, n - 1);
		for (int i = 0; i < n; ++i) {
			if (i >= 0 && i <= p1 - 1) out.println(rev1[i]);
			else if (i >= p1 && i <= p2 - 1) out.println(rev2[i]);
			else out.println(rev3[i]);
		}
	}
	
	class SuffixArray{
		int k = 1;
		int n;
		Integer[] sa;
		int[] rank, tmp;
		
		SuffixArray(int[] arra){
			n    = arra.length;
			sa   = new Integer[n + 1];
			rank = new int[n + 1];
			tmp  = new int[n + 1];
			
			for (int i = 0; i <= n; ++i) {
				sa[i] = i;
				rank[i] = i < n ? arra[i] : -1;
			}
			
			for (k = 1; k <= n; k *= 2) {
				Arrays.sort(sa, cmp);
				tmp[sa[0]] = 0;
				for (int i = 1; i <= n; ++i) {
					tmp[sa[i]] = tmp[sa[i - 1]] + (cmp.compare(sa[i - 1], sa[i]) < 0 ? 1 : 0);
				}
				
				for (int i = 0; i <= n; ++i) {
					rank[i] = tmp[i];
				}
			}
		}
		
		private Comparator<Integer> cmp = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				int i = o1;
				int j = o2;
				if (rank[i] != rank[j]) return rank[i] - rank[j];
				else {
					int ri = i + k <= n ? rank[i + k] : -1;
					int rj = j + k <= n ? rank[j + k] : -1;
					return ri - rj;
				}
			}
		}; 
	}
	
	
	void swap(int[] arra, int i, int j) {
		int tmp = arra[i];
		arra[i] = arra[j];
		arra[j] = tmp;
	}
	
	int[] reverse(int[] a, int i, int j) {
		int n = a.length;
		int[] rev = new int[n];
		for (int k = 0; k < n; ++k) rev[k] = a[k];
		while (i < j) {
			swap(rev, i++, j--);
		}
		return rev;
	}
	
	int[] merge(int[] a, int i, int j) {
		int n = j - i + 1;
		n += n;
		int[] merge = new int[n];
		for (int k = 0; k < n / 2; ++k) {
			merge[k] = merge[k + n / 2] = a[i + k];
		}
		return merge;
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


