package com.daimens.algorithm.may;

/**
 * 
 * @author DemonSong
 * 
 *         134.Gas Station
 * 
 *         There are N gas stations along a circular route, where the amount of
 *         gas at station i is gas[i].
 * 
 *         You have a car with an unlimited gas tank and it costs cost[i] of gas
 *         to travel from station i to its next station (i+1). You begin the
 *         journey with an empty tank at one of the gas stations.
 * 
 *         Return the starting gas station's index if you can travel around the
 *         circuit once, otherwise return -1.
 *
 */
public class SolutionDay21_L0134 {
	
	//TLE 如何知道位置？
//	public int canCompleteCircuit(int[] gas, int[] cost) {
//		int tank = 0;
//		boolean[] canNotvisit = new boolean[gas.length];
//		for (int j = 0; j < cost.length; j++){
//			for (int i = j; i < cost.length + j; i++){
//				tank += i < gas.length ? gas[i] : gas[i % gas.length];
//				tank -= i < gas.length ? cost[i] : cost[i % gas.length];
//				if (tank < 0) canNotvisit[j] = true;
//			}
//		}
//		for (int i = 0; i < gas.length; i++){
//			if(!canNotvisit[i]) return i;
//		}
//		return -1;
//    }
	
	//gas的容量最大
//	public int canCompleteCircuit(int[] gas, int[] cost) {
//		int max = gas[0]-cost[0];
//		int maxIndex = 0;
//		for (int i = 1; i < gas.length; i++){
//			if (gas[i] - cost[i] > max){
//				max = gas[i]-cost[i];
//				maxIndex = i;
//			}
//		}
//		
//		int tank = 0;
//		for (int i = maxIndex; i < gas.length + maxIndex; i++){
//			tank += i < gas.length ? gas[i] : gas[i % gas.length];
//			tank -= i < gas.length ? cost[i] : cost[i % gas.length];
//			if (tank < 0) return -1;
//		}
//		return maxIndex;
//	}
	
	public int canCompleteCircuit(int[] gas, int[] cost) {
		int n = gas.length;
		int sum = 0;
		int pos = 0;
		int tol = 0;
		for (int i = 0; i < n; i++){
			sum += gas[i] - cost[i];
			if (sum < 0){
				tol += sum;
				sum = 0;
				pos = i + 1;
			}
		}
		tol += sum;
		return tol < 0 ? -1 : pos;
	}
	

	public static void main(String[] args) {
		SolutionDay21_L0134 day = new SolutionDay21_L0134();
		int[] gas = {2,4};
		int[] cost = {3,4};
		day.canCompleteCircuit(gas, cost);
	}
}
