package com.daimens.algorithm.june;

/**
 * 
 * @author DemonSong
 * 
 *         420. Strong Password Checker
 * 
 *         A password is considered strong if below conditions are all met:
 * 
 *         It has at least 6 characters and at most 20 characters. It must
 *         contain at least one lowercase letter, at least one uppercase letter,
 *         and at least one digit. It must NOT contain three repeating
 *         characters in a row ("...aaa..." is weak, but "...aa...a..." is
 *         strong, assuming other conditions are met). Write a function
 *         strongPasswordChecker(s), that takes a string s as input, and return
 *         the MINIMUM change required to make s a strong password. If s is
 *         already strong, return 0.
 * 
 *         Insertion, deletion or replace of any one character are all
 *         considered as one change.
 * 
 *
 */
public class SolutionDay14_L0420 {
	
	public int strongPasswordChecker(String s) {
		int n = s.length();
		char[] cs = s.toCharArray();
		int res = 0;
		int d = 1, a = 1, A = 1;
		for (int i = 0; i < n; ++i){
			if (cs[i] >= '0' && cs[i] <= '9') d = 0;
			if (cs[i] >= 'a' && cs[i] <= 'z') a = 0;
			if (cs[i] >= 'A' && cs[i] <= 'Z') A = 0;
		}
		int missing = d + a + A;
		if (faceSitu3(cs) != 0){
			int opera = faceSitu3(cs);
			if (n < 6){
				res += opera != 5 ? (6 - n) : 2;
			}
			else if (n > 20){
				res += (n - 20);
				opera -= (n - 20); //删连续的
				opera = Math.max(0, opera);
				if (opera == 0) res += missing;
				
			}
		}else{
			
		}
		return 0;
    }
	
	// 连续出现字符串的个数
	private int faceSitu3(char[] cs){
		int cnt = 0;
		char pre = '#';
		int k = 1;
		for (int i = 0; i < cs.length; ++i){
			if (cs[i] == pre){
				k++;
			}
			else{
				if (k >= 3){
					cnt += (k - 2);
				}
				k = 1;
			}
			pre = cs[i];
		}
		if (k >= 3) cnt += (k -2);
		return cnt;
	}
	
	public static void main(String[] args) {
		SolutionDay14_L0420 day = new SolutionDay14_L0420();
		String s = "aaaaa44466";
		System.out.println(day.faceSitu3(s.toCharArray()));
	}

}
