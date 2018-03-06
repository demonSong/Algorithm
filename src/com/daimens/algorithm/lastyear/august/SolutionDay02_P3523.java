package com.daimens.algorithm.august;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class SolutionDay02_P3523 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/201708/P3523.txt";
	
	
	static final int INF = 1 << 29;
	static final int[][] direction = {{1, 0},{-1, 0},{0, 1},{0, -1}, {0, 0}};
	int W;
	int H;
	int[] dist = new int[256 * 256 * 256];
	int[][] d = new int[3][256];
	void solve() {
		while (true){
			int w = ni();
			int h = ni();
			int n = ni();
			
			W = w;
			H = h;
			
			if (w + h + n == 0) break;
			char[][] board = new char[h][w];
			for (int i = 0; i < h; ++i){
				for (int j = 0; j < w; ++j){
					board[i][j] = nc();
				}
			}
			
			//初始状态
			int init = 0;
			int goal = 0;
			
			for (int i = 0; i < h; ++i){
				for (int j = 0; j < w; ++j){
					if (Character.isLowerCase(board[i][j])){
						int pos = board[i][j] - 'a';
						init |= ((i << 4) | j) << (pos * 8);
						board[i][j] = ' ';
					}
					if (Character.isUpperCase(board[i][j])){
						int pos = board[i][j] - 'A';
						goal |= ((i << 4) | j) << (pos * 8);
						board[i][j] = ' ';
					}
				}
			}
			
			d = new int[3][256];
			for (int i = 0; i < 3; ++i) Arrays.fill(d[i], INF);
			for (int i = 0; i < n; ++i){
				int v = stateToVerties(goal, i);
				Node start = new Node(v, 0);
				d[i][v] = 0;
				Queue<Node> queue = new PriorityQueue<Node>();
				queue.offer(start);
				while (!queue.isEmpty()){
					Node now = queue.poll();
					int row = now.v / 16;
					int col = now.v % 16;
					for (int[] dir : direction){
						int nrow = row + dir[0];
						int ncol = col + dir[1];
						int nv = nrow * 16 + ncol;
						if (nrow >= 0 && nrow < h && ncol >= 0 && ncol < w && board[nrow][ncol] != '#'
								&& d[i][now.v] + 1 < d[i][nv]) {
							d[i][nv] = d[i][now.v] + 1;
							queue.offer(new Node(nv, d[i][nv]));
						}
					}
				}
			}
			
			State start = new State(init, 0);
			State end = new State(goal, 0);
			dist = new int[256 * 256 * 256];
			Arrays.fill(dist, INF);
			dist[start.s] = 0;
			Queue<Node> queue = new PriorityQueue<Node>();
			queue.offer(new Node(start.s, 0));
			int ans = -1;
			while (!queue.isEmpty()){
				Node now = queue.poll();
				if (now.v == end.s){
					ans = dist[now.v];
					break;
				}
				
				int cost = now.d - hstar(now.v, d, n);
				if (cost > dist[now.v]) continue;
				
				List<State> nextStates = moveNext(new State(now.v, dist[now.v]), board, n);
				for (State state : nextStates){
					int next = state.s;
					if (dist[next] > dist[now.v] + 1){
						dist[next] = dist[now.v] + 1;
						queue.offer(new Node(next, dist[next] + hstar(next, d, n)));
					}
				}
			}
			
			out.println(ans);
		}
	}
	
	public int hstar(int state, int[][] d, int n){ //计算当前状态到终态的最短距离
		int dist = 0;
		for (int i = 0; i < n; ++i){
			int sv = stateToVerties(state, i);
			dist = Math.max(dist, d[i][sv]);
		}
		return dist;
	}
	
	class State{
		int s;
		int turn;
		
		public State(int s, int turn){
			this.s = s;
			this.turn = turn;
		}
	}
	
	public List<State> moveNext(State now, char[][] board, int n){
		List<State>[] states = new ArrayList[n];
		for (int i = 0; i < n; ++i) states[i] = new ArrayList<State>();
		
		for (int i = 0; i < n; ++i){
			int v = stateToVerties(now.s, i);
			int row = v / 16;
			int col = v % 16;
			for (int[] dir : direction){
				int nrow = row + dir[0];
				int ncol = col + dir[1];
				if (nrow >= 0 && nrow < H && ncol >= 0 && ncol < W && board[nrow][ncol] != '#'){
					State state = new State(ghostToState(nrow, ncol, i),now.turn + 1);
					states[i].add(state);
				}
			}
		}
		
		//check 三种非法状态， 1. 撞墙 （在生成状态时已经避免） 2. 重合  3. 交换
		List<State> validState = new ArrayList<State>();
		for (int i = 0; 0 < n && i < states[0].size(); ++i){
			int state = states[0].get(i).s;
			int turn = states[0].get(i).turn;
			for (int j = 0; 1 < n && j < states[1].size(); ++j){
				state = states[0].get(i).s;
				state |= states[1].get(j).s;
				for (int l = 0; 2 < n && l < states[2].size(); ++l){
					state = states[0].get(i).s;
					state |= states[1].get(j).s;
					state |= states[2].get(l).s;
					if (3 == n && valid(state, now.s, n)) validState.add(new State(state, turn));
				}
				if (2 == n && valid(state, now.s, n)) validState.add(new State(state, turn));
			}
			if (1 == n) validState.add(new State(state, turn));
		}
		return validState;
	}
	
	
	public boolean valid(int state, int prev, int n){
		int i = state >> 16 & (0xff);
		int j = state >>  8 & (0xff);
		int k = state >>  0 & (0xff);
		if (n == 2 && (j == k)) return false;
		if (n == 3 && (i == j || j == k || k == i)) return false; //重合
		if (n == 2){
			int pj = prev >> 8 & (0xff);
			int pk = prev >> 0 & (0xff);
			if (pj == k && pk == j) return false;
		}
		if (n == 3){
			int pi = prev >> 16 & (0xff);
			int pj = prev >>  8 & (0xff);
			int pk = prev >>  0 & (0xff);
			if (swap(i, j, pi, pj) || swap(i, k, pi, pk) || swap(j, k, pj, pk)) return false; //交换
		}
		return true;
	}
	
	public boolean swap(int i, int j, int pi, int pj){
		return (i == pj) && (j == pi);
	}
	
	public int ghostToState(int i, int j, int id){
		int state = 0;
		state = (i << 4 | j) << (id * 8);
		return state;
	}
	
	public int stateToVerties(int state, int n){
		int ss = state >> (8 * n) & (0xff);
		int si = ss >> 4;
		int sj = ss & (0x0f);
		return si * 16 + sj;
	}
	
	class Node implements Comparable<Node>{
		int v;
		int d;
		public Node(int v, int d){
			this.v = v;
			this.d = d;
		}
		@Override
		public int compareTo(Node that) {
			return this.d - that.d;
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
		new SolutionDay02_P3523().run();
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
		return !(c >= 32 && c <= 126);
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


////状态搜索
//State start = new State(init, 0);
//State end = new State(goal, 0);
//Set<Integer> visited = new HashSet<Integer>();
//Queue<State> bfs = new LinkedList<State>();
//bfs.offer(start);
//visited.add(start.s);
//int ans = -1;
//while (!bfs.isEmpty()){
//	State now = bfs.poll();
//	if (now.s == end.s){
//		ans = now.turn;
//		break;
//	}
//	List<State> nextStates = moveNext(now, board, n);
//	for (State state : nextStates){
//		if (!visited.contains(state.s) ){ //&& state.turn + hstar(state.s, d, n) >= hh
//			System.out.println(state);
//			visited.add(state.s);
//			bfs.offer(state);
//		}
//	}
//}
