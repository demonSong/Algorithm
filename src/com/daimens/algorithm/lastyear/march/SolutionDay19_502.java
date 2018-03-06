package com.daimens.algorithm.march;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import sun.launcher.resources.launcher;

/**
 * 
 * @author DemonSong
 * 542.01 Matrix
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
 * 
 * The distance between two adjacent cells is 1.
 * 
 * Example 1:
 * Input:
 * 000
 * 010
 * 000
 * 
 * Output:
 * 000
 * 010
 * 000
 * 
 * Example 2:
 * Input:
 * 000
 * 010
 * 111
 * 
 * Output:
 * 000
 * 010
 * 121
 * 
 * Note:
 * 1. The number of elements of the given matrix will not exceed 10,000.
 * 2. There are at least one 0 in the given matrix.
 * 3. The cells are adjacent in only four directions: up,down,left,right.
 *
 */
public class SolutionDay19_502 {

//	public List<List<Integer>> updateMatrix(List<List<Integer>> matrix) {
//		
//		if(matrix.size() == 0) return matrix;
//		
//		
//		for (int i = 0; i < matrix.size(); i++) {
//			for (int j = 0; j < matrix.get(i).size(); j++) {
//				if(matrix.get(i).get(j) == 1){
//					distances = new int[4];
//					updfs(matrix, i, j);
//					dndfs(matrix, i, j);
//					lfdfs(matrix, i, j);
//					rtdfs(matrix, i, j);
//					
//					int up = distances[0];
//					int dn = distances[1];
//					int lf = distances[2];
//					int rt = distances[3];
//					
//					int min = Math.min(Math.min(up, dn),Math.min(lf,rt));
//					matrix.get(i).set(j, min);
//				}
//			}
//		}
//		
//		return matrix;
//    }
//	
//	
//	
//	int[] distances = new int[4];
//	private void updfs(List<List<Integer>> matrix,int i, int j){
//		
//		if (matrix.size() == 0) return;
//		
//		int row = matrix.size();
//		
//		if(i < 0 || i >= row || matrix.get(i).get(j) == 0) {
//			//up bounded
//			
//			if(i < 0){
//				distances[0] += row;
//			}
//			return;
//		}
//		
//		distances[0]++;
//		updfs(matrix, i-1, j);
//		
//	}
//	
//	private void dndfs(List<List<Integer>> matrix,int i, int j){
//		
//		if (matrix.size() == 0) return;
//		
//		int row = matrix.size();
//		
//		if(i < 0 || i >= row || matrix.get(i).get(j) == 0) {
//			//up bounded
//			
//			if(i >= row){
//				distances[1] += row;
//			}
//			return;
//		}
//		
//		distances[1]++;
//		dndfs(matrix, i+1, j);
//		
//	}
//	
//	private void lfdfs(List<List<Integer>> matrix,int i, int j){
//		
//		if (matrix.size() == 0) return;
//		
//		int col = matrix.get(i).size();
//		
//		if(j < 0 || j >= col || matrix.get(i).get(j) == 0) {
//			//up bounded
//			
//			if(j < 0){
//				distances[2] += col;
//			}
//			return;
//		}
//		
//		distances[2]++;
//		lfdfs(matrix, i, j-1);
//		
//	}
//
//	private void rtdfs(List<List<Integer>> matrix,int i, int j){
//		
//		if (matrix.size() == 0) return;
//		
//		int col = matrix.get(i).size();
//		
//		if(j < 0 || j >= col || matrix.get(i).get(j) == 0) {
//			//up bounded
//			
//			if(j >= col){
//				distances[3] += col;
//			}
//			return;
//		}
//		
//		distances[3]++;
//		rtdfs(matrix, i, j+1);
//		
//	}
	
	public List<List<Integer>> updateMatrix(List<List<Integer>> matrix) {
		int m = matrix.size();
		int n = matrix.get(0).size();

		Queue<int[]> queue = new LinkedList<>();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix.get(i).get(j) == 0) {
					queue.offer(new int[] { i, j });
				} else {
					matrix.get(i).set(j, Integer.MAX_VALUE);
				}
			}
		}

		int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };  //表示四个方向

		
		//主要还是一个相邻节点的元素更新问题
		while (!queue.isEmpty()) {
			int[] cell = queue.poll();
			for (int[] d : dirs) {
				int r = cell[0] + d[0];
				int c = cell[1] + d[1];
				if (r < 0 || r >= m || c < 0 || c >= n || matrix.get(r).get(c) <= matrix.get(cell[0]).get(cell[1]) + 1)
					continue;
				
				//后续更新的节点还要放在队列中
				queue.add(new int[] { r, c });
				matrix.get(r).set(c, matrix.get(cell[0]).get(cell[1]) + 1);
			}
		}

		return matrix;
	}
	
	
	
	
	public static void main(String[] args) {
		SolutionDay19_502 day = new SolutionDay19_502();
		
		List<List<Integer>> matrix = new ArrayList<>();
		List<Integer> row1 = new ArrayList<>();
		row1.add(0);
		row1.add(0);
		row1.add(0);
		matrix.add(row1);
		
		List<Integer> row2 = new ArrayList<>();
		row2.add(0);
		row2.add(1);
		row2.add(0);
		matrix.add(row2);
		
		List<Integer> row3 = new ArrayList<>();
		row3.add(1);
		row3.add(1);
		row3.add(1);
		matrix.add(row3);
		
		day.updateMatrix(matrix);
	}
}
