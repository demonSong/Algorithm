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

public class SolutionDay22_P3415 {
	
	String INPUT = "./data/judge/201709/P3415.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay22_P3415().run();
	}
	
	class SuffixArray{
		int k = 1;
		int n;
		Integer[] sa;
		int[] rank, tmp, lcp;
		
		SuffixArray(char[] cs){
			n    = cs.length; 
			sa   = new Integer[n + 1];
			rank = new int[n + 1];
			tmp  = new int[n + 1];
			lcp  = new int[n];
			
			for (int i = 0; i <= n; ++i) {
				sa[i] = i;
				rank[i] = i < n ? cs[i] : -1;
			}
			
			for (k = 1; k <= n; k <<= 1) {
				Arrays.sort(sa, cmp);
				tmp[sa[0]] = 0;
				for (int i = 1; i <= n; ++i) {
					tmp[sa[i]] = tmp[sa[i - 1]] + (cmp.compare(sa[i - 1], sa[i]) < 0 ? 1 : 0);
				}
				for (int i = 0; i <= n; ++i) {
					rank[i] = tmp[i];
				}
			}
			
			// lcp
			int h = 0;
			for (int i = 0; i <= n; ++i) rank[sa[i]] = i;
			for (int i = 0; i < n; ++i) {
				int j = sa[rank[i] - 1];
				if (h > 0) h --;
				for (;j + h < n && i + h < n; ++h) {
					if (cs[j + h] != cs[i + h]) break;
				}
				lcp[rank[i] - 1] = h;
			}
		}
		
		Comparator<Integer> cmp = new Comparator<Integer>() {
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
	
	void read() {
		while (true) {
			int k = ni();
			if (k == 0) break;
			String s = ns();
			String t = ns();
			SuffixArray sa = new SuffixArray((s + '$' + t).toCharArray());
			long ans = 0;
			ans += solve(true, s.length(), k, sa);
			ans += solve(false, s.length(), k, sa);
			out.println(ans);
		}
	}
	
	int[][] stack;
	int top;
	long sum;
	long solve(boolean isS, int sLen, int k, SuffixArray sa) {
		long ans = 0;
		sum = 0;
		top = 0;
		stack = new int[sa.n + 16][2];
		for (int i = 0; i < sa.n; ++i) {
			if (sa.lcp[i] < k) {
				top = 0;
				sum = 0;
			}
			else {
				int size = 0;
				if ((isS && sa.sa[i] < sLen) || (!isS && sa.sa[i] > sLen)) {
					size ++;
					sum += sa.lcp[i] - k + 1;
				}
				while (top > 0 && sa.lcp[i] < stack[top - 1][0]) {
					--top;
					sum -= stack[top][1] * (stack[top][0] - sa.lcp[i]);
					size += stack[top][1];
				}
				if (size > 0) {
					stack[top][0] = sa.lcp[i];
					stack[top][1] = size;
					top ++;
				}
				
				if ((isS && sa.sa[i + 1] > sLen) || (!isS && sa.sa[i + 1] < sLen)) {
					ans += sum;
				}
			}
		}
		return ans;
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


//void read() {
//	int tot = 0;
//	while (true) {
//		int k = ni();
//		if (k == 0) break;
//		String s = ns();
//		String t = ns();
//		SuffixArray sa = new SuffixArray(((char)('z' + 1) + s + '$' + t).toCharArray());
////		StringBuilder sb = new StringBuilder((char)('z' + 1) + s + '$' + t);
////		for (int i = 0; i < sb.length(); ++i) {
////			System.out.println(sa.sa[i] + "    " + sb.toString().substring(sa.sa[i]) + "   " + sa.lcp[i]);
////		}
//		long ans = 0, sumA = 0, sumB = 0;
//		int[][] stack = new int[sa.n + 16][2];
//		int top1 = 0;
//		int top2 = 0;
//		int cntA = 0;
//		int cntB = 0;
//		for (int i = 0; i < sa.n; ++i) {
//			if (sa.sa[i] >= 1 && sa.sa[i] < s.length() + 1) { // A
//				if (sa.lcp[i] < k) {
//					top1 = 0;
//					cntA = 0;
//					sumA = 0;
//				}
//				else {
//					while (top1 > 0 && stack[top1 - 1][0] > sa.lcp[i]) {
//						cntA ++;
//						sumA -= stack[top1 - 1][0] - k + 1;
//						top1 --;
//					}
//					stack[top1++][0] = sa.lcp[i];
//					sumA += stack[top1 - 1][0] - k + 1;
//				}
//				
////				for (int j = 0; j < top2; ++j) {
////					ans += stack[j][1] - k + 1;
////				}
//				ans += sumB;
//				
//				if (top2 - 1 >= 0) ans += cntB * (stack[top2 - 1][1] - k + 1);
//				
//				if (sa.lcp[i] < k) {
//					cntB = 0;
//					top2 = 0;
//					sumB = 0;
//				}
//				
//				// 计算完毕之后，重新更新B的栈
//				while (top2 > 0 && stack[top2 - 1][1] > sa.lcp[i]) {
//					cntB ++;
//					sumB -= stack[top2 - 1][1] - k + 1;
//					top2 --;
//				}
//			}
//			else if (sa.sa[i] > s.length() + 1) { // B
//				if (sa.lcp[i] < k) {
//					top2 = 0;
//					cntB = 0;
//					sumB = 0;
//				}
//				else {
//					while (top2 > 0 && stack[top2 - 1][1] > sa.lcp[i]) {
//						cntB ++;
//						sumB -= stack[top2 - 1][1] - k + 1;
//						top2 --;
//					}
//					stack[top2++][1] = sa.lcp[i];
//					sumB += stack[top2 - 1][1] - k + 1;
//				}
////				for (int j = 0; j < top1; ++j) {
////					ans += stack[j][0] - k + 1;
////				}
//				
//				ans += sumA;
//				
//				if (top1 - 1 >= 0) ans += cntA * (stack[top1 - 1][0] - k + 1);
//				
//				if (sa.lcp[i] < k) {
//					cntA = 0;
//					top1 = 0;
//					sumA = 0;
//				}
//				
//				// 计算完毕之后，重新更新A的栈
//				while (top1 > 0 && stack[top1 - 1][0] > sa.lcp[i]) {
//					cntA ++;
//					sumA -= stack[top1 - 1][0] - k + 1;
//					top1 --;
//				}
//			}
//		}
//		out.println(ans);
//		System.out.println(ans == cmp[tot++]);
//	}
//}

//void read() {
//	int tot = 0;
//	while (true) {
//		int k = ni();
//		if (k == 0) break;
//		String s = ns();
//		String t = ns();
//		SuffixArray sa = new SuffixArray(((char)('z' + 1) + s + '$' + t).toCharArray());
//		long ans = 0;
//		for (int i = 0; i < sa.n; ++i) {
//			if (sa.sa[i] >= 1 && sa.sa[i] < s.length() + 1 && sa.lcp[i] >= k) {
//				int min = sa.lcp[i];
//				for (int j = i + 1; j < sa.n; ++j){ 
//					if (sa.sa[j] > s.length() + 1) {
//						min = Math.min(sa.lcp[j - 1], min);
//						ans += min - k + 1;
//					}
//					
//					if (sa.lcp[j] < k) break;
//				}
//				
//			}
//			else if (sa.sa[i] > s.length() + 1 && sa.lcp[i] >= k){
//				int min = sa.lcp[i];
//				for (int j = i + 1; j < sa.n; ++j) {
//					if (sa.sa[j] < s.length() + 1 && sa.sa[j] >= 1) {
//						min = Math.min(min, sa.lcp[j - 1]);
//						ans += min - k + 1;
//					}
//					
//					if (sa.lcp[j] < k) break;
//				}
//			}
//		}
//		out.println(ans);
//		System.out.println(ans == cmp[tot++]);
//	}
//}
//




