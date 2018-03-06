package com.daimens.algorithm.june;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author DemonSong
 * 
 *         282.Expression Add Operators
 * 
 *         Given a string that contains only digits 0-9 and a target value,
 *         return all possibilities to add binary operators (not unary) +, -, or
 *         * between the digits so they evaluate to the target value.
 * 
 *         Examples: "123", 6 -> ["1+2+3", "1*2*3"] "232", 8 -> ["2*3+2",
 *         "2+3*2"] "105", 5 -> ["1*0+5","10-5"] "00", 0 -> ["0+0", "0-0",
 *         "0*0"] "3456237490", 9191 -> []
 *
 */
public class SolutionDay05_L0282 {
	public List<String> addOperators(String num, int target) {
		List<String> rst = new ArrayList<String>();
        if(num == null || num.length() == 0) return rst;
        helper(rst, "", num, 0, target, 0, 0);
        return rst;
    }
	
	private void helper(List<String> ans, String path, String num, int pos, int target, long val, long multed){
		if (pos == num.length()){
			if (target == val){
				ans.add(path);
			}
		}
		for (int i = pos; i < num.length(); i++){
			if(i != pos && num.charAt(pos) == '0') break;
			long cur = Long.parseLong(num.substring(pos, i + 1));
			if (pos == 0){
				helper(ans, path + cur, num, i + 1, target, cur, cur);
			}
			else{
				helper(ans, path + "+" + cur, num, i + 1, target, val + cur, cur);
				helper(ans, path + "-" + cur, num, i + 1, target, val - cur, -cur);
				helper(ans, path + "*" + cur, num, i + 1, target, val - multed + multed * cur, multed * cur);
			}
		}
	}
	
	public static void main(String[] args) {
		SolutionDay05_L0282 day = new SolutionDay05_L0282();
		day.addOperators("105", 5);
	}
	
}
