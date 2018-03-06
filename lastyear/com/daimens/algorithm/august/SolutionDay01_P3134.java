package com.daimens.algorithm.august;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class SolutionDay01_P3134 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/201708/P3134.txt";
	
	int MAX_N = 1024;
	int MAX_D = 20;
	int[] exp = new int[MAX_D];
	int[] ans = new int[MAX_N];
	
	void solve(){
		Arrays.fill(exp, 1);
		for (int i = 2; i < MAX_N; ++i){
			ans[i] = upper(i);
		}
		dfs(0);
		while (true){
			int n = ni();
			if (n == 0) break;
			out.println(ans[n]);
		}
	}
	
	public void dfs(int d) {
		if (d > MAX_D) {
			return; 
		}
		for (int i = 0; i <= d; i++) {
			exp[d + 1] = exp[i] + exp[d]; // 乘法
			if (exp[d + 1] < MAX_N && ans[exp[d + 1]] >= d + 1) { //这层的解要是被更新的话，继续更新下下层的解
				ans[exp[d + 1]]  = d + 1; //更新解
				dfs(d + 1);
			}
			exp[d + 1] = exp[d] - exp[i]; // 除法
			if (exp[d + 1] > 0 && ans[exp[d + 1]] >= d + 1) {
				ans[exp[d + 1]] = d + 1;
				dfs(d + 1);
			}
		}
	}
	
	public int upper(int n){
		int cnt = 0;
		while (n > 0){
			if ((n & 1) != 0){
				cnt ++;
			}
			n >>= 1;
			cnt ++;
		}
		return cnt - 2;
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
		new SolutionDay01_P3134().run();
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

//class State{
//int n;
//int turn;
//public State(int n, int turn){
//	this.n = n;
//	this.turn = turn;
//}
//}
//
//void solve() {
//while (true){
//	int n = ni();
//	if (n == 0) break;
//	Queue<Integer> queue = new LinkedList<Integer>();
//	Set<Integer> visited = new HashSet<Integer>();
//	List<State> states = new ArrayList<State>();
//	queue.offer(1);
//	int ans = -1;
//	int turn = 0;
//	boolean end = false;
//	outer: while (!queue.isEmpty() && !end){
//		int size = queue.size();
//		for (int i = 0; i < size; ++i){
//			int now = queue.poll();
//			if (visited.contains(now)) continue;
//			states.add(new State(now, turn));
//			if (now == n){
//				ans = turn;
//				end = true;
//				continue outer;
//			}
//			visited.add(now);
//			for (int j : visited){
//				for (int k : visited){
//					if (!visited.contains(j + k)){
//						queue.offer(j + k);
//					}
//					if (k > j && !visited.contains(k - j)){
//						queue.offer(k - j);
//					}
//				}
//			}
//		}
//		turn++;
//	}
//	Collections.sort(states, (a, b) -> (a.turn - b.turn));
//	for (State ss : states){
//		out.println(ss.n + ":" + ss.turn);
//	}
//	out.println(ans);
//}
//}


