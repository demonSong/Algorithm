package com.daimens.algorithm.may;

/**
 * 
 * @author DemonSong
 * 
 *         52.N - Queens II
 * 
 *         Follow up for N-Queens problem.
 * 
 *         Now, instead outputting board configurations, return the total number
 *         of distinct solutions.
 *
 * 
 * 
 */
public class SolutionDay23_L0052 {
	
	public int totalNQueens(int n) {
        boolean[] cols = new boolean[n];
        boolean[] diag = new boolean[2*n];
        boolean[] riag = new boolean[2*n];
        cnt = 0;
        backTrack(0, n, cols, diag, riag);
        return cnt;
    }
	
	int cnt;
	private void backTrack(int r, int n, boolean[] cols, boolean[] diag, boolean[] riag){
		if (n == r){
			cnt++;
			return;
		}else{
			for (int c = 0; c < n; c++){
				int dig = r + c, rig = r - c + n;
				if(!cols[c] && !diag[dig] && !riag[rig]){
					cols[c] = true;
					diag[dig] = true;
					riag[rig] = true;
					backTrack(r+1, n, cols, diag, riag);
					cols[c] = false;
					diag[dig] = false;
					riag[rig] = false;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		SolutionDay23_L0052 day = new SolutionDay23_L0052();
		day.totalNQueens(4);
	}

}
