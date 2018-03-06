package com.daimens.algorithm.march;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author DemonSong
 * 120.Triangle
 * Given a triangle,find the minimum path sum from top to bottom.Each step you may move to adjacent numbers
 * on the row below. 
 * For example,given the following triangle
 * [
 *     [2],
 *    [3,4],
 *   [6,5,7],
 *  [4,1,8,3]
 * ]
 * 
 * The minimum path sum from top to bottomn is 11 (i.e.,2 + 3 + 5 + 1 = 11)
 * Note:
 * Bonus point if you are able to do this using only O(n) extra space,where n is the total number of rows in the triangle.
 *
 */
public class SolutionDay16_120 {
	//相当于在求每个点的最短路径,每个点只记录当前的最短路径,这种寻求边的关系还是太麻烦了
//	public int minimumTotal(List<List<Integer>> triangle) {
//        if(triangle.size() == 0) return 0;
//        
//        int len = triangle.size();
//        int tol = (1 + len) * len / 2;
//        
//        int[] dp = new int[tol+1];
//        dp[0] = triangle.get(0).get(0);
//        
//		for (int i = 1; i < len ; i++) {
//			for (int j = 0; j < triangle.get(i).size(); j++) {
//				int cur = (1+i) * i / 2 + j;
//				int fir = i * (i-1) /2 + j - 1 < 0 ? -1 : i * (i-1) /2 + j - 1;
//				int sec = fir + 1 >= i ? tol : fir + 1;
//				
//				if(fir == -1){
//					dp[cur] = dp[sec] + triangle.get(i).get(j);
//				}
//				else if(sec == tol){
//					dp[cur] = dp[fir] + triangle.get(i).get(j);	
//				}
//				else{
//					dp[cur] = Math.min(dp[fir]+triangle.get(i).get(j), dp[sec] + triangle.get(i).get(j));
//				}
//			}
//		}
//        
//		int min = Integer.MAX_VALUE;
//		for (int i = 0; i < len; i++){
//			if(dp[tol-i-1] < min){
//				min = dp[tol-i-1];
//			}
//		}
//		
//		return min;
//    }
	
	
	//from bottom to top.
	public int minimumTotal(List<List<Integer>> triangle) {
        for(int i = triangle.size() - 2; i >= 0; i--)
            for(int j = 0; j <= i; j++)
                triangle.get(i).set(j, triangle.get(i).get(j) + Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1)));
        return triangle.get(0).get(0);
    }
	
	
	
	public static void main(String[] args) {
		SolutionDay16_120 day = new SolutionDay16_120();
		List<Integer> ans = new ArrayList<>();
		ans.add(-1);
		
		List<List<Integer>> triangle = new ArrayList<>();
		triangle.add(ans);
		
		ans = new ArrayList<>();
		ans.add(3);
		ans.add(2);
		
		triangle.add(ans);
		

		ans = new ArrayList<>();
		ans.add(1);
		ans.add(-2);
		ans.add(-1);
		
		triangle.add(ans);
		day.minimumTotal(triangle);
	}
	

}
