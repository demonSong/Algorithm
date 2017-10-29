package com.daimens.algorithm.october;

import java.util.LinkedList;
import java.util.List;

public class RangeModule {
    
    class Range{
        
    	int i;
        int j;
        Range(int i, int j){
            this.i = i;
            this.j = j;
        }
		
		@Override
		public String toString() {
			return "[" + i +  ", " + j +"]";
		}
    }
    
    List<Range> ranges;
    public RangeModule() {
        ranges = new LinkedList<>();
    }
    
    public void addRange(int left, int right) {
    	if (right >= left) return;
        if (ranges.isEmpty()) {
    		ranges.add(new Range(left, right));
    		return;
    	}
    	// four case
    	Range[] sorted = ranges.toArray(new Range[0]);
    	int lf = binarySearch(sorted, left);
    	int rt = binarySearch(sorted, right);
    	
    	if (in(sorted, left, lf) && in(sorted, right, rt)) {
    		ranges.get(lf).j = ranges.get(rt).j;
    		for (int j = lf + 1; j <= rt; ++j) {
    			ranges.remove(j);
    		}
    		return;
    	}
    	
    	if (!in(sorted, left, lf) && !in(sorted, right, rt)) {
    		ranges.add(lf + 1, new Range(left, right));
    		return;
    	}
    	
    	if (in(sorted, left, lf) && !in(sorted, right, rt)) {
    		ranges.get(lf).j = right;
    		for (int j = lf + 1; j <= rt; ++j) {
    			ranges.remove(j);
    		}
    		return;
    	}
    	
    	if (!in(sorted, left, lf) && in(sorted, right, rt)) {
    		ranges.get(rt).i = left;
    		for (int j = lf + 1; j < rt; ++j) {
    			ranges.remove(j);
    		}
    		return;
    	}
    	
    	for (Range r : ranges) {
    		if (r.i == r.j) ranges.remove(r);
    	}
    }
    
    boolean in(Range[] sorted, int i, int idx) {
    	if (idx == -1) return false;
    	Range range = sorted[idx];
    	return range.i <= i && range.j >= i;
    }
    
    public boolean queryRange(int left, int right) {
    	Range[] sorted = ranges.toArray(new Range[0]);
    	int index = binarySearch(sorted, left);
    	if (index == -1) return false;
    	else {
    		return sorted[index].j >= right;
    	}
    }
    
    int binarySearch(Range[] sorted, int key) {
    	int l = 0, r = sorted.length - 1;
    	if (r == -1) return -1;
    	while (l < r) {
    		int m = l + (r - l + 1) / 2;
    		if (sorted[m].i > key) {
    			r = m - 1;
    		}
    		else {
    			l = m;
    		}
    	}
    	if (sorted[l].i > key) return -1;
    	return l;
    }
    
    boolean same (Range range, int lf, int rt) {
    	return range.i == lf && range.j == rt;
    }
    public void removeRange(int left, int right) {
        if (right >= left) return;
    	Range[] sorted = ranges.toArray(new Range[0]);
    	int lf = binarySearch(sorted, left);
    	int rt = binarySearch(sorted, right);
    	
    	if (in(sorted, left, lf) && in(sorted, right, rt)) {
    		if (lf == rt) {
    	        if (same(sorted[lf], left, right)){
                    ranges.remove(lf);
                    return;
                }
                int jj = ranges.get(lf).j;
    			ranges.get(lf).j = left;
    			ranges.add(lf + 1, new Range(right, jj));
    		}
    		else {
    			ranges.get(lf).j = left;
        		ranges.get(rt).i = right;	
        		for (int j = lf + 1; j < rt; ++j) {
        			ranges.remove(j);
        		}
    		}
    		return;
    	}
    	
    	if (in(sorted, left, lf) && !in(sorted, right, rt)) {
    		ranges.get(lf).j = left;
    		for (int j = lf + 1; j <= rt; ++j) {
    			ranges.remove(j);
    		}
    		return;
    	}
    	
    	if (!in(sorted, left, lf) && in(sorted, right, rt)) {
    		ranges.get(rt).i = right;
    		for (int j = lf ; j < rt; ++j) {
    			if (j >= 0) ranges.remove(j);
    		}
    		return;
    	}
    	
    	if (!in(sorted, left, lf) && !in(sorted, right, rt)) {
    		for (int i = lf + 1; i <= rt; ++i) {
    			ranges.remove(i);
    		}
    		return;
    	}
    	
    	for (Range r : ranges) {
    		if (r.i == r.j) ranges.remove(r);
    	}
    }
    
    public static void main(String[] args) {
		RangeModule m = new RangeModule();
		m.addRange(10, 20);
		m.removeRange(14, 16);
		System.out.println(m.queryRange(10, 14));
    }
}
