package jbitlanguage;

import java.util.ArrayList;

public class GlobalVariables {
	protected static int GAME = 0;
	protected static int CUR = Integer.MIN_VALUE;
	protected static ArrayList<Integer> FIRST = new ArrayList<Integer>();
	protected static ArrayList<Integer> SECOND = new ArrayList<Integer>();
	protected static int  FirstResult = 0;
	protected static int SecondResult = 0;
	protected static String dump = "";
	
	public static int getCUR() {
		return CUR;
	}
	
	public static void setCUR(int cUR) {
		CUR = cUR;
	}
	
	public static int getValue(int ID, int index){
		if(ID == 1){
			return SECOND.get(index);
		}
		else {
			return FIRST.get(index);
		}
	}
	
	public static void setFirstValue(int val){
		FIRST.add(val);
	}
	
	public static void setSecondValue(int val){
		SECOND.add(val);
	}
	
	public static void setGameValue(int i){
		GAME = i;
	}
	
	public static int getGameValue(){
		return GAME;
	}
	
	public static int getFirstResult(){
		return FirstResult;
	}
	
	public static int getSecondResult(){
		return SecondResult;
	}
	
	public static void setDump(String str){
		dump += str;
	}
	
	public static String getDump(){
		return dump;
	}
	
	public static void Reinitialize(){
		GAME = 0;
		CUR = Integer.MIN_VALUE;
		FIRST.clear();
		SECOND.clear();
		FirstResult = 0;
		SecondResult = 0;
		dump = "";
	}
	
	public static void Calculate(){
		for(int i = 0; i < GAME; i++){
			// A betray
			if(FIRST.get(i) == 0){
				// B betray
				if(SECOND.get(i) == 0){
					FirstResult += 1;
					SecondResult += 1;
				}
				else {
					FirstResult += 5;
					SecondResult += 0;
				}
			}
			else {
				// B betray
				if(SECOND.get(i) == 0){
					FirstResult += 0;
					SecondResult += 5;
				}
				else {
					FirstResult += 3;
					SecondResult += 3;
				}
			}
		}
	}
	
}
