package com.daimens.algorithm.march;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SolutionDay27_L0501 {
	
	static class Interval implements Comparable<Interval>{
		
		int s;
		int e;
		public Interval(int s, int e) {
			this.s = s;
			this.e = e;
		}
		@Override
		public int compareTo(Interval o) {
			return this.s == o.s ? this.e - o.e : this.s - o.s;
		}
	}
	
	public static int GetOverLappingCount(List<Interval> intervals) {
		Collections.sort(intervals);
		int count = 1;
		int maxR = -0x3f3f3f3f;
		for (int i = 0; i < intervals.size(); ++i) {
			if (i == 0) maxR = Math.max(maxR, intervals.get(i).e);
			else {
				if (intervals.get(i).s <= maxR) {
					count ++;
					maxR = Math.min(maxR, intervals.get(i).e);
				}
				else {
					count = 0;
					maxR = intervals.get(i).e;
				}
			}
		}
		return count;
	} 
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int n = in.nextInt();
			int[] lf_x = new int[n];
			int[] lf_y = new int[n];
			int[] rt_x = new int[n];
			int[] rt_y = new int[n];
			for (int i = 0; i < n; ++i) lf_x[i] = in.nextInt();
			for (int i = 0; i < n; ++i) lf_y[i] = in.nextInt();
			for (int i = 0; i < n; ++i) rt_x[i] = in.nextInt();
			for (int i = 0; i < n; ++i) rt_y[i] = in.nextInt();
			
			int max = 0;
			for (int i = 0; i < n; ++i) {
				int lfx = lf_x[i];
				int rtx = rt_x[i];
				int lfy = lf_y[i];
				int rty = rt_y[i];
				
				List<Interval> intervals = new ArrayList<>();
				intervals.add(new Interval(lfx, rtx));
				for (int j = 0; j < n; ++j) {
					if (i == j) continue;
					int rrty = rt_y[j];
					int llfy = lf_y[j];
					if (rrty <= rty && rrty >= lfy || llfy >= lfy && llfy <= rrty) {
						intervals.add(new Interval(lf_x[j], rt_x[j]));
					}
				}
				max = Math.max(max, GetOverLappingCount(intervals));
			}
			System.out.println(max);
		}
		in.close();
	}

}
