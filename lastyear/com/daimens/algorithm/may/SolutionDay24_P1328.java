package com.daimens.algorithm.may;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 
 * @author DemonSong
 * 
 *         1328.Radar Installation
 * 
 *         Description
 * 
 *         Assume the coasting is an infinite straight line. Land is in one side
 *         of coasting, sea in the other. Each small island is a point locating
 *         in the sea side. And any radar installation, locating on the
 *         coasting, can only cover d distance, so an island in the sea can be
 *         covered by a radius installation, if the distance between them is at
 *         most d.
 * 
 *         We use Cartesian coordinate system, defining the coasting is the
 *         x-axis. The sea side is above x-axis, and the land side below. Given
 *         the position of each island in the sea, and given the distance of the
 *         coverage of the radar installation, your task is to write a program
 *         to find the minimal number of radar installations to cover all the
 *         islands. Note that the position of an island is represented by its
 *         x-y coordinates.
 * 
 *         Figure A Sample Input of Radar Installations
 * 
 * 
 *         Input
 * 
 *         The input consists of several test cases. The first line of each case
 *         contains two integers n (1<=n<=1000) and d, where n is the number of
 *         islands in the sea and d is the distance of coverage of the radar
 *         installation. This is followed by n lines each containing two
 *         integers representing the coordinate of the position of each island.
 *         Then a blank line follows to separate the cases.
 * 
 *         The input is terminated by a line containing pair of zeros Output
 * 
 *         For each test case output one line consisting of the test case number
 *         followed by the minimal number of radar installations needed. "-1"
 *         installation means no solution for that case. Sample Input
 * 
 *         3 2 1 2 -3 1 2 1
 * 
 *         1 2 0 2
 * 
 *         0 0 Sample Output
 * 
 *         Case 1: 2 Case 2: 1
 *
 */
public class SolutionDay24_P1328 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int id = 0;
		while (true){
			String[] info = in.nextLine().trim().split(" ");
			int n = Integer.parseInt(info[0]);
			int d = Integer.parseInt(info[1]);
			if (n == 0 && d == 0) break;
			int[][] islands = new int[n][2];
			for (int i = 0; i < n; i++){
				String[] pos = in.nextLine().trim().split(" ");
				islands[i][0] = Integer.parseInt(pos[0]);
				islands[i][1] = Integer.parseInt(pos[1]);
			}
			in.nextLine();
			System.out.println("Case "+(++id)+": "+solve(islands, d));
		}
		in.close();
	}
	
	private static class Section{
		double start;
		double end;
		Section(double start, double end){
			this.start = start;
			this.end = end;
		}
	}
	private static int solve(int[][] islands, int d){
		Section[] sections = new Section[islands.length];
		for (int i = 0; i < islands.length; i++){
			int x = islands[i][0];
			int y = islands[i][1];
			if (y > d) return -1;
			double sqrt = Math.sqrt(d*d-y*y);
			sections[i] = new Section(x-sqrt, x+sqrt);
		}
		Arrays.sort(sections, new Comparator<Section>() {
			@Override
			public int compare(Section o1, Section o2) {
				return (o1.start != o2.start ? o1.start - o2.start : o1.end - o2.end) > 0 ? 1 : -1;
			}
		});
		
		double minEnd = -1 << 30;
		int step = 0;
		for (int i = 0; i < sections.length; i++){
			if(sections[i].start <= minEnd){
				minEnd = Math.min(minEnd, sections[i].end);
			}else{
				step ++;
				minEnd = sections[i].end;
			}
		}
		return step;
	}
}
