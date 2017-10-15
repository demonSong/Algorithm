package com.daimens.algorithm.october;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class SolutionDay15_L0699 {
	
//	class Edge implements Comparable<Edge>{
//		int x;
//		int y;
//		int isX;
//		
//		Edge(int x, int y, int isX){
//			this.x = x;
//			this.y = y;
//			this.isX = isX;
//		}
//		
//		@Override
//		public int compareTo(Edge o) {
//			return this.x != o.x ? this.x - o.x : this.isX - o.isX;
//		}
//		
//		@Override
//		public String toString() {
//			return "" + x;
//		}
//	}
//	
//    public List<Integer> fallingSquares(int[][] positions) {
//    	int n = positions.length;
//    	List<Integer> ans = new ArrayList<>();
//    	
//    	TreeMap<Edge, Integer> map = new TreeMap<>();
//    	List<Edge> tmp = new ArrayList<Edge>();
//    	int max = 0;
//    	
//    	for (int i = 0; i < n; ++i) {
//    		int x = positions[i][0];
//    		int h = positions[i][1];
//    		int y = h + x - 1;
//    		
//    		Edge lf = new Edge(x, y, 0);
//    		Edge rt = new Edge(y, y, 1);
//    		
//    		int h_max = 0;
//    		for (Edge e : map.subMap(lf, rt).keySet()) {
//    			h_max = Math.max(h_max, map.get(e));
//    		}
//    		
////    		for (Edge key : map.keySet()) {
////    			if (key.x <= x && key.y >= y){
////    				h_max = Math.max(h_max, map.get(key));
////    			}
////    		}
//    		
//    		for (Edge edge : tmp) {
//    			if (edge.x <= x && edge.y >= y) {
//    				h_max = Math.max(h_max, edge.isX);
//    			}
//    		}
//    		
//    		h_max += h;
//    		
//    		map.put(new Edge(x, y, 0), h_max);
//    		map.put(new Edge(y, y, 1), h_max);
//    		tmp.add(new Edge(x, y, h_max));
//    		
//    		//update
////    		for (Edge e : map.subMap(lf, rt).keySet()) {
////    			map.put(e, h_max);
////    		}
////    		
//    		max = Math.max(max, h_max);
//    		ans.add(max);
//    	}
//    	
//    	return ans;
//    }	
	
//	class Edge implements Comparable<Edge>{
//		int x;
//		int y;
//		int isX;
//		
//		Edge(int x, int y, int isX){
//			this.x = x;
//			this.y = y;
//			this.isX = isX;
//		}
//		
//		@Override
//		public int compareTo(Edge o) {
//			return this.x != o.x ? this.x - o.x : this.isX - o.isX;
//		}
//		
//		@Override
//		public String toString() {
//			return "" + x;
//		}
//	}
//	
//    public List<Integer> fallingSquares(int[][] positions) {
//    	int n = positions.length;
//    	List<Integer> ans = new ArrayList<>();
//    	
//    	TreeMap<Double, Integer> map = new TreeMap<>();
//    	List<Edge> tmp = new ArrayList<Edge>();
//    	int max = 0;
//    	
//    	for (int i = 0; i < n; ++i) {
//    		int x = positions[i][0];
//    		int h = positions[i][1];
//    		int y = h + x - 1;
//    
//            
//    		int h_max = 0;
//    		for (Double e : map.subMap(x - 0.1, y + 0.1).keySet()) {
//    			h_max = Math.max(h_max, map.get(e));
//    		}
//    		
//    		for (Edge edge : tmp) {
//    			if (edge.x <= x && edge.y >= y) {
//    				h_max = Math.max(h_max, edge.isX);
//    			}
//    		}
//    		
//    		h_max += h;
//    		
//    		map.put(x * 1.0, h_max);
//    		map.put(y * 1.0, h_max);
//    		tmp.add(new Edge(x, y, h_max));
//    		
//    		max = Math.max(max, h_max);
//    		ans.add(max);
//    	}
//    	
//    	return ans;
//    }
	
	
	    public List<Integer> fallingSquares(int[][] positions) {
	    	int n = positions.length;
	    	List<Integer> ans = new ArrayList<>();
	    	
	    	TreeMap<Double, Integer> map = new TreeMap<>();
	    	for (int[] pos : positions) {
	    		int x = pos[0];
	    		int h = pos[1];
	    		int y = x + h - 1;
	    		map.put(x * 1.0, h);
	    		map.put(y * 1.0, h);
	    	}
	    	int max = 0;
	    	
	    	for (int i = 0; i < n; ++i) {
	    		int x = positions[i][0];
	    		int h = positions[i][1];
	    		int y = h + x - 1;
	    
	            
	    		int h_max = 0;
	    		for (Double e : map.subMap(x - 0.1, y + 0.1).keySet()) {
	    			h_max = Math.max(h_max, map.get(e));
	    		}
	    		
	    		h_max += h;
	    		
	    		for (Double e : map.subMap(x - 0.1, y + 0.1).keySet()) {
	    			map.put(e, map.get(e) + h_max);
	    		}
	    		
	    		max = Math.max(max, h_max);
	    		ans.add(max);
	    	}
	    	
	    	return ans;
	    }	
	
	public static void main(String[] args) {
		SolutionDay15_L0699 day = new SolutionDay15_L0699();
//		int[][] pos = {{1, 2},{2, 3},{6, 1}};
//		int[][] pos = {{6, 4},{2, 7},{6, 9}};
//		int[][] pos = {{9, 1},{6, 5},{6, 7}};
//		int[][] pos = {{2, 1},{2, 9},{1, 8}};
//		int[][] pos = {{1, 5},{2, 2},{7, 5}};
//		int[][] pos = {{100, 100},{200, 100}};
//		int[][] pos = {{33,34},{47,62},{70,16}};
//		int[][] pos = {{3,2},{8,3},{1,4},{8,10},{9,3}};
		int[][] pos = {{9, 7},{1, 9},{3, 1}};
		System.out.println(day.fallingSquares(pos));
	}
}
