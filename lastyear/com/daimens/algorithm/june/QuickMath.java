package com.daimens.algorithm.june;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.daimens.algorithm.utils.Stopwatch;

public class QuickMath {
	
	private static final String CHARSET_NAME = "UTF-8";
	private static PrintWriter out;
	
	static {
        try {
            out = new PrintWriter(new OutputStreamWriter(System.out, CHARSET_NAME), true);
        }
        catch (UnsupportedEncodingException e) {
            System.out.println(e);
        }
    }
	
	public static void main(String[] args) {
//		long[] test = RandomUtil.randomLongArray(2, 10000);
//		Stopwatch watch = new Stopwatch();
//		out = new PrintWriter(out);
//		for (int i = 0; i < test.length; ++i){
//			System.out.println(quickPow(test[i], 4, 111110000) == test[i] * test[i] * test[i] * test[i]);
//		}
//		double time1 = watch.elapsedTime();
//	    out.printf("(%.2f seconds)\n",time1);
//	    out.flush();
		
//		Stopwatch watch = new Stopwatch();
//		for (int i = 1033; i <= 8179; ++i){
//			if(millerRabin(i, 20)) System.out.println(i);
//		}
//		double time1 = watch.elapsedTime();
//	    out.printf("(%.2f seconds)\n",time1);
//	    out.flush();
		
		double lf = 0;
		double rt = 1;
		int cnt = 0;
		for (int i = 0; i < 50; ++i){
			double mid = (lf + rt) / 2;
			lf = mid;
			System.out.println(++cnt);
			System.out.println(lf);
		}
	    
	    
//		map = new HashMap<>();
//		find(1000,120);
//		System.out.println();
	}
	
	public static boolean odd(int i){
		return (i & 1) != 0;
	}
	
	/**
	 * 解决溢出问题
	 * @param a
	 * @param b
	 * @param mod
	 * @return (a * b) % mod
	 */
	public static long quickMul(long a, long b, long mod){
		long ans = 0;
		while (b != 0){
			if ((b & 1) != 0){
				b--;
				ans = (ans + a) % mod;
			}
			b >>= 1;
			a = (a + a) % mod;
		}
		return ans;
	}
	
	public static long quickPow(long a, long b, long mod){
		long ans = 1;
		while (b != 0){
			if ((b & 1) != 0){
				b--;
				ans = quickMul(ans, a, mod);
			}
			b >>= 1;
			a = quickMul(a, a, mod);
		}
		return ans;
	}
	
	public static boolean witness(long a, long n){
		long tem = n - 1;
		int j = 0;
		while (tem % 2 == 0){
			tem /= 2;
			j++;
		}
		long x = quickPow(a, tem, n);
		if (x == 1 || x == n - 1) return true;
		
		while ((j--) != 0){
			x = quickMul(x, x, n);
			if (x == n - 1) return true;
		}
		return false;
	}
	
	private static long random(long n){
		return Math.abs(new Random().nextLong() % (n + 1));
	}
	
	public static boolean millerRabin(long n, int times){
		if (n == 2) return true;
		if (n < 2 || n % 2 == 0) return false;
		
		for (int i = 0; i < times; ++i){
			long a = random(n - 2) + 1;
			if (!witness(a, n)) return false;
		}
		return true;
	}
	
	public static long gcd(long a, long b){
		if (b == 0) return a;
		else return gcd(b, a % b);
	}
	
	public static long pollardRho(long n, long c){
		long x, y, d, i = 1, k = 2;
		x = random(n - 1) + 1; //[1,n]
		y = x;
		while (true){
			i++;
			x = (quickMul(x, x, n) + c) % n;
			d = gcd(y - x, n);
			if (1 < d && d < n) return d;
			if (y == x) return n;
			if (i == k){
				y = x;
				k <<= 1;
			}
		}
	}
	
	static int number = 0;
	static Map<Long, Integer> map = new HashMap<>();
	
	public static void find(long n, long c){
		if (n == 1) return;
		if (millerRabin(n, 20)){
			map.put(n, map.getOrDefault(n, 0) + 1);
			number++;
			return;
		}
		long p = n;
		long k = c;
		while (p >= n) p = pollardRho(p, c--);
		find(p, k);
		find(n / p, k);
	}
	
}
