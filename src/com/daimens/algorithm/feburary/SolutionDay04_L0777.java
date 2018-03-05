package com.daimens.algorithm.feburary;

public class SolutionDay04_L0777 {
	
//    public boolean canTransform(String start, String end) {
//    	noMake = new HashSet<>();
//    	return dfs(start, end);
//    }
//    
//    Set<String> noMake;
//    boolean dfs(String start, String end) {
//    	if (noMake.contains(start)) return false;
//    	if (start.equals(end)) return true;
//    	int n1 = start.length();
//    	int n2 = end.length();
//    	if (n1 != n2) return false;
//    	
//    	int i = 0;
//    	while (i < n1 && start.charAt(i) == end.charAt(i)) i ++;
//    	i = Math.max(0, i - 1);
//    	
//    	start = start.substring(i);
//    	end = end.substring(i);
//    	char[] aux = start.toCharArray();
//    	if (start.length() >= 1 && aux[0] == 'L') {
//    		if (aux[0] != end.charAt(0)) { 
//    			noMake.add(start);
//    			return false;
//    		}
//    	}
//    	
//    	for (int j = 0; j < start.length() - 1; ++j) {
//    		if(j + 1 < start.length() && canReplace(aux[j], aux[j + 1])) {
//    			//change
//    			char tmp = aux[j];
//				aux[j] = aux[j + 1];
//				aux[j + 1] = tmp; 
//				if (canTransform(new String(aux), end)) return true;
//				tmp = aux[j];
//				aux[j] = aux[j + 1];
//				aux[j + 1] = tmp;
//    		}
//    	}
//    	noMake.add(start);
//    	return false;
//    }
	
	 public boolean canTransform(String start, String end) {
		 if (!start.replace("X", "").equals(end.replace("X", ""))) return false;
		 char[] s = start.toCharArray();
		 char[] e = end.toCharArray();
		 int n = s.length;
		 int i = 0, j = 0;
		 while (i < n && j < n) {
			 while (j < n && e[j] == 'X') j ++;
			 while (i < n && s[i] == 'X') i ++;
			 if (i == n || j == n) break;
			 if (s[i] == 'R' && i > j) return false;
			 if (s[i] == 'L' && i < j) return false;
			 i ++;
			 j ++;
		 }
		 return true;
	 }

    public static void main(String[] args) {
		SolutionDay04_L0777 day = new SolutionDay04_L0777();
//		System.out.println(day.canTransform("XXXRXXXXRXXLXRXXXXXXRXXXXLXXXX", "XXXXXXXRRLXXXXXXXXXRRLXXXXXXXX"));
//		System.out.println(day.canTransform("XLLR", "LXLX"));
//		System.out.println(day.canTransform("XRXXLXLXXXXRXXXXLXXL", "XXRXLXXLXXRLXXXLXXXX")); // false
		System.out.println(day.canTransform("XXXXXLXXXX", "LXXXXXXXXX")); // true
//		System.out.println(day.canTransform("XXRXXLXXXX", "XXXXRXXLXX")); // true
	}

}
