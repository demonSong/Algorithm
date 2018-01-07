package com.daimens.algorithm.december;
	
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
	
public class SolutionDay23_P1062 {
	
	String INPUT = "./data/judge/201712/P1062.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay23_P1062().run();
	}
	
	static final int INF = 0x3f3f3f3f;
	List<int[]>[] subs;
	int N, M;
	int[] goods;
	int[] rates;
	
	int[][][] mem;
	
	int dfs(int s, int min, int max, boolean[] vis) {
		if (mem[s][min][max] > 0) return mem[s][min][max];
		int ans = goods[s];
		vis[s] = true;
		for (int[] other : subs[s]) {
			if (!vis[other[0]] && Math.abs(max - rates[other[0]]) <= M && Math.abs(min - rates[other[0]]) <= M) {
				int maxx = Math.max(max, rates[other[0]]);
				int minn = Math.min(min, rates[other[0]]);
				ans = Math.min(ans, other[1] + dfs(other[0], minn, maxx, vis));
			}
		}
		vis[s] = false;
		mem[s][min][max] = ans;
		return ans;
	}
	
	void read() {
		M = ni();
		N = ni();
		
		goods = new int[N];
		rates = new int[N];
		
		subs = new ArrayList[N];
		for (int i = 0; i < N; ++i) subs[i] = new ArrayList<int[]>();
		
		int maxR = 0;
		for (int i = 0; i < N; ++i) {
			goods[i] = ni();   // P
			rates[i] = ni();   // L
			maxR = Math.max(maxR, rates[i]);
			int X = ni();
			for (int j = 0; j < X; ++j) {
				int T = ni();
				T --;
				int V = ni();
				subs[i].add(new int[] {T, V});
			}
		}
		
		mem = new int[N][maxR + 1][maxR + 1];
		out.println(dfs(0, rates[0], rates[0], new boolean[N]));
	}

	FastScanner in;
	PrintWriter out;
	
	void run() throws IOException {
		boolean oj;
		try {
			oj = ! System.getProperty("user.dir").equals("F:\\oxygen_workspace\\Algorithm");
		} catch (Exception e) {
			oj = System.getProperty("ONLINE_JUDGE") != null;
		}
		
		InputStream is = oj ? System.in : new FileInputStream(new File(INPUT));
		in = new FastScanner(is);
		out = new PrintWriter(System.out);
		long s = System.currentTimeMillis();
		read();
		out.flush();
		if (!oj){
			System.out.println("[" + (System.currentTimeMillis() - s) + "ms]");
		}
	}
	
	public boolean more(){
		return in.hasNext();
	}
	
	public int ni(){
		return in.nextInt();
	}
	
	public long nl(){
		return in.nextLong();
	}
	
	public double nd(){
		return in.nextDouble();
	}
	
	public String ns(){
		return in.nextString();
	}
	
	public char nc(){
		return in.nextChar();
	}
	
	class FastScanner {
		BufferedReader br;
		StringTokenizer st;
		boolean hasNext;

		public FastScanner(InputStream is) throws IOException {
			br = new BufferedReader(new InputStreamReader(is));
			hasNext = true;
		}

		public String nextToken() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (Exception e) {
					hasNext = false;
					return "##";
				}
			}
			return st.nextToken();
		}
		
		String next = null;
		public boolean hasNext(){
			next = nextToken();
			return hasNext;
		}

		public int nextInt() {
			if (next == null){
				hasNext();
			}
			String more = next;
			next = null;
			return Integer.parseInt(more);
		}

		public long nextLong() {
			if (next == null){
				hasNext();
			}
			String more = next;
			next = null;
			return Long.parseLong(more);
		}

		public double nextDouble() {
			if (next == null){
				hasNext();
			}
			String more = next;
			next = null;
			return Double.parseDouble(more);
		}
		
		public String nextString(){
			if (next == null){
				hasNext();
			}
			String more = next;
			next = null;
			return more;
		}
		
		public char nextChar(){
			if (next == null){
				hasNext();
			}
			String more = next;
			next = null;
			return more.charAt(0);
		}
	}
	
	static class D{
		
		public static void pp(int[][] board, int row, int col) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < row; ++i) {
				for (int j = 0; j < col; ++j) {
					sb.append(board[i][j] + (j + 1 == col ? "\n" : " "));
				}
			}
			System.out.println(sb.toString());
		}
		
		public static void pp(char[][] board, int row, int col) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < row; ++i) {
				for (int j = 0; j < col; ++j) {
					sb.append(board[i][j] + (j + 1 == col ? "\n" : " "));
				}
			}
			System.out.println(sb.toString());
		}
	}
	
	static class ArrayUtils {

		public static void fill(int[][] f, int value) {
			for (int i = 0; i < f.length; ++i) {
				Arrays.fill(f[i], value);
			}
		}
		
		public static void fill(int[][][] f, int value) {
			for (int i = 0; i < f.length; ++i) {
				fill(f[i], value);
			}
		}
		
		public static void fill(int[][][][] f, int value) {
			for (int i = 0; i < f.length; ++i) {
				fill(f[i], value);
			}
		}
	}
	
	static class Num{
		public static <K> void inc(Map<K, Integer> mem, K k) {
			if (!mem.containsKey(k)) mem.put(k, 0);
			mem.put(k, mem.get(k) + 1);
		}
	}
}
	
