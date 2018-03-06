package com.daimens.algorithm.november;

import java.util.HashMap;

/**
 * 
 * @author Demon Song
 * 447. Number of Boomerangs
 * Given n points in the plane that are all pairwise distinct,
 * a "boommerange" is a tuple of points (i,j,k)such that the 
 * distance between i and j equals the distance between i and k
 * (the order of the tuple matters)
 * Find the number of boomeranges. You may assume that n will be
 * at most 500 and coordinates of points are all in range [-10000,10000]
 * Example:
 * Input:
 * [[0,0],[1,0],[2,0]]
 * Output:
 * 2
 * Explanation:
 * The two boomerange are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]
 *
 */
public class SolutionDay26 {
	//排列组合问题，找寻一个点到任意点的距离，并且记录下来
	public int numberOfBoomerangs(int[][] points) {
		int sum = 0;
		//两层循环 1. 第一个顶点的循环 2. 计算顶点间距离的循环 - 在内循环中计算 tuple 次数
		for (int i = 0 ; i < points.length; i++){ //points的个数
			//以距离为key，相同距离的次数为value，建立HashMap
			HashMap<Integer, Integer> distanceCountMap = new HashMap<Integer,Integer>();
			for (int j = 0; j < points.length; j++){
				//计算两点间的距离
				int distance = pointsDistance(points[i][0], points[i][1], points[j][0], points[j][1]);
				//map 中不存在对应的distance
				if (!distanceCountMap.containsKey(distance)){
					distanceCountMap.put(distance, 1);
				}else{ //map 中存在对应的key
					int value = distanceCountMap.get(distance);
					distanceCountMap.put(distance, ++value);
				}
			}
			//循环结束
			for (Integer value : distanceCountMap.values()){
				sum += value * (value-1);
			}
		}
        return sum;
    }
	
	public int pointsDistance(int xPt1,int yPt1, int xPt2,int yPt2){
		int x = xPt1-xPt2;
		int y = yPt1-yPt2;
		return x*x + y*y;
	}
	
	public static void main(String[] args) {
		int[][] points = {{1,0},{0,0},{2,0}};
		SolutionDay26 day26 = new SolutionDay26();
		System.out.println(day26.numberOfBoomerangs(points));
	}
}
