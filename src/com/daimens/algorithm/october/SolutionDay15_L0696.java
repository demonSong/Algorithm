package com.daimens.algorithm.october;

public class SolutionDay15_L0696 {
	
//    public int countBinarySubstrings(String s) {
//    	int n = s.length();
//    	char[] cs = s.toCharArray();
//    	int[] num = new int[n];
//    	for (int i = 0; i < n; ++i) {
//    		num[i] = cs[i] == '0' ? - 1 : 1;
//    	}
//    	
//    	int[] sum = new int[n + 1];
//    	for (int i = 0; i < n; ++i) {
//    		sum[i + 1] = sum[i] + num[i];
//    	}
//    	
//    	Map<Integer, Integer> map = new HashMap<>();
//    	map.put(0, 0);
//    	int cnt = 0;
//    	for (int i = 1; i <= n; ++i) {
//    		if (map.containsKey(sum[i])) {
//    			if (group(cs, map.get(sum[i]), i - 1)) cnt++;
//    			
//    		}
//    		map.put(sum[i], i);
//    	}
//    	return cnt;
//    }
//    
//    boolean group(char[] cs, int i, int j) {
//    	int prev = cs[i] - '0';
//    	int n = j - i + 1;
//    	for (int k = 1; k < n / 2; ++k) {
//    		if (prev != cs[k + i] - '0') return false; 
//    	}
//    	return true;
//    }
	
// 

	public int countBinarySubstrings(String s) {
		int n = s.length();
		char[] cs = s.toCharArray();
		
		int now = cs[0] - '0';
		int cnt = 1;
		int ans = 0;
		int lst = 0;
		
		for (int i = 1; i < n; ++i) {
			if (now != cs[i]) {
				ans += Math.min(cnt, lst);
				lst = cnt;
				cnt = 1;
				now = cs[i];
			}
			else {
				cnt ++;
			}
		}
		
		ans += Math.min(cnt, lst);
		return ans;
	}
	
	public static void main(String[] args) {
		SolutionDay15_L0696 day = new SolutionDay15_L0696();
		System.out.println(day.countBinarySubstrings("00110"));
	}
}
