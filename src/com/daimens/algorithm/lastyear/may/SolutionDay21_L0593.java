package com.daimens.algorithm.may;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author DemonSong
 * 
 *         593.Valid Square
 * 
 *         Given the coordinates of four points in 2D space, return whether the
 *         four points could construct a square.
 * 
 *         The coordinate (x,y) of a point is represented by an integer array
 *         with two integers.
 * 
 *         Example: Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
 *         Output: True Note:
 * 
 *         All the input integers are in the range [-10000, 10000]. A valid
 *         square has four equal sides with positive length and four equal
 *         angles (90-degree angles). Input points have no order.
 *
 */
public class SolutionDay21_L0593 {

	// public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
	// if (distance(p1, p2) == distance(p2, p3) && distance(p2, p3) ==
	// distance(p3, p4) && distance(p3, p4) == distance(p4, p1)) return true;
	// if (distance(p1, p3) == distance(p3, p2) && distance(p2, p4) ==
	// distance(p4, p1) && distance(p4, p1) == distance(p1, p3)) return true;
	// if (distance(p1, p4) == distance(p4, p2) && distance(p2, p3) ==
	// distance(p3, p1) && distance(p1, p3) == distance(p3, p1)) return true;
	// return false;
	// }
	//
	// private boolean OK(int[] p1, int[] p2, int[] p3, int area){
	// if (distance(p1, p2) == distance(p1, p3)){
	// int x1 = p1[0] - p2[0];
	// int x2 = p1[1] - p2[1];
	// int sum = x1 * x1 + x2 * x2;
	// if (sum == area) return true;
	// }
	// return false;
	// }
	//
	// private double distance(int[] a1, int[] a2){
	// int x1 = a1[0] - a2[0];
	// int x2 = a1[1] - a2[1];
	// return Math.sqrt(x1*x1 + x2*x2);
	// }
	//
	// private boolean oneLine(int[] p1, int[] p2, int[] p3){
	// if (area(p1,p2,p3,p1) == 0) return true;
	// if (area(p1,p2,p3,p2) == 0) return true;
	// if (area(p1,p2,p3,p3) == 0) return true;
	// return false;
	// }
	//
	// private int area(int[] p1, int[] p2, int[] p3, int[] o1) {
	// int x1 = p1[0] - o1[0];
	// int y1 = p1[1] - o1[1];
	//
	// int x2 = p2[0] - o1[0];
	// int y2 = p2[1] - o1[1];
	//
	// int x3 = p3[0] - o1[0];
	// int y3 = p3[1] - o1[1];
	//
	// return x1 * y2 + x2 * y3 + x3 * y1 - x3 * y2 - x2 * y1 - x1 * y3;
	// }

	// public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
	// Set<Integer> set = new HashSet<Integer>();
	// set.add(distance(p1, p2));
	// set.add(distance(p1, p3));
	// set.add(distance(p1, p4));
	// set.add(distance(p2, p3));
	// set.add(distance(p2, p4));
	// set.add(distance(p3, p4));
	// return set.size() == 2 && !set.contains(0);
	// }

	private int distance(int[] p1, int[] p2) {
		int x1 = p1[0] - p2[0];
		int x2 = p1[1] - p2[1];
		return x1 * x1 + x2 * x2;
	}

	private boolean check(int[] p1, int[] p2, int[] p3, int[] p4) {
		return distance(p1, p2) == distance(p2, p3) && distance(p2, p3) == distance(p3, p4)
				&& distance(p3, p4) == distance(p4, p1) && distance(p1, p3) == distance(p2, p4)
				&& distance(p1, p2) != 0;
	}

	public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
		return check(p1, p2, p3, p4) || check(p1, p3, p2, p4) || check(p1, p2, p4, p3);
	}

	public static void main(String[] args) {
		SolutionDay21_L0593 day = new SolutionDay21_L0593();
		int[] p1 = { 0, 1 };
		int[] p2 = { 1, 2 };
		int[] p3 = { 0, 2 };
		int[] p4 = { 0, 0 };
		day.validSquare(p1, p2, p3, p4);
	}

}
