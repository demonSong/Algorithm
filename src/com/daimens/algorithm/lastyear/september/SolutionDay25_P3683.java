package com.daimens.algorithm.september;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class SCC {
	public static int n;
	public static V[] us;
	
	public static int scc(V[] vs) {
		n = vs.length;
		us = new V[n];
		for (V v : vs) if (!v.visit) dfs(v);
		for (V v : vs) v.visit = false;
		for (V u : us) if (!u.visit) dfsRev(u, n++);
		return n;
	}
	
	public static void dfs(V v) {
		v.visit = true;
		for (V u : v.fs) if (!u.visit) dfs(u);
		us[--n] = v;
	}
	
	public static void dfsRev(V v, int k) {
		v.visit = true;
		for (V u : v.rs) if (!u.visit) dfsRev(u, k);
		v.comp = k;
	}
	
	
	public static class V {
		public boolean visit;
		public int comp;
		public List<V> fs = new ArrayList<V>();
		public List<V> rs = new ArrayList<V>();
		
		public void add(V u) {
			fs.add(u);
			u.rs.add(this);
		}
	}
}

public class SolutionDay25_P3683 {
	
	String INPUT = "./data/judge/201709/P3683.txt";
	
	public static void main(String[] args) throws IOException {
		new SolutionDay25_P3683().run();
	}
	static final int MAX_N = 1000 + 16;
	int[] S = new int[MAX_N], T = new int[MAX_N], D = new int[MAX_N];
	int N;
	
	
	void read() {
		N = ni();
		for (int i = 0; i < N; ++i) {
			String ss = ns();
			String tt = ns();
			D[i] = ni();
			S[i] = Integer.parseInt(ss.substring(0, 2)) * 60 + Integer.parseInt(ss.substring(3));
			T[i] = Integer.parseInt(tt.substring(0, 2)) * 60 + Integer.parseInt(tt.substring(3));
		}
		solve();
	}
	
	boolean cross(int a, int b, int l, int r) {
		return a < r && l < b;
	}
	
	void solve() {
		SCC.V[] vs = new SCC.V[N * 2];
		for (int i = 0; i < vs.length; ++i) {
			vs[i] = new SCC.V();
		}
		for (int i = 0; i < N; ++i) {
			for (int j = i + 1; j < N; ++j) {
				if (cross(S[i], S[i] + D[i], S[j], S[j] + D[j])) {
					vs[2 * i].add(vs[2 * j + 1]);
					vs[2 * j].add(vs[2 * i + 1]);
				}
				if (cross(S[i], S[i] + D[i], T[j] - D[j], T[j])) {
					vs[2 * i].add(vs[2 * j]);
					vs[2 * j + 1].add(vs[2 * i + 1]);
				}
				if (cross(T[i] - D[i], T[i], S[j], S[j] + D[j])) {
					vs[2 * i + 1].add(vs[2 * j + 1]);
					vs[2 * j].add(vs[2 * i]);
				}
				if (cross(T[i] - D[i], T[i], T[j] - D[j], T[j])) {
					vs[2 * i + 1].add(vs[2 * j]);
					vs[2 * j + 1].add(vs[2 * i]);
				}
			}
		}
		
		SCC.scc(vs);
		for (int i = 0; i < N; ++i) {
			if (vs[2 * i].comp == vs[2 * i + 1].comp) {
				out.println("NO");
				return;
			}
		}
		
		out.println("YES");
		for (int i = 0; i < N; ++i) {
			if (vs[2 * i].comp > vs[2 * i + 1].	comp) {
				printf(S[i], S[i] + D[i]);
			}
			else {
				printf(T[i] - D[i], T[i]);
			}
		}
	}
	
	void printf(int s, int t) {
		out.printf("%02d:%02d %02d:%02d\n", s / 60, s % 60, t / 60, t % 60);
	}

	FastScanner in;
	PrintWriter out;
	
