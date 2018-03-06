package com.daimens.algorithm.august;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class SolutionDay02_P2032 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/201708/P2032.txt";
	
	static final int MAX_N = 10;
	int ans, H, W, limit, max_W;
	int[][] T; //以(x,y)为右下角的正方形最大边长
	int[][] F; //输入
	int[][] X; // 每个点被几个正方形覆盖
	List<int[]> candicates;
	
	void solve() {
		while (true){
			int w = ni();
			int h = ni();
			if (w + h == 0) break;
			
			W = w;
			H = h;
			F = new int[MAX_N][MAX_N]; //输入
			for (int i = 0; i < h; ++i){
				for (int j = 0; j < w; ++j){
					F[i][j] = ni();
				}
			}
			
			//初始化
			init();
			out.println(idaStar() + ans);
		}
	}
	
	void init(){
		ans = 0;
		max_W = 0;
		T = new int[MAX_N][MAX_N]; //以(x,y)为右下角的正方形最大边长
		X = new int[MAX_N][MAX_N]; // 每个点被几个正方形覆盖
		candicates = new ArrayList<int[]>();
		
		//统计每个顶点可覆盖的最大宽度
		for (int i = 0; i < W; ++i){
			T[0][i] = F[0][i];
		}
		for (int j = 0; j < H; ++j){
			T[j][0] = F[j][0];
		}
		
		for (int i = 1; i < H; ++i){
			for (int j = 1; j < W; ++j){
				if (F[i][j] != 0){
					T[i][j] = Math.min(Math.min(T[i - 1][j], T[i][j - 1]), T[i - 1][j - 1]) + 1;
				}
			}
		}
		//删除完全覆盖的正方形
		for (int i = 0; i < H; ++i){
			for (int j = 0; j < W; ++j){
				if (T[i][j] != 0){
					int len = T[i][j];
					for (int k = 0; k < len; ++k){
						for (int l = 0; l < len; ++l){
							if (k == 0 && l == 0) continue;
							if (i - k >= 0 && j - l >= 0 && T[i - k][j - l] + Math.max(l, k) <= T[i][j]){ //完全包含，则排除
								T[i - k][j - l] = 0;
							}
						}
					}
				}
			}
		}
		
		int[][] K = new int[MAX_N][MAX_N]; //每个点被几个正方形覆盖
		for (int i = 0; i < H; ++i){
			for (int j = 0; j < W; ++j){
				if (T[i][j] != 0){
					int t = T[i][j];
					for (int l = 0; l < t; ++l){
						for (int k = 0; k < t; ++k){
							K[i - l][j - k] ++;
						}
					}
				}
			}
		}
		
		for (int i = 0; i < H; ++i){
			for (int j = 0; j < W; ++j){
				outer:
				if (T[i][j] != 0){
					int t = T[i][j];
					for (int l = 0; l < t; ++l){
						for (int k = 0; k < t; ++k){
							if (K[i - l][j - k] == 1){
								for (int x = 0; x < t; ++x){
									for (int y = 0; y < t; ++y){
										X[i - x][j - y] ++;
									}
								}
								ans ++;
								T[i][j] = 0;
								break outer;
							}
						}
					}
				}
			}
		}

		for (int i = H - 1; i >= 0; --i){
			for (int j = W - 1; j >= 0; --j){
				if (T[i][j] != 0){
					max_W = Math.max(max_W, T[i][j]);
					candicates.add(new int[]{i, j});
				}
			}
		}
	}
	
	boolean done(){
		for (int i = 0; i < H; ++i){
			for (int j = 0; j < W; ++j){
				if (F[i][j] != 0 && X[i][j] == 0) return false;
			}
		}
		return true;
	}
	
	int h(){
		int sum = 0;
		for (int i = 0; i < H; ++i){
			for (int j = 0; j < W; ++j){
				if (F[i][j] != 0 && X[i][j] == 0) sum ++;
			}
		}
		return sum / (max_W * max_W);
	}
	
	int idaStar(){
		if (max_W == 0){
			return 0;
		}
		for (limit = h(); limit < 100; ++limit){
			if (dfs(0, 0)) return limit;
		}
		return -1;
	}
	
	boolean dfs(int s, int cost){
		if (done()) return true;
		if (s >= candicates.size()) return false;
		if (cost + h() >= limit) return false;
		
		for (int i = candicates.get(s)[0] + 1; i < H; i++){
	        for (int j = 0; j < W; ++j){
	            if (F[i][j] != 0 && X[i][j] == 0)
	                return false;
	        }
	    }
		
		if (dfs(s + 1, cost)) return true;
		int[][] x_backup = copy(X);
		int pi = candicates.get(s)[0];
		int pj = candicates.get(s)[1];
		int w = T[pi][pj];
		for (int l = 0; l < w; ++l){
			for (int k = 0; k < w; ++k){
				X[pi - l][pj - k]++;
			}
		}
		if (dfs(s + 1, cost + 1)) return true;
		X = copy(x_backup);
		return false;
	}
	
	int[][] copy(int[][] X){
		int n = X.length;
		int m = X[0].length;
		int[][] clone = new int[n][m];
		for (int i = 0; i < n; ++i){
			for (int j = 0; j < n; ++j){
				clone[i][j] = X[i][j];
			}
		}
		return clone;
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
		new SolutionDay02_P2032().run();
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


