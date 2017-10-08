package com.daimens.algorithm.october;

public class SolutionDay08_M0001 {
	
	public void solveSudoku(char[][] board) {
		solve(board);
	}
	
	boolean check(char[][] board, int i, int j) {
        int[] map = new int[10];
		for (int k = 0; k < 9; ++k) {
			if (board[i][k] == '.') continue;
            int num = board[i][k] - '0';
            map[num] ++;
            if (map[num] > 1) return false;
        }
		
        map = new int[10];
		for (int k = 0; k < 9; ++k) {
			if (board[k][j] == '.') continue;
            int num = board[k][j] - '0';
            map[num]++;
            if (map[num] > 1) return false;
        }
        
        map = new int[10];
		
		int si = i / 3 * 3;
		int sj = j / 3 * 3;
		
		for (int x = 0; x < 3; ++x) {
			for (int y = 0; y < 3; ++y) {
				int nx = si + x;
				int ny = sj + y;
				if (board[nx][ny] == '.') continue;
                int num = board[nx][ny] - '0';
                map[num]++;
                if (map[num] > 1) return false;
            }
		}
		return true;
	}
	
	boolean solve(char[][] board) {
		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j) {
				if (board[i][j] == '.') {
					for (char num = '1'; num <= '9'; ++num) {
                        board[i][j] = num;
						if (check(board, i, j) && solve(board)) {
                            return true;
						}
                        board[i][j] = '.';
					}
                    return false;
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		SolutionDay08_M0001 day = new SolutionDay08_M0001();
		char[][] board = {"..9478...".toCharArray(), "7........".toCharArray(), ".2.1.9...".toCharArray(), "..7...24.".toCharArray()
				,".64.1.59.".toCharArray(), ".98...3..".toCharArray(), "...8.3.2.".toCharArray(), "........6".toCharArray()
				,"...2759..".toCharArray()};
		day.solveSudoku(board);
	}
}
