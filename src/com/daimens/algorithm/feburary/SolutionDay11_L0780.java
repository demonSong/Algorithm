package com.daimens.algorithm.feburary;

public class SolutionDay11_L0780 {

	public boolean reachingPoints(int sx, int sy, int tx, int ty) {
		return f(sx, sy, tx, ty);
	}
	
	boolean f(int sx, int sy, int tx, int ty) {
		if (tx < sx || ty < sy) return false;
		if(sy == ty && (tx-sx) % sy == 0) return true;
    	if(sx == tx && (ty-sy) % sx == 0) return true;
		if (ty > tx) {
			if (f(sx, sy, tx, ty % tx)) return true;
		}
		else {
			if (f(sx, sy, tx % ty, ty)) return true;
		}
		return false;
	}
	
//	boolean f(int sx, int sy, int tx, int ty) {
//	if (tx < sx || ty < sy) return false;
//	if (tx == sx && ty == sy) return true;
//	if (f(sx, sx + sy, tx, ty) || f(sx + sy, sy, tx, ty)) return true;
//	return false;
//}

//	boolean f(long sx, long sy, long tx, long ty) {
//		long d = gcd(tx, ty);
//		if (sx % d != 0 || sy % d != 0) return false;
//		if (sx > tx || sy > ty) return false;
//		if (sx == tx && sy == ty) return true;
//		if (sx + sy >= Integer.MAX_VALUE) return false;
//		if (Math.abs(tx - ty) % sx != 0 && Math.abs(tx - ty) % sy != 0) return false;
//		if (f(sx + sy, sy, tx, ty) || f(sx, sx + sy, tx, ty)) return true;
//		return false;
//	}
//	
//	public long gcd(long x, long y) {
//	    return y == 0 ? x : gcd(y, x % y);
//	}

	public static void main(String[] args) {
		SolutionDay11_L0780 day = new SolutionDay11_L0780();
		System.out.println(day.reachingPoints(5, 7, 455955547, 420098884));
	}

}
