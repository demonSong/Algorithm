package com.daimens.algorithm.september;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SolutionDay11_A1070 {
	
	String INPUT = "./data/judge/201709/A1070.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay11_A1070().run();
	}
	
	static final int MAX_N = 20000 + 16;
	
	int len;
	int[] seq;
	
	class FimoSequence{
		
		class FirstHalf{
			Queue<Integer> data;
			Queue<Integer> MAX, MIN;
			
			public FirstHalf() {
				data = new ArrayDeque<>();
				MAX  = new ArrayDeque<>();
				MIN  = new ArrayDeque<>();
			}
			
			void add(int x) {
				data.offer(x);
				if (data.size() == 1) {
					MAX.offer(x);
					MIN.offer(x);
				}
				else {
					if (MAX.peek() < x) {
						MAX.offer(x);
					}
					
					if (MIN.peek() > x) {
						MIN.offer(x);
					}
				}
			}
			
			int remove() {
				int x = data.peek();
				data.poll();
				if (MIN.peek() == x) {
					MIN.poll();
				}
				if (MAX.peek() == x) {
					MAX.poll();
				}
				return x;
			}
			
			int size() {
				return data.size();
			}
			
		}
		
		class SecondHalf{
			Deque<Integer> data;
			Deque<Integer> MAX, MIN;
			
			public SecondHalf() {
				data = new ArrayDeque<>();
				MAX  = new ArrayDeque<>();
				MIN  = new ArrayDeque<>();
			}
			
			void add(int x) {
				data.offer(x);
				if (data.size() == 1) {
					MAX.offer(x);
					MIN.offer(x);
				}
				else {
					while (!MAX.isEmpty() && MAX.peek() < x) {
						MAX.poll();
					}
					while (!MIN.isEmpty() && MIN.peek() > x) {
						MIN.poll();
					}
					MAX.offer(x);
					MIN.offer(x);
				}
			}
			
			int remove() {
				int x = data.peekFirst();
				data.pollFirst();
				if (MIN.peekFirst() == x) {
					MIN.pollFirst();
				}
				if (MAX.peekFirst() == x) {
					MAX.pollFirst();
				}
				return x;
			}
			
			int size() {
				return data.size();
			}
		}
		
		FirstHalf  fh;
		SecondHalf sh;
		public FimoSequence() {
			fh = new FirstHalf();
			sh = new SecondHalf();
		}
		
		int size() {
			return sh.size() + fh.size();
		}
		
		void add(int x) {
			sh.add(x);
			if (sh.size() > fh.size()) {
				fh.add(sh.remove());
			}
		}
		
		int min_fh(int i) {
			Integer[] ans = fh.MIN.toArray(new Integer[0]);
			return ans[fh.MIN.size() - i];
		}
		
		
		int min_sh(int i) {
			Integer[] ans = sh.MIN.toArray(new Integer[0]);
			return ans[i - 1];
		}
		
		int max_fh(int i) {
			Integer[] ans = fh.MAX.toArray(new Integer[0]);
			return ans[fh.MAX.size() - i];
		}
		
		int max_sh(int i) {
			Integer[] ans = sh.MAX.toArray(new Integer[0]);
			return ans[i - 1];
		}
		
		int remove() {
			int r = fh.remove();
			if (sh.size() > fh.size()) {
				fh.add(sh.remove());
			}
			return r;
		}
		
	}
	
	FimoSequence fimo;
	
	void solve() {
		while (more()) {
			int N = ni();
			seq = new int[MAX_N];
			len = 0;
			fimo = new FimoSequence();
			
			for (int i = 0; i < N; ++i) {
				int q = ni();
				switch(q) {
					case 0:
						int t = ni();
						fimo.add(t);
						break;
					case 1:
						out.println(fimo.remove());
						break;
					case 2:
						out.println(fimo.min_fh(1));
						break;
					case 3:
						out.println(fimo.min_sh(1));
						break;
					case 4:
						t = ni();
						out.println(fimo.min_fh(t));
						break;
					case 5:
						t = ni();
						out.println(fimo.min_sh(t));
						break;
					case 6:
						out.printf("%d\n", fimo.max_fh(1));
	                    break;
	                case 7:
	                	out.printf("%d\n", fimo.max_sh(1));
	                    break;
	                case 8:
	                    t = ni();
	                    out.printf("%d\n", fimo.max_fh(t));
	                    break;
	                case 9:
	                	t = ni();
	                	out.printf("%d\n", fimo.max_sh(t));
	                    break;
				}
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
	
	static class ArrayUtils {

		public static void fill(int[][] f, int value) {
			for (int i = 0; i < f.length; ++i) {
				Arrays.fill(f[i], value);
			}
		}
		
		public static void fill(int[][][] f, int value) {
			for (int i = 0; i < f.length; ++i) {
				fill(f[i], value);
			}
		}
		
		public static void fill(int[][][][] f, int value) {
			for (int i = 0; i < f.length; ++i) {
				fill(f[i], value);
			}
		}
	}
}

