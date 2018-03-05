package com.daimens.algorithm.feburary;

public class SolutionDay11_L0782 {
	
	public int getResult(int[] line) {
		int N = line.length;
		int value1 = 0 , value2 = 0 , ans = - 1;
		int cnt1 = 0 , cnt2 = 0;
		
		for (int i = 0;i < N; ++i) {
			int need = (i % 2 == 0) ? 1 : 0;
			if (line[i] != need) {
				value1 ++;
			}
			if (line[i] == 0) {
				cnt1 ++;
			}
			if (need == 0) {
				cnt2 ++;
			}
		}
		if (cnt1 == cnt2) {
			value1 /= 2;
			if (value1 < ans || ans < 0) {
				ans = value1;
			}
		}
		
		cnt1 = cnt2 = 0;
		for (int i = 0;i < N;i ++) {
			int need = (i % 2 == 0) ? 0 : 1;
			if (line[i] != need) {
				value2 ++;
			}
			if (line[i] == 0) {
				cnt1 ++;
			}
			if (need == 0) {
				cnt2 ++;
			}
		}
		if (cnt1 == cnt2) {
			value2 /= 2;
			if (value2 < ans || ans < 0) {
				ans = value2;
			}
		}
		return ans;
		
	}

	boolean check(int[][] board) {
		int N = board.length;
		// check row
		for (int i = 0; i < N; ++i) {
			int num_1 = 0;
			int num_0 = 0;
			for (int j = 0; j < N; ++j) {
				if (board[i][j] == 0)
					num_0++;
				else
					num_1++;
			}
			if (Math.abs(num_1 - num_0) > 1)
				return false;
		}

		// check col
		for (int i = 0; i < N; ++i) {
			int num_1 = 0;
			int num_0 = 0;
			for (int j = 0; j < N; ++j) {
				if (board[j][i] == 0)
					num_0++;
				else
					num_1++;
			}
			if (Math.abs(num_1 - num_0) > 1)
				return false;
		}
		return true;
	}
	
    public int movesToChessboard(int[][] board) {
    	if (check(board)) {
    		int n = board.length;
    		int[] col = new int[n];
    		for (int i = 0; i < n; ++i) col[i] = board[i][0];
    		return getResult(board[0]) + getResult(col);
    	}
    	return -1;
    }
	
//	String OK_1(int N) {
//		StringBuilder sb = new StringBuilder();
//		StringBuilder a1 = new StringBuilder();
//		StringBuilder a2 = new StringBuilder();
//		for (int i = 0; i < N; ++i) {
//			a1.append(i % 2);
//			a2.append((i + 1) % 2);
//		}
//		for (int j = 0; j < N; ++j) {
//			sb.append((j + 1) % 2 == 0 ? a1.toString() : a2.toString());
//		}
//		return sb.toString();
//	}
//	
//	String OK_2(int N) {
//		StringBuilder sb = new StringBuilder();
//		StringBuilder a1 = new StringBuilder();
//		StringBuilder a2 = new StringBuilder();
//		for (int i = 0; i < N; ++i) {
//			a1.append(i % 2);
//			a2.append((i + 1) % 2);
//		}
//		for (int j = 0; j < N; ++j) {
//			sb.append((j + 1) % 2 == 0 ? a2.toString() : a1.toString());
//		}
//		return sb.toString();
//	}
//
//	String toState(int[][] board) {
//		StringBuilder sb = new StringBuilder();
//		int n = board.length;
//		for (int i = 0; i < n; ++i) {
//			for (int j = 0; j < n; ++j) {
//				sb.append(board[i][j]);
//			}
//		}
//		return sb.toString();
//	}
//	
//	public int[][] changeRow(int[][] board, int i, int j) {
//		int N = board.length;
//		int[][] ans = new int[N][N];
//		for (int col = 0; col < N; ++col) {
//			for (int row = 0; row < N; ++row) {
//				ans[row][col] = board[row][col];
//			}
//		}
//		for (int col = 0; col < N; ++col) {
//			int tmp = ans[i][col];
//			ans[i][col] = ans[j][col]; 
//			ans[j][col] = tmp;
//		}
//		return ans;
//	}
//	
//	public int[][] changeCol(int[][] board, int i, int j){
//		int N = board.length;
//		int[][] ans = new int[N][N];
//		for (int col = 0; col < N; ++col) {
//			for (int row = 0; row < N; ++row) {
//				ans[row][col] = board[row][col];
//			}
//		}
//		for (int row = 0; row < N; ++row) {
//			int tmp = ans[row][i];
//			ans[row][i] = ans[row][j]; 
//			ans[row][j] = tmp;
//		}
//		return ans;
//	}
//	
//	boolean check(int[][] board) {
//		int N = board.length;
//		// check row
//		for (int i = 0; i < N; ++i) {
//			int num_1 = 0;
//			int num_0 = 0;
//			for (int j = 0; j < N; ++j) {
//				if (board[i][j] == 0) num_0 ++;
//				else num_1 ++;
//			}
//			if (Math.abs(num_1 - num_0) > 1) return false;
//		}
//		
//		// check col
//		for (int i = 0; i < N; ++i) {
//			int num_1 = 0;
//			int num_0 = 0;
//			for (int j = 0; j < N; ++j) {
//				if (board[j][i] == 0) num_0 ++;
//				else num_1 ++;
//			}
//			if (Math.abs(num_1 - num_0) > 1) return false;
//		}
//		return true;
//	}
//	
//    public int movesToChessboard(int[][] board) {
//    	int N = board.length;
//    	if (!check(board)) return -1;
//    	String ok_1 = OK_1(N);
//    	String ok_2 = OK_2(N);
//    	Queue<int[][]> q = new ArrayDeque<>();
//    	Set<String> vis = new HashSet<>();
//    	int step = 0;
//    	q.offer(board);
//    	vis.add(toState(board));
//    	while (!q.isEmpty()) {
//    		int size = q.size();
//    		for (int i = 0; i < size; ++i) {
//    			int[][] now = q.poll();
//    			String state = toState(now);
//    			if (state.equals(ok_1) || state.equals(ok_2)) {
//    				return step;
//    			}
//    			for (int l = 0; l < N; ++l) {
//    				for (int k = l + 1; k < N; ++k) {
//    					int[][] row = changeRow(now, l, k);
//    					String state_ = toState(row);
//    					if (!vis.contains(state_)) {
//    						q.offer(row);
//    						vis.add(state_);
//    					}
//    					int[][] col = changeCol(now, l, k);
//    					state_ = toState(col);
//    					if (!vis.contains(state_)) {
//    						q.offer(col);
//    						vis.add(state_);
//    					}
//    				}
//    			}
//    		}
//    		step += 1;
//    	}
//    	return -1;
//    }
    
	public static void main(String[] args) {
		SolutionDay11_L0782 day = new SolutionDay11_L0782();
		int[][] board = {{1, 0}, {0, 1}};
//		int[][] board = {{0,0,1,1,1,1,0},{0,0,1,1,1,1,0},{0,0,1,1,1,1,0},{0,1,0,0,1,1,1},{0,1,0,0,1,1,1},{0,1,0,0,1,1,1},{0,0,1,1,1,1,0}};
		System.out.println(day.movesToChessboard(board));
	}

}
