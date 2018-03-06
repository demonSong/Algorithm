package com.daimens.algorithm.june;

import java.util.Scanner;

/**
 * 
 * @author DemonSong
 *
 */
public class SolutionDay01_C4171 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int[][] board = new int[4][4];
		for (int i = 0; i < 4; i++){
			for (int j = 0; j < 4; j++){
				board[i][j] = in.nextInt();
			}
		}
		System.out.println(solve(board));
		in.close();
	}

	private static String solve(int[][] board){
		boolean p1 = board[0][3] == 1;
		boolean p2 = board[1][3] == 1;
		boolean p3 = board[2][3] == 1;
		boolean p4 = board[3][3] == 1;
		
		boolean check = false;
		// p1 
		check |= p1 && board[0][0] == 1 || p4 && board[0][0] == 1;
		check |= p1 && board[0][1] == 1 || p3 && board[0][1] == 1;
		check |= p1 && board[0][2] == 1 || p2 && board[0][2] == 1;
		
		// p2
		check |= p2 && board[1][0] == 1 || p1 && board[1][0] == 1;
		check |= p2 && board[1][1] == 1 || p4 && board[1][1] == 1;
		check |= p2 && board[1][2] == 1 || p3 && board[1][2] == 1;
		
		// p3
		check |= p3 && board[2][0] == 1 || p2 && board[2][0] == 1;
		check |= p3 && board[2][1] == 1 || p1 && board[2][1] == 1;
		check |= p3 && board[2][2] == 1 || p4 && board[2][2] == 1;
		
		// p4
		check |= p4 && board[3][0] == 1 || p3 && board[3][0] == 1;
		check |= p4 && board[3][1] == 1 || p2 && board[3][1] == 1;
		check |= p4 && board[3][2] == 1 || p1 && board[3][2] == 1;
		
		return check ? "YES" : "NO";
	}
	
}
