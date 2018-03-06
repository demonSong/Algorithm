package com.daimens.algorithm.june;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author DemonSong
 * 
 * 149.Max Points on a Line
 * 
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 *
 */
public class SolutionDay08_L0149 {
	
	public int maxPoints(Point[] points) {
		if (points==null) return 0;
    	if (points.length<=2) return points.length;
    	
    	int res = 0;
    	Map<Double, Integer> map;
    	for (int i = 0; i < points.length; ++i){
    		int dup = 1;
    		int max = 0;
    		map = new HashMap<>();
    		for (int j = i + 1; j < points.length; ++j){
    			if (points[j].y == points[i].y && points[j].x == points[i].x){ 
    				dup++;
    				continue;
    			}
    			if (points[j].y == points[i].y){
    				map.put(0.0, map.getOrDefault(0.0, 0) + 1);
    				continue;
    			}
    			if (points[j].x == points[i].x){
    				map.put(Double.MAX_VALUE, map.getOrDefault(Double.MAX_VALUE, 0) + 1);
    				continue;
    			}
    			int x = points[j].x;
    			int y = points[j].y;
    			double k = (double)(y - points[i].y) / (double) (x - points[i].x);
    			map.put(k, map.getOrDefault(k, 0) + 1);
    		}
    		
    		for (int val : map.values()){
    			max = Math.max(max, val);
    		}
    		
    		res = Math.max(res, max + dup);
    	}
    	
    	return res;
	}
	
//	public int maxPoints(Point[] points) {
//        if(points.length <= 0) return 0;
//        if(points.length <= 2) return points.length;
//        int result = 0;
//        for(int i = 0; i < points.length; i++){
//            HashMap<Double, Integer> hm = new HashMap<Double, Integer>();
//            int samex = 1;
//            int samep = 0;
//            for(int j = 0; j < points.length; j++){
//                if(j != i){
//                    if((points[j].x == points[i].x) && (points[j].y == points[i].y)){
//                        samep++;
//                    }
//                    if(points[j].x == points[i].x){
//                        samex++;
//                        continue;
//                    }
//                    double k = (double)(points[j].y - points[i].y) / (double)(points[j].x - points[i].x);
//                    if(hm.containsKey(k)){
//                        hm.put(k,hm.get(k) + 1);
//                    }else{
//                        hm.put(k, 2);
//                    }
//                    result = Math.max(result, hm.get(k) + samep);
//                }
//            }
//            result = Math.max(result, samex);
//        }
//        return result;
//    }
	
	public static void main(String[] args) {
		SolutionDay08_L0149 day = new SolutionDay08_L0149();
		Point[] p = new Point[3];
		p[0] = new Point(0,0);
		p[1] = new Point(94911151,94911150);
		p[2] = new Point(94911152,94911151);
		day.maxPoints(p);
	}

}

class Point{
	int x;
	int y;
	
	Point(){
		x = 0;
		y = 0;
	}
	
	Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "["+x+", "+y+"]";
	}
}
