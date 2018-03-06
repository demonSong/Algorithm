package com.daimens.algorithm.april;

/**
 * 
 * @author DemonSong 
 * 463.Island Perimeter 
 *         You are given a map in form of a
 *         two-dimensional integer grid where 1 represents land and 0 represents
 *         water. Grid cells are connected horizontally/vertically (not
 *         diagonally). The grid is completely surrounded by water, and there is
 *         exactly one island (i.e., one or more connected land cells). The
 *         island doesn't have "lakes" (water inside that isn't connected to the
 *         water around the island). One cell is a square with side length 1.
 *         The grid is rectangular, width and height don't exceed 100. Determine
 *         the perimeter of the island.
 * Example:
 * [[0,1,0,0],
 *  [1,1,1,0],
 *  [0,1,0,0],
 *  [1,1,0,0]]
 * Answer: 16
 * Explanation: The perimeter is the 16 yellow stripes in the image below:
 * 
 *
 */
public class SolutionDay12_463 {
	public int islandPerimeter(int[][] grid) {
        int row = grid.length;
        if (row == 0) return 0;
        int col = grid[0].length;
        if (col == 0) return 0;
        
        int count = 0;
        int[] leftRight = {-1,0,1,0};
        int[] upDown = {0,-1,0,1};
        for (int i = 0; i < row; i++){
        	for (int j = 0; j < col; j++){
        		if (grid[i][j] == 1){
        			count += 4;
        			for (int k = 0; k < 4; k++){
        				int left = i + leftRight[k];
        				int up = j + upDown[k];
        				
        				if (left == -1 || up == -1 || left == row || up == col) continue;
        				
        				if (grid[left][up] == 1){
        					count--;
        				}
        			}
        		}
        	}
        }
        
        return count;
    }
}
