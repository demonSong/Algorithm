package com.daimens.algorithm.september;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class SolutionDay22_P2217 {
	
	String INPUT = "./data/judge/201709/P2217.txt";
	
	static Scanner in;
	public static void main(String[] args) throws IOException {
		in = new Scanner(System.in);
		new SolutionDay22_P2217().run();
	}
	
	void read() {
		int t = in.nextInt();
		in.nextLine();
		while (t --> 0) {
			String S = in.nextLine();
			String T = in.nextLine();
			StringBuilder sb = new StringBuilder();
			sb.append(S + '$' + T);
			SuffixArray sa = new SuffixArray(sb.toString().toCharArray());
			int max = 0;
			for (int i = 0; i < sb.length(); ++i) {
				if (sa.sa[i] < S.length() != sa.sa[i + 1] < S.length())
					max = Math.max(max, sa.lcp[i]);
			}
			System.out.println("Nejdelsi spolecny retezec ma delku " + max + ".");
		}
	}

	class SuffixArray{
		int k = 1;
		int n;
		Integer[] sa;
		int[] rank, tmp;
		int[] lcp;
		
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
			for (int i = 0; i <= n; ++i) rank[sa[i]] = i;  //求出每个后缀的当前排名
			int h = 0;
			for (int i = 0; i < n; ++i) {
				int j = sa[rank[i] - 1]; // 后缀i的前一个后缀
				if (h > 0) h--;
				for (;j + h < n && i + h < n; ++h) {
					if (cs[j + h] != cs[i + h]) break;
				}
				lcp[rank[i] -1] = h;
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
	
	void run() throws IOException {
		read();
	}
}

