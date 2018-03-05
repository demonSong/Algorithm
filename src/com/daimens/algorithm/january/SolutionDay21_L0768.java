package com.daimens.algorithm.january;

public class SolutionDay21_L0768 {
	
	static final int SIZE = (1 << 18) + 1;
	static final int INF = 1 << 29;

	int[] dat = new int[SIZE];
	int[] max = new int[SIZE];

	int n_;

	public void init(int N) {
		n_ = 1;
		while (n_ < N)
			n_ *= 2;
		for (int i = 0; i < 2 * n_ - 1; ++i) {
			dat[i] = INF;
			max[i] = -INF;
		}
	}

	public void update(int k, int val) {
		k += (n_ - 1);
		dat[k] = val;
		max[k] = val;
		while (k > 0) {
			k = (k - 1) / 2;
			dat[k] = Math.min(dat[2 * k + 1], dat[k * 2 + 2]);
			max[k] = Math.max(max[2 * k + 1], max[k * 2 + 2]);
		}
	}

	public int query(int k, int i, int j, int l, int r) {
		if (j <= l || i >= r)
			return INF;
		else if (i <= l && j >= r) {
			return dat[k];
		} else {
			int lch = 2 * k + 1;
			int rch = 2 * k + 2;
			int mid = (l + r) / 2;
			int lf = query(lch, i, j, l, mid);
			int rt = query(rch, i, j, mid, r);
			return Math.min(lf, rt);
		}
	}
	
	public int maxQuery(int k, int i, int j, int l, int r) {
		if (j <= l || i >= r)
			return -INF;
		else if (i <= l && j >= r) {
			return max[k];
		} else {
			int lch = 2 * k + 1;
			int rch = 2 * k + 2;
			int mid = (l + r) / 2;
			int lf = maxQuery(lch, i, j, l, mid);
			int rt = maxQuery(rch, i, j, mid, r);
			return Math.max(lf, rt);
		}
	}
	
//    public int maxChunksToSorted(int[] arr) {
//    	init(arr.length);
//    	for (int i = 0; i < arr.length; ++i) {
//    		update(i, arr[i]);
//    	}
//    	int prv = 0;
//    	int ans = 1;
//    	for (int i = 1; i <= arr.length - 1; ++i) {
//    		int max = maxQuery(0, prv, i, 0, n_);
//    		int min = query(0, i, arr.length, 0, n_);
//    		if (max <= min) {
//    			ans ++;
//    			prv = i;
//    		}
//    	}
//    	return ans;
//    }
    
	
    public int maxChunksToSorted(int[] arr) {
    	int n = arr.length;
    	int ans = 1;
    	int max = -0x3f3f3f3f;
    	int[] min = new int[n];
    	min[n - 1] = arr[n - 1];
    	for (int i = n - 2; i >= 0; --i) {
    		min[i] = Math.min(arr[i], min[i + 1]);
    	}
    	for (int i = 1; i < n; ++i) {
    		max = Math.max(max, arr[i - 1]);
    		if (max <= min[i]) ans ++;
    	}
    	return ans;
    }
    
	public static void main(String[] args) {
		SolutionDay21_L0768 day = new SolutionDay21_L0768();
		int[] arra = {4,2,6,1,5,3,0};
		System.out.println(day.maxChunksToSorted(arra));
	}
}
