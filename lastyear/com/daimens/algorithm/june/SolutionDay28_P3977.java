package com.daimens.algorithm.june;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;

public class SolutionDay28_P3977 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/3977.txt";
	
//	void solve() {
//		while(true){
//			int n = ni();
//			if (n == 0) break;
//			long[] arra = new long[n];
//			for (int i = 0; i < n; ++i) arra[i] = nl();
//			
//			long[] neg = new long[n];
//			long[] pos = new long[n];
//			int PN = 0, NN = 0;
//			
//			for (int i = 0; i < n; ++i){
//				if (arra[i] >= 0){
//					pos[PN++] = arra[i];
//				}
//				else{
//					neg[NN++] = arra[i];
//				}
//			}
//			
//			if (PN == 0){
//				out.println(neg[0] + " " + 1);
//				continue;
//			}
//			
//			if (NN == 0 || pos[0] == 0){
//				out.println(pos[0] + " " + 1);
//				continue;
//			}
//			
//			Sum[] sums = new Sum[(1 << PN) - 1];
//			for (int i = 1; i < 1 << PN; ++i){
//				long sum = 0;
//				int cnt = 0;
//				for (int j = 0; j < PN; ++j){
//					if ((i & (1 << j)) != 0){
//						sum += pos[j];
//						cnt++;
//					}
//				}
//				sums[i-1] = new Sum();
//				sums[i-1].sum = sum;
//				sums[i-1].cnt = cnt;
//			}
//			Arrays.sort(sums, new Comparator<Sum>() {
//				@Override
//				public int compare(Sum o1, Sum o2) {
//					return o1.sum < o2.sum ? -1 : 1;
//				}
//			});
//			
//			long min = 1 << 30;
//			int count = 0;
//			for (int i = 1; i < 1 << NN; ++i){
//				long key = 0;
//				int cnt = 0;
//				for (int j = 0; j < NN; ++j){
//					if ((i & (1 << j)) != 0){
//						key += neg[j];
//						cnt++;
//					}
//				}
//				int index = binarySearch(sums, -key);
//				if (index == -1){
//					if (Math.abs(key + sums[0].sum) < min){
//						min = Math.abs(key + sums[0].sum);
//						count = cnt + sums[0].cnt;
//					}
//				}
//				else{
//					if (Math.abs(key + sums[index].sum) < min){
//						min = Math.abs(key + sums[index].sum);
//						count = cnt + sums[index].cnt;
//					}
//					if ((index + 1 != (1 << PN) - 1) && Math.abs(key + sums[index + 1].sum) < min){
//						min = Math.abs(key + sums[index + 1].sum);
//						count = cnt + sums[index + 1].cnt;
//					}
//				}
//			}
//			out.println(min + " " + count);
//		}
//	}
	
	
//	void solve() {
//		while(true){
//			int n = ni();
//			if (n == 0) break;
//			long[] arra = new long[n];
//			for (int i = 0; i < n; ++i) arra[i] = nl();
//			
//			long[] LL = new long[n];
//			long[] RR = new long[n];
//			int LN = 0, RN = 0;
//			
//			for (int i = 0; i < n; i += 2){
//				LL[LN++] = arra[i];
//				if (i + 1 < n) RR[RN++] = arra[i + 1];
//			}
//			
//			if (RN == 0){
//				out.println(LL[0] + " " + 1);
//				continue;
//			}
//			
//			Sum[] sums = new Sum[1 << RN];
//			for (int i = 0; i < 1 << RN; ++i){
//				long sum = 0;
//				int cnt = 0;
//				for (int j = 0; j < RN; ++j){
//					if((i & (1 << j)) != 0){
//						sum += RR[j];
//						cnt++;
//					}
//				}
//				sums[i] = new Sum();
//				sums[i].sum = sum;
//				sums[i].cnt = sum < 0 ? -cnt : cnt;
//			}
//			
//			Arrays.sort(sums, new Comparator<Sum>() {
//				@Override
//				public int compare(Sum o1, Sum o2) {
//					return o1.sum == o2.sum ? o1.cnt - o2.cnt : (o1.sum < o2.sum ? -1 : 1);
//				}
//			});
//			
//			long min = 1 << 30;
//			int count = 0;
//			for (int i = 0; i < 1 << LN; ++i){
//				long key = 0;
//				int cnt = 0;
//				for (int j = 0; j < LN; ++j){
//					if((i & (1 << j)) != 0){
//						key += LL[j];
//						cnt++;
//					}
//				}
//				
//				int lo = lowBound(sums, key);
//				if (lo != -1){
//					if (cnt - sums[lo].cnt == 0) continue;
//					if (-(sums[lo].sum + key) < min){
//						min = - sums[lo].sum - key;
//						count = cnt - sums[lo].cnt;
//					}else if (sums[lo].sum + key == -min && cnt - sums[lo].cnt < count){
//						count = cnt - sums[lo].cnt;
//					}
//				}
//				int hi = upBound(sums, key);
//				if (hi != -1){
//					if (sums[hi].cnt + cnt == 0) continue;
//					if (sums[hi].sum + key < min){
//						min = sums[hi].sum + key;
//						count = cnt + sums[hi].cnt;
//					}else if (sums[hi].sum + key == min && cnt + sums[hi].cnt < count){
//						count = cnt + sums[hi].cnt;
//					}
//				}
//			}
//			out.println(min + " " + count);
//		}
//	}
	
