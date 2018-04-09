package com.daimens.algorithm.march;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolutionDay05_L0811 {
	
	
    public List<String> subdomainVisits(String[] cpdomains) {
    	Map<String, Integer> cnt = new HashMap<>();
    	for (String val : cpdomains) {
    		int time = Integer.parseInt(val.split(" ")[0]);
    		String[] domains = val.split(" ")[1].split("\\.");
    		String hei = "";
    		for (int i = domains.length - 1; i >= 0; --i) {
    			hei = domains[i] + "." + hei;
    			cnt.put(hei.substring(0, hei.length() - 1), cnt.getOrDefault(hei.substring(0, hei.length() - 1), 0) + time);
    		}
    	}
    	
    	List<String> ans = new ArrayList<>();
    	for (String key : cnt.keySet()) {
    		int val = cnt.get(key);
    		ans.add(val + " " + key);
    	}
    	return ans;
    }
    
    public static void main(String[] args) {
    	SolutionDay05_L0811 day = new SolutionDay05_L0811();
    	System.out.println(day.subdomainVisits(new String[] {"9001 discuss.leetcode.com"}));
	}
    
}
