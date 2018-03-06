package com.daimens.algorithm.graph;

import java.util.Stack;

/**
 * 
 * @author DemonSong
 *
 */
public class DirectedCycle {
	
	private boolean[] marked;
	private int[] edgeTo;
	private boolean[] onStack;
	private Stack<Integer> cycle;
	
	public DirectedCycle(Digraph G){
		marked = new boolean[G.V()];
		onStack = new boolean[G.V()];
		edgeTo = new int[G.V()];
		for (int v = 0; v < G.V(); v++){
			if (!marked[v]) dfs(G, v);
		}
	}
	
	private void dfs(Digraph G, int v){
		marked[v] = true;  //全局标记
		onStack[v] = true; //状态要还原?
		for (int w : G.adj(v)){
			if (this.hasCycle()) return;
			if (!marked[w]){
				edgeTo[w] = v; //那个点连到终点，也就是循环的起点开始逐渐搜索
				dfs(G, w); //存在有向环的情况下，避免死循环
			}
			else if (onStack[w]){ //如果不存在有向环，是不可能来到这状态的
				cycle = new Stack<Integer>();
				for (int x = v; x != w; x = edgeTo[x]){
					cycle.push(x);
				}
				cycle.push(w);
				cycle.push(v);
			}
		}
		//状态还原，在dfs函数结束的地方，因为onStack并非函数参数，而是类似于全局变量
		onStack[v] = false; //正常结束的终点，不记录路径中。出现多条路径指向同一个终点，这种情况检测没有任何问题。
	}
	
	public boolean hasCycle(){
		return cycle != null;
	}
	
	public Iterable<Integer> cycle(){
		return cycle;
	}
	
	public static void main(String[] args) {
		Digraph g = new Digraph(4);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		g.addEdge(3, 1);
		
		DirectedCycle cycle = new DirectedCycle(g);
		System.out.println(cycle.hasCycle());
	}
}
