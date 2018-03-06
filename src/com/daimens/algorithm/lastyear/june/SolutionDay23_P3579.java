package com.daimens.algorithm.june;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

/**
 * 
 * @author DemonSong
 * 
 *         3579.Median
 * 
 *         Description
 * 
 *         Given N numbers, X1, X2, ... , XN, let us calculate the difference of
 *         every pair of numbers: ∣Xi - Xj∣ (1 ≤ i ＜ j ≤ N). We can get C(N,2)
 *         differences through this work, and now your task is to find the
 *         median of the differences as quickly as you can!
 * 
 *         Note in this problem, the median is defined as the (m/2)-th smallest
 *         number if m,the amount of the differences, is even. For example, you
 *         have to find the third smallest one in the case of m = 6.
 * 
 *         Input
 * 
 *         The input consists of several test cases. In each test case, N will
 *         be given in the first line. Then N numbers are given, representing
 *         X1, X2, ... , XN, ( Xi ≤ 1,000,000,000 3 ≤ N ≤ 1,00,000 )
 * 
 *         Output
 * 
 *         For each test case, output the median in a separate line.
 * 
 *         Sample Input
 * 
 *         4 1 3 2 4 3 1 10 2 Sample Output
 * 
 *         1 8
 *
 */
public class SolutionDay23_P3579 {
	
//	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		while (in.hasNextInt()){
//			int N = in.nextInt();
//			int[] nums = new int[N];
//			for (int i = 0; i < N; ++i){
//				nums[i] = in.nextInt();
//			}
//			Arrays.sort(nums);
//			int[] cnt = new int[N-1];
//			for (int i = 0; i < N - 1; ++i){
//				cnt[i] = N - 1 - i;
//			}
//			for (int i = 1; i < N - 1; ++i){
//				cnt[i] += cnt[i - 1];
//			}
//			
//			int key = N * (N - 1) / 4;
//			int len = binarySearch(cnt, key) + 1;
//			int rem = len - 2 >= 0 ? key - cnt[len - 2] : key;
//			int[] aux = new int[N - len];
//			for (int i = 0; i < N - len; ++i){
//				aux[i] = nums[i + len] - nums[i];
//			}
//			Arrays.sort(aux);
//			if (N * (N -1) / 2 % 2 == 0)
//				System.out.println(aux[rem-1]);
//			else 
//				System.out.println(aux[rem]);
//		}
//		in.close();
//	}
	
	static int[] nums;
	static int N;
	static int C;
	public static void main(String[] args) throws IOException {
		StreamTokenizer st = new StreamTokenizer(new BufferedReader(  
                new InputStreamReader(System.in)));  
        while (st.nextToken()!=StreamTokenizer.TT_EOF) {  
			N = (int)st.nval;
			nums = new int[N];
			for (int i = 0; i < N; ++i){
				st.nextToken();
				nums[i] = (int)st.nval;
			}
			Arrays.sort(nums);
			C = N * (N - 1) / 2;
			int lf = 0, rt = nums[N-1] - nums[0] + 1;
			while (rt - lf > 1){
				int mid = (lf + rt) / 2;
				if (valid(mid)) rt = mid;
				else lf = mid;
			}
			System.out.println(rt);
		}
	}
	
	public static boolean valid(int mid){
		int cnt = 0;
		for (int i = 0; i < N - 1; ++i){
			int pos = binarySearch(nums, i + 1, N - 1, nums[i] + mid);
			if (pos != -1){
				cnt += (pos - i);
			}
		}
		return cnt >= (C + 1) / 2;
	}
	
	public static int binarySearch(int[] nums, int lf, int rt, int key){
		while (lf < rt){
			int mid = lf + (rt - lf + 1) / 2;
			if (nums[mid] > key) rt = mid - 1;
			else lf = mid;
		}
		if (nums[lf] <= key) return lf;
		return -1;
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
