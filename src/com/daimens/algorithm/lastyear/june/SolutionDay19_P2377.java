package com.daimens.algorithm.june;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * 
 * @author DemonSong
 * 
 *         2377.Bad Cowtractors
 * 
 *         Description
 * 
 *         Bessie has been hired to build a cheap internet network among Farmer
 *         John's N (2 <= N <= 1,000) barns that are conveniently numbered 1..N.
 *         FJ has already done some surveying, and found M (1 <= M <= 20,000)
 *         possible connection routes between pairs of barns. Each possible
 *         connection route has an associated cost C (1 <= C <= 100,000). Farmer
 *         John wants to spend the least amount on connecting the network; he
 *         doesn't even want to pay Bessie.
 * 
 *         Realizing Farmer John will not pay her, Bessie decides to do the
 *         worst job possible. She must decide on a set of connections to
 *         install so that (i) the total cost of these connections is as large
 *         as possible, (ii) all the barns are connected together (so that it is
 *         possible to reach any barn from any other barn via a path of
 *         installed connections), and (iii) so that there are no cycles among
 *         the connections (which Farmer John would easily be able to detect).
 *         Conditions (ii) and (iii) ensure that the final set of connections
 *         will look like a "tree". Input
 * 
 *         Line 1: Two space-separated integers: N and M
 * 
 *         Lines 2..M+1: Each line contains three space-separated integers A, B,
 *         and C that describe a connection route between barns A and B of cost
 *         C. Output
 * 
 *         Line 1: A single integer, containing the price of the most expensive
 *         tree connecting all the barns. If it is not possible to connect all
 *         the barns, output -1. Sample Input
 * 
 *         5 8 1 2 3 1 3 7 2 3 10 2 4 4 2 5 8 3 4 6 3 5 2 4 5 17 Sample Output
 * 
 *         42
 * 
 *
 */
public class SolutionDay19_P2377 {
	
	static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int cost;
		
		public Edge(int from, int to, int cost){
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int M = in.nextInt();
		List<Edge>[] g = new ArrayList[N];
		for (int i = 0; i < N; ++i) g[i] = new ArrayList<Edge>();
		
		for (int i = 0; i < M; ++i){
			int from = in.nextInt();
			int to = in.nextInt();
			from--;
			to--;
			int cost = in.nextInt();
			g[from].add(new Edge(from,to,cost));
			g[to].add(new Edge(to,from,cost));
		}
		
		System.out.println(prim(g));
	}
	
	static boolean[] marked;
	static MaxPQ<Edge> pq;
	private static int prim(List<Edge>[] g){
		int V = g.length;
		marked = new boolean[V];
		pq = new MaxPQ<Edge>();
		visit(g, 0);
		long sum = 0;
		int cnt = 1;
		while (!pq.isEmpty()){
			Edge e = pq.delMax();
			if (marked[e.to]) continue;
			sum += e.cost;
			visit(g, e.to);
			cnt++;
		}
		return cnt == V ? (int)sum : -1;
	}
	
