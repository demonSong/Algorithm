package com.daimens.algorithm.may;

/**
 * 
 * @author DemonSong
 * 
 *         79.Word Search
 * 
 *         Given a 2D board and a word, find if the word exists in the grid.
 * 
 *         The word can be constructed from letters of sequentially adjacent
 *         cell, where "adjacent" cells are those horizontally or vertically
 *         neighboring. The same letter cell may not be used more than once.
 * 
 *         For example, Given board =
 * 
 *         [ ['A','B','C','E'], ['S','F','C','S'], ['A','D','E','E'] ] word =
 *         "ABCCED", -> returns true, word = "SEE", -> returns true, word =
 *         "ABCB", -> returns false.
 *
 */
public class SolutionDay23_L0079 {
	public boolean exist(char[][] board, String word) {
		char[] words = word.toCharArray();
		for (int i = 0; i < board.length; i++){
			for (int j = 0; j < board[i].length; j++){
				if(board[i][j] == words[0]){
					board[i][j] = '#';
					if (helper(board, i, j, words, 1)) return true;
					board[i][j] = words[0];
				}
			}
		}
        return false;
    }
	
	int[][] dir = {{1,0},{-1,0},{0,-1},{0,1}};

	private boolean helper(char[][] board, int x, int y, char[] words, int pos) {
		if (pos == words.length) {
			return true;
		} else {
			int row = board.length, col = board[0].length;
			for (int[] d : dir){
				int nx = x + d[0];
				int ny = y + d[1];
				if (nx >= 0 && nx < row && ny >= 0 && ny < col && board[nx][ny] != '#' && board[nx][ny] == words[pos]){
					board[nx][ny] = '#';
					if(helper(board, nx, ny, words, pos+1)) return true;
					board[nx][ny] = words[pos];
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		SolutionDay23_L0079 day = new SolutionDay23_L0079();
		char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
		day.exist(board, "ABCCED");
	}
	
}
