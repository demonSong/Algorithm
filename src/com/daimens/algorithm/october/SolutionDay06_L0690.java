package com.daimens.algorithm.october;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class SolutionDay06_L0690 {

	public int getImportance(List<Employee> employees, int id) {
        sum = 0;
        bfs(employees, id);
        return sum;
	}
	
	int sum = 0;
	public void bfs(List<Employee> employees, int id) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(id);
		while (!queue.isEmpty()) {
			int now = queue.poll();
			Employee ee = employees.get(now - 1);
			sum += ee.importance;
			for (int num : ee.subordinates) {
				queue.offer(num);
			}
				
		}
	}
}

class Employee{
	public int id;
	
	public int importance;
	
	public List<Integer> subordinates;
}
