package com.daimens.algorithm.october;

import java.util.ArrayList;
import java.util.List;

public class SolutionDay08_M0001 {

	public void solveSudoku(char[][] board) {
		solve(board);
	}

	boolean check(char[][] board, int i, int j) {
		int[] map = new int[10];
		for (int k = 0; k < 9; ++k) {
			if (board[i][k] == '.')
				continue;
			int num = board[i][k] - '0';
			map[num]++;
			if (map[num] > 1)
				return false;
		}

		map = new int[10];
		for (int k = 0; k < 9; ++k) {
			if (board[k][j] == '.')
				continue;
			int num = board[k][j] - '0';
			map[num]++;
			if (map[num] > 1)
				return false;
		}

		map = new int[10];

		int si = i / 3 * 3;
		int sj = j / 3 * 3;

		for (int x = 0; x < 3; ++x) {
			for (int y = 0; y < 3; ++y) {
				int nx = si + x;
				int ny = sj + y;
				if (board[nx][ny] == '.')
					continue;
				int num = board[nx][ny] - '0';
				map[num]++;
				if (map[num] > 1)
					return false;
			}
		}
		return true;
	}

	boolean solve(char[][] board) {
		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j) {
				if (board[i][j] == '.') {
					for (char num = '1'; num <= '9'; ++num) {
						board[i][j] = num;
						if (check(board, i, j) && solve(board)) {
							return true;
						}
						board[i][j] = '.';
					}
					return false;
				}
			}
		}

		return false;
	}

	public String countAndSay(int n) {
		String[] s = new String[n + 1];
		s[1] = "1";
		for (int j = 2; j <= n; ++j) {
			// count
			char[] cs = s[j - 1].toCharArray();
			StringBuilder sb = new StringBuilder();
			int pre = cs[0] - '0';
			int cnt = 1;
			for (int i = 1; i < cs.length; ++i) {
				if (cs[i] - '0' != pre) {
					sb.append("" + cnt + pre);
					cnt = 1;
				} else {
					cnt++;
				}
				pre = cs[i] - '0';
			}

			sb.append("" + cnt + pre);
			s[j] = sb.toString();
		}

		return s[n];
	}

	public static List<List<Integer>> ans6 = new ArrayList<List<Integer>>();
	public static int[] path6 = new int[100];

	public static void robot6(int idx, int k, int n) {
		if (n == 0) {
			List<Integer> tmp = new ArrayList<Integer>();
			for (int i = 0; i < k; ++i) {
				tmp.add(path6[i]);
			}
			ans6.add(tmp);
			return;
		}
		if (n < 0) {
			return;
		}
		for (int i = idx + 1; i <= 9; ++i) {
			path6[idx] = i;
			robot6(idx + 1, k, n - i);
		}
	}

	public List<List<Integer>> combinationSum3(int k, int n) {
		ans6.clear();
		robot6(0, k, n);
		return ans6;

	}

	public static void main(String[] args) {
		SolutionDay08_M0001 day = new SolutionDay08_M0001();
		char[][] board = { "..9478...".toCharArray(), "7........".toCharArray(), ".2.1.9...".toCharArray(),
				"..7...24.".toCharArray(), ".64.1.59.".toCharArray(), ".98...3..".toCharArray(),
				"...8.3.2.".toCharArray(), "........6".toCharArray(), "...2759..".toCharArray() };
		day.solveSudoku(board);
		System.out.println(day.countAndSay(5));
	}
}