//	void solve() {
//		while(true){
//			int n = ni();
//			if (n == 0) break;
//			long[] arra = new long[n];
//			for (int i = 0; i < n; ++i) arra[i] = nl();
//			
//			long[] LL = new long[n];
//			long[] RR = new long[n];
//			int LN = 0, RN = 0;
//			
//			for (int i = 0; i < n; i += 2){
//				LL[LN++] = arra[i];
//				if (i + 1 < n) RR[RN++] = arra[i + 1];
//			}
//			
//			if (RN == 0){
//				out.println(LL[0] + " " + 1);
//				continue;
//			}
//			
//			Sum[] sums = new Sum[1 << RN];
//			for (int i = 0; i < 1 << RN; ++i){
//				long sum = 0;
//				int cnt = 0;
//				for (int j = 0; j < RN; ++j){
//					if((i & (1 << j)) != 0){
//						sum += RR[j];
//						cnt++;
//					}
//				}
//				sums[i] = new Sum();
//				sums[i].sum = sum;
//				sums[i].cnt = cnt;
//			}
//			
//			Arrays.sort(sums, new Comparator<Sum>() {
//				@Override
//				public int compare(Sum o1, Sum o2) {
//					return o1.sum < o2.sum ? -1 : 1;
//				}
//			});
//			
//			long min = 1 << 30;
//			int count = 0;
//			for (int i = 0; i < 1 << LN; ++i){
//				long key = 0;
//				int cnt = 0;
//				for (int j = 0; j < LN; ++j){
//					if((i & (1 << j)) != 0){
//						key += LL[j];
//						cnt++;
//					}
//				}
//				
//				
//			}
//			out.println(min + " " + count);
//		}
//	}
//	
	
