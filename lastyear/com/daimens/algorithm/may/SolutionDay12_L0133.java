package com.daimens.algorithm.may;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author DemonSong
 * 
 *         133.Clone Graph
 * 
 *         Clone an undirected graph. Each node in the graph contains a label
 *         and a list of its neighbors.
 * 
 * 
 *         OJ's undirected graph serialization: Nodes are labeled uniquely.
 * 
 *         We use # as a separator for each node, and , as a separator for node
 *         label and each neighbor of the node. As an example, consider the
 *         serialized graph {0,1,2#1,2#2,2}.
 * 
 *         The graph has a total of three nodes, and therefore contains three
 *         parts as separated by #.
 * 
 *         First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
 *         Second node is labeled as 1. Connect node 1 to node 2. Third node is
 *         labeled as 2. Connect node 2 to node 2 (itself), thus forming a
 *         self-cycle. Visually, the graph looks like the following:	
 * 
 *         1 / \ / \ 0 --- 2 / \ \_/
 *
 */
public class SolutionDay12_L0133 {
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		Map<Integer,UndirectedGraphNode> visited = new HashMap<>();
		UndirectedGraphNode clone = create(node, visited);
		return clone;
    }
	
	
	private UndirectedGraphNode create(UndirectedGraphNode node,Map<Integer,UndirectedGraphNode> visited){
		if (node == null) return null;
		if (visited.containsKey(node.label)) return visited.get(node.label);
		UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
		visited.put(node.label, clone);
		for (UndirectedGraphNode n : node.neighbors){
			clone.neighbors.add(create(n,visited));
		}
		return clone;
	}
	
	public static void main(String[] args) {
		SolutionDay12_L0133 day = new SolutionDay12_L0133();
		UndirectedGraphNode node = new UndirectedGraphNode(0);
		node.neighbors.add(node);
		day.cloneGraph(node);
		System.out.println();
	}
}

class UndirectedGraphNode{
	int label;
	List<UndirectedGraphNode> neighbors;
	public UndirectedGraphNode(int x) {
		label = x;
		neighbors = new ArrayList<UndirectedGraphNode>();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("label: " + label +"\n");
		sb.append("neighbors: ");
		for (UndirectedGraphNode n : neighbors){
			sb.append(n.label+" ");
		}
		return sb.toString();
	}
}