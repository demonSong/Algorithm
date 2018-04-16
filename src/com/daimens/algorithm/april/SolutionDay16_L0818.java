package com.daimens.algorithm.april;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class SolutionDay16_L0818 {
	
//    int[] dp = new int[10000+12];
//    public int racecar(int t) {
//        if (dp[t] > 0) return dp[t];
//        int n = (int)(Math.log(t) / Math.log(2)) + 1;
//        if (1 << n == t + 1) dp[t] = n;
//        else {
//            dp[t] = racecar((1 << n) - 1 - t) + n + 1;
//            for (int m = 0; m < n - 1; ++m)
//                dp[t] = Math.min(dp[t], racecar(t - (1 << (n - 1)) + (1 << m)) + n + m + 1);
//        }
//        return dp[t];
//    }
	
    class CarInfo{
        int pos, speed;
        public CarInfo(int p, int s) {
            pos = p;
            speed = s;
        }
    }
    public int racecar(int target) {
        Set<String> visited = new HashSet<>();
        String begin = 0 + "/" + 1;
        visited.add(begin);
        Queue<CarInfo> queue = new LinkedList<>();
        queue.add(new CarInfo(0,1));
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                CarInfo cur = queue.poll();
                if (cur.pos == target) return level;
                String s1 = (cur.pos + cur.speed) + "/" + (cur.speed * 2);
                String s2 = cur.pos + "/" + (cur.speed > 0 ? -1 : 1);
                if (Math.abs(cur.pos + cur.speed - target) < target && !visited.contains(s1)) {
                    visited.add(s1);
                    queue.add(new CarInfo(cur.pos + cur.speed, cur.speed * 2));
                }
                if (Math.abs(cur.pos - target) < target && !visited.contains(s2)) {
                    visited.add(s2);
                    queue.add(new CarInfo(cur.pos, cur.speed > 0 ? -1 : 1));
                }
            }
            
            level++;
        }
        return -1;
    }
	
//    public int racecar(int target) {
//    	Map<Integer, Integer> map = new HashMap<>();
//    	int sum = 0;
//    	int cnt = 0;
//    	int speed = 1;
//    	while (sum < 10000) {
//    		sum += speed;
//    		cnt ++;
//    		speed *= 2;
//    		map.put(sum, cnt);
//    	}
//    	if (map.containsKey(target)) return map.get(target);
//    	
//    	int[] dp = new int[10000+12];
//    	Arrays.fill(dp, 0x3f3f3f3f);
//    	for (int i = 1; i < 10000; ++i) {
//    		int cnt_ = 1;
//    		int speed_ = -1;
//    		int j = i;
//    		while (j >= 0) {
//    			j += speed_;
//    			cnt_ ++;
//    			speed_ *= 2;
//    			if (j >= 0 && map.containsKey(i)) dp[j] = Math.min(dp[j], cnt_ + map.get(i));
//    		}
//    	}
//    	
//    	return dp[target];
//    }
	
	public static void main(String[] args) {
		SolutionDay16_L0818 day = new SolutionDay16_L0818();
		System.out.println(day.racecar(3));
	}
}
