package com.cs477.linn;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
 * @version 4.12.2012
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
    private String currentItem="";
    
    
    
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
    	
    }
    public void changeListName(View v){
    	setContentView(R.layout.listedit);
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
            
    	}
    	else{ //if list is not a new list, do this stuff
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
    	}
    	
    	
        goHomeScreen(v);	
    }
    
    /*
     * deleteList
     * 
     * Deletes a list from the Dropbox.
     */
    public void deleteList(View v) throws DropboxException{
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
            		Toast.makeText(this,"ListName = "+listName,Toast.LENGTH_LONG).show(); //test
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
        goHomeScreen(v);
      Toast.makeText(this,listName+" was deleted.",Toast.LENGTH_LONG).show();
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
    	//DISPLAY TEXTS
    	TextView display_text = new TextView(this);
    	RelativeLayout.LayoutParams p2 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    	p2.addRule(RelativeLayout.LEFT_OF, R.id.options);
    	p2.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
    	display_text.setLayoutParams(p2);
    	display_text.setPadding(0,0,0,20);
    	display_text.setTextSize(15);
    	display_text.setText("Tap a list to modify it, or tap 'Create new list' to start a new list");
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
    	p1.addRule(RelativeLayout.LEFT_OF, R.id.options);
    	list_name.setLayoutParams(p1);
    	list_name.setPadding(0, 0, 0, 20);
    	list_name.setTextSize(15);
    	list_name.setText(currentList);
    	list_name.setId(R.id.name);
    	list_name.setOnClickListener(new OnClickListener(){
    		public void onClick(View v){
    			changeListName(v);
    		}
    	});
    	rl.addView(list_name);
    	//OPTIONS BUTTON
    	Button options_but = new Button(this);
    	RelativeLayout.LayoutParams p2 = new RelativeLayout.LayoutParams(75, 75);
    	p2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
    	options_but.setLayoutParams(p2);
    	options_but.setBackgroundResource(R.drawable.options_icon);
    	options_but.setId(R.id.options);
    	options_but.setOnClickListener(new OnClickListener(){
    		public void onClick(View v){
    			goToOptions(v);
    		}
    	});
    	rl.addView(options_but);
    	//ADD ITEM BUTTON
    	Button create_but = new Button(this);
    	RelativeLayout.LayoutParams p3 = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
    	p3.addRule(RelativeLayout.BELOW, R.id.options);
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
				            	String token = tokenizer.nextToken();
				            	if(token.equals(i.getName().replaceAll(" ", "_"))){
				            		newContents += token + " " +tokenizer.nextToken() + " " + tokenizer.nextToken() + " " + tokenizer.nextToken() + " " + i.isCheckedOff() + "\n";
				            	}
				            	else{
				            		newContents += token + " " +tokenizer.nextToken() + " " + tokenizer.nextToken() + " " + tokenizer.nextToken() + " " + tokenizer.nextToken() + "\n";
				            	}
				            }
	 
				        } catch(Exception e){
				        	//catch exception
				        }
						
						
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
            	
            }   
        }catch(Exception e){
            Log.e("downloadFile", e.getMessage());
        }
        
    	
    	sv.addView(rl);
        setContentView(sv);
    	
    }
    /*
     * view the information of a selected Item
     * UNDER CONSTRUCTION
     */
    public void viewItem(Item i){
    	currentItem = i.getName();
    	setContentView(R.layout.additem);
    	
    	EditText name = (EditText) findViewById(R.id.enterItem);
    	name.setHint(i.getName());
    	//name.setText(i.getName(), TextView.BufferType.EDITABLE);
    	EditText category = (EditText) findViewById(R.id.enterCategory);
    	category.setHint(i.getCategory());
    	//category.setText(i.getCategory(), TextView.BufferType.EDITABLE);
    	
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
    public void onBackPressed(){
    	goHomeScreen(new View(this));
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
    public void goToList(String list_name){
    	currentList = list_name;
    	createListViewLayout();
        //setContentView(R.layout.listview);
    }
    
    /*
     * OptionsActivity usage
     */
    public void goToOptions(View v){
    	//Toast.makeText(this,"Function not available.",Toast.LENGTH_LONG).show();
        setContentView(R.layout.options);
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
            String name = et_name.getText().toString().replaceAll(" ", "_");
            EditText et_cat = (EditText) findViewById(R.id.enterCategory);
            String cat = et_cat.getText().toString().replaceAll(" ", "_");
            String store = "N/A";
            RadioButton rb1 = (RadioButton) findViewById(R.id.RBOne);
            RadioButton rb2 = (RadioButton) findViewById(R.id.RBTwo);
            RadioButton rb3 = (RadioButton) findViewById(R.id.RBThree);
            RadioButton rb4 = (RadioButton) findViewById(R.id.RBFour);
            //RadioButton rb5 = (RadioButton) findViewById(R.id.RBFive);
            int priority;
            if(rb1.isChecked()){ priority = 1;}
            else if(rb2.isChecked()){ priority = 2;}
            else if(rb3.isChecked()){ priority = 3;}
            else if(rb4.isChecked()){ priority = 4;}
            else{ priority = 5;}
            
            Item newItem = new Item(name, cat, store, priority);
            
            //get list content and add item info to list content
            String listName = currentList;
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            try{
                DropboxFileInfo info = mDBApi.getFile("/"+currentUser+"/"+listName+".txt", null, outputStream, null);
                
                String content = new String(outputStream.toByteArray());
                content += "\n" + newItem.getName() + " " + newItem.getCategory() + " " + newItem.getStore() + " " + newItem.getPriority() + " false";
                
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
            
    	}
    	else{
    		//get new item info
            EditText et_name = (EditText) findViewById(R.id.enterItem);
            String name = et_name.getText().toString().replaceAll(" ", "_");
            EditText et_cat = (EditText) findViewById(R.id.enterCategory);
            String cat = et_cat.getText().toString().replaceAll(" ", "_");
            String store = "N/A";
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
                	String token = tokenizer.nextToken();
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

}
