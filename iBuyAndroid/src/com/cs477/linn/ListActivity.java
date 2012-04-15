/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs477.linn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * ListActivity
 * 
 * @author N. Williams (TEAM LINN)
 * @version 4.11.2012
 */
public class ListActivity extends Activity {

    private String currentUser;
    private String currentList;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.listview);  
        currentUser = getIntent().getStringExtra("curUser");
        currentList = getIntent().getStringExtra("curList");
        
        //change name of list to current list name
        Button list_name = (Button) findViewById(R.id.name);
        list_name.setText(currentList);
    }
    /*
     * changeListName
     * 
     * Shows "change name" views.
     */
    public void changeListName(View v){
        EditText name_edit = (EditText) findViewById(R.id.name_edit);
        Button name_save = (Button) findViewById(R.id.name_save);
        
        name_edit.setVisibility(0);
        name_save.setVisibility(0);
    }
    /*
     * saveListName
     * 
     * Copies the data from original list file to new list file.  Hides "change
     * name" views.
     */
    public void saveListName(View v) throws FileNotFoundException, IOException{
        EditText name_edit = (EditText) findViewById(R.id.name_edit);
        Button name_save = (Button) findViewById(R.id.name_save);
        String newName = name_edit.getText().toString();
                
        //GET DATA FROM ORIGINAL FILE
        FileInputStream fIn = openFileInput(currentList+".txt");
        InputStreamReader isr = new InputStreamReader(fIn);
        char[] inputBuffer = new char[10000000];
        isr.read(inputBuffer);
        String readString = new String(inputBuffer);
        fIn.close();
        isr.close();
        
        //SAVE DATA TO NEW FILE
        currentList = newName.replaceAll(" ", "_");
        FileOutputStream fOut = openFileOutput(currentList+".txt", MODE_WORLD_READABLE);
        OutputStreamWriter osw = new OutputStreamWriter(fOut);
        osw.write(readString);
        osw.flush();
        osw.close();
        
        //HIDE CHANGE NAME VIEWS
        name_edit.setVisibility(4);
        name_save.setVisibility(4);   
    }
    
    public void getAllItems() throws FileNotFoundException, IOException{
        FileInputStream fIn = openFileInput(currentList+".txt");
        InputStreamReader isr = new InputStreamReader(fIn);
        char[] inputBuffer = new char[10000000];
        isr.read(inputBuffer);
        String readString = new String(inputBuffer);
        
        TextView items = (TextView) findViewById(R.id.itemstext);
        items.setText(readString);
    }
    
    /*
     * ListViewActivity usage
     */
    public void goToAddItem(View v){
        setContentView(R.layout.additem);
    }
    
    
    /*
     * Global usage
     */
    public void goHomeScreen(View v){
        //setContentView(R.layout.lists);
        Intent myIntent = new Intent(v.getContext(), UserActivity.class);
        myIntent.putExtra("curUser", currentUser);
        //myIntent.putExtra("curList", currentList);
        startActivityForResult(myIntent, 0);
    }
    
    /*
     * OptionsActivity usage
     */
    public void goToOptions(View v){
        setContentView(R.layout.options); 
    }
    
     /*
     * UNDER CONSTRUCTION
     * -crashes app
     */
    public void addNewItem(View v){
        setContentView(R.layout.listview);
        /*
        EditText item = (EditText) findViewById(R.id.enteritem);
        EditText category = (EditText) findViewById(R.id.entercategory);
        EditText store = (EditText) findViewById(R.id.enterstore);
        EditText priority = (EditText) findViewById(R.id.enterpriority);
        
        Editable e = item.getText();
        String content = e.toString();
        e = category.getText();
        content += " "+e.toString();  
        e = store.getText();
        content += " "+e.toString(); 
        e = priority.getText();
        content += " "+e.toString();
        
        TextView item1 = (TextView) findViewById(R.id.item1);
        TextView item2 = (TextView) findViewById(R.id.item2);
        TextView item3 = (TextView) findViewById(R.id.item3);
        TextView item4 = (TextView) findViewById(R.id.item4);
        TextView item5 = (TextView) findViewById(R.id.item5);
        
        if(item1.getVisibility() == 4){
            item1.setVisibility(0);
            item1.setText(item.getText().toString());
            
        }
        else if(item2.getVisibility() == 4){
            item2.setVisibility(0);
            item2.setText(item.getText().toString());
        }
        else if(item3.getVisibility() == 4){
            item3.setVisibility(0);
            item3.setText(item.getText().toString());
        }
        else if(item4.getVisibility() == 4){
            item4.setVisibility(0);
            item4.setText(item.getText().toString());
        }
        else if(item5.getVisibility() == 4){
            item5.setVisibility(0);
            item5.setText(item.getText().toString());
        }
        else{
            Toast.makeText(this,"NOT ENOUGH TEXTVIEWS",Toast.LENGTH_LONG).show();
        }
        */
        
        /*
        LinearLayout layout = (LinearLayout) findViewById(R.layout.main);
        TextView tv = new TextView(MainActivity.this);
        tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        tv.setText("Added item");
        layout.addView(tv);
         */
    }
}
