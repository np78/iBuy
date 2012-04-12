/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs477.linn;

/**
 * Item - acts as an Item object
 * @author Nicole Williams
 * @version 4/3/12
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
    
    public boolean isCheckedOff(){
        return checked_off;
    }
}