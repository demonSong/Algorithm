package com.daimens.algorithm.june;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author DemonSong
 * 
 *         166. Fraction to Recurring Decimal
 * 
 *         Given two integers representing the numerator and denominator of a
 *         fraction, return the fraction in string format.
 * 
 *         If the fractional part is repeating, enclose the repeating part in
 *         parentheses.
 * 
 *         For example,
 * 
 *         Given numerator = 1, denominator = 2, return "0.5". Given numerator =
 *         2, denominator = 1, return "2". Given numerator = 2, denominator = 3,
 *         return "0.(6)".
 *
 */
public class SolutionDay08_L0166 {
	
	public String fractionToDecimal(int numerator, int denominator) {
		if (numerator == 0) return "0";
		StringBuilder sb = new StringBuilder();
		sb.append((((numerator & 1 << 31) ^ (denominator & 1 << 31)) != 0) ? "-" : "");
		long num = Math.abs((long)numerator);
		long den = Math.abs((long)denominator);
		
		long k = num / den;
		sb.append(k);
		num %= den;
		if (num == 0) return sb.toString();
		sb.append(".");
		//fraction
		Map<Long,Integer> map = new HashMap<>();
		while (num != 0){
			num *= 10;
			long fact = num / den;
			if (map.containsKey(num)){
				int index = map.get(num);
				sb.append(")");
				sb.insert(index-1, "(");
				break;
			}
			sb.append(fact);
			map.put(num,sb.length());
			num %= den;
		}
		
		return sb.toString();
    }
	
//	public String fractionToDecimal(int numerator, int denominator) {
//        if (numerator == 0) {
//            return "0";
//        }
//        StringBuilder res = new StringBuilder();
//        // "+" or "-"
//        res.append(((numerator > 0) ^ (denominator > 0)) ? "-" : "");
//        long num = Math.abs((long)numerator);
//        long den = Math.abs((long)denominator);
//        
//        // integral part
//        res.append(num / den);
//        num %= den;
//        if (num == 0) {
//            return res.toString();
//        }
//        
//        // fractional part
//        res.append(".");
//        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
//        map.put(num, res.length());
//        while (num != 0) {
//            num *= 10;
//            res.append(num / den);
//            num %= den;
//            if (map.containsKey(num)) {
//                int index = map.get(num);
//                res.insert(index, "(");
//                res.append(")");
//                break;
//            }
//            else {
//                map.put(num, res.length());
//            }
//        }
//        return res.toString();
//    }
	
	public static void main(String[] args) {
		SolutionDay08_L0166 day = new SolutionDay08_L0166();
		System.out.println(day.fractionToDecimal(94911151,94911150));
		System.out.println((long) 1 / 133);
	}
	
}
