package com.daimens.algorithm.june;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author DemonSong
 * 
 *         189.Convenient Location
 * 
 *         便利な町
 *         来春卒業するAさんは、就職を機に引越しをすることにしました。就職する会社は、オフィスがいくつかの町にあって、日によって出勤するオフィスが違います。そこでAさんは,どこのオフィスに
 *         行くにも時間の短い町に住もうと考えました。
 * 
 *         そこであなたは、Aさんを助けるため、住むのに一番便利な町を探すことになりました。
 * 
 * 
 * 
 *         町には 0
 *         から始まる番号が振られており、町と町の間には道があります。それぞれの道に対して通勤時間が決まっています。Aさんがある町に住んでいる場合に、自分の町のオフィスまでの通勤時間は
 *         0
 *         とします。このときに全ての町までの通勤時間の総和を考えます。例えば、町と道の配置が上の図のようになっていて、Aさんが町1に住んだ場合には、それぞれの町までの通勤時間は
 * 
 *         町 0 まで 80 町 1 まで 0 町 2 まで 20 町 3 まで 70 町 4 まで 90
 * 
 *         となり、総和は 260 となります。
 * 
 *         道の数と、全ての道の情報を入力とし、それぞれの町に住んだ場合の通勤時間の総和を計算し、それが最小となる町の番号と、そのときの通勤時間の総和を出力するプログラムを作成してください。ただし、通勤時間の総和が最小となる町が複数ある場合は、一番小さい町の番号及びその時の通勤時間の総和を出力してください。町の総数は
 *         10 以下、道の総数は 45
 *         以下とし、全ての道は双方向に移動でき、通勤時間は方向によって変わらないものとします。また、どの町からでもその他全ての町への
 *         経路があるものとします。
 * 
 *         Input 複数のデータセットの並びが入力として与えられます。入力の終わりはゼロひとつの行で示されます。
 *         各データセットは以下の形式で与えられます。
 * 
 *         n a1 b1 c1 a2 b2 c2 : an bn cn １行目に道の数 n (1 ≤ n ≤ 45) が与えられます。続く n 行に
 *         i 番目の道の情報が与えられます。 ai, bi (0 ≤ ai, bi ≤ 9) は i 番目の道がつないでいる町の番号、ci (0 ≤
 *         ci ≤ 100) はその道の通勤時間を表します。
 * 
 *         Output データセット毎に、通勤時間の総和が最小になる町の番号及びその時の通勤時間の総和を空白区切りで１行に出力します。
 * 
 *         Sample Input 6 0 1 80 1 2 20 0 2 60 2 3 50 3 4 60 1 4 90 2 0 1 1 1 2
 *         1 0 Output for the Sample Input 2 240 1 2
 *
 */
public class SolutionDay15_A0189 {
	
	static final int INF = 1 << 29;
	static final int MAX = 11;
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		while (true){
			int N = in.nextInt();
			if (N == 0) break;
			int[][] graph = new int[MAX][MAX];
			int n = 0;
			for (int i = 0; i < N; ++i){
				int u = in.nextInt();
				int v = in.nextInt();
				int d = in.nextInt();
				graph[u][v] = d;
				graph[v][u] = d;
				n = Math.max(n, Math.max(u,v) + 1);
			}
			solve(graph,n);
		}
	}
	
	private static void solve(int[][] graph, int n){
		int[][] distance = new int[n][n];
		
		for (int[] d : distance) Arrays.fill(d, INF);
		
		for (int i = 0; i < n; ++i){
			for (int j = 0; j < n; ++j){
				if (i == j) distance[i][i] = 0;
				if (graph[i][j] != 0){
					distance[i][j] = graph[i][j];
				}
			}
		}
		
		for (int i = 0; i < n; ++i){
			for (int j = 0; j < n; ++j){
				for (int k = 0; k < n; ++k){
					distance[j][k] = Math.min(distance[j][k], distance[j][i] + distance[i][k]);
				}
			}
		}
		
		int minSum = INF;
		int node = INF;
		for (int i = 0; i < n; ++i){
			int sum = 0;
			for (int j = 0; j < n; ++j){
				sum += distance[i][j];
			}
			if (sum < minSum){
				node = i;
				minSum = sum;
			}
		}
		
		System.out.println(node + " " + minSum);
	}

	static class Scanner {

		private BufferedReader br;
		private StringTokenizer tok;

		public Scanner(InputStream is) throws IOException {
			br = new BufferedReader(new InputStreamReader(is));
			getLine();
		}

		private void getLine() throws IOException {
			while (tok == null || !tok.hasMoreTokens()) {
				tok = new StringTokenizer(br.readLine());
			}
		}

		private boolean hasNext() {
			return tok.hasMoreTokens();
		}

		public String next() throws IOException {
			if (hasNext()) {
				return tok.nextToken();
			} else {
				getLine();
				return tok.nextToken();
			}
		}

		public int nextInt() throws IOException {
			if (hasNext()) {
				return Integer.parseInt(tok.nextToken());
			} else {
				getLine();
				return Integer.parseInt(tok.nextToken());
			}
		}

		public long nextLong() throws IOException {
			if (hasNext()) {
				return Long.parseLong(tok.nextToken());
			} else {
				getLine();
				return Long.parseLong(tok.nextToken());
			}
		}

		public double nextDouble() throws IOException {
			if (hasNext()) {
				return Double.parseDouble(tok.nextToken());
			} else {
				getLine();
				return Double.parseDouble(tok.nextToken());
			}
		}
	}
}
