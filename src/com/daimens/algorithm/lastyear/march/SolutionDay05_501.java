package com.daimens.algorithm.march;

/**
 * 
 * @author DemonSong
 * 531.Lonely Pixel I
 * Given a picture consisting of black and white pixels,find the number of black lonely pixels.
 * The picture is represented by a 2D char array consisting of 'B' and 'W',which means black
 * and white pixels respectively.
 * A black lonely pixel is character 'B' that located at a specific position where the same 
 * row and same column don't have any other black pixels.
 * Example:
 * Input:
 * [['W', 'W', 'B'],
 *  ['W', 'B', 'W'],
 *  ['B', 'W', 'W']]
 * Output: 3
 * Explanation: All the three 'B's are black lonely pixels.
 * Note:
 * 1. The range of width and height of the input 2D array is [1500].
 *
 */
public class SolutionDay05_501 {
	
	public int findLonelyPixel(char[][] picture) {

		int sum = 0;
		for (int row = 0; row < picture.length; row++) {
			int[] count = new int[26];
			for (int col = 0; col < picture[row].length; col++) {
				count[picture[row][col] - 'A']++;
			}
			if (count['B' - 'A'] == 1) {
				sum++;
			}
		}

		for (int col = 0; col< picture[0].length; col++) {
			int[] count = new int[26];
			for (int row = 0; row < picture.length; row++) {
				count[picture[row][col] - 'A']++;
			}
			if (count['B' - 'A'] > 1) {
				sum -= count['B' - 'A'];
			}
		}

		return sum < 0 ? 0 : sum;
	}
	
	public static void main(String[] args) {
		SolutionDay05_501 day = new SolutionDay05_501();
		
	}

}
