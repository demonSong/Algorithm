package com.daimens.algorithm.june;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author DemonSong
 * 
 *         1703. Find them, Catch Them
 * 
 *         Description
 * 
 *         The police office in Tadu City decides to say ends to the chaos, as
 *         launch actions to root up the TWO gangs in the city, Gang Dragon and
 *         Gang Snake. However, the police first needs to identify which gang a
 *         criminal belongs to. The present question is, given two criminals; do
 *         they belong to a same clan? You must give your judgment based on
 *         incomplete information. (Since the gangsters are always acting
 *         secretly.)
 * 
 *         Assume N (N <= 10^5) criminals are currently in Tadu City, numbered
 *         from 1 to N. And of course, at least one of them belongs to Gang
 *         Dragon, and the same for Gang Snake. You will be given M (M <= 10^5)
 *         messages in sequence, which are in the following two kinds:
 * 
 *         1. D [a] [b] where [a] and [b] are the numbers of two criminals, and
 *         they belong to different gangs.
 * 
 *         2. A [a] [b] where [a] and [b] are the numbers of two criminals. This
 *         requires you to decide whether a and b belong to a same gang. Input
 * 
 *         The first line of the input contains a single integer T (1 <= T <=
 *         20), the number of test cases. Then T cases follow. Each test case
 *         begins with a line with two integers N and M, followed by M lines
 *         each containing one message as described above. Output
 * 
 *         For each message "A [a] [b]" in each case, your program should give
 *         the judgment based on the information got before. The answers might
 *         be one of "In the same gang.", "In different gangs." and "Not sure
 *         yet." Sample Input
 * 
 *         1 5 5 A 1 2 D 1 2 A 1 2 D 2 4 A 1 4 Sample Output
 * 
 *         Not sure yet. In different gangs. In the same gang.
 *
 */
public class SolutionDay15_P1703 {
	
	static class Union{
		int[] id;
		int[] sz;
		
		public Union(int size){
			id = new int[size];
			sz = new int[size];
			for (int i = 0; i < size; ++i){
				id[i] = i;
				sz[i] = 1;
			}
		}
		
		public int find(int i){
			while (id[i] != i){
				i = id[i];
			}
			return i;
		}
		
		public boolean same(int i, int j){
			return find(i) == find(j);
		}
		
		public void union(int i, int j){
			int p = find(i);
			int q = find(j);
			
			if (p == q) return;
			if (sz[p] < sz[q]){
				id[p] = q;
				sz[q] += sz[p];
			}
			else{
				id[q] = p;
				sz[p] += sz[q];
			}
		}
	}
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int i = 0; i < T; ++i){
			int N = in.nextInt();
			int M = in.nextInt();
			Union u = new Union(2 * N);
			for (int j = 0; j < M; ++j){
				String opera = in.next();
				int c1 = in.nextInt() - 1;
				int c2 = in.nextInt() - 1;
				if (opera.equals("A")){
					if (u.same(c1, c2)){
						System.out.println("In the same gang.");
					}
					else if (u.same(c1, c2 + N)){
						System.out.println("In different gangs.");
					}
					else{
						System.out.println("Not sure yet.");
					}
				}
				else{
					u.union(c1, c2 + N);
					u.union(c1 + N, c2);
				}
			}
		}
	}
	
	static class Scanner {

		private BufferedReader br;
		private StringTokenizer tok;

		public Scanner(InputStream is) throws IOException {
			br = new BufferedReader(new InputStreamReader(is));
			getLine();
		}

		private void getLine() throws IOException {
			while (tok == null || !tok.hasMoreTokens()) {
				tok = new StringTokenizer(br.readLine());
			}
		}

		private boolean hasNext() {
			return tok.hasMoreTokens();
		}

		public String next() throws IOException {
			if (hasNext()) {
				return tok.nextToken();
			} else {
				getLine();
				return tok.nextToken();
			}
		}

		public int nextInt() throws IOException {
			if (hasNext()) {
				return Integer.parseInt(tok.nextToken());
			} else {
				getLine();
				return Integer.parseInt(tok.nextToken());
			}
		}

		public long nextLong() throws IOException {
			if (hasNext()) {
				return Long.parseLong(tok.nextToken());
			} else {
				getLine();
				return Long.parseLong(tok.nextToken());
			}
		}

		public double nextDouble() throws IOException {
			if (hasNext()) {
				return Double.parseDouble(tok.nextToken());
			} else {
				getLine();
				return Double.parseDouble(tok.nextToken());
			}
		}
	}
}
