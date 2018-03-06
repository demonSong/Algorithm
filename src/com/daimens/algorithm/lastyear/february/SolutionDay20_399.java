package com.daimens.algorithm.february;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Demon Song
 * 399.Evaluate Division
 * Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real
 * number (floating point number). Given some queries,return the answers.If the answer does not exist,return -1.0.
 * Example:
 * Given a / b = 2.0, b / c = 3.0.
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?.
 * return [6.0,0.5,-1.0,1.0,-1.0].
 * The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , 
 * where equations.size() == values.size(), and the values are positive. This represents the equations. 
 * Return vector<double>.
 * According to the example above:
 * equations = [ ["a", "b"], ["b", "c"] ],
 * values = [2.0, 3.0],
 * queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
 * The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
 *
 */
public class SolutionDay20_399 {
//	public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
//		HashMap<HashMap<String, String>, Double> map = new HashMap<>();
//		HashMap<String, HashMap<String, String>> head = new HashMap<>(); // 遇到相同的head会被覆盖掉的啊
//		HashMap<String, String> pointer1 = new HashMap<>();
//		
//		for (int i = 0; i < equations.length; i++){
//			String[] equation = equations[i];
//			
//			
//			pointer1.put(equation[0], equation[1]);
//			head.put(equation[0], pointer1);
//			map.put(pointer1, values[i]);
//			
//			pointer1.put(equation[1], equation[0]);
//			head.put(equation[0], pointer1);
//			map.put(pointer1, 1/ values[i]);
//		}
//		
//		double[] results = new double[queries.length];
//		for (int i = 0; i < queries.length; i++) {
//			String[] query = queries[i];
//			
//			String queryStart = query[0];
//			String queryEnd = query[1];
//			
//			while (head.containsKey(queryStart) && head.containsKey(queryEnd)){
//				HashMap<String, String> pointer = head.get(query[0]);
//				String query2 = pointer.get(query[0]);
//				double res = map.get(pointer);
//			}
//			
//		}
//		
//        return null;
//    }
	
	public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
		//map 可以是多个，这就需要把集合取出来，再存放值
		Map<String,Map<String,Double>> numMap = new HashMap<>();
		int i = 0;
		for (String[] str : equations){
			insertPairs(numMap, str[0], str[1], values[i]);
			insertPairs(numMap, str[1], str[0], 1 / values[i]);
			i++;
		}
		
		double[] res = new double[queries.length];
		i = 0;
		for (String[] q : queries){
			Double resObj = handleQuery(q[0], q[1], numMap, new HashSet<>());
            res[i++] = (resObj != null) ? resObj : -1.0;
		}
		
		return res;
	}
	
	private void insertPairs(Map<String,Map<String,Double>> numMap,String num,String denom,Double value){
		Map<String,Double> denomMap = numMap.get(num);
		if(denomMap == null){
			denomMap = new HashMap<>();
			numMap.put(num, denomMap);
		}
		denomMap.put(denom, value);
	}
	
	private Double handleQuery(String num,String denom,Map<String,Map<String,Double>> numMap,Set<String> visitedSet){
		String dupeKey = num + ":" + denom;
		if(visitedSet.contains(dupeKey)) return null;
		if(!numMap.containsKey(num) || !numMap.containsKey(denom)) return null;
		if(num.equals(denom)) return 1.0;
		//start query
		Map<String,Double> denomMap = numMap.get(num);
		visitedSet.add(dupeKey);
		for (String key : denomMap.keySet()){
			Double res = handleQuery(key, denom, numMap, visitedSet);
			if(res != null){
				return denomMap.get(key) * res;
			}
		}
		//回溯过程
		visitedSet.remove(dupeKey);
		return null;
	}
	
	public static void main(String[] args) {
		SolutionDay20_399 day = new SolutionDay20_399();
		String[][] equations = {{"a","b"},{"b","c"}};
		double[] values = {2.0,3.0};
		String[][] queries = {{"a","c"},{"b","a"},{"a","e"},{"a","a"},{"x","x"}};
		day.calcEquation(equations, values, queries);
	}
}
