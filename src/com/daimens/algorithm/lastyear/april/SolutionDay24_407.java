package com.daimens.algorithm.april;

/**
 * 
 * @author DemonSong
 * 
 *         407.Trapping Rain Water II
 * 
 *         Given an m x n matrix of positive integers representing the height of
 *         each unit cell in a 2D elevation map, compute the volume of water it
 *         is able to trap after raining.
 * 
 *         Note: Both m and n are less than 110. The height of each unit cell is
 *         greater than 0 and is less than 20,000.
 * 
 *         Example:
 * 
 *         Given the following 3x6 height map: [ [1,4,3,1,3,2], [3,2,1,3,2,4],
 *         [2,3,3,2,3,1] ]
 * 
 *         Return 4.
 * 
 *         The above image represents the elevation map
 *         [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]] before the rain.
 * 
 * 
 *         After the rain, water are trapped between the blocks. The total
 *         volume of water trapped is 4.
 *
 */
public class SolutionDay24_407 {
	public int trapRainWater(int[][] heightMap) {
		
		int row = heightMap.length;
		if (row <= 1) return 0;
		int col = heightMap[0].length;
		if (col <= 1) return 0;
		
		if (row == 2 || col == 2) return 0;
		
		int area = 0;
		
        return 0;
    }
}
