package com.daimens.algorithm.may;

import java.util.Scanner;

/**
 * 
 * @author DemonSong
 * 
 * 0118. Property Distribution
 * 
 * 
 *
 */
public class SolutionDay09_A0118 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (true) {
			int W = in.nextInt();
			int H = in.nextInt();

			char[][] map = new char[H][W];
			if (W == 0 && H == 0)
				break;
			
			for (int i = 0; i < H; i++) {
				char[] ss = in.next().trim().toCharArray();
				for (int j = 0; j < W; j++) {
					map[i][j] = ss[j];
				}
			}
			System.out.println(solve(map));
		}
		in.close();
	}
	
	private static int solve(char[][] map){
		
		int row = map.length;
		int col = map[0].length;
		
		int count = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (map[i][j] != '.'){
					count ++;
					dfs(map, i, j, map[i][j]);
				}
			}
		}
		
		return count;
	}
	
	private static void dfs(char[][] map, int i, int j, char c){
		map[i][j] = '.';
		int row = map.length;
		int col = map[0].length;
		int[][] directions = {{-1,0},{1,0},{0,1},{0,-1}};
		
		for (int d = 0; d < 4; d++){
			int x = i + directions[d][0];
			int y = j + directions[d][1];
			
			if (x >= 0 && x < row && y >= 0 && y < col && map[x][y] == c){
				dfs(map, x, y, c);
			}
		}
	}

}
