package com.daimens.algorithm.june;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 
 * @author DemonSong
 * 
 *         71. Simplify Path
 * 
 *         Given an absolute path for a file (Unix-style), simplify it.
 * 
 *         For example, path = "/home/", => "/home" path = "/a/./b/../../c/", =>
 *         "/c"
 *
 */
public class SolutionDay12_L0071 {
	
//	public String simplifyPath(String path) {
//		Deque<String> stack = new ArrayDeque<>();
//		char[] cs = path.toCharArray();
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < path.length(); ++i){
//			if (cs[i] == '/'){
//				if (i + 1 < path.length() && cs[i+1] == '/') i++;
//				String dir = sb.toString();
//				if (dir.isEmpty()) continue;
//				if (dir.equals("..")){
//					if (!stack.isEmpty()) stack.pop();
//				}
//				else if (dir.equals(".")){
//				}
//				else{
//					stack.push(dir);
//				}
//				sb = new StringBuilder();
//			}
//			else{
//				sb.append(cs[i]);
//			}
//		}
//		if (sb.length() != 0){
//			String dir = sb.toString();
//			if (dir.equals("..")){
//				if (!stack.isEmpty()) stack.pop();
//			}
//			else if (dir.equals(".")){
//			}
//			else{
//				stack.push(dir);
//			}
//		}
//		sb = new StringBuilder();
//		while (!stack.isEmpty()){
//			sb.append("/" + stack.pollLast());
//		}
//		return sb.toString().isEmpty() ? "/" : sb.toString();
//    }
	
	public String simplifyPath(String path) {
		Deque<String> stack = new LinkedList<>();
		Set<String> omit = new HashSet<>(Arrays.asList("..",".",""));
		for (String dir : path.split("/")){
			if (dir.equals("..") && !stack.isEmpty()) stack.pop();
			else if (!omit.contains(dir)) stack.push(dir);
		}
		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) sb.append("/" + stack.pollLast());
		return sb.length() == 0 ? "/" : sb.toString();
	}
	
	public static void main(String[] args) {
		SolutionDay12_L0071 day = new SolutionDay12_L0071();
		System.out.println(day.simplifyPath("/a/./b/../../c/"));
		System.out.println(day.simplifyPath("/home/"));
		System.out.println(day.simplifyPath("/../"));
		System.out.println(day.simplifyPath("/home//foo/"));
		System.out.println(day.simplifyPath("/home//foo"));
		System.out.println(day.simplifyPath("/."));
		System.out.println(day.simplifyPath("/.."));
	}

}
