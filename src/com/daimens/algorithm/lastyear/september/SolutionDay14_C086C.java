package com.daimens.algorithm.september;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class SolutionDay14_C086C {
	
	String INPUT = "./data/judge/201709/C086C.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay14_C086C().run();
	}
	
	static final int MOD = 1000000009;
	
	int solve(int n, String[] p) {
		int m = p.length;
		
		//求出每个模式的后缀
		Set<String> pfx = new HashSet<>();
		pfx.add("");
		int maxLength = 1;
		for (int i = 0; i < m; ++i) {
			maxLength = Math.max(maxLength, p[i].length());
			for (int j = 1; j <= p[i].length(); ++j) {
				pfx.add(p[i].substring(0, j));
			}
		}
		
		int state = pfx.size();
		Map<String, Integer> mem = new HashMap<>();
		int idx = 0;
		for (String pf : pfx) {
			mem.put(pf, idx++);
		}
		
		//更新每个状态对应的最大子串长度
		int[] finish = new int[state];
		for (String pf : pfx) {
			for (String pp : p) {
				if (pf.endsWith(pp)) {
					finish[mem.get(pf)] = Math.max(finish[mem.get(pf)], pp.length());
				}
			}
		}
		
		//状态转移
		int[][] next = new int[state][4];
		for (String pf : pfx) {
			for (int ch = 0; ch < 4; ++ch) {
				String nxt = pf + "ATCG".charAt(ch);
				while (!mem.containsKey(nxt)) {
					nxt = nxt.substring(1);
				}
				next[mem.get(pf)][ch] = mem.get(nxt);
			}
		}
		
		int[][] dp = new int[state + 16][maxLength + 16];
		dp[mem.get("")][0] = 1;
		for (int i = 1; i <= n; ++i) {
			dp = oneStep(dp, maxLength, state, next, finish);
		}
		
		int ans = 0;
		for (int i = 0; i < state; ++i) {
			ans += dp[i][0];
			if (ans >= MOD) ans -= MOD;
		}
		return ans;
	}
	
	int[][] oneStep(int[][] cnt, int maxLength, int state, int[][] next, int[] finish) {
		int[][] newDp = new int[state + 16][maxLength + 16];
		for (int oldState = 0; oldState < state; ++oldState) {
			for (int oldNeed = 0; oldNeed < maxLength; ++oldNeed) {
				for (int ch = 0; ch < 4; ++ch) {
					int newState = next[oldState][ch];
					int newNeed  = oldNeed + 1;
					if (newNeed <= finish[newState]) newNeed = 0;
					if (newNeed >= maxLength) continue;
					newDp[newState][newNeed] += cnt[oldState][oldNeed];
					if (newDp[newState][newNeed] >= MOD)  newDp[newState][newNeed] -= MOD;
				}
			}
		}
		return newDp;
	}
	
	void read() {
		int n = ni();
		int m = ni();
		String[] p = new String[m];
		for (int i = 0; i < m; ++i) {
			p[i] = ns();
		}
		out.println(solve(n, p));
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


