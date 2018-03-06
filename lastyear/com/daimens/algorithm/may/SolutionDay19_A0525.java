package com.daimens.algorithm.may;

import java.util.Scanner;

/**
 * 
 * @author DemonSong
 * 
 *         0525. Oscenbei
 * 
 *         おせんべい 問題
 *         IOI製菓では，創業以来の伝統の製法で煎餅（せんべい）を焼いている．この伝統の製法は，炭火で一定時間表側を焼き，表側が焼けると裏返して，炭火で一定時間裏側を焼くというものである．この伝統を守りつつ，煎餅を機械で焼いている．この機械は縦
 *         R (1 ≤ R ≤ 10) 行, 横 C (1 ≤ C ≤ 10000)
 *         列の長方形状に煎餅を並べて焼く．通常は自動運転で，表側が焼けたら一斉に煎餅を裏返し裏側を焼く．
 * 
 *         ある日，煎餅を焼いていると，煎餅を裏返す直前に地震が起こり何枚かの煎餅が裏返ってしまった．幸いなことに炭火の状態は適切なままであったが，これ以上表側を焼くと創業以来の伝統で定められている焼き時間を超えてしまい，煎餅の表側が焼けすぎて商品として出荷できなくなる．そこで，急いで機械をマニュアル操作に変更し，まだ裏返っていない煎餅だけを裏返そうとした．この機械は，横の行を何行か同時に裏返したり縦の列を何列か同時に裏返したりすることはできるが，残念なことに，煎餅を１枚ごと裏返すことはできない．
 * 
 *         裏返すのに時間がかかると，地震で裏返らなかった煎餅の表側が焼けすぎて商品として出荷できなくなるので，横の何行かを同時に１回裏返し，引き続き，縦の何列かを同時に１回裏返して，表側を焼きすぎずに両面を焼くことのできる煎餅，つまり，「出荷できる煎餅」の枚数をなるべく多くすることにした．横の行を１行も裏返さない，あるいは，縦の列を１列も裏返さない場合も考えることにする．出荷できる煎餅の枚数の最大値を出力するプログラムを書きなさい．
 * 
 *         地震の直後に，煎餅が次の図のような状態になったとする．黒い丸が表側が焼ける状態を，白い丸が裏側が焼ける状態を表している．
 * 
 * 
 *         1行目を裏返すと次の図のような状態になる．
 * 
 * 
 *         さらに， 1列目と5列目を裏返すと次の図のような状態になる．この状態では，出荷できる煎餅は9枚である．
 * 
 * 
 *         ヒント R の上限 10 は C の上限 10000 に比べて小さいことに注意せよ．
 * 
 *         入力 入力は複数のデータセットからなる．各データセットは以下の形式で与えられる．
 * 
 *         入力の1行目には2つの整数 R, C (1 ≤ R ≤ 10, 1 ≤ C ≤ 10 000) が空白を区切りとして書かれている．続く R
 *         行は地震直後の煎餅の状態を表す． (i+1) 行目 (1 ≤ i ≤ R) には， C 個の整数 ai,1, ai,2, ……, ai,C
 *         が空白を区切りとして書かれており， ai,j は i 行 j 列 の煎餅の状態を表している. ai,j が 1 なら表側が焼けることを，
 *         0 なら裏側が焼けることを表す．
 * 
 *         C, R がともに 0 のとき入力の終了を示す. データセットの数は 5 を超えない．
 * 
 *         出力 データセットごとに，出荷できる煎餅の最大枚数を1行に出力する．
 * 
 *         入出力例 入力例 2 5 0 1 0 1 0 1 0 0 0 1 3 6 1 0 0 0 1 0 1 1 1 0 1 0 1 0 1 1
 *         0 1 0 0 出力例 9 15
 *
 */
public class SolutionDay19_A0525 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (true) {
			int row = in.nextInt();
			int col = in.nextInt();
			if (row == 0 && col == 0)
				break;

