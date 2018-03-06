package com.daimens.algorithm.march;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Demon Song
 * 533.Lonely Pixel II
 * Given a picture consisting of black and white pixels, and a positive integer N, 
 * find the number of black pixels located at some specific row R and column C that align with all the following rules:
 * 1. Row R and column C both contain exactly N black pixels.
 * 2. For all rows that have a black pixel at column C, they should be exactly the same as row R
 * The picture is represented by a 2D char array consisting of 'B' and 'W', which means black and white pixels respectively.
 * Example:
 * Input:
 * [['W', 'B', 'W', 'B', 'B', 'W'],    
 * ['W', 'B', 'W', 'B', 'B', 'W'],    
 * ['W', 'B', 'W', 'B', 'B', 'W'],    
 * ['W', 'W', 'B', 'W', 'B', 'W']]
 * N = 3
 * Output: 6
 * Explanation:
 * All the bold 'B' are the black pixels we need (all 'B's at column 1 and 3).
 *         0    1    2    3    4    5         column index                                            
 * 0    [['W', 'B', 'W', 'B', 'B', 'W'],    
 * 1     ['W', 'B', 'W', 'B', 'B', 'W'],    
 * 2     ['W', 'B', 'W', 'B', 'B', 'W'],    
 * 3     ['W', 'W', 'B', 'W', 'B', 'W']]    
 * row index
 * Take 'B' at row R = 0 and column C = 1 as an example:
 * Rule 1, row R = 0 and column C = 1 both have exactly N = 3 black pixels. 
 * Rule 2, the rows have black pixel at column C = 1 are row 0, row 1 and row 2. They are exactly the same as row R = 0.
 * Note:
 * 1. The range of width and height of the input 2D array is [1,200].
 *
 */
public class SolutionDay05_502 {
	
//	public int findBlackPixel(char[][] picture, int N) {
//		int sum = 0;
//		
//		//先col
//		boolean[] fitCol = new boolean[picture[0].length];
//		for (int col = 0; col< picture[0].length;col++){
//			//统计每一行，如果有多个元素 就删除了
//			int[] count = new int[26];
//			
//			for (int row = 0; row < picture.length;row++){
//				count[picture[row][col]-'A']++;
//			}
//			
//			if(count['B'-'A'] == N){
//				fitCol[col] = true;
//				sum += N;
//			}else{
//				fitCol[col] = false;
//			}
//		}
//
//		for (int row = 0; row< picture.length;row++){ //列
//			//统计每一行，如果有多个元素 就删除了
//			int[] count = new int[26];
//			int[] add = new int[26];
//			
//			for (int col = 0; col < picture[row].length;col++){//行
//				count[picture[row][col]-'A']++;
//				if(!fitCol[col]){
//					add[picture[row][col]-'A']++;
//				}
//			}
//			
//			//无关紧要的情况
//			if(count['B'-'A'] != N){ 
//				sum -= count['B'-'A'];
//				sum += add['B'-'A'];
//			}
//		}
//		
//        return sum < 0 ? 0 : sum;
//    }
	
	public int findBlackPixel(char[][] picture, int N) {
		int m = picture.length;
		if (m == 0) return 0;
		int n = picture[0].length;
		if (n == 0) return 0;
		
		Map<String,Integer> map = new HashMap<>();
		
		//扫描行
		for (int i = 0; i < m; i++){
			String key = scanRow(picture, i, N);
			if(key.length()!=0){
				//符合 N的所有key组合
				map.put(key, map.getOrDefault(key, 0)+1);
			}
		}
		
		int result = 0;
		for (String key : map.keySet()){
			if(map.get(key) == N){ //列的个数也符合N的情况
				for (int j = 0; j < n; j++){
					if(key.charAt(j) == 'B' && scanCol(picture, j) == N){
						result += N;
					}
				}
			}
		}
		return result;
	}
	
	private String scanRow(char[][] picture, int row,int target){
		int n = picture[0].length;
		int count = 0;
		StringBuilder sb = new StringBuilder();
		
		for (int j = 0; j < n; j++) {
			if(picture[row][j] == 'B'){
				count++;
			}
			
			sb.append(picture[row][j]); //sb 对所有情况计数
		}
		
		if(count == target) return sb.toString();
		return "";
	}
	
	private int scanCol(char[][] picture,int col){
		int m = picture.length;
		int result = 0;
		for (int i = 0; i < m;i++){
			if(picture[i][col] == 'B'){
				result ++;
			}
		}
		return result;
	}
	
	
	public static void main(String[] args) {
		SolutionDay05_502 day = new SolutionDay05_502();
		
	}
}
