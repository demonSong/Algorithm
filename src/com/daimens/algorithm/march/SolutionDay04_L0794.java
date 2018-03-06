package com.daimens.algorithm.march;

public class SolutionDay04_L0794 {
	
	public boolean validTicTacToe(String[] board) {
		int x_count = 0;
		int o_count = 0;
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				if (board[i].charAt(j) == 'X') x_count++;
				else if (board[i].charAt(j) == 'O') o_count++;
			}
		}
		char[][] map = new char[3][3];
		for (int i = 0; i < 3; ++i) {
			map[i] = board[i].toCharArray();
		}
		if (o_count > x_count || x_count - o_count > 1) return false;
		
		if (check_win_positions(map, 'O')) {
			if (check_win_positions(map, 'X')) {
				return false;
			}
			return o_count == x_count;
		}
		
		if (check_win_positions(map, 'X') && x_count != o_count + 1) return false;
		
		return true;
    }
	
	boolean check_win_positions(char[][] map, char player) {
		for (int i = 0; i < 3; ++i) {
			if (map[i][0] == map[i][1] && map[i][1] == map[i][2]
					&& map[i][2] == player) {
				return true;
			}
		}
		
		for (int j = 0; j < 3; ++j) {
			if (map[0][j] == map[1][j] && map[1][j] == map[2][j]
					&& map[2][j] == player) {
				return true;
			}
		}
		
		
		if (map[0][0] == map[1][1] && map[1][1] == map[2][2] && map[2][2]  == player ||
				map[0][2] == map[1][1] && map[1][1] == map[2][0] && map[2][0] == player)
			return true;
							
	    return false;
	}
}