			int[][] grid = new int[row][col];
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					grid[i][j] = in.nextInt();
				}
			}
			System.out.println(solve(grid));
		}
		in.close();
	}

	private static void flip(int[][] grid, int row) {
		for (int i = 0; i < grid[0].length; i++) {
			grid[row][i] = grid[row][i] == 1 ? 0 : 1;
		}
	}

	private static int total(int[][] grid, int col) {
		int n = grid.length;
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (grid[i][col] == 1)
				count++;
		}
		return Math.max(count, n - count);
	}
	
	private static int solve(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;

		int nn = 1 << n;
		int max = Integer.MIN_VALUE;
		
		for (int i = 0; i < nn; i++){
			for (int j = 0; j < n; j++){
				if ((i & (1 << j)) != 0) {
					flip(grid, j);
				}
			}
			int count = 0;
			for (int j = 0; j < m; j++) {
				count += total(grid, j);
			}
			max = Math.max(max, count);
			for (int j = 0; j < n; j++){
				if ((i & (1 << j)) != 0) {
					flip(grid, j);
				}
			}
		}
		return max;
	}

//	private static int solve(int[][] grid) {
//		int n = grid.length;
//		int m = grid[0].length;
//
//		int nn = 1 << n;
//		int max = Integer.MIN_VALUE;
//		while (nn-- != 0) {
//			String nns = Integer.toBinaryString(nn);
//			char[] nnc = nns.toCharArray();
//			for (int i = 0; i < nnc.length; i++) {
//				if (nnc[i] == '1') {
//					flip(grid, i);
//				}
//			}
//			int count = 0;
//			for (int j = 0; j < m; j++) {
//				count += total(grid, j);
//			}
//			max = Math.max(max, count);
//
//			for (int i = 0; i < nnc.length; i++) {
//				if (nnc[i] == '1') {
//					flip(grid, i);
//				}
//			}
//		}
//		return max;
//	}

	// private static int solve(int[][] grid) {
	// int n = grid.length;
	// int m = grid[0].length;
	//
	// int nn = 1 << n;
	//
	// int max = Integer.MIN_VALUE;
	// while (nn-- != 0) {
	// int[][] clone1 = clone(grid);
	// String nns = Integer.toBinaryString(nn);
	// char[] nnc = nns.toCharArray();
	// for (int i = nns.length() - 1; i >= 0; i--) {
	// if (nnc[i] == '1') {
	// rotate(clone1, i, true);
	// }
	// }
	// int nm = 1 << m;
	// while (nm-- != 0) {
	// int[][] clone2 = clone(clone1);
	// String nms = Integer.toBinaryString(nm);
	// char[] nmc = nms.toCharArray();
	// for (int i = nms.length() - 1; i >= 0; i--) {
	// if (nmc[i] == '1') {
	// rotate(clone2, i, false);
	// }
	// }
	// max = Math.max(max, total(clone2));
	// }
	// }
	// return max;
	// }
	//
	// private static int[][] clone(int[][] grid) {
	// int n = grid.length;
	// int m = grid[0].length;
	// int[][] clone = new int[n][m];
	// for (int i = 0; i < n; i++) {
	// for (int j = 0; j < m; j++) {
	// clone[i][j] = grid[i][j];
	// }
	// }
	// return clone;
	// }
	//
	// private static void rotate(int[][] grid, int x, boolean isRow) {
	// int n = grid.length;
	// int m = grid[0].length;
	// if (isRow) {
	// for (int i = 0; i < m; i++) {
	// grid[x][i] = grid[x][i] == 1 ? 0 : 1;
	// }
	// } else {
	// for (int i = 0; i < n; i++) {
	// grid[i][x] = grid[i][x] == 1 ? 0 : 1;
	// }
	// }
	// }
	//
	// private static int total(int[][] grid) {
	// int n = grid.length;
	// int m = grid[0].length;
	//
	// int total = 0;
	// for (int i = 0; i < n; i++) {
	// for (int j = 0; j < m; j++) {
	// if (grid[i][j] == 1)
	// total++;
	// }
	// }
	// return total;
	// }

}
