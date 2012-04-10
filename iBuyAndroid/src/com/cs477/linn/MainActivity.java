package com.cs477.linn;

import android.R.layout;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
//import com.dropbox.client2.DropboxAPI;
//import com.dropbox.client2.android.AndroidAuthSession;
//import com.dropbox.client2.session.AccessTokenPair;
//import com.dropbox.client2.session.AppKeyPair;
//import com.dropbox.client2.session.Session.AccessType;

public class MainActivity extends Activity
{
    //DROPBOX INSTANCE VARIABLES
    /*
    final static private String APP_KEY = "nn7lnxubh1knv63";
    final static private String APP_SECRET = "l02f3ekx7lc7wx2";
    final static private AccessType ACCESS_TYPE = AccessType.APP_FOLDER;
    public static String sessionKey = "wiaqh0zpg745z2v";
    public static String sessionSecret = "5elro586b19u7n2"; 
    private DropboxAPI<AndroidAuthSession> mDBApi;*/
    
    
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
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

                AccessTokenPair tokens = mDBApi.getSession().getAccessTokenPair();
            } catch (IllegalStateException e) {
                Log.i("DbAuthLog", "Error authenticating", e);
            }
        }
    }*/
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
            DropboxAPI.Entry newEntry = mDBApi.putFileOverwrite("/"+curList.list_name+".txt", inputStream,
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
    /*
    public void downloadFile(View v){
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
    /**
     * Adds a new user.  Adds the user name and password to the user file &
     * adds a new folder for the user's files.
     */
    /*
    public void addNewUser(String username, String password) throws DropboxException{
        String folderpath = "/"+username;
        mDBApi.createFolder(folderpath);
        
        String userspath = "/users.txt";
        //add username and password to users.txt
        
    }
    */
    /**
     * Adds an item to the list in the current users folder.
     */
    /*
    public void addItemToList(String cur_user, String list_name, Item item){
        String filename = "/"+cur_user+"/"+list_name+".txt";
        String item_name = item.getName();
        String item_category = item.getCategory();
        //Add item and category to list
    }
    */
    /**
     * Deletes an item from a list in the user's folder.
     * @param cur_user
     * @param list_name
     * @param item 
     */
    /*
    public void deleteItem(String cur_user, String list_name, Item item){
        String item_name = item.getName();
        String item_category = item.getCategory();
        
        String filename = "/"+cur_user+"/"+list_name+".txt";
        //delete specific item from list
    }
    */
    /**
     * logIn
     * Takes the contents of the username and password fields and attempts
     * to login to the application. Does nothing if unable to login.
     * @param v 
     */
    /*
    public void logIn(View v){
        //username
        EditText user_text = (EditText) findViewById(R.id.useredit);
        Editable user_edit = user_text.getText();
        String username = user_edit.toString();
        //password
        EditText pass_text = (EditText) findViewById(R.id.pwedit);
        Editable pass_edit = pass_text.getText();
        String password = pass_edit.toString();
        Toast.makeText(this,username+"/"+password,Toast.LENGTH_LONG).show();
        //if both fields are not empty, then attempt login
        if(!username.equals("") && !password.equals("")){
            try {
                isLoggedIn = checkUserFile(username, password);
            } catch (IOException ex) {
                Logger.getLogger(iBuyActivity.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(isLoggedIn){
            //go to ListsActivity and lists screen
            startActivity(new Intent("edu.nau.cs477.CLEARSCREEN"));
        }       
    }   */ 
    /**
     * checkUserFile
     * Downloads users.txt from Dropbox and checks to see if the username and
     * password match the contents in the file.
     * 
     * @param username
     * @param password 
     * @return boolean
     */
    /*
    public boolean checkUserFile(String username, String password) throws IOException{
        //TextView file_view = (TextView) v.findViewById(R.id.text_download);
        //Get file
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try{
            DropboxFileInfo info = mDBApi.getFile("/users.txt", null, outputStream, null);
            //DropboxInputStream dis = mDBApi.getFileStream("/users.txt", null);
            //dis.copyStreamToOutput(outputStream, null);
            String content = new String(outputStream.toByteArray());
            StringTokenizer st = new StringTokenizer(content);
            outputStream.close();
            while(st.hasMoreTokens()){
                //if username and password match in user contents file
                if(st.nextToken().equals(username) && st.nextToken().equals(password)){
                    //Toast.makeText(this,"loggedIn",Toast.LENGTH_LONG).show();
                    return true;
                    
                }
            }         
        }catch(DropboxException e){
            Log.e("DbExampleLog", "Something went wrong while downloading.");
        }
        //Toast.makeText(this,"NOT loggedIn",Toast.LENGTH_LONG).show();
        return false; //username and password don't match file contents
    } */  
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
        
        TextView newItem = new TextView(this);
        newItem.setId(1);
        newItem.setText("apple");
        RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT);
        relativeParams.addRule(RelativeLayout.BELOW, R.id.item1);
        /*
        newItem.setClickable(true);
        newItem.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {
                goToAddItem(arg0);
            }
        });*/
        
        listviewlayout.addView(newItem, relativeParams);
        
        setContentView(R.layout.listview);
        
    }
}
