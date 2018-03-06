package com.daimens.algorithm.june;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * 
 * @author DemonSong
 * 
 *         3268.Silver Cow Party
 * 
 *         Description
 * 
 *         One cow from each of N farms (1 ≤ N ≤ 1000) conveniently numbered
 *         1..N is going to attend the big cow party to be held at farm #X (1 ≤
 *         X ≤ N). A total of M (1 ≤ M ≤ 100,000) unidirectional (one-way roads
 *         connects pairs of farms; road i requires Ti (1 ≤ Ti ≤ 100) units of
 *         time to traverse.
 * 
 *         Each cow must walk to the party and, when the party is over, return
 *         to her farm. Each cow is lazy and thus picks an optimal route with
 *         the shortest time. A cow's return route might be different from her
 *         original route to the party since roads are one-way.
 * 
 *         Of all the cows, what is the longest amount of time a cow must spend
 *         walking to the party and back?
 * 
 *         Input
 * 
 *         Line 1: Three space-separated integers, respectively: N, M, and X
 *         Lines 2..M+1: Line i+1 describes road i with three space-separated
 *         integers: Ai, Bi, and Ti. The described road runs from farm Ai to
 *         farm Bi, requiring Ti time units to traverse. Output
 * 
 *         Line 1: One integer: the maximum of time any one cow must walk.
 *         Sample Input
 * 
 *         4 8 2 1 2 4 1 3 2 1 4 7 2 1 1 2 3 5 3 1 2 3 4 4 4 2 3 Sample Output
 * 
 *         10 Hint
 * 
 *         Cow 4 proceeds directly to the party (3 units) and returns via farms
 *         1 and 3 (7 units), for a total of 10 time units.
 *
 */
public class SolutionDay16_P3268 {
	
//	static int INF = 1 << 29;
//	static int[][] g;
//	static int[][] d;
//	static int N;
//	
//	public static void main(String[] args) throws IOException {
//		Scanner in = new Scanner(System.in);
//		N = in.nextInt();
//		
//		int M = in.nextInt();
//		int X = in.nextInt();
//		g = new int[N][N];
//		d = new int[N][N];
//		for (int i = 0; i < N; ++i) Arrays.fill(g[i], INF);
//		for (int i = 0; i < N; ++i) g[i][i] = 0;
//		
//		for (int i = 0; i < M; ++i){
//			int f = in.nextInt();
//			int t = in.nextInt();
//			f--;
//			t--;
//			int c = in.nextInt();
//			g[f][t] = c;
//		}
//		
//		for (int i = 0; i < N; ++i){
//			//initial
//			Arrays.fill(d[i], INF);
//			d[i][i] = 0;
//			dijkstra(i);
//		}
//		
//		int max = 0;
//		for (int i = 0; i < N; ++i){
//			if (i == X) continue;
//			max = Math.max(max, d[i][X] + d[X][i]);
//		}
//		System.out.println(max);
//	}
//	
//	private static void dijkstra(int s){
//		int V = N;
//		boolean[] used = new boolean[V];
//		while (true){
//			int v = -1;
//			for (int i = 0; i < V; ++i){
//				if (!used[i] && (v == -1 || d[s][i] < d[s][v])) v = i;
//			}
//			if (v == -1) break;
//			used[v] = true;
//			for (int i = 0; i < V; ++i){
//				d[s][i] = Math.min(d[s][i],d[s][v] + g[v][i]);
//			}
//		}
//	}
//	
//	private static void warshallFloyd(){
//		for (int i = 0; i < N; ++i){
//			for (int j = 0; j < N; ++j){
//				for (int k = 0; k < N; ++k){
//					g[j][k] = Math.min(g[j][k], g[j][i] + g[i][k]);
//				}
//			}
//		}
//	}
	
	static int INF = 1 << 29;
	static int[][] d;
	static List<Edge>[] graph;
	static int N;
	
	static class Edge{
		int from;
		int to;
		int cost;
		
		@Override
		public String toString() {
			return from + "->" + to + " ,cost: " + cost;
		}
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		
		int M = in.nextInt();
		int X = in.nextInt();
		graph = new ArrayList[N];
		
		d = new int[N][N];
		for (int i = 0; i < N; ++i) graph[i] = new ArrayList<>();
		
		for (int i = 0; i < M; ++i){
			int f = in.nextInt();
			int t = in.nextInt();
			f--;
			t--;
			int c = in.nextInt();
			Edge e = new Edge();
			e.from = f;
			e.to = t;
			e.cost = c;
			graph[e.from].add(e);
		}
		
		for (int i = 0; i < N; ++i){
			//initial
			Arrays.fill(d[i], INF);
			d[i][i] = 0;
			dijkstra(i);
		}
		
		int max = 0;
		for (int i = 0; i < N; ++i){
			if (i == X) continue;
			max = Math.max(max, d[i][X] + d[X][i]);
		}
		System.out.println(max);
	}
	
	private static void dijkstra(int s){
		int V = N;
		IndexMinPQ<Integer> pq = new IndexMinPQ<>(V);
		pq.insert(s, d[s][s]);
		while (!pq.isEmpty()){
			int v = pq.delMin();
			for (Edge e : graph[v]){
				int from = e.from;
				int to = e.to;
				int cost = e.cost;
				if (d[s][from] + cost < d[s][to]){
					d[s][to] = d[s][from] + cost;
					if (pq.contains(to)) pq.decreaseKey(to, d[s][to]);
					else pq.insert(to, d[s][to]);
				}
			}
		}
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
	
	static class IndexMinPQ<Key extends Comparable<Key>> implements Iterable<Integer> {
	    private int maxN;        // maximum number of elements on PQ
	    private int n;           // number of elements on PQ
	    private int[] pq;        // binary heap using 1-based indexing
	    private int[] qp;        // inverse of pq - qp[pq[i]] = pq[qp[i]] = i
	    private Key[] keys;      // keys[i] = priority of i

	    /**
	     * Initializes an empty indexed priority queue with indices between {@code 0}
	     * and {@code maxN - 1}.
	     * @param  maxN the keys on this priority queue are index from {@code 0}
	     *         {@code maxN - 1}
	     * @throws IllegalArgumentException if {@code maxN < 0}
	     */
	    public IndexMinPQ(int maxN) {
	        if (maxN < 0) throw new IllegalArgumentException();
	        this.maxN = maxN;
	        n = 0;
	        keys = (Key[]) new Comparable[maxN + 1];    // make this of length maxN??
	        pq   = new int[maxN + 1];
	        qp   = new int[maxN + 1];                   // make this of length maxN??
	        for (int i = 0; i <= maxN; i++)
	            qp[i] = -1;
	    }

	    /**
	     * Returns true if this priority queue is empty.
	     *
	     * @return {@code true} if this priority queue is empty;
	     *         {@code false} otherwise
	     */
	    public boolean isEmpty() {
	        return n == 0;
	    }

	    /**
	     * Is {@code i} an index on this priority queue?
	     *
	     * @param  i an index
	     * @return {@code true} if {@code i} is an index on this priority queue;
	     *         {@code false} otherwise
	     * @throws IndexOutOfBoundsException unless {@code 0 <= i < maxN}
	     */
	    public boolean contains(int i) {
	        if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
	        return qp[i] != -1;
	    }

	    /**
	     * Returns the number of keys on this priority queue.
	     *
	     * @return the number of keys on this priority queue
	     */
	    public int size() {
	        return n;
	    }

	    /**
	     * Associates key with index {@code i}.
	     *
	     * @param  i an index
	     * @param  key the key to associate with index {@code i}
	     * @throws IndexOutOfBoundsException unless {@code 0 <= i < maxN}
	     * @throws IllegalArgumentException if there already is an item associated
	     *         with index {@code i}
	     */
	    public void insert(int i, Key key) {
	        if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
	        if (contains(i)) throw new IllegalArgumentException("index is already in the priority queue");
	        n++;
	        qp[i] = n;
	        pq[n] = i;
	        keys[i] = key;
	        swim(n);
	    }

	    /**
	     * Returns an index associated with a minimum key.
	     *
	     * @return an index associated with a minimum key
	     * @throws NoSuchElementException if this priority queue is empty
	     */
	    public int minIndex() {
	        if (n == 0) throw new NoSuchElementException("Priority queue underflow");
	        return pq[1];
	    }

	    /**
	     * Returns a minimum key.
	     *
	     * @return a minimum key
	     * @throws NoSuchElementException if this priority queue is empty
	     */
	    public Key minKey() {
	        if (n == 0) throw new NoSuchElementException("Priority queue underflow");
	        return keys[pq[1]];
	    }

	    /**
	     * Removes a minimum key and returns its associated index.
	     * @return an index associated with a minimum key
	     * @throws NoSuchElementException if this priority queue is empty
	     */
	    public int delMin() {
	        if (n == 0) throw new NoSuchElementException("Priority queue underflow");
	        int min = pq[1];
	        exch(1, n--);
	        sink(1);
	        assert min == pq[n+1];
	        qp[min] = -1;        // delete
	        keys[min] = null;    // to help with garbage collection
	        pq[n+1] = -1;        // not needed
	        return min;
	    }

	    /**
	     * Returns the key associated with index {@code i}.
	     *
	     * @param  i the index of the key to return
	     * @return the key associated with index {@code i}
	     * @throws IndexOutOfBoundsException unless {@code 0 <= i < maxN}
	     * @throws NoSuchElementException no key is associated with index {@code i}
	     */
	    public Key keyOf(int i) {
	        if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
	        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
	        else return keys[i];
	    }

	    /**
	     * Change the key associated with index {@code i} to the specified value.
	     *
	     * @param  i the index of the key to change
	     * @param  key change the key associated with index {@code i} to this key
	     * @throws IndexOutOfBoundsException unless {@code 0 <= i < maxN}
	     * @throws NoSuchElementException no key is associated with index {@code i}
	     */
	    public void changeKey(int i, Key key) {
	        if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
	        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
	        keys[i] = key;
	        swim(qp[i]);
	        sink(qp[i]);
	    }

	    /**
	     * Change the key associated with index {@code i} to the specified value.
	     *
	     * @param  i the index of the key to change
	     * @param  key change the key associated with index {@code i} to this key
	     * @throws IndexOutOfBoundsException unless {@code 0 <= i < maxN}
	     * @deprecated Replaced by {@code changeKey(int, Key)}.
	     */
	    @Deprecated
	    public void change(int i, Key key) {
	        changeKey(i, key);
	    }

	    /**
	     * Decrease the key associated with index {@code i} to the specified value.
	     *
	     * @param  i the index of the key to decrease
	     * @param  key decrease the key associated with index {@code i} to this key
	     * @throws IndexOutOfBoundsException unless {@code 0 <= i < maxN}
	     * @throws IllegalArgumentException if {@code key >= keyOf(i)}
	     * @throws NoSuchElementException no key is associated with index {@code i}
	     */
	    public void decreaseKey(int i, Key key) {
	        if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
	        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
	        if (keys[i].compareTo(key) <= 0)
	            throw new IllegalArgumentException("Calling decreaseKey() with given argument would not strictly decrease the key");
	        keys[i] = key;
	        swim(qp[i]);
	    }

	    /**
	     * Increase the key associated with index {@code i} to the specified value.
	     *
	     * @param  i the index of the key to increase
	     * @param  key increase the key associated with index {@code i} to this key
	     * @throws IndexOutOfBoundsException unless {@code 0 <= i < maxN}
	     * @throws IllegalArgumentException if {@code key <= keyOf(i)}
	     * @throws NoSuchElementException no key is associated with index {@code i}
	     */
	    public void increaseKey(int i, Key key) {
	        if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
	        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
	        if (keys[i].compareTo(key) >= 0)
	            throw new IllegalArgumentException("Calling increaseKey() with given argument would not strictly increase the key");
	        keys[i] = key;
	        sink(qp[i]);
	    }

	    /**
	     * Remove the key associated with index {@code i}.
	     *
	     * @param  i the index of the key to remove
	     * @throws IndexOutOfBoundsException unless {@code 0 <= i < maxN}
	     * @throws NoSuchElementException no key is associated with index {@code i}
	     */
	    public void delete(int i) {
	        if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
	        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
	        int index = qp[i];
	        exch(index, n--);
	        swim(index);
	        sink(index);
	        keys[i] = null;
	        qp[i] = -1;
	    }


	   /***************************************************************************
	    * General helper functions.
	    ***************************************************************************/
	    private boolean greater(int i, int j) {
	        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
	    }

	    private void exch(int i, int j) {
	        int swap = pq[i];
	        pq[i] = pq[j];
	        pq[j] = swap;
	        qp[pq[i]] = i;
	        qp[pq[j]] = j;
	    }


	   /***************************************************************************
	    * Heap helper functions.
	    ***************************************************************************/
	    private void swim(int k) {
	        while (k > 1 && greater(k/2, k)) {
	            exch(k, k/2);
	            k = k/2;
	        }
	    }

	    private void sink(int k) {
	        while (2*k <= n) {
	            int j = 2*k;
	            if (j < n && greater(j, j+1)) j++;
	            if (!greater(k, j)) break;
	            exch(k, j);
	            k = j;
	        }
	    }


	   /***************************************************************************
	    * Iterators.
	    ***************************************************************************/

	    /**
	     * Returns an iterator that iterates over the keys on the
	     * priority queue in ascending order.
	     * The iterator doesn't implement {@code remove()} since it's optional.
	     *
	     * @return an iterator that iterates over the keys in ascending order
	     */
	    public Iterator<Integer> iterator() { return new HeapIterator(); }

	    private class HeapIterator implements Iterator<Integer> {
	        // create a new pq
	        private IndexMinPQ<Key> copy;

	        // add all elements to copy of heap
	        // takes linear time since already in heap order so no keys move
	        public HeapIterator() {
	            copy = new IndexMinPQ<Key>(pq.length - 1);
	            for (int i = 1; i <= n; i++)
	                copy.insert(pq[i], keys[pq[i]]);
	        }

	        public boolean hasNext()  { return !copy.isEmpty();                     }
	        public void remove()      { throw new UnsupportedOperationException();  }

	        public Integer next() {
	            if (!hasNext()) throw new NoSuchElementException();
	            return copy.delMin();
	        }
	    }
	}

}
