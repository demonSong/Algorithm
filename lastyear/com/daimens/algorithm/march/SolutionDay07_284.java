package com.daimens.algorithm.march;

import java.util.Iterator;

/**
 * 
 * @author DemonSong
 * 284.Peeking Iterator
 * Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator
 * that support the peek() operation -- it essentially peek() at the element that will be returned by the next
 * call to next().
 * 
 * Here is an example. Assume that the iterator is initialized to the beginning of the list:[1,2,3]
 * Call next() gets you 1, the first element in the list.
 * Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.
 * You call next() the final time and it returns 3, the last element. Calling hasNext() after that should return false.
 * Hint:
 * 1. Think of "looking ahead". You want to cache the next element.
 * 2. Is one variable sufficient ? why or why not?
 * 3. Test your design with call order of peek() before next() vs next() before peek().
 * 4. For a clean implementation, check out Google's guava library source code.
 * Follow up : How could you extend your design to be generic and work with all types, not just integer?
 *
 */
public class SolutionDay07_284 implements Iterator<Integer>{
	
	/**my simple idea**/
//	private List<Integer> nums;
//	
//	public SolutionDay07_284(Iterator<Integer> iterator){
//		nums = new ArrayList<>();
//		
//		for (; iterator.hasNext();) {
//			Integer integer = (Integer) iterator.next();
//			nums.add(integer);
//		}
//	}
//	
//	public Integer peek(){
//		return nums.get(0);
//	}
//	
//	@Override
//	public boolean hasNext() {
//		return !nums.isEmpty();
//	}
//
//	@Override
//	public Integer next() {
//		Integer num = nums.get(0);
//		nums.remove(0);
//		return num;
//	}
	
	private Integer next = null;
	private Iterator<Integer> iter;
	
	public SolutionDay07_284(Iterator<Integer> iterator){
		iter = iterator;
		if(iter.hasNext())
			next = iter.next();
	}

	@Override
	public boolean hasNext() {
		return next != null;
	}

	@Override
	public Integer next() {
		Integer res = next;
		next = iter.hasNext() ? iter.next() : null;
		return res;
	}
	
	public Integer peek(){
		return next;
	}
	
}
