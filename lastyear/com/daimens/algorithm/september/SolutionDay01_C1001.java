package com.daimens.algorithm.september;

public class SolutionDay01_C1001 {
	
	public static void main(String[] args) {
		System.out.println(solve(7));
	}
	
	public static String solve(int x){
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= x; ++i){
			int cnt = 2 * i - 1;
			int space = x - i;
			for (int j = 0; j < space; ++j){
				sb.append(" ");
			}
			for (int j = 0; j < cnt; ++j){
				sb.append("#");
			}
			sb.append("\n");
		}
		
		for (int i = x - 1; i >= 1; --i){
			int cnt = 2 * i - 1;
			int space = x - i;
			for (int j = 0; j < space; ++j){
				sb.append(" ");
			}
			for (int j = 0; j < cnt; ++j){
				sb.append("#");
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}

}
