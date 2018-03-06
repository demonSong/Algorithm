package com.daimens.algorithm.september;

import java.util.ArrayList;
import java.util.List;

public class SolutionDay24_L0682 {
	
//	class Pair{
//		int x;
//		Pair(int x){
//			this.x = x;
//		}
//	}
	
//	public int calPoints(String[] ops) {
//        List<Pair> ans = new ArrayList<>();
//        for (int i = 0; i < ops.length; ++i) {
//        	String val = ops[i];
//        	if (val.equals("C")) {
//        		ans.remove(ans.size() - 1);
//        	}
//        	else if (val.equals("D")) {
//        		int last = ans.get(ans.size() - 1).x;
//        		ans.add(new Pair(last * 2));
//        	}
//        	else if (val.equals("+")) {
//        		int last = ans.get(ans.size() - 1).x;
//        		int prev = ans.get(ans.size() - 2).x;
//        		ans.add(new Pair(last + prev));
//        	}
//        	else {
//        		int num = Integer.parseInt(val);
//        		ans.add(new Pair(num));
//        	}
//        }
//        
//        int sum = 0;
//        for (int i = 0; i < ans.size(); ++i) {
//        	sum += ans.get(i).x;
//        }
//        return sum;
//    }
	
	
//	public int calPoints(String[] ops) {
//        List<Integer> ans = new ArrayList<>();
//        for (int i = 0; i < ops.length; ++i) {
//        	String val = ops[i];
//        	if (val.equals("C")) {
//        		ans.remove(ans.size() - 1);
//        	}
//        	else if (val.equals("D")) {
//        		int last = ans.get(ans.size() - 1);
//        		ans.add(last * 2);
//        	}
//        	else if (val.equals("+")) {
//        		int last = ans.get(ans.size() - 1);
//        		int prev = ans.get(ans.size() - 2);
//        		ans.add(last + prev);
//        	}
//        	else {
//        		int num = Integer.parseInt(val);
//        		ans.add(num);
//        	}
//        }
//        
//        int sum = 0;
//        for (int i = 0; i < ans.size(); ++i) {
//        	sum += ans.get(i);
//        }
//        return sum;
//    }
	
	public int calPoints(String[] ops) {
		int n = ops.length;
		int[] list = new int[n + 1];
		int tot = 0;
		for (int i = 0; i < n; ++i) {
			String val = ops[i];
			if (val.equals("C")) {
				tot--;
			}
			else if (val.equals("D")) {
				int lst = list[tot - 1];
				list[tot++] = lst * 2;
			}
			else if (val.equals("+")) {
				int lst = list[tot - 1];
				int prv = list[tot - 2];
				list[tot++] = lst + prv;
			}
			else {
				int num = Integer.parseInt(val);
				list[tot++] = num;
			}
		}
		
		int sum = 0;
		for (int i = 0; i < tot; ++i) {
			sum += list[i];
		}
		
		return sum;
	}
	
	public static void main(String[] args) {
		SolutionDay24_L0682 day = new SolutionDay24_L0682();
	}
}
