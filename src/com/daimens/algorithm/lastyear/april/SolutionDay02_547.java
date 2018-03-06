package com.daimens.algorithm.april;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author DemonSong 
 * 
 * 547.Friend Circles
 * 
 *         There are N students in a class. Some of them are friends, while some
 *         are not. Their friendship is transitive in nature. For example, if A
 *         is a direct friend of B, and B is a direct friend of C, then A is an
 *         indirect friend of C. And we defined a friend circle is a group of
 *         students who are direct or indirect friends.
 * 
 *         Given a N*N matrix M representing the friend relationship between
 *         students in the class. If M[i][j] = 1, then the ith and jth students
 *         are direct friends with each other, otherwise not. And you have to
 *         output the total number of friend circles among all the students.
 * 
 *         Example 1: Input: [[1,1,0], [1,1,0], [0,0,1]] Output: 2
 *         Explanation:The 0th and 1st students are direct friends, so they are
 *         in a friend circle. The 2nd student himself is in a friend circle. So
 *         return 2.
 * 
 *         Example 2: Input: [[1,1,0], [1,1,1], [0,1,1]] Output: 1
 *         Explanation:The 0th and 1st students are direct friends, the 1st and
 *         2nd students are direct friends, so the 0th and 2nd students are
 *         indirect friends. All of them are in the same friend circle, so
 *         return 1.
 * 
 *         Note: 
 *         1. N is in range [1,200]. 
 *         2. M[i][i] = 1 for all students. 
 *         3. If M[i][j]= 1, then M[j][i] = 1.
 * 
 *
 */
public class SolutionDay02_547 {
	
//	public int findCircleNum(int[][] M) {
//		
//		if (M.length == 0) return 0;
//		
//		
//		int[][] friends = new int[M.length][M.length];
//		for (int i = 0; i < friends.length; i++){
//			friends[i][i] = i+1;
//		}
//		
//		for (int i = 0; i < M.length; i++){
//			for (int j = i; j < M.length; j++){
//				if(M[i][j] == 1){
//					if(i == 0)
//						friends[i][j] = i+1;
//					if (i != 0){
//						
//						int friendOfK = i+1;
//						for (int k = i-1; k >= 0; k--){
//							if(M[i-1][i] == 1){
//								friendOfK = friends[i-1][i];
//								break;
//							}
//						}
//						friends[i][j] = friendOfK;
//					}
//				}
//			}
//		}
//		
//		if(M[M.length-1][0] == 1){  //最后一个人和第一个人的关系
//			friends[0][M.length-1] = friends[M.length-1][M.length-1];
//		}
//		
//		
//		int max = M.length;
//		
//		Set<Integer> sets = new HashSet<>();
//		for (int i = 0; i < friends.length; i++){
//			for (int j = i + 1; j < friends.length; j++){
//				sets.add(friends[i][j]);
//			}
//		}
//		
//		
//        return sets.size() - 1;
//    }
	
//	public int findCircleNum(int[][] M) {
//		if (M.length == 0) return 0;
//		
//		int len = M.length;
//		int[][] friends = new int[len][len];
//		
//		for (int i = 0; i < len; i++){
//			friends[i][i] = i + 1;
//		}
//		
//		for (int i = 0; i < len; i++){
//			for (int j = i; j < len ; j++){
//				
//				if(i == 0){
//					if(M[i][j] == 1){
//						friends[i][j] = 1;
//						friends[j][i] = 1;
//					}
//				}
//				else{
//					int friendsOfK = i + 1;
//					for (int k = 0; k < i; k++){
//						if(friends[i][k] != 0){
//							friendsOfK = friends[i][k];
//						}
//					}
//					
//					if(M[i][j] == 1){
//						friends[i][j] = friendsOfK;
//						friends[j][i] = friendsOfK;
//					}
//				}
//			}
//		}
//		
//		Set<Integer> set = new HashSet<>();
//		for (int i = 0; i < len;i ++){
//			for (int j = 0; j < len; j++){
//				set.add(friends[i][j]);
//			}
//		}
//		
//		if (M[M.length-1][0] == 1){
//			return set.size()-2 < 0 ? 1 : set.size()-2;
//		}
//		
//		return set.size()-1 < 0 ? 1:set.size()-1;
//		
//	}
	
	public int findCircleNum(int[][] M) {
		
		if (M.length == 0) return 0;
		
		int len = M.length;
		
		int count = 0;
		for (int i = 0; i < len; i++) {
			if(M[i][i] != 0){
				count ++;
				bfs(M,i);
			}
		}
		
		return count;
	}
	
	private void bfs(int[][] M,int row){
		
		Queue<Integer> queue = new LinkedList<>();
		queue.add(row);
		
		while (!queue.isEmpty()){
			int h = queue.poll();
			for (int col = 0; col < M.length; col ++){
				if(M[h][col] != 0){
					queue.offer(col);
					M[h][col] = 0;
				}
			}
		}
		
	}
	
	
	
	public static void main(String[] args) {
		SolutionDay02_547 day = new SolutionDay02_547();
		int[][] M = {{1,0,0,1},{0,1,1,0},{0,1,1,1},{1,0,1,1}};
		
		day.findCircleNum(M);
	}

}
