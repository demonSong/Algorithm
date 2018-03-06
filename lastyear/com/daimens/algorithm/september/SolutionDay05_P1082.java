package com.daimens.algorithm.september;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SolutionDay05_P1082 {
	
	String INPUT = "./data/judge/201709/P1082.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay05_P1082().run();
	}
	
	static int[] leap_month    = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	static int[] nonleap_month = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	boolean[][][] dp = new boolean[200][13][32];
	
	void solve() {
		int T = ni();
		
		DP();
		while (T --> 0) {
			int year  = ni();
			int month = ni();
			int day   = ni();
			out.println(dp[year - 1900][month][day] ? "YES" : "NO");
		}
	}
	
	void DP() {
		dp[2001 - 1900][11][3] = true;
		dp[2001 - 1900][11][2] = false;
		dp[2001 - 1900][11][1] = true;
		for (int j = 4; j <= 30; ++j) dp[2001 - 1900][11][j] = true;
		
		// 2001
		for (int mon = 10; mon >= 1; --mon) {
			for (int j = nonleap_month[mon]; j >= 1; --j) {
				// 当前月是否为最后一天
				if (j == nonleap_month[mon]) {
					dp[101][mon][j] |= !dp[101][mon + 1][1];
				}
				else {
					dp[101][mon][j] |= !dp[101][mon][j + 1];
				}
				
				if (j <= nonleap_month[mon + 1]) {
					dp[101][mon][j] |= !dp[101][mon + 1][j];
				}
			}
		}
		
		for (int year = 2000 - 1900; year >= 0; --year) {
			int[] month = leap(year) ? leap_month : nonleap_month;
			for (int mon = 12; mon >= 1; --mon) {
				for (int day = month[mon]; day >= 1; --day) {
					if (mon == 12 && day == month[mon]) { // 1 年中的最后一天
						dp[year][mon][day] = !dp[year + 1][1][1] || !dp[year + 1][1][day];
					}
					else if (day == month[mon]) { // 1个月中的最后一天
						dp[year][mon][day] |= !dp[year][mon + 1][1];
						if (day <= month[mon + 1]) {
							dp[year][mon][day] |= !dp[year][mon + 1][day];
						}
					}
					else {  // 其他
						dp[year][mon][day] |= !dp[year][mon][day + 1];
						if (day <= month[mon % 12 + 1]) {
							dp[year][mon][day] |= !dp[year][mon % 12 + 1][day];
						}
					}
				}
			}
		}
	}
	
	boolean leap(int year){
        return (year % 4 == 0 && year % 100 != 0 || year % 400 == 0);
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

