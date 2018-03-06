package com.daimens.algorithm.march;

/**
 * 
 * @author DemonSong
 * 537.Complex Number Multiplication
 * Given two strings representing two complex number.
 * You need to return a string representing their multiplication. Note i^2 = -1 according to the definition.
 * 
 * Example 1:
 * Input: "1+1i", "1+1i"
 * Output: "0+2i"
 * Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
 * Example 2:
 * Input: "1+-1i", "1+-1i"
 * Output: "0+-2i"
 * Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.
 * 
 * Note:
 * 1. The input strings will not have extra blank.
 * 2. The input strings will be given in the form of a+bi, where the integer a and b will both belong to the range of [-100, 100]. 
 * And the output should be also in this form.
 *
 */
public class SolutionDay26_537 {
	
//	public String complexNumberMultiply(String a, String b) {
//		
//		int a1 = 0;
//		int a2 = 0;
//		
//		StringBuilder a1Builder = new StringBuilder();
//		StringBuilder a2Builder = new StringBuilder();
//		for(int i = 0; i < a.length(); i++){
//			if(a.charAt(i) != '+')
//				a1Builder.append(a.charAt(i));
//			if(a.charAt(i) == '+'){
//				a2Builder.append(a.substring(i+1,a.length()-1));
//				break;
//			}
//		}
//		
//		a1 = Integer.parseInt(a1Builder.toString());
//		a2 = Integer.parseInt(a2Builder.toString());
//		
//		
//		int b1 = 0;
//		int b2 = 0;
//		
//		StringBuilder b1Builder = new StringBuilder();
//		StringBuilder b2Builder = new StringBuilder();
//		for(int i = 0; i < b.length(); i++){
//			if(b.charAt(i) != '+')
//				b1Builder.append(b.charAt(i));
//			
//			if(b.charAt(i) == '+'){
//				b2Builder.append(b.substring(i+1,b.length()-1));
//				break;
//			}
//		}
//		
//		b1 = Integer.parseInt(b1Builder.toString());
//		b2 = Integer.parseInt(b2Builder.toString());
//		
//		StringBuilder res = new StringBuilder();
//		
//		int c1 = a1*b1 - a2*b2;
//		int c2 = a1 * b2 + a2 * b1;
//		
//		res.append(String.valueOf(c1));
//		res.append("+");
//		res.append(String.valueOf(c2));
//		res.append("i");
//		
//        return res.toString();
//    }
	
//	public String complexNumberMultiply(String a, String b) {
//		
//		int a1 = Integer.parseInt(a.split("\\+|i")[0]);
//		int a2 = Integer.parseInt(a.split("\\+|i")[1]);
//		
//		int b1 = Integer.parseInt(b.split("\\+|i")[0]);
//		int b2 = Integer.parseInt(b.split("\\+|i")[1]);
//
//		String res = "";
//
//		int c1 = a1 * b1 - a2 * b2;
//		int c2 = a1 * b2 + a2 * b1;
//
//		res = String.valueOf(c1)+"+"+String.valueOf(c2)+"i"; 
//		
//		return res;
//	}
	
	public String complexNumberMultiply(String a, String b) {
		int[] aa = parseComplex(a);
        int[] bb = parseComplex(b);
        
        int c1 = aa[0] * bb[0] - aa[1] * bb[1];
        int c2 = aa[0] * bb[1] + aa[1] * bb[0];
        
		return String.valueOf(c1)+"+"+String.valueOf(c2)+"i";
	}
	
	private int[] parseComplex(String complex){
		
		int[] res = new int[2];
		
		int i = 0;
		for(; complex.charAt(i) != '+'; i++);
		
		res[0] = Integer.parseInt(complex.substring(0,i)); 
		res[1] = Integer.parseInt(complex.substring(i+1,complex.length()-1));
		
		return res;
	}
	
	
	public static void main(String[] args) {
		SolutionDay26_537 day = new SolutionDay26_537();
		
		String a = "1+1i";
		String b = "1+1i";
		
		day.complexNumberMultiply(a, b);
		
	}

}
