package com.daimens.algorithm.december;

public class SolutionDay03_L0738 {
	
//	
//	public int monotoneIncreasingDigits(int N) {
//		String num=String.valueOf(N);
//		char[] cs=num.toCharArray();
//		char[] ans=new char[];
//		for(int i=0;i<cs.length;++i) {
//			while(cs[]) {
//				
//			}
//		}
//	}
	
	
	public int monotoneIncreasingDigits(int N) {
		int max = 0;
		String num = String.valueOf(N);
		int n = num.length();
		
		for (int i = 0; i < n; ++i) {
			StringBuilder sb = new StringBuilder(num.substring(0, i + 1));
			int tmp = Integer.parseInt(sb.toString()) - 1;
			StringBuilder ss = new StringBuilder(String.valueOf(tmp));
			for (int j = i + 1; j < n; ++j) {
				ss.append("9");
			}
			int cmp = Integer.parseInt(ss.toString());
			if (valid(ss.toString()))
				max = Math.max(max, cmp);
		}
		
		if (valid(num)) max = Math.max(max, N);
		
		return max;
    }
	
	boolean valid(String num) {
		int n = num.length();
		char[] cs = num.toCharArray();
		for (int i = 1; i < n; ++i) {
			if (cs[i] < cs[i - 1]) return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		SolutionDay03_L0738 day = new SolutionDay03_L0738();
		System.out.println(day.monotoneIncreasingDigits(23289));
	}
}
