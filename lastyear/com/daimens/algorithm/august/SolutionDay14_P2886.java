package com.daimens.algorithm.august;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class SolutionDay14_P2886 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/201708/P2886.txt";
	
	class Pair{
		String name;
		int step;
		public Pair(String name, int step){
			this.name = name;
			this.step = step;
		}
	}
	
	Scanner in;
	public SolutionDay14_P2886(){
		in = new Scanner(System.in);
	}
	
	
	static final int MAX_N = 500000 + 16;
	void solve() {
		factors();
		while (in.hasNext()){
			int N = in.nextInt();
			int K = in.nextInt();
			
			/************** 计算 约数个数 ******************/
			int[] p = new int[MAX_N];
			int max = 0;
			int id = 0;
			
			for (int i = 1; i < N + 1; ++i){
				if (max < table[i]){
					max = table[i];
					id = i;
				}
				table[i] = max;
				p[i] = id;
			}
			
			init(N);
			for (int i = 1; i <= N; ++i) add(i, 1);
			Pair[] ps = new Pair[N];
			
			for (int i = 0; i < N; ++i){
				String name = in.next();
				int step = in.nextInt();
				ps[i] = new Pair(name, step);
			}
			
			int index = 0;
			int len = N;
			for (int i = 0; i < p[N]; ++i){
				index = binarySearch(K);
				add(index, -1);
				len --;
				if (len == 0) break;
				int step = ps[index - 1].step;
				if (step < 0){
					step = -step;
					K = (sum(index) - 1 + len - (step - 1) % len) % len + 1;
				}
				else{
					K = (sum(index) - 1 + step) % len + 1;
				}
			}
			
			System.out.println(ps[index - 1].name + " " + table[N]);
		}
		
	}
	
	/******************* Binary Index Tree*******************/
	int[] bit;
	int N;
	
	public void init(int n){
		bit = new int[MAX_N];
		this.N = n;
	}
	
	public void add(int i, int val){
		while (i <= N){
			bit[i] += val;
			i += i & -i;
		}
	}
	
	public int sum(int i){
		int res = 0;
		while (i > 0){
			res += bit[i];
			i -= i & -i;
		}
		return res;
	}
	
	/******************* binary search *******************/
	public int binarySearch(int key){
		int lf = 1, rt = N;
		while (lf < rt){
			int mid = lf + (rt - lf) / 2;
			if (sum(mid) < key) lf = mid + 1;
			else rt = mid;
		}
		
		if (sum(lf) == key) return lf;
		return -1;
	}
	
	// 速度太慢，采用分解为素数的办法
	int[] table = new int[MAX_N];
	public void factors(){
		Arrays.fill(table, 1);
		for (int i = 2; i < MAX_N; ++i){
			if (table[i] == 1){
				for (int j = i; j < MAX_N; j += i){
					int k = 0;
					for (int m = j; m % i == 0; m /= i, k++);
					table[j] *= (k + 1);
				}
			}
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
		new SolutionDay14_P2886().run();
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
