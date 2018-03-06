package com.daimens.algorithm.august;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SolutionDay12_B1005 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		while (in.hasNext()){
			String ll = in.nextLine();
			int n = Integer.parseInt(ll.trim().split(" ")[0]);
			int m = Integer.parseInt(ll.trim().split(" ")[1]);
			char[][] board = new char[n][m];
			for (int i = 0; i < n; ++i){
				String line = in.nextLine();
				board[i] = line.toCharArray();
			}
			
			char[][] clone = clone(board);
			//求出0的个数
			int bit0 = bfs(board, '0');
			int bit1 = bfs(board, '1');
			if (bit0 == 1 && bit1 == 1){
				System.out.println("1");
			}
			else if (bit0 == 2 && bit1 == 1){
				boolean existOne = false;
				for (int i = 0; i < m; ++i){
					if (clone[0][i] == '1'){
						existOne = true;
						break;
					}
				}
				
				boolean a = false;
				for (int i = 0; i < m; ++i){
					if (clone[n - 1][i] == '1'){
						a = true;
						break;
					}
				}
				
				if (existOne && a) System.out.println("1");
				else System.out.println("0");
			}
			else{
				System.out.println("-1");
			}
		}
		in.close();
	}
	
	public static char[][] clone(char[][] board){
		int n = board.length;
		int m = board[0].length;
		char[][] clone = new char[n][m];
		for (int i = 0; i < n; ++i){
			for (int j = 0; j < m; ++j){
				clone[i][j] = board[i][j];
			}
		}
		return clone;
	}
	
	static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
	public static int bfs(char[][] board, char flag){
		Queue<int[]> queue = new LinkedList<int[]>();
		int n = board.length;
		int m = board.length;
		int res = 0;
		for (int i = 0; i < n; ++i){
			for (int j = 0; j < m; ++j){
				if (board[i][j] == flag){
					queue.offer(new int[]{i, j});
					board[i][j] = '#';
					res ++;
					while (!queue.isEmpty()){
						int[] now = queue.poll();
						for (int[] d : dir){
							int nx = now[0] + d[0];
							int ny = now[1] + d[1];
							if (nx >= 0 && nx < n && ny >= 0 && ny < m && board[nx][ny] == flag){
								queue.offer(new int[]{nx, ny});
								board[nx][ny] = '#';
							}
						}
					}
				}
			}
		}
		return res;
	}
}


