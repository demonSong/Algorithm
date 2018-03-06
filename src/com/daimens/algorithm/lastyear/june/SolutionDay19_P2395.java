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

public class SolutionDay19_P2395 {
	
	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int cost;

		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}

	static class Union {
		int[] id;
		int[] sz;

		public Union(int SIZE) {
			id = new int[SIZE];
			sz = new int[SIZE];
			for (int i = 0; i < SIZE; ++i) {
				id[i] = i;
				sz[i] = 1;
			}
		}

		public int find(int i) {
			while (i != id[i]) {
				i = id[i];
			}
			return i;
		}

		public boolean connect(int i, int j) {
			return find(i) == find(j);
		}

		public void union(int i, int j) {
			int p = find(i);
			int q = find(j);
			if (p == q)
				return;
			if (sz[p] < sz[q]) {
				id[p] = q;
				sz[q] += sz[p];
			} else {
				id[q] = p;
				sz[p] += sz[1];
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int M = in.nextInt();
		u = new Union(N);
		List<Edge>[] g = new ArrayList[N];
		for (int i = 0; i < N; ++i) g[i] = new ArrayList<Edge>();
		for (int i = 0; i < M; ++i){
			int from = in.nextInt();
			int to = in.nextInt();
			from--;
			to--;
			int cost = in.nextInt();
			g[from].add(new Edge(from, to, cost));
		}
		System.out.println(krusal(g));
	}
	
	static MinPQ<Edge> pq;
	static Union u;
	private static int krusal(List<Edge>[] graph){
		int V = graph.length;
		pq = new MinPQ<Edge>();
		for (int i = 0; i < V; ++i){
			for (Edge e : graph[i]){
				pq.insert(e);
			}
		}
		long res = 0;
		while (!pq.isEmpty()){
			Edge e = pq.delMin();
			int from = e.from, to = e.to;
			if (u.connect(from, to)) continue;
			res = Math.max(res, e.cost);
			u.union(from,to);
		}
		return (int)res;
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
	
	static class MinPQ<Key> implements Iterable<Key> {
	    private Key[] pq;                    // store items at indices 1 to n
	    private int n;                       // number of items on priority queue
	    private Comparator<Key> comparator;  // optional comparator

	    /**
	     * Initializes an empty priority queue with the given initial capacity.
	     *
	     * @param  initCapacity the initial capacity of this priority queue
	     */
	    public MinPQ(int initCapacity) {
	        pq = (Key[]) new Object[initCapacity + 1];
	        n = 0;
	    }

	    /**
	     * Initializes an empty priority queue.
	     */
	    public MinPQ() {
	        this(1);
	    }

	    /**
	     * Initializes an empty priority queue with the given initial capacity,
	     * using the given comparator.
	     *
	     * @param  initCapacity the initial capacity of this priority queue
	     * @param  comparator the order to use when comparing keys
	     */
	    public MinPQ(int initCapacity, Comparator<Key> comparator) {
	        this.comparator = comparator;
	        pq = (Key[]) new Object[initCapacity + 1];
	        n = 0;
	    }

	    /**
	     * Initializes an empty priority queue using the given comparator.
	     *
	     * @param  comparator the order to use when comparing keys
	     */
	    public MinPQ(Comparator<Key> comparator) {
	        this(1, comparator);
	    }

	    /**
	     * Initializes a priority queue from the array of keys.
	     * <p>
	     * Takes time proportional to the number of keys, using sink-based heap construction.
	     *
	     * @param  keys the array of keys
	     */
	    public MinPQ(Key[] keys) {
	        n = keys.length;
	        pq = (Key[]) new Object[keys.length + 1];
	        for (int i = 0; i < n; i++)
	            pq[i+1] = keys[i];
	        for (int k = n/2; k >= 1; k--)
	            sink(k);
	        assert isMinHeap();
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
	     * Returns a smallest key on this priority queue.
	     *
	     * @return a smallest key on this priority queue
	     * @throws NoSuchElementException if this priority queue is empty
	     */
	    public Key min() {
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
	     * @param  x the key to add to this priority queue
	     */
	    public void insert(Key x) {
	        // double size of array if necessary
	        if (n == pq.length - 1) resize(2 * pq.length);

	        // add x, and percolate it up to maintain heap invariant
	        pq[++n] = x;
	        swim(n);
	        assert isMinHeap();
	    }

	    /**
	     * Removes and returns a smallest key on this priority queue.
	     *
	     * @return a smallest key on this priority queue
	     * @throws NoSuchElementException if this priority queue is empty
	     */
	    public Key delMin() {
	        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
	        exch(1, n);
	        Key min = pq[n--];
	        sink(1);
	        pq[n+1] = null;         // avoid loitering and help with garbage collection
	        if ((n > 0) && (n == (pq.length - 1) / 4)) resize(pq.length  / 2);
	        assert isMinHeap();
	        return min;
	    }


	   /***************************************************************************
	    * Helper functions to restore the heap invariant.
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
	    * Helper functions for compares and swaps.
	    ***************************************************************************/
	    private boolean greater(int i, int j) {
	        if (comparator == null) {
	            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
	        }
	        else {
	            return comparator.compare(pq[i], pq[j]) > 0;
	        }
	    }

	    private void exch(int i, int j) {
	        Key swap = pq[i];
	        pq[i] = pq[j];
	        pq[j] = swap;
	    }

	    // is pq[1..N] a min heap?
	    private boolean isMinHeap() {
	        return isMinHeap(1);
	    }

	    // is subtree of pq[1..n] rooted at k a min heap?
	    private boolean isMinHeap(int k) {
	        if (k > n) return true;
	        int left = 2*k;
	        int right = 2*k + 1;
	        if (left  <= n && greater(k, left))  return false;
	        if (right <= n && greater(k, right)) return false;
	        return isMinHeap(left) && isMinHeap(right);
	    }


	    /**
	     * Returns an iterator that iterates over the keys on this priority queue
	     * in ascending order.
	     * <p>
	     * The iterator doesn't implement {@code remove()} since it's optional.
	     *
	     * @return an iterator that iterates over the keys in ascending order
	     */
	    public Iterator<Key> iterator() { return new HeapIterator(); }

	    private class HeapIterator implements Iterator<Key> {
	        // create a new pq
	        private MinPQ<Key> copy;

	        // add all items to copy of heap
	        // takes linear time since already in heap order so no keys move
	        public HeapIterator() {
	            if (comparator == null) copy = new MinPQ<Key>(size());
	            else                    copy = new MinPQ<Key>(size(), comparator);
	            for (int i = 1; i <= n; i++)
	                copy.insert(pq[i]);
	        }

	        public boolean hasNext()  { return !copy.isEmpty();                     }
	        public void remove()      { throw new UnsupportedOperationException();  }

	        public Key next() {
	            if (!hasNext()) throw new NoSuchElementException();
	            return copy.delMin();
	        }
	    }
	}
}