	private static void visit(List<Edge>[] g, int v){
		marked[v] = true;
		for (Edge e : g[v]){
			if (!marked[e.to]) pq.insert(e);
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
	
	static class MaxPQ<Key> implements Iterable<Key> {
	    private Key[] pq;                    // store items at indices 1 to n
	    private int n;                       // number of items on priority queue
	    private Comparator<Key> comparator;  // optional Comparator

	    /**
	     * Initializes an empty priority queue with the given initial capacity.
	     *
	     * @param  initCapacity the initial capacity of this priority queue
	     */
	    public MaxPQ(int initCapacity) {
	        pq = (Key[]) new Object[initCapacity + 1];
	        n = 0;
	    }

	    /**
	     * Initializes an empty priority queue.
	     */
	    public MaxPQ() {
	        this(1);
	    }

	    /**
	     * Initializes an empty priority queue with the given initial capacity,
	     * using the given comparator.
	     *
	     * @param  initCapacity the initial capacity of this priority queue
	     * @param  comparator the order in which to compare the keys
	     */
	    public MaxPQ(int initCapacity, Comparator<Key> comparator) {
	        this.comparator = comparator;
	        pq = (Key[]) new Object[initCapacity + 1];
	        n = 0;
	    }

	    /**
	     * Initializes an empty priority queue using the given comparator.
	     *
	     * @param  comparator the order in which to compare the keys
	     */
	    public MaxPQ(Comparator<Key> comparator) {
	        this(1, comparator);
	    }

	    /**
	     * Initializes a priority queue from the array of keys.
	     * Takes time proportional to the number of keys, using sink-based heap construction.
	     *
	     * @param  keys the array of keys
	     */
	    public MaxPQ(Key[] keys) {
	        n = keys.length;
	        pq = (Key[]) new Object[keys.length + 1]; 
	        for (int i = 0; i < n; i++)
	            pq[i+1] = keys[i];
	        for (int k = n/2; k >= 1; k--)
	            sink(k);
	        assert isMaxHeap();
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
	     * Returns the number of keys on this priority queue.
	     *
	     * @return the number of keys on this priority queue
	     */
	    public int size() {
	        return n;
	    }

	    /**
	     * Returns a largest key on this priority queue.
	     *
	     * @return a largest key on this priority queue
	     * @throws NoSuchElementException if this priority queue is empty
	     */
	    public Key max() {
	        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
	        return pq[1];
	    }

	    // helper function to double the size of the heap array
	    private void resize(int capacity) {
	        assert capacity > n;
	        Key[] temp = (Key[]) new Object[capacity];
	        for (int i = 1; i <= n; i++) {
	            temp[i] = pq[i];
	        }
	        pq = temp;
	    }


	    /**
	     * Adds a new key to this priority queue.
	     *
	     * @param  x the new key to add to this priority queue
	     */
	    public void insert(Key x) {

	        // double size of array if necessary
	        if (n >= pq.length - 1) resize(2 * pq.length);

	        // add x, and percolate it up to maintain heap invariant
	        pq[++n] = x;
	        swim(n);
	        assert isMaxHeap();
	    }

	    /**
	     * Removes and returns a largest key on this priority queue.
	     *
	     * @return a largest key on this priority queue
	     * @throws NoSuchElementException if this priority queue is empty
	     */
	    public Key delMax() {
	        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
	        Key max = pq[1];
	        exch(1, n--);
	        sink(1);
	        pq[n+1] = null;     // to avoid loiterig and help with garbage collection
	        if ((n > 0) && (n == (pq.length - 1) / 4)) resize(pq.length / 2);
	        assert isMaxHeap();
	        return max;
	    }


	   /***************************************************************************
	    * Helper functions to restore the heap invariant.
	    ***************************************************************************/

	    private void swim(int k) {
	        while (k > 1 && less(k/2, k)) {
	            exch(k, k/2);
	            k = k/2;
	        }
	    }

	    private void sink(int k) {
	        while (2*k <= n) {
	            int j = 2*k;
	            if (j < n && less(j, j+1)) j++;
	            if (!less(k, j)) break;
	            exch(k, j);
	            k = j;
	        }
	    }

	   /***************************************************************************
	    * Helper functions for compares and swaps.
	    ***************************************************************************/
	    private boolean less(int i, int j) {
	        if (comparator == null) {
	            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
	        }
	        else {
	            return comparator.compare(pq[i], pq[j]) < 0;
	        }
	    }

	    private void exch(int i, int j) {
	        Key swap = pq[i];
	        pq[i] = pq[j];
	        pq[j] = swap;
	    }

	    // is pq[1..N] a max heap?
	    private boolean isMaxHeap() {
	        return isMaxHeap(1);
	    }

	    // is subtree of pq[1..n] rooted at k a max heap?
	    private boolean isMaxHeap(int k) {
	        if (k > n) return true;
	        int left = 2*k;
	        int right = 2*k + 1;
	        if (left  <= n && less(k, left))  return false;
	        if (right <= n && less(k, right)) return false;
	        return isMaxHeap(left) && isMaxHeap(right);
	    }


	   /***************************************************************************
	    * Iterator.
	    ***************************************************************************/

	    /**
	     * Returns an iterator that iterates over the keys on this priority queue
	     * in descending order.
	     * The iterator doesn't implement {@code remove()} since it's optional.
	     *
	     * @return an iterator that iterates over the keys in descending order
	     */
	    public Iterator<Key> iterator() {
	        return new HeapIterator();
	    }

	    private class HeapIterator implements Iterator<Key> {

	        // create a new pq
	        private MaxPQ<Key> copy;

	        // add all items to copy of heap
	        // takes linear time since already in heap order so no keys move
	        public HeapIterator() {
	            if (comparator == null) copy = new MaxPQ<Key>(size());
	            else                    copy = new MaxPQ<Key>(size(), comparator);
	            for (int i = 1; i <= n; i++)
	                copy.insert(pq[i]);
	        }

	        public boolean hasNext()  { return !copy.isEmpty();                     }
	        public void remove()      { throw new UnsupportedOperationException();  }

	        public Key next() {
	            if (!hasNext()) throw new NoSuchElementException();
	            return copy.delMax();
	        }
	    }
	}
}
