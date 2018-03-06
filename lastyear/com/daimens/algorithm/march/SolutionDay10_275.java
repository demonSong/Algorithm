package com.daimens.algorithm.march;

/**
 * 
 * @author DemonSong
 * 275.H-Index II
 * Follow up for H-index: What if the citations array is sorted in ascending order? Could you optimize your algorithm?
 * Hint:
 * 1. Expected runtime complexity is in O(log n) and the input is sorted.
 *
 */
public class SolutionDay10_275 {
	
	public int hIndex(int[] citations) {
		if (citations.length == 0)
			return 0;

		int len = citations.length;
		int l = 0, r = len - 1;

		while (l <= r) {
			int m = l + (r - l) / 2;
			if (citations[m] == len - m) {
				return len - m;
			} else if (citations[m] < len - m) {
				l = m + 1;
			} else {
				r = m - 1;
			}
		}

		return len - l;
	}

}
