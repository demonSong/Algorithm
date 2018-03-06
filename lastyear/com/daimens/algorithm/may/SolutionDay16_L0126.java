package com.daimens.algorithm.may;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 
 * @author DemonSong
 * 
 *         126.Word Ladder II
 * 
 *         Given two words (beginWord and endWord), and a dictionary's word
 *         list, find all shortest transformation sequence(s) from beginWord to
 *         endWord, such that:
 * 
 *         Only one letter can be changed at a time Each transformed word must
 *         exist in the word list. Note that beginWord is not a transformed
 *         word. For example,
 * 
 *         Given: beginWord = "hit" endWord = "cog" wordList =
 *         ["hot","dot","dog","lot","log","cog"] Return [
 *         ["hit","hot","dot","dog","cog"], ["hit","hot","lot","log","cog"] ]
 *         Note: Return an empty list if there is no such transformation
 *         sequence. All words have the same length. All words contain only
 *         lowercase alphabetic characters. You may assume no duplicates in the
 *         word list. You may assume beginWord and endWord are non-empty and are
 *         not the same. UPDATE (2017/1/20): The wordList parameter had been
 *         changed to a list of strings (instead of a set of strings). Please
 *         reload the code definition to get the latest changes.
 *
 */
public class SolutionDay16_L0126 {

	// public List<List<String>> findLadders(String beginWord, String endWord,
	// List<String> wordList) {
	// Map<String, List<String>> map = new HashMap<>();
	//
	// map.put(beginWord, new ArrayList<>());
	// for (String word : wordList){
	// map.put(word, new ArrayList<>());
	// }
	// for (String key : map.keySet()){
	// List<String> container = map.get(key);
	// for (String word : wordList){
	// if (oneDiff(key, word)){
	// container.add(word);
	// }
	// }
	// map.put(key, container);
	// }
	// Set<String> visited = new HashSet<>();
	// List<List<String>> ans = helper(map, beginWord, endWord,visited);
	// return ans;
	// }

	// public List<List<String>> findLadders(String beginWord, String endWord,
	// List<String> wordList) {
	// Map<String, List<String>> map = new HashMap<>();
	//
	// map.put(beginWord, new ArrayList<>());
	// for (String word : wordList){
	// map.put(word, new ArrayList<>());
	// }
	// for (String key : map.keySet()){
	// List<String> container = map.get(key);
	// for (String word : wordList){
	// if (oneDiff(key, word)){
	// container.add(word);
	// }
	// }
	// map.put(key, container);
	// }
	// int distance = bfs(beginWord, endWord, wordList);
	// Set<String> visited = new HashSet<>();
	// List<List<String>> ans = new ArrayList<>();
	// dfs(map, beginWord, endWord, ans, new ArrayList<>(), distance, visited);
	// return ans;
	// }

	// private void dfs(Map<String, List<String>> map,String beginWord, String
	// endWord, List<List<String>> ans, List<String> path, int
	// distance,Set<String> visited){
	// if (distance == 0 || visited.contains(beginWord)) return;
	// visited.add(beginWord);
	// path.add(beginWord);
	// if (distance == 2){
	// if (map.get(beginWord).contains(endWord)){
	// path.add(endWord);
	// ans.add(new ArrayList<>(path));
	// }
	// path.remove(beginWord);
	// visited.remove(beginWord);
	// return;
	// }
	// for (String find : map.get(beginWord)){
	// dfs(map, find, endWord, ans, path, distance-1, visited);
	// }
	// path.remove(beginWord);
	// visited.remove(beginWord);
	// }

	// private void dfs(Map<String, List<String>> map,String beginWord, String
	// endWord, List<List<String>> ans, List<String> path, int
	// distance,Set<String> visited){
	// path.add(beginWord);
	// if (distance == 0){
	// path.remove(path.size()-1);
	// return;
	// }
	// if (beginWord.equals(endWord)){
	// ans.add(new ArrayList<>(path));
	// path.remove(path.size()-1);
	// return;
	// }
	// for (String find : map.get(beginWord)){
	// dfs(map, find, endWord, ans, path, distance-1, visited);
	// }
	// path.remove(path.size()-1);
	// }

	// private int bfs(String beginWord, String endWord, List<String> wordList)
	// {
	// List<String> reached = new ArrayList<>();
	// reached.add(beginWord);
	// Set<String> wordSet = new HashSet<>(wordList);
	// if(!wordSet.contains(endWord)) return 0;
	// wordSet.add(endWord);
	//
	// int distance = 1;
	// while (!reached.contains(endWord)){ //达到该目的地
	// List<String> toAdd = new ArrayList<>();
	// for (String each : reached){
	// for (int i = 0; i < each.length(); i++){
	// char[] chars = each.toCharArray();
	// for (char c = 'a'; c <= 'z'; c++){
	// chars[i] = c;
	// String wd = new String(chars);
	// if (wordSet.contains(wd)){
	// toAdd.add(wd);
	// wordSet.remove(wd);
	// }
	// }
	// }
	// }
	// distance ++;
	// if (toAdd.size() == 0) return 0;
	// reached = toAdd;
	// }
	// return distance;
	// }

	// private List<List<String>> helper(Map<String, List<String>> map,String
	// beginWord, String endWord, Set<String> visited){
	// List<List<String>> ans = new ArrayList<>();
	// if (!map.containsKey(endWord) || visited.contains(beginWord)) return
	// null;
	// visited.add(beginWord);
	// for (String find : map.get(beginWord)){
	// if (find.equals(endWord)){
	// List<String> container = new ArrayList<>();
	// container.add(beginWord);
	// container.add(find);
	// ans.add(container);
	// visited.remove(beginWord);
	// return ans;
	// }
	// }
	//
	// Map<Integer, List<List<List<String>>>> memory = new HashMap<>();
	// int min = Integer.MAX_VALUE;
	// for (String find : map.get(beginWord)){
	// List<List<String>> tmp = helper(map, find, endWord,visited);
	// if (tmp == null) continue;
	// min = Math.min(min, tmp.get(0).size());
	// if (memory.containsKey(tmp.get(0).size())){
	// List<List<List<String>>> container = memory.get(tmp.get(0).size());
	// container.add(tmp);
	// memory.put(tmp.get(0).size(), container);
	// }else{
	// List<List<List<String>>> container = new ArrayList<>();
	// container.add(tmp);
	// memory.put(tmp.get(0).size(), container);
	// }
	// }
	//
	// if (min == Integer.MIN_VALUE) return ans;
	//
	// for (List<List<String>> tmp : memory.get(min)){
	// for (int i = 0; i < tmp.size(); i++){
	// List<String> container = new ArrayList<>();
	// container.add(beginWord);
	// container.addAll(tmp.get(i));
	// ans.add(container);
	// }
	// }
	// visited.remove(beginWord);
	// return ans;
	// }
	//
	//
	// private boolean oneDiff(String a, String b){
	// if (a.equals(b)) return false;
	// char[] aa = a.toCharArray();
	// char[] bb = b.toCharArray();
	//
	// int oneDiff = 0;
	// for (int i = 0; i < aa.length; i++){
	// if (aa[i] != bb[i]){
	// oneDiff ++;
	// if (oneDiff >= 2) return false;
	// }
	// }
	// return true;
	// }

