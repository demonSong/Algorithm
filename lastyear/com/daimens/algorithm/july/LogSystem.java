package com.daimens.algorithm.july;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogSystem {
	
//	class Log{
//		String year;
//		String month;
//		String day;
//		String hour;
//		String minute;
//		String second;
//		int id;
//	}
//	
//	List<Log> logs;
//	public LogSystem() {
//		logs = new ArrayList<>();
//    }
//    
//    public void put(int id, String timestamp) {
//        Log log = new Log();
//        log.id = id;
//        String[] times = timestamp.split(":");
//        log.year = times[0];
//        log.month = times[0] + times[1];
//        log.day = times[0] + times[1] + times[2];
//        log.hour = times[0] + times[1] + times[2] + times[3];
//        log.minute = times[0] + times[1] + times[2] + times[3] + times[4];
//        log.second = times[0] + times[1] + times[2] + times[3] + times[4] + times[5];
//        logs.add(log);
//    }
//    
//    public List<Integer> retrieve(String s, String e, String gra) {
//        String[] ss = s.split(":");
//        String[] es = e.split(":");
//        StringBuilder stcmp = new StringBuilder();
//        StringBuilder etcmp = new StringBuilder();
//        List<Integer> ans = new ArrayList<>();
//        if (gra.equals("Year")){
//        	stcmp.append(ss[0]);
//        	etcmp.append(es[0]);
//        	for (int i = 0; i < logs.size(); ++i){
//        		if (logs.get(i).year.compareTo(stcmp.toString()) >= 0 && logs.get(i).year.compareTo(etcmp.toString()) <= 0){
//        			ans.add(logs.get(i).id);
//        		}
//        	}
//        }
//        if (gra.equals("Month")){
//        	stcmp.append(ss[0] + ss[1]);
//        	etcmp.append(es[0] + es[1]);
//        	for (int i = 0; i < logs.size(); ++i){
//        		if (logs.get(i).month.compareTo(stcmp.toString()) >= 0 && logs.get(i).month.compareTo(etcmp.toString()) <= 0){
//        			ans.add(logs.get(i).id);
//        		}
//        	}
//        }
//        if (gra.equals("Day")){
//        	stcmp.append(ss[0] + ss[1] + ss[2]);
//        	etcmp.append(es[0] + es[1] + es[2]);
//        	for (int i = 0; i < logs.size(); ++i){
//        		if (logs.get(i).day.compareTo(stcmp.toString()) >= 0 && logs.get(i).day.compareTo(etcmp.toString()) <= 0){
//        			ans.add(logs.get(i).id);
//        		}
//        	}
//        }
//        if (gra.equals("Hour")){
//        	stcmp.append(ss[0] + ss[1] + ss[2] + ss[3]);
//        	etcmp.append(es[0] + es[1] + es[2] + es[3]);
//        	for (int i = 0; i < logs.size(); ++i){
//        		if (logs.get(i).hour.compareTo(stcmp.toString()) >= 0 && logs.get(i).hour.compareTo(etcmp.toString()) <= 0){
//        			ans.add(logs.get(i).id);
//        		}
//        	}
//        }
//        if (gra.equals("Minute")){
//        	stcmp.append(ss[0] + ss[1] + ss[2] + ss[3] + ss[4]);
//        	etcmp.append(es[0] + es[1] + es[2] + es[3] + es[4]);
//        	for (int i = 0; i < logs.size(); ++i){
//        		if (logs.get(i).minute.compareTo(stcmp.toString()) >= 0 && logs.get(i).minute.compareTo(etcmp.toString()) <= 0){
//        			ans.add(logs.get(i).id);
//        		}
//        	}
//        }
//        if (gra.equals("Second")){
//        	stcmp.append(ss[0] + ss[1] + ss[2] + ss[3] + ss[4] + ss[5]);
//        	etcmp.append(es[0] + es[1] + es[2] + es[3] + es[4] + es[5]);
//        	for (int i = 0; i < logs.size(); ++i){
//        		if (logs.get(i).second.compareTo(stcmp.toString()) >= 0 && logs.get(i).second.compareTo(etcmp.toString()) <= 0){
//        			ans.add(logs.get(i).id);
//        		}
//        	}
//        }
//    	return ans;
//    }
	
	List<String[]> logs;
	public LogSystem() {
        logs = new ArrayList<>();
    }
    
    public void put(int id, String timestamp) {
        logs.add(new String[]{id + "", timestamp});
    }
    
    List<String> units = Arrays.asList("Year", "Month", "Day", "Hour", "Minute", "Second");
    int[] idices = {4, 7, 10, 13, 16, 19};
    public List<Integer> retrieve(String s, String e, String gra) {
    	List<Integer> ans = new ArrayList<>();
    	int idx = idices[units.indexOf(gra)];
    	for (String[] log : logs){
    		if (log[1].substring(0, idx).compareTo(s.substring(0, idx)) >= 0 &&
    				log[1].substring(0, idx).compareTo(e.substring(0,idx)) <= 0) ans.add(Integer.parseInt(log[0]));
    	}
    	return ans;
    }
}
