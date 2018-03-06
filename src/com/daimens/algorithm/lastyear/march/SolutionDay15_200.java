package com.daimens.algorithm.march;

/**
 * 
 * @author DemonSong
 * 200.Number of Islands
 * Given a 2d grid map of '1's(land) and '0's(water),count the number of islands. An island is surrounded by water and if 
 * formed by connecting adjacent lands horizontally or vertically. You may assume all four edges are all surrounded by water.
 * 
 * Example 1:
 * 11110
 * 11010
 * 11000
 * 00000
 * Answer:1
 * 
 * Example 2:
 * 11000
 * 11000
 * 00100
 * 00011
 * Answer:3
 *
 */
public class SolutionDay15_200 {
	
	private int row;
	private int col;
	
	public int numIslands(char[][] grid) {
		
		int count = 0;
		row = grid.length;
		if( row == 0) return 0;
		col = grid[0].length;
		
		for (int i = 0; i < row; i++){
			for (int j = 0; j < col; j++){
				
				if(grid[i][j] == '1'){
					DFSMarking(grid, i, j);
					++count;
				}
			}
		}
		
		
        return count;
    }
	
	
	//搜索所有临街的 '1'元素靠谱 .
	private void DFSMarking(char[][] grid,int i,int j){
		if (i < 0 || j < 0 || i >= row || j >= col || grid[i][j] != '1')  // '0'的情况 就return
			return;
		
		grid[i][j] = '0';
		DFSMarking(grid, i + 1, j);
	    DFSMarking(grid, i - 1, j);
	    DFSMarking(grid, i, j + 1);
	    DFSMarking(grid, i, j - 1);
	}
}
