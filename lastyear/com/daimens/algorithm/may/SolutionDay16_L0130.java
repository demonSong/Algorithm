package com.daimens.algorithm.may;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author DemonSong
 * 
 *         130.Surrounded Regions
 * 
 *         Given a 2D board containing 'X' and 'O' (the letter O), capture all
 *         regions surrounded by 'X'.
 * 
 *         A region is captured by flipping all 'O's into 'X's in that
 *         surrounded region.
 * 
 *         For example, X X X X X O O X X X O X X O X X After running your
 *         function, the board should be:
 * 
 *         X X X X X X X X X X X X X O X X
 *
 */
public class SolutionDay16_L0130 {
	
	//从边界出发， bfs
	public void solve(char[][] board) {
        
		int row = board.length;
		if (row == 0) return;
		int col = board[0].length;
		if (col == 0) return;
		
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] canNotTurn = new boolean[row][col];
		for (int i = 0; i < row; i++) {
			if (board[i][0] == 'O') {
				queue.offer(new int[] { i, 0 });
				canNotTurn[i][0] = true;
			}
			if (board[i][col - 1] == 'O') {
				queue.offer(new int[] { i, col - 1 });
				canNotTurn[i][col-1] = true;
			}
		}

		for (int j = 1; j < col - 1; j++) {
			if (board[0][j] == 'O') {
				queue.offer(new int[] { 0, j });
				canNotTurn[0][j] = true;
			}
			if (board[row - 1][j] == 'O') {
				queue.offer(new int[] { row - 1, j });
				canNotTurn[row-1][j] = true;
			}
		}
		
		int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
		while(!queue.isEmpty()){
			int[] curr = queue.poll();
			for (int[] d : dir){
				int nrow = curr[0] + d[0];
				int ncol = curr[1] + d[1];
				if (nrow >= 0 && nrow < row && ncol >= 0 && ncol < col && board[nrow][ncol] == 'O' && !canNotTurn[nrow][ncol]){
					queue.offer(new int[]{nrow, ncol});
					canNotTurn[nrow][ncol] = true;
				}
			}
		}
		
		for (int i = 0; i < row; i++){
			for (int j = 0; j < col; j++){
				if (!canNotTurn[i][j] && board[i][j] == 'O'){
					board[i][j] = 'X';
				}
			}
		}
    }
}
