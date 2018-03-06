package com.daimens.algorithm.june;

import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * @author DemonSong
 * 
 *         3685.Matrix
 * 
 *         Description
 * 
 *         Given a N × N matrix A, whose element in the i-th row and j-th column
 *         Aij is an number that equals i2 + 100000 × i + j2 - 100000 × j + i ×
 *         j, you are to find the M-th smallest element in the matrix.
 * 
 *         Input
 * 
 *         The first line of input is the number of test case. For each test
 *         case there is only one line contains two integers, N(1 ≤ N ≤ 50,000)
 *         and M(1 ≤ M ≤ N × N). There is a blank line before each test case.
 * 
 *         Output
 * 
 *         For each test case output the answer on a single line.
 * 
 *         Sample Input
 * 
 *         12
 * 
 *         1 1
 * 
 *         2 1
 * 
 *         2 2
 * 
 *         2 3
 * 
 *         2 4
 * 
 *         3 1
 * 
 *         3 2
 * 
 *         3 8
 * 
 *         3 9
 * 
 *         5 1
 * 
 *         5 25
 * 
 *         5 10 Sample Output
 * 
 *         3 -99993 3 12 100007 -199987 -99993 100019 200013 -399969 400031
 *         -99939
 *
 */
public class SolutionDay23_P3685 {
	
	static int n;
	static long m;
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		while (N-->0){
			n = in.nextInt();
			m = in.nextLong();
			long lf = -25000000001l;
			long rt = 25000000001l;
			while (rt - lf > 1){
				long mid = (rt + lf) / 2;
				if (check(mid)) rt = mid;
				else lf = mid;
			}
			System.out.println(rt);
		}
		in.close();
	}
	
	public static long f(long i, long j){
		return i * i + 100000 * i + j * j - 100000 * j + i * j;
	}
	
	public static boolean check(long mid){
		
		long cnt = 0;
		for (int j = 1; j <= n; ++j){
			cnt += binarySearch(j, mid);
		}
		return cnt >= m;
	}
	
	public static long binarySearch(int j, long key){
		int lf = 1, rt = n;
		while (lf < rt){
			int mid = lf + (rt - lf + 1) / 2;
			if (f(mid, j) > key) rt = mid - 1;
			else lf = mid;
		}
		if (f(lf, j) <= key) return lf;
		return 0;
	}
	
//	static class Scanner {
//
//		private BufferedReader br;
//		private StringTokenizer tok;
//
//		public Scanner(InputStream is) throws IOException {
//			br = new BufferedReader(new InputStreamReader(is));
//			getLine();
//		}
//
//		private void getLine() throws IOException {
//			while (tok == null || !tok.hasMoreTokens()) {
//				tok = new StringTokenizer(br.readLine());
//			}
//		}
//
//		private boolean hasNext() {
//			return tok.hasMoreTokens();
//		}
//
//		public String next() throws IOException {
//			if (hasNext()) {
//				return tok.nextToken();
//			} else {
//				getLine();
//				return tok.nextToken();
//			}
//		}
//
//		public int nextInt() throws IOException {
//			if (hasNext()) {
//				return Integer.parseInt(tok.nextToken());
//			} else {
//				getLine();
//				return Integer.parseInt(tok.nextToken());
//			}
//		}
//
//		public long nextLong() throws IOException {
//			if (hasNext()) {
//				return Long.parseLong(tok.nextToken());
//			} else {
//				getLine();
//				return Long.parseLong(tok.nextToken());
//			}
//		}
//
//		public double nextDouble() throws IOException {
//			if (hasNext()) {
//				return Double.parseDouble(tok.nextToken());
//			} else {
//				getLine();
//				return Double.parseDouble(tok.nextToken());
//			}
//		}
//	}
}
