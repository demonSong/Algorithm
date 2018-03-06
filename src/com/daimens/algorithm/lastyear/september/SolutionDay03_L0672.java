package com.daimens.algorithm.september;

import java.util.HashSet;
import java.util.Set;

public class SolutionDay03_L0672 {
	
	public int flipLights(int n, int m) {
		n = Math.min(n, 16);
		Set<Integer> ans = new HashSet<Integer>();
		
		ans.add((1 << n) - 1);
		for (int i = 0; i < 1 << 4; ++i){
			int state = (1 << n) - 1;
			int mask  = (1 << n) - 1;
			if (Integer.bitCount(i) <= m){
				if ((i >> 4 & 1) != 0){
					state = (~state & mask);
				}
				
				if ((i >> 3 & 1) != 0){
					state = (state ^ 0x5555) & mask;
				}
				
				if ((i >> 2 & 1) != 0){
					state = (state ^ 0xAAAA) & mask;
				}
				
				if ((i >> 1 & 1) != 0){
					state = (state ^ 0x9249) & mask;
				}
				
				ans.add(state);
			}
		}
		
        return ans.size();
    }
	
	Set<String> ans;
	public void done1(String now){
		ans.add(op1(now));
		ans.add(op2(now));
		ans.add(op3(now));
		ans.add(op4(now));
	}
	
	public void done2(String now){
		// m = 2
		done1(now);
		
	}
	
	public String op1(String now){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < now.length(); ++i){
			if (now.charAt(i) == '0'){
				sb.append(1);
			}
			else{
				sb.append(0);
			}
		}
		return sb.toString();
	}
	
	public String op2(String now){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < now.length(); ++i){
			if (i % 2 == 0 && now.charAt(i) == '0'){
				sb.append(1);
			}
			
			else if (i % 2 == 0 && now.charAt(i) == '1'){
				sb.append(0);
			}
			
			else{
				sb.append(now.charAt(i));
			}
		}
		return sb.toString();
	}
	
	public String op3(String now){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < now.length(); ++i){
			if (i % 2 != 0 && now.charAt(i) == '0'){
				sb.append(1);
			}
			
			else if (i % 2 != 0 && now.charAt(i) == '1'){
				sb.append(0);
			}
			
			else{
				sb.append(now.charAt(i));
			}
		}
		return sb.toString();
	}
	
	public String op4(String now){
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= now.length(); ++i){
			if (i % 3 == 1 && now.charAt(i - 1) == '0'){
				sb.append(1);
			}
			
			else if (i % 3 == 1 && now.charAt(i - 1) == '1'){
				sb.append(0);
			}
			
			else{
				sb.append(now.charAt(i - 1));
			}
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		SolutionDay03_L0672 day = new SolutionDay03_L0672();
		System.out.println(day.flipLights(10, 6));
	}

}
