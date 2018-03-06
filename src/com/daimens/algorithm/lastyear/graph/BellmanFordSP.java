package com.daimens.algorithm.graph;

import java.util.Queue;

import com.daimens.algorithm.utils.In;

public class BellmanFordSP {
	
	private double[] distTo;
	private DirectedEdge[] edgeTo;
	private boolean[] onQueue;
	private Queue<Integer> queue;
	private int cost;
	private Iterable<DirectedEdge> cycle;
	
	public BellmanFordSP(EdgeWeightedDigraph G, int s) {
		distTo = new double[G.V()];
		edgeTo = new DirectedEdge[G.V()];
		for (int v = 0; v < G.V(); v++){
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0.0;
		
		for (int pass = 0; pass < G.V(); pass++){
			
			// 需要遍历E条边
			for (int v = 0; v < G.V(); v++){
				relax(G, v);
			}
		}
		System.out.println();
	}
	
	/**
	 * G.adj(v) 返回顶点v的所有边
	 * @param G
	 * @param v
	 */
	private void relax(EdgeWeightedDigraph G, int v){
		for (DirectedEdge e : G.adj(v)){
			int w = e.to(); // 汇点 w
			//松弛操作 更新从v出发的所有其他w顶点的最小距离
			if (distTo[w] > distTo[v] + e.weight()){
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
			}
		}
	}
	
	public boolean hasPathTo(int v){
		return distTo[v] < Double.POSITIVE_INFINITY;
	}
	
	
	public static void main(String[] args) {
		In in = new In("./data/tinyEWD.txt");
		EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
		BellmanFordSP sp = new BellmanFordSP(G, 0);
	}
}
