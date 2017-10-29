package com.daimens.algorithm.october;

public class SolutionDay29_L0443 {
	
    public int compress(char[] chars) {
    	int n = chars.length;
    	
    	if (n == 0) return 0;
    	
    	char p = chars[0];
    	int cnt = 1;
    	int tot = 0;
    	
    	for (int i = 1; i < n; ++i) {
    		if (chars[i] == p) {
    			cnt ++;
    		}
    		else {
    			if (cnt == 1) {
    				chars[tot++] = p;
    			}
    			else {
    				chars[tot++] = p;
    				String num = String.valueOf(cnt);
    				for (int j = 0; j < num.length(); ++j) {
    					chars[tot++] = num.charAt(j);
    				}
    			}
    			cnt = 1;
    		}
    		p = chars[i];
    	}
    	
    	if (cnt == 1) {
			chars[tot++] = p;
		}
		else {
			chars[tot++] = p;
			String num = String.valueOf(cnt);
			for (int j = 0; j < num.length(); ++j) {
				chars[tot++] = num.charAt(j);
			}
		}
    	
    	return tot;
    }
	
	public static void main(String[] args) {
		SolutionDay29_L0443 day = new SolutionDay29_L0443();
		String chars = "aabbccc";
		System.out.println(day.compress(chars.toCharArray()));
	}

}
