package com.daimens.algorithm.graph;

import com.daimens.algorithm.collection.Bag;

public class Digraph{
	private static final String NEWLINE = System.getProperty("line.separator");

	private final int V;
	private int E;
	private Bag<Integer>[] adj;
	
	public Digraph(int V){
		this.V = V;
		adj = new Bag[V];
		for (int i = 0; i < V; i++){
			adj[i] = new Bag<Integer>();
		}
	}
	
	public void addEdge(int v, int w){
		adj[v].add(w);
		E++;
	}
	
	public Iterable<Integer> adj(int v) {
        return adj[v];
    }
	
	public int V(){
		return this.V;
	}
	
	public int E(){
		return this.E;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " vertices, " + E + " edges " + NEWLINE);
		for (int v = 0; v < V; v++) {
			s.append(String.format("%d: ", v));
			for (int w : adj[v]) {
				s.append(String.format("%d ", w));
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}
	
}