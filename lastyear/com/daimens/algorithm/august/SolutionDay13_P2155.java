package com.daimens.algorithm.august;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class SolutionDay13_P2155 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/201708/P2155.txt";
	
	int N;
	void solve() {
		int t = ni();
		while (t --> 0){
			N = ni();
			int C = ni();
			BIT2DTree bit = new BIT2DTree();
			for (int i = 0; i < C; ++i){
				char c = nc();
				if (c == 'C'){
					int x = ni();
					int y = ni();
					int l = ni();
					int r = ni();
					x ++;
					y ++;
					l ++;
					r ++;
					bit.add(l , r, 1);
					bit.add(x - 1, y - 1, 1);
					bit.add(x - 1, r, 1);
					bit.add(l, y - 1, 1);
				}
				else{
					int x = ni();
					int y = ni();
					out.println(bit.sum(x, y) & 1);
				}
			}
			out.println();
		}
	}
	
	/***************Binary Index Tree*******************/
	
	static final int MAX_N = 1000 + 16;
	
	class BIT2DTree{
		int[][] bit;
		
		public BIT2DTree(){
			bit = new int[MAX_N][MAX_N];
		}

		public void add(int x, int y, int val){
			for (int i = x; i <= N; i += i & -i){
				for (int j = y; j <= N; j += j & -j){
					bit[i][j] += val;
				}
			}
		}
		
		public int sum(int x, int y){
			int ans = 0;
			for (int i = x; i > 0; i -= i & -i){
				for (int j = y; j > 0; j -= j & -j){
					ans += bit[i][j];
				}
			}
			return ans;
		}
	}
	
	
	//以下代码不能AC
	BIT2DTree bit_XY, bit_X, bit_Y, bit;
	
	public void init(){
		bit_XY = new BIT2DTree();
		bit_X = new BIT2DTree();
		bit_Y = new BIT2DTree();
		bit = new BIT2DTree();
	}
		
	public int sum (int x, int y){
		return bit.sum(x, y) + bit_XY.sum(x, y) * x * y + bit_X.sum(x, y) * x + bit_Y.sum(x, y) * y;
	}
	
	public int sumRange(int x, int y, int l, int r){ //l >= x && r >= y
		return sum(l, r) + sum(x - 1, y - 1) - (sum(l, y - 1) + sum(x - 1, r));
	}
	
	public void add(int x, int y, int l, int r, int val){
		bit_XY.add(x, y, val);
		bit_XY.add(x, r + 1, -val);
		bit_XY.add(l + 1, y, -val);
		bit_XY.add(l + 1, r + 1, val);
		
		bit_X.add(x, y, -val * (y - 1));
		bit_X.add(x, r + 1, val * r);
		bit_X.add(l + 1, y, (y - 1) * val);
		bit_X.add(l + 1, r + 1, - val * r);
		
		bit_Y.add(x, y, -val * (x - 1));
		bit_Y.add(x, r + 1, val * (x - 1));
		bit_Y.add(l + 1, y, val * l);
		bit_Y.add(l + 1, r + 1, - val * l);
		
		bit.add(x, y, (x - 1) * (y - 1) * val);
		bit.add(x, r + 1, -r * (x - 1) * val);
		bit.add(l + 1, y, -l * (y - 1) * val);
		bit.add(l + 1, r + 1, l * r * val);
		
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
		new SolutionDay13_P2155().run();
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
