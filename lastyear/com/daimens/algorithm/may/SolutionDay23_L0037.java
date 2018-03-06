package com.daimens.algorithm.may;

/**
 * 
 * @author DemonSong
 * 
 *         37.Sudoku Solver
 * 
 *         Write a program to solve a Sudoku puzzle by filling the empty cells.
 * 
 *         Empty cells are indicated by the character '.'.
 * 
 *         You may assume that there will be only one unique solution.
 * 
 * 
 *         A sudoku puzzle...
 * 
 * 
 *         ...and its solution numbers marked in red.
 * 
 *
 */
public class SolutionDay23_L0037 {
	public void solveSudoku(char[][] board) {
		backTrack(board);
	}

	private boolean backTrack(char[][] board) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == '.') {
					for (char c = '1'; c <= '9'; c++) {
						if (isValid(board, i, j, c)) {
							board[i][j] = c;
							if (backTrack(board)) {
								return true;
							} else {
								board[i][j] = '.';
							}
						}
					}
					//说明遍历了1-9都没找到答案 直接false即可
					return false;
				}
			}
		}
		return true;
	}

	private boolean isValid(char[][] board, int row, int col, char c) {
		for (int i = 0; i < 9; i++) {
			if (board[i][col] != '.' && board[i][col] == c)
				return false;
			if (board[row][i] != '.' && board[row][i] == c)
				return false;
			if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] != '.'
					&& board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c)
				return false;
		}
		return true;
	}

	private boolean valid(String nums) {
		int num = 0;
		char[] board = nums.toCharArray();
		for (int i = 0; i < board.length; i++) {
			num |= 1 << (board[i] - '1');
		}
		return (1 << 9) - 1 == num;
	}

	public static void main(String[] args) {
		SolutionDay23_L0037 day = new SolutionDay23_L0037();
		String[] sudoku = { "..9748...", "7........", ".2.1.9...", "..7...24.", ".64.1.59.", ".98...3..", "...8.3.2.",
				"........6", "...2759.." };
		char[][] board = new char[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				board[i][j] = sudoku[i].toCharArray()[j];
			}
		}
		day.solveSudoku(board);
	}
}
