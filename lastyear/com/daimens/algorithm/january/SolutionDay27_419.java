package com.daimens.algorithm.january;

/**
 * 
 * @author Demon Song
 * 419.Battleships in a Board
 * Given an 2D board,count how many battleships are in it. The battleships are represented with 'x' s,
 * empty slots are represented with '.'s.You may assume the following rules.
 * 1. You receive a valid board,made of only battleships or empty slots.
 * 2. Battleships can only be placed horizontally or vertically. In other words,they can only be made of the
 * shape 1xN (1 row, N columns) or Nx1(N rows, 1 column),where N can be of any size.
 * 3. At least one horizontal or vertical cell separates between two battleships - there are no adjacent
 * battleships.
 * Example:
 * X .. X
 * . .. X
 * . .. X
 * In the above board there are 2 battleships.
 * Invalid Example:
 * ...X
 * XXXX
 * ...X
 * This is an invalid board that you will not receive - as battleships will always have a cell separating 
 * between them.
 * 
 *
 */
public class SolutionDay27_419 {
	//DFS算法  找到矩阵里所有的船的数目
	/**
	 * 思路:
	 * 1.设置一个与矩阵同样大小的布尔数组visited，用于表示某一个位置是否已经被访问过。最一般的解题思路
	 * 
	 * 
	 * @param board
	 * @return
	 */
	public int countBattleships(char[][] board){
		if(board.length == 0){
			return 0;
		}
		int rsize = board.length;
		int csize = board[0].length;
		int count = 0;
		
		for(int i = 0; i < rsize; i++){
			for (int j = 0; j < csize; j++){
				if(board[i][j] == 'X' && (i == 0 || board[i-1][j] == '.') && (j == 0 || board[i][j-1] == '.')){
					count ++;
				}
			}
		}
		
		return count;
	}
}
