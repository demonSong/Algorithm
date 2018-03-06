package com.daimens.algorithm.may;

/**
 * 
 * @author DemonSong
 * 
 *         488.Zuma Game
 * 
 *         Think about Zuma Game. You have a row of balls on the table, colored
 *         red(R), yellow(Y), blue(B), green(G), and white(W). You also have
 *         several balls in your hand.
 * 
 *         Each time, you may choose a ball in your hand, and insert it into the
 *         row (including the leftmost place and rightmost place). Then, if
 *         there is a group of 3 or more balls in the same color touching,
 *         remove these balls. Keep doing this until no more balls can be
 *         removed.
 * 
 *         Find the minimal balls you have to insert to remove all the balls on
 *         the table. If you cannot remove all the balls, output -1.
 * 
 *         Examples:
 * 
 *         Input: "WRRBBW", "RB" Output: -1 Explanation: WRRBBW -> WRR[R]BBW ->
 *         WBBW -> WBB[B]W -> WW
 * 
 *         Input: "WWRRBBWW", "WRBRW" Output: 2 Explanation: WWRRBBWW ->
 *         WWRR[R]BBWW -> WWBBWW -> WWBB[B]WW -> WWWW -> empty
 * 
 *         Input:"G", "GGGGG" Output: 2 Explanation: G -> G[G] -> GG[G] -> empty
 * 
 *         Input: "RBYYBBRRB", "YRBGB" Output: 3 Explanation: RBYYBBRRB ->
 *         RBYY[Y]BBRRB -> RBBBRRB -> RRRB -> B -> B[B] -> BB[B] -> empty
 * 
 *         Note: You may assume that the initial row of balls on the table won’t
 *         have any 3 or more consecutive balls with the same color. The number
 *         of balls on the table won't exceed 20, and the string represents
 *         these balls is called "board" in the input. The number of balls in
 *         your hand won't exceed 5, and the string represents these balls is
 *         called "hand" in the input. Both input strings will be non-empty and
 *         only contain characters 'R','Y','B','G','W'.
 *
 */
public class SolutionDay10_L0488 {
	
//	public int findMinStep(String board, String hand) {
//		if (board.isEmpty()) return -1;
//		
//		char[] b = board.toCharArray();
//		char[] h = hand.toCharArray();
//		
//		minStep = h.length;
//		dfs(b, h, 1);
//        return minStep;
//    }
//	
//	int minStep;
//	private void dfs(char[] b, char[] h, int step){
//		
//		if (step - 1 >= h.length) return;
//		
//		char hit = h[step-1];
//		for (int i = 0; i < b.length; i++){
//			if(b[i] == hit){
//				
//			}
//		}
//	}
	
	
	
	public int findMinStep(String board, String hand) {
		int[] handCount = new int[32];
		for (int i = 0; i < hand.length(); i++){
			handCount[hand.charAt(i)-'A']++; //顺序无关
		}
		int min_step = helper(board + "#", handCount);
		return min_step == 6 ? -1 : min_step;
	}

	private int helper(String board, int[] handCount){
		String s = removeConsecutive(board);
		if (s.equals("#")) return 0;
		char[] cc = s.toCharArray();
		int min_step = 6;
		for (int i = 0, j = 0; j < s.length(); j++){
			int step = 0;
			if (cc[i] == cc[j]) continue;
			// j - i = 1 or 2
			int need = 3- (j - i);
			if (need <= handCount[cc[i]-'A']){
				step += need;
				handCount[cc[i]-'A'] -= need;
				min_step = Math.min(min_step,step + helper(s.substring(0, i) + s.substring(j), handCount));
				handCount[cc[i]-'A'] += need;
			}
			i = j;
		}
		return min_step;
	}
	
	private String removeConsecutive(String board) {
		char[] cc = board.toCharArray();
		for (int i = 0, j = 0; j < cc.length; j++){
			if (cc[i] == cc[j]) continue;
			if (j - i >= 3) return removeConsecutive(board.substring(0, i) + board.substring(j));
			else i = j;
		}
		return board;
	}
	
	public static void main(String[] args) {
		SolutionDay10_L0488 day = new SolutionDay10_L0488();
		String s = "BBB";
		System.out.println(day.removeConsecutive(s));
		//day.findMinStep("RBYYBBRRB", "YRBGB");
		day.findMinStep("WWRRBBWW", "WRBRW");
		//String x = "ww";
		//System.out.println(x.substring(0, 0) + x.substring(2));
	}
	
	
}
