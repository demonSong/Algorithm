package com.daimens.algorithm.may;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 
 * @author DemonSong
 * 
 *         332.Reconstruct Itinerary
 * 
 *         Given a list of airline tickets represented by pairs of departure and
 *         arrival airports [from, to], reconstruct the itinerary in order. All
 *         of the tickets belong to a man who departs from JFK. Thus, the
 *         itinerary must begin with JFK.
 * 
 *         Note: If there are multiple valid itineraries, you should return the
 *         itinerary that has the smallest lexical order when read as a single
 *         string. For example, the itinerary ["JFK", "LGA"] has a smaller
 *         lexical order than ["JFK", "LGB"]. All airports are represented by
 *         three capital letters (IATA code). You may assume all tickets form at
 *         least one valid itinerary. Example 1: tickets = [["MUC", "LHR"],
 *         ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]] Return ["JFK", "MUC",
 *         "LHR", "SFO", "SJC"]. Example 2: tickets =
 *         [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 *         Return ["JFK","ATL","JFK","SFO","ATL","SFO"]. Another possible
 *         reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is
 *         larger in lexical order.
 *
 */
public class SolutionDay11_L0332 {

//	public List<String> findItinerary(String[][] tickets) {
//		Map<String,List<String>> graph = new HashMap<>();
//		for (String[] tick : tickets){
//			String from = tick[0];
//			String to = tick[1];
//			if (graph.containsKey(from)){
//				List<String> tmp = graph.get(from);
//				tmp.add(to);
//				graph.put(from, tmp);
//			}else{
//				List<String> tmp = new ArrayList<>();
//				tmp.add(to);
//				graph.put(from, tmp);
//			}
//		}
//		List<List<String>> ans = new ArrayList<>();
//		List<String> res = new ArrayList<>();
//		res.add("JFK");
//		dfs(graph, "JFK", ans, res);
//		return null;
//	}
//	
//	private void dfs(Map<String,List<String>> graph, String start,List<List<String>> ans, List<String> res){
//		List<String> to = graph.get(start);
//		boolean isEnd = true;
//		for (int i = 0; i < to.size(); i++){
//			String tmp = to.get(i);
//			if (!tmp.equals("#")){
//				isEnd = false;
//			}
//		}
//		
//		if (isEnd){
//			ans.add(new ArrayList<>(res));
//			return;
//		}
//		
//		for (int i = 0; i< to.size(); i++){
//			String tos = to.get(i);
//			to.set(i, "#");
//			if	(!tos.equals("#")){
//				res.add(tos);
//				dfs(graph, tos, ans, res);
//				res.remove(res.size()-1);
//				to.set(i, tos);
//			}
//		}
//	}
	
	public List<String> findItinerary(String[][] tickets) {
		Map<String, PriorityQueue<String>> targets = new HashMap<>();
		for (String[] ticket : tickets)
	        targets.computeIfAbsent(ticket[0], k -> new PriorityQueue<String>()).add(ticket[1]);
		visit("JFK",targets);
		return route;
	}
	
	List<String> route = new LinkedList<String>();
	private void visit(String airport, Map<String, PriorityQueue<String>> targets) {
		while (targets.containsKey(airport) && !targets.get(airport).isEmpty())
			visit(targets.get(airport).poll(), targets);
		route.add(0, airport);
	}
	
	public static void main(String[] args) {
		SolutionDay11_L0332 day = new SolutionDay11_L0332();
		String[][] tickets = {{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
		day.findItinerary(tickets);
	}
	
}

class Graph{
	String from;
	String[] to;
	
}
