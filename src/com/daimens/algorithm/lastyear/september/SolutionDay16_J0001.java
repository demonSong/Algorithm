package com.daimens.algorithm.september;

import java.util.Map;
import java.util.TreeMap;

public class SolutionDay16_J0001 {

	static class Point {
		char tag;
		double x;
		double y;

		Point(char tag, double x, double y) {
			this.tag = tag;
			this.x = x;
			this.y = y;
		}
	}

	public static double dist(Point a, Point b) {
		double dx = a.x - b.x;
		double dy = a.y - b.y;
		return Math.sqrt(dx * dx + dy * dy);
	}
	
	void compress(int[] x) {
		Map<Integer, Integer> map = new TreeMap<>();
		for (int i = 0; i < x.length; ++i) {
			map.put(x[i], i);
		}
	}

	static int[][] dir = { { -10, 10 }, { 10, -10 }, { 10, 10 }, { -10, -10 } };
	
	public static void main(String[] args) {
		double[][] arra = { { 110, 0 }, { 30.3, 89.8 }, { 66.0, 84.7 }, { 98.4, 76.7 }, { 73.7, 61.0 }, { 57.9, 47.6 },
				{ 86.8, 22.0 }, { 93.6, 48.8} };
		
		int[] x = new int[8];
		int[] y = new int[8];
		
		int max = 0;
		for (int i = 0; i < 8; ++i) {
			x[i] = (int) arra[i][0] / 3;
			y[i] = (int) arra[i][1] / 3;
			max = Math.max(x[i], max);
			max = Math.max(y[i], max);
		}
		
		char[][] map = new char[max + 1][max + 1];
		int row = map.length;
		int col = map[0].length;
		for (int i = 0; i < map.length; ++i) {
			for (int j = 0; j < map[0].length; ++j) {
				map[i][j] = '*';
			}
		}
		
		map[row - y[0] - 1][x[0] - 1] = 'H';
		
		for (int i = 0; i < 7; ++i) {
			map[row - (y[i + 1]) - 1][x[i + 1] - 1] = (char)('A' + i); 
		}
		
		
		Point[] points = new Point[8];
		points[0] = new Point('H', arra[0][0], arra[0][1]);
		for (char ch = 'A'; ch <= 'G'; ++ch) {
			points[ch - 'A' + 1] = new Point(ch, arra[ch - 'A' + 1][0], arra[ch - 'A' + 1][1]);
		}
		
		for (int i = 1; i <= 7; ++i) {
			Point p = points[i];
			double x1 = p.x;
			double y1 = p.y;
			
			// -10 -10
			double dx = x1 - 10;
			double dy = y1 - 10;
			int r = ((int)Math.ceil(dx * 1000 / 38.2) + 1);
			int c = ((int)Math.ceil(dy * 1000 / 38.2) + 1);
			//System.out.println(check(r, p, true) && check(c, p, false));
			System.out.println((char)('A' + i - 1) + " " + r + " " + c);
			
			dx = x1 - 10;
			dy = y1 + 10;
			System.out.println((char)('A' + i - 1) + " " + ((int)Math.ceil(dx * 1000 / 38.2) + 1) + " " + ((int)Math.floor(dy * 1000 / 38.2) + 1));
			
			dx = x1 + 10;
			dy = y1 - 10;
			System.out.println((char)('A' + i - 1) + " " + ((int)Math.floor(dx * 1000 / 38.2) + 1) + " " + ((int)Math.ceil(dy * 1000 / 38.2) + 1));
			
			dx = x1 + 10;
			dy = y1 + 10;
			System.out.println((char)('A' + i - 1) + " " + ((int)Math.floor(dx * 1000 / 38.2) + 1) + " " + ((int)Math.floor(dy * 1000 / 38.2) + 1));
			
		}
		
		for (int i = 0; i < 8; ++i) {
			for (int j = i + 1; j < 8; ++j) {
				Point a = points[i];
				Point b = points[j];
				if (a.tag == 'H') {
					System.out.println(a.tag + " -> " + b.tag + " " + (dist(a, b) - 10));
				}
				else {
					System.out.println(a.tag + " -> " + b.tag + " " + (dist(a, b)));
				}
			}
		}
	}
	
	static boolean check(int ans, Point a, boolean is) {
		double x = a.x;
		double y = a.y;
		ans --;
		if (is) {
			return ans * 38.2 <= (x + 10) * 1000 && ans * 38.2 >= (x - 10) * 1000;
		}
		else {
			return ans * 38.2 <= (y + 10) * 1000 && ans * 38.2 >= (y - 10) * 1000;
		}
	}
	
	static class D{
		
		public static void pp(int[][] board, int row, int col) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < row; ++i) {
				for (int j = 0; j < col; ++j) {
					sb.append(board[i][j] + (j + 1 == col ? "\n" : " "));
				}
			}
			System.out.println(sb.toString());
		}
		
		public static void pp(char[][] board, int row, int col) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < row; ++i) {
				for (int j = 0; j < col; ++j) {
					sb.append(board[i][j] + (j + 1 == col ? "\n" : " "));
				}
			}
			System.out.println(sb.toString());
		}
	}
}
