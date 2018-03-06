package com.daimens.algorithm.june;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SolutionDay29_P3977 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			int n = new Integer(br.readLine());
			if (n == 0) break;
			long[] arra = new long[n];

			String input[] = br.readLine().split("\\s");
			for (int i = 0; i < n; i++) {
				arra[i] = new Long(input[i]);
			}

			long[] LL = new long[n];
			long[] RR = new long[n];
			int LN = 0, RN = 0;

			for (int i = 0; i < n; i += 2) {
				LL[LN++] = arra[i];
				if (i + 1 < n)
					RR[RN++] = arra[i + 1];
			}

			if (RN == 0) {
				System.out.println(LL[0] + " " + 1);
				continue;
			}

			Sum[] sums = new Sum[1 << RN];
			for (int i = 0; i < 1 << RN; ++i) {
				long sum = 0;
				int cnt = 0;
				for (int j = 0; j < RN; ++j) {
					if ((i & (1 << j)) != 0) {
						sum += RR[j];
						cnt++;
					}
				}
				sums[i] = new Sum(sum, cnt);
			}
			Arrays.sort(sums);

			long ans = 1000000000000001l;
			int ansc = 36;
			for (int i = 0; i < 1 << LN; ++i) {
				long key = 0;
				int cnt = 0;
				for (int j = 0; j < LN; ++j) {
					if ((i & (1 << j)) != 0) {
						key += LL[j];
						cnt++;
					}
				}

				int l = 0;
				int r = sums.length;
				while (r - l > 1) {
					int m = (l + r) / 2;
					if (sums[m].sum + key >= 0) {
						r = m;
					} else {
						l = m;
					}
				}
				int L = l;
				l = -1;
				r = sums.length - 1;
				while (r - l > 1) {
					int m = (l + r) / 2;
					if (sums[m].sum + key > 0) {
						r = m;
					} else {
						l = m;
					}
				}
				int R = r;
				for(int k = L; k <= R; ++k){
					long tempsum = Math.abs(sums[k].sum + key);
					int tempcount = sums[k].cnt + cnt;
					if (tempcount == 0) continue;
					
					if(tempsum < ans){
						ans = tempsum;
						ansc = tempcount;
					}
					else if(tempsum == ans & tempcount < ansc){
						ansc = tempcount;
					}
				}
			}
			System.out.println(ans + " " + ansc);
		}
	}
}

class Sum implements Comparable<Sum> {
	long sum;
	int cnt;

	Sum(long sum, int count) {
		this.sum = sum;
		this.cnt = count;
	}

	public int compareTo(Sum o) {
		if (this.sum - o.sum < 0)
			return -1;
		else
			return 1;
	}
}
