/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs477.linn;

import java.util.Date;
import java.util.LinkedList;

import android.widget.Toast;



/**
 * List - acts as a list object that contains Item objects
 * @author N. Williams (Team LINN)
 * @version 4/28/12
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
    	LinkedList<Item> newList = items;
    	Item temp;
    	for(int i=0; i< newList.size(); i++){
    		for(int j=1; j<newList.size(); j++){
    			if(newList.get(j-1).getPriority() > newList.get(j).getPriority()){
    				temp = newList.get(j-1);
    				newList.set(j-1, newList.get(j));
    				newList.set(j, temp);
    			}
    		}
    	}
    	items = newList; //update items
    }
    /*
     * sortItemsByCategory
     * 
     * Sorts the LinkedList of Items by the item's category.
     */
    public void sortItemsByCategory(){
    	LinkedList<Item> newList = items; 
    	Item temp;
    	for(int i=0; i< newList.size(); i++){
    		for(int j=1; j<newList.size(); j++){
    			if(newList.get(j-1).getCategory().compareTo(newList.get(j).getCategory()) > 0){
    				temp = newList.get(j-1);
    				newList.set(j-1, newList.get(j));
    				newList.set(j, temp);
    			}
    		}
    	}
    	items = newList; //update items;
    	
    }
    /*
     * sortItemsByStore
     * 
     * Sorts the LinkedList of Items by the item's store.
     */
    public void sortItemsByStore(){
    	LinkedList<Item> newList = items; 
    	Item temp;
    	for(int i=0; i< newList.size(); i++){
    		for(int j=1; j<newList.size(); j++){
    			if(newList.get(j-1).getStore().compareTo(newList.get(j).getStore()) > 0){
    				temp = newList.get(j-1);
    				newList.set(j-1, newList.get(j));
    				newList.set(j, temp);
    			}
    		}
    	}
    	items = newList; //update items;
    }
    
    /*
     * searchForItem
     * 
     * Searches for an specific item in the list and places
     * it at the front of the list (if found).
     */
    public boolean searchForItem(String item){
    	Item temp;
    	for(int i=0; i< items.size(); i++){
    		temp = items.get(i);
    		if(temp.getName().equals(item)){
    			Item swap = items.get(0); //first position
    			items.set(i, swap); 
    			items.set(0, temp); //put match in first position		
    			return true; //item found
    		}
    	}
    	return false; //item not found
    }
    
}
