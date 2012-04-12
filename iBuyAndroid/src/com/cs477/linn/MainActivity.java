package com.cs477.linn;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.dropbox.client2.session.*;
import com.dropbox.client2.*;
import com.dropbox.client2.DropboxAPI.DropboxFileInfo;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.exception.DropboxException;
import com.dropbox.client2.exception.DropboxUnlinkedException;

import com.dropbox.client2.session.Session.AccessType;



import android.R.layout;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * MainActivity
 * 
 * @author N. Williams
 * @version 4.11.2012
 *
 */
public class MainActivity extends Activity
{
    //DROPBOX INSTANCE VARIABLES
    
    final static private String APP_KEY = "nn7lnxubh1knv63";
    final static private String APP_SECRET = "l02f3ekx7lc7wx2";
    final static private AccessType ACCESS_TYPE = AccessType.APP_FOLDER;
    public static String sessionKey = "wiaqh0zpg745z2v";
    public static String sessionSecret = "5elro586b19u7n2"; 
    private DropboxAPI<AndroidAuthSession> mDBApi;
    
    private boolean isLoggedIn;
    private String currentUser;
    private String currentList;
    
    
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        isLoggedIn=false;
        createDropboxSession();
    }
    
    /**
     * createDropboxSession()
     * Creates a dropbox session with the APP_KEY and APP_SECRET for dropbox.
     */
    
    private void createDropboxSession(){
    	try{
        // And later in some initialization function:
        AppKeyPair appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
        AndroidAuthSession session = new AndroidAuthSession(appKeys, ACCESS_TYPE);
        mDBApi = new DropboxAPI<AndroidAuthSession>(session);
        
        // MyActivity below should be your activity class name
        mDBApi.getSession().setAccessTokenPair(new AccessTokenPair(sessionKey, sessionSecret));
        //Toast.makeText(this, "created dropbox session", RESULT_OK);
    	}catch(Exception e){
    		Log.v("ERROR DROPBOX",e.getMessage());
    	}
    }
    
    /*
     * onResume()
     * 
     * Used for DropBox session.
     */
    
    @Override
    protected void onResume() {
        super.onResume();

        if (mDBApi.getSession().authenticationSuccessful()) {
            try {
                // MANDATORY call to complete auth.
                // Sets the access token on the session
                mDBApi.getSession().finishAuthentication();

                AccessTokenPair tokens = mDBApi.getSession().getAccessTokenPair();
            } catch (IllegalStateException e) {
                Log.i("DbAuthLog", "Error authenticating", e);
            }
        }
    }
    /**
     * uploadFile
     * Upload a file to Dropbox.
     * @param v 
     */ 
    /*
    public void uploadFile(View v){
        
        List curList = new List("Nicole", "ShoppingList1");
        curList.addItem(new Item("Apples", "Fruit"));
        curList.addItem(new Item("Oranges", "Fruit"));
        String fileContents = curList.toString();
        // Uploading content.
        ByteArrayInputStream inputStream = new ByteArrayInputStream(fileContents.getBytes());
        try {
            DropboxAPI.Entry newEntry = mDBApi.putFileOverwrite("/"+curList.getName()+".txt", inputStream,
                   fileContents.length(), null);
           Log.i("DbExampleLog", "The uploaded file's rev is: " + newEntry.rev);
        } catch (DropboxUnlinkedException e) {
           // User has unlinked, ask them to link again here.
           Log.e("DbExampleLog", "User has unlinked.");
        } catch (DropboxException e) {
           Log.e("DbExampleLog", "Something went wrong while uploading.");
        }
    } */
    /**
     * downloadFile
     * Download a file from Dropbox.
     * @param v 
     */
    
    /*public void downloadFile(View v){
        final TextView file_view = (TextView) findViewById(R.id.text_download);
        //Get file
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try{
            DropboxFileInfo info = mDBApi.getFile("/ShoppingList1.txt", null, outputStream, null);
            
            String content = new String(outputStream.toByteArray());
            Toast.makeText(this,content,Toast.LENGTH_LONG).show();
            file_view.setText(content); //CRASHES AT THIS LINE
            
        }catch(Exception e){
            Log.e("downloadFile", e.getMessage());
        }
    }*/
    
    //---------------------------------------------------------------------------------------
    //LOGIN ACTIVITY STUFF
    //---------------------------------------------------------------------------------------
    
    /**
     * logIn
     * Takes the contents of the username and password fields and attempts
     * to login to the application. Does nothing if unable to login.
     * @param v 
     */ 
    public void logIn(View v){
        //username
        EditText user_text = (EditText) findViewById(R.id.useredit);
        Editable user_edit = user_text.getText();
        String username = user_edit.toString();
        //password
        EditText pass_text = (EditText) findViewById(R.id.pwedit);
        Editable pass_edit = pass_text.getText();
        String password = pass_edit.toString();
        //if both fields are not empty, then attempt login
        if(!username.equals("") && !password.equals("")){
            try {
                isLoggedIn = checkUserFile(username, password);
            } catch (IOException ex) {
                Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        if(isLoggedIn){
        	TextView error = (TextView) findViewById(R.id.login_error);
        	error.setVisibility(4); //set invisible
            //go to ListsActivity and lists screen
            //startActivity(new Intent("edu.nau.cs477.CLEARSCREEN"));
        	currentUser = username;
        	setContentView(R.layout.lists);
        }
        else{
        	//error
        	TextView error = (TextView) findViewById(R.id.login_error);
        	error.setVisibility(0); //set visible
        }
    }   
    /**
     * checkUserFile
     * Downloads users.txt from Dropbox and checks to see if the username and
     * password match the contents in the file.
     * 
     * @param username
     * @param password 
     * @return boolean
     */ 
    public boolean checkUserFile(String username, String password) throws IOException{
        //Get file
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try{
            DropboxFileInfo info = mDBApi.getFile("/users.txt", null, outputStream, null);
            String content = new String(outputStream.toByteArray());
            StringTokenizer st = new StringTokenizer(content);
            outputStream.close();
            while(st.hasMoreTokens()){
                //if username and password match in user contents file
                if(st.nextToken().equals(username) && st.nextToken().equals(password)){
                    return true;           
                }
            }         
        }catch(DropboxException e){
            Log.e("DbExampleLog", "Something went wrong while downloading.");
        }
        return false; //username and password don't match file contents
    }   
   
    
    //---------------------------------------------------------------------------------------
    // USER'S LISTS 
    //---------------------------------------------------------------------------------------
    
    public void createNewList(View v){
    	currentList="";
    	setContentView(R.layout.listedit);
    	
    }
    
    //CRASHES
    public void saveListName(View v){
    	
    	EditText name = (EditText) findViewById(R.id.listname_edit);
    	String st = name.getText().toString();
    	
    	if(currentList.equals("")){ //new list
    		//add new list to lists.txt
    		 ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    	        try{
    	        	//download lists.txt info
    	            DropboxFileInfo info = mDBApi.getFile("/"+currentUser+"/lists.txt", null, outputStream, null);
    	            
    	            String content = new String(outputStream.toByteArray());
    	            content += "\n"+ (new List(currentUser,st)).newListData();
    	            
    	            ByteArrayInputStream inputStream = new ByteArrayInputStream(content.getBytes());
    	            try {
    	            	//update lists.txt
    	                DropboxAPI.Entry newEntry = mDBApi.putFileOverwrite("/"+currentUser+"/lists.txt", inputStream, content.length(), null);
    	                content = "";
    	                //create <listname>.txt
    	                inputStream = new ByteArrayInputStream(content.getBytes());
    	                newEntry = mDBApi.putFileOverwrite("/"+currentUser+"/"+st+".txt", inputStream, content.length(), null);
    	               
    	            } catch (DropboxUnlinkedException e) {
    	               // User has unlinked, ask them to link again here.
    	               Log.e("DbExampleLog", "User has unlinked.");
    	            } catch (DropboxException e) {
    	               Log.e("DbExampleLog", "Something went wrong while uploading.");
    	            }
    	            
    	        }catch(Exception e){
    	            Log.e("downloadFile", e.getMessage());
    	        }
    		
    	}
    	else{ //not a new list
    		/*
    		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    		try{
    			//get lists.txt content
    			DropboxFileInfo info = mDBApi.getFile("/"+currentUser+"/"+currentList+".txt", null, outputStream, null);
    			String content = new String(outputStream.toByteArray());
    			//find <listname> in lists.txt content
    			
    			
    			//download <listname>.txt content
    			DropboxFileInfo info = mDBApi.getFile("/"+currentUser+"/"+currentList+".txt", null, outputStream, null);
    			String content = new String(outputStream.toByteArray());
    			
    			//upload <newlistname>.txt
    			ByteArrayInputStream inputStream = new ByteArrayInputStream(content.getBytes());
	            try {
	            	//update lists.txt
	                DropboxAPI.Entry newEntry = mDBApi.putFileOverwrite("/lists.txt", inputStream, content.length(), null);
	                content = "";
	                //create <listname>.txt
	                inputStream = new ByteArrayInputStream(content.getBytes());
	                newEntry = mDBApi.putFileOverwrite("/"+currentUser+"/"+st+".txt", inputStream, content.length(), null);
	               
	            } catch (DropboxUnlinkedException e) {
	               // User has unlinked, ask them to link again here.
	               Log.e("DbExampleLog", "User has unlinked.");
	            } catch (DropboxException e) {
	               Log.e("DbExampleLog", "Something went wrong while uploading.");
	            }
    			
    			//upload empty <listname>.txt
    			
    			
    		} catch(Exception e){
	            Log.e("downloadFile", e.getMessage());
	        }*/
    	}
    	currentList = st; //change to new current list
    	//createListsLayout();
    	setContentView(R.layout.listview);
    	
    }
    /*
     * creates the layout for lists.xml
     */
    public void createListsLayout(){
    	RelativeLayout rl = new RelativeLayout(this);
    }
    /*
     * creates the layout for the current list (lists the items)
     */
    public void createListViewLayout(){
    	
    }
    
    /*
     * saves the options from the options screen and then goes to the 
     * home screen
     */
    public void saveOptions(View v){
    	Toast.makeText(this,"Save Successful.",Toast.LENGTH_LONG).show();
    	goHomeScreen(v);
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
    	Toast.makeText(this,"Function not available.",Toast.LENGTH_LONG).show();
        //setContentView(R.layout.options);
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
        
        setContentView(R.layout.listview);
        
    }
    /**
     * Adds an item to the list in the current users folder.
     */
    
    public void addItemToList(String cur_user, String list_name, Item item){
        String filename = "/"+cur_user+"/"+list_name+".txt";
        
        //Add item and category to list
    }
    
    /**
     * Deletes an item from a list in the user's folder.
     * @param cur_user
     * @param list_name
     * @param item 
     */
    
    public void deleteItem(String cur_user, String list_name, Item item){
        String item_name = item.getName();
        String item_category = item.getCategory();
        
        String filename = "/"+cur_user+"/"+list_name+".txt";
        //delete specific item from list
    }
}
