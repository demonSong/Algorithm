package com.daimens.algorithm.july;

import java.util.Arrays;
import java.util.List;

public class SolutionDay17_L0636 {
	
	public int[] exclusiveTime(int n, List<String> logs) {
        int[] ans = new int[n];
        int[] stack = new int[logs.size() + 2];
       
        int cur = -1;
        int prev = 0;
        for (String log : logs){
        	String[] param = log.split(":");
        	int id = Integer.parseInt(param[0]);
        	int time = Integer.parseInt(param[2]);
        	
        	if (cur == -1){
        		stack[++cur] = id;
        		continue;
        	}
        	if (param[1].equals("start")){
        		ans[stack[cur]] += (time - prev);
        		stack[++cur] = id;
        	}
        	else{
        		ans[stack[cur]] += (time - prev + 1);
        		time++;
        		cur--;
        	}
        	prev = time;
        }
        return ans;
    }
	
	public static void main(String[] args) {
		SolutionDay17_L0636 day = new SolutionDay17_L0636();
		int n = 2;
		List<String> logs = Arrays.asList(new String[] { "0:start:0", "1:start:2", "1:end:5", "0:end:6" });
		day.exclusiveTime(n, logs);
	}
}
