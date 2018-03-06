package com.daimens.algorithm.may;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

/**
 * 
 * @author DemonSong
 * 
 *         0121. Seven Puzzle
 * 
 *         7 パズル 7 パズルは 8
 *         つの正方形のカードとこれらのカードがぴたりと収まる枠で構成されています。それぞれのカードには、互いに区別できるように 0, 1, 2,
 *         ..., 7 と番号がつけられています。枠には、縦に 2 個、横に 4 個のカードを並べることができます。
 * 
 *         7 パズルを始めるときには、まず枠にすべてのカードを入れます。枠のなかで 0
 *         のカードだけは、上下左右に隣接するカードと位置を交換することができます。たとえば、枠の状態が図(a) のときに、0
 *         のカードの右に隣接した、7 のカードと位置を交換すれば、図(b) の状態になります。あるいは、図(a) の状態から 0
 *         のカードの下に隣接した 2 のカードと位置を交換すれば図(c) の状態になります。図(a) の状態で 0
 *         のカードと上下左右に隣接するカードは 7 と 2 のカードだけなので、これ以外の位置の入れ替えは許されません。
 * 
 *         ゲームの目的は、カードをきれいに整列して図(d)
 *         の状態にすることです。最初の状態を入力とし、カードをきれいに整列するまでに、必要な最小手数を出力するプログラムを作成してください。ただし、入力されたカードの状態からは図(d)
 *         の状態に移ることは可能であるとします。
 * 
 *         入力データは、1 行に 8 つの数字が空白区切りで与えられます。これらは、最初の状態のカードの並びを表します。例えば、図(a)
 *         の数字表現は0 7 3 4 2 5 1 6 に、図(c) は 2 7 3 4 0 5 1 6 となります。
 * 
 * 
 *         図(a) 0 7 3 4 2 5 1 6 の場合 図(b) 7 0 3 4 2 5 1 6 の場合
 * 
 * 
 * 
 *         図(c) 2 7 3 4 0 5 1 6 の場合 図(d) 0 1 2 3 4 5 6 7 (最終状態)
 * 
 *         Input 上記形式で複数のパズルが与えられます。入力の最後まで処理してください。 与えられるパズルの数は 1,000 以下です。
 * 
 *         Output 各パズルについて、最終状態へ移行する最小手数を１行に出力してください。
 * 
 *         Sample Input 0 1 2 3 4 5 6 7 1 0 2 3 4 5 6 7 7 6 5 4 3 2 1 0 Output
 *         for the Sample Input 0 1 28
 *
 */
public class SolutionDay18_A0121 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Map<String,Integer> map = bfs();
		while(in.hasNext()){
			String key = "";
			for (int i = 0; i < 2; i++){
				for (int j = 0; j < 4; j++){
					key += in.nextInt();
				}
			}
			System.out.println(map.get(key));
		}
		in.close();
	}
	
	static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	private static Map<String, Integer> bfs(){
		Map<String, Integer> map = new HashMap<>();
		map.put("01234567", 0);
		char[][] init = {{'0','1','2','3'},{'4','5','6','7'}};
		Queue<char[][]> queue = new LinkedList<>();
		queue.offer(init);
		
		while (!queue.isEmpty()){
			char[][] now = queue.poll();
			String nows = "";
			
			int[] cur = new int[2];
			for (int i = 0; i < 2; i++){
				for (int j = 0; j < 4; j++){
					nows += now[i][j]-'0';
					if (now[i][j] == '0'){
						cur[0] = i;
						cur[1] = j;
					}
				}
			}
			
			for (int[] d : dir){
				int nx = cur[0] + d[0];
				int ny = cur[1] + d[1];
				if (nx >= 0 && nx < 2 && ny >= 0 && ny < 4){
					char[][] next = clone(now);
					swap(next, cur[0], cur[1], nx, ny);
					char[] ss = new char[8];
					for (int i = 0, k = 0; i < 2; i++){
						for (int j = 0; j < 4; j++){
							ss[k++] = next[i][j];
						}
					}
					String nn = new String(ss);
					if (!map.containsKey(nn)){
						map.put(nn, map.get(nows)+1);
						queue.offer(next);
					}
				}
			}
		}
		return map;
	}
	
	private static char[][] clone(char[][] now){
		int row = now.length;
		int col = now[0].length;
		char[][] clone = new char[row][col];
		for (int i = 0; i < row; i++){
			for (int j = 0; j < col; j++){
				clone[i][j] = now[i][j];
			}
		}
		return clone;
	}
	
	private static void swap(char[][] map, int x1, int y1, int x2, int y2){
		char tmp = map[x1][y1];
		map[x1][y1] = map[x2][y2];
		map[x2][y2] = tmp;
	}
	
}
