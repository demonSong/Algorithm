package com.daimens.algorithm.august;

import java.util.HashSet;
import java.util.Set;

public class SolutionDay12_B1000 {
	
	public static void main(String[] args) {
		for (int i = 1; i <= 500000; ++i){
			if (factors(i) != ss(i)){
				System.out.println("测试失败");
				return;
			}
		}
		System.out.println("测试成功");
	}
	
	public static int factors(int p){
		int res = 0;
		for (int i = 1; i <= p / i; ++i){
			if (p % i == 0){
				int x = p / i;
				if (x != i) res += 2;
				else res += 1;
			}
		}
		return res;
	}
	
	public static int ss(int x){
		Set<Integer> set = new HashSet<Integer>();
		set.add(1);
		set.add(x);
		for (int i = 2; i <= x / i; ++i){
			if (x % i == 0){
				set.add(i);
				set.add(x / i);
			}
		}
		return set.size();
	}
	
}


