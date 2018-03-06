package com.daimens.algorithm.august;

import java.util.Scanner;

public class SolutionDay10_P3468 {
	
//	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		while (in.hasNext()){
//			int N = in.nextInt();
//			int M = in.nextInt();
//			data = new long[size];
//			datb = new long[size];
//			
//			int[] nums = new int[N];
//			for (int i = 0; i < N; ++i){
//				nums[i] = in.nextInt();
//				add(0, i, i + 1, 0, N, nums[i]);
//			}
//			
//			for (int i = 0; i < M; ++i){
//				String T = in.next();
//				if (T.equals("Q")){
//					int a = in.nextInt();
//					int b = in.nextInt();
//					a --;
//					b --;
//					System.out.println(sum(0, a, b + 1, 0, N));
//				}
//				else{
//					int a = in.nextInt();
//					int b = in.nextInt();
//					int c = in.nextInt();
//					a --;
//					b --;
//					add(0, a, b + 1, 0, N, c);
//				}
//			}
//			
//		}
//		in.close();
//	}
	
//	static int size = (1 << 18) - 1;
//	static long[] data;
//	static long[] datb;
//	
//	public static void add(int k, int a, int b, int l, int r, int val){
//		if (a <= l && r <= b) data[k] += val;
//		else if (a < r && l < b){
//			datb[k] += (Math.min(r, b) - Math.max(l, a)) * val;
//			add(2 * k + 1, a, b, l, (l + r) / 2, val);
//			add(2 * k + 2, a, b, (l + r) / 2, r, val);
//		}
//	}
//	
//	public static long sum(int k, int a, int b, int l, int r){
//		if (b <= l || r <= a) return 0;
//		if (a <= l && r <= b){
//			return data[k] * (r - l - 1 + 1) + datb[k];
//		}
//		else{
//			long res = (Math.min(r, b) - Math.max(l, a)) * data[k];
//			res += sum(2 * k + 1, a, b, l, (l + r) / 2);
//			res += sum(2 * k + 2, a, b, (l + r) / 2, r);
//			return res;
//		}
//	}
	
	static int n;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()){
			int N = in.nextInt();
			int M = in.nextInt();
			RangeBIT bit = new RangeBIT(N + 1, n);
			n = N;
			int[] nums = new int[N];
			for (int i = 0; i < N; ++i){
				nums[i] = in.nextInt();
				bit.bit0.add(i + 1, nums[i]);
			}
			
			for (int i = 0; i < M; ++i){
				String T = in.next();
				if (T.equals("Q")){
					int a = in.nextInt();
					int b = in.nextInt();
					a --;
					long ans = 0;
					ans += bit.sum(b);
					ans -= bit.sum(a);
					System.out.println(ans);
				}
				else{
					int a = in.nextInt();
					int b = in.nextInt();
					int x = in.nextInt();
					bit.add(a, b, x);
				}
			}
			
		}
		in.close();
	}
	
//	static long[] BIT0;
//	static long[] BIT1;
//	
//	public static void addBIT0(int pos, int val){
//		while (pos <= n){
//			BIT0[pos] += val;
//			pos += pos & - pos;
//		}
//	}
//	
//	public static void addBIT1(int pos, int val){
//		while (pos <= n){
//			BIT1[pos] += val;
//			pos += pos & -pos;
//		}
//	}
//	
//	public static long sum(long[] BIT, int i){
//		long sum = 0;
//		while (i > 0){
//			sum += BIT[i];
//			i -= i & -i;
//		}
//		return sum;
//	}
	
	/***************Binary Index Tree*******************/
	static class BIT{
		long[] BIT;
		int n;
		public BIT(int N, int n){
			this.n = n;
			BIT = new long[N];
		}
		
		public void add(int i, int val){
			while (i <= n){
				BIT[i] += val;
				i += i & -i;
			}
		}
		
		public long sum(int i){
			long sum = 0;
			while (i > 0){
				sum += BIT[i];
				i -= i & -i;
			}
			return sum;
		}
	}
	
	static class RangeBIT{
		BIT bit0;
		BIT bit1;
		
		public RangeBIT(int N, int n){
			this.bit0 = new BIT(N, n);
			this.bit1 = new BIT(N, n);
		}
		
		//[L, R]
		public void add(int l, int r, int val){
			bit0.add(l, -val * (l - 1));
			bit1.add(l, val);
			bit0.add(r + 1, val * r);
			bit1.add(r + 1, -val);
		}
		
		public long sum(int i){
			return bit1.sum(i) * i + bit0.sum(i);
		}
	}
	
	
	
//	static class SegmentTree{
//		int size;
//		long[] nodes;
//		long[] aux;
//		int[] nums;
//		int n;
//		public SegmentTree(int[] nums){
//			this.n = nums.length;
//			this.size = (1 << n) - 1;
//			this.nums = nums;
//			this.nodes = new long[size];
//			this.aux = new long[size];
//			init(0, 0, n);
//		}
//		
//		public void init(int s, int l, int r){
//			//叶子结点
//			if (r - l == 1){
//				nodes[s] = nums[l];
//			}
//			else{
//				int chl = 2 * s + 1;
//				int chr = 2 * s + 2;
//				int m = (l + r) / 2;
//				init(chl, l, m);
//				init(chr, m, r);
//				nodes[s] = nodes[chr] + nodes[chl];
//			}
//		}
//		
//		public void change(int s, int val, int l, int r, int x, int y){
//			if (r - l == 1){
//				aux[s] += val;
//			}
//			else{
//				int m = (l + r) / 2;
//				if (y < m){
//					change(2 * s + 1, val, l, m, x, y);
//					aux[s] = (aux[2 * s + 1]);
//				}
//				else if (x >= m){
//					change(2 * s + 2, val, m, r, x, y);
//					aux[s] = (aux[2 * s + 2]);
//				}
//				else{
//					change (2 * s + 1, val, l, m, x, m - 1);
//					change (2 * s + 2, val, m, r, m, y);
//					aux[s] = (aux[2 * s + 1] + aux[2 * s + 2]); 
//				}
//			}
//		}
//		
//		public long query(int s, int l, int r, int x, int y){
//			long sum = 0;
//			if (y < l || x >= r) return 0;
//			if (l == x && r == y + 1) return nodes[s] + aux[s];
//			int m = (l + r) / 2;
//			if (y < m){
//				sum += query(2 * s + 1, l, m, x, y);
//			}
//			else if (x >= m){
//				sum += query(2 * s + 2, m, r, x, y);
//			}
//			else{
//				sum += query(2 * s + 1, l, m, x, m - 1);
//				sum += query(2 * s + 2, m, r, m, y);
//			}
//			return sum;
//		}
//		
//	}
}
