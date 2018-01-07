package com.daimens.algorithm.january;

public class SolutionDay07_L0760 {
	
//    public int[] anagramMappings(int[] A, int[] B) {
//    	int n = A.length;
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int i = 0; i < n; ++i) {
//        	map.put(B[i], i);
//        }
//        int[] ans = new int[n];
//        for (int i = 0; i < n; ++i) {
//        	ans[i] = map.get(A[i]);
//        }
//        return ans;
//    }
	
    public int[] anagramMappings(int[] A, int[] B) {
    	int n = A.length;
    	int[] ans = new int[n];
    	boolean[] vis = new boolean[n];
    	for (int i = 0; i < n; ++i) {
    		int find = -1;
    		for (int j = 0; j < n; ++j) {
    			if (!vis[j]) {
    				if (A[i] == B[j]) {
    					vis[j] = true;
    					find = j;
    					break;
    				}
    			}
    		}
    		ans[i] = find;
    	}
    	return ans;
    }
    
	public static void main(String[] args) {
		SolutionDay07_L0760 day = new SolutionDay07_L0760();
		System.out.println();
	}
}
