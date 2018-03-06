package com.daimens.algorithm.august;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SolutionDay13_B1006 {
	
	static class Interval{
		int ss;
		int ee;
		public Interval(int ss, int ee){
			this.ss = ss;
			this.ee = ee;
		}
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()){
			int n = in.nextInt();
			int m = in.nextInt();
			Interval[] intervals = new Interval[n];
			for (int i = 0; i < n; ++i){
				int ss = in.nextInt();
				int ee = in.nextInt();
				intervals[i] = new Interval(ss, ee);
			}
			
			//合并
			Arrays.sort(intervals, new Comparator<Interval>() {
				@Override
				public int compare(Interval o1, Interval o2) {
					return o1.ss != o2.ss ? o1.ss - o2.ss : o1.ee - o2.ee;
				}
			});
			
			List<Interval> process = new ArrayList<Interval>();
			Interval nn = intervals[0];
			for (int i = 1; i < n; ++i){
				Interval nxt = intervals[i];
				if (nxt.ss > nn.ee){
					process.add(nn);
					nn = intervals[i];
				}
				else{
					nn.ee = Math.max(nxt.ee, nn.ee);
				}
			}
			process.add(nn);
			// find max
			int max = 0;
			int[] a1 = new int[process.size()];
			int[] a2 = new int[process.size() - 1];
			int[] sum = new int[process.size() + 1];
			for (int i = 0; i < process.size(); ++i){
				a1[i] = process.get(i).ee - process.get(i).ss + 1;
				sum[i + 1] = sum[i] + a1[i];
				max = Math.max(max, a1[i] + m);
			}
			
			for (int i = 1; i < process.size(); ++i){
				Interval prv = process.get(i - 1);
				Interval nxt = process.get(i);
				a2[i - 1] = nxt.ss - prv.ee + 1 - 2;
			}
			
			int size = a2.length;
			int lf = 0, rt = 0;
			int hh = 0;
			for (;;){
				while (rt < size && hh <= m){
					hh += a2[rt++];
					max = Math.max(max, hh + sum[rt + 1] - sum[lf]);
				}
				if (hh <= m) break;
				hh -= a2[lf++];
			}
			
			System.out.println(max);
		}
		in.close();
	}

}
