package com.daimens.algorithm.june;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author DemonSong
 * 
 *         68.Text Justification
 * 
 *         Given an array of words and a length L, format the text such that
 *         each line has exactly L characters and is fully (left and right)
 *         justified.
 * 
 *         You should pack your words in a greedy approach; that is, pack as
 *         many words as you can in each line. Pad extra spaces ' ' when
 *         necessary so that each line has exactly L characters.
 * 
 *         Extra spaces between words should be distributed as evenly as
 *         possible. If the number of spaces on a line do not divide evenly
 *         between words, the empty slots on the left will be assigned more
 *         spaces than the slots on the right.
 * 
 *         For the last line of text, it should be left justified and no extra
 *         space is inserted between words.
 * 
 *         For example, words: ["This", "is", "an", "example", "of", "text",
 *         "justification."] L: 16.
 * 
 *         Return the formatted lines as: [ "This is an", "example of text",
 *         "justification. " ] Note: Each word is guaranteed not to exceed L in
 *         length.
 *
 */
public class SolutionDay12_L0068 {
	
//	public List<String> fullJustify(String[] words, int maxWidth) {
//		List<String> ans = new ArrayList<>();
//		if (words.length == 0 || maxWidth == 0){
//			ans.add("");
//			return ans;
//		}
//		String res = greedy(words, maxWidth);
//		while (!res.isEmpty()){
//			ans.add(res);
//			res = greedy(words, maxWidth);
//		}
//		return ans;
//    }
//	
//	int pos = 0;
//	private String greedy(String[] words, int maxWidth){
//		if (pos == words.length) return "";
//		String ans = words[pos];
//		int cnt = ans.length();
//		for (int i = pos + 1; i < words.length; ++i){
//			if (cnt + words[i].length() + 1 <= maxWidth){
//				ans += " " + words[i];
//				cnt = ans.length();
//				pos = i + 1;
//			}
//			else{
//				pos = i;
//				int space = maxWidth - cnt;
//				String[] all = ans.split(" ");
//				int len = all.length - 1;
//				
//				StringBuilder sb = new StringBuilder();
//				if (len == 0){
//					sb.append(all[0]);
//					for (int k = 0; k < space; ++k){
//						sb.append(" ");
//					}
//					return sb.toString();
//				}
//				
//				if (space % len == 0){
//					for (int k = 0; k < all.length; ++k){
//						sb.append(all[k] + " ");
//						for (int l = 0; l < space / len; ++l){
//							sb.append(" ");
//						}
//					}
//					return sb.toString().trim();
//				}
//				else{
//					int re = space % len;
//					int sp = space / len;
//					for (int k = 0; k < all.length; ++k){
//						sb.append(all[k] + " " + ((re != 0) ? " " : ""));
//						re = Math.max(--re,0);
//						for (int l = 0; l < sp; ++l){
//							sb.append(" ");
//						}
//					}
//					return sb.toString().trim();
//				}
//			}
//		}
//		
//		pos = words.length;
//		int space = maxWidth - cnt;
//		String[] all = ans.split(" ");
//		int len = all.length - 1;
//		StringBuilder sb = new StringBuilder();
//		if (len == 0){
//			sb.append(all[0]);
//			for (int k = 0; k < space; ++k){
//				sb.append(" ");
//			}
//			return sb.toString();
//		}
//		
//		sb = new StringBuilder(ans);
//		for (int k = 0; k < space; ++k){
//			sb.append(" ");
//		}
//		return sb.toString();
//	}
	
	public List<String> fullJustify(String[] words, int maxWidth) {
		List<String> ans = new ArrayList<>();
		int pos = 0;
		while (pos < words.length){
			int cnt = words[pos].length();
			int i = pos + 1;
			while (i < words.length && cnt + words[i].length() + 1 <= maxWidth){
				cnt += words[i++].length() + 1;
			}
			int space = maxWidth - cnt;
			int len = i - pos - 1;
			StringBuilder sb = new StringBuilder();
			if (len == 0 || i == words.length){
				sb = new StringBuilder();
				for (int k = pos; k < i; ++k){
				    sb.append(words[k] + " ");
				}
				sb.deleteCharAt(sb.length()-1);
				for (int k = 0; k < space; ++k){
					sb.append(" ");
				}
				ans.add(sb.toString());
			}
			else{
				sb = new StringBuilder();
				int re = space % len;
				int sp = space / len;
				for (int k = pos; k < i; ++k){
					sb.append(words[k] + " " + ((re != 0) ? " " : ""));
					re = Math.max(--re,0);
					for (int l = 0; l < sp; ++l){
						sb.append(" ");
					}
				}
				ans.add(sb.toString().trim());
			}
			pos = i;
		}
		return ans;
    }
	
//	int pos = 0;
//	private String greedy(String[] words, int maxWidth){
//		if (pos == words.length) return "";
//		
//	}
	

	public static void main(String[] args) {
		SolutionDay12_L0068 day = new SolutionDay12_L0068();
//		String[] words = {"This", "is", "an", "example", "of", "text","justification."};
//		String[] words = {"A", "b", "g", "d", "e"};
		String[] words = {"My","momma","always","said,","\"Life","was","like","a","box","of","chocolates.","You","never","know","what","you're","gonna","get."};
		System.out.println(day.fullJustify(words, 20));
	}
}
