package com.daimens.algorithm.june;

/**
 * 
 * @author DemonSong
 * 
 *         165. Compare Version Numbers
 * 
 *         Compare two version numbers version1 and version2. If version1 >
 *         version2 return 1, if version1 < version2 return -1, otherwise return
 *         0.
 * 
 *         You may assume that the version strings are non-empty and contain
 *         only digits and the . character. The . character does not represent a
 *         decimal point and is used to separate number sequences. For instance,
 *         2.5 is not "two and a half" or "half way to version three", it is the
 *         fifth second-level revision of the second first-level revision.
 * 
 *         Here is an example of version numbers ordering:
 * 
 *         0.1 < 1.1 < 1.2 < 13.37
 *
 */
public class SolutionDay12_L0165 {
	
//	public int compareVersion(String version1, String version2) {
//		String[] v1 = version1.split("\\.");
//		String[] v2 = version2.split("\\.");
//		int min = Math.min(v1.length, v2.length);
//		int i = 0;
//		while (i < min && Integer.parseInt(v1[i]) == Integer.parseInt(v2[i])) i++;
//		if (i == min){
//			if (v1.length > v2.length){
//				for (int j = i; j < v1.length; ++j){
//					if (Integer.parseInt(v1[j]) != 0) return 1;
//				}
//			}
//			else if (v1.length < v2.length){
//				for (int j = i; j < v2.length; ++j){
//					if (Integer.parseInt(v2[j]) != 0) return -1;
//				}
//			}
//			return 0;
//		}
//        return Integer.parseInt(v1[i]) > Integer.parseInt(v2[i]) ? 1 : -1;
//    }
	
	public int compareVersion(String version1, String version2) {
		String[] v1 = version1.split("\\.");
		String[] v2 = version2.split("\\.");
		int max = Math.max(v1.length, v2.length);
		for (int i = 0; i < max; ++i){
			int num1 = i < v1.length ? Integer.parseInt(v1[i]) : 0;
			int num2 = i < v2.length ? Integer.parseInt(v2[i]) : 0;
			if (num1 < num2) return -1;
			if (num1 > num2) return 1;
		}
		return 0;
	}
	
	public static void main(String[] args) {
		SolutionDay12_L0165 day = new SolutionDay12_L0165();
		System.out.println(day.compareVersion("1.0", "1"));
	}
}
