package com.daimens.algorithm.november;

public class Main {
	
    public int maxDepth(TreeNode root) {
    	if (root == null) return 0;
    	int lf_ans = maxDepth(root.left);
        return 0;
    }
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(p.val>root.val && q.val>root.val) {return lowestCommonAncestor(root.right,p,q);}
        else if(p.val<root.val && q.val<root.val) {return lowestCommonAncestor(root.left,p,q);}
        else {return root;} 
    }
    
//    public TreeNode sortedArrayToBST(int[] nums) {
//    }
    
	public static void main(String[] args) {
		Main daystring = new Main();
		TreeNode root=new TreeNode(2);
		  root.left=new TreeNode(1);
		  root.right=new TreeNode(3);
		  //System.out.println(daystring.maxDepth(root));
		  System.out.println(daystring.lowestCommonAncestor(root, root.left, root.right));
	}
}
