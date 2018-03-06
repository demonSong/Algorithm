package com.daimens.algorithm.may;

import java.util.Scanner;

/**
 * 
 * @author DemonSong
 * 
 *         0033.Ball
 * 
 *         図のように二股に分かれている容器があります。1 から 10 までの番号が付けられた10 個の玉を容器の開口部 A から落とし、左の筒 B
 *         か右の筒 C に玉を入れます。板 D は支点 E を中心に左右に回転できるので、板 D を動かすことで筒 B と筒 C
 *         のどちらに入れるか決めることができます。
 * 
 *         開口部 A から落とす玉の並びを与えます。それらを順番に筒 B 又は筒 Cに入れていきます。このとき、筒 B と筒 C
 *         のおのおのが両方とも番号の小さい玉の上に大きい玉を並べられる場合は YES、並べられない場合は NO
 *         と出力するプログラムを作成してください。ただし、容器の中で玉の順序を入れ替えることはできないものとします。また、続けて同じ筒に入れることができるものとし、筒
 *         B, C ともに 10 個の玉がすべて入るだけの余裕があるものとします。
 * 
 *         Input 複数のデータセットが与えられます。１行目にデータセット数 N が与えられます。つづいて、N
 *         行のデータセットが与えられます。各データセットに 10 個の番号が左から順番に空白区切りで与えられます。
 * 
 *         Output 各データセットに対して、YES または NO を１行に出力して下さい。
 * 
 *         Sample Input 2 3 1 4 2 5 6 7 8 9 10 10 9 8 7 6 5 4 3 2 1 Output for
 *         the Sample Input YES NO
 *
 */
public class SolutionDay09_A0033 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		for (int i = 0; i < n; i++){
			for(int j = 1; j <= 10; j++){
				ball[j] = in.nextInt();
			}
			System.out.println(solve(ball));
		}
		in.close();
	}
	
	static int kase;
	static int[] ball = new int[15];
	static int[] l = new int[15];
	static int[] r = new int[15];
	private static String solve(int[] ball){
		kase = 0;
		dfs(1, 1, 1);
		return kase == 1 ? "YES" : "NO";
	}
	
	private static void dfs(int i, int j, int k){
		if (i > 10){
			kase = 1;
			return;
		}
		if (kase == 1) return;
		if (ball[i] > l[j-1]){ //守卫条件
			l[j] = ball[i];
			dfs(i+1, j+1, k);
		}
		if (ball[i] > r[k-1]){ //守卫条件
			r[k] = ball[i];
			dfs(i+1, j, k+1);
		}
	}
	
//	private static String solve(int[] ball){
//	int[] dp = new int[ball.length];
//	
//	int max = 0;
//	for (int i = 0; i < ball.length; i++){
//		dp[i] = 1;
//		for (int j = 0; j < i; j++){
//			if (ball[i] <= ball[j]){
//				dp[i] = Math.max(dp[i], dp[j]+1);
//			}
//			max = Math.max(max, dp[i]);
//		}
//	}
//	return max <= 2 ? "YES":"NO";
//}
}
