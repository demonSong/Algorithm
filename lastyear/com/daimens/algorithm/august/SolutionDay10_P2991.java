package com.daimens.algorithm.august;

import java.util.Scanner;

public class SolutionDay10_P2991 {
	
	static class SegementTree{
		int size;
		double[] vx;
		double[] vy;
		double[] angle;
		
		public SegementTree(int n){
			this.size = (1 << n) - 1;
			vx = new double[size];
			vy = new double[size];
			angle = new double[size];
		}
		
		public void init(int s, int l, int r){ //对应的区间为[l,r)
			angle[s] = vx[s] = 0;
			
			//叶子结点 和非叶子结点
			if (r - l == 1){
				vy[s] = len[l]; //表示区间叶子结点与下一段的向量 
			}
			else{
				int chl = s * 2 + 1;
				int chr = s * 2 + 2;
				init(chl, l, (l + r) / 2); //天然的划分所有区间
				init(chr, (l + r) / 2, r);
				vy[s] = vy[chl] + vy[chr]; //对于非叶子结点，vy表示合并后，终点向量距离原点的（x,y）
			}
		}
		
		//从s开始，更新与s+1的角度，逆时针旋转角度a，当前线段树的结点为v，对应的区间为l和r
		//由题意得，线段树中的每个左区间的向量需要变换，但右区间作为整体不需要做任何计算。
		public void change(int s, double a, int v, int l, int r){
			if (s <= l) return; // 如果需要旋转的s,对应当前的区间为右孩子，则不需要更新，因为它们隶属于一个整体
			else if (s < r){
				int chl = v * 2 + 1, chr = v * 2 + 2;
				int m = (l + r) / 2;
				change(s, a, chl, l, m);
				change(s, a, chr, m, r);
				if (s <= m) angle[v] += a; // 右儿子需要转动的角度,如果s 在右侧，则只需要更新当前状态ang[v]
				double sin = Math.sin(angle[v]);
				double cos = Math.cos(angle[v]);
				vx[v] = vx[chl] + (cos * vx[chr] - sin * vy[chr]);
				vy[v] = vy[chl] + (sin * vx[chr] + cos * vy[chr]);
			}
		}
	}
	
	static int[] len;
	static double[] prv;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()){
			int n = in.nextInt();
			int c = in.nextInt();
			
			len = new int[n];
			prv = new double[n];
			for (int i = 0; i < n; ++i){
				len[i] = in.nextInt();
				prv[i] = Math.PI;
			}
			
			SegementTree tree = new SegementTree(15);
			tree.init(0, 0, n);
			
			for (int i = 0; i < c; ++i){
				int s = in.nextInt();
				int A = in.nextInt();
				double a = A / 360.0 * 2 * Math.PI;
				tree.change(s, a - prv[s], 0, 0, n);
				prv[s] = a;
				System.out.println(String.format("%.2f %.2f", tree.vx[0], tree.vy[0]));
			}
		}
		in.close();
	}

}
