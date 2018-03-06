package com.daimens.algorithm.may;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 
 * @author DemonSong
 * 
 *         582.Kill Process
 * 
 *         Given n processes, each process has a unique PID (process id) and its
 *         PPID (parent process id).
 * 
 *         Each process only has one parent process, but may have one or more
 *         children processes. This is just like a tree structure. Only one
 *         process has PPID that is 0, which means this process has no parent
 *         process. All the PIDs will be distinct positive integers.
 * 
 *         We use two list of integers to represent a list of processes, where
 *         the first list contains PID for each process and the second list
 *         contains the corresponding PPID.
 * 
 *         Now given the two lists, and a PID representing a process you want to
 *         kill, return a list of PIDs of processes that will be killed in the
 *         end. You should assume that when a process is killed, all its
 *         children processes will be killed. No order is required for the final
 *         answer.
 * 
 *         Example 1: Input: pid = [1, 3, 10, 5] ppid = [3, 0, 5, 3] kill = 5
 *         Output: [5,10] Explanation: 3 / \ 1 5 / 10 Kill 5 will also kill 10.
 *         Note: The given kill id is guaranteed to be one of the given PIDs. n
 *         >= 1.
 *
 */
public class SolutionDay14_L0582 {

	// public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid,
	// int kill) {
	// List<Integer> ans = new ArrayList<>();
	// ans.add(kill);
	// boolean[] visited = new boolean[ppid.size()];
	// List<Integer> index = find(ppid,kill,visited);
	// Queue<List<Integer>> queue = new LinkedList<>();
	// if (!index.isEmpty()) queue.offer(index);
	// while (!queue.isEmpty()){
	// List<Integer> tmp = queue.poll();
	// for (int i = 0; i < tmp.size(); i++){
	// int target = pid.get(tmp.get(i));
	// ans.add(target);
	// List<Integer> array = find(ppid, target,visited);
	// if (!array.isEmpty()) queue.offer(array);
	// }
	// }
	// return ans;
	// }
	//
	//
	// private List<Integer> find(List<Integer> ppid, int kill,boolean[]
	// visited){
	// List<Integer> ans = new ArrayList<>();
	// for (int i = 0; i < ppid.size(); i++){
	// if (visited[i]) continue;
	// if (ppid.get(i) == kill){
	// visited[i] = true;
	// ans.add(i);
	// }
	// }
	// return ans;
	// }

	public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
		Map<Integer, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < ppid.size(); i++) {
			map.computeIfAbsent(ppid.get(i), k -> new ArrayList<Integer>()).add(pid.get(i));
		}
		List<Integer> ans = new ArrayList<>();
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(kill);
		while (!queue.isEmpty()) {
			int target = queue.poll();
			ans.add(target);
			if (map.containsKey(target)) {
				List<Integer> tmp = map.get(target);
				for (int num : tmp) {
					queue.offer(num);
				}
			}
		}
		return ans;
	}
	
	public static void main(String[] args) {
		SolutionDay14_L0582 day = new SolutionDay14_L0582();
		int[] nums = { 1, 2, 3 };
		int[] nums2 = { 0, 1, 1 };
		List<Integer> pid = new ArrayList<>();
		for (int n : nums) {
			pid.add(n);
		}
		List<Integer> ppid = new ArrayList<>();
		for (int n : nums2) {
			ppid.add(n);
		}
		day.killProcess(pid, ppid, 1);
	}

}
