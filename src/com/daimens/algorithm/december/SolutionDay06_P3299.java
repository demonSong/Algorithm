package com.daimens.algorithm.december;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;

public class SolutionDay06_P3299 {
	
	String INPUT = "./data/judge/201712/P3299.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay06_P3299().run();
	}
	
	double calH(double T, double D) {
		double e = 6.11 * Math.pow(Math.E, 5417.7530 * ((1 / 273.16) - (1 / (D + 273.16))));
		double h = 0.5555 * (e - 10.0);
		return T + h;
	}
	
	double calT(double D, double H) {
		double e = 6.11 * Math.pow(Math.E, 5417.7530 * ((1 / 273.16) - (1 / (D + 273.16))));
		double h = 0.5555 * (e - 10.0);
		return H - h;
	}
	
	double calD(double T, double H) {
		double h = H - T;
		double e = h / 0.5555 + 10.0;
		double x = Math.log(e / 6.11);
		double d = 1 / 273.16 - x / 5417.25;
		return 1 / d - 273.16;
	}
	
	void read() {
		while (true) {
			String line = ns();
			
			if (line.equals("E")) break;
			double T = 500;
			double D = 500;
			double H = 500;
			
			if (line.equals("T")) T = nd();
			else if (line.equals("D")) D= nd();
			else H = nd();
			
			line = ns();
			
			if (line.equals("T")) T = nd();
			else if (line.equals("D")) D = nd();
			else H = nd();
			
			if (T == 500) {
				out.printf("T %.1f D %.1f H %.1f\n", calT(D, H), D, H);
			}
			else if (D == 500) {
				out.printf("T %.1f D %.1f H %.1f\n", T, calD(T, H), H);
			}
			else {
				out.printf("T %.1f D %.1f H %.1f\n", T, D, calH(T, D));
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
	
	static class D{
		
		public static void pp(int[][] board, int row, int col) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < row; ++i) {
				for (int j = 0; j < col; ++j) {
					sb.append(board[i][j] + (j + 1 == col ? "\n" : " "));
				}
			}
			System.out.println(sb.toString());
		}
		
		public static void pp(char[][] board, int row, int col) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < row; ++i) {
				for (int j = 0; j < col; ++j) {
					sb.append(board[i][j] + (j + 1 == col ? "\n" : " "));
				}
			}
			System.out.println(sb.toString());
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
	
	static class Num{
		public static <K> void inc(Map<K, Integer> mem, K k) {
			if (!mem.containsKey(k)) mem.put(k, 0);
			mem.put(k, mem.get(k) + 1);
		}
	}
}

