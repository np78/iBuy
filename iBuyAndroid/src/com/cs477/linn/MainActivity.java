package com.cs477.linn;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
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
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * MainActivity
 * 
 * @author N. Williams
 * @version 05.01.2012
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
    
    static final int DIALOG_ITEM_SEARCH = 0;
    
    private boolean isLoggedIn;
    private String currentUser;
    private String currentList;
    private String currentItem="";
    
    private int sortBy; // 0 IS NO SORTING
    static final int SORT_BY_CATEGORY = 1;
    static final int SORT_BY_STORE = 2;
    static final int SORT_BY_PRIORITY = 3;
    
    private boolean commonItemsReport; //get value from file
    private boolean expenseReportAll; //get value from file
    private boolean expenseReportLastWeek; //get value from file
    
    
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.login);
        isLoggedIn=false; //DEFAULT VALUE
        sortBy = 0; //DEFAULT VALUE 
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
        	//setContentView(R.layout.lists);
        	goHomeScreen(v);
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
            Log.e("DbExampleLog", "Something went wrong while downloading /users.txt.");
        }
        return false; //username and password don't match file contents
    }   
   
    
    //---------------------------------------------------------------------------------------
    // USER'S LISTS ACTIVITY STUFF
    //---------------------------------------------------------------------------------------
    
    
    /*
     * createNewList
     * 
     * Adds a new list file to dropbox, and adds name of list to lists.txt file.
     */
    public void createNewList(View v){
    	currentList="";
    	setContentView(R.layout.listedit);
    	TextView createNew = (TextView) findViewById(R.id.listedit_title);
    	createNew.setVisibility(View.GONE);
    	Button delete = (Button) findViewById(R.id.delete_list);
    	delete.setVisibility(View.INVISIBLE);
    	
    	
    }
    /*
     * changeListName
     * 
     * Go to a screen where you can change the name of the list.
     */
    public void changeListName(View v){
    	setContentView(R.layout.listedit);
    	TextView createNew = (TextView) findViewById(R.id.listedit_title);
    	createNew.setVisibility(View.VISIBLE);
    	Button delete = (Button) findViewById(R.id.delete_list);
    	delete.setVisibility(View.VISIBLE);
    }
    /*
     * saveListName
     * 
     * 
     */
    public void saveListName(View v) throws DropboxException{
    	//get entered name
    	EditText name = (EditText) findViewById(R.id.listname_edit);
    	String st = name.getText().toString();
    	st = st.replaceAll(" ", "_");
    	
    	//check to see if list exists first
    	if(currentList.equals("")){ //if list is a new list, then do this stuff
    		//upload a new file with <name>.txt
    		String newListContent = "";
    		ByteArrayInputStream inputStream = new ByteArrayInputStream(newListContent.getBytes());
    		try {
    			DropboxAPI.Entry newEntry = mDBApi.putFileOverwrite("/"+currentUser+"/"+st+".txt", inputStream,
    					newListContent.length(), null);
    			//Log.i("DbExampleLog", "The uploaded file's rev is: " + newEntry.rev);
    		} catch (DropboxUnlinkedException e) {
    			// User has unlinked, ask them to link again here.
    			Log.e("DbExampleLog", "User has unlinked.");
    		} catch (DropboxException e) {
    			Log.e("DbExampleLog", "Something went wrong while uploading new list.");
    		}	
    		//add file to lists.txt
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            try{
            	//get contents of lists.txt
                DropboxFileInfo info = mDBApi.getFile("/"+currentUser+"/lists.txt", null, outputStream, null);
                
                String content = new String(outputStream.toByteArray());
                //Toast.makeText(this,content,Toast.LENGTH_LONG).show();
                List l = new List(currentUser, st);
                content += "\n" + l.newListData();
                //add new contents to lists.txt
                ByteArrayInputStream is = new ByteArrayInputStream(content.getBytes());
                try {
                    DropboxAPI.Entry newEntry = mDBApi.putFileOverwrite("/"+currentUser+"/lists.txt", is,
                           content.length(), null);
                   //Log.i("DbExampleLog", "The uploaded file's rev is: " + newEntry.rev);
                } catch (DropboxUnlinkedException e) {
                   // User has unlinked, ask them to link again here.
                   Log.e("DbExampleLog", "User has unlinked.");
                } catch (DropboxException e) {
                   Log.e("DbExampleLog", "Something went wrong while updating lists.txt");
                }
                
            }catch(Exception e){
                Log.e("downloadFile", e.getMessage());
            }
            goHomeScreen(v);
    	}
    	else if(currentList.equals(st)){ //tried to change the name to the same name
    		Toast.makeText(this,"Try a new name; list with same name already exists!", Toast.LENGTH_LONG).show();
    		
    	}
    	else{ //if list is not a new list & doesn't have the same name as an existing list, do this stuff
    		//update lists.txt, remove old list name, add new list name
    		String old_name = currentList;
    		String new_name = st;
    		
    		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            try{
            	//get contents of lists.txt
                DropboxFileInfo info = mDBApi.getFile("/"+currentUser+"/lists.txt", null, outputStream, null);
                
                String content = new String(outputStream.toByteArray());
                StringTokenizer tokenizer = new StringTokenizer(content);
                //Toast.makeText(this,content,Toast.LENGTH_LONG).show();
                List oldList = new List(currentUser, old_name);
                List newList = new List(currentUser, new_name);
                newList.setTotal(oldList.getTotal());       
                //find old list stuff in lists.txt
                String newContents = "";
                while(tokenizer.hasMoreTokens()){
                	String token = tokenizer.nextToken();
                	if(token.equals(oldList.getName())){
                		newContents += newList.getName() + " " + tokenizer.nextToken();
                		tokenizer.nextToken();
                		newContents += " " + newList.getDate() + "\n";
                	}
                	else{
                		newContents += token + " " + tokenizer.nextToken() + " " + tokenizer.nextToken() + "\n";
                	}
                }
                //add new contents to lists.txt
                ByteArrayInputStream is = new ByteArrayInputStream(newContents.getBytes());
                try {
                    DropboxAPI.Entry newEntry = mDBApi.putFileOverwrite("/"+currentUser+"/lists.txt", is,
                           newContents.length(), null);
                   //Log.i("DbExampleLog", "The uploaded file's rev is: " + newEntry.rev);
                } catch (DropboxUnlinkedException e) {
                   // User has unlinked, ask them to link again here.
                   Log.e("DbExampleLog", "User has unlinked.");
                } catch (DropboxException e) {
                   Log.e("DbExampleLog", "Something went wrong while updating lists.txt");
                }
                
            }catch(Exception e){
                Log.e("downloadFile", e.getMessage());
            }
            
            DropboxAPI.Entry newEntry = mDBApi.move("/"+currentUser+"/"+old_name+".txt", "/"+currentUser+"/"+new_name+".txt");
            goHomeScreen(v);
    	}
    	
    	
        	
    }
    public void goToDeleteList(View v){
    	//CONFIRM DELETE
    	AlertDialog confirmation = new AlertDialog.Builder(this).create();
    	confirmation.setTitle("Delete Confirmation");
    	confirmation.setMessage("Are you sure you want to delete list?");
    	confirmation.setButton("Yes", new DialogInterface.OnClickListener(){
    		public void onClick(DialogInterface dialog, int which){
    			try {
    				dialog.dismiss();
					deleteList();
				} catch (DropboxException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
    		}
    	});
    	confirmation.setButton2("No",new DialogInterface.OnClickListener(){
    		public void onClick(DialogInterface dialog, int which){
    			dialog.dismiss();
    			//setContentView(R.layout.listedit);
    			changeListName(new View(MainActivity.this));
    		}
    	});
    	
    	confirmation.show();
    	
    }
    /*
     * deleteList
     * 
     * Deletes a list from the Dropbox.
     */
    public void deleteList() throws DropboxException{
    	String listName = currentList;
    	
    	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try{
        	//get contents of lists.txt
            DropboxFileInfo info = mDBApi.getFile("/"+currentUser+"/lists.txt", null, outputStream, null);
            
            String content = new String(outputStream.toByteArray());
            StringTokenizer tokenizer = new StringTokenizer(content);
            
            String newContents = "";
            while(tokenizer.hasMoreTokens()){
            	String token = tokenizer.nextToken();
            	if(token.equals(listName)){
            		//Toast.makeText(this,"ListName = "+listName,Toast.LENGTH_LONG).show(); //test
            		String extaTokens = tokenizer.nextToken() + tokenizer.nextToken();
            	}
            	else{
            		newContents += token + " " + tokenizer.nextToken() + " " + tokenizer.nextToken() + "\n";
            	}
            }
            
            
            
            //update lists.txt
            ByteArrayInputStream is = new ByteArrayInputStream(newContents.getBytes());
            try {
                DropboxAPI.Entry newEntry = mDBApi.putFileOverwrite("/"+currentUser+"/lists.txt", is,
                       newContents.length(), null);
               //Log.i("DbExampleLog", "The uploaded file's rev is: " + newEntry.rev);
            } catch (DropboxUnlinkedException e) {
               // User has unlinked, ask them to link again here.
               Log.e("DbExampleLog", "User has unlinked.");
            } catch (DropboxException e) {
               Log.e("DbExampleLog", "Something went wrong while updating lists.txt");
            }
            
        }catch(Exception e){
            Log.e("downloadFile", e.getMessage());
        }
        //delete list file
        mDBApi.delete("/"+currentUser+"/"+listName+".txt");
        goHomeScreen(new View(MainActivity.this));
      Toast.makeText(this,"List was deleted.",Toast.LENGTH_LONG).show();
    }
    /*
     * logout()
     * 
     * Logs out of the current user's session and returns to the login screen.
     */
    public void logout(){
    	currentUser="";
    	currentList="";
    	currentItem="";
    	isLoggedIn=false;
    	setContentView(R.layout.login);
    	
    }
    /*
     * createListsLayout()
     * 
     * creates the layout for lists.xml
     * 
     */
    public void createListsLayout(){
    	RelativeLayout rl = new RelativeLayout(this);
    	
    	//OPTIONS BUTTON
    	Button options_but = new Button(this);
    	RelativeLayout.LayoutParams p1 = new RelativeLayout.LayoutParams(75, 75);
    	p1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
    	options_but.setLayoutParams(p1);
    	options_but.setBackgroundResource(R.drawable.options_icon);
    	options_but.setId(R.id.options);
    	options_but.setOnClickListener(new OnClickListener(){
    		public void onClick(View v){
    			goToOptions(v);
    		}
    	});
    	rl.addView(options_but);
    	//LOGOUT BUTTON
    	Button logout_but = new Button(this);
    	RelativeLayout.LayoutParams logoutParams = new RelativeLayout.LayoutParams(75,75);
    	logoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
    	logout_but.setLayoutParams(logoutParams);
    	logout_but.setBackgroundResource(R.drawable.logout_icon);
    	logout_but.setId(R.id.logout);
    	logout_but.setOnClickListener(new OnClickListener(){
    		public void onClick(View v){
    			logout();
    		}
    	});
    	rl.addView(logout_but);
    	//DISPLAY TEXTS
    	TextView display_text = new TextView(this);
    	RelativeLayout.LayoutParams p2 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    	p2.addRule(RelativeLayout.LEFT_OF, R.id.options);
    	p2.addRule(RelativeLayout.RIGHT_OF,R.id.logout);
    	display_text.setLayoutParams(p2);
    	display_text.setPadding(0,0,0,20);
    	display_text.setTextSize(15);
    	display_text.setPadding(10, 0, 0, 0);
    	display_text.setText("Tap a list to modify it, or tap 'Create new list' for new list");
    	display_text.setId(R.id.display);
    	rl.addView(display_text);
    	//CREATE LIST BUTTON
    	Button create_but = new Button(this);
    	RelativeLayout.LayoutParams p3 = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
    	p3.addRule(RelativeLayout.BELOW, R.id.options);
    	create_but.setLayoutParams(p3);
    	create_but.setCompoundDrawablesWithIntrinsicBounds(R.drawable.add_button, 0, 0, 0);
    	create_but.setTextSize(20);
    	create_but.setText("Create new list");
    	create_but.setId(R.id.create);
    	create_but.setOnClickListener(new OnClickListener(){
    		public void onClick(View v){
    			//create new list
    			//setContentView(R.layout.listview);
    			createNewList(v);
    		}
    	});
    	rl.addView(create_but);
    	//LIST TEXT VIEWS - GET FROM LISTS.TXTS
    	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try{
            DropboxFileInfo info = mDBApi.getFile("/"+currentUser+"/lists.txt", null, outputStream, null);

            String content = new String(outputStream.toByteArray());
            StringTokenizer tokenizer = new StringTokenizer(content);
            
            int num_lists = 1;
            while(tokenizer.hasMoreTokens()){
            	String list_name = tokenizer.nextToken();
            	
            	final TextView list = new TextView(this);
            	RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, 50);
            	if(num_lists == 1){
            		param.addRule(RelativeLayout.BELOW, R.id.create);
            	}
            	else{
            		param.addRule(RelativeLayout.BELOW, num_lists-1);
            	}
            	list.setLayoutParams(param);
            	list.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.black_line);
            	list.setClickable(true);
            	list.setTextSize(20);
            	list.setBackgroundColor(Color.WHITE);
            	list.setTextColor(Color.BLACK);
            	list.setText(list_name); //set to list name
            	list.setId(num_lists);
            	list.setOnClickListener(new OnClickListener(){
            		String name = (String) list.getText();
            		public void onClick(View v){
            			//view selected list
            			goToList(name); 
            		}
            	});
            	rl.addView(list);
            	num_lists++;
            	String extra = tokenizer.nextToken() + " " + tokenizer.nextToken();
            	
            }
            
        }catch(Exception e){
            Log.e("downloadFile", e.getMessage());
        }
    	
        currentList="";
    	//SET LAYOUT AS CONTENT VIEW
    	setContentView(rl);
    	
    }
    /*
     * createListViewLayout
     * 
     * Creates the layout for the current list (lists the items) dynamically.
     * 
     * 
     */
    public void createListViewLayout(){
    	ScrollView sv = new ScrollView(this);
    	
    	RelativeLayout rl = new RelativeLayout(this);
    	
    	//LIST NAME BUTTON
    	Button list_name = new Button(this);
    	RelativeLayout.LayoutParams p1 = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
    	p1.addRule(RelativeLayout.LEFT_OF, R.id.search);
    	p1.addRule(RelativeLayout.RIGHT_OF, R.id.home);
    	list_name.setLayoutParams(p1);
    	list_name.setCompoundDrawablesWithIntrinsicBounds(R.drawable.modify_icon, 0, R.drawable.delete_small, 0);
    	//list_name.setPadding(0, 0, 0, 20);
    	list_name.setTextSize(15);
    	list_name.setText(currentList);
    	list_name.setId(R.id.name);
    	list_name.setOnClickListener(new OnClickListener(){
    		public void onClick(View v){
    			changeListName(v);
    		}
    	});
    	rl.addView(list_name);
    	//SEARCH BUTTON
    	Button options_but = new Button(this);
    	RelativeLayout.LayoutParams p2 = new RelativeLayout.LayoutParams(75, 75);
    	p2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
    	options_but.setLayoutParams(p2);
    	options_but.setBackgroundResource(R.drawable.search);
    	options_but.setId(R.id.search);
    	options_but.setOnClickListener(new OnClickListener(){
    		public void onClick(View v){
    			goToSearch(v);
    		}
    	});
    	rl.addView(options_but);
    	//HOME BUTTON
    	Button home_but = new Button(this);
    	RelativeLayout.LayoutParams homeParams = new RelativeLayout.LayoutParams(75,75);
    	homeParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
    	home_but.setLayoutParams(homeParams);
    	home_but.setBackgroundResource(R.drawable.home_icon);
    	home_but.setId(R.id.home);
    	home_but.setOnClickListener(new OnClickListener(){
    		public void onClick(View v){
    			goHomeScreen(v);
    		}
    	});
    	rl.addView(home_but);
    	//ADD ITEM BUTTON
    	Button create_but = new Button(this);
    	RelativeLayout.LayoutParams p3 = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
    	p3.addRule(RelativeLayout.BELOW, R.id.search);
    	create_but.setLayoutParams(p3);
    	create_but.setCompoundDrawablesWithIntrinsicBounds(R.drawable.add_button, 0, 0, 0);
    	create_but.setTextSize(20);
    	create_but.setText("Add an item");
    	create_but.setId(R.id.create);
    	create_but.setOnClickListener(new OnClickListener(){
    		public void onClick(View v){
    			goToAddItem(v);
    		}
    	});
    	rl.addView(create_but);
    	//ITEMS IN THE LIST
    	
    	List l = new List(currentUser, currentList);
    	
    	
    	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try{
            DropboxFileInfo info = mDBApi.getFile("/"+currentUser+"/"+currentList+".txt", null, outputStream, null);

            String content = new String(outputStream.toByteArray());
            StringTokenizer tokenizer = new StringTokenizer(content);
            
            int num_items=1;
            int num_checkbox=101;
            while(tokenizer.hasMoreTokens()){
            	//get item data and add to list l
            	String iname = tokenizer.nextToken();
            	String icategory = tokenizer.nextToken();
            	String istore = tokenizer.nextToken();
            	String ipriority = tokenizer.nextToken();
            	String isChecked = tokenizer.nextToken();
            	int p;
            	if(ipriority.equals("1")){p = 1;}
            	else if(ipriority.equals("2")){p = 2;}
            	else if(ipriority.equals("3")){p = 3;}
            	else if(ipriority.equals("4")){p = 4;}
            	else{p = 5;}
            	
            	final Item i = new Item(iname, icategory, istore, p);
            	i.setCheckedOff(isChecked);
            	
            	l.addItem(i);
            }
            //
            //SORTING
            //
            sortItems(l); //sort all the items in the list
            LinkedList<Item> allItems = l.getItems();
            String newContentsSort="";
            for(Item iSort : allItems){
            	newContentsSort += iSort.getItemData() + "\n";
            }
            //Toast.makeText(this, newContentsSort + "\n"+ sortBy, Toast.LENGTH_LONG).show(); //test
            ByteArrayInputStream isSort = new ByteArrayInputStream(newContentsSort.getBytes());
            try {
                DropboxAPI.Entry newEntry = mDBApi.putFileOverwrite("/"+currentUser+"/"+currentList+".txt", isSort,
                       newContentsSort.length(), null);
               //Log.i("DbExampleLog", "The uploaded file's rev is: " + newEntry.rev);
            } catch (DropboxUnlinkedException e) {
               // User has unlinked, ask them to link again here.
               Log.e("DbExampleLog", "User has unlinked.");
            } catch (DropboxException e) {
               Log.e("DbExampleLog", "Something went wrong while updating lists.txt during the sorting process");
            }
            //
            //LAYOUT: add sorted items to the layout
            //
            for(int ll=0;ll<allItems.size();ll++){
            	final Item i = allItems.get(ll);
            	//add items to layout
            	//
            	//CHECK BOX
            	//
            	CheckBox cb = new CheckBox(this);
            	RelativeLayout.LayoutParams cb_param = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            	cb_param.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            	if(num_checkbox == 101){
            		cb_param.addRule(RelativeLayout.BELOW, R.id.create);
            	}
            	else{
            		cb_param.addRule(RelativeLayout.BELOW, num_checkbox-1);
            	}
            	cb.setLayoutParams(cb_param);
            	cb.setId(num_checkbox);
            	cb.setChecked(i.isCheckedOff());
            	cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						// TODO Auto-generated method stub
						String co = isChecked + "";
						i.setCheckedOff(co); //change value in item
						String newContents = "";
						ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				        try{
				            DropboxFileInfo info = mDBApi.getFile("/"+currentUser+"/"+currentList+".txt", null, outputStream, null);

				            String content = new String(outputStream.toByteArray());
				            StringTokenizer tokenizer = new StringTokenizer(content);
				            
				            
				            while(tokenizer.hasMoreTokens()){
				            	String token = tokenizer.nextToken();//name
				            	if(token.equals(i.getName().replaceAll(" ", "_"))){
				            		//add name, category, store, priority, and checked-off to contents
				            		String cat = tokenizer.nextToken();
				            		String store = tokenizer.nextToken();
				            		String priority = tokenizer.nextToken();
				            		String old_check = tokenizer.nextToken();
				            		newContents += token + " " +cat + " " + store + " " + priority + " " + i.isCheckedOff() + "\n";
				            	}
				            	else{
				            		//add name, category, store, priority, and checked-off to contents
				            		newContents += token + " " +tokenizer.nextToken() + " " + tokenizer.nextToken() + " " + tokenizer.nextToken() + " " + tokenizer.nextToken() + "\n";
				            	}
				            }
	 
				        } catch(Exception e){
				        	//catch exception
				        }
						
						//Toast.makeText(buttonView.getContext(), newContents, Toast.LENGTH_LONG).show(); //test
						ByteArrayInputStream is = new ByteArrayInputStream(newContents.getBytes());
				            try {
				                DropboxAPI.Entry newEntry = mDBApi.putFileOverwrite("/"+currentUser+"/"+currentList+".txt", is,
				                       newContents.length(), null);
				               //Log.i("DbExampleLog", "The uploaded file's rev is: " + newEntry.rev);
				            } catch (DropboxUnlinkedException e) {
				               // User has unlinked, ask them to link again here.
				               Log.e("DbExampleLog", "User has unlinked.");
				            } catch (DropboxException e) {
				               Log.e("DbExampleLog", "Something went wrong while updating lists.txt");
				            }
						
					}
				});
            	rl.addView(cb);
            	//
            	//item text view
            	//
            	TextView item = new TextView(this);
            	RelativeLayout.LayoutParams i_param = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
            	if(num_items == 1){
            		i_param.addRule(RelativeLayout.BELOW, R.id.create);
            	}
            	else{
            		i_param.addRule(RelativeLayout.BELOW, num_items-1);
            	}
            	i_param.addRule(RelativeLayout.LEFT_OF, num_checkbox);
            	item.setLayoutParams(i_param);
            	item.setCompoundDrawablesWithIntrinsicBounds(R.drawable.red_line, 0, R.drawable.red_line, R.drawable.blue_line);
            	item.setBackgroundColor(Color.WHITE);
            	item.setTextColor(Color.BLACK);
            	item.setTextSize(20);
            	item.setText(i.getName());
            	item.setId(num_items);
            	item.setClickable(true);
            	item.setOnClickListener(new OnClickListener(){
            		Item iTemp = i;
            		public void onClick(View v){
            			//view selected item
            			viewItem(iTemp);
            		}
            	});
            	rl.addView(item);
            	
            	num_items++;
            	num_checkbox++; 
            	
            }
            /*
            while(tokenizer.hasMoreTokens()){
            	//get item data and add to list l
            	String iname = tokenizer.nextToken();
            	String icategory = tokenizer.nextToken();
            	String istore = tokenizer.nextToken();
            	String ipriority = tokenizer.nextToken();
            	String isChecked = tokenizer.nextToken();
            	int p;
            	if(ipriority.equals("1")){p = 1;}
            	else if(ipriority.equals("2")){p = 2;}
            	else if(ipriority.equals("3")){p = 3;}
            	else if(ipriority.equals("4")){p = 4;}
            	else{p = 5;}
            	
            	final Item i = new Item(iname, icategory, istore, p);
            	i.setCheckedOff(isChecked);
            	
            	l.addItem(i);
            	
            	//add items to layout
            	//checkbox
            	CheckBox cb = new CheckBox(this);
            	RelativeLayout.LayoutParams cb_param = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            	cb_param.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            	if(num_checkbox == 101){
            		cb_param.addRule(RelativeLayout.BELOW, R.id.create);
            	}
            	else{
            		cb_param.addRule(RelativeLayout.BELOW, num_checkbox-1);
            	}
            	cb.setLayoutParams(cb_param);
            	cb.setId(num_checkbox);
            	cb.setChecked(i.isCheckedOff());
            	cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						// TODO Auto-generated method stub
						String co = isChecked + "";
						i.setCheckedOff(co); //change value in item
						String newContents = "";
						ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				        try{
				            DropboxFileInfo info = mDBApi.getFile("/"+currentUser+"/"+currentList+".txt", null, outputStream, null);

				            String content = new String(outputStream.toByteArray());
				            StringTokenizer tokenizer = new StringTokenizer(content);
				            
				            
				            while(tokenizer.hasMoreTokens()){
				            	String token = tokenizer.nextToken();//name
				            	if(token.equals(i.getName().replaceAll(" ", "_"))){
				            		//add name, category, store, priority, and checked-off to contents
				            		String cat = tokenizer.nextToken();
				            		String store = tokenizer.nextToken();
				            		String priority = tokenizer.nextToken();
				            		String old_check = tokenizer.nextToken();
				            		newContents += token + " " +cat + " " + store + " " + priority + " " + i.isCheckedOff() + "\n";
				            	}
				            	else{
				            		//add name, category, store, priority, and checked-off to contents
				            		newContents += token + " " +tokenizer.nextToken() + " " + tokenizer.nextToken() + " " + tokenizer.nextToken() + " " + tokenizer.nextToken() + "\n";
				            	}
				            }
	 
				        } catch(Exception e){
				        	//catch exception
				        }
						
						//Toast.makeText(buttonView.getContext(), newContents, Toast.LENGTH_LONG).show(); //test
						ByteArrayInputStream is = new ByteArrayInputStream(newContents.getBytes());
				            try {
				                DropboxAPI.Entry newEntry = mDBApi.putFileOverwrite("/"+currentUser+"/"+currentList+".txt", is,
				                       newContents.length(), null);
				               //Log.i("DbExampleLog", "The uploaded file's rev is: " + newEntry.rev);
				            } catch (DropboxUnlinkedException e) {
				               // User has unlinked, ask them to link again here.
				               Log.e("DbExampleLog", "User has unlinked.");
				            } catch (DropboxException e) {
				               Log.e("DbExampleLog", "Something went wrong while updating lists.txt");
				            }
						
					}
				});
            	rl.addView(cb);
            	//item text view
            	TextView item = new TextView(this);
            	RelativeLayout.LayoutParams i_param = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
            	if(num_items == 1){
            		i_param.addRule(RelativeLayout.BELOW, R.id.create);
            	}
            	else{
            		i_param.addRule(RelativeLayout.BELOW, num_items-1);
            	}
            	i_param.addRule(RelativeLayout.LEFT_OF, num_checkbox);
            	item.setLayoutParams(i_param);
            	item.setCompoundDrawablesWithIntrinsicBounds(R.drawable.red_line, 0, R.drawable.red_line, R.drawable.blue_line);
            	item.setBackgroundColor(Color.WHITE);
            	item.setTextColor(Color.BLACK);
            	item.setTextSize(20);
            	item.setText(iname);
            	item.setId(num_items);
            	item.setClickable(true);
            	item.setOnClickListener(new OnClickListener(){
            		Item iTemp = i;
            		public void onClick(View v){
            			//view selected item
            			viewItem(iTemp);
            		}
            	});
            	rl.addView(item);
            	
            	num_items++;
            	num_checkbox++; 
            	
            }   */
        }catch(Exception e){
            Log.e("downloadFile", e.getMessage());
        }
        
    	
    	sv.addView(rl);
        setContentView(sv);
    	
    }
    /*
     * view the information of a selected Item
     * 
     */
    public void viewItem(Item i){
    	currentItem = i.getName();
    	setContentView(R.layout.additem);
    	
    	EditText name = (EditText) findViewById(R.id.enterItem);
    	
    	if(name.isSelected()){
    		name.setHint("");
    	}
    	else{
    		name.setHint(i.getName());
    	}
    	
    	EditText category = (EditText) findViewById(R.id.enterCategory);
    	if(category.isSelected()){
    		category.setHint("");
    	}
    	else{
    		category.setHint(i.getCategory());
    	}
    	
    	EditText store = (EditText) findViewById(R.id.enterStore);
    	if(store.isSelected()){
    		store.setHint("");
    	}
    	else{
    		store.setHint(i.getStore());
    	}
    	
    	int priority = i.getPriority();
    	if(priority == 1){
    		RadioButton rb = (RadioButton) findViewById(R.id.RBOne);
    		rb.setChecked(true);
    	}
    	else if(priority == 2){
    		RadioButton rb = (RadioButton) findViewById(R.id.RBTwo);
    		rb.setChecked(true);
    	}
    	else if(priority == 3){
    		RadioButton rb = (RadioButton) findViewById(R.id.RBThree);
    		rb.setChecked(true);
    	}
    	else if(priority == 4){
    		RadioButton rb = (RadioButton) findViewById(R.id.RBFour);
    		rb.setChecked(true);
    	}
    	else{
    		RadioButton rb = (RadioButton) findViewById(R.id.RBFive);
    		rb.setChecked(true);
    	}
    }
    
    /*
     * (non-Javadoc)
     * @see android.app.Activity#onBackPressed()
     * OVERRIDE
     */

    public void onBackPressed(View v){
    	super.onBackPressed();
    	goHomeScreen(v);
    }
    
    /*
     * saveOptions
     * saves the options from the options screen and then goes to the 
     * home screen
     */
    public void saveOptions(View v){
    	//
    	//set SORTING values from radio buttons
    	//
    	RadioButton RBcat = (RadioButton) findViewById(R.id.RB_bycategory);
    	RadioButton RBstore = (RadioButton) findViewById(R.id.RB_bystore);
    	RadioButton RBpriority = (RadioButton) findViewById(R.id.RB_bypriority);
    	RadioButton RBnone = (RadioButton) findViewById(R.id.RB_bynone);
    	if(RBcat.isChecked()){
    		sortBy = SORT_BY_CATEGORY;
    		
    	}
    	else if(RBstore.isChecked()){
    		sortBy = SORT_BY_STORE;
    	}
    	else if(RBpriority.isChecked()){ //if RBpriority.isChecked()
    		sortBy = SORT_BY_PRIORITY;
    	}
    	else if(RBnone.isChecked()){
    		sortBy = 0; //no sorting
    	}
    	//
    	//save REPORT values from check boxes
    	//
    	CheckBox CBcommonitems = (CheckBox) findViewById(R.id.common);
    	CheckBox CBbylastweek = (CheckBox) findViewById(R.id.lastWeek);
    	CheckBox CBall = (CheckBox) findViewById(R.id.lastTrip);
    	boolean itemsReport = false;
    	boolean expenseReport = false;
    	boolean expenseReport1 = false; 
    	boolean expenseReport2 = false;
    	if(CBcommonitems.isChecked()){
    		//change report.txt value to true
    		itemsReport = true;
    	}
    	if(CBbylastweek.isChecked()){
    		//change report.txt value to true
    		expenseReport1 = true;
    		expenseReport = true;
    	}
    	if(CBall.isChecked()){
    		//change report.txt value to true
    		expenseReport2 = false;
    		expenseReport = true;
    	}
    	//
    	//get new contents for report.txt with new REPORT values
    	//
    	String newContents = "";
    	newContents += itemsReport + " " + expenseReport + " " + expenseReport1 + " " + expenseReport2; 
    	ByteArrayInputStream is = new ByteArrayInputStream(newContents.getBytes());
        try {
            DropboxAPI.Entry newEntry = mDBApi.putFileOverwrite("/"+currentUser+"/report.txt", is,
                   newContents.length(), null);
           //Log.i("DbExampleLog", "The uploaded file's rev is: " + newEntry.rev);
        } catch (DropboxUnlinkedException e) {
           // User has unlinked, ask them to link again here.
           Log.e("DbExampleLog", "User has unlinked.");
        } catch (DropboxException e) {
           Log.e("DbExampleLog", "Something went wrong while updating lists.txt");
        }
    	
    	Toast.makeText(this,"Save Successful.",Toast.LENGTH_LONG).show();
    	
    	//Toast.makeText(this,"Function not available.",Toast.LENGTH_LONG).show();
    	goHomeScreen(v);
    }
    
    /*
     * ListsActivity usage
     */
    public void goToList(String list_name){
    	currentList = list_name;
    	//sortItemsByPriority();
    	createListViewLayout();
        //setContentView(R.layout.listview);
    }
    public void goToCurrentList(View v){
    	createListViewLayout();
    }
    
    /*
     * OptionsActivity usage
     */
    public void goToOptions(View v){
    	//Toast.makeText(this,"Function not available.",Toast.LENGTH_LONG).show();
    	//set saved report button info
    	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try{
            DropboxFileInfo info = mDBApi.getFile("/"+currentUser+"/report.txt", null, outputStream, null);
            
            String content = new String(outputStream.toByteArray());
            StringTokenizer st = new StringTokenizer(content);
            //items statistics
            if(st.nextToken().equals("true")){
            	commonItemsReport = true;
            }
            else{
            	commonItemsReport = false;
            }
            //expense report
            String extra = st.nextToken();
            //expense report last week
            if(st.nextToken().equals("true")){
            	expenseReportLastWeek = true;
            }
            else{
            	expenseReportLastWeek = false;
            }
            //expense report all
            if(st.nextToken().equals("true")){
            	expenseReportAll = true;
            }
            else{
            	expenseReportAll = false;
            }
            
        }catch(Exception e){
            Log.e("downloadFile", e.getMessage());
        }
    	//set layout
    	setContentView(R.layout.options);
    	//set saved sorting button info
    	RadioButton RB;
    	if(sortBy==SORT_BY_CATEGORY){
    		RB = (RadioButton) findViewById(R.id.RB_bycategory);
    		RB.setChecked(true);

    	}
    	else if(sortBy==SORT_BY_STORE){
    		RB = (RadioButton) findViewById(R.id.RB_bystore);
    		RB.setChecked(true);
    		
    	}
    	else if(sortBy==SORT_BY_PRIORITY){ //sortBy==SORT_BY_PRIORITY
    		RB = (RadioButton) findViewById(R.id.RB_bypriority);
    		RB.setChecked(true);

    	}
    	else{//sortBy=0
    		RB = (RadioButton) findViewById(R.id.RB_bynone);
    		RB.setChecked(true);
    		
    	}
    	
    	
    	CheckBox CB;
    	if(commonItemsReport==true){
    		CB = (CheckBox) findViewById(R.id.common);
    		CB.setChecked(true);
    	}
    	if(expenseReportAll==true){
    		CB = (CheckBox) findViewById(R.id.lastTrip);
    		CB.setChecked(true);
    	}
    	if(expenseReportLastWeek==true){
    		CB = (CheckBox) findViewById(R.id.lastWeek);
    		CB.setChecked(true);
    	}
        
    }
    
    /*
     * Global usage
     */
    public void goHomeScreen(View v){
        //setContentView(R.layout.lists);
    	createListsLayout();
    }
    /*
     * ListViewActivity usage
     */
    public void goToAddItem(View v){
    	currentItem="";
        setContentView(R.layout.additem);
    }
    /*
     * addNewItem
     * 
     * Adds a new item to the current list. Or edits a current item.
     */
    public void addNewItem(View v){
    	EditText temp = (EditText) findViewById(R.id.enterItem);
    	
    	if(currentItem.equals("")){ //new item
    		//get new item info
            EditText et_name = (EditText) findViewById(R.id.enterItem);
            String name;
            //Toast.makeText(this,et_name.length()+"",Toast.LENGTH_LONG).show();
            //if(et_name.length()==0){
            	//name = null;
            //}else{
            	name = et_name.getText().toString().replaceAll(" ", "_");
            //}
            EditText et_cat = (EditText) findViewById(R.id.enterCategory);
            String cat;
            //if(et_cat.length()==0){
            	//cat = null;
           // }
            //else{
            	cat = et_cat.getText().toString().replaceAll(" ", "_");
            //}
            EditText et_store = (EditText) findViewById(R.id.enterStore);
            String store;
            store = et_store.getText().toString().replaceAll(" ", "_");
            RadioButton rb1 = (RadioButton) findViewById(R.id.RBOne);
            RadioButton rb2 = (RadioButton) findViewById(R.id.RBTwo);
            RadioButton rb3 = (RadioButton) findViewById(R.id.RBThree);
            RadioButton rb4 = (RadioButton) findViewById(R.id.RBFour);
            RadioButton rb5 = (RadioButton) findViewById(R.id.RBFive);
            int priority;
            if(rb1.isChecked()){ priority = 1;}
            else if(rb2.isChecked()){ priority = 2;}
            else if(rb3.isChecked()){ priority = 3;}
            else if(rb4.isChecked()){ priority = 4;}
            else if(rb5.isChecked()){ priority = 5;}
            else{ priority = 1;} //default
  
            //if any fields are blank, then don't change file & put up an error
        	//if(name.equals(null) | cat.equals(null) | store.equals(null)){
        		//Toast.makeText(this, "ERROR: All fields must be filled out.", Toast.LENGTH_LONG);
        	//}
        	//else{//no fields are blank
        		
        		Item newItem = new Item(name, cat, store, priority);
        		//get list content and add item info to list content
                String listName = currentList;
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                try{
                    DropboxFileInfo info = mDBApi.getFile("/"+currentUser+"/"+listName+".txt", null, outputStream, null);
                    
                    String content = new String(outputStream.toByteArray());
                    content += "\n" + newItem.getName() + " " + newItem.getCategory() + " " + newItem.getStore() + " " + newItem.getPriority() + " false";
                    //Toast.makeText(this, content, Toast.LENGTH_LONG).show(); //test
                  
                    ByteArrayInputStream inputStream = new ByteArrayInputStream(content.getBytes());
                    try {
                        DropboxAPI.Entry newEntry = mDBApi.putFileOverwrite("/"+currentUser+"/"+listName+".txt", inputStream,
                               content.length(), null);
                       //Log.i("DbExampleLog", "The uploaded file's rev is: " + newEntry.rev);
                    } catch (DropboxUnlinkedException e) {
                       // User has unlinked, ask them to link again here.
                       Log.e("DbExampleLog", "User has unlinked.");
                    } catch (DropboxException e) {
                       Log.e("DbExampleLog", "Something went wrong while adding new item to list.");
                    }
                    
                }catch(Exception e){
                    Log.e("downloadFile", e.getMessage());
                }
                
                goToList(listName); //go to view current list with added item
        	//} 
    	}
    	else{
    		//get new item info
            EditText et_name = (EditText) findViewById(R.id.enterItem);
            String name = et_name.getText().toString().replaceAll(" ", "_");
            EditText et_cat = (EditText) findViewById(R.id.enterCategory);
            String cat = et_cat.getText().toString().replaceAll(" ", "_");
            EditText et_store = (EditText) findViewById(R.id.enterStore);
            String store = et_store.getText().toString().replaceAll(" ", "_");
            RadioButton rb1 = (RadioButton) findViewById(R.id.RBOne);
            RadioButton rb2 = (RadioButton) findViewById(R.id.RBTwo);
            RadioButton rb3 = (RadioButton) findViewById(R.id.RBThree);
            RadioButton rb4 = (RadioButton) findViewById(R.id.RBFour);
            
            if(name.equals("")){ //empty
            	name = et_name.getHint().toString().replaceAll(" ", "_");
            }
            if(cat.equals("")){ //empty
            	cat = et_cat.getHint().toString().replaceAll(" ", "_");
            }
            if(store.equals("")){
            	store = et_store.getHint().toString().replaceAll(" ", "_");
            }
            int priority;
            if(rb1.isChecked()){ priority = 1;}
            else if(rb2.isChecked()){ priority = 2;}
            else if(rb3.isChecked()){ priority = 3;}
            else if(rb4.isChecked()){ priority = 4;}
            else{ priority = 5;}
            
            String listName = currentList;
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            try{
                DropboxFileInfo info = mDBApi.getFile("/"+currentUser+"/"+listName+".txt", null, outputStream, null);
                
                String content = new String(outputStream.toByteArray());
                StringTokenizer tokenizer = new StringTokenizer(content);
                
                String newContents = "";
                while(tokenizer.hasMoreTokens()){
                	String token = tokenizer.nextToken(); //name
                	//if we find the item we are editing, change the information
                	if(token.equals(et_name.getHint().toString().replaceAll(" ", "_"))){
                		String extraStuff = tokenizer.nextToken() + tokenizer.nextToken() + tokenizer.nextToken(); //category, store, priority
                		String isChecked = tokenizer.nextToken();
                		newContents += name + " " + cat + " " + store + " " + priority + " " + isChecked + "\n";
                		
                	}
                	else{
                		newContents += token + " " + tokenizer.nextToken() + " " + tokenizer.nextToken() + " " + tokenizer.nextToken() + " " + tokenizer.nextToken() +"\n";
                	}
                }
                
                
                ByteArrayInputStream inputStream = new ByteArrayInputStream(newContents.getBytes());
                try {
                    DropboxAPI.Entry newEntry = mDBApi.putFileOverwrite("/"+currentUser+"/"+listName+".txt", inputStream,
                           newContents.length(), null);
                   //Log.i("DbExampleLog", "The uploaded file's rev is: " + newEntry.rev);
                } catch (DropboxUnlinkedException e) {
                   // User has unlinked, ask them to link again here.
                   Log.e("DbExampleLog", "User has unlinked.");
                } catch (DropboxException e) {
                   Log.e("DbExampleLog", "Something went wrong while adding new item to list.");
                }
                
            }catch(Exception e){
                Log.e("downloadFile", e.getMessage());
            }
            goToList(listName); //go to view current list with added item
    	}
    	
    	currentItem = "";
    	
    }
    
    /*
     * goToSearch
     * 
     * Gets user to search item dialog, where a user can 
     * enter in text to search for an item that matches that
     * text. Returns to current list of items when complete.
     * 
     * 
     */
    public void goToSearch(View v){
    	//Toast.makeText(v.getContext(),"The item search feature has been disabled.",Toast.LENGTH_LONG).show(); //message
    	//bring up dialog
    	
    	final Dialog dialog = new Dialog(this);
    	dialog.setContentView(R.layout.dialog);
    	dialog.setTitle("Item Search");
    	
    	Button dialogSave = (Button) dialog.findViewById(R.id.dialog_save);
    	dialogSave.setOnClickListener(new OnClickListener(){
    		public void onClick(View v){ 			
    			EditText dialogEdit = (EditText) dialog.findViewById(R.id.dialog_edit);
    			String item = dialogEdit.getText().toString();
    			sortBy=0; //set sorting to NONE
    			searchForItem(item); //do item search for list
    			
    			//Toast.makeText(v.getContext(),"Function currently not availabe; please continue onto next task.",Toast.LENGTH_LONG).show();
    			dialog.dismiss(); //exit dialog
    			
    		}
    	});
    	
    	dialog.show(); //show dialog on screen
    	
    	
    }
    
    /*
     * searchForItem
     * 
     * Searches for an item in the current list and updates the list so that the searched item
     * is at the top of the list (only if the item exists).
     * 
     * UNDER CONSTRUCTION
     */
    public void searchForItem(String item){
    	//get information for item search from layout
    	//EditText et = (EditText) findViewById(R.id.dialog_edit);
    	//Editable et_edit = et.getText();
    	//String item = et_edit.toString();
    	String listName = currentList;
    	boolean itemFound = false;
    	
    	//list to work with
    	List l = new List(currentUser,currentList);
    	//add items to list
    	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try{
            DropboxFileInfo info = mDBApi.getFile("/"+currentUser+"/"+listName+".txt", null, outputStream, null);
            
            String content = new String(outputStream.toByteArray());
            StringTokenizer tokenizer = new StringTokenizer(content);
            
            while(tokenizer.hasMoreTokens()){
            	//get item data
            	String iname = tokenizer.nextToken();
            	String icat = tokenizer.nextToken();
            	String istore = tokenizer.nextToken();
            	String p = tokenizer.nextToken();
            	int ipriority = Integer.parseInt(p);
            	String icheck = tokenizer.nextToken();
            	//create item object
            	Item newItem = new Item(iname, icat, istore, ipriority);
            	newItem.setCheckedOff(icheck);
            	//add new item object to list
            	l.addItem(newItem); 	
            }
            
          //search for item
            itemFound = l.searchForItem(item);
        	
            //if item was found...
            if(itemFound){
            	LinkedList<Item> allItems = l.getItems();
            	String newContents = ""; //new contents for file
            	//for each item in the list..
            	for(Item i: allItems){
            		//add data to newContents
            		newContents += i.getItemData() + "\n";
            	}
            	//add newContents to file
            	//Toast.makeText(this, newContents, Toast.LENGTH_LONG).show();//test
            	ByteArrayInputStream inputStream = new ByteArrayInputStream(newContents.getBytes());
                try {
                    DropboxAPI.Entry newEntry = mDBApi.putFileOverwrite("/"+currentUser+"/"+listName+".txt", inputStream,
                           newContents.length(), null);
                   //Log.i("DbExampleLog", "The uploaded file's rev is: " + newEntry.rev);
                } catch (DropboxUnlinkedException e) {
                   // User has unlinked, ask them to link again here.
                   Log.e("DbExampleLog", "User has unlinked.");
                } catch (DropboxException e) {
                   Log.e("DbExampleLog", "Something went wrong while updating a list, after item search.");
                }
            }
            else{
            	//no item of that name was found
            	Toast.makeText(this, "Item was not found.", Toast.LENGTH_LONG).show(); //error msg
            }
        }catch(Exception e){
        	Log.e("ItemSearchLog", "Problem when searching for an item in a list.");
        }
        createListViewLayout();
    	   
    }
    
    /*
     * sortItems
     * 
     * Sorts the items in a list by either priority, category, or store.
     * 
     */
    public void sortItems(List l){
    	//sort items within list object
    	if(sortBy == SORT_BY_PRIORITY){
    		l.sortItemsByPriority();
    	}
    	else if(sortBy == SORT_BY_CATEGORY){
    		l.sortItemsByCategory();
    	}
    	else if(sortBy == SORT_BY_STORE){ //sortBy == SORT_BY_STORE
    		l.sortItemsByStore();
    	}
    	
    }
 

}
