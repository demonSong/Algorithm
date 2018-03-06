package com.daimens.algorithm.june;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class SolutionDay28_P2674 {
	
	static class Inhabitant{
		char direction;
		String name;
		double pos;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (true){
			String line = in.nextLine().trim();
			int n = Integer.parseInt(line);
			if (n == 0) break;	
			line = in.nextLine();
			String[] nums = line.trim().split(" +");
			double l = Double.parseDouble(nums[0]);
			double v = Double.parseDouble(nums[1]);
			Inhabitant[] ants = new Inhabitant[n];
			for (int i = 0; i < n; ++i){
				ants[i] = new Inhabitant();
				line = in.nextLine();
				nums = line.trim().split(" +");
				ants[i].direction = Character.toLowerCase(nums[0].charAt(0));
				ants[i].name = nums[2];
				ants[i].pos = Double.parseDouble(nums[1]);
			}
			Arrays.sort(ants, new Comparator<Inhabitant>() {
				@Override
				public int compare(Inhabitant o1, Inhabitant o2) {
					double pos1 = o1.pos;
					double pos2 = o2.pos;
					return pos1 < pos2 ? -1 : 1;
				}
			});
			double maxDistance = 0.0;
			int index = 0;	
			boolean forward = true;
			for (int i = 0; i < n; ++i){
				if(ants[i].direction == 'n'){
					if(ants[i].pos > maxDistance){
						maxDistance = ants[i].pos;
						index = i;
						forward = false;
					}
				}
				else{
					if ((l - ants[i].pos) > maxDistance){
						maxDistance = l - ants[i].pos;
						index = i;
						forward = true;
					}
				}
			}
			
			if(forward){
				int cnt = 0;
				for (int i = index; i < n; ++i){
					if (ants[i].direction == 'n') cnt++;
				}
				index += cnt;
			}
			else{
				int cnt = 0;
				for (int i = index; i >= 0; --i){
					if(ants[i].direction == 'p') cnt++;
				}
				index -= cnt;
			}
			double result = maxDistance / v;
			System.out.printf("%13.2f %s\n",(int) (result * 100) / 100.0, ants[index].name);
		}
		in.close();
	}
	
	
}
