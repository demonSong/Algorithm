package com.daimens.algorithm.feburary;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SolutionDay04_P2503 {
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		Map<String, String> dict = new HashMap<String, String>();
		
		while (in.hasNextLine()) {
			String str = in.nextLine();
			if (str.contains(" ")) {
				dict.put(str.split(" ")[1], str.split(" ")[0]);
			}
			else {
				if (str.isEmpty()) continue;
				if (dict.containsKey(str)) System.out.println(dict.get(str));
				else System.out.println("eh");
			}
		}
		in.close();
	}
}

