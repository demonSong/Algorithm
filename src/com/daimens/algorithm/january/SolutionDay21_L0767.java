package com.daimens.algorithm.january;

import java.util.PriorityQueue;

public class SolutionDay21_L0767 {
	
	class P implements Comparable<P>{
		int c;
		int count;
		P(int c, int count){
			this.c = c;
			this.count = count;
		}
		@Override
		public int compareTo(P o) {
			return o.count - this.count;
		}
		
		@Override
		public String toString() {
			return c + "," + count;
		}
	}
	
    public String reorganizeString(String S) {
    	P[] ps = new P[32];
    	for (int i = 0; i < 32; ++i) ps[i] = new P(i, 0);
    	for (char c : S.toCharArray()) {
    		int key = c - 'a';
    		ps[key].count ++;
    	}
    	PriorityQueue<P> queue = new PriorityQueue<>();
    	for (int i = 0; i < 32; ++i) {
    		if (ps[i].count >= 1)
    			queue.offer(ps[i]);
    	}	
    	StringBuilder sb = new StringBuilder();
    	while (!queue.isEmpty()) {
    		P fir = queue.poll(); // fir
    		if (queue.isEmpty()) {
    			if (fir.count >= 2) return "";
    			sb.append((char)(fir.c + 'a'));
    		}
    		else {
    			P sec = queue.poll(); // sec
        		sb.append((char)(fir.c + 'a'));
        		sb.append((char)(sec.c + 'a'));
        		fir.count --;
        		sec.count --;
        		if (fir.count >= 1) {
        			queue.offer(fir);
        		}
        		if (sec.count >= 1) {
        			queue.offer(sec);
        		}
    		}
    	}
    	return sb.toString();
    }
	
	public static void main(String[] args) {
		SolutionDay21_L0767 day = new SolutionDay21_L0767();
		System.out.println(day.reorganizeString("abbabbaaab"));
	}

}
