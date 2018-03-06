package com.daimens.algorithm.graph;

import com.daimens.algorithm.collection.IndexMinPQ;
import com.daimens.algorithm.utils.In;

public class DijkstraSP {
	
	private DirectedEdge[] edgeTo;
	private double[] distTo;
	private IndexMinPQ<Double> pq;
	
	public DijkstraSP(EdgeWeightedDigraph G, int s){
		for (DirectedEdge e : G.edges()){
			if (e.weight() < 0){
				throw new IllegalArgumentException("edge " + e + " has negative weight");
			}
		}
		
		distTo = new double[G.V()];
		edgeTo = new DirectedEdge[G.V()];
		
		for (int v = 0; v < G.V(); v++){
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0.0;
		
		pq = new IndexMinPQ<Double>(G.V());
		pq.insert(s, distTo[s]);
		
		while (!pq.isEmpty()){
			int v = pq.delMin();
			for (DirectedEdge e : G.adj(v)){
				relax(e);
			}
		}
	}
	
	
	private void relax(DirectedEdge e){
		int v = e.from(), w = e.to();
		if (distTo[w] > distTo[v] + e.weight()){
			distTo[w] = distTo[v] + e.weight();
			edgeTo[w] = e;
			if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
			else pq.insert(w, distTo[w]);
		}
	}
	
	public static void main(String[] args) {
		In in = new In("./data/tinyEWD.txt");
		EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
		System.out.println(G);
		DijkstraSP sp = new DijkstraSP(G, 0);
		System.out.println();
	}
	
}