//	//最接近某个value的上界
//	public int upBound(Sum[] sums, long key){
//		
//	}
	
	void solve() {
		while(true){
			int n = ni();
			if (n == 0) break;
			long[] arra = new long[n];
			for (int i = 0; i < n; ++i) arra[i] = nl();
			
			long[] LL = new long[n];
			long[] RR = new long[n];
			int LN = 0, RN = 0;
			
			for (int i = 0; i < n; i += 2){
				LL[LN++] = arra[i];
				if (i + 1 < n) RR[RN++] = arra[i + 1];
			}
			
			if (RN == 0){
				out.println(LL[0] + " " + 1);
				continue;
			}
			
			Sum[] sums = new Sum[1 << RN];
			for (int i = 0; i < 1 << RN; ++i){
				long sum = 0;
				int cnt = 0;
				for (int j = 0; j < RN; ++j){
					if((i & (1 << j)) != 0){
						sum += RR[j];
						cnt++;
					}
				}
				sums[i] = new Sum();
				sums[i].sum = sum;
				sums[i].cnt = cnt;
			}
			
			Arrays.sort(sums, new Comparator<Sum>() {
				@Override
				public int compare(Sum o1, Sum o2) {
					return o1.sum < o2.sum ? -1 : 1;
				}
			});
			
			long min = 1000000000000001l;
			int count = 100;
			for (int i = 0; i < 1 << LN; ++i){
				long key = 0;
				int cnt = 0;
				for (int j = 0; j < LN; ++j){
					if((i & (1 << j)) != 0){
						key += LL[j];
						cnt++;
					}
				}
				int lo = boundLow(sums, sums[lowBound(sums, key)].sum);
				int hi = boundUp(sums, sums[upBound(sums, key)].sum);
				
				for (int l = lo; l <= hi; ++l){
					long sum = sums[l].sum + key < 0 ? -(sums[l].sum + key) : sums[l].sum + key;
					if (sums[l].cnt + cnt == 0) continue;
					if (sum < min){
						min = sum;
						count = sums[l].cnt + cnt;
					}else if (sum == min && sums[l].cnt + cnt < count){
						count = sums[l].cnt + cnt;
					}
				}
			}
			out.println(min + " " + count);
		}
	}
	
	
	class Sum{
		long sum;
		int cnt;
		
		@Override
		public String toString() {
			return String.valueOf(sum);
		}
	}
	
	public int boundUp(Sum[] sums, long key){
		int lf = 0, rt = sums.length - 1;
		while (lf < rt){
			int mid = lf + (rt - lf + 1) / 2;
			if (sums[mid].sum > key) rt = mid - 1;
			else lf = mid;
		}
		if (sums[lf].sum == key) return lf;
		return sums.length - 1;
	}
	
	public int boundLow(Sum[] sums, long key){
		int lf = 0, rt = sums.length - 1;
		while (lf < rt){
			int mid = lf + (rt - lf) / 2;
			if (sums[mid].sum < key) lf = mid + 1;
			else rt = mid;
		}
		if (sums[lf].sum == key) return lf;
		return 0;
	}
	
	
	
	public int upBound(Sum[] sums, long key){
		int lf = 0, rt = sums.length - 1;
		while (lf < rt){
			int mid = lf + (rt - lf) / 2;
			if (sums[mid].sum + key <= 0) lf = mid + 1;
			else rt = mid;
		}
		if (sums[lf].sum + key > 0) return lf;
		return sums.length - 1;
	}
	
	public int lowBound(Sum[] sums, long key){
		int lf = 0, rt = sums.length - 1;
		while (lf < rt){
			int mid = lf + (rt - lf + 1) / 2;
			if (sums[mid].sum + key >= 0) rt = mid - 1;
			else lf = mid;
		}
		if (sums[lf].sum + key < 0) return lf;
		return 0;
	}
	
	void run() throws Exception {
		is = oj ? System.in : new FileInputStream(new File(INPUT));
		out = new PrintWriter(System.out);

		long s = System.currentTimeMillis();
		solve();
		out.flush();
		tr(System.currentTimeMillis() - s + "ms");
	}

	public static void main(String[] args) throws Exception {
		new SolutionDay28_P3977().run();
	}

	private byte[] inbuf = new byte[1024];
	public int lenbuf = 0, ptrbuf = 0;

	private int readByte() {
		if (lenbuf == -1)
			throw new InputMismatchException();
		if (ptrbuf >= lenbuf) {
			ptrbuf = 0;
			try {
				lenbuf = is.read(inbuf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (lenbuf <= 0)
				return -1;
		}
		return inbuf[ptrbuf++];
	}

	private boolean isSpaceChar(int c) {
		return !(c >= 33 && c <= 126);
	}

	private int skip() {
		int b;
		while ((b = readByte()) != -1 && isSpaceChar(b))
			;
		return b;
	}

	private double nd() {
		return Double.parseDouble(ns());
	}

	private char nc() {
		return (char) skip();
	}

	private String ns() {
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b != '
									// ')
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}

	private char[] ns(int n) {
		char[] buf = new char[n];
		int b = skip(), p = 0;
		while (p < n && !(isSpaceChar(b))) {
			buf[p++] = (char) b;
			b = readByte();
		}
		return n == p ? buf : Arrays.copyOf(buf, p);
	}

	private char[][] nm(int n, int m) {
		char[][] map = new char[n][];
		for (int i = 0; i < n; i++)
			map[i] = ns(m);
		return map;
	}

	private int[] na(int n) {
		int[] a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = ni();
		return a;
	}

	private int ni() {
		int num = 0, b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
		if (b == '-') {
			minus = true;
			b = readByte();
		}

		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = readByte();
		}
	}

	private long nl() {
		long num = 0;
		int b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
		if (b == '-') {
			minus = true;
			b = readByte();
		}

		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = readByte();
		}
	}

	private boolean oj = System.getProperty("ONLINE_JUDGE") != null;

	private void tr(Object... o) {
		if (!oj)
			System.out.println(Arrays.deepToString(o));
	}
}


