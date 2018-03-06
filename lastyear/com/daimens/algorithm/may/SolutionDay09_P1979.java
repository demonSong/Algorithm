package com.daimens.algorithm.may;

import java.util.Scanner;

/**
 * 
 * @author DemonSong
 * 
 *         1979. Red and Black 
 *         
 *         Description
 *         
 *         There is a rectangular room, covered with square tiles. Each tile is
 *         colored either red or black. A man is standing on a black tile. From
 *         a tile, he can move to one of four adjacent tiles. But he can't move
 *         on red tiles, he can move only on black tiles.
 * 
 *         Write a program to count the number of black tiles which he can reach
 *         by repeating the moves described above. Input
 * 
 *         The input consists of multiple data sets. A data set starts with a
 *         line containing two positive integers W and H; W and H are the
 *         numbers of tiles in the x- and y- directions, respectively. W and H
 *         are not more than 20.
 * 
 *         There are H more lines in the data set, each of which includes W
 *         characters. Each character represents the color of a tile as follows.
 * 
 *         '.' - a black tile '#' - a red tile '@' - a man on a black
 *         tile(appears exactly once in a data set) The end of the input is
 *         indicated by a line consisting of two zeros. Output
 * 
 *         For each data set, your program should output a line which contains
 *         the number of tiles he can reach from the initial tile (including
 *         itself). Sample Input
 * 
 *         6 9 ....#. .....# ...... ...... ...... ...... ...... #@...# .#..#. 11
 *         9 .#......... .#.#######. .#.#.....#. .#.#.###.#. .#.#..@#.#.
 *         .#.#####.#. .#.......#. .#########. ........... 11 6 ..#..#..#..
 *         ..#..#..#.. ..#..#..### ..#..#..#@. ..#..#..#.. ..#..#..#.. 7 7
 *         ..#.#.. ..#.#.. ###.### ...@... ###.### ..#.#.. ..#.#.. 0 0 Sample
 *         Output
 * 
 *         45 59 6 13
 *
 */
public class SolutionDay09_P1979 {

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

	private static int solve(char[][] map) {
		int row = map.length;
		int col = map[0].length;
		
		count = 0;
		for (int i = 0; i < row; i++){
			for (int j = 0; j < col; j++){
				if (map[i][j] == '@'){
					dfs(map, i, j);
				}
			}
		}
		return count;
	}
	
	static int count = 0;
	private static void dfs(char[][] map, int i, int j){
		
		map[i][j] = '#';
		int row = map.length;
		int col = map[0].length;
		
		count ++;
		int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
		for (int d = 0; d < 4; d++){
			int x = i + directions[d][0];
			int y = j + directions[d][1];
			if (x >= 0 && x < row && y >= 0 && y < col && map[x][y] == '.'){
				dfs(map, x, y);
			}
		}
	}
}
