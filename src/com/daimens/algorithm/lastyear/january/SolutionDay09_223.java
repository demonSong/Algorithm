package com.daimens.algorithm.january;

/**
 * 
 * @author Demon Song
 * 223. Rectangle Area
 * Find the total area covered by two rectilinear rectangles in a 2D plane.
 * Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
 * Assume that the total area is never beyond the maximum possible value of int.
 *
 */
public class SolutionDay09_223 {
	
//	public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {  
//        int area1 = (C-A) * (D-B);  
//        int area2 = (G-E) * (H-F);  
//          
//        int overlapRegion = overlap(A, B, C, D, E, F, G, H);  
//        return area1 + area2 - overlapRegion;  
//    }  
//      
//    private int overlap(int A, int B, int C, int D, int E, int F, int G, int H) {  
//        int h1 = Math.max(A, E);  
//        int h2 = Math.min(C, G);  
//        int h = h2 - h1;  
//          
//        int v1 = Math.max(B, F);  
//        int v2 = Math.min(D, H);  
//        int v = v2 - v1;  
//          
//        if(h<=0 || v<=0) return 0;  
//        else return h*v;  
//    }
	
	public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {  
        int val = (C-A)*(D-B) + (G-E)*(H-F);
        if (E > C || G < A || F > D || H < B) {
            return val;
        }
        val -= (Math.min(C,G) - Math.max(A,E))*(Math.min(D,H) - Math.max(B,F));
        return val;  
    }
}
