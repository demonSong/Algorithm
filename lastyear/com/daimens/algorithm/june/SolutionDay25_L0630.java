package com.daimens.algorithm.june;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 
 * @author DemonSong
 * 
 *         630. Course Schedule III My SubmissionsBack To Contest User Accepted:
 *         25 User Tried: 659 Total Accepted: 25 Total Submissions: 2084
 *         Difficulty: Medium There are n different online courses numbered from
 *         1 to n. Each course has some duration(course length) t and closed on
 *         dth day. A course should be taken continuously for t days and must be
 *         finished before or on the dth day. You will start at the 1st day.
 * 
 *         Given n online courses represented by pairs (t,d), your task is to
 *         find the maximal number of courses that can be taken.
 * 
 *         Example: Input: [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
 *         Output: 3 Explanation: There're totally 4 courses, but you can take 3
 *         courses at most: First, take the 1st course, it costs 100 days so you
 *         will finish it on the 100th day, and ready to take the next course on
 *         the 101st day. Second, take the 3rd course, it costs 1000 days so you
 *         will finish it on the 1100th day, and ready to take the next course
 *         on the 1101st day. Third, take the 2nd course, it costs 200 days so
 *         you will finish it on the 1300th day. The 4th course cannot be taken
 *         now, since you will finish it on the 3300th day, which exceeds the
 *         closed date. Note: The integer 1 <= d, t, n <= 10,000. You can't take
 *         two courses simultaneously.
 *
 */
public class SolutionDay25_L0630 {

	// public int scheduleCourse(int[][] courses) {
	// int n = courses.length;
	// int max = 0;
	// for (int i = 0; i < n; ++i){
	// max = Math.max(dfs(courses,i,0,new boolean[n]),max);
	// }
	// return max;
	// }
	//
	// public int dfs(int[][] courses, int pos, int start, boolean[] visited){
	// int now = courses[pos][0] + start;
	// if (now <= courses[pos][1]){
	// visited[pos] = true;
	// int max = 1;
	// for (int i = 0; i < courses.length; ++i){
	// if (!visited[i]) max = Math.max(dfs(courses, i, now, visited)+1, max);
	// }
	// return max;
	// }
	// return 0;
	// }

	// public int scheduleCourse(int[][] courses) {
	// PriorityQueue<int[]> queue = new PriorityQueue<>((a,b) -> a[0] == b[0] ?
	// a[1] - b[1] : a[0] - b[0]);
	// int n = courses.length;
	// for (int i = 0; i < n; ++i) queue.offer(courses[i]);
	// int cnt = 0;
	// int now = 0;
	// while (!queue.isEmpty()){
	// int[] c = queue.poll();
	// if (now + c[0] <= c[1]){
	// now += c[0];
	// cnt++;
	// }
	// }
	// return cnt;
	// }

	public int scheduleCourse(int[][] courses) {
		Arrays.sort(courses, (a, b) -> a[1] - b[1]);
		Queue<Integer> q = new PriorityQueue<>();
		int n = courses.length;
		int now = 0;
		for (int i = 0; i < n; ++i) {
			now += courses[i][0];
			q.offer(-courses[i][0]);
			while (now > courses[i][1]) {
				now += q.poll();
			}
		}
		return q.size();
	}

	public static void main(String[] args) {
		SolutionDay25_L0630 day = new SolutionDay25_L0630();
		int[][] courses = { { 5, 15 }, { 3, 19 }, { 6, 7 }, { 2, 10 }, { 5, 16 }, { 8, 14 }, { 10, 11 }, { 2, 19 } };
		System.out.println(day.scheduleCourse(courses));
	}
}