	void run() throws IOException {
		boolean oj;
		try {
			oj = ! System.getProperty("user.dir").equals("F:\\java_workspace\\leetcode");
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
}


//public class SolutionDay25_P3683 {
//
//String INPUT = "./data/judge/201709/P3683.txt";
//
//public static void main(String[] args) throws IOException {
//	new SolutionDay25_P3683().run();
//}
//static final int MAX_N = 1000 + 16;
//int[] S = new int[MAX_N], T = new int[MAX_N], D = new int[MAX_N];
//int N;
//
//static final int MAX_V = 3000 + 16;
//
//List<Integer>[] g = new List[MAX_V], rg = new List[MAX_V];
//List<Integer> po  = new ArrayList<Integer>();
//boolean[] used = new boolean[MAX_V];
//int[] cmp = new int[MAX_V];
//int V;
//
//void init(int V){
//	this.V = V;
//	Arrays.fill(used, false);
//	po = new ArrayList<Integer>();
//	for (int i = 0; i < V; ++i) g[i]  = new ArrayList<Integer>();
//	for (int i = 0; i < V; ++i) rg[i] = new ArrayList<Integer>();
//}
//
//void add(int from, int to) {
//	g[from].add(to);
//	rg[to].add(from);
//}
//
//void dfs(int v) {
//	used[v] = true;
//	for (int to : g[v]) {
//		if (!used[to]) dfs(to);
//	}
//	po.add(v);
//}
//
//void rdfs(int v, int k) {
//	used[v] = true;
//	cmp[v]  = k;
//	for (int to : rg[v]) {
//		if (!used[to]) rdfs(to, k);
//	}
//}
//
//int kosarajuSCC() {
//	for (int i = 0; i < V; ++i) {
//		if (!used[i]) dfs(i);
//	}
//	Arrays.fill(used, false);
//	int k = 0;
//	for (int i = po.size() - 1; i >= 0; --i) {
//		if (!used[po.get(i)]) rdfs(po.get(i), k++);
//	}
//	return k;
//}
//
//void read() {
//	N = ni();
//	for (int i = 0; i < N; ++i) {
//		String ss = ns();
//		String tt = ns();
//		D[i] = ni();
//		S[i] = Integer.parseInt(ss.substring(0, 2)) * 60 + Integer.parseInt(ss.substring(3));
//		T[i] = Integer.parseInt(tt.substring(0, 2)) * 60 + Integer.parseInt(tt.substring(3));
//	}
//	solve();
//}
//
//boolean cross(int a, int b, int l, int r) {
//	return a < r && l < b;
//}
//
//void solve() {
//	init(2 * N);
//	for (int i = 0; i < N; ++i) {
//		for (int j = i + 1; j < N; ++j) {
//			if (cross(S[i], S[i] + D[i], S[j], S[j] + D[j])) {
//				add(2 * i, 2 * j + 1);
//				add(2 * j, 2 * i + 1);
//			}
//			if (cross(S[i], S[i] + D[i], T[j] - D[j], T[j])) {
//				add(2 * i, 2 * j);
//				add(2 * j + 1, 2 * i + 1);
//			}
//			if (cross(T[i] - D[i], T[i], S[j], S[j] + D[j])) {
//				add(2 * i + 1, 2 * j + 1);
//				add(2 * j, 2 * i);
//			}
//			if (cross(T[i] - D[i], T[i], T[j] - D[j], T[j])) {
//				add(2 * i + 1, 2 * j);
//				add(2 * j + 1, 2 * i);
//			}
//		}
//	}
//	
//	kosarajuSCC();
//	for (int i = 0; i < N; ++i) {
//		if (cmp[2 * i] == cmp[2 * i + 1]) {
//			out.println("NO");
//			return;
//		}
//	}
//	
//	out.println("YES");
//	for (int i = 0; i < N; ++i) {
//		if (cmp[2 * i] > cmp[2 * i + 1]) {
//			printf(S[i], S[i] + D[i]);
//		}
//		else {
//			printf(T[i] - D[i], T[i]);
//		}
//	}
//}
//
//void printf(int s, int t) {
//	out.printf("%02d:%02d %02d:%02d\n", s / 60, s % 60, t / 60, t % 60);
//}
//
//FastScanner in;
//PrintWriter out;
//
//void run() throws IOException {
//	boolean oj;
//	try {
//		oj = ! System.getProperty("user.dir").equals("F:\\java_workspace\\leetcode");
//	} catch (Exception e) {
//		oj = System.getProperty("ONLINE_JUDGE") != null;
//	}
//	
//	InputStream is = oj ? System.in : new FileInputStream(new File(INPUT));
//	in = new FastScanner(is);
//	out = new PrintWriter(System.out);
//	long s = System.currentTimeMillis();
//	read();
//	out.flush();
//	if (!oj){
//		System.out.println("[" + (System.currentTimeMillis() - s) + "ms]");
//	}
//}
//
//public boolean more(){
//	return in.hasNext();
//}
//
//public int ni(){
//	return in.nextInt();
//}
//
//public long nl(){
//	return in.nextLong();
//}
//
//public double nd(){
//	return in.nextDouble();
//}
//
//public String ns(){
//	return in.nextString();
//}
//
//public char nc(){
//	return in.nextChar();
//}
//
//class FastScanner {
//	BufferedReader br;
//	StringTokenizer st;
//	boolean hasNext;
//
//	public FastScanner(InputStream is) throws IOException {
//		br = new BufferedReader(new InputStreamReader(is));
//		hasNext = true;
//	}
//
//	public String nextToken() {
//		while (st == null || !st.hasMoreTokens()) {
//			try {
//				st = new StringTokenizer(br.readLine());
//			} catch (Exception e) {
//				hasNext = false;
//				return "##";
//			}
//		}
//		return st.nextToken();
//	}
//	
//	String next = null;
//	public boolean hasNext(){
//		next = nextToken();
//		return hasNext;
//	}
//
//	public int nextInt() {
//		if (next == null){
//			hasNext();
//		}
//		String more = next;
//		next = null;
//		return Integer.parseInt(more);
//	}
//
//	public long nextLong() {
//		if (next == null){
//			hasNext();
//		}
//		String more = next;
//		next = null;
//		return Long.parseLong(more);
//	}
//
//	public double nextDouble() {
//		if (next == null){
//			hasNext();
//		}
//		String more = next;
//		next = null;
//		return Double.parseDouble(more);
//	}
//	
//	public String nextString(){
//		if (next == null){
//			hasNext();
//		}
//		String more = next;
//		next = null;
//		return more;
//	}
//	
//	public char nextChar(){
//		if (next == null){
//			hasNext();
//		}
//		String more = next;
//		next = null;
//		return more.charAt(0);
//	}
//}
//}

