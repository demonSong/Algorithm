package com.daimens.algorithm.august;

import java.util.Scanner;

public class SolutionDay12_B1004 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = Integer.parseInt(in.nextLine());
		for (int i = 0; i < T; ++i){
			String line = in.nextLine().trim();
			String[] nums = line.split("-");
			int year = Integer.parseInt(nums[0]);
			int mont = Integer.parseInt(nums[1]);
			int dayy = Integer.parseInt(nums[2]);
			
			if (!isRun(year)){
				int ans = 1;
				long sum = 0;
				if (isRun(year + ans)) sum += 366;
				else sum += 365;
				while (sum % 7 != 0){
					ans ++;
					if (isRun(year + ans)){
						if (mont <= 2 && dayy <= 28 && (sum + 365) % 7 == 0){
							break;
						}
						else 
							sum += 366;
					}
					else sum += 365;
				}
				System.out.println(year + ans);
			}
			else{
				if (mont == 2 && dayy == 29){
					int ans = 1;
					long sum = 0;
					sum += (3 * 365 + 366);
					while (sum % 7 != 0){
						ans ++;
						sum += 3 * 365 + 366;
					}
					System.out.println(year + ans * 4);
				}
				else{
					int ans = 1;
					long sum = 0;
					if (mont >= 3) sum += 365;
					else sum += 366;
					while (sum % 7 != 0){
						ans ++;
						if (isRun(year + ans)){
							if (mont <= 2 && dayy <= 28 && (sum + 365) % 7 == 0){
								break;
							}
							else 
								sum += 366;
						}
						else{
							sum += 365;
						}
					}
					System.out.println(year + ans);
				}
			}
		}
		in.close();
	}
	
	
	
	public static boolean isRun(int year){
		return (year % 4 == 0 && year % 100 != 0 || year % 400 == 0);
	}

}
