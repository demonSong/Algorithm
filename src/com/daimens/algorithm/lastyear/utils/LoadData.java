package com.daimens.algorithm.utils;

import java.util.ArrayList;
import java.util.List;

import sun.security.util.Length;

public abstract class LoadData {
	
	
	
	public static String[] loadStringArray(String path){
		List<String> res = new ArrayList<>();
        try {
            In in = new In(path);
            while (!in.isEmpty()) {
                String s = in.readLine();
                res.add(s);
            }
        }
        catch (IllegalArgumentException e) {
            System.out.println(e);
        }
        
        return res.toArray(new String[0]);
	}
	
	
	public static Bag[] loadAll01BagData(){
		Bag[] bags = new Bag[10];
		for (int i = 0; i < 10; i++){
			bags[i] = load01BagData(i);
		}
		return bags;
	}
	
	public static Bag load01BagData(int id){
		
		String path = "./data/01bagTest/beibao";
        try {
            In in = new In(path+id+".in");
            String s = in.readLine();
            String[] tmp = s.split(" ");
            int capacity = Integer.parseInt(tmp[0]);
            int len = Integer.parseInt(tmp[1]);
            
            int[] w = new int[len];
            int[] v = new int[len];
            
            Bag bag = new Bag();
            for (int i = 0; i < len; i++){
            	String data = in.readLine();
            	w[i] = Integer.parseInt(data.split(" ")[0]);
            	v[i] = Integer.parseInt(data.split(" ")[1]);
            }
            
            bag.capacity = capacity;
            bag.w = w;
            bag.v = v;
            
            in.close();
            
            in = new In(path+id+".out");
            int answer = in.readInt();
            bag.answer  = answer;
            
            return bag;
        }
        catch (IllegalArgumentException e) {
            System.out.println(e);
            return null;
        }
	}
	
	public static void main(String[] args) {
		String[] ans = loadStringArray("./data/1000words.txt");
	}
}



