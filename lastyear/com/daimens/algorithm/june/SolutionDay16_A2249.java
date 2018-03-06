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
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author DemonSong
 * 
 *         2249.Road Construction
 * 
 *         Problem H: Road Construction King Mercer is the king of ACM kingdom.
 *         There are one capital and some cities in his kingdom. Amazingly,
 *         there are no roads in the kingdom now. Recently, he planned to
 *         construct roads between the capital and the cities, but it turned out
 *         that the construction cost of his plan is much higher than expected.
 * 
 *         In order to reduce the cost, he has decided to create a new
 *         construction plan by removing some roads from the original plan.
 *         However, he believes that a new plan should satisfy the following
 *         conditions:
 * 
 *         For every pair of cities, there is a route (a set of roads)
 *         connecting them. The minimum distance between the capital and each
 *         city does not change from his original plan. Many plans may meet the
 *         conditions above, but King Mercer wants to know the plan with minimum
 *         cost. Your task is to write a program which reads his original plan
 *         and calculates the cost of a new plan with the minimum cost.
 * 
 *         Input The input consists of several datasets. Each dataset is
 *         formatted as follows.
 * 
 *         N M u1 v1 d1 c1 . . . uM vM dM cM The first line of each dataset
 *         begins with two integers, N and M (1 ≤ N ≤ 10000, 0 ≤ M ≤ 20000). N
 *         and M indicate the number of cities and the number of roads in the
 *         original plan, respectively.
 * 
 *         The following M lines describe the road information in the original
 *         plan. The i-th line contains four integers, ui, vi, di and ci (1 ≤
 *         ui, vi ≤ N , ui ≠ vi , 1 ≤ di ≤ 1000, 1 ≤ ci ≤ 1000). ui , vi, di and
 *         ci indicate that there is a road which connects ui-th city and vi-th
 *         city, whose length is di and whose cost needed for construction is
 *         ci.
 * 
 *         Each road is bidirectional. No two roads connect the same pair of
 *         cities. The 1-st city is the capital in the kingdom.
 * 
 *         The end of the input is indicated by a line containing two zeros
 *         separated by a space. You should not process the line as a dataset.
 * 
 *         Output For each dataset, print the minimum cost of a plan which
 *         satisfies the conditions in a line.
 * 
 *         Sample Input 3 3 1 2 1 2 2 3 2 1 3 1 3 2 5 5 1 2 2 2 2 3 1 1 1 4 1 1
 *         4 5 1 1 5 3 1 1 5 10 1 2 32 10 1 3 43 43 1 4 12 52 1 5 84 23 2 3 58
 *         42 2 4 86 99 2 5 57 83 3 4 11 32 3 5 75 21 4 5 23 43 5 10 1 2 1 53 1
 *         3 1 65 1 4 1 24 1 5 1 76 2 3 1 19 2 4 1 46 2 5 1 25 3 4 1 13 3 5 1 65
 *         4 5 1 34 0 0 Output for the Sample Input 3 5 137 218
 *
 */
public class SolutionDay16_A2249 {
	
	static class Edge{
		int from;
		int to;
		int dist;
		int cost;
		
		@Override
		public String toString() {
			return "{"+from + "->" + to + ", dist: " + dist + "}";
		}
	}
	
	static int INF = 1 << 29;
	static List<Edge>[] graph;
	static int[] dist;
	static int N;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		while (true){
			N = in.nextInt();
			int M = in.nextInt();
			if (N == 0 && M == 0) break;
			graph = new ArrayList[N];
			for (int i = 0; i < N; ++i) graph[i] = new ArrayList<>();
			
			for (int i = 0; i < M; ++i){
				int f = in.nextInt();
				int t = in.nextInt();
				f--;
				t--;
				int dist = in.nextInt();
				int cost = in.nextInt();
				
				Edge edge = new Edge();
				edge.from = f;
				edge.to = t;
				edge.cost = cost;
				edge.dist = dist;
				graph[edge.from].add(edge);
				
				edge = new Edge();
				edge.from = t;
				edge.to = f;
				edge.cost = cost;
				edge.dist = dist;
				graph[edge.from].add(edge);
			}
			
			dijkstra(0);
			long sum = 0;
			for (int i = 1; i < N; ++i){
				int min = INF;
				for (Edge e : graph[i]){
					if (dist[i] == dist[e.to] + e.dist && e.cost < min){
						min = e.cost;
					}
				}
				sum += min;
			}
			System.out.println(sum);
		}
	}
	
	static class Node implements Comparable<Node>{
		int id;
		int dist;
		public Node(int id, int dist){
			this.id = id;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.dist - o.dist;
		}
	}
	
	private static void dijkstra(int s){
		dist = new int[N];
		Arrays.fill(dist,INF);
		dist[s] = 0;
		Queue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(s,dist[s]));
		while (!pq.isEmpty()){
			int v = pq.poll().id;
			for (Edge e : graph[v]){
				if (e.dist + dist[e.from] < dist[e.to]){
					dist[e.to] = e.dist + dist[e.from];
					pq.offer(new Node(e.to, dist[e.to]));
				}
			}
		}
	}
	
//	private static void dijkstra(int s){
//		dist = new int[N];
//		Arrays.fill(dist,INF);
//		dist[s] = 0;
//		IndexMinPQ<Integer> pq = new IndexMinPQ<>(N);
//		pq.insert(s, dist[s]);
//		while (!pq.isEmpty()){
//			int v = pq.delMin();
//			for (Edge e : graph[v]){
//				if (dist[e.from] + e.dist < dist[e.to]){
//					dist[e.to] = dist[e.from] + e.dist;
//					if (pq.contains(e.to)) pq.decreaseKey(e.to, dist[e.to]);
//					else pq.insert(e.to, dist[e.to]);
//				}
//			}
//		}
//	}
	
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
	    @SuppressWarnings("unchecked")
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
