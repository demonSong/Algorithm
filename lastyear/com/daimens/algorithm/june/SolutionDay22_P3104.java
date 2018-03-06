package com.daimens.algorithm.june;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * 
 * @author DemonSong
 * 
 *         3104.Drying
 * 
 *         Description
 * 
 *         It is very hard to wash and especially to dry clothes in winter. But
 *         Jane is a very smart girl. She is not afraid of this boring process.
 *         Jane has decided to use a radiator to make drying faster. But the
 *         radiator is small, so it can hold only one thing at a time.
 * 
 *         Jane wants to perform drying in the minimal possible time. She asked
 *         you to write a program that will calculate the minimal time for a
 *         given set of clothes.
 * 
 *         There are n clothes Jane has just washed. Each of them took ai water
 *         during washing. Every minute the amount of water contained in each
 *         thing decreases by one (of course, only if the thing is not
 *         completely dry yet). When amount of water contained becomes zero the
 *         cloth becomes dry and is ready to be packed.
 * 
 *         Every minute Jane can select one thing to dry on the radiator. The
 *         radiator is very hot, so the amount of water in this thing decreases
 *         by k this minute (but not less than zero — if the thing contains less
 *         than k water, the resulting amount of water will be zero).
 * 
 *         The task is to minimize the total time of drying by means of using
 *         the radiator effectively. The drying process ends when all the
 *         clothes are dry.
 * 
 *         Input
 * 
 *         The first line contains a single integer n (1 ≤ n ≤ 100 000). The
 *         second line contains ai separated by spaces (1 ≤ ai ≤ 109). The third
 *         line contains k (1 ≤ k ≤ 109).
 * 
 *         Output
 * 
 *         Output a single integer — the minimal possible number of minutes
 *         required to dry all clothes.
 * 
 *         Sample Input
 * 
 *         sample input #1 3 2 3 9 5
 * 
 *         sample input #2 3 2 3 6 5 Sample Output
 * 
 *         sample output #1 3
 * 
 *         sample output #2 2
 *
 */
public class SolutionDay22_P3104 {
	
//	public static void main(String[] args) throws IOException {
//		Scanner in = new Scanner(System.in);
//		int N = in.nextInt();
//		int[] clothes = new int[N];
//		MaxPQ<Integer> pq = new MaxPQ<Integer>();
//		for (int i = 0; i < N; ++i){
//			clothes[i] = in.nextInt();
//			pq.insert(clothes[i]);
//		}
//		int K = in.nextInt();
//		int cnt = 0;
//		while (!pq.isEmpty()){
//			List<Integer> tmp = new ArrayList<Integer>();
//			int c = pq.delMax();
//			c -= K;
//			if (c > 0) tmp.add(c);
//			int size = pq.size();
//			for (int i = 0; i < size; ++i){
//				c = pq.delMax();
//				c--;
//				if (c > 0) tmp.add(c);
//			}
//			for (int t : tmp) pq.insert(t);
//			cnt++;
//		}
//		System.out.println(cnt);
//	}
	
	static int[] clothes;
	static int K;
	static int N;
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		clothes = new int[N];
		int max = 0;
		for (int i = 0; i < N; ++i){
			clothes[i] = in.nextInt();
			max = Math.max(max, clothes[i]);
		}
		K = in.nextInt();
		if (K == 1){
			System.out.println(max);
			return;
		}
		int lf = 0;
		int rt = max;
		while (lf < rt){
			int mid = lf + (rt - lf) / 2;
			if (!valid(mid)) lf = mid + 1;
			else rt = mid;
		}
		System.out.println(lf);
	}
	
	public static boolean valid(int m){
		for (int i = 0, times = 0; i < N; ++i){
			int more = clothes[i] - m;
			if (more >= 0){
				times += (int) Math.ceil(more / (1.0 * (K - 1)));
				if (times > m) return false;
			}
		}
		return true;
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