	// public List<List<String>> findLadders(String start, String end,
	// List<String> dict) {
	// // hash set for both ends
	// Set<String> set1 = new HashSet<String>();
	// Set<String> set2 = new HashSet<String>();
	//
	// // initial words in both ends
	// set1.add(start);
	// set2.add(end);
	//
	// // we use a map to help construct the final result
	// Map<String, List<String>> map = new HashMap<String, List<String>>();
	//
	// // build the map
	// helper(dict, set1, set2, map, false);
	//
	// List<List<String>> res = new ArrayList<List<String>>();
	// List<String> sol = new ArrayList<String>(Arrays.asList(start));
	//
	// // recursively build the final result
	// generateList(start, end, map, sol, res);
	//
	// return res;
	// }
	//
	// boolean helper(List<String> dict, Set<String> set1, Set<String> set2,
	// Map<String, List<String>> map, boolean flip) {
	// if (set1.isEmpty()) {
	// return false;
	// }
	//
	// if (set1.size() > set2.size()) {
	// return helper(dict, set2, set1, map, !flip);
	// }
	//
	// // remove words on current both ends from the dict
	// dict.removeAll(set1);
	// dict.removeAll(set2);
	//
	// // as we only need the shortest paths
	// // we use a boolean value help early termination
	// boolean done = false;
	//
	// // set for the next level
	// Set<String> set = new HashSet<String>();
	//
	// // for each string in end 1
	// for (String str : set1) {
	// for (int i = 0; i < str.length(); i++) {
	// char[] chars = str.toCharArray();
	//
	// // change one character for every position
	// for (char ch = 'a'; ch <= 'z'; ch++) {
	// chars[i] = ch;
	//
	// String word = new String(chars);
	//
	// // make sure we construct the tree in the correct direction
	// String key = flip ? word : str;
	// String val = flip ? str : word;
	//
	// List<String> list = map.containsKey(key) ? map.get(key) : new
	// ArrayList<String>();
	//
	// if (set2.contains(word)) {
	// done = true;
	//
	// list.add(val);
	// map.put(key, list);
	// }
	//
	// if (!done && dict.contains(word)) {
	// set.add(word);
	//
	// list.add(val);
	// map.put(key, list);
	// }
	// }
	// }
	// }
	//
	// // early terminate if done is true
	// return done || helper(dict, set2, set, map, !flip);
	// }
	//
	// void generateList(String start, String end, Map<String, List<String>>
	// map, List<String> sol, List<List<String>> res) {
	// if (start.equals(end)) {
	// res.add(new ArrayList<String>(sol));
	// return;
	// }
	//
	// // need this check in case the diff between start and end happens to be
	// one
	// // e.g "a", "c", {"a", "b", "c"}
	// if (!map.containsKey(start)) {
	// return;
	// }
	//
	// for (String word : map.get(start)) {
	// sol.add(word);
	// generateList(word, end, map, sol, res);
	// sol.remove(sol.size() - 1);
	// }
	// }

