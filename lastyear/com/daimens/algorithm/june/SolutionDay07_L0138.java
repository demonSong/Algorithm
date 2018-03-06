package com.daimens.algorithm.june;

/**
 * 
 * @author DemonSong
 * 
 *         138. Copy List with Random Pointer
 * 
 * 
 *         A linked list is given such that each node contains an additional
 *         random pointer which could point to any node in the list or null.
 * 
 *         Return a deep copy of the list.
 *
 */
public class SolutionDay07_L0138 {
	
//	public RandomListNode copyRandomList(RandomListNode head) {
//		RandomListNode dummy = new RandomListNode(0);
//		RandomListNode cur = dummy;
//		
//		RandomListNode pos = head;
//		Map<Integer, RandomListNode> index = new HashMap<>();
//		Map<RandomListNode, Integer> originIndex = new HashMap<>();
//		
//		int cnt = 0;
//		while (pos != null){
//			cur.next = new RandomListNode(pos.label);
//			originIndex.put(pos, cnt);
//			index.put(cnt++, cur.next);
//			cur = cur.next;
//			pos = pos.next;
//		}
//		
//		pos = head;
//		cur = dummy.next;
//		
//		while (pos != null){
//			if (pos.random != null){
//				int idx = originIndex.get(pos.random);
//				cur.random = index.get(idx);
//			}
//			cur = cur.next;
//			pos = pos.next;
//		}
//		return dummy.next;
//    }
	
//	public RandomListNode copyRandomList(RandomListNode head) {
//		if (head == null) return null;
//		
//		Map<RandomListNode, RandomListNode> map = new HashMap<>();
//		RandomListNode pos = head;
//		while (pos != null){
//			map.put(pos, new RandomListNode(pos.label));
//			pos = pos.next;
//		}
//		
//		pos = head;
//		while (pos != null){
//			map.get(pos).next = map.get(pos.next);
//			map.get(pos).random = map.get(pos.random);
//			pos = pos.next;
//		}
//		return map.get(head);
//	}
	
	public RandomListNode copyRandomList(RandomListNode head) {
		if (head == null) return null;
		RandomListNode pos = head;
		while (pos != null){
			RandomListNode copy = new RandomListNode(pos.label);
			copy.next = pos.next;
			pos.next = copy;
			pos = pos.next.next;
		}
		
		pos = head;
		while (pos != null){
			pos.next.random = pos.random != null ? pos.random.next : null;
			pos = pos.next.next;
		}

		pos = head;
		RandomListNode copy = pos.next;
		RandomListNode curr = copy;
		while (pos != null){
			pos.next = pos.next.next;
			pos = pos.next;
			curr.next = pos != null ? pos.next : null;
			curr = curr.next;
		}
		return copy;
	}
	
	public static void main(String[] args) {
		SolutionDay07_L0138 day = new SolutionDay07_L0138();
		RandomListNode head = new RandomListNode(1);
		head.next = new RandomListNode(2);
		day.copyRandomList(head);
	}
}

class RandomListNode{
	int label;
	RandomListNode next, random;
	public RandomListNode(int x) {
		this.label = x;
	}
	@Override
	public String toString() {
		return "[" + label + "]";
	}
}