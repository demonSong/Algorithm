package com.daimens.algorithm.december;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class SolutionDay03_L0502 {
	
//	class Pair implements Comparable<Pair>{
//		
//		int key;
//		int val;
//		
//		Pair(int key, int val){
//			this.key = key;
//			this.val = val;
//		}
//
//		@Override
//		public int compareTo(Pair o) {
//			return this.val - o.val;
//		}
//	}
//	
//    public int deleteAndEarn(int[] nums) {
//        Map<Integer, Integer> mem = new HashMap<>();
//        
//        for (int n : nums) {
//        	mem.put(n, mem.getOrDefault(n, 0) + 1);
//        }
//        
//        Map<Integer, Integer> loss = new HashMap<>();
//        for (int key : mem.keySet()) {
//        	if (mem.containsKey(key + 1)) {
//        		loss.put(key, loss.getOrDefault(key, 0) + (key + 1) * mem.get(key + 1));
//        	}
//        	if (mem.containsKey(key - 1)){
//        		loss.put(key, loss.getOrDefault(key, 0) + (key - 1) * mem.get(key - 1));
//        	}
//        	if (!mem.containsKey(key + 1) && !mem.containsKey(key - 1)) {
//        		loss.put(key, loss.getOrDefault(key, 0) + 0);
//        	}
//        }
//        
//        PriorityQueue<Pair> queue = new PriorityQueue<>();
//        for (int key : loss.keySet()) {
//        	queue.offer(new Pair(key, loss.get(key)));
//        }
//        
//        int ans = 0;
//        Set<Integer> vis = new HashSet<>();
//        while (!queue.isEmpty()) {
//        	Pair now = queue.poll();
//        	if (vis.contains(now.key)) {
//        		continue;
//        	}
//        	ans += now.key * mem.get(now.key);
//        	vis.add(now.key);
//        	vis.add(now.key + 1);
//        	vis.add(now.key - 1);
//        }
//        
//    	return ans;
//    }
    
//    public int deleteAndEarn(int[] nums) {
//    	Map<Integer, Integer> mem = new HashMap<>();
//    	for (int num : nums) {
//    		mem.put(num, mem.getOrDefault(num, 0) + 1);
//    	}
//    	
//    	PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> (b - a));
//    	for (int key : mem.keySet()) queue.offer(key);
//    	
//    	int ans = 0;
//    	Set<Integer> vis = new HashSet<>();
//    	while (!queue.isEmpty()) {
//    		int p1 = queue.poll();
//    		if (vis.contains(p1)) continue;
//    		if (!queue.isEmpty()) {
//    			int p2 = queue.poll();
//    			if (loss(p1, mem, vis) < loss(p2, mem, vis)) {
//    				ans += p1 * mem.get(p1);
//    				vis.add(p1);
//        			vis.add(p1 + 1);
//        			vis.add(p1 - 1);
//        			queue.offer(p2);
//    			}
//    			else if (loss(p1, mem, vis) > loss(p2, mem, vis)){
//    				ans += p2 * mem.get(p2);
//    				vis.add(p2);
//        			vis.add(p2 + 1);
//        			vis.add(p2 - 1);
//        			queue.offer(p1);
//    			}
//    			else {
//    				
//    			}
//    		}
//    		else {
//    			ans += p1 * mem.get(p1);
//    			vis.add(p1);
//    			vis.add(p1 + 1);
//    			vis.add(p1 - 1);
//    		}
//    	}
//    	return ans;
//    }
//    
//    int loss(int num, Map<Integer, Integer> mem, Set<Integer> vis) {
//    	int loss = 0;
//    	if (mem.containsKey(num + 1) && !vis.contains(num + 1)) loss += (num + 1) * mem.get(num + 1);
//    	if (mem.containsKey(num - 1) && !vis.contains(num - 1)) loss += (num - 1) * mem.get(num - 1);
//    	return loss;
//    }
    
    
//    public int deleteAndEarn(int[] nums) {
//    	Map<Integer, Integer> mem = new HashMap<>();
//    	for (int n : nums) {
//    		mem.put(n, mem.getOrDefault(n, 0) + 1);
//    	}
//    	
//    	int[] cnt = new int[mem.size()];
//    	Set<Integer> keys = mem.keySet();
//    	
//    	int[] key = keys.stream().mapToInt(i -> i).toArray();
//    	
//    	Arrays.sort(key);
//    	for (int i = 0; i < key.length; ++i) {
//    		cnt[i] = mem.get(key[i]);
//    	}
//    	dp = new int[20000 + 16];
//    	return robot(key, cnt, 0);
//    }
//    
//    int[] dp;
//    public int robot(int[] key, int[] cnt, int pos) {
//    	if (pos >= key.length) return 0;
//    	if (dp[pos] > 0) return dp[pos];
//    	
//    	int ans = 0;
//    	int choose = 0;
//    	
//    	if (pos + 1 < key.length && key[pos + 1] - 1 == key[pos]) {
//    		choose = key[pos] * cnt[pos] + robot(key, cnt, pos + 2);
//    	}
//    	else {
//    		choose = key[pos] * cnt[pos] + robot(key, cnt, pos + 1);
//    	}
//    	
//    	ans = Math.max(ans, choose);
//    	
//    	ans = Math.max(ans, robot(key, cnt, pos + 1));
//    	return dp[pos] = ans;
//    }
    
	
//    public int deleteAndEarn(int[] nums) {
//    	if (nums.length == 0) return 0;
//    	
//    	Map<Integer, Integer> mem = new HashMap<>();
//    	for (int n : nums) {
//    		mem.put(n, mem.getOrDefault(n, 0) + 1);
//    	}
//    	
//    	int[] cnt = new int[mem.size()];
//    	Set<Integer> keys = mem.keySet();
//    	
//    	int[] key = keys.stream().mapToInt(i -> i).toArray();
//    	Arrays.sort(key);
//    	
//    	for (int i = 0; i < key.length; ++i) {
//    		cnt[i] = mem.get(key[i]);
//    	}
//    	
//    	int[] dp = new int[20000 + 16];
//    	int last = key.length;
//    	
//    	dp[last - 1] = key[last - 1] * cnt[last - 1];
//    	for (int i = last - 2; i >= 0; --i) {
//    		if (key[i] + 1 == key[i + 1]) {
//    			dp[i] = Math.max(dp[i], dp[i + 2] + key[i] * cnt[i]);
//    		}
//    		else {
//    			dp[i] = Math.max(dp[i], dp[i + 1] + key[i] * cnt[i]);
//    		}
//    		
//    		dp[i] = Math.max(dp[i], dp[i + 1]);
//    	}
//    	
//    	return dp[0];
//    }
    
	  public int deleteAndEarn(int[] nums) {
		  int[] cnt = new int[10016];
		  for (int num : nums) cnt[num]++;
		  
		  int[] dp = new int[10016];
		  dp[0] = 0;
		  dp[1] = cnt[1];
		  
		  for (int i = 2; i <= 10000; ++i) {
			  dp[i] = Math.max(dp[i - 1], dp[i - 2] + cnt[i] * i);
		  }
		  
		  return dp[10000];
	  }
    
	
	public static void main(String[] args) {
		SolutionDay03_L0502 day = new SolutionDay03_L0502();
		int[] nums = {8,7,3,8,1,4,10,10,10,2};
		System.out.println(day.deleteAndEarn(nums));
	}

}
