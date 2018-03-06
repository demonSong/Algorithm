package com.daimens.algorithm.july;

public class Node {
	
	Node next;
	int val;
	
	public Node(int val){
		this.val = val;
	}
	
	@Override
	public String toString() {
		return val + "";
	}
	
}
