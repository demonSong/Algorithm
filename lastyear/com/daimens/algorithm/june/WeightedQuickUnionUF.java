package com.daimens.algorithm.june;

import com.daimens.algorithm.utils.In;

public class WeightedQuickUnionUF {
	
	int[] id;
	int[] sz;
	int count;
	
	public WeightedQuickUnionUF(int N){
		count = N;
		id = new int[N];
		sz = new int[N];
		for (int i = 0; i < N; i++) id[i] = i;
		for (int i = 0; i < N; i++) sz[i] = 1;
	}
	
	public void union(int p, int q){
		int pid = find(p);
		int qid = find(q);
		
		if (pid == qid) return;
		
		if (sz[pid] < sz[qid]){
			id[pid] = qid;
			sz[qid] += sz[pid];
		}
		else{
			id[qid] = pid;
			sz[pid] += sz[qid];
		}
		count --;
	}
	
	public boolean connect(int p, int q){
		return find(p) == find(q);
	}
	
	public int find(int p){
		while (p != id[p]){
			p = id[p];
		}
		return p;
	}
	
	public int count(){
		return count;
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
