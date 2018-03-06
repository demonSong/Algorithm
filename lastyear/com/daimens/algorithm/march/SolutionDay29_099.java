package com.daimens.algorithm.march;

/**
 * 
 * @author DemonSong
 * 99.Recover Binary Search Tree
 * Two element of a binary search tree (BST) are swapped by mistake.
 * Recover the tree without changing its structure.
 * 
 * Note:
 * A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
 *
 */
public class SolutionDay29_099 {
	
	//BST
//	public void recoverTree(TreeNode root){
//		if(root == null) return;
//		
//		inorder(root);
//		antiInorder(root);
//		change(root, problem[1],problem[0]);
//		change(root, problem[0],problem[1]);
//	}
//	
//	int[] problem = {Integer.MIN_VALUE,Integer.MAX_VALUE};
//	boolean isFound1 = false;
//	private void inorder(TreeNode root){
//		if(root == null) return;
//		
//		inorder(root.left);
//		
//		if(root.val > problem[0] && !isFound1){
//			problem[0] = root.val;
//		}
//		else{
//			isFound1 = true;
//		}
//		
//		inorder(root.right);
//	}
//	
//	
//	boolean isFound2 = false;
//	private void antiInorder(TreeNode root){
//		if(root == null){
//			return;
//		}
//		
//		antiInorder(root.right);
//		
//		if(root.val < problem[1] && !isFound2)
//			problem[1] = root.val;
//		else{
//			isFound2 = true;
//		}
//		antiInorder(root.left);
//	}
//	
//	boolean isChange = false;
//	int first = 1;
//	private void change(TreeNode root,int target,int change){
//		if(root == null) return;
//		
//		change(root.left,target,change);
//		
//		if(root.val == target && !isChange){
//			root.val = change;
//			isChange = true;
//		}else if(root.val == target && (first++) == 2){ //wow，fantastic
//			root.val = change;
//		}
//		change(root.right,target,change);
//	}
	
	
	public void recoverTree(TreeNode root) {
		if(root == null) return;
	
		inorder(root);
		
		int tmp = firstElement.val;
		firstElement.val = secondElement.val;
		secondElement.val = tmp;
		
	}
	
	TreeNode firstElement = null; //这样就可以直接对节点进行操作了
	TreeNode secondElement = null;
	TreeNode preElement = new TreeNode(Integer.MIN_VALUE);
	
	private void inorder(TreeNode root){
		if(root == null) return;
		
		inorder(root.left);
		
		if(firstElement == null && root.val <= preElement.val){ //这种检测效率更高
			firstElement = preElement;
		}
		
		if(firstElement != null && root.val <= preElement.val){
			secondElement = root;
		}

		//所有操作完成，再更新前向指针
		preElement = root;
		
		inorder(root.right);
	}
	
	public static void main(String[] args) {
		SolutionDay29_099 day = new SolutionDay29_099();
		TreeNode root = new TreeNode(0);
		root.left = new TreeNode(1);
		day.recoverTree(root);
	}
	
	
}
