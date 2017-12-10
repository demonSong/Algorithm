package com.daimens.algorithm.december;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.Finishings;

public class SolutionDay10_L0502 {
	
    public int findClosestLeaf(TreeNode root, int k) {
    	leaves = new ArrayList<>();
    	leave_map = new HashMap<>();
    	node_map = new HashMap<>();
    	
    	dist = new int[1016][1016];
    	for (int i = 0; i < 1016; ++i) {
    		for (int j = 0; j < 1016; ++j) {
    			dist[i][j] = 0x3f3f3f3f;
    		}
    	}
    	
    	leaf(root);
    	
    	time = 0;
    	nodes = new ArrayList<>();
    	dfs(root);
    	
    	for (int i = 0; i < nodes.size(); ++i) {
    		for (int j = 0; j < nodes.size(); ++j) {
    			dist[nodes.get(i).idx][nodes.get(j).idx] = Math.abs(nodes.get(i).timeStamp - nodes.get(j).timeStamp);
    		}
    	}
    	
    	int min = 0x3f3f3f3f;
    	int minNode = -1;
    	
    	for (int i = 0; i < leaves.size(); ++i) {
    		finished = false;
    		int sum = findPath(root, node_map.get(k), leave_map.get(leaves.get(i)));
    		if (sum < min) {
    			min = sum;
    			minNode = leaves.get(i);
    		}
    	}
    	
    	return minNode;
    }
    
    Map<Integer, TreeNode> leave_map;
    List<Integer> leaves;
    int[][] dist;
    
    int time = 0;
    
    class Node{
    	
    	int idx;
    	int timeStamp;
    	
    	Node(int idx, int timeStamp){
    		this.idx = idx;
    		this.timeStamp = timeStamp;
    	}
    }
    
    List<Node> nodes;
    Map<Integer, TreeNode> node_map;
    
    public void dfs(TreeNode root) {
    	if (root == null) return;
    	nodes.add(new Node(root.val, time));
    	node_map.put(root.val, root);
    	time ++;
    	dfs(root.left);
    	dfs(root.right);
    }
    
    public void leaf(TreeNode root) {
    	if (root == null) return;
    	
    	if (root.left == null && root.right == null) {
    		leaves.add(root.val);
    		leave_map.put(root.val, root);
    	}
    	
    	leaf(root.left);
    	leaf(root.right);
    }
    
    boolean finished = false;
    public int findPath(TreeNode root, TreeNode first, TreeNode second) {
    	int value = 0;  
        if (root == first || root == second) {  
            value = 1;  
        } else if (root == null) {  
            return 0;  
        }
        if (first.val == second.val) return 0;
        int leftvalue = findPath(root.left, first, second);  
        int rightvalue = findPath(root.right, first, second);  
        if (leftvalue * rightvalue != 0 || value * leftvalue != 0  
                || value * rightvalue != 0) {  
            // find the common parent of the first and second node  
            finished = true;  
            return leftvalue + rightvalue;  
        } else if (leftvalue != 0 || rightvalue != 0 || value != 0) {  
            return finished ? leftvalue + rightvalue : leftvalue + rightvalue  
                    + 1;  
        }  
        return 0;  
    }  
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		
 		if(root == null || root == p || root == q) return root;
 		
 		TreeNode left = lowestCommonAncestor(root.left, p, q);
 		TreeNode right = lowestCommonAncestor(root.right, p, q);
 		
 		if(left != null && right != null) return root;

 		return left != null ? left : right;
 	}
    
	
	public static void main(String[] args) {
		SolutionDay10_L0502 day = new SolutionDay10_L0502();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(4);
		root.left.left.left = new TreeNode(5);
		root.left.left.left.left = new TreeNode(6);
		root.right = new TreeNode(3);
		System.out.println(day.findClosestLeaf(root, 2));
	}

}