	// Solution one TLE
	// public List<List<String>> findLadders(String beginWord, String endWord,
	// List<String> wordList) {
	// // 这部分代码还要放在bfs中去完成
	// Map<String, List<String>> map = new HashMap<>();
	// map.put(beginWord, new ArrayList<>());
	// for (String word : wordList){
	// map.put(word, new ArrayList<>());
	// }
	// for (String key : map.keySet()){
	// List<String> container = map.get(key);
	// for (String word : wordList){
	// if (oneDiff(key, word)){
	// container.add(word);
	// }
	// }
	// map.put(key, container);
	// }
	// Map<String,Integer> distanceMap = new HashMap<>();
	// int distance = bfs(beginWord, endWord, wordList,distanceMap);
	// List<List<String>> ans = new ArrayList<>();
	// dfs(map, beginWord, endWord, ans, new ArrayList<>(), distance,
	// distanceMap);
	// return ans;
	// }
	//
	// private void dfs(Map<String, List<String>> map,String beginWord, String
	// endWord, List<List<String>> ans, List<String> path, int distance,
	// Map<String, Integer> distanceMap){
	// path.add(beginWord);
	// if (distance == 0){
	// path.remove(path.size()-1);
	// return;
	// }
	// if (beginWord.equals(endWord)){
	// ans.add(new ArrayList<>(path));
	// path.remove(path.size()-1);
	// return;
	// }
	// for (String find : map.get(beginWord)){
	// if (!distanceMap.containsKey(find)) continue;
	// if (distanceMap.get(beginWord) + 1 == distanceMap.get(find))
	// dfs(map, find, endWord, ans, path, distance-1,distanceMap);
	// }
	// path.remove(path.size()-1);
	// }
	//
	//
	//
	// // "a", "b", "c" 会失败
	// private int bfs(String beginWord, String endWord, List<String> wordList,
	// Map<String, Integer> distanceMap) {
	// List<String> reached = new ArrayList<>();
	// reached.add(beginWord);
	// Set<String> wordSet = new HashSet<>(wordList);
	// if(!wordSet.contains(endWord)) return 0;
	// wordSet.add(endWord);
	// int distance = 1;
	// distanceMap.put(beginWord, distance);
	// while (!reached.contains(endWord)){ //达到该目的地
	// List<String> toAdd = new ArrayList<>();
	// for (String each : reached){
	// for (int i = 0; i < each.length(); i++){
	// char[] chars = each.toCharArray();
	// for (char c = 'a'; c <= 'z'; c++){
	// chars[i] = c;
	// String wd = new String(chars);
	// if (wordSet.contains(wd)){
	// toAdd.add(wd);
	// wordSet.remove(wd);
	// }
	// }
	// }
	// }
	// distance ++;
	// for (String each: toAdd){
	// if (distanceMap.containsKey(each)) continue;
	// distanceMap.put(each, distance);
	// }
	// if (toAdd.size() == 0) return 0;
	// reached = toAdd;
	// }
	// return distance;
	// }
	//
	// private boolean oneDiff(String a, String b){
	// if (a.equals(b)) return false;
	// char[] aa = a.toCharArray();
	// char[] bb = b.toCharArray();
	//
	// int oneDiff = 0;
	// for (int i = 0; i < aa.length; i++){
	// if (aa[i] != bb[i]){
	// oneDiff ++;
	// if (oneDiff >= 2) return false;
	// }
	// }
	// return true;
	// }

	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		Map<String, List<String>> map = new HashMap<>();
		Map<String, Integer> distanceMap = new HashMap<>();
		Set<String> wordDict = new HashSet<>(wordList);
		wordDict.add(beginWord);
		int distance = bfs(beginWord, endWord, wordDict, distanceMap, map);
		List<List<String>> ans = new ArrayList<>();
		if (distance == 0)
			return ans;
		dfs(map, beginWord, endWord, ans, new ArrayList<>(), distance, distanceMap);
		return ans;
	}

	private void dfs(Map<String, List<String>> map, String beginWord, String endWord, List<List<String>> ans,
			List<String> path, int distance, Map<String, Integer> distanceMap) {
		path.add(beginWord);
		if (distance == 0) {
			path.remove(path.size() - 1);
			return;
		}
		if (beginWord.equals(endWord)) {
			ans.add(new ArrayList<>(path));
			path.remove(path.size() - 1);
			return;
		}
		for (String find : map.get(beginWord)) {
			if (!distanceMap.containsKey(find))
				continue;
			if (distanceMap.get(beginWord) + 1 == distanceMap.get(find))
				dfs(map, find, endWord, ans, path, distance - 1, distanceMap);
		}
		path.remove(path.size() - 1);
	}

	private int bfs(String beginWord, String endWord, Set<String> wordDict, Map<String, Integer> distanceMap,
			Map<String, List<String>> map) {
		if (!wordDict.contains(endWord))
			return 0;
		map.put(beginWord, new ArrayList<>());
		for (String word : wordDict) {
			map.put(word, new ArrayList<>());
		}

		Queue<String> queue = new LinkedList<>();
		queue.offer(beginWord);
		distanceMap.put(beginWord, 1);

		while (!queue.isEmpty()) {
			int count = queue.size();
			boolean foundEnd = false;
			for (int i = 0; i < count; i++) {
				String cur = queue.poll();
				int curDistance = distanceMap.get(cur);
				List<String> neighbors = getNeighbors(cur, wordDict);
				if (neighbors.size() == 0)
					return 0;
				for (String neighbor : neighbors) {
					map.get(cur).add(neighbor);
					if (!distanceMap.containsKey(neighbor)) {
						distanceMap.put(neighbor, curDistance + 1);
						if (endWord.equals(neighbor)) {
							foundEnd = true;
						} else {
							queue.offer(neighbor);
						}
					}
				}
			}
			if (foundEnd)
				break;
		}
		return distanceMap.get(endWord);
	}

	private List<String> getNeighbors(String word, Set<String> wordList) {
		List<String> ans = new ArrayList<>();
		for (int i = 0; i < word.length(); i++) {
			char[] cc = word.toCharArray();
			for (char c = 'a'; c <= 'z'; c++) {
				cc[i] = c;
				String newWord = new String(cc);
				if (wordList.contains(newWord)) {
					if (newWord.equals(word))
						continue;
					ans.add(newWord);
				}
			}
		}
		return ans;
	}

