package com.daimens.algorithm.may;

import java.util.Scanner;

/**
 * 
 * @author DemonSong
 * 
 *         1017. Packets
 * 
 *         Description
 * 
 *         A factory produces products packed in square packets of the same
 *         height h and of the sizes 1*1, 2*2, 3*3, 4*4, 5*5, 6*6. These
 *         products are always delivered to customers in the square parcels of
 *         the same height h as the products have and of the size 6*6. Because
 *         of the expenses it is the interest of the factory as well as of the
 *         customer to minimize the number of parcels necessary to deliver the
 *         ordered products from the factory to the customer. A good program
 *         solving the problem of finding the minimal number of parcels
 *         necessary to deliver the given products according to an order would
 *         save a lot of money. You are asked to make such a program. Input
 * 
 *         The input file consists of several lines specifying orders. Each line
 *         specifies one order. Orders are described by six integers separated
 *         by one space representing successively the number of packets of
 *         individual size from the smallest size 1*1 to the biggest size 6*6.
 *         The end of the input file is indicated by the line containing six
 *         zeros. Output
 * 
 *         The output file contains one line for each line in the input file.
 *         This line contains the minimal number of parcels into which the order
 *         from the corresponding line of the input file can be packed. There is
 *         no line in the output file corresponding to the last ``null'' line of
 *         the input file. Sample Input
 * 
 *         0 0 4 0 0 1 7 5 1 0 0 0 0 0 0 0 0 0 Sample Output
 * 
 *         2 1
 *
 */
public class SolutionDay24_P1017 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (true){
			int[] h = new int[6];
			boolean isOver = true;
			for (int i = 0; i < 6; i++){
				h[i] = in.nextInt();
				if (h[i] != 0) isOver = false;
			}
			if (isOver) break;
			System.out.println(solve(h));
		}
		in.close();
	}
	
	static int[] space = {0,5,3,1};
	private static int solve(int[] h){
		int n = h[5] + h[4] + h[3] + (int)Math.ceil(h[2] / 4.0);
		
		//计算能够放入2*2的个数
		int y = 5 * h[3] + space[h[2] % 4];
		
		if (h[1] > y){
			n += Math.ceil((h[1] - y) / 9.0); //多出来的2*2块，每9个能拼成 6*6块
		}
		
		//计算能够放入1*1的个数
		int x = 36 * n - 36 * h[5] - 25 * h[4] - 16 * h[3] - 9 * h[2] - 4 * h[1];
		
		if (h[0] > x) {
			n += Math.ceil((h[0] - x) / 36.0);
		}
		
		return n;
	}
	
}
