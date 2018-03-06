package com.daimens.algorithm.april;

/**
 * 
 * @author DemonSong 
 * 
 * 529.Minesweeper
 * 
 *         Let's play the minesweeper game (Wikipedia, online game)!
 * 
 *         You are given a 2D char matrix representing the game board. 'M'
 *         represents an unrevealed mine, 'E' represents an unrevealed empty
 *         square, 'B' represents a revealed blank square that has no adjacent
 *         (above, below, left, right, and all 4 diagonals) mines, digit ('1' to
 *         '8') represents how many mines are adjacent to this revealed square,
 *         and finally 'X' represents a revealed mine.
 * 
 *         Now given the next click position (row and column indices) among all
 *         the unrevealed squares ('M' or 'E'), return the board after revealing
 *         this position according to the following rules:
 * 
 *         If a mine ('M') is revealed, then the game is over - change it to
 *         'X'. If an empty square ('E') with no adjacent mines is revealed,
 *         then change it to revealed blank ('B') and all of its adjacent
 *         unrevealed squares should be revealed recursively. If an empty square
 *         ('E') with at least one adjacent mine is revealed, then change it to
 *         a digit ('1' to '8') representing the number of adjacent mines.
 *         Return the board when no more squares will be revealed. Example 1:
 *         Input:
 * 
 *         [['E', 'E', 'E', 'E', 'E'], ['E', 'E', 'M', 'E', 'E'], ['E', 'E',
 *         'E', 'E', 'E'], ['E', 'E', 'E', 'E', 'E']]
 * 
 *         Click : [3,0]
 * 
 *         Output:
 * 
 *         [['B', '1', 'E', '1', 'B'], ['B', '1', 'M', '1', 'B'], ['B', '1',
 *         '1', '1', 'B'], ['B', 'B', 'B', 'B', 'B']]
 * 
 *         Explanation:
 * 
 *         Example 2: Input:
 * 
 *         [['B', '1', 'E', '1', 'B'], ['B', '1', 'M', '1', 'B'], ['B', '1',
 *         '1', '1', 'B'], ['B', 'B', 'B', 'B', 'B']]
 * 
 *         Click : [1,2]
 * 
 *         Output:
 * 
 *         [['B', '1', 'E', '1', 'B'], ['B', '1', 'X', '1', 'B'], ['B', '1',
 *         '1', '1', 'B'], ['B', 'B', 'B', 'B', 'B']]
 * 
 *         Explanation:
 * 
 *         Note: The range of the input matrix's height and width is [1,50]. The
 *         click position will only be an unrevealed square ('M' or 'E'), which
 *         also means the input board contains at least one clickable square.
 *         The input board won't be a stage when game is over (some mines have
 *         been revealed). For simplicity, not mentioned rules should be ignored
 *         in this problem. For example, you don't need to reveal all the
 *         unrevealed mines when the game is over, consider any cases that you
 *         will win the game or flag any squares.
 *
 */
public class SolutionDay22_529 {
	
	public char[][] updateBoard(char[][] board, int[] click) {
		int row = board.length;
		if (row == 0) return board;
		int col = board[0].length;
		if (col == 0) return board;
		
		int x = click[0];
		int y = click[1];
		
		// game over
		if (board[x][y] == 'M'){
			board[x][y] = 'X';
			return board;
		}
		
		int[][] directions = { { -1, -1, -1, 0, 0, 1, 1, 1 }, { -1, 0, 1, -1, 1, -1, 0, 1 } };
		
		int count = 0;
		for (int i = 0; i < 8; i++){
			int xx = x + directions[0][i];
			int yy = y + directions[1][i];
			if (xx < 0 || xx >= row || yy < 0 || yy >= col) continue;
			if (board[xx][yy] == 'M') count ++;
		}
		
		if (count > 0){
			board[x][y] = (char) ('0' + count);
			return board;
		}
		else{
			board[x][y] = 'B';
			for (int i = 0; i < 8; i ++){
				int xx = x + directions[0][i];
				int yy = y + directions[1][i];
				if (xx < 0 || xx >= row || yy < 0 || yy >= col || board[xx][yy] == 'B') continue;
				updateBoard(board, new int[]{xx,yy});
			}
		}
		return board;
    }
	
}
