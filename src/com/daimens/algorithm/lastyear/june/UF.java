package com.daimens.algorithm.june;

import com.daimens.algorithm.utils.In;

public class UF {
	
	int[] union;
	int SIZE;
	
	public UF(int N) {
		union = new int[N];
		for (int i = 0; i < N; i++){
			union[i] = i;
		}
		SIZE = N;
	}
	
	public void union(int p, int q){
		int pid = find(p);
		int qid = find(q);
		
		if (pid == qid) return;
		
		union[qid] = pid;
		
		SIZE--;
	}
	
	public int find(int p){
		while (p != union[p]){
			p = union[p];
		}
		return p;
	}
	
	
//	public void union(int p, int q){
//		int x = find(p);
//		int y = find(q);
//		
//		if (x == y) return;
//		
//		for (int i = 0; i < union.length; i++){
//			if (union[i] == y) union[i] = x;
//		}
//		
//		SIZE --;
//	}
	
//	public int find(int p){
//		return union[p];
//	}
	
	public boolean connected(int p, int q){
		return find(p) == find(q);
	}
	
	public int count(){
		return SIZE;
	}
	
	public static void main(String[] args) {
		In in = new In("./data/mediumUF.txt");
		int N = in.readInt();
		UF uf = new UF(N);
		for (int i = 0; i < N; i++){
			int p = in.readInt();
			int q = in.readInt();
			if (uf.connected(p, q)) continue;
			uf.union(p,q);
			System.out.println(p + " " + q);
		}
		System.out.println(uf.count() + " components");
	}
}
