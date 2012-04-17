/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs477.linn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author N. Williams (TEAM LINN)
 * @version 4.11.2012
 */
public class UserActivity extends Activity {
    
    //final static private String APP_KEY = "nn7lnxubh1knv63";
    //final static private String APP_SECRET = "l02f3ekx7lc7wx2";
    //final static private AccessType ACCESS_TYPE = AccessType.APP_FOLDER;
    //public static String sessionKey = "wiaqh0zpg745z2v";
    //public static String sessionSecret = "5elro586b19u7n2"; 
    //private DropboxAPI<AndroidAuthSession> mDBApi;

    private String currentUser;
    private String currentList;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.lists);  
        currentUser = getIntent().getStringExtra("curUser");
        //createDropboxSession();
    }
    /**
     * createDropboxSession()
     * Creates a dropbox session with the APP_KEY and APP_SECRET for dropbox.
     */
    /*
    private void createDropboxSession(){
        // And later in some initialization function:
        AppKeyPair appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
        AndroidAuthSession session = new AndroidAuthSession(appKeys, ACCESS_TYPE);
        mDBApi = new DropboxAPI<AndroidAuthSession>(session);
        
        // MyActivity below should be your activity class name
        mDBApi.getSession().setAccessTokenPair(new AccessTokenPair(sessionKey, sessionSecret));
        //Toast.makeText(this, "created dropbox session", RESULT_OK);
    }*/
    
    /*
     * onResume()
     * 
     * Used for DropBox session.
     */
    /*
    @Override
    protected void onResume() {
        super.onResume();

        if (mDBApi.getSession().authenticationSuccessful()) {
            try {
                // MANDATORY call to complete auth.
                // Sets the access token on the session
                mDBApi.getSession().finishAuthentication();

                //AccessTokenPair tokens = mDBApi.getSession().getAccessTokenPair();
            } catch (IllegalStateException e) {
                Toast.makeText(this,"error authenticating",Toast.LENGTH_LONG).show();
                Log.i("DbAuthLog", "Error authenticating", e);
            }
        }
    }*/
    
    /*
     * addNewList
     * 
     * Dynamically adds a new list to listview.xml (layout) and creates a new
     * list file (LOCAL).
     */
    public void addNewList(View v) throws IOException{
        currentList = "Untitled";
        /*
        // Uploading content.
        ByteArrayInputStream inputStream = new ByteArrayInputStream(fileContents.getBytes());
        try {
            DropboxAPI.Entry newEntry = mDBApi.putFileOverwrite("/"+currentUser+"/"+currentList+".txt", inputStream,
                   fileContents.length(), null);
           //Log.i("DbExampleLog", "The uploaded file's rev is: " + newEntry.rev);
        } catch (DropboxUnlinkedException e) {
           // User has unlinked, ask them to link again here.
           Log.e("DbExampleLog", "User has unlinked.");
        } catch (DropboxException e) {
           Log.e("DbExampleLog", "Something went wrong while uploading.");
        }*/
        
        
        
        //Toast.makeText(this,currentUser,Toast.LENGTH_LONG).show();

        //CREATE <LISTNAME>.TXT
        //ADD LIST CONTENTS TO <LISTNAME>.TXT
        
        //DATE STRING
        Date date = new Date(System.currentTimeMillis());
        String d_string = date.toString().replaceAll(" ", "_");

        //DUMMY DATA
        String list_content = currentList +" 0.00 "+ d_string;

        FileOutputStream fOut = openFileOutput(currentList+".txt", MODE_WORLD_READABLE);
        OutputStreamWriter osw = new OutputStreamWriter(fOut);
        osw.write(list_content);
        osw.flush();
        osw.close();
        
        //CREATE A NEW TEXTVIEW
        //ADD NEW TEXTVIEW TO LISTS.XML
        TextView tv = (TextView) findViewById(R.id.samplelist1);
        TextView tv2 = (TextView) findViewById(R.id.samplelist2);
        TextView tv3 = (TextView) findViewById(R.id.samplelist3);
        
        FileInputStream fIn = openFileInput(currentList+".txt");
        InputStreamReader isr = new InputStreamReader(fIn);
        char[] inputBuffer = new char[list_content.length()];
        isr.read(inputBuffer);
        String readString = new String(inputBuffer);
        if(tv.getVisibility() == 4){
            tv.setText(readString);
            tv.setVisibility(0);
            tv.setClickable(true); 
            //currentList=readString;
            tv.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    //Button b = (Button) findViewById(R.id.name);
                    //String name = currentList.replaceAll("_", " ");
                    //b.setText(name);
                    Intent myIntent = new Intent(arg0.getContext(), ListActivity.class);
                    myIntent.putExtra("curUser", currentUser);
                    myIntent.putExtra("curList", currentList);
                    startActivityForResult(myIntent, 0);
                }
            });
        }
        else if(tv2.getVisibility() == 4){
            tv2.setText(readString);
            tv2.setVisibility(0);
            tv2.setClickable(true);
            //currentList=readString;
            tv2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    //Button list_name = (Button) findViewById(R.id.name);
                    //TextView tv = (TextView) findViewById(R.id.samplelist2);
                    //list_name.setText(tv.getText());
                    Intent myIntent = new Intent(arg0.getContext(), ListActivity.class);
                    myIntent.putExtra("curUser", currentUser);
                    myIntent.putExtra("curList", currentList);
                    startActivityForResult(myIntent, 0);
                }
            });
            
        }
        else if(tv3.getVisibility() == 4){
            tv3.setText(readString);
            tv3.setVisibility(0);
            tv3.setClickable(true);
            //currentList=readString;
            tv3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    //Button list_name = (Button) findViewById(R.id.name);
                    //TextView tv = (TextView) findViewById(R.id.samplelist3);
                    //list_name.setText(tv.getText());
                    Intent myIntent = new Intent(arg0.getContext(), ListActivity.class);
                    myIntent.putExtra("curUser", currentUser);
                    myIntent.putExtra("curList", currentList);
                    startActivityForResult(myIntent, 0);
                }
            });
        }
        else{
            Toast.makeText(this,"NOT ENOUGH TEXTVIEWS",Toast.LENGTH_LONG).show();
        }   
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
}
