package com.daimens.algorithm.september;

import java.util.HashSet;
import java.util.Set;

public class SolutionDay24_L0681 {
	
	
//	public String nextClosestTime(String time) {
//		String[] val = time.split(":");
//		Set<Integer> set = new HashSet<>();
//		int hour = add(set, val[0]);
//		int minu = add(set, val[1]);
//		
//		int[] times = new int[] {hour, minu};
//		nxt(times);
//		
//		while (!contains(times[0], times[1], set)) {
//			nxt(times);
//		}
//		return valid(times[0]) + ":" + valid(times[1]);
//	}
//	
//	public void nxt(int[] time) {
//		int hour = time[0];
//		int minu = time[1];
//		minu ++;
//		if (minu == 60) {
//			hour ++;
//			minu = 0;
//			if (hour == 24) hour = 0;
//		}
//		time[0] = hour;
//		time[1] = minu;
//	}
//	
//	public int add(Set<Integer> set, String timeStr) {
//		int time = Integer.parseInt(timeStr);
//		set.add(time / 10);
//		set.add(time % 10);
//		return time;
//	}
//	
//	public String valid(int time) {
//		if (time >= 0 && time <= 9) return "0" + time;
//		else return time + "";
//	}
//	
//	public boolean contains(int hour, int minu, Set<Integer> set) {
//		return set.contains(hour / 10) && set.contains(hour % 10) && set.contains(minu / 10) && set.contains(minu % 10);
//	}
	
	int diff = 0x3f3f3f3f;
	String result = "";
	int h;
	int m;
	public String nextClosestTime(String time) {
		int[] digit = new int[4];
		int tot = 0;
		String[] val = time.split(":");
		int hour = Integer.parseInt(val[0]);
		int minu = Integer.parseInt(val[1]);
		digit[tot++] = hour / 10;
		digit[tot++] = hour % 10;
		digit[tot++] = minu / 10;
		digit[tot++] = minu % 10;
		
		h = hour;
		m = minu;
		
		dfs(digit, 0, new int[4]);
		
		return result;
	}
	
	int cnt = 0;
//	void dfs(int[] digit, int i, int[] ans) {
//		if (i == 4) {
//			cnt ++;
//			int hour = 10 * ans[0] + ans[1];
//			int minu = 10 * ans[2] + ans[3];
//			int df = diff(hour, minu);
//			if (df < diff) {
//				diff = df;
//				result = valid(hour) + ":" + valid(minu);
//			}
//		}
//		else {
//			for (int j = 0; j < 4; ++j) {
//				ans[i] = digit[j];
//				if (i == 1) {
//					int hour = 10 * ans[0] + ans[1];
//					if (hour >= 0 && hour <= 23) dfs(digit, i + 1, ans);
//				}
//				else if (i == 3) {
//					int minu = 10 * ans[2] + ans[3];
//					if (minu >= 0 && minu <= 59) dfs(digit, i + 1, ans);
//				}
//				else {
//					dfs(digit, i + 1, ans);
//				}
//			}
//		}
//	}

	void dfs(int[] digit, int i, int[] ans) {
		if (i == 4) {
			cnt ++;
			int hour = 10 * ans[0] + ans[1];
			int minu = 10 * ans[2] + ans[3];
			if (hour >= 0 && hour <= 23 && minu >= 0 && minu <= 59) {
				int df = diff(hour, minu);
				if (df < diff) {
					diff = df;
					result = valid(hour) + ":" + valid(minu);
				}
			}
		}
		else {
			for (int j = 0; j < 4; ++j) {
				ans[i] = digit[j];
				dfs(digit, i + 1, ans);
				ans[i] = -1;
			}
		}
	}
	
	int diff(int hour, int minu) {
		int c2o = 60 * 60 - h * 60 - m;
		int n2o = 60 * 60 - hour * 60 - minu;
		return n2o < c2o ? c2o - n2o : c2o - n2o + 3600;
	}
	
	public String valid(int time) {
		if (time >= 0 && time <= 9) return "0" + time;
		else return time + "";
	}
	
	public static void main(String[] args) {
		SolutionDay24_L0681 day = new SolutionDay24_L0681();
		System.out.println(day.nextClosestTime("23:59"));
		System.out.println(day.cnt);
	}
}
