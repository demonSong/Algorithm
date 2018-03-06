package com.daimens.algorithm.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.daimens.algorithm.utils.In;

public class SymbolDigraph {
	private static final String NEWLINE = System.getProperty("line.separator");
	
	private Digraph g;
	private Map<String, Integer> map = new HashMap<>();
	private String[] keys;
	
	
	public SymbolDigraph(String filename, String delimiter){
		In in = new In(filename);
		while (in.hasNextLine()){
			String[] data = in.readLine().split(delimiter);
			for (int i = 0; i < data.length; i++){
				if (!map.containsKey(data[i])){
					map.put(data[i], map.size());
				}
			}
		}
		
		keys = new String[map.size()];
		for (String name : map.keySet()){
			keys[map.get(name)] = name;
		}
		
		g = new Digraph(map.size());
		in = new In(filename);
		while (in.hasNextLine()){
			String[] data = in.readLine().split(delimiter);
			int v = map.get(data[0]);
			for (int i = 1; i < data.length; i++){
				g.addEdge(v, map.get(data[i]));
			}
		}
	}
	
	public Digraph digraph(){
		return g;
	}
	
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(g.V() + " vertices, " + g.E() + " edges" + NEWLINE);
		for (int v = 0; v < g.V(); v++){
			s.append(String.format("%s:", keys[v]));
			for (int w : g.adj(v)){
				s.append(String.format("%s ", keys[w]));
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}
	
	public static void main(String[] args) {
		SymbolDigraph g = new SymbolDigraph("./data/jobs.txt", "/");
		System.out.println(g);
	}

}
