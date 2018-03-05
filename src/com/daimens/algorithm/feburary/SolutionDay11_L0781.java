package com.daimens.algorithm.feburary;

public class SolutionDay11_L0781 {
	
    public int numRabbits(int[] answers) {
        int[] map = new int[1024];
        for (int ans : answers) {
        	map[ans]++;
        }
        int ans = 0;
        for (int i = 0; i < 1024; ++i) {
        	if (map[i] != 0) {
        		int batch = i + 1;
        		int count = map[i] / batch;
        		if (map[i] % batch != 0) count++;
        		ans += batch * count;
        	}
        }
        return ans;
    }
	
	public static void main(String[] args) {
		SolutionDay11_L0781 day = new SolutionDay11_L0781();
		int[] answers = {1, 1, 1, 1};
		System.out.println(day.numRabbits(answers));
	}

}
