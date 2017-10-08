package com.daimens.algorithm.october;

import java.util.HashMap;
import java.util.Map;

public class SolutionDay08_L0502 {
	
	static final int INF = 0x3f3f3f3f;
	Map<String, Integer> mem = new HashMap<>();
	
	int f(int[] cost, int k, String hash, int n) {
		if (hash.isEmpty()) return 0;
		else if (k == n) {
			if (hash.isEmpty()) return 0;
			return INF;
		}
		else {
			if (mem.containsKey(hash + "," + k)) return mem.get(hash + "," + k);
			int ans = INF;
			int[] tar = preprocess(hash);
			for (int i = 0; i <= cost[k]; ++i) {
				String hh = construct(tar, i, k);
				ans = Math.min(ans, f(cost, k + 1, hh, n) + i);
			}
			mem.put(hash + "," + k, ans);
			return ans;
		}
	}
	
	
	
	String construct(int[] tar, int times, int k) {
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 32; ++i) {
			if (tar[i] > 0) {
				for (int j = 1; j <= Math.max(0, tar[i] - times * sticker_map[k][i]); ++j){
					sb.append((char)(i + 'a'));
				}
			}
		}
		return sb.toString();
	}
	
	boolean valid(int[] tar) {
		for (int i = 0; i < 32; ++i) {
			if (tar[i] > 0) return false;
		}
		return true;
	}
	
	int[][] sticker_map;
    public int minStickers(String[] stickers, String target) {
    	if (judge(stickers, target)) return -1;
    	int[] tar = preprocess(target);
    	int n = stickers.length;
    	int[] cost = new int[n];
    	sticker_map = new int[n][32];
    	for (int i = 0; i < n; ++i) {
    		sticker_map[i] = preprocess(stickers[i]);
    		cost[i] = cost(stickers[i], tar);
    	}
    	return f(cost, 0, target, n);
    }
    
    boolean judge(String[] stickers, String target) {
    	int[] map = new int[32];
    	for (char c : target.toCharArray()) {
    		if (map[c - 'a'] == 0)
    			map[c - 'a']++;
    	}
    	for (String s : stickers) {
    		for (char c : s.toCharArray()) map[c - 'a'] --;
    	}
    	
    	for (int i = 0; i < 32; ++i) {
    		if (map[i] >= 1) return true;
    	}
    	return false;
    }
    
    int[] preprocess(String target) {
    	int[] map = new int[32];
    	for (char c : target.toCharArray()) map[c - 'a']++;
    	return map;
    }
    
    int cost(String stickers, int[] tar) {
    	int max = 0;
    	int[] sticker = preprocess(stickers);
    	for (int i = 0; i < 32; ++i) {
    		if (tar[i] > 0) {
    			if (sticker[i] == 0) continue;
    			max = Math.max(max, (int)Math.ceil((tar[i]  * 1.0 / sticker[i])));
    		}
    	}
    	return max;
    }
	
	public static void main(String[] args) {
		SolutionDay08_L0502 day = new SolutionDay08_L0502();
//		String[] stickers = {"divide","danger","student","share","feet","say","expect","chair","special","blue","differ","thank","doctor","top","there","had","ice","mark","note","equate","basic","so","hope","happy","draw","evening","star","shall","thousand","mother","quite","letter","atom","baby","such","trouble","stand","day","room","third","level","salt","thing","shore","truck","block","time","fresh","dream","talk"};
//		String target = "distantcollect";
		String[] stickers = {"with", "example", "science"};
		String target = "thehat";
		System.out.println(day.minStickers(stickers, target));
	}
}

//static final int INF = 0x3f3f3f3f;
//
//Map<String, Integer> mem = new HashMap<>();
//int f(String hash, int n) {
//	if (hash.isEmpty()) return 0;
//	if (mem.containsKey(hash)) return mem.get(hash);
//	
//	int ans = INF;
//	for (int i = 0; i < n; ++i) {
//		
//		int[] tar = preprocess(hash);
//		for (int j = 0; j < 32; ++j) {
//			if (sticker_map[i][j] > 0) {
//				tar[j] -= sticker_map[i][j];
//			}
//		}
//		
//		StringBuilder sb = new StringBuilder();
//		for (int j = 0; j < 32; ++j) {
//			if (tar[j] > 0) {
//				for (int k = 1; k <= tar[j]; ++k) {
//					sb.append((char)(j + 'a'));
//				}
//			}
//		}
//		
//		if (sb.toString().length() != hash.length()) {
//			ans = Math.min(ans, f(sb.toString(), n) + 1);
//		}
//	}
//	mem.put(hash, ans);
//	return ans;
//}
