package com.daimens.algorithm.august;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            int w = in.nextInt();
            int h = in.nextInt();
            if (w == 0 && h == 0) {
                break;
            }
            int n = in.nextInt();
            int[] x1 = new int[n];
            int[] y1 = new int[n];
            int[] x2 = new int[n];
            int[] y2 = new int[n];
            for (int i = 0; i < n; i++) {
                x1[i] = in.nextInt();
                y1[i] = in.nextInt();
                x2[i] = in.nextInt();
                y2[i] = in.nextInt();
            }
             
            w = compress(x1, x2, w);
            h = compress(y1, y2, h);
             
            boolean[][] a = new boolean[w][h];
            for (int i = 0; i < n; i++) {
                for (int x = x1[i]; x < x2[i]; x++) {
                    for (int y = y1[i]; y < y2[i]; y++) {
                        a[x][y] = true;
                    }
                }
            }
             
            int ans = 0;
            for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {
                    if (a[x][y]) {
                        continue;
                    }
                    ans++;
                    a[x][y] = true;
                    ArrayDeque<int[]> stack = new ArrayDeque<int[]>();
                    stack.push(new int[]{x, y});
                    while (!stack.isEmpty()) {
                        int[] xy = stack.pop();
                        int[] dx = {-1, 0, 1, 0};
                        int[] dy = {0, -1, 0, 1};
                        for (int j = 0; j < 4; j++) {
                            int _x = xy[0] + dx[j];
                            int _y = xy[1] + dy[j];
                            if (_x >= 0 && _x < w && _y >= 0 && _y < h && !a[_x][_y]) {
                                a[_x][_y] = true;
                                stack.push(new int[]{_x, _y});
                            }
                        }
                    }
                }
            }
             
            System.out.println(ans);
        }
    }
     
    static int compress(int[] x1, int[] x2, int w) {
        int n = x1.length;
        TreeSet<Integer> set = new TreeSet<Integer>();
        set.add(0);
        set.add(w);
        for (int i = 0; i < n; i++) {
            set.add(x1[i]);
            set.add(x2[i]);
        }
        Integer[] x = set.toArray(new Integer[0]);
        for (int i = 0; i < n; i++) {
            x1[i] = Arrays.binarySearch(x, x1[i]);
            x2[i] = Arrays.binarySearch(x, x2[i]);
        }
        return x.length - 1;
    }
}