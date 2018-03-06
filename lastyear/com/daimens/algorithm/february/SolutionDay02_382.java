package com.daimens.algorithm.february;

import java.util.Random;

/**
 * 
 * @author Demon Song
 * 382.Linked List Random Node 
 * Given a singly linked list, return a random node's value from the linked list.Each node must
 * have the same probability of being chosen.
 * Follow up:
 * What if the linked list is extremely large and its length is unknown to you ? Could you solve this
 * efficiently without using extra space?
 * Example:
 * Init a singly linked list [1,2,3]
 * ListNode node = new ListNode(1);
 * head.next = new ListNode(2);
 * head.next.next = new ListNode(3);
 * Solution solution = new Solution(head);
 * getRandom() should return either 1,2,or 3 randomly.Each element should have equal probability of returning.
 * solution.getRandom(); 
 *
 */
public class SolutionDay02_382 {
	
	ListNode head;
	Random random;
	//计算生成一个均匀分布的函数
	public SolutionDay02_382(ListNode head){
		this.head = head;
	}
	
	public int getRandom(){
		ListNode c = head;
		int r = c.val;
		for(int i = 1; c.next !=null; i++){
			c = c.next;
			if(randInt(0,i) == i) r = c.val; //你这是要给后者一个机会嘛？
		}
		return r;
	}
	
	private int randInt(int min, int max) {
		return min + (int) (Math.random() * (max - min + 1));
	}
	
	public static void main(String[] args) {
		SolutionDay02_382 day02_382 = new SolutionDay02_382(null);
		for(int i = 0; i < 100; i++){
			System.out.println(day02_382.randInt(0, 2));
		}
	}
}

class Solution{
	
	ListNode head;
	Random random;
	
	public Solution(ListNode head){
		this.head = head;
	}
	
	public int getRandom(){
		ListNode c = head;
		int r = c.val;
		for(int i = 2; c.next !=null; i++){
			c = c.next;
			if(hit(i)){
				r = c.val;
			}
		}
		return r;
	}
	
	public boolean hit(int i){
		int factor = (int) Math.random() * i +1;
		return i == factor;
	}

}

class ListNode{
	int val;
	ListNode next;
	ListNode(int x){
		val = x;
	}
}