package com.daimens.algorithm.april;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.text.html.CSS;

public class SolutionDay16_L0816 {
	
	class P{
		
		double x;
		double y;
		
		P(double x, double y){
			this.x = x;
			this.y = y;
		}
		
		@Override
		public boolean equals(Object that) {
			P obj = (P) that;
			return Double.compare(this.x, obj.x) == 0 && Double.compare(this.y, obj.y) == 0;
		}
		
		@Override
		public int hashCode() {
			return (int) (this.x * 10000007 + this.y * 10000007) % 1000000007;
		}
	}
	
	// 0.0 0.0010 和 0.001
	
	public boolean contains(String x) {
		for (char c : x.toCharArray()) if (c == '.') return true;
		return false;
	}
	
	public boolean valid(String x) {
		if (contains(x)) {
			String[] tw = x.split("\\.");
			if (tw[0].length() >= 2) {
				char[] cs = tw[0].toCharArray();
				if (cs[0] == '0') return false;
			}
			if (Integer.parseInt(tw[1]) == 0) return false;
			if (tw[1].length() >= 2) {
				char[] cs = tw[1].toCharArray();
				if (cs[cs.length - 1] == '0') return false;
			}
		}
		else {
			if (x.length() >= 2) {
				if (x.charAt(0) == '0') return false;
			}
		}
		return true;
	}
	
    public List<String> ambiguousCoordinates(String S) {
    	List<String> ans = new ArrayList<>();
    	S = S.replace("(", "");
    	S = S.replace(")", "");
    	int n = S.length();
    	for (int i = 1; i < n; ++i) {
    		String lf = S.substring(0, i);
    		String rt = S.substring(i);
    		if (valid(lf)) {
    			for (int l = 1; l < rt.length(); ++l) {
					String b1 = rt.substring(0, l);
					String b2 = rt.substring(l);
					if (valid(b1 + "." + b2)) {
						ans.add("(" + lf + "," + b1 + "." + b2 + ")");
					}
				}
    			if (valid(rt)) {
    				ans.add("(" + lf + "," + rt + ")");
    			}
    		}
    		for (int j = 1; j < lf.length(); ++j) {
    			String a1 = lf.substring(0, j);
    			String a2 = lf.substring(j);
    			if (valid(a1 + "." + a2)) {
    				for (int l = 1; l < rt.length(); ++l) {
    					String b1 = rt.substring(0, l);
    					String b2 = rt.substring(l);
    					if (valid(b1 + "." + b2)) {
    						ans.add("(" + a1 + "." + a2 + "," + b1 + "." + b2 + ")");
    					}
    				}
    				if (valid(rt)) {
        				ans.add("(" + a1 + "." + a2 + "," + rt + ")");
        			}
    			}
    		}
    	}
    	return ans;
    }
    
	
	public static void main(String[] args) {
		SolutionDay16_L0816 day = new SolutionDay16_L0816();
		System.out.println(day.ambiguousCoordinates("(00011)"));
	}

}
