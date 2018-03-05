package com.daimens.algorithm.january;

public class SolutionDay12_L0765 {
	
    public int minSwapsCouples(int[] row) {
        int n = row.length;
        int ans = 0;
        for (int i = 0; i < n; i += 2) {
        	int dest;
        	if ((row[i] & 1) == 0) dest = row[i] + 1;
        	else dest = row[i] - 1;
        	if (row[i + 1] == dest) continue;
        	ans ++;
        	for (int j = i + 2; j < n; ++j) {
        		if (row[j] == dest) {
        			int tmp = row[i + 1];
        			row[i + 1] = row[j];
        			row[j] = tmp;
        			break;
        		}
        	}
        }
        return ans;
    }

	public static void main(String[] args) {
		SolutionDay12_L0765 day = new SolutionDay12_L0765();
	}
}
