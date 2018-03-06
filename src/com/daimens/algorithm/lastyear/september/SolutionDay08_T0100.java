package com.daimens.algorithm.september;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class SolutionDay08_T0100 {

	public TreeNode invertTree(TreeNode root) {
		if (root == null)
			return root;
		TreeNode left = invertTree(root.left);
		TreeNode right = invertTree(root.right);
		root.left = right;
		root.right = left;
		return root;
	}

	public boolean canWinNim(int n) {
		return n % 4 != 0;
	}

	public char findTheDifference(String s, String t) {
		char c = 0;
		for (char cc : s.toCharArray())
			c ^= cc;
		for (char tt : t.toCharArray())
			c ^= tt;
		return c;
	}

	public int longestPalindromeSubseq(String s) {
		char[] cs = s.toCharArray();
		int n = cs.length;
		int[][] dp = new int[n + 16][n + 16];
		return 0;
	}

	// 300
	public int lengthOfLIS(int[] nums) {
		if (nums.length == 0)
			return 0;
		int n = nums.length;
		int[] dp = new int[n + 16];
		Arrays.fill(dp, 1);
		int max = 1;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < i; ++j) {
				if (nums[i] > nums[j])
					dp[i] = Math.max(dp[i], dp[j] + 1);
				max = Math.max(max, dp[i]);
			}
		}
		return max;
	}

	// 575
	public int distributeCandies(int[] candies) {
		int n = candies.length;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int num : candies)
			map.put(num, map.getOrDefault(num, 0) + 1);
		return Math.min(n / 2, map.size());
	}

	// 347

	// 500 abcdefghijklmnopqrstuvwxyz
	int[] map = { 2, 3, 3, 2, 1, 2, 2, 2, 1, 2, 2, 2, 3, 3, 1, 1, 1, 1, 2, 1, 1, 3, 1, 3, 1, 3 };

	public String[] findWords(String[] words) {
		List<String> ans = new ArrayList<>();
		for (String word : words)
			if (valid(word))
				ans.add(word);
		return ans.toArray(new String[0]);
	}

	public boolean valid(String word) {
		int row = map[word.toLowerCase().charAt(0) - 'a'];
		for (char c : word.toLowerCase().toCharArray()) {
			int rr = map[c - 'a'];
			if (rr != row)
				return false;
		}
		return true;
	}

	// 268
	public int missingNumber(int[] nums) {
		int n = nums.length;
		int[] map = new int[n + 16];
		for (int i = 0; i < n; ++i) {
			map[nums[i]] = 1;
		}

		for (int i = 0; i <= n; ++i) {
			if (map[i] == 0)
				return i;
		}

		return -1;
	}

	public int lengthOfLastWord(String s) {
		String[] ans = s.split("\\+");
		return ans[ans.length - 1].length();
	}

	public int[] twoSum(int[] numbers, int target) {
		int n = numbers.length;
		for (int i = 0; i < n; ++i) {
			int key = target - numbers[i];
			int lf = i + 1;
			int rt = n - 1;
			while (lf < rt) {
				int mid = lf + (rt - lf + 1) / 2;
				if (numbers[mid] > key) {
					rt = mid - 1;
				} else {
					lf = mid;
				}
			}

			if (numbers[lf] == key)
				return new int[] { i + 1, lf + 1 };

		}

		return new int[] { -1, -1 };
	}

	public void connect(TreeLinkNode root) {
		Queue<TreeLinkNode> queue = new LinkedList<>();
		if (root == null)
			return;
		queue.offer(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			TreeLinkNode curr = queue.poll();
			if (curr.left != null)
				queue.offer(curr.left);
			if (curr.right != null)
				queue.offer(curr.right);
			for (int i = 1; i < size; ++i) {
				TreeLinkNode now = queue.poll();
				curr.next = now;
				curr = curr.next;
				if (curr.left != null)
					queue.offer(curr.left);
				if (curr.right != null)
					queue.offer(curr.right);
			}
		}
	}

	public List<TreeNode> generateTrees(int n) {
		List<TreeNode>[] ans = new ArrayList[n + 16];
		for (int i = 0; i <= n + 1; ++i)
			ans[i] = new ArrayList<>();
		ans[1].add(new TreeNode(n));
		for (int i = 2; i <= n; ++i) {
			// insert i
			for (int j = 1; j < i; ++j) {
				for (TreeNode root : ans[j]) {
					TreeNode insert = new TreeNode(i);
					insert.left = root;
					ans[i].add(insert);
				}
			}

			// insert 1

			for (int j = 2; j < i; ++j) {
				for (TreeNode root : ans[j]) {
					dfs(root);
					TreeNode insert = new TreeNode(1);
					insert.right = root;
					ans[1].add(insert);
				}
			}

			// build 2 ~ i - 1
			for (int j = 2; j <= i - 1; ++j) {
				for (TreeNode root : ans[j]) {
					root = build(root, i);
				}
			}
		}

		List<TreeNode> res = new ArrayList<>();
		for (int i = 1; i <= n; ++i)
			res.addAll(ans[i]);
		return res;
	}

	public void dfs(TreeNode root) {
		if (root == null)
			return;
		root.val--;
		dfs(root.left);
		dfs(root.right);
	}

	public TreeNode build(TreeNode root, int val) {
		if (root == null)
			return new TreeNode(val);
		if (root.val < val) {
			root.right = build(root.right, val);
		} else {
			root.left = build(root.left, val);
		}
		return root;
	}

	public boolean isValidSerialization(String preorder) {
		String[] root = preorder.split(",");
		return dfs(root, 1);
	}

	public int left(int i) {
		return 2 * i;
	}

	public int right(int i) {
		return 2 * i + 1;
	}

	public boolean isNode(String node) {
		return !node.equals("#");
	}

	public boolean dfs(String[] root, int now) {
		if (now - 1 >= root.length)
			return true;
		String node = root[now - 1];
		if (!isNode(node)) {
			if (left(now) - 1 < root.length && isNode(root[left(now) - 1]))
				return false;
			if (right(now) - 1 < root.length && isNode(root[right(now) - 1]))
				return false;
			return true;
		} else {
			return dfs(root, left(now)) && dfs(root, right(now));
		}
	}
	
	int[] ans;
	public void dp() {
		int[] map = new int[8450 + 16];
		map[1] = 1;
		for (int j = 1; j < map.length; ++j) {
			if (map[j] != 0) {
				if (2 * j < map.length) map[j * 2] = 1;
				if (3 * j < map.length) map[j * 3] = 1;
				if (5 * j < map.length) map[j * 5] = 1;
			}
		}
		
		ans = new int[1692];
		for (int i = 0, j = 1; i < map.length; ++i) {
			if (map[i] != 0) {
				ans[j ++] = i;
			}
		}
	}
	
	public int nthUglyNumber(int n) {
		return ans[n];
	}
	
	public int integerBreak(int n) {
		int[] dp = new int[n + 1];
		dp[2] = 1;
		for (int i = 3; i <= n; ++i) {
			dp[i] = i - 1;
			for (int j = 2; j < i; ++j) {
				dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
			}
		}
		return dp[n];
	}
	
	public int[] intersect(int[] nums1, int[] nums2) {
		Set<Integer> set1 = new HashSet<>();
		Set<Integer> set2 = new HashSet<>();
		for (int num1 : nums1) set1.add(num1);
		for (int num2 : nums2) set2.add(num2);
		
		for (int num : set1) {
			if (set2.contains(num)) set2.remove(num);
		}
		
		List<Integer> ans = new ArrayList<>();
		for (int i = 0; i < nums2.length; ++i) {
			if (!set2.contains(nums2[i])) {
				ans.add(nums2[i]);
			}
		}
		
		int[] res = new int[ans.size()];
		for (int i = 0; i < ans.size(); ++i) {
			res[i] = ans.get(i);
		}
		
		return res;
    }



	public static void main(String[] args) {
		SolutionDay08_T0100 day = new SolutionDay08_T0100();
		// String[] words = { "Hello", "Alaska", "Dad", "Peace" };
		// System.out.println(day.findWords(words));
		//
		// System.out.println(day.twoSum(new int[] { 2, 3, 4 }, 6));
		//
		// // Map 的用法
		// Map<Integer, Integer> map = new HashMap<>();
		// // 存放 key 和 value
		// map.put(7, 1);
		// map.put(1, 2);
		//
		// System.out.println(map.get(0)); // 根据 key 找值
		//
		// Map<String, Integer> strMap = new HashMap<>();
		// strMap.put("demon", 123);
		// strMap.put("yuanyuan", 321);
		//
		// Map<Character, Integer> mm = new HashMap<>();
		// mm.put('q', 1);
		// mm.put('A', 2);
		//
		// char cc = 'A';
		// System.out.println(mm.get(cc));
		//
		// String[] arra = { "1213", "ddadas", "dasda" };
		// List<String> list = new ArrayList<>();
		// list.add(arra[0]);
		//
		// String[] ans = new String[list.size()];
		// for (int i = 0; i < list.size(); ++i) {
		// ans[i] = list.get(i);
		// }
		// System.out.println(3 & -3);
		
		System.out.println(day.integerBreak(8));
	}
}

class TreeLinkNode {
	int val;
	TreeLinkNode left, right, next;

	TreeLinkNode(int x) {
		val = x;
	}
}