//	public List<List<String>> findLadders(String start, String end, List<String> wordList) {
//		HashSet<String> dict = new HashSet<String>(wordList);
//		List<List<String>> res = new ArrayList<List<String>>();
//		HashMap<String, ArrayList<String>> nodeNeighbors = new HashMap<String, ArrayList<String>>();// Neighbors
//																									// for
//																									// every
//																									// node
//		HashMap<String, Integer> distance = new HashMap<String, Integer>();//
//		ArrayList<String> solution = new ArrayList<String>();
//
//		dict.add(start);
//		bfs(start, end, dict, nodeNeighbors, distance);
//		dfs(start, end, dict, nodeNeighbors, distance, solution, res);
//		return res;
//	}
//
//	// BFS: Trace every node's distance from the start node (level by level).
//	private void bfs(String start, String end, Set<String> dict, HashMap<String, ArrayList<String>> nodeNeighbors,
//			HashMap<String, Integer> distance) {
//		for (String str : dict)
//			nodeNeighbors.put(str, new ArrayList<String>());
//
//		Queue<String> queue = new LinkedList<String>();
//		queue.offer(start);
//		distance.put(start, 0);
//
//		while (!queue.isEmpty()) {
//			int count = queue.size();
//			boolean foundEnd = false;
//			for (int i = 0; i < count; i++) {
//				String cur = queue.poll();
//				int curDistance = distance.get(cur);
//				ArrayList<String> neighbors = getNeighbors(cur, dict);
//
//				for (String neighbor : neighbors) {
//					nodeNeighbors.get(cur).add(neighbor);
//					if (!distance.containsKey(neighbor)) {// Check if visited
//						distance.put(neighbor, curDistance + 1);
//						if (end.equals(neighbor))// Found the shortest path
//							foundEnd = true;
//						else
//							queue.offer(neighbor);
//					}
//				}
//			}
//
//			if (foundEnd)
//				break;
//		}
//	}
//
//	// Find all next level nodes.
//	private ArrayList<String> getNeighbors(String node, Set<String> dict) {
//		ArrayList<String> res = new ArrayList<String>();
//		char chs[] = node.toCharArray();
//
//		for (char ch = 'a'; ch <= 'z'; ch++) {
//			for (int i = 0; i < chs.length; i++) {
//				if (chs[i] == ch)
//					continue;
//				char old_ch = chs[i];
//				chs[i] = ch;
//				if (dict.contains(String.valueOf(chs))) {
//					res.add(String.valueOf(chs));
//				}
//				chs[i] = old_ch;
//			}
//
//		}
//		return res;
//	}
//
//	// DFS: output all paths with the shortest distance.
//	private void dfs(String cur, String end, Set<String> dict, HashMap<String, ArrayList<String>> nodeNeighbors,
//			HashMap<String, Integer> distance, ArrayList<String> solution, List<List<String>> res) {
//		solution.add(cur);
//		if (end.equals(cur)) {
//			res.add(new ArrayList<String>(solution));
//		} else {
//			for (String next : nodeNeighbors.get(cur)) {
//				if (distance.get(next) == distance.get(cur) + 1) {
//					dfs(next, end, dict, nodeNeighbors, distance, solution, res);
//				}
//			}
//		}
//		solution.remove(solution.size() - 1);
//	}

	public static void main(String[] args) {
		SolutionDay16_L0126 day = new SolutionDay16_L0126();
		// String beginWord = "hit";
		// String endWord = "cog";
		// String[] words = {"hot","dot","dog","lot","log","cog"};
		
		String beginWord = "sand";
		String endWord = "acne";
		String[] words = { "slit", "bunk", "wars", "ping", "viva", "wynn", "wows", "irks", "gang", "pool", "mock",
				"fort", "heel", "send", "ship", "cols", "alec", "foal", "nabs", "gaze", "giza", "mays", "dogs", "karo",
				"cums", "jedi", "webb", "lend", "mire", "jose", "catt", "grow", "toss", "magi", "leis", "bead", "kara",
				"hoof", "than", "ires", "baas", "vein", "kari", "riga", "oars", "gags", "thug", "yawn", "wive", "view",
				"germ", "flab", "july", "tuck", "rory", "bean", "feed", "rhee", "jeez", "gobs", "lath", "desk", "yoko",
				"cute", "zeus", "thus", "dims", "link", "dirt", "mara", "disc", "limy", "lewd", "maud", "duly", "elsa",
				"hart", "rays", "rues", "camp", "lack", "okra", "tome", "math", "plug", "monk", "orly", "friz", "hogs",
				"yoda", "poop", "tick", "plod", "cloy", "pees", "imps", "lead", "pope", "mall", "frey", "been", "plea",
				"poll", "male", "teak", "soho", "glob", "bell", "mary", "hail", "scan", "yips", "like", "mull", "kory",
				"odor", "byte", "kaye", "word", "honk", "asks", "slid", "hopi", "toke", "gore", "flew", "tins", "mown",
				"oise", "hall", "vega", "sing", "fool", "boat", "bobs", "lain", "soft", "hard", "rots", "sees", "apex",
				"chan", "told", "woos", "unit", "scow", "gilt", "beef", "jars", "tyre", "imus", "neon", "soap", "dabs",
				"rein", "ovid", "hose", "husk", "loll", "asia", "cope", "tail", "hazy", "clad", "lash", "sags", "moll",
				"eddy", "fuel", "lift", "flog", "land", "sigh", "saks", "sail", "hook", "visa", "tier", "maws", "roeg",
				"gila", "eyes", "noah", "hypo", "tore", "eggs", "rove", "chap", "room", "wait", "lurk", "race", "host",
				"dada", "lola", "gabs", "sobs", "joel", "keck", "axed", "mead", "gust", "laid", "ends", "oort", "nose",
				"peer", "kept", "abet", "iran", "mick", "dead", "hags", "tens", "gown", "sick", "odis", "miro", "bill",
				"fawn", "sumo", "kilt", "huge", "ores", "oran", "flag", "tost", "seth", "sift", "poet", "reds", "pips",
				"cape", "togo", "wale", "limn", "toll", "ploy", "inns", "snag", "hoes", "jerk", "flux", "fido", "zane",
				"arab", "gamy", "raze", "lank", "hurt", "rail", "hind", "hoot", "dogy", "away", "pest", "hoed", "pose",
				"lose", "pole", "alva", "dino", "kind", "clan", "dips", "soup", "veto", "edna", "damp", "gush", "amen",
				"wits", "pubs", "fuzz", "cash", "pine", "trod", "gunk", "nude", "lost", "rite", "cory", "walt", "mica",
				"cart", "avow", "wind", "book", "leon", "life", "bang", "draw", "leek", "skis", "dram", "ripe", "mine",
				"urea", "tiff", "over", "gale", "weir", "defy", "norm", "tull", "whiz", "gill", "ward", "crag", "when",
				"mill", "firs", "sans", "flue", "reid", "ekes", "jain", "mutt", "hems", "laps", "piss", "pall", "rowe",
				"prey", "cull", "knew", "size", "wets", "hurl", "wont", "suva", "girt", "prys", "prow", "warn", "naps",
				"gong", "thru", "livy", "boar", "sade", "amok", "vice", "slat", "emir", "jade", "karl", "loyd", "cerf",
				"bess", "loss", "rums", "lats", "bode", "subs", "muss", "maim", "kits", "thin", "york", "punt", "gays",
				"alpo", "aids", "drag", "eras", "mats", "pyre", "clot", "step", "oath", "lout", "wary", "carp", "hums",
				"tang", "pout", "whip", "fled", "omar", "such", "kano", "jake", "stan", "loop", "fuss", "mini", "byrd",
				"exit", "fizz", "lire", "emil", "prop", "noes", "awed", "gift", "soli", "sale", "gage", "orin", "slur",
				"limp", "saar", "arks", "mast", "gnat", "port", "into", "geed", "pave", "awls", "cent", "cunt", "full",
				"dint", "hank", "mate", "coin", "tars", "scud", "veer", "coax", "bops", "uris", "loom", "shod", "crib",
				"lids", "drys", "fish", "edit", "dick", "erna", "else", "hahs", "alga", "moho", "wire", "fora", "tums",
				"ruth", "bets", "duns", "mold", "mush", "swop", "ruby", "bolt", "nave", "kite", "ahem", "brad", "tern",
				"nips", "whew", "bait", "ooze", "gino", "yuck", "drum", "shoe", "lobe", "dusk", "cult", "paws", "anew",
				"dado", "nook", "half", "lams", "rich", "cato", "java", "kemp", "vain", "fees", "sham", "auks", "gish",
				"fire", "elam", "salt", "sour", "loth", "whit", "yogi", "shes", "scam", "yous", "lucy", "inez", "geld",
				"whig", "thee", "kelp", "loaf", "harm", "tomb", "ever", "airs", "page", "laud", "stun", "paid", "goop",
				"cobs", "judy", "grab", "doha", "crew", "item", "fogs", "tong", "blip", "vest", "bran", "wend", "bawl",
				"feel", "jets", "mixt", "tell", "dire", "devi", "milo", "deng", "yews", "weak", "mark", "doug", "fare",
				"rigs", "poke", "hies", "sian", "suez", "quip", "kens", "lass", "zips", "elva", "brat", "cosy", "teri",
				"hull", "spun", "russ", "pupa", "weed", "pulp", "main", "grim", "hone", "cord", "barf", "olav", "gaps",
				"rote", "wilt", "lars", "roll", "balm", "jana", "give", "eire", "faun", "suck", "kegs", "nita", "weer",
				"tush", "spry", "loge", "nays", "heir", "dope", "roar", "peep", "nags", "ates", "bane", "seas", "sign",
				"fred", "they", "lien", "kiev", "fops", "said", "lawn", "lind", "miff", "mass", "trig", "sins", "furl",
				"ruin", "sent", "cray", "maya", "clog", "puns", "silk", "axis", "grog", "jots", "dyer", "mope", "rand",
				"vend", "keen", "chou", "dose", "rain", "eats", "sped", "maui", "evan", "time", "todd", "skit", "lief",
				"sops", "outs", "moot", "faze", "biro", "gook", "fill", "oval", "skew", "veil", "born", "slob", "hyde",
				"twin", "eloy", "beat", "ergs", "sure", "kobe", "eggo", "hens", "jive", "flax", "mons", "dunk", "yest",
				"begs", "dial", "lodz", "burp", "pile", "much", "dock", "rene", "sago", "racy", "have", "yalu", "glow",
				"move", "peps", "hods", "kins", "salk", "hand", "cons", "dare", "myra", "sega", "type", "mari", "pelt",
				"hula", "gulf", "jugs", "flay", "fest", "spat", "toms", "zeno", "taps", "deny", "swag", "afro", "baud",
				"jabs", "smut", "egos", "lara", "toes", "song", "fray", "luis", "brut", "olen", "mere", "ruff", "slum",
				"glad", "buds", "silt", "rued", "gelt", "hive", "teem", "ides", "sink", "ands", "wisp", "omen", "lyre",
				"yuks", "curb", "loam", "darn", "liar", "pugs", "pane", "carl", "sang", "scar", "zeds", "claw", "berg",
				"hits", "mile", "lite", "khan", "erik", "slug", "loon", "dena", "ruse", "talk", "tusk", "gaol", "tads",
				"beds", "sock", "howe", "gave", "snob", "ahab", "part", "meir", "jell", "stir", "tels", "spit", "hash",
				"omit", "jinx", "lyra", "puck", "laue", "beep", "eros", "owed", "cede", "brew", "slue", "mitt", "jest",
				"lynx", "wads", "gena", "dank", "volt", "gray", "pony", "veld", "bask", "fens", "argo", "work", "taxi",
				"afar", "boon", "lube", "pass", "lazy", "mist", "blot", "mach", "poky", "rams", "sits", "rend", "dome",
				"pray", "duck", "hers", "lure", "keep", "gory", "chat", "runt", "jams", "lays", "posy", "bats", "hoff",
				"rock", "keri", "raul", "yves", "lama", "ramp", "vote", "jody", "pock", "gist", "sass", "iago", "coos",
				"rank", "lowe", "vows", "koch", "taco", "jinn", "juno", "rape", "band", "aces", "goal", "huck", "lila",
				"tuft", "swan", "blab", "leda", "gems", "hide", "tack", "porn", "scum", "frat", "plum", "duds", "shad",
				"arms", "pare", "chin", "gain", "knee", "foot", "line", "dove", "vera", "jays", "fund", "reno", "skid",
				"boys", "corn", "gwyn", "sash", "weld", "ruiz", "dior", "jess", "leaf", "pars", "cote", "zing", "scat",
				"nice", "dart", "only", "owls", "hike", "trey", "whys", "ding", "klan", "ross", "barb", "ants", "lean",
				"dopy", "hock", "tour", "grip", "aldo", "whim", "prom", "rear", "dins", "duff", "dell", "loch", "lava",
				"sung", "yank", "thar", "curl", "venn", "blow", "pomp", "heat", "trap", "dali", "nets", "seen", "gash",
				"twig", "dads", "emmy", "rhea", "navy", "haws", "mite", "bows", "alas", "ives", "play", "soon", "doll",
				"chum", "ajar", "foam", "call", "puke", "kris", "wily", "came", "ales", "reef", "raid", "diet", "prod",
				"prut", "loot", "soar", "coed", "celt", "seam", "dray", "lump", "jags", "nods", "sole", "kink", "peso",
				"howl", "cost", "tsar", "uric", "sore", "woes", "sewn", "sake", "cask", "caps", "burl", "tame", "bulk",
				"neva", "from", "meet", "webs", "spar", "fuck", "buoy", "wept", "west", "dual", "pica", "sold", "seed",
				"gads", "riff", "neck", "deed", "rudy", "drop", "vale", "flit", "romp", "peak", "jape", "jews", "fain",
				"dens", "hugo", "elba", "mink", "town", "clam", "feud", "fern", "dung", "newt", "mime", "deem", "inti",
				"gigs", "sosa", "lope", "lard", "cara", "smug", "lego", "flex", "doth", "paar", "moon", "wren", "tale",
				"kant", "eels", "muck", "toga", "zens", "lops", "duet", "coil", "gall", "teal", "glib", "muir", "ails",
				"boer", "them", "rake", "conn", "neat", "frog", "trip", "coma", "must", "mono", "lira", "craw", "sled",
				"wear", "toby", "reel", "hips", "nate", "pump", "mont", "died", "moss", "lair", "jibe", "oils", "pied",
				"hobs", "cads", "haze", "muse", "cogs", "figs", "cues", "roes", "whet", "boru", "cozy", "amos", "tans",
				"news", "hake", "cots", "boas", "tutu", "wavy", "pipe", "typo", "albs", "boom", "dyke", "wail", "woke",
				"ware", "rita", "fail", "slab", "owes", "jane", "rack", "hell", "lags", "mend", "mask", "hume", "wane",
				"acne", "team", "holy", "runs", "exes", "dole", "trim", "zola", "trek", "puma", "wacs", "veep", "yaps",
				"sums", "lush", "tubs", "most", "witt", "bong", "rule", "hear", "awry", "sots", "nils", "bash", "gasp",
				"inch", "pens", "fies", "juts", "pate", "vine", "zulu", "this", "bare", "veal", "josh", "reek", "ours",
				"cowl", "club", "farm", "teat", "coat", "dish", "fore", "weft", "exam", "vlad", "floe", "beak", "lane",
				"ella", "warp", "goth", "ming", "pits", "rent", "tito", "wish", "amps", "says", "hawk", "ways", "punk",
				"nark", "cagy", "east", "paul", "bose", "solo", "teed", "text", "hews", "snip", "lips", "emit", "orgy",
				"icon", "tuna", "soul", "kurd", "clod", "calk", "aunt", "bake", "copy", "acid", "duse", "kiln", "spec",
				"fans", "bani", "irma", "pads", "batu", "logo", "pack", "oder", "atop", "funk", "gide", "bede", "bibs",
				"taut", "guns", "dana", "puff", "lyme", "flat", "lake", "june", "sets", "gull", "hops", "earn", "clip",
				"fell", "kama", "seal", "diaz", "cite", "chew", "cuba", "bury", "yard", "bank", "byes", "apia", "cree",
				"nosh", "judo", "walk", "tape", "taro", "boot", "cods", "lade", "cong", "deft", "slim", "jeri", "rile",
				"park", "aeon", "fact", "slow", "goff", "cane", "earp", "tart", "does", "acts", "hope", "cant", "buts",
				"shin", "dude", "ergo", "mode", "gene", "lept", "chen", "beta", "eden", "pang", "saab", "fang", "whir",
				"cove", "perk", "fads", "rugs", "herb", "putt", "nous", "vane", "corm", "stay", "bids", "vela", "roof",
				"isms", "sics", "gone", "swum", "wiry", "cram", "rink", "pert", "heap", "sikh", "dais", "cell", "peel",
				"nuke", "buss", "rasp", "none", "slut", "bent", "dams", "serb", "dork", "bays", "kale", "cora", "wake",
				"welt", "rind", "trot", "sloe", "pity", "rout", "eves", "fats", "furs", "pogo", "beth", "hued", "edam",
				"iamb", "glee", "lute", "keel", "airy", "easy", "tire", "rube", "bogy", "sine", "chop", "rood", "elbe",
				"mike", "garb", "jill", "gaul", "chit", "dons", "bars", "ride", "beck", "toad", "make", "head", "suds",
				"pike", "snot", "swat", "peed", "same", "gaza", "lent", "gait", "gael", "elks", "hang", "nerf", "rosy",
				"shut", "glop", "pain", "dion", "deaf", "hero", "doer", "wost", "wage", "wash", "pats", "narc", "ions",
				"dice", "quay", "vied", "eons", "case", "pour", "urns", "reva", "rags", "aden", "bone", "rang", "aura",
				"iraq", "toot", "rome", "hals", "megs", "pond", "john", "yeps", "pawl", "warm", "bird", "tint", "jowl",
				"gibe", "come", "hold", "pail", "wipe", "bike", "rips", "eery", "kent", "hims", "inks", "fink", "mott",
				"ices", "macy", "serf", "keys", "tarp", "cops", "sods", "feet", "tear", "benz", "buys", "colo", "boil",
				"sews", "enos", "watt", "pull", "brag", "cork", "save", "mint", "feat", "jamb", "rubs", "roxy", "toys",
				"nosy", "yowl", "tamp", "lobs", "foul", "doom", "sown", "pigs", "hemp", "fame", "boor", "cube", "tops",
				"loco", "lads", "eyre", "alta", "aged", "flop", "pram", "lesa", "sawn", "plow", "aral", "load", "lied",
				"pled", "boob", "bert", "rows", "zits", "rick", "hint", "dido", "fist", "marc", "wuss", "node", "smog",
				"nora", "shim", "glut", "bale", "perl", "what", "tort", "meek", "brie", "bind", "cake", "psst", "dour",
				"jove", "tree", "chip", "stud", "thou", "mobs", "sows", "opts", "diva", "perm", "wise", "cuds", "sols",
				"alan", "mild", "pure", "gail", "wins", "offs", "nile", "yelp", "minn", "tors", "tran", "homy", "sadr",
				"erse", "nero", "scab", "finn", "mich", "turd", "then", "poem", "noun", "oxus", "brow", "door", "saws",
				"eben", "wart", "wand", "rosa", "left", "lina", "cabs", "rapt", "olin", "suet", "kalb", "mans", "dawn",
				"riel", "temp", "chug", "peal", "drew", "null", "hath", "many", "took", "fond", "gate", "sate", "leak",
				"zany", "vans", "mart", "hess", "home", "long", "dirk", "bile", "lace", "moog", "axes", "zone", "fork",
				"duct", "rico", "rife", "deep", "tiny", "hugh", "bilk", "waft", "swig", "pans", "with", "kern", "busy",
				"film", "lulu", "king", "lord", "veda", "tray", "legs", "soot", "ells", "wasp", "hunt", "earl", "ouch",
				"diem", "yell", "pegs", "blvd", "polk", "soda", "zorn", "liza", "slop", "week", "kill", "rusk", "eric",
				"sump", "haul", "rims", "crop", "blob", "face", "bins", "read", "care", "pele", "ritz", "beau", "golf",
				"drip", "dike", "stab", "jibs", "hove", "junk", "hoax", "tats", "fief", "quad", "peat", "ream", "hats",
				"root", "flak", "grit", "clap", "pugh", "bosh", "lock", "mute", "crow", "iced", "lisa", "bela", "fems",
				"oxes", "vies", "gybe", "huff", "bull", "cuss", "sunk", "pups", "fobs", "turf", "sect", "atom", "debt",
				"sane", "writ", "anon", "mayo", "aria", "seer", "thor", "brim", "gawk", "jack", "jazz", "menu", "yolk",
				"surf", "libs", "lets", "bans", "toil", "open", "aced", "poor", "mess", "wham", "fran", "gina", "dote",
				"love", "mood", "pale", "reps", "ines", "shot", "alar", "twit", "site", "dill", "yoga", "sear", "vamp",
				"abel", "lieu", "cuff", "orbs", "rose", "tank", "gape", "guam", "adar", "vole", "your", "dean", "dear",
				"hebe", "crab", "hump", "mole", "vase", "rode", "dash", "sera", "balk", "lela", "inca", "gaea", "bush",
				"loud", "pies", "aide", "blew", "mien", "side", "kerr", "ring", "tess", "prep", "rant", "lugs", "hobo",
				"joke", "odds", "yule", "aida", "true", "pone", "lode", "nona", "weep", "coda", "elmo", "skim", "wink",
				"bras", "pier", "bung", "pets", "tabs", "ryan", "jock", "body", "sofa", "joey", "zion", "mace", "kick",
				"vile", "leno", "bali", "fart", "that", "redo", "ills", "jogs", "pent", "drub", "slaw", "tide", "lena",
				"seep", "gyps", "wave", "amid", "fear", "ties", "flan", "wimp", "kali", "shun", "crap", "sage", "rune",
				"logs", "cain", "digs", "abut", "obit", "paps", "rids", "fair", "hack", "huns", "road", "caws", "curt",
				"jute", "fisk", "fowl", "duty", "holt", "miss", "rude", "vito", "baal", "ural", "mann", "mind", "belt",
				"clem", "last", "musk", "roam", "abed", "days", "bore", "fuze", "fall", "pict", "dump", "dies", "fiat",
				"vent", "pork", "eyed", "docs", "rive", "spas", "rope", "ariz", "tout", "game", "jump", "blur", "anti",
				"lisp", "turn", "sand", "food", "moos", "hoop", "saul", "arch", "fury", "rise", "diss", "hubs", "burs",
				"grid", "ilks", "suns", "flea", "soil", "lung", "want", "nola", "fins", "thud", "kidd", "juan", "heps",
				"nape", "rash", "burt", "bump", "tots", "brit", "mums", "bole", "shah", "tees", "skip", "limb", "umps",
				"ache", "arcs", "raft", "halo", "luce", "bahs", "leta", "conk", "duos", "siva", "went", "peek", "sulk",
				"reap", "free", "dubs", "lang", "toto", "hasp", "ball", "rats", "nair", "myst", "wang", "snug", "nash",
				"laos", "ante", "opal", "tina", "pore", "bite", "haas", "myth", "yugo", "foci", "dent", "bade", "pear",
				"mods", "auto", "shop", "etch", "lyly", "curs", "aron", "slew", "tyro", "sack", "wade", "clio", "gyro",
				"butt", "icky", "char", "itch", "halt", "gals", "yang", "tend", "pact", "bees", "suit", "puny", "hows",
				"nina", "brno", "oops", "lick", "sons", "kilo", "bust", "nome", "mona", "dull", "join", "hour", "papa",
				"stag", "bern", "wove", "lull", "slip", "laze", "roil", "alto", "bath", "buck", "alma", "anus", "evil",
				"dumb", "oreo", "rare", "near", "cure", "isis", "hill", "kyle", "pace", "comb", "nits", "flip", "clop",
				"mort", "thea", "wall", "kiel", "judd", "coop", "dave", "very", "amie", "blah", "flub", "talc", "bold",
				"fogy", "idea", "prof", "horn", "shoo", "aped", "pins", "helm", "wees", "beer", "womb", "clue", "alba",
				"aloe", "fine", "bard", "limo", "shaw", "pint", "swim", "dust", "indy", "hale", "cats", "troy", "wens",
				"luke", "vern", "deli", "both", "brig", "daub", "sara", "sued", "bier", "noel", "olga", "dupe", "look",
				"pisa", "knox", "murk", "dame", "matt", "gold", "jame", "toge", "luck", "peck", "tass", "calf", "pill",
				"wore", "wadi", "thur", "parr", "maul", "tzar", "ones", "lees", "dark", "fake", "bast", "zoom", "here",
				"moro", "wine", "bums", "cows", "jean", "palm", "fume", "plop", "help", "tuba", "leap", "cans", "back",
				"avid", "lice", "lust", "polo", "dory", "stew", "kate", "rama", "coke", "bled", "mugs", "ajax", "arts",
				"drug", "pena", "cody", "hole", "sean", "deck", "guts", "kong", "bate", "pitt", "como", "lyle", "siam",
				"rook", "baby", "jigs", "bret", "bark", "lori", "reba", "sups", "made", "buzz", "gnaw", "alps", "clay",
				"post", "viol", "dina", "card", "lana", "doff", "yups", "tons", "live", "kids", "pair", "yawl", "name",
				"oven", "sirs", "gyms", "prig", "down", "leos", "noon", "nibs", "cook", "safe", "cobb", "raja", "awes",
				"sari", "nerd", "fold", "lots", "pete", "deal", "bias", "zeal", "girl", "rage", "cool", "gout", "whey",
				"soak", "thaw", "bear", "wing", "nagy", "well", "oink", "sven", "kurt", "etna", "held", "wood", "high",
				"feta", "twee", "ford", "cave", "knot", "tory", "ibis", "yaks", "vets", "foxy", "sank", "cone", "pius",
				"tall", "seem", "wool", "flap", "gird", "lore", "coot", "mewl", "sere", "real", "puts", "sell", "nuts",
				"foil", "lilt", "saga", "heft", "dyed", "goat", "spew", "daze", "frye", "adds", "glen", "tojo", "pixy",
				"gobi", "stop", "tile", "hiss", "shed", "hahn", "baku", "ahas", "sill", "swap", "also", "carr", "manx",
				"lime", "debs", "moat", "eked", "bola", "pods", "coon", "lacy", "tube", "minx", "buff", "pres", "clew",
				"gaff", "flee", "burn", "whom", "cola", "fret", "purl", "wick", "wigs", "donn", "guys", "toni", "oxen",
				"wite", "vial", "spam", "huts", "vats", "lima", "core", "eula", "thad", "peon", "erie", "oats", "boyd",
				"cued", "olaf", "tams", "secs", "urey", "wile", "penn", "bred", "rill", "vary", "sues", "mail", "feds",
				"aves", "code", "beam", "reed", "neil", "hark", "pols", "gris", "gods", "mesa", "test", "coup", "heed",
				"dora", "hied", "tune", "doze", "pews", "oaks", "bloc", "tips", "maid", "goof", "four", "woof", "silo",
				"bray", "zest", "kiss", "yong", "file", "hilt", "iris", "tuns", "lily", "ears", "pant", "jury", "taft",
				"data", "gild", "pick", "kook", "colt", "bohr", "anal", "asps", "babe", "bach", "mash", "biko", "bowl",
				"huey", "jilt", "goes", "guff", "bend", "nike", "tami", "gosh", "tike", "gees", "urge", "path", "bony",
				"jude", "lynn", "lois", "teas", "dunn", "elul", "bonn", "moms", "bugs", "slay", "yeah", "loan", "hulk",
				"lows", "damn", "nell", "jung", "avis", "mane", "waco", "loin", "knob", "tyke", "anna", "hire", "luau",
				"tidy", "nuns", "pots", "quid", "exec", "hans", "hera", "hush", "shag", "scot", "moan", "wald", "ursa",
				"lorn", "hunk", "loft", "yore", "alum", "mows", "slog", "emma", "spud", "rice", "worn", "erma", "need",
				"bags", "lark", "kirk", "pooh", "dyes", "area", "dime", "luvs", "foch", "refs", "cast", "alit", "tugs",
				"even", "role", "toed", "caph", "nigh", "sony", "bide", "robs", "folk", "daft", "past", "blue", "flaw",
				"sana", "fits", "barr", "riot", "dots", "lamp", "cock", "fibs", "harp", "tent", "hate", "mali", "togs",
				"gear", "tues", "bass", "pros", "numb", "emus", "hare", "fate", "wife", "mean", "pink", "dune", "ares",
				"dine", "oily", "tony", "czar", "spay", "push", "glum", "till", "moth", "glue", "dive", "scad", "pops",
				"woks", "andy", "leah", "cusp", "hair", "alex", "vibe", "bulb", "boll", "firm", "joys", "tara", "cole",
				"levy", "owen", "chow", "rump", "jail", "lapp", "beet", "slap", "kith", "more", "maps", "bond", "hick",
				"opus", "rust", "wist", "shat", "phil", "snow", "lott", "lora", "cary", "mote", "rift", "oust", "klee",
				"goad", "pith", "heep", "lupe", "ivan", "mimi", "bald", "fuse", "cuts", "lens", "leer", "eyry", "know",
				"razz", "tare", "pals", "geek", "greg", "teen", "clef", "wags", "weal", "each", "haft", "nova", "waif",
				"rate", "katy", "yale", "dale", "leas", "axum", "quiz", "pawn", "fend", "capt", "laws", "city", "chad",
				"coal", "nail", "zaps", "sort", "loci", "less", "spur", "note", "foes", "fags", "gulp", "snap", "bogs",
				"wrap", "dane", "melt", "ease", "felt", "shea", "calm", "star", "swam", "aery", "year", "plan", "odin",
				"curd", "mira", "mops", "shit", "davy", "apes", "inky", "hues", "lome", "bits", "vila", "show", "best",
				"mice", "gins", "next", "roan", "ymir", "mars", "oman", "wild", "heal", "plus", "erin", "rave", "robe",
				"fast", "hutu", "aver", "jodi", "alms", "yams", "zero", "revs", "wean", "chic", "self", "jeep", "jobs",
				"waxy", "duel", "seek", "spot", "raps", "pimp", "adan", "slam", "tool", "morn", "futz", "ewes", "errs",
				"knit", "rung", "kans", "muff", "huhs", "tows", "lest", "meal", "azov", "gnus", "agar", "sips", "sway",
				"otis", "tone", "tate", "epic", "trio", "tics", "fade", "lear", "owns", "robt", "weds", "five", "lyon",
				"terr", "arno", "mama", "grey", "disk", "sept", "sire", "bart", "saps", "whoa", "turk", "stow", "pyle",
				"joni", "zinc", "negs", "task", "leif", "ribs", "malt", "nine", "bunt", "grin", "dona", "nope", "hams",
				"some", "molt", "smit", "sacs", "joan", "slav", "lady", "base", "heck", "list", "take", "herd", "will",
				"nubs", "burg", "hugs", "peru", "coif", "zoos", "nick", "idol", "levi", "grub", "roth", "adam", "elma",
				"tags", "tote", "yaws", "cali", "mete", "lula", "cubs", "prim", "luna", "jolt", "span", "pita", "dodo",
				"puss", "deer", "term", "dolt", "goon", "gary", "yarn", "aims", "just", "rena", "tine", "cyst", "meld",
				"loki", "wong", "were", "hung", "maze", "arid", "cars", "wolf", "marx", "faye", "eave", "raga", "flow",
				"neal", "lone", "anne", "cage", "tied", "tilt", "soto", "opel", "date", "buns", "dorm", "kane", "akin",
				"ewer", "drab", "thai", "jeer", "grad", "berm", "rods", "saki", "grus", "vast", "late", "lint", "mule",
				"risk", "labs", "snit", "gala", "find", "spin", "ired", "slot", "oafs", "lies", "mews", "wino", "milk",
				"bout", "onus", "tram", "jaws", "peas", "cleo", "seat", "gums", "cold", "vang", "dewy", "hood", "rush",
				"mack", "yuan", "odes", "boos", "jami", "mare", "plot", "swab", "borg", "hays", "form", "mesh", "mani",
				"fife", "good", "gram", "lion", "myna", "moor", "skin", "posh", "burr", "rime", "done", "ruts", "pays",
				"stem", "ting", "arty", "slag", "iron", "ayes", "stub", "oral", "gets", "chid", "yens", "snub", "ages",
				"wide", "bail", "verb", "lamb", "bomb", "army", "yoke", "gels", "tits", "bork", "mils", "nary", "barn",
				"hype", "odom", "avon", "hewn", "rios", "cams", "tact", "boss", "oleo", "duke", "eris", "gwen", "elms",
				"deon", "sims", "quit", "nest", "font", "dues", "yeas", "zeta", "bevy", "gent", "torn", "cups", "worm",
				"baum", "axon", "purr", "vise", "grew", "govs", "meat", "chef", "rest", "lame" };

		// String beginWord = "hot";
		// String endWord = "dog";
		// String[] words = {"hot","dog"};
		// String beginWord = "a";
		// String endWord = "c";
		// String[] words = {"a","b","c"};
		// String beginWord = "lost";
		// String endWord = "miss";
		// String[] words = {"most","mist","miss","lost","fist","fish"};
		List<String> wordList = Arrays.asList(words);
		day.findLadders(beginWord, endWord, wordList);
		// System.out.println(day.oneDiff("aaa", "aaa"));

		List<List<String>> ans = new ArrayList<>();
		System.out.println(ans.isEmpty());
	}
}
