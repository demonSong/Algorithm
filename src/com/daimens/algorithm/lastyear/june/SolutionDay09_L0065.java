package com.daimens.algorithm.june;

/**
 * 
 * @author DemonSong
 * 
 *         65. Valid Number
 * 
 *         Validate if a given string is numeric.
 * 
 *         Some examples: "0" => true " 0.1 " => true "abc" => false "1 a" =>
 *         false "2e10" => true Note: It is intended for the problem statement
 *         to be ambiguous. You should gather all requirements up front before
 *         implementing one.
 *
 */
public class SolutionDay09_L0065 {
	
//	int i = 0;
//	int n = 0;
//	char[] c;
//	public boolean isNumber(String s) {
//		if (s.isEmpty()) return false;
//		n = s.length();
//		c = s.toCharArray();
//		
//		boolean isValid = true;
//
//		while (i < n && c[i] == ' ') i++;
//		if (i == n || !(c[i] == '+' || c[i] == '-' || c[i] == '.' || Character.isDigit(c[i]))) return false;
//		
//		//'+' or '-'
//		if (c[i] == '+' || c[i] == '-'){
//			i++;
//		}
//		
//		if (c[i] == '.'){
//			i++;
//
//			if (i < n && c[i] == 'e') return false;
//            
//            if (i < n && Character.isDigit(c[i])){
//                while (i < n && Character.isDigit(c[i])) i++;
//
//			    if (i < n && c[i] == 'e'){
//        			i++;
//                    while (i < n && c[i] == ' ') i++;
//                    if (i == n || ! (Character.isDigit(c[i]) || c[i] == '+' || c[i] == '-')) return false;
//                    
//                    if (i < n && (c[i] == '+' || c[i] == '-')) i++;
//                    if (i == n) return false;
//                    
//                    while (i < n && Character.isDigit(c[i])) i++;
//        			while (i < n && c[i] == ' ') i++;
//        			if (i == n) return true;
//        		    return false;
//		        }
//		        
//		        while (i < n && c[i] == ' ') i++;
//			    if (i == n) return true;
//            }
//            return false;
//		}
//		
//		if (i == n || !Character.isDigit(c[i])) return false;
//		
//		long num = 0;
//		while (i < n && Character.isDigit(c[i])){
//			num = 10 * num + c[i++] - '0';
//		}
//		
//		while (i < n && c[i] == ' ') i++;
//		if (i == n) return isValid;
//		
//		
//		if (!(c[i] == '.' || c[i] == 'e')) return false;
//		
//		if (!Character.isDigit(c[i-1]) && c[i] == '.') return false;
//		if (!Character.isDigit(c[i-1]) && c[i] == 'e') return false;
//		
//		if (c[i] == '.'){
//			i++;
//
//			if (i < n && c[i] == 'e'){
//        			i++;
//                    while (i < n && c[i] == ' ') i++;
//                    if (i == n || ! (Character.isDigit(c[i]) || c[i] == '+' || c[i] == '-')) return false;
//                    
//                    if (i < n && (c[i] == '+' || c[i] == '-')) i++;
//                    if (i == n) return false;
//                    
//                    while (i < n && Character.isDigit(c[i])) i++;
//        			while (i < n && c[i] == ' ') i++;
//        			if (i == n) return true;
//        		    return false;
//		    }	
//            
//            if (i < n && Character.isDigit(c[i])){
//                while (i < n && Character.isDigit(c[i])) i++;
//
//			    if (i < n && c[i] == 'e'){
//        			i++;
//                    while (i < n && c[i] == ' ') i++;
//                    if (i == n || ! (Character.isDigit(c[i]) || c[i] == '+' || c[i] == '-')) return false;
//                    
//                    if (i < n && (c[i] == '+' || c[i] == '-')) i++;
//                    if (i == n) return false;
//                    
//                    while (i < n && Character.isDigit(c[i])) i++;
//        			while (i < n && c[i] == ' ') i++;
//        			if (i == n) return true;
//        		    return false;
//		        }
//		        
//		        while (i < n && c[i] == ' ') i++;
//			    if (i == n) return true;
//			    return false;
//            }
//            
//    		while (i < n && c[i] == ' ') i++;
//			if (i == n) return true;
//            return false;			
//		}
//	    isValid = isEvalid();
//	    return isValid;
//    }
//	
//	private boolean isEvalid(){
//		if (i < n && c[i] == 'e'){
//			i++;
//            while (i < n && c[i] == ' ') i++;
//            if (i == n || ! (Character.isDigit(c[i]) || c[i] == '+' || c[i] == '-')) return false;
//            
//            if (i < n && (c[i] == '+' || c[i] == '-')) i++;
//            if (i == n) return false;
//            
//            while (i < n && Character.isDigit(c[i])) i++;
//			while (i < n && c[i] == ' ') i++;
//			if (i == n) return true;
//		    return false;
//		}
//		return false;
//	}
	
//	static int[][] DFA = {
//			{0,0,0,0,0,0,0,0,0,0},
//			{0,1,2,3,4,0,0,0,0,0},
//			{0,0,0,3,4,0,0,0,0,0},
//			{0,0,0,3,0,5,6,0,0,9},
//			{0,0,0,0,0,5,0,0,0,0},
//			{0,0,0,0,0,5,6,0,0,9},
//			{0,0,0,0,0,0,0,7,8,0},
//			{0,0,0,0,0,0,0,0,8,0},
//			{0,0,0,0,0,0,0,0,8,9},
//			{0,0,0,0,0,0,0,0,0,9}
//	};
//	public boolean isNumber(String s) {
//		if (s.isEmpty()) return false;
//		int n = s.length();
//		char[] c = s.toCharArray();
//		int currentState = 1;
//		for (int i = 0; i < n; ++i){
//			if (c[i] == ' '){
//				if (currentState == 1) currentState = DFA[currentState][1];
//				else if (currentState == 9) currentState = DFA[currentState][9];
//				else if (currentState == 3) currentState = DFA[currentState][9];
//				else if (currentState == 5) currentState = DFA[currentState][9];
//				else if (currentState == 8) currentState = DFA[currentState][9];
//				else return false;
//			}
//			if (c[i] >= '0' && c[i] <= '9'){
//				if (currentState ==1) currentState = DFA[currentState][3];
//				else if (currentState ==2) currentState = DFA[currentState][3];
//				else if (currentState ==3) currentState = DFA[currentState][3];
//				else if (currentState ==4) currentState = DFA[currentState][5];
//				else if (currentState ==5) currentState = DFA[currentState][5];
//				else if (currentState ==7) currentState = DFA[currentState][8];
//				else if (currentState ==6) currentState = DFA[currentState][8];
//				else if (currentState ==8) currentState = DFA[currentState][8];
//				else return false;
//			}
//			if (c[i] == '.'){
//				if (currentState ==1) currentState = DFA[currentState][4];
//				else if (currentState ==2) currentState = DFA[currentState][4];
//				else if (currentState ==3) currentState = DFA[currentState][5];
//				else return false;
//			}
//			if (c[i] == '+' || c[i] == '-'){
//				if (currentState ==1) currentState = DFA[currentState][2];
//				else if (currentState ==6) currentState = DFA[currentState][7];
//				else return false;
//			}
//			if (c[i] == 'e'){
//				if (currentState == 3) currentState = DFA[currentState][6];
//				else if (currentState == 5) currentState = DFA[currentState][6];
//				else return false;
//			}
//		}
//		return currentState == 9 || currentState == 3 || currentState == 5 || currentState == 8;
//	}
	
//	static int[][] DFA = {
//			{0,0,0,0,0,0,0,0,0,0},
//			{0,1,2,3,4,0,0,0,0,0},
//			{0,0,0,3,4,0,0,0,0,0},
//			{0,0,0,3,0,5,6,0,0,9},
//			{0,0,0,0,0,5,0,0,0,0},
//			{0,0,0,0,0,5,6,0,0,9},
//			{0,0,0,0,0,0,0,7,8,0},
//			{0,0,0,0,0,0,0,0,8,0},
//			{0,0,0,0,0,0,0,0,8,9},
//			{0,0,0,0,0,0,0,0,0,9}
//	};
//	public boolean isNumber(String s) {
//		if (s.isEmpty()) return false;
//		int n = s.length();
//		char[] c = s.toCharArray();
//		int currentState = 1;
//		for (int i = 0; i < n; ++i){
//			if (c[i] == ' '){
//				if (currentState == 1) currentState = DFA[currentState][1];
//				else if (currentState == 9) currentState = DFA[currentState][9];
//				else if (currentState == 3) currentState = DFA[currentState][9];
//				else if (currentState == 5) currentState = DFA[currentState][9];
//				else if (currentState == 8) currentState = DFA[currentState][9];
//				else return false;
//			}
//			else if (c[i] >= '0' && c[i] <= '9'){
//				if (currentState ==1) currentState = DFA[currentState][3];
//				else if (currentState ==2) currentState = DFA[currentState][3];
//				else if (currentState ==3) currentState = DFA[currentState][3];
//				else if (currentState ==4) currentState = DFA[currentState][5];
//				else if (currentState ==5) currentState = DFA[currentState][5];
//				else if (currentState ==7) currentState = DFA[currentState][8];
//				else if (currentState ==6) currentState = DFA[currentState][8];
//				else if (currentState ==8) currentState = DFA[currentState][8];
//				else return false;
//			}
//			else if (c[i] == '.'){
//				if (currentState ==1) currentState = DFA[currentState][4];
//				else if (currentState ==2) currentState = DFA[currentState][4];
//				else if (currentState ==3) currentState = DFA[currentState][5];
//				else return false;
//			}
//			else if (c[i] == '+' || c[i] == '-'){
//				if (currentState ==1) currentState = DFA[currentState][2];
//				else if (currentState ==6) currentState = DFA[currentState][7];
//				else return false;
//			}
//			else if (c[i] == 'e'){
//				if (currentState == 3) currentState = DFA[currentState][6];
//				else if (currentState == 5) currentState = DFA[currentState][6];
//				else return false;
//			}
//			else return false;
//		}
//		return currentState == 9 || currentState == 3 || currentState == 5 || currentState == 8;
//	}
	
