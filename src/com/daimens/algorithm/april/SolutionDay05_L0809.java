package com.daimens.algorithm.april;

public class SolutionDay05_L0809 {
	
    public int expressiveWords(String S, String[] words) {
    	int n = S.length();
    	int[] seq = new int[n];
    	int tot = 0;
    	int[] cnt = new int[n];
    	
    	char prev = '#';
    	int time = 1;
    	for (char c : S.toCharArray()) {
    		if (c == prev) {
    			time ++;
    		}
    		else {
    			if (prev != '#') {
	    			cnt[tot] = time;
	    			seq[tot++] = prev - 'a';
	    			time = 1;
    			}
    		}
    		prev = c;
    	}
    	
    	cnt[tot] = time;
		seq[tot++] = prev - 'a';
    	
    	int ans = 0;
    	for (String val : words) {
        	int sn = val.length();
        	int[] sseq = new int[sn];
        	int stot = 0;
        	int[] scnt = new int[sn];
        	
        	char sprev = '#';
        	int stime = 1;
        	for (char c : val.toCharArray()) {
        		if (c == sprev) {
        			stime ++;
        		}
        		else {
        			if (sprev != '#') {
	        			scnt[stot] = stime;
	        			sseq[stot++] = sprev - 'a';
	        			stime = 1;
        			}
        		}
        		sprev = c;
        	}
        	
        	scnt[stot] = stime;
    		sseq[stot++] = sprev - 'a';
        	
        	if (tot != stot) continue;
        	boolean ok = true;
        	for (int i = 0; i < tot; ++i) {
        		if (sseq[i] != seq[i]) ok = false;
        		if (cnt[i] <= 2) {
        			if (scnt[i] != cnt[i]) ok = false;
        		}
        		else {
        			if (scnt[i] > cnt[i]) ok = false;
        		}
        	}
        	if (ok) ans += 1;
    	}
    	return ans;
    }
    
    public static void main(String[] args) {
    	SolutionDay05_L0809 day = new SolutionDay05_L0809();
    	System.out.println(day.expressiveWords("yyrrrrrjaappoooyybbbebbbbriiiiiyyynnnvvwtwwwwwooeeexxxxxkkkkkaaaaauuuu", new String[] {"hello"}));
	}
    
}
