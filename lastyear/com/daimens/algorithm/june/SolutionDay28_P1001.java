package com.daimens.algorithm.june;

import java.math.BigDecimal;
import java.util.Scanner;

public class SolutionDay28_P1001 {

	public static void main(String agrs[]) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			BigDecimal n1 = sc.nextBigDecimal();
			int n2 = sc.nextInt();
			n1 = n1.pow(n2);
			String str = n1.stripTrailingZeros().toPlainString();
			if (str.startsWith("0.")) {
				str = str.substring(1);
			}
			System.out.println(str);
		}
		sc.close();
	}

}
