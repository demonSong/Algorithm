package com.daimens.algorithm.august;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class SolutionDay24_P3368 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/201708/P3368.txt";
	
	static final int SIZE = (1 << 18) - 1;
	class Pair{
		int max;
		int left;
		int right;
		
			
		public Pair(int max, int left, int right){
			this.max = max;
			this.left = left;
			this.right = right;
		}
		
		@Override
		public String toString() {
			return max + " " + left + " " + right;
		}
	}
	
	Pair[] dat = new Pair[SIZE];
	int[] A;
	void solve() {
		while (true){
			int N = ni();
			if (N == 0) break;
			
			int Q = ni();
			 A = new int[N];
			
			for (int i = 0; i < N; ++i){
				A[i] = ni();
			}
			
			init(0, 0, N);
			for (int q = 0; q < Q; ++q){
				int i = ni();
				int j = ni();
				i--;
				out.println(query(0, i, j, 0, N).max);
			}
			
		}
	}
	
	// 区间 [l, r)
	public void init(int k, int l, int r){
		if (r - l == 1){
			dat[k] = new Pair(1, 1, 1);
		}
		else{
			int lch = 2 * k + 1;
			int rch = 2 * k + 2;
			init(lch, l, (l + r) / 2);
			init(rch, (l + r) / 2, r);
			
			dat[k] = new Pair(0, 0, 0);
			dat[k].max = Math.max(dat[lch].max, dat[rch].max);
			
			int mid = (l + r) / 2;
			if (A[mid - 1] == A[mid]){
				dat[k].max = Math.max(dat[k].max, dat[lch].right + dat[rch].left);
			}
			
			if (A[l] == A[mid]){
				dat[k].left = dat[lch].left + dat[rch].left;
			}
			else{
				dat[k].left = dat[lch].left;
			}
			
			if (A[r - 1] == A[mid - 1]){
				dat[k].right = dat[lch].right + dat[rch].right;
			}
			else{
				dat[k].right = dat[rch].right;
			}
		}
	}
	
	// 查询
	public Pair query(int k, int i, int j, int l, int r){
		if (j <= l || i >= r) return new Pair(0, 0, 0);
		else if (i <= l && j >= r){
			return dat[k];
		}
		else{
			int mid = (l + r) / 2;
			Pair lch = query(2 * k + 1, i, j, l, mid);
			Pair rch = query(2 * k + 2, i, j, mid, r);
			
			Pair ans = new Pair(Math.max(lch.max, rch.max), lch.left, rch.right);
			
			if (A[mid] == A[mid - 1]){
				ans.max = Math.max(ans.max, lch.right + rch.left);
			}
			
			if (A[l] == A[mid]) ans.left += rch.left;
			if (A[r - 1] == A[mid - 1]) ans.right += lch.right;
			
			return ans;
		}
	}
	
	void run() throws Exception {
		is = oj ? System.in : new FileInputStream(new File(INPUT));
		out = new PrintWriter(System.out);

		long s = System.currentTimeMillis();
		solve();
		out.flush();
		tr(System.currentTimeMillis() - s + "ms");
	}

	public static void main(String[] args) throws Exception {
		new SolutionDay24_P3368().run();
	}

	private byte[] inbuf = new byte[1024];
	public int lenbuf = 0, ptrbuf = 0;

	private int readByte() {
		if (lenbuf == -1)
			throw new InputMismatchException();
		if (ptrbuf >= lenbuf) {
			ptrbuf = 0;
			try {
				lenbuf = is.read(inbuf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (lenbuf <= 0)
				return -1;
		}
		return inbuf[ptrbuf++];
	}

	private boolean isSpaceChar(int c) {
		return !(c >= 33 && c <= 126);
	}

	private int skip() {
		int b;
		while ((b = readByte()) != -1 && isSpaceChar(b))
			;
		return b;
	}

	private double nd() {
		return Double.parseDouble(ns());
	}

	private char nc() {
		return (char) skip();
	}

	private String ns() {
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b != '
									// ')
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}

	private char[] ns(int n) {
		char[] buf = new char[n];
		int b = skip(), p = 0;
		while (p < n && !(isSpaceChar(b))) {
			buf[p++] = (char) b;
			b = readByte();
		}
		return n == p ? buf : Arrays.copyOf(buf, p);
	}

	private char[][] nm(int n, int m) {
		char[][] map = new char[n][];
		for (int i = 0; i < n; i++)
			map[i] = ns(m);
		return map;
	}

	private int[] na(int n) {
		int[] a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = ni();
		return a;
	}

	private int ni() {
		int num = 0, b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
		if (b == '-') {
			minus = true;
			b = readByte();
		}

		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = readByte();
		}
	}

	private long nl() {
		long num = 0;
		int b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
		if (b == '-') {
			minus = true;
			b = readByte();
		}

		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = readByte();
		}
	}

	private boolean oj = System.getProperty("ONLINE_JUDGE") != null;

	private void tr(Object... o) {
		if (!oj)
			System.out.println(Arrays.deepToString(o));
	}
}

//没有使用非递减的性质，统计起来相当慢

//	static final int B = 1000;
//	static final int MAX_N = 100000 + 2000;
//	Map<Integer, Integer>[] bucket = new HashMap[MAX_N / B];
//	
//	void solve() {
//		while (true){
//			int N = ni();
//			if (N == 0) break;
//			
//			int Q = ni();
//			int[] A = new int[N];
//
//			for (int i = 0; i <= N / B; ++i) bucket[i] = new HashMap<Integer, Integer>();
//			for (int i = 0; i < N; ++i){
//				A[i] = ni();
//				int b = i / B;
//				if (!bucket[b].containsKey(A[i])) bucket[b].put(A[i], 0);
//				bucket[b].put(A[i], bucket[b].get(A[i]) + 1);
//			}
//			
//			for (int q = 0; q < Q; ++q){
//				int i = ni();
//				int j = ni();
//				i--;
//				
//				int l = i, r = j;
//				Map<Integer, Integer> map = new HashMap<Integer, Integer>();
//				int max = 0;
//				while (l < r && l % B != 0){
//					int key = A[l++];
//					if (!map.containsKey(key)) map.put(key, 0);
//					map.put(key, map.get(key) + 1);
//				}
//				
//				while (l < r && r % B != 0){
//					int key = A[--r];
//					if (!map.containsKey(key)) map.put(key, 0);
//					map.put(key, map.get(key) + 1);
//				}
//				
//				while (l < r){
//					int b = l / B;
//					for (int key : bucket[b].keySet()){
//						if (!map.containsKey(key)) map.put(key, 0);
//						map.put(key, map.get(key) + bucket[b].get(key));
//					}
//					l += B;
//				}
//				
//				for (int key : map.keySet()){
//					max = Math.max(map.get(key), max);
//				}
//				
//				out.println(max);
//			}
//		}
//	}


