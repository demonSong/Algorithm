package com.daimens.algorithm.november;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolutionDay13_L0726 {
	
	
	public String countOfAtoms(String formula) {
        Map<String, Integer> map = go(formula.toCharArray(), 0, formula.length() - 1);
        
        StringBuilder sb = new StringBuilder();
        List<String> set = new ArrayList<>(map.keySet());
        Collections.sort(set);
        for (String key : set) {
        	sb.append(key);
        	if (map.get(key) > 1) {
        		sb.append(map.get(key));
        	}
        }
        return sb.toString();
    }
	
	public Map<String, Integer> go(char[] cs, int s, int e){
		if (s > e) return new HashMap<>();
		
		Map<String, Integer> ans = new HashMap<>();
		int idx = new String(cs).substring(s, e + 1).indexOf("(");
		if (idx != -1) idx += s;
		if (idx == -1) {
			
			StringBuilder sb = new StringBuilder();
			for (int i = s; i <= e;) {
				if (Character.isUpperCase(cs[i])) {
					sb.append(cs[i]);
					i ++;
					
					if (i <= e && Character.isUpperCase(cs[i])) {
						String ele = sb.toString();
						if (!ans.containsKey(ele)) {
							ans.put(ele, 1);
						}
						else {
							ans.put(ele, ans.get(ele) + 1);
						}
						sb = new StringBuilder();
					}
					else if (i <= e && Character.isLowerCase(cs[i])) {
						while (i <= e && Character.isLowerCase(cs[i])) {
							sb.append(cs[i]);
							i ++;
						}
						
						if (i <= e && !Character.isDigit(cs[i])){
							String ele = sb.toString();
							if (!ans.containsKey(ele)) {
								ans.put(ele, 1);
							}
							else {
								ans.put(ele, ans.get(ele) + 1);
							}
							sb = new StringBuilder();
						}
					}
					
					if (i <= e && Character.isDigit(cs[i])) {
						int num = 0;
						while (i <= e && Character.isDigit(cs[i])) {
							num = num * 10 + cs[i] - '0';
							i ++;
						}
						
						String ele = sb.toString();
						if (!ans.containsKey(ele)) {
							ans.put(ele, num);
						}
						else {
							ans.put(ele, ans.get(ele) + num);
						}
						
						sb = new StringBuilder();
					}
				}
			}
			
			String ele = sb.toString();
			if (!ele.isEmpty()) {
				if (!ans.containsKey(ele)) {
					ans.put(ele, 1);
				}
				else {
					ans.put(ele, ans.get(ele) + 1);
				}
			}
			return ans;
		}
		else {
			Map<String, Integer> lf = go(cs, s, idx - 1);
			
			StringBuilder sb = new StringBuilder();
			int p = 1;
			Map<String, Integer> include;
			int num = 0;
			int j = -1;
			for (int i = idx + 1; i <= e; ++i) {
				sb.append(cs[i]);
				if (cs[i] == '(') {
					p ++;
				}
				else if (cs[i] == ')') {
					p --;
					if (p == 0) {
						j = i + 1;
						while (j <= e && Character.isDigit(cs[j])) {
							num = num * 10 + cs[j] - '0';
							j ++;
						}
						break;
					}
				}
			}
			String ele = sb.toString().substring(0, sb.length() - 1);
			include = go(ele.toCharArray(), 0, ele.length() - 1);
			
			for (String key : include.keySet()) {
				include.put(key, include.get(key) * num);
			}
			
			Map<String, Integer> rt = go(cs, j, e);
			
			add(ans, lf);
			add(ans, include);
			add(ans, rt);
			
			return ans;
		}
	}
	
	public void add(Map<String, Integer> ans, Map<String, Integer> tmp) {
		for (String key : tmp.keySet()) {
			if (!ans.containsKey(key)) {
				ans.put(key, tmp.get(key));
			}
			else {
				ans.put(key, ans.get(key) + tmp.get(key));
			}
		}
	}
	
	
	public static void main(String[] args) {
		SolutionDay13_L0726 day = new SolutionDay13_L0726();
		System.out.println(day.countOfAtoms("BeBe49"));
	}

}
