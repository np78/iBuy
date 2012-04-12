/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs477.linn;



/**
 * List - acts as a list object
 * @author Nicole Williams (Team LINN)
 * @version 4/3/12
 */
public class List {
    public String user; //name of user that list belongs
    public String list_name;
    //public LinkedList<Item> items; //list of items in the list
    
    public List(String u, String name){
        user = u;
        list_name = name;
        //items = new LinkedList<Item>(); //empty list
    }
    
    public String getUser(){
        return user;
    }
    
    public String getName(){
        return list_name;
    }
    
    public void addItem(Item i){
        //items.add(i);
    }
    
    public void removeItem(Item i){
        //items.remove(i);
    }
    /*
    public void displayItems(View v){
        TextView display = (TextView) v.findViewById(R.id.text_download);
        String display_text = "";
        for(int i=0; i<items.size(); i++){
            Item cur = (Item) items.get(i);
            display_text += cur.getName() + "\n";
        }
        display.setText(display_text);
    }*/
    
    /*
    @Override
    public String toString(){
        String display_text = "";
        for(int i=0; i<items.size(); i++){
            Item cur = (Item) items.get(i);
            display_text += cur.getName() + "\n";
        }
        return display_text;
    }*/
    
}
