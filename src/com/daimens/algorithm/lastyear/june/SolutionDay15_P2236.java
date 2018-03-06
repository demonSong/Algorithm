package com.daimens.algorithm.june;

import java.util.Scanner;

/**
 * 
 * @author DemonSong
 * 
 *         2236.Wireless Network
 * 
 *         Description
 * 
 *         An earthquake takes place in Southeast Asia. The ACM (Asia Cooperated
 *         Medical team) have set up a wireless network with the lap computers,
 *         but an unexpected aftershock attacked, all computers in the network
 *         were all broken. The computers are repaired one by one, and the
 *         network gradually began to work again. Because of the hardware
 *         restricts, each computer can only directly communicate with the
 *         computers that are not farther than d meters from it. But every
 *         computer can be regarded as the intermediary of the communication
 *         between two other computers, that is to say computer A and computer B
 *         can communicate if computer A and computer B can communicate directly
 *         or there is a computer C that can communicate with both A and B.
 * 
 *         In the process of repairing the network, workers can take two kinds
 *         of operations at every moment, repairing a computer, or testing if
 *         two computers can communicate. Your job is to answer all the testing
 *         operations. Input
 * 
 *         The first line contains two integers N and d (1 <= N <= 1001, 0 <= d
 *         <= 20000). Here N is the number of computers, which are numbered from
 *         1 to N, and D is the maximum distance two computers can communicate
 *         directly. In the next N lines, each contains two integers xi, yi (0
 *         <= xi, yi <= 10000), which is the coordinate of N computers. From the
 *         (N+1)-th line to the end of input, there are operations, which are
 *         carried out one by one. Each line contains an operation in one of
 *         following two formats: 1. "O p" (1 <= p <= N), which means repairing
 *         computer p. 2. "S p q" (1 <= p, q <= N), which means testing whether
 *         computer p and q can communicate.
 * 
 *         The input will not exceed 300000 lines. Output
 * 
 *         For each Testing operation, print "SUCCESS" if the two computers can
 *         communicate, or "FAIL" if not. Sample Input
 * 
 *         4 1 0 1 0 2 0 3 0 4 O 1 O 2 O 4 S 1 4 O 3 S 1 4 Sample Output
 * 
 *         FAIL SUCCESS
 *
 */
public class SolutionDay15_P2236 {
	
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
		
		public int find(int i){
			while (id[i] != i){
				i = id[i];
			}
			return i;
		}
		
		public boolean connect(int i, int j){
			return find(i) == find(j);
		}
	}
	
	private static boolean distance(int[] c1, int[] c2, int d){
		int x = c1[0] - c2[0];
		int y = c1[1] - c2[1];
		return (x * x + y * y) <= d * d;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String[] n = in.nextLine().trim().split(" ");
		
		int N = Integer.parseInt(n[0]);
		int D = Integer.parseInt(n[1]);
		
		boolean[] status = new boolean[N];
		boolean[][] distance = new boolean[N][N];
		Union union = new Union(N);
		
		int[][] coord = new int[N][2];
		for (int i = 0; i < N; ++i){
			n = in.nextLine().trim().split(" ");
			coord[i][0] = Integer.parseInt(n[0]);
			coord[i][1] = Integer.parseInt(n[1]);
		}
		
		for (int i = 0; i < N; ++i){
			for (int j = i + 1; j < N; ++j){
				if(distance(coord[i], coord[j], D)){
					distance[i][j] = true;
					distance[j][i] = true;
				}
			}
		}
		
		while (in.hasNextLine()){
			String[] operates = in.nextLine().trim().split(" ");
			if (operates[0].equals("O")){
				int c = Integer.parseInt(operates[1]) - 1;
				status[c] = true;
				for (int j = 0; j < N; ++j){
					if (j == c) continue;
					if (status[j] && distance[j][c])
						union.union(c, j);
				}
			}
			if (operates[0].equals("S")){
				int c1 = Integer.parseInt(operates[1]) - 1;
				int c2 = Integer.parseInt(operates[2]) - 1;
				System.out.println(union.connect(c1, c2) ? "SUCCESS" : "FAIL");
			}
		}
		in.close();
	}
}
