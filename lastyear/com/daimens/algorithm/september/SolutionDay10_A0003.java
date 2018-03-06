package com.daimens.algorithm.september;

import java.util.Scanner;

public class SolutionDay10_A0003 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			String str = in.next();
			char[] cs  = str.toCharArray();
			int min = 1 << 29;
			boolean[] isRed = new boolean[cs.length];
			for (int i = 0; i < cs.length; ++i) {
				if (cs[i] == 'R') isRed[i] = true;
				else isRed[i] = false;
			}
			
			for (int i = 0; i < cs.length; ++i) {
				int cnt = 0;
				for (int j = 0; j <= i; ++j) {
					if (!isRed[j]) cnt++;
				}
				for (int j = i + 1; j < cs.length; ++j) {
					if (isRed[j]) cnt++;
				}
				min = Math.min(min, cnt);
			}
			
			int cnt = 0;
			for (int i = 0; i < cs.length; ++i) {
				if (isRed[i]) cnt ++;
			}
			
			System.out.println(Math.min(min, cnt));
		}
		in.close();
	}

}
