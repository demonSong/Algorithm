package com.daimens.algorithm.december;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class SolutionDay24_L0752 {
	
//    public int openLock(String[] deadends, String target) {
//    	Set<String> vis = new HashSet<>();
//    	for (int i = 0; i < deadends.length; ++i) {
//    		vis.add(deadends[i]);
//    	}
//    	
//    	Queue<String> queue = new ArrayDeque<>();
//    	queue.offer("0000");
//    	if (vis.contains("0000")) return -1;
//    	
//    	vis.add("0000");
//    	int[] downUp = {1, -1};
//    	int turn = 0;
//    	while (!queue.isEmpty()) {
//    		int size = queue.size();
//    		for (int i = 0; i < size; ++i) {
//    			String now = queue.poll();
//    			if (now.equals(target)) {
//    				return turn;
//    			}
//    			for (int j = 0; j < 4; ++j) {
//    				for (int k = 0; k < 2; ++k) {
//    					char[] cs = now.toCharArray();
//    					int digit = (cs[j] - '0' + downUp[k] + 10) % 10;
//    					cs[j] = (char) ('0' + digit);
//    					String nxt = new String(cs);
//    					if (!vis.contains(nxt)) {
//    						vis.add(nxt);
//    						queue.offer(nxt);
//    					}
//    				}
//    			}
//    		}
//    		turn ++;
//    	}
//    	return -1;
//    }
	
    public int openLock(String[] deadends, String target) {
    	int tar = Integer.parseInt(target);
    	Set<Integer> vis = new HashSet<>();
    	for (int i = 0; i < deadends.length; ++i) {
    		vis.add(Integer.parseInt(deadends[i]));
    	}
    	
    	Queue<Integer> queue = new ArrayDeque<>();
    	queue.offer(0);
    	if (vis.contains(0)) return -1;
    	vis.add(0);
    	
    	int[] ten    = {1000, 100, 10, 1};
    	int[] downUp = {1, -1};
    	int turn = 0;
    	
    	while (!queue.isEmpty()) {
    		int size = queue.size();
    		for (int i = 0; i < size; ++i) {
    			int now = queue.poll();
    			if (now == tar) {
    				return turn;
    			}
    			for (int j = 0; j < 4; ++j) {
    				for (int k = 0; k < 2; ++k) {
    					int[] digits = new int[4];
    					for (int l = 0; l < 4; ++l) {
    						digits[l] = now / ten[l] % 10;
    					}
    					digits[j] = (digits[j] + downUp[k] + 10) % 10;
    					int nxt = 0;
    					for (int l = 3; l >= 0; --l) {
    						nxt = 10 * nxt + digits[l];
    					}
    					if (!vis.contains(nxt)) {
    						vis.add(nxt);
    						queue.offer(nxt);
    					}
    				}
    			}
    		}
    		turn ++;
    	}
    	return -1;
    }
	
	public static void main(String[] args) {
		SolutionDay24_L0752 day = new SolutionDay24_L0752();
		String[] deads = {"3221","2030","3313","1302","1222","3301","1121","2322","3003","0103"};
		System.out.println(day.openLock(deads, "0202"));
	}
	
}
