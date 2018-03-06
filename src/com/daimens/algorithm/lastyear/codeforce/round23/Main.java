package com.daimens.algorithm.codeforce.round23;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 * Built using CHelper plug-in Actual solution is at the top
 *
 * @author Shurikat
 */
public class Main {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		B solver = new B();
		solver.solve(1, in, out);
		out.close();
	}

	static class B {
		public void solve(int testNumber, InputReader in, PrintWriter out) {
			int n = in.nextInt();
			int[] a = in.getIntArray1D(n);
			Arrays.sort(a);

			int cnt1;
			int cnt2;
			int cnt3;
			{
				int[][] mins = new int[][] { { -1, 0 }, { -1, 0 }, { -1, 0 } };
				int minId = 0;
				mins[minId][0] = a[0];
				mins[minId][1] = 0;
				for (int i : a) {
					if (i > mins[minId][0]) {
						++minId;
						if (minId == 3) {
							break;
						}
						mins[minId][0] = i;
						mins[minId][1] = 1;
					} else {
						mins[minId][1]++;
					}
				}

				cnt1 = mins[0][1];
				cnt2 = mins[1][1];
				cnt3 = mins[2][1];
			}

			long ans = 1;
			if (cnt1 >= 3) {
				ans = (long) cnt1 * (cnt1 - 1) * (cnt1 - 2) / 6;
			} else if (cnt1 + cnt2 >= 3) {
				if (cnt1 == 1) {
					ans = (long) cnt1 * cnt2 * (cnt2 - 1) / 2;
				} else {
					ans = (long) cnt2 * cnt1 * (cnt1 - 1) / 2;
				}
			} else if (cnt1 + cnt2 + cnt3 >= 3) {
				ans = (long) cnt1 * cnt2 * cnt3;
			}
			out.println(ans);
		}

	}

	static class InputReader implements AutoCloseable {
		private final InputStream in;
		private int capacity;
		private byte[] buffer;
		private int len = 0;
		private int cur = 0;

		public InputReader(InputStream stream) {
			this(stream, 100_000);
		}

		public InputReader(InputStream stream, int capacity) {
			this.in = stream;
			this.capacity = capacity;
			buffer = new byte[capacity];
		}

		private boolean update() {
			if (cur >= len) {
				try {
					cur = 0;
					len = in.read(buffer, 0, capacity);
					if (len <= 0)
						return false;
				} catch (IOException e) {
					throw new InputMismatchException();
				}
			}
			return true;
		}

		private int read() {
			if (update())
				return buffer[cur++];
			else
				return -1;
		}

		private boolean isSpace(int c) {
			return c == '\n' || c == '\t' || c == '\r' || c == ' ';
		}

		private int readSkipSpace() {
			int c;
			do {
				c = read();
			} while (isSpace(c));
			return c;
		}

		public int nextInt() {
			int c = readSkipSpace();
			if (c < 0)
				throw new InputMismatchException();
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			int res = 0;
			do {
				if (c == -1)
					break;
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res = res * 10 + c - '0';
				if (res < 0)
					throw new InputMismatchException();
				c = read();
			} while (!isSpace(c));
			res *= sgn;
			return res;
		}

		public int[] getIntArray1D(int n) {
			int[] array = new int[n];
			for (int i = 0; i < n; ++i) {
				array[i] = nextInt();
			}
			return array;
		}

		public void close() throws IOException {
			in.close();
		}

	}
}
