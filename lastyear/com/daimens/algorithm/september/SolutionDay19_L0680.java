package com.daimens.algorithm.september;

public class SolutionDay19_L0680 {
	
//	public boolean validPalindrome(String s) {
//        char[] cs = s.toCharArray();
//        int n = cs.length;
//        if (n == 0) return true;
//        int lf = 0, rt = n - 1;
//        int cnt = 0;
//        boolean valid1 = true;
//        while (lf < rt){
//            if (cs[lf] == cs[rt]){
//                lf ++;
//                rt --;
//            }   
//            else{
//                if (cs[lf + 1] == cs[rt]){
//                    lf ++;
//                    cnt++;
//                }
//                else if (cs[lf] == cs[rt - 1]){
//                    rt --;
//                    cnt++;
//                }
//                else{
//                	valid1 = false;
//                    break;
//                }
//            }
//        }
//        valid1 = valid1 && cnt <= 1;
//        boolean valid2 = true;
//        cnt = 0;
//        lf = 0;
//        rt = n - 1;
//        cnt = 0;
//        while (lf < rt){
//            if (cs[lf] == cs[rt]){
//                lf ++;
//                rt --;
//            }   
//            else{
//            	if (cs[lf] == cs[rt - 1]){
//                     rt --;
//                     cnt++;
//                }
//            	else if (cs[lf + 1] == cs[rt]){
//                    lf ++;
//                    cnt++;
//                }
//                else{
//                    valid2 = false;
//                    break;
//                }
//            }
//        }
//        valid2 = valid2 && cnt <= 1;
//        return valid1 || valid2;
//    }

	
	public boolean validPalindrome(String s) {
		char[] cs = s.toCharArray();
		int lf = 0, rt = s.length() - 1;
		while (lf < rt) {
			if (cs[lf] != cs[rt]) return isPalindrome(cs, lf + 1, rt) || isPalindrome(cs, lf, rt - 1);
			lf ++;
			rt --;
		}
		return true;
	}
	
	boolean isPalindrome(char[] cs, int start, int end) {
		while (start < end) {
			if (cs[start++] != cs[end--]) return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		SolutionDay19_L0680 day = new SolutionDay19_L0680();
		System.out.println(new StringBuilder("aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga").reverse().toString());
		System.out.println(new StringBuilder("aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga").toString());
		System.out.println(day.validPalindrome("abc"));
	}
}
