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
import android.widget.EditText;
import android.widget.RelativeLayout;
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
            Log.e("DbExampleLog", "Something went wrong while downloading.");
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
    
    //CRASHES
    /*
     * saveListName
     * 
     * 
     */
    public void saveListName(View v){
    	
    	
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
    	RelativeLayout.LayoutParams p1 = new RelativeLayout.LayoutParams(100, 100);
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
    			setContentView(R.layout.listview);
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
            	currentList = list_name;
            	
            	TextView list = new TextView(this);
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
            		public void onClick(View v){
            			//view selected list
            			goToList(currentList); 
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
     * CRASHES
     */
    public void createListViewLayout(){
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
    			//change name of list
    		}
    	});
    	rl.addView(list_name);
    	//OPTIONS BUTTON
    	Button options_but = new Button(this);
    	RelativeLayout.LayoutParams p2 = new RelativeLayout.LayoutParams(100, 100);
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
    	RelativeLayout.LayoutParams p3 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
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
            	
            	Item i = new Item(iname, icategory, istore, p);
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
            		//onCheck stuff ...
            		//...
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
            		public void onClick(View v){
            			goToAddItem(v);
            		}
            	});
            	rl.addView(item);
            	
            	num_items++;
            	num_checkbox++;
            	
            }   
        }catch(Exception e){
            Log.e("downloadFile", e.getMessage());
        }
        
        setContentView(rl);
    	
    }
    /*
     * create the layout for a specific item
     */
    public void createItemLayout(Item i){
    	
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
        //setContentView(R.layout.lists);
    	createListsLayout();
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
