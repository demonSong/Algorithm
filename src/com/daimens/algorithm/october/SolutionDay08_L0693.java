package com.daimens.algorithm.october;

public class SolutionDay08_L0693 {
	
//	public boolean hasAlternatingBits(int n) {
//		String binary = Integer.toBinaryString(n);
//		char[] cs = binary.toCharArray();
//		int bit = cs[0] - '0';
//		for (int i = 1; i < cs.length; ++i) {
//			if (bit == cs[i] - '0') return false;
//			bit = cs[i] - '0';
//		}
//		return true;
//    }
	
	public boolean hasAlternatingBits(int n) {
		int bit = n >> 0 & 1;
		n >>= 1;
		while (n > 0) {
			if (bit == (n & 1)) return false;
			bit = n & 1;
			n >>= 1;
		}
		return true;
    }
	
	public static void main(String[] args) {
		SolutionDay08_L0693 day = new SolutionDay08_L0693();
		System.out.println(day.hasAlternatingBits(7));
	}
}
