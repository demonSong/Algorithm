package com.daimens.algorithm.june;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author DemonSong
 * 
 *         616. Add Bold Tag in String
 * 
 *         Given a string s and a list of strings dict, you need to add a closed
 *         pair of bold tag <b> and </b> to wrap the substrings in s that exist
 *         in dict. If two such substrings overlap, you need to wrap them
 *         together by only one pair of closed bold tag. Also, if two substrings
 *         wrapped by bold tags are consecutive, you need to combine them.
 * 
 *         Example 1: Input: s = "abcxyz123" dict = ["abc","123"] Output:
 *         "<b>abc</b>xyz<b>123</b>" Example 2: Input: s = "aaabbcc" dict =
 *         ["aaa","aab","bc"] Output: "<b>aaabbc</b>c" Note: The given dict
 *         won't contain duplicates, and its length won't exceed 100. All the
 *         strings in input have length in range [1, 1000].
 *
 */
public class SolutionDay11_L0616 {
	
	class Interval{
		int i;
		int j;
		public Interval(int i, int j){
			this.i = i;
			this.j = j;
		}
		
		@Override
		public String toString() {
			return "["+i+" ,"+j+"]";
		}
	}
	public String addBoldTag(String s, String[] dict) {
		List<Interval> inters = new ArrayList<>();
		for (int i = 0; i < dict.length; ++i){
			List<Interval> it = findWord(s, dict[i]);
			if (!it.isEmpty()){
				inters.addAll(it);
			}
		}
		if (inters.isEmpty()) return s;
		Collections.sort(inters, (a,b) -> (a.i != b.i ? a.i - b.i : a.j - b.j));
		List<Interval> mergedInterval = merge(inters);
		StringBuilder sb = new StringBuilder();
		int lst = 0;
		for (Interval it : mergedInterval){
			int i = it.i;
			int j = it.j;
			sb.append(s.substring(lst,i));
			sb.append("<b>");
			sb.append(s.substring(i,j+1));
			sb.append("</b>");
			lst = j + 1;
		}
		sb.append(s.substring(lst,s.length()));
		return sb.toString();
	}
	
	private List<Interval> merge(List<Interval> intervals){
		List<Interval> ans = new ArrayList<>();
		Interval merge = intervals.get(0);
		int start = merge.i;
		int end = merge.j;
		for (Interval it : intervals){
			if (it.i > end + 1){
				ans.add(new Interval(start, end));
				start = it.i;
				end = it.j;
			}else{
				end = Math.max(end, it.j);
			}
		}
		ans.add(new Interval(start, end));
		return ans;
	}
	
	private List<Interval> findWord(String s, String f){
		List<Interval> ans = new ArrayList<>();
		char[] cs = s.toCharArray();
		char[] fs = f.toCharArray();
		int j = -1;
		for (int k = 0; k < cs.length; ++k){
			int l = 0;
			while (l < fs.length && k < cs.length && cs[k] == fs[l]){
				k++;
				l++;
			}
			j = k - 1;
			if (l == fs.length) {
				ans.add(new Interval(j - fs.length + 1,j));
			}
			k -= l;
		}
		return ans;
	}

	public static void main(String[] args) {
		SolutionDay11_L0616 day = new SolutionDay11_L0616();
		List<Interval> interval = day.findWord("aaabbcc", "a");
		System.out.println(interval);
		String[] dict = {"a","b","c"};
		System.out.println(day.addBoldTag("aaabbcc", dict));
	}

}
