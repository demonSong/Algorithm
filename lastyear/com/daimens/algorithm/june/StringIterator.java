package com.daimens.algorithm.june;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.html.CSS;

/**
 * 
 * @author DemonSong
 * 
 *         604. Design Compressed String Iterator
 * 
 *         Design and implement a data structure for a compressed string
 *         iterator. It should support the following operations: next and
 *         hasNext.
 * 
 *         The given compressed string will be in the form of each letter
 *         followed by a positive integer representing the number of this letter
 *         existing in the original uncompressed string.
 * 
 *         next() - if the original string still has uncompressed characters,
 *         return the next letter; Otherwise return a white space. hasNext() -
 *         Judge whether there is any letter needs to be uncompressed.
 * 
 *         Note: Please remember to RESET your class variables declared in
 *         StringIterator, as static/class variables are persisted across
 *         multiple test cases. Please see here for more details.
 *
 */
//public class StringIterator {
//
//	List<Integer> words = new ArrayList<>();
//	Map<Integer, Integer> map = new HashMap<>();
//	char[] cs;
//
//	public StringIterator(String compressedString) {
//		int n = compressedString.length();
//		cs = compressedString.toCharArray();
//		for (int i = 0; i < n; ++i) {
//			if (Character.isAlphabetic(cs[i])) {
//				words.add(i);
//			} else {
//				int num = 0;
//				int lst = i - 1;
//				while (i < n && Character.isDigit(cs[i])) {
//					num = num * 10 + cs[i++] - '0';
//				}
//				map.put(lst, num);
//				i--;
//			}
//		}
//	}
//
//	public char next() {
//		if (!hasNext())
//			return ' ';
//		int idx = words.get(0);
//		char c = cs[words.get(0)];
//		int cnt = map.get(idx);
//		if (cnt == 1) {
//			words.remove(0);
//			map.remove(idx);
//		} else {
//			map.put(idx, cnt - 1);
//		}
//		return c;
//	}
//
//	public boolean hasNext() {
//		return !map.isEmpty();
//	}
//
//	public static void main(String[] args) {
//		StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");
//
//		iterator.next(); // return 'L'
//		iterator.next(); // return 'e'
//		iterator.next(); // return 'e'
//		iterator.next(); // return 't'
//		iterator.next(); // return 'C'
//		iterator.next(); // return 'o'
//		iterator.next(); // return 'd'
//		iterator.hasNext(); // return true
//		iterator.next(); // return 'e'
//		iterator.hasNext(); // return false
//		iterator.next(); // return ' '
//	}
//}

public class StringIterator {
	
	char[] letter;
	int[] cnt;
	int len;
	public StringIterator(String compressedString) {
		int n = compressedString.length();
		char[] s = compressedString.toCharArray();
		letter = new char[n+1];
		cnt = new int[n+1];
		for (int i = 0, k = 0; i < n; ++i){
			if (s[i] >= 'a' && s[i] <= 'z' || s[i] >= 'A' && s[i] <= 'Z'){
				letter[k] = s[i];
				len++;
			}
			else{
				int num = 0;
				while (i < n && s[i] >= '0' && s[i] <= '9'){
					num = num * 10 + s[i++] - '0';
				}
				cnt[k] = num;
				k++;
				i--;
			}
		}
	}

	int fir = 0;
	public char next() {
		if (!hasNext()) return ' ';
		char c = letter[fir];
		if (cnt[fir] == 1) fir++;
		else cnt[fir]--;
		return c;
	}

	public boolean hasNext() {
		return len != fir;
	}

	public static void main(String[] args) {
		StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");
		iterator.next(); // return 'L'
		iterator.next(); // return 'e'
		iterator.next(); // return 'e'
		iterator.next(); // return 't'
		iterator.next(); // return 'C'
		iterator.next(); // return 'o'
		iterator.next(); // return 'd'
		iterator.hasNext(); // return true
		iterator.next(); // return 'e'
		iterator.hasNext(); // return false
		iterator.next(); // return ' '
	}
}
