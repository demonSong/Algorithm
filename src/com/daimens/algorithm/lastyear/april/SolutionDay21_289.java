package com.daimens.algorithm.april;

/**
 * 
 * @author DemonSong
 * 
 *         289.Game of Life
 * 
 *         According to the Wikipedia's article: "The Game of Life, also known
 *         simply as Life, is a cellular automaton devised by the British
 *         mathematician John Horton Conway in 1970."
 * 
 *         Given a board with m by n cells, each cell has an initial state live
 *         (1) or dead (0). Each cell interacts with its eight neighbors
 *         (horizontal, vertical, diagonal) using the following four rules
 *         (taken from the above Wikipedia article):
 * 
 *         Any live cell with fewer than two live neighbors dies, as if caused
 *         by under-population. Any live cell with two or three live neighbors
 *         lives on to the next generation. Any live cell with more than three
 *         live neighbors dies, as if by over-population.. Any dead cell with
 *         exactly three live neighbors becomes a live cell, as if by
 *         reproduction. Write a function to compute the next state (after one
 *         update) of the board given its current state.
 * 
 *         Follow up: Could you solve it in-place? Remember that the board needs
 *         to be updated at the same time: You cannot update some cells first
 *         and then use their updated values to update other cells. In this
 *         question, we represent the board using a 2D array. In principle, the
 *         board is infinite, which would cause problems when the active area
 *         encroaches the border of the array. How would you address these
 *         problems?
 *
 */
public class SolutionDay21_289 {
//	public void gameOfLife(int[][] board) {
//
//		int row = board.length;
//		if (row == 0)
//			return;
//		int col = board[0].length;
//		if (col == 0)
//			return;
//
//		int[][] tmp = new int[row][col];
//		for (int i = 0; i < row; i++){
//			for (int j = 0; j < col; j++) {
//				tmp[i][j] = board[i][j];
//			}
//		}
//		
//		int[][] directions = { { -1, -1, -1, 0, 0, 1, 1, 1 }, { -1, 0, 1, -1, 1, -1, 0, 1 } };
//		for (int i = 0; i < row; i++){
//			for (int j = 0; j < col; j++){
//				if(tmp[i][j] == 1){
//					int count = 0;
//					for (int k = 0; k < directions[0].length; k++){
//						int x = i + directions[0][k];
//						int y = j + directions[1][k];
//						
//						if (x >=0 && x < row && y >= 0 && y < col){
//							if (tmp[x][y] == 1) count ++;
//						}
//					}
//					if (count < 2 || count >= 4) board[i][j] = 0;
//				}else{
//					int count = 0;
//					for (int k = 0; k < directions[0].length; k++){
//						int x = i + directions[0][k];
//						int y = j + directions[1][k];
//						if (x >= 0 && x < row && y >= 0 && y < col){
//							if (tmp[x][y] == 1) count++;
//						}
//					}
//					if (count == 3) board[i][j] = 1;
//				}
//			}
//		}
//	}
	
	// 错误的，不止一个状态需要记录
	// current next 00 01 10 11 四个状态直接赋值给board
	public void gameOfLife(int[][] board) {

		int row = board.length;
		if (row == 0)
			return;
		int col = board[0].length;
		if (col == 0)
			return;

		int[][] directions = { { -1, -1, -1, 0, 0, 1, 1, 1 }, { -1, 0, 1, -1, 1, -1, 0, 1 } };
	
		int tmp_row = -1;
		int tmp_col = -1;
		int tmp_bod = -1;
		for (int i = 0; i < row; i++){
			for (int j = 0; j < col; j++){
				if(board[i][j] == 1){
					int count = 0;
					for (int k = 0; k < directions[0].length; k++){
						int x = i + directions[0][k];
						int y = j + directions[1][k];
						
						if (x >=0 && x < row && y >= 0 && y < col){
							if (board[x][y] == 1) count ++;
						}
						
						if (x == tmp_row && y == tmp_col && tmp_bod == 1) count++;
						if (x == tmp_row && y == tmp_col && tmp_bod == 0) count--;
						
					}
					if (count < 2 || count >= 4){
						board[i][j] = 0;
						tmp_bod = 1;
						tmp_row = i;
						tmp_col = j;
					}
				}else{
					int count = 0;
					for (int k = 0; k < directions[0].length; k++){
						int x = i + directions[0][k];
						int y = j + directions[1][k];
						if (x >= 0 && x < row && y >= 0 && y < col){
							if (board[x][y] == 1) count++;
						}
						
						if (x == tmp_row && y == tmp_col && tmp_bod == 1) count++;
						if (x == tmp_row && y == tmp_col && tmp_bod == 0) count--;
					}
					if (count == 3){
						board[i][j] = 1;
						tmp_bod = 0;
						tmp_row = i;
						tmp_col = j;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		SolutionDay21_289 day = new SolutionDay21_289();
		int[][] board = {{1,1},{1,0}};
		day.gameOfLife(board);
	}
}