	public boolean isNumber(String s) {
		if (s.isEmpty()) return false;
		int n = s.length();
		char[] c = s.toCharArray();
		int currentState = 1;
		for (int i = 0; i < n; ++i){
			if (c[i] == ' '){
				if (currentState == 1) currentState = 1;
				else if (currentState == 9 || currentState == 3) currentState = 9;
				else if (currentState == 5 || currentState == 8) currentState = 9;
				else return false;
			}
			else if (c[i] >= '0' && c[i] <= '9'){
				if (currentState ==1 || currentState == 2 || currentState == 3) currentState = 3;
				else if (currentState ==4 || currentState == 5) currentState = 5;
				else if (currentState ==6 || currentState == 7 || currentState == 8) currentState = 8;
				else return false;
			}
			else if (c[i] == '.'){
				if (currentState ==1 || currentState == 2) currentState = 4;
				else if (currentState ==3) currentState = 5;
				else return false;
			}
			else if (c[i] == '+' || c[i] == '-'){
				if (currentState ==1) currentState = 2;
				else if (currentState ==6) currentState = 7;
				else return false;
			}
			else if (c[i] == 'e'){
				if (currentState == 3 || currentState == 5) currentState = 6;
				else return false;
			}
			else return false;
		}
		return currentState == 9 || currentState == 3 || currentState == 5 || currentState == 8;
	}
	
	public static void main(String[] args) {
		SolutionDay09_L0065 day = new SolutionDay09_L0065();
		System.out.println(day.isNumber("-1."));
	}
}
