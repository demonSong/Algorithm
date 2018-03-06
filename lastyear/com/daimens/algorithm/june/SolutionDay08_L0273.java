package com.daimens.algorithm.june;

/**
 * 
 * @author DemonSong
 * 
 *         273.Integer to English Words
 * 
 *         Convert a non-negative integer to its english words representation.
 *         Given input is guaranteed to be less than 231 - 1.
 * 
 *         For example, 123 -> "One Hundred Twenty Three" 12345 -> "Twelve
 *         Thousand Three Hundred Forty Five" 1234567 -> "One Million Two
 *         Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 *
 */
public class SolutionDay08_L0273 {
	
//	private final String[] LESS_THAN_20 = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
//	private final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
//	private final int[] BIT = {1000000000,1000000,1000,100};
//	public String numberToWords(int num) {
//		if (num >= 0 && num < 100){
//			if (num >= 0 && num < 20){
//				return LESS_THAN_20[num];
//			}
//			if (num >= 20){
//				String ans = TENS[num / 10 ] + " " + LESS_THAN_20[num % 10];
//				if (num % 10 == 0){
//					return ans.substring(0, ans.length()-5);
//				}
//				return ans;
//			}
//		}
//		String nums = Integer.toString(num);
//		int n = nums.length();
//		StringBuilder sb = new StringBuilder();
//		if (n == 10){
//			sb.append(numberToWords(num / BIT[0]) + " Billion ");
//			if (num / BIT[1] % 1000 != 0)
//				sb.append(numberToWords(num / BIT[1] % 1000) + " Million ");
//			if (num / BIT[2] % 1000 != 0)
//				sb.append(numberToWords(num / BIT[2] % 1000) + " Thousand ");
//			if (num / BIT[3] % 10 != 0)
//				sb.append(numberToWords(num / BIT[3] % 10) + " Hundred ");
//			sb.append(numberToWords(num % 100));
//		}
//		else if (n >= 7){
//			sb.append(numberToWords(num / BIT[1] % 1000) + " Million ");
//			if (num / BIT[2] % 1000 != 0)
//				sb.append(numberToWords(num / BIT[2] % 1000) + " Thousand ");
//			if (num / BIT[3] % 10 != 0)
//				sb.append(numberToWords(num / BIT[3] % 10) + " Hundred ");
//			sb.append(numberToWords(num % 100));
//		}
//		else if (n >= 4){
//			sb.append(numberToWords(num / BIT[2] % 1000) + " Thousand ");
//			if (num / BIT[3] % 10 != 0)
//				sb.append(numberToWords(num / BIT[3] % 10) + " Hundred ");
//			sb.append(numberToWords(num % 100));
//		}
//		else if (n >= 3){
//			sb.append(numberToWords(num / BIT[3] % 10) + " Hundred ");
//			sb.append(numberToWords(num % 100));
//		}
//		
//		return sb.toString().replace(" Zero", "");
//    }
	
//	private final String[] LESS_THAN_20 = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
//	private final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
//	private final int[] BIT = {1000000000,1000000,1000,100};
//	public String numberToWords(int num) {
//		if (num >= 0 && num < 100){
//			if (num >= 0 && num < 20){
//				return LESS_THAN_20[num];
//			}
//			if (num >= 20){
//				String ans = TENS[num / 10 ] + " " + LESS_THAN_20[num % 10];
//				if (num % 10 == 0){
//					return ans.substring(0, ans.length()-5);
//				}
//				return ans;
//			}
//		}
//		String nums = Integer.toString(num);
//		int n = nums.length();
//		StringBuilder sb = new StringBuilder();
//		if (n == 10){
//			sb.append(numberToWords(num / BIT[0]) + " Billion ");
//		}
//		if (n >= 7 && num / BIT[1] % 1000 != 0){
//			sb.append(numberToWords(num / BIT[1] % 1000) + " Million ");
//		}
//		if (n >= 4 && num / BIT[2] % 1000 != 0){
//			sb.append(numberToWords(num / BIT[2] % 1000) + " Thousand ");
//		}
//		if (n >= 3 && num / BIT[3] % 10 != 0){
//			sb.append(numberToWords(num / BIT[3] % 10) + " Hundred ");
//		}
//		sb.append(numberToWords(num % 100));
//		return sb.toString().replace(" Zero", "");
//    }
	
	private final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
	private final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
	
	public String numberToWords(int num) {
		if (num == 0) return "Zero";
		return helper(num);
    }
	
	private String helper(int num){
		String ans = "";
		if (num < 20) ans = LESS_THAN_20[num];
		else if (num < 100) ans = TENS[num / 10] + " " + LESS_THAN_20[num % 10];
		else if (num < 1000) ans = helper(num / 100) + " Hundred " + helper(num % 100);
		else if (num < 1000000) ans = helper(num / 1000) + " Thousand " + helper(num % 1000); 
		else if (num < 1000000000) ans = helper(num/1000000) + " Million " +  helper(num % 1000000);
        else ans = helper(num/1000000000) + " Billion " + helper(num % 1000000000);
        return ans.trim();
	}
	
	public static void main(String[] args) {
		SolutionDay08_L0273 day = new SolutionDay08_L0273();
		System.out.println(day.numberToWords(Integer.MAX_VALUE));
		System.out.println(Integer.MAX_VALUE % 100);
	}
}
