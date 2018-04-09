package com.daimens.algorithm.march;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SolutionDay29_L0503 {

	static class E{
		int u;
		int v;
		int d;
		E(int u, int v, int d){
			this.u = u;
			this.v = v;
			this.d = d;
		}
	}
	
	static boolean dfs(List<E>[] g, int s, int to, int p) {
		if (to == s && p != -1) return true;
		for (E e : g[to]) {
			int nxt = e.v;
			if (dfs(g, s, nxt, to)) return true;
		}
		return false;
	}
	
	static int dfs(List<E>[] g, int s, int p) {
		int res = 0;
		for (E e : g[s]) {
			res = Math.max(res, dfs(g, e.v, s) + e.d);
		}
		return res;
	}
	
	
/*请完成下面这个函数，实现题目要求的功能
当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^ 
******************************开始写代码******************************/
	
    static int calculate(int n, String[] arra) {
		List<E>[] g = new ArrayList[5];
		for (int i = 0; i < 5; ++i) g[i] = new ArrayList<>();
		
		int[][] matrix = new int[5][5];
		int inf = 0x3f3f3f3f;
		for (int i = 0; i < 5; ++i) Arrays.fill(matrix[i], inf);
		for (int i = 0; i < n; ++i) {
			String a = arra[i];
			for (int j = 1; j < a.length(); j += 2) {
				int d = a.charAt(j) - '0';
				int u = a.charAt(j - 1) - 'A';
				int v = a.charAt(j + 1) - 'A';
				g[u].add(new E(u, v, d));
				matrix[u][v] = -d;
			}
		}
		
		boolean isCircle = false;
		for (int i = 0; i < n; ++i) {
			if (dfs(g, i, i, -1)) isCircle = true;
		}
		
		if (isCircle) return -1;
		
		for (int k = 0; k < 5; k++) {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (matrix[i][j] > matrix[i][k] + matrix[k][j]) {
						matrix[i][j] = matrix[i][k] + matrix[k][j];
					}
				}
			}
		}
		
		int max = 0;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				if (i == j && matrix[i][j] <= inf - 100) return -1;
				if (matrix[i][j] >= inf) continue;
				max = Math.max(-matrix[i][j], max);
			}
		}
		return max;
    }
/******************************结束写代码******************************/


    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int res;
            
        int _M;
        _M = Integer.parseInt(in.nextLine().trim());
        
        int _strs_size = _M;
        String[] _strs = new String[_strs_size];
        String _strs_item;
        for(int _strs_i = 0; _strs_i < _strs_size; _strs_i++) {
            try {
                _strs_item = in.nextLine();
            } catch (Exception e) {
                _strs_item = null;
            }
            _strs[_strs_i] = _strs_item;
        }
  
        res = calculate(_M, _strs);
        System.out.println(String.valueOf(res));    

    }
}