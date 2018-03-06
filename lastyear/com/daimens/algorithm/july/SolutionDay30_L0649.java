package com.daimens.algorithm.july;

import java.util.LinkedList;
import java.util.Queue;

public class SolutionDay30_L0649 {
	
	public String predictPartyVictory(String senate) {
		Queue<Character> queue = new LinkedList<>();
		int R = 0;
		int D = 0;
		for (char c : senate.toCharArray()){
			queue.offer(c);
			if (c == 'R') R++;
			else D++;
		}
		
		String ans = "";
		int ban = 0;
		while (true){
			if (R == 0 || D == 0){
				char winner = queue.poll();
				ans = winner == 'R' ? "Radiant" : "Dire";
				break;
			}
			char candicate = queue.poll();
			ban++;
			queue.offer(candicate);
			while (!queue.isEmpty() && candicate == queue.peek()){
				ban ++;
				queue.offer(queue.poll());
			}
			while (!queue.isEmpty() && candicate != queue.peek()){
				ban --;
				char loser = queue.poll();
				if (loser == 'R') R--;
				else D--;
				if (ban == 0) {
					ban = 0;
					break;
				}
			}
		}
		return ans;
    }
	
	public static void main(String[] args) {
		SolutionDay30_L0649 day = new SolutionDay30_L0649();
		System.out.println(day.predictPartyVictory("RDD"));
	}

}
