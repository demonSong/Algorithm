package com.daimens.algorithm.august;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SolutionDay12_B1001 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int MAX_N = 100000;
		Union union = new Union(2 * MAX_N);
		List<Integer> ans = new ArrayList<Integer>();
		int cnt = 0;
		for (int i = 0; i < N; ++i){
			int x = in.nextInt();
			int y = in.nextInt();
			int c = in.nextInt();
			if (c == 1){
				cnt ++;
				if (union.same(x, y + MAX_N)){
					ans.add(cnt);
					cnt = 0;
				}
				else{
					union.union(x, y);
				}
			}
			else{
				cnt ++;
				if (!union.same(x, y)){
					union.union(x, y + MAX_N);
				}
				else{
					ans.add(cnt);
					cnt = 0;
				}
			}
		}
		
		System.out.println(ans.size());
		for (int num : ans){
			System.out.println(num);
		}
		in.close();
	}
	
	
	static class Union{
        int[] id;
        int[] sz;

        public Union(int size){
            id = new int[size];
            sz = new int[size];
            for (int i = 0; i < size; ++i){
                id[i] = i;
                sz[i] = 1;
            }
        }

        public int find(int i){
            while (id[i] != i){
                i = id[i];
            }
            return i;
        }

        public boolean same(int i, int j){
            return find(i) == find(j);
        }

        public void union(int i, int j){
            int p = find(i);
            int q = find(j);

            if (p == q) return;
            if (sz[p] < sz[q]){
                id[p] = q;
                sz[q] += sz[p];
            }
            else{
                id[q] = p;
                sz[p] += sz[q];
            }
        }
    }
}
