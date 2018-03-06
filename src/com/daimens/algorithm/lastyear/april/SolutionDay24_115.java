package com.daimens.algorithm.april;

/**
 * 
 * @author DemonSong
 * 
 *         115.Distinct Subsequences
 * 
 *         Given a string S and a string T, count the number of distinct
 *         subsequences of T in S.
 * 
 *         A subsequence of a string is a new string which is formed from the
 *         original string by deleting some (can be none) of the characters
 *         without disturbing the relative positions of the remaining
 *         characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is
 *         not).
 * 
 *         Here is an example: S = "rabbbit", T = "rabbit"
 * 
 *         Return 3.
 *         
 *         连续传递可以看作是置空
 *         位置信息同样会影响结果
 *         
 *         解决有顺序的T在S中的组合数
 *         "ra" ,"r","r","a","a"
 *         
 *         aarr 
 *         1 1 1 1 1
 *         0 0 0 1 2
 *         0 0 0 0 0
 *         
 *		   ra的空间位置决定了组合数
 *         rara
 *         1 1 1 1 1
 *         0 1 1 2 2
 *         0 0 1 1 3
 *         
 *         arar
 *         rraa
 *         arra
 *         raar
 *         
 *         
 *         内循环为：s
 *         外循环为：t
 *         
 *         T 中的前j个字符 在S中出现的次数
 *         
 *         "aaa", "aa"
 *         
 *         1 1 1 0
 *         0 1 2 3
 *         0 0 1 3
 *         
 *         r
 *         ra,
 *         rab,
 *         rabb,
 *         rabbit,
 *         rabbbit,
 *         
 *         ra
 *         r,
 *         ra,
 *         rab,
 *         rabb,
 *         rabbit,
 *         rabbbit,
 *         
 *         rab
 *         r,
 *         ra,
 *         rab,
 *         rabb,
 *         rabbit,
 *         rabbbit,
 *         
 *         rabb
 *         r,
 *         ra,
 *         rab,
 *         rabb,
 *         rabbit,
 *         rabbbit,
 *         
 *         rabbi
 *         r,
 *         ra,
 *         rab,
 *         rabb,
 *         rabbit,
 *         rabbbit,
 *         
 *         rabbit
 *         r,
 *         ra,
 *         rab,
 *         rabb,
 *         rabbit,
 *         rabbbit,
 */
public class SolutionDay24_115 {

	public int numDistinct(String s, String t) {
		
		int[][] mem = new int[t.length()+1][s.length()+1];
		
		for (int j = 0; j <= s.length(); j++)
			mem[0][j] = 1;
		
		
		for (int i = 0; i < t.length(); i++){
			for (int j = 0; j < s.length(); j++){
				if (s.charAt(j) == t.charAt(i)){
					mem[i+1][j+1] = mem[i][j] + mem[i+1][j];
				}else{
					mem[i+1][j+1] = mem[i+1][j];
				}
			}
		}
		
		return mem[t.length()][s.length()];
    }

	public static void main(String[] args) {
		SolutionDay24_115 day = new SolutionDay24_115();
		day.numDistinct("rara", "ra");
	}
}
