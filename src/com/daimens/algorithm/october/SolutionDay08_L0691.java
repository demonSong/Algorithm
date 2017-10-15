package com.daimens.algorithm.october;

public class SolutionDay08_L0691 {
	
//	static final int INF = 0x3f3f3f3f;
//	Map<String, Integer> mem = new HashMap<>();
//	
//	int f(String target, int n) {
//		if (mem.containsKey(target)) return mem.get(target);
//		
//		int[] tar = preprocess(target);
//		int ans = INF;
//		for (int i = 0; i < n; ++i) {
//    
//            StringBuilder sb = new StringBuilder();
//			for (int j = 0; j < 32; ++j) {
//				if (tar[j] > 0) {
//					for (int k = 1; k <= Math.max(0, tar[j] - sticker_map[i][j]); ++k) {
//						sb.append((char)(j + 'a'));
//					}
//				}
//			}
//			
//			String sub = sb.toString();
//			
//			if (sub.length() != target.length()) {
//				ans = Math.min(ans, f(sub, n) + 1);
//			}
//		}
//		mem.put(target, ans);
//		return ans;
//	}
//	
//	int[][] sticker_map;
//    public int minStickers(String[] stickers, String target) {
//    	if (judge(stickers, target)) return -1;
//    	int n = stickers.length;
//    	sticker_map = new int[n][32];
//    	for (int i = 0; i < n; ++i) {
//    		sticker_map[i] = preprocess(stickers[i]);
//    	}
//    	mem.put("", 0);
//    	return f(target, n);
//    }
//    
//    boolean judge(String[] stickers, String target) {
//    	int[] map = new int[32];
//    	for (char c : target.toCharArray()) {
//    		if (map[c - 'a'] == 0)
//    			map[c - 'a']++;
//    	}
//    	for (String s : stickers) {
//    		for (char c : s.toCharArray()) map[c - 'a'] --;
//    	}
//    	
//    	for (int i = 0; i < 32; ++i) {
//    		if (map[i] >= 1) return true;
//    	}
//    	return false;
//    }
//    
//    int[] preprocess(String target) {
//    	int[] map = new int[32];
//    	for (char c : target.toCharArray()) map[c - 'a']++;
//    	return map;
//    }
    
//	static final int INF = 0x3f3f3f3f;
//	Map<String, Integer> mem = new HashMap<>();
//	
//	int f(int[] cost, int k, String hash, int n) {
//		if (hash.isEmpty()) return 0;
//		else if (k == n) {
//			if (hash.isEmpty()) return 0;
//			return INF;
//		}
//		else {
//			if (mem.containsKey(hash + "," + k)) return mem.get(hash + "," + k);
//			int ans = INF;
//			int[] tar = preprocess(hash);
//			for (int i = 0; i <= cost[k]; ++i) {
//				String hh = construct(tar, i, k);
//				ans = Math.min(ans, f(cost, k + 1, hh, n) + i);
//			}
//			mem.put(hash + "," + k, ans);
//			return ans;
//		}
//	}
//	
//	
//	
//	String construct(int[] tar, int times, int k) {
//		
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < 32; ++i) {
//			if (tar[i] > 0) {
//				for (int j = 1; j <= Math.max(0, tar[i] - times * sticker_map[k][i]); ++j){
//					sb.append((char)(i + 'a'));
//				}
//			}
//		}
//		return sb.toString();
//	}
//	
//	boolean valid(int[] tar) {
//		for (int i = 0; i < 32; ++i) {
//			if (tar[i] > 0) return false;
//		}
//		return true;
//	}
//	
//	int[][] sticker_map;
//    public int minStickers(String[] stickers, String target) {
//    	if (judge(stickers, target)) return -1;
//    	int[] tar = preprocess(target);
//    	int n = stickers.length;
//    	int[] cost = new int[n];
//    	sticker_map = new int[n][32];
//    	for (int i = 0; i < n; ++i) {
//    		sticker_map[i] = preprocess(stickers[i]);
//    		cost[i] = cost(stickers[i], tar);
//    	}
//    	return f(cost, 0, target, n);
//    }
//    
//    boolean judge(String[] stickers, String target) {
//    	int[] map = new int[32];
//    	for (char c : target.toCharArray()) {
//    		if (map[c - 'a'] == 0)
//    			map[c - 'a']++;
//    	}
//    	for (String s : stickers) {
//    		for (char c : s.toCharArray()) map[c - 'a'] --;
//    	}
//    	
//    	for (int i = 0; i < 32; ++i) {
//    		if (map[i] >= 1) return true;
//    	}
//    	return false;
//    }
//    
//    int[] preprocess(String target) {
//    	int[] map = new int[32];
//    	for (char c : target.toCharArray()) map[c - 'a']++;
//    	return map;
//    }
//    
//    int cost(String stickers, int[] tar) {
//    	int max = 0;
//    	int[] sticker = preprocess(stickers);
//    	for (int i = 0; i < 32; ++i) {
//    		if (tar[i] > 0) {
//    			if (sticker[i] == 0) continue;
//    			max = Math.max(max, (int)Math.ceil((tar[i]  * 1.0 / sticker[i])));
//    		}
//    	}
//    	return max;
//    }
	
	static final int INF = 0x3f3f3f3f;
	
    public int minStickers(String[] stickers, String target) {
    	int N = target.length();
    	int[] dp = new int[1 << N];
    	
    	for (int i = 1; i < 1 << N; ++i) dp[i] = -1;
    	
    	for (int state = 0; state < 1 << N; ++state) {
    		if (dp[state] == -1) continue;
    		for (String sticker : stickers) {
    			int now = state;
    			for (char c : sticker.toCharArray()) {
    				for (int i = 0; i < N; ++i) {
    					if (((now >> i) & 1) == 1) continue;
    					if (c == target.charAt(i)) {
    						now |= 1 << i;
    						break;
    					}
    				}
    			}
    			
    			update(dp, now, state);
    		}
    	}
    	
    	return dp[(1 << N) - 1];
    }
    
    public void update(int[] dp, int now, int state) {
    	if (dp[now] == -1 || dp[now] > dp[state] + 1) {
    		dp[now] = dp[state] + 1;
    	}
    }
    
	public static void main(String[] args) {
		SolutionDay08_L0691 day = new SolutionDay08_L0691();
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
