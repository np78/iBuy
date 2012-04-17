/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs477.linn;

/**
 * Item - acts as an Item object
 * @author N. Williams (Team LINN)
 * @version 4/11/12
 */
public class Item {
    private String item_name;
    private String item_category;
    private String item_store;
    private int item_priority;
    private boolean checked_off;
    
    public Item(String name, String category, String store, int priority){
        item_name = name;
        item_category = category;
        item_store = store;
        item_priority = priority;
        checked_off = false;
    }
    
    public String getName(){
        return item_name;
    }
    
    public String getCategory(){
        return item_category;
    }
    
    public String getStore(){
    	return item_store;
    }
    
    public int getPriority(){
    	return item_priority;
    }
    
    public void setCheckedOff(String b){
    	if(b.equals("true")){
    		checked_off = true;
    	}
    	else{
    		checked_off = false;
    	}
    }
   
    public boolean isCheckedOff(){
        return checked_off;
    }
    
    /*
     * getItemData
     * 
     * Returns a String of the data to add to a (.txt) list file.
     * NAME CATEGORY STORE PRIORITY CHECKED-OFF
     */
    public String getItemData(){
    	String data = item_name.replaceAll(" ", "_") + " " + item_category.replaceAll(" ", "_") + " " + item_store.replaceAll(" ", "_") + " " + item_priority + " " + checked_off;
    	return data;
    }
}