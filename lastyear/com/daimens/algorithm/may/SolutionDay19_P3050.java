package com.daimens.algorithm.may;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 * @author DemonSong
 * 
 *         3050.Hopscotch
 * 
 *         Description
 * 
 *         The cows play the child's game of hopscotch in a non-traditional way.
 *         Instead of a linear set of numbered boxes into which to hop, the cows
 *         create a 5x5 rectilinear grid of digits parallel to the x and y axes.
 * 
 *         They then adroitly hop onto any digit in the grid and hop forward,
 *         backward, right, or left (never diagonally) to another digit in the
 *         grid. They hop again (same rules) to a digit (potentially a digit
 *         already visited).
 * 
 *         With a total of five intra-grid hops, their hops create a six-digit
 *         integer (which might have leading zeroes like 000201).
 * 
 *         Determine the count of the number of distinct integers that can be
 *         created in this manner. Input
 * 
 *         Lines 1..5: The grid, five integers per line Output
 * 
 *         Line 1: The number of distinct integers that can be constructed
 *         Sample Input
 * 
 *         1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 2 1 1 1 1 1 1 Sample Output
 * 
 *         15
 * 
 *
 */
public class SolutionDay19_P3050 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int[][] grid = new int[5][5];
		for (int i = 0; i < 5; i++){
			for (int j = 0; j < 5; j++){
				grid[i][j] = in.nextInt();
			}
		}
		System.out.println(solve(grid));
		in.close();
	}
	
	private static int solve(int[][] grid){
		ans = new HashSet<>();
		for (int i = 0; i < 5; i++){
			for (int j = 0; j < 5; j++){
				dfs(grid,i, j, 5, "");
			}
		}
		return ans.size();
	}
	
	static Set<String> ans = new HashSet<>();
	static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	
	private static void dfs(int[][] grid,int i, int j, int hop,String path){
		path += grid[i][j];
		if (hop == 0){
			ans.add(path);
		}else{
			for (int[] d : dir){
				int nx = i + d[0];
				int ny = j + d[1];
				if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5){
					dfs(grid, nx, ny, hop-1, path);
				}
			}
		}
		path.substring(0,path.length()-1);
	}

}
