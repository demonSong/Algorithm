package com.daimens.algorithm.may;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 
 * @author DemonSong
 * 
 *         3669. Meteor Shower
 * 
 *         Description
 * 
 *         Bessie hears that an extraordinary meteor shower is coming; reports
 *         say that these meteors will crash into earth and destroy anything
 *         they hit. Anxious for her safety, she vows to find her way to a safe
 *         location (one that is never destroyed by a meteor) . She is currently
 *         grazing at the origin in the coordinate plane and wants to move to a
 *         new, safer location while avoiding being destroyed by meteors along
 *         her way.
 * 
 *         The reports say that M meteors (1 ≤ M ≤ 50,000) will strike, with
 *         meteor i will striking point (Xi, Yi) (0 ≤ Xi ≤ 300; 0 ≤ Yi ≤ 300) at
 *         time Ti (0 ≤ Ti ≤ 1,000). Each meteor destroys the point that it
 *         strikes and also the four rectilinearly adjacent lattice points.
 * 
 *         Bessie leaves the origin at time 0 and can travel in the first
 *         quadrant and parallel to the axes at the rate of one distance unit
 *         per second to any of the (often 4) adjacent rectilinear points that
 *         are not yet destroyed by a meteor. She cannot be located on a point
 *         at any time greater than or equal to the time it is destroyed).
 * 
 *         Determine the minimum time it takes Bessie to get to a safe place.
 * 
 *         Input
 * 
 *         Line 1: A single integer: M Lines 2..M+1: Line i+1 contains three
 *         space-separated integers: Xi, Yi, and Ti
 * 
 *         Output
 * 
 *         Line 1: The minimum time it takes Bessie to get to a safe place or -1
 *         if it is impossible.
 * 
 *         Sample Input
 * 
 *         4 0 0 2 2 1 2 1 1 2 0 3 5 Sample Output
 * 
 *         5
 *
 */
public class SolutionDay18_P3669 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int M = in.nextInt();
		int[][] map = new int[M][2];
		int[] T = new int[M];
		for (int i = 0; i < M; i++){
			map[i][0] = in.nextInt();
			map[i][1] = in.nextInt();
			T[i] = in.nextInt();
		}
		System.out.println(solve(map, T));
		in.close();
	}
	
	static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1},{0,0}};
	static final int INF = 1 << 30;
	
	private static int solve(int[][] map, int[] T){
		int[][] distance = new int[512][512];
		int[][] hit = new int[512][512];
		
		for (int i = 0; i < distance.length; i++){
			Arrays.fill(distance[i], INF);
			Arrays.fill(hit[i], INF);
		}
		distance[0][0] = 0;
		int M = map.length;
		int last = T[0];
		for (int i = 0; i < M; i++){
			int x = map[i][0];
			int y = map[i][1];
			last = Math.max(last, T[i]);
			for (int[] d : dir){
				int nx = x + d[0];
				int ny = y + d[1];
				if (nx >= 0 && nx < 512 && ny >= 0 && ny < 512){
					hit[nx][ny] = Math.min(hit[nx][ny], T[i]);
				}
			}
		}
		
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[]{0,0});
		if (hit[0][0] == 0) return -1; 
		
		int step = 0;
		while (!queue.isEmpty()){
			int size = queue.size();
			step++;
			for (int i = 0; i < size; i++){
				int[] cur = queue.poll();
				for (int k = 0; k < 4; k++){
					int nx = cur[0] + dir[k][0];
					int ny = cur[1] + dir[k][1];
					if (nx >= 0 && nx < 512 && ny >= 0 && ny < 512 && step < hit[nx][ny] && distance[nx][ny] == INF){
						distance[nx][ny] = step;
						if(hit[nx][ny] > last){
							return step;
						}
						queue.offer(new int[]{nx,ny});
					}
				}
			}
		}
		return -1;
	}
}
