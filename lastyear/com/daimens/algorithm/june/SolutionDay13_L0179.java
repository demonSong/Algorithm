package com.daimens.algorithm.june;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 
 * @author DemonSong
 *			
 *         179. Largest Number
 *          
 *         Given a list of non negative integers, arrange them such that they
 *         form the largest number.
 * 
 *         For example, given [3, 30, 34, 5, 9], the largest formed number is
 *         9534330.
 * 
 *         Note: The result may be very large, so you need to return a string
 *         instead of an integer.
 *
 */
public class SolutionDay13_L0179 {

//	class Int{
//		int f;
//		int l;
//		int num;
//		public Int(int f, int l, int num){
//			this.f = f;
//			this.l = l;
//			this.num = num;
//		}
//	}
//	
//	// 长度相同  选大的
//	// 长度不同  选长度小的
//	public String largestNumber(int[] nums) {
//		int max = Integer.MIN_VALUE;
//		for (int i = 0; i < nums.length; ++i){
//			max = Math.max(max, nums[i]);
//		}
//		int k = 0;
//		while (max != 0){k++; max /= 10;}
//		Queue<Integer>[] bucket = new PriorityQueue[k + 1];
//		for (int num : nums){
//			int len = String.valueOf(num).length();
//			if (bucket[len] == null) bucket[len] = new PriorityQueue<>((a,b) -> b - a);
//			bucket[len].offer(num);
//		}
//		StringBuilder sb = new StringBuilder();
//		Queue<Int> queue = new PriorityQueue<>((a,b) -> (b.f != a.f ? b.f - a.f : a.l - b.l));
//		for (int i = 0; i < bucket.length; ++i){
//			if (bucket[i] != null){
//				int num = bucket[i].poll();
//				String n = String.valueOf(num);
//				int len = String.valueOf(num).length();
//				queue.offer(new Int(n.charAt(0) - '0', len, num));
//			}
//		}
//		while (!queue.isEmpty()){
//			Int it = queue.poll();
//			sb.append(it.num);
//			if (!bucket[it.l].isEmpty()){
//				int num = bucket[it.l].poll();
//				String n = String.valueOf(num);
//				int len = String.valueOf(num).length();
//				queue.offer(new Int(n.charAt(0) - '0', len, num));
//			}
//		}
//		return sb.toString();
//    }
	
	class Int{
		int f;
		int l;
		int num;
		public Int(int f, int l, int num){
			this.f = f;
			this.l = l;
			this.num = num;
		}
	}
	
//	public String largestNumber(int[] nums) {
//		int max = Integer.MIN_VALUE;
//		for (int i = 0; i < nums.length; ++i){
//			max = Math.max(max, nums[i]);
//		}
//		int k = 0;
//		while (max != 0){k++; max /= 10;}
//		Queue<Int> queue = new PriorityQueue<>((a,b) -> b.f != a.f ? b.f - a.f : b.l - a.l);
//		for (int num : nums){
//			String n = String.valueOf(num);
//			int len = n.length();
//			StringBuilder sb = new StringBuilder(n);
//			if (n.length() < k){
//				int diff = k - len;
//				for (int j = 0; j < diff; ++j){
//					sb.append(n.charAt(0));
//				}
//			}
//			Int it = new Int(Integer.parseInt(sb.toString()),len, num);
//			queue.offer(it);
//		}
//		StringBuilder sb = new StringBuilder();
//		while (!queue.isEmpty()) sb.append(queue.poll().num); 
//		return valid(sb.toString());
//    }
//	
//	private String valid(String ans){
//		char[] cs= ans.toCharArray();
//		int n = 0;
//		int i = 0;
//		while (i < cs.length){
//			n = 10 * n + cs[i++] - '0';
//		}
//		if (n == 0) return "0";
//		else return ans;
//	}
	
	public String largestNumber(int[] nums) {
		String[] s = new String[nums.length];
		for (int i = 0; i < nums.length; ++i){
			s[i] = nums[i] + "";
		}
		Arrays.sort(s, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				String s1 = o1 + o2;
				String s2 = o2 + o1;
				return s1.compareTo(s2);
			}
		});
		
		if (s[s.length - 1].charAt(0) == '0' ) return "0";
		String ans = "";
		for (String n : s){
			ans = n + ans;
		}
		return ans;
	}
	
	public static void main(String[] args) {
		SolutionDay13_L0179 day = new SolutionDay13_L0179();
		int[] nums = {830,8308};
		System.out.println(day.largestNumber(nums));
		String[] num = {"830","8308"};
		Arrays.sort(num, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				String s1 = o1 + o2;
				String s2 = o2 + o1;
				return s1.compareTo(s2);
			}
		});
		System.out.println(num[0] + " " + num[1]);
		String ans = "";
		for (String n : num){
			ans = n + ans;
		}
		System.out.println(ans);
	}
}
