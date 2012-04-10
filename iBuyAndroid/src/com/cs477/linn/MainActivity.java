package com.cs477.linn;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }
    /*
     * LoginActivity usage
     */
    public void logIn(View v){
        setContentView(R.layout.lists);
    }
    /*
     * ListsActivity usage
     */
    public void goToList(View v){
        setContentView(R.layout.listview);
    }
    /*
     * ListViewActivity usage
     */
    public void goToAddItem(View v){
        setContentView(R.layout.additem);
    }
    /*
     * OptionsActivity usage
     */
    public void goToOptions(View v){
        setContentView(R.layout.options);
    }
    
    /*
     * Global usage
     */
    public void goHomeScreen(View v){
        setContentView(R.layout.lists);
    }
    
    /*
     * UNDER CONSTRUCTION
     * -crashes app
     */
    public void addNewItem(View v){
        RelativeLayout listviewlayout = (RelativeLayout) findViewById(R.layout.listview);
        //item name
        //EditText name = (EditText) findViewById(R.id.enter);
        //item category
        //EditText category = (EditText) findViewById(R.id.enter_cat);
        //item quantity
        //EditText quantity = (EditText) findViewById(R.id.enter_quan);
        
        //Editable content_edit = name.getText();
        //String content = content_edit.toString();
        
        TextView newItem = new TextView(MainActivity.this);
        newItem.setText("apple");
        newItem.setLayoutParams(new LayoutParams(
                RelativeLayout.LayoutParams.FILL_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT));
        /*
        newItem.setClickable(true);
        newItem.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {
                goToAddItem(arg0);
            }
        });*/
        
        ((RelativeLayout) listviewlayout).addView(newItem);
        
        setContentView(R.layout.listview);
        
    }
}
