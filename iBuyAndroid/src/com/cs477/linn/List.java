/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs477.linn;


import java.util.Date;
import java.util.LinkedList;


/**
 * List - acts as a list object that contains Item objects
 * @author N. Williams (Team LINN)
 * @version 4/11/12
 */
public class List {
    private String user; //name of user that list belongs
    private String list_name;
    private String list_total;
    private String list_date;
    private LinkedList<Item> items; //list of items in the list
    
    public List(String u, String name){
        user = u;
        list_name = name;
        list_total = "0.00";
        Date d = new Date(System.currentTimeMillis());
        list_date = d.toString().replaceAll(" ", "_");
        items = new LinkedList<Item>(); //empty list
    }
    
    public String getUser(){
        return user;
    }
    
    public String getName(){
        return list_name;
    }
    
    public String getTotal(){
    	return list_total;
    }
    
    public String getDate(){
    	return list_date;
    }
    
    public void addItem(Item i){
        items.add(i);
    }
    
    public void removeItem(Item i){
        items.remove(i);
    }
    public LinkedList<Item> getItems(){
    	return items;
    }
    /*
     * newListData
     * 
     * Returns a String of the data to add to lists.txt file.
     */
    public String newListData(){
    	String data = list_name.replaceAll(" ", "_") + " " + list_total + " " + list_date;
    	return data;
    }
    
    public void setName(String name){
    	list_name = name;
    }
    
    public void setUser(String u){
    	user = u;
    }
    public void setTotal(String total){
    	list_total = total;
    }
    public void setDate(String date){
    	list_date = date;
    }
    /*
     * sortItemsByPriority
     * 
     * Sorts the LinkedList of Items by the item's priority.
     */
    public void sortItemsByPriority(){
    	for(int i= items.size(); --i>=0; ){
    		boolean flipped=false;
    		for(int j=0;j<1;j++){
    			if(items.get(j).getPriority() > items.get(j+1).getPriority()){
    				Item T = items.get(j);
    				items.set(j, items.get(j+1));
    				items.set(j+1, T);
    				flipped = true;
    			}
    		}
    		if(!flipped){
    			return;
    		}
    	}
    }
    
}
