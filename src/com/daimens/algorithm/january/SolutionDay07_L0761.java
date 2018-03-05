package com.daimens.algorithm.january;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class SolutionDay07_L0761 {
	
//    public String makeLargestSpecial(String S) {
//    	String max = new String(S);
//    	int n = S.length();
//    	Map<Integer, List<String>> map = new HashMap<>();
//    	char[] cs = S.toCharArray();
//    	for (int i = 0; i < n; ++i) {
//    		if (cs[i] == '1') {
//    			int j = i + 1 + 1; 
//    			while (j <= n) {
//    				if (isSpecial(S.substring(i, j)))
//    						map.computeIfAbsent(i, k -> new ArrayList<>()).add(S.substring(i, j));
//    				j += 2;
//    			}
//    		}
//    	}
//    	for (int i : map.keySet()) {
//    		List<String> firs = map.get(i);
//    		for (String fir : firs) {
//    			int j = i + fir.length();
//    			if (map.containsKey(j)) {
//    				for (String sec : map.get(j)) {
//	    				String cnd = swap(S, i, fir.length(), j, sec.length());
//						if (max.compareTo(cnd) < 0) {
//							max = cnd;
//						}
//    				}
//    			}
//    		}
//    	}
//    	if (max.equals(S)) return max;
//    	else return makeLargestSpecial(max);
//    }
//    
//    String swap(String S, int i, int n1, int j, int n2) {
//    	String ans = S.substring(0, i);
//    	ans += S.substring(j, j + n2);
//    	ans += S.substring(i + n1, j);
//    	ans += S.substring(i, i + n1);
//    	ans += S.substring(j + n2);
//    	return ans;
//    }
//    
//    public boolean isSpecial(String S){
//    	char[] cs = S.toCharArray();
//    	int count = 0;
//    	for(int i = 0; i < S.length(); i++){
//    		if (cs[i] == '1') count ++;
//    		else count --;
//    		if (count < 0) return false;
//    	}
//        return count == 0;
//    }
	
	public String makeLargestSpecial(String S) {
        int count = 0, i = 0;
        List<String> res = new ArrayList<String>();
        for (int j = 0; j < S.length(); ++j) {
          if (S.charAt(j) == '1') count++;
          else count--;
          if (count == 0) {
            res.add('1' + makeLargestSpecial(S.substring(i + 1, j)) + '0');
            i = j + 1;
          }
        }
        Collections.sort(res, Collections.reverseOrder());
        return String.join("", res);
    }
	
	public static void main(String[] args) {
		SolutionDay07_L0761 day = new SolutionDay07_L0761();
		System.out.println(day.makeLargestSpecial("110110100100"));
	}
}
