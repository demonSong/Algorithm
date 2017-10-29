package com.daimens.algorithm.october;

public class SolutionDay29_L0501 {
	
    public int compress(char[] chars) {
    	int n = chars.length;
    	if (n == 0) return 0;
    	char p = chars[0];
    	StringBuilder sb = new StringBuilder();
    	int cnt = 1;
    	for (int i = 1; i < n; ++i) {
    		if (chars[i] == p) {
    			cnt ++;
    		}
    		else {
    			if (cnt == 1) {
    				sb.append(p);
    			}
    			else {
    				sb.append(p + "" + cnt);
    			}
    			cnt = 1;
    		}
    		p = chars[i];
    	}
    	
    	if (cnt == 1) {
			sb.append(p);
		}
		else {
			sb.append(p + "" + cnt);
		}
    	for (int i = 0; i < sb.length(); ++i) {
    		chars[i] = sb.charAt(i);
    	}
    	
    	return sb.length();
    }
	
	public static void main(String[] args) {
		SolutionDay29_L0501 day = new SolutionDay29_L0501();
		String chars = "aabbccc";
		System.out.println(day.compress(chars.toCharArray()));
	}

}
