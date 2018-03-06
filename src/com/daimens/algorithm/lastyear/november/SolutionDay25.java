package com.daimens.algorithm.november;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Demon Song 401. Binary Watch A binary watch has 4 LEDs on the top
 *         which represent the hours (0-11), and the 6 LEDs on the bottom
 *         represent the minutes (0-59). Each LED represents a zero or one, with
 *         the least significant bit on the right. Given a non-negative integer
 *         n which represents the number of LEDs that are currently on, return
 *         all possible times the watch could represent. Example: Input: n = 1
 *         Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04",
 *         "0:08", "0:16", "0:32"]
 */
public class SolutionDay25 {
	public List<String> readBinaryWatch(int num) {
		int[] hours = new int[12];
		int[] minutes = new int[60];
		for (int i = 0 ; i < 12 ; i++){
			hours[i] =i;
		}
		
		for (int j = 0 ; j < 60 ; j++){
			minutes[j] =j;
		}
		
		List<String> times = new ArrayList<String>();
		
		for (int i = 0 ; i < 12 ;i++){
			for (int j = 0 ; j < 60 ;j++){
				//·ûºÏÇé¿ö
				if(countOne(hours[i])+countOne(minutes[j]) == num){
					String time = String.valueOf(hours[i])+":";
					String minute = String.valueOf(minutes[j]);
					time =time + ((minute.length()==1) ? ("0"+minute) : minute);
					times.add(time);
				}
			}
		}
		return times;
	}
	
	public int countOne(int num){
		int counter = 0;
		while(num > 0){
			if(num % 2 ==1){
				counter +=1;
			}
			num = num/2;
		}
		return counter;
	}
	
	public static void main(String[] args) {
		SolutionDay25 day25 = new SolutionDay25();
		System.out.println(day25.countOne(11));
		day25.readBinaryWatch(0);
	}
}
