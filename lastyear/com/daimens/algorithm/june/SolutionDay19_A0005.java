package com.daimens.algorithm.june;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author DemonSong
 * 
 *         5.GCD and LCM
 * 
 *         GCD and LCM Time Limit : 1 sec, Memory Limit : 65536 KB Japanese
 *         version is here GCD and LCM Write a program which computes the
 *         greatest common divisor (GCD) and the least common multiple (LCM) of
 *         given a and b.
 * 
 *         Input Input consists of several data sets. Each data set contains a
 *         and b separated by a single space in a line. The input terminates
 *         with EOF.
 * 
 *         Constraints 0 < a, b ≤ 2,000,000,000 LCM(a, b) ≤ 2,000,000,000 The
 *         number of data sets ≤ 50 Output For each data set, print GCD and LCM
 *         separated by a single space in a line.
 * 
 *         Sample Input 8 6 50000000 30000000 Output for the Sample Input 2 24
 *         10000000 150000000
 * 
 *
 */
public class SolutionDay19_A0005 {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        long a,b;
 
		while ((str = br.readLine()) != null) {
			a = Long.parseLong(str.substring(0, str.indexOf(" ")));
			b = Long.parseLong(str.substring(str.indexOf(" ") + 1, str.length()));

			System.out.println(gcd(a, b) + " " + lcm(a, b));
        }
    }
	
	private static long gcd(long a, long b){
		if (b == 0) return a;
		return gcd(b, a % b);
	}
	
	private static long lcm(long a, long b) {
		return a * b / gcd(a, b);
	}

}
