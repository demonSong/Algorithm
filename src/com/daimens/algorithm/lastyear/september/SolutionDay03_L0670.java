package com.daimens.algorithm.september;

public class SolutionDay03_L0670 {

	// public int maximumSwap(int num) {
	// String ns = String.valueOf(num);
	// char[] cs = ns.toCharArray();
	// int id = findMax(cs, 0, cs.length - 1);
	// int j = 0;
	// while (j < cs.length && cs[j] == cs[id]){
	// j ++;
	// id = findMax(cs, j, cs.length - 1);
	// }
	//
	// if (j == cs.length) return num;
	//
	// swap(cs, j, id);
	//
	// int ans = 0;
	// for (int i = 0; i < cs.length; ++i){
	// ans = 10 * ans + (cs[i] - '0');
	// }
	//
	// return ans;
	// }
	//
	// public void swap(char[] cs, int i, int j){
	// char tmp = cs[i];
	// cs[i] = cs[j];
	// cs[j] = tmp;
	// }
	//
	// public int findMax(char[] cs, int i, int j){
	// int id = i;
	// for (int l = i; l <= j; ++l){
	// if (cs[l] >= cs[id]){
	// id = l;
	// }
	// }
	// return id;
	// }

	public int maximumSwap(int num) {
		char temp;
		int index = 0;
		int j = 0;
		int result;
		char s[] = String.valueOf(num).toCharArray();
		while (j < s.length) {
			for (int i = j; i < s.length - 1; i++) {
				if ((i + 1) < s.length && index < s.length && s[i + 1] >= s[index]) {
					index = i + 1;
				}
			}
			if (index < s.length && j < s.length && s[index] == s[j]) {
				j = j + 1;
				index = j;
			} else {
				break;
			}
		}
		if (j >= s.length || index >= s.length) {
			return num;
		}
		temp = s[index];
		s[index] = s[j];
		s[j] = temp;
		result = Integer.parseInt(new String(s));
		return result;
	}

	// public int maximumSwap(int num) {
	// char[] cs = String.valueOf(num).toCharArray();
	// int[] bucket = new int[10];
	//
	// for (int i = 0; i < cs.length; ++i){
	// bucket[cs[i] - '0'] = i;
	// }
	//
	// outer :for (int j = 0; j < cs.length; ++j){
	// for (int i = 9; i > cs[j] - '0'; --i){
	// if (bucket[i] > j){
	// char tmp = cs[bucket[i]];
	// cs[bucket[i]] = cs[j];
	// cs[j] = tmp;
	// break outer;
	// }
	// }
	// }
	// return Integer.valueOf(new String(cs));
	// }

	public static void main(String[] args) {
		SolutionDay03_L0670 day = new SolutionDay03_L0670();
		System.out.println(day.maximumSwap(9973));
	}

}
