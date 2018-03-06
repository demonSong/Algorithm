package com.daimens.algorithm.may;

import java.util.Comparator;
import java.util.PriorityQueue;

import javafx.scene.control.Cell;

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
public class SolutionDay13_L0407 {
	
	private class Cell{
		int row;
		int col;
		int height;
		public Cell(int row, int col, int height){
			this.row = row;
			this.col = col;
			this.height = height;
		}
	}
	
	public int trapRainWater(int[][] heightMap) {
		int row = heightMap.length;
		if (row <= 2) return 0;
		int col = heightMap[0].length;
		if (col <= 2) return 0;
		
		int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
		
		// 从最低的cell遍历
		PriorityQueue<Cell> queue = new PriorityQueue<>(1,new Comparator<Cell>(){
			@Override
			public int compare(Cell o1, Cell o2) {
				return o1.height - o2.height;
			}
		});
		boolean[][] visited = new boolean[row][col];
		for (int i = 0; i < row; i++){
			visited[i][0] = true;
			visited[i][col-1] = true;
			queue.offer(new Cell(i, 0, heightMap[i][0]));
			queue.offer(new Cell(i, col-1, heightMap[i][col-1]));
		}
		
		for (int i = 0; i < col; i++){
			visited[0][i] = true;
			visited[row-1][i] = true;
			queue.offer(new Cell(0, i, heightMap[0][i]));
			queue.offer(new Cell(row-1, i, heightMap[row-1][i]));
		}
		
		int area = 0;
		while (!queue.isEmpty()){
			Cell cell = queue.poll();
			for (int[] d : dir){
				int nrow = cell.row + d[0];
				int ncol = cell.col + d[1];
				if (nrow >=0 && nrow < row && ncol >= 0 && ncol < col && !visited[nrow][ncol]){
					visited[nrow][ncol] = true;
					area += Math.max(0, cell.height-heightMap[nrow][ncol]);
					queue.offer(new Cell(nrow, ncol, Math.max(heightMap[nrow][ncol], cell.height)));
				}
			}
		}
		return area;
	}
	
	public static void main(String[] args) {
		SolutionDay13_L0407 day = new SolutionDay13_L0407();
		int[][] heightMap = {{1,4,3,1,3,2},{3,2,1,3,2,4},{2,3,3,2,3,1}};
		day.trapRainWater(heightMap);
	}
}


