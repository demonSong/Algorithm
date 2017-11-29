package com.daimens.algorithm.november;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class SolutionDay26_L0733 {
	
	public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
		int n = image.length;
		int m = image[0].length;
		
		int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
		
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {sr, sc});
		
		Set<Integer> vis = new HashSet<>();
		vis.add(sr * m + sc);
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int color = image[now[0]][now[1]];
			image[now[0]][now[1]] = newColor;
			for (int[] d : dir) {
				int nx = now[0] + d[0];
				int ny = now[1] + d[1];
				if (nx >= 0 && nx < n && ny >= 0 && ny < m && image[nx][ny] == color && !vis.contains(nx * m + ny)) {
					queue.offer(new int[] {nx, ny});
					vis.add(nx * m + ny);
				}
			}
		}
		return image;
    }
	
	public static void main(String[] args) {
		SolutionDay26_L0733 day = new SolutionDay26_L0733();
	}

}
