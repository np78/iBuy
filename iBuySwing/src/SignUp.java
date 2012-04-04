import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.DropboxAPI.DropboxFileInfo;
import com.dropbox.client2.DropboxAPI.Entry;
import com.dropbox.client2.exception.DropboxException;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.RequestTokenPair;
import com.dropbox.client2.session.Session;
import com.dropbox.client2.session.WebAuthSession;


public class SignUp extends JFrame implements ActionListener{

	private static JButton signUp = new JButton("Sign Up");
	private static JButton close = new JButton("Close");
	private static JTextField username = new JTextField("Enter username here");
	private static JPasswordField password = new JPasswordField();
	private static SignUp signUpWindow;
	private static DropboxAPI<WebAuthSession> mDBApi;
	
	public SignUp()
	{
		super("iBuy - SignUp");
		//Layout is here
  	    setLayout(new GridLayout(4,1));
	    setSize(500, 250);
	    
	    //Create session
	    AppKeyPair appKeyPair = new AppKeyPair(Home.APIKey, Home.APISecret);
        WebAuthSession session = new WebAuthSession(appKeyPair, Home.ACCESS_TYPE);
        mDBApi = new DropboxAPI<WebAuthSession>(session);
        mDBApi.getSession().setAccessTokenPair(new AccessTokenPair(Home.sessionKey, Home.sessionSecret));
	    
        //Buttons are predefined in class but are changed here
	    username.addActionListener(this);
  	    password.addActionListener(this);
  	    signUp.addActionListener(this);
  	    close.addActionListener(this);
	    
  	    //Layout is here also
	    add(username);
	    add(password);
	    add(signUp);
	    add(close);
	    
	    setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == close)
		{
			Home h = new Home();
			setVisible(false);
			dispose();
		}
  		if(e.getSource() == signUp)
  		{
  			String user = username.getText();
  			String pass = password.getText();
  			boolean usernameIsTaken = false;
  			if(user.equals("") || pass.equals(""))
  				System.out.println("Please Complete Fields");
  			else
  			{
	  			try
	  			{
	  				//Reads users.txt to String to StringTokenizer
	  				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		            DropboxFileInfo info = mDBApi.getFile("/users.txt", null, outputStream, null);
		            //newUserFile is used later
		            String newUserFile = new String(outputStream.toByteArray());
		            StringTokenizer scanner = new StringTokenizer(new String(outputStream.toByteArray()));
		  			//Checks if username is already taken
		            while(scanner.hasMoreTokens())
		  			{
		  				String username = scanner.nextToken();
		  				String password = scanner.nextToken();
		  				if(username.equals(user))
		  				{
		  					usernameIsTaken = true;
		  				}
		  			}
					if(usernameIsTaken)
					{
						System.out.println("Username is taken.");
					}
					else
					{
						if(pass.contains(" "))
							System.out.println("No spaces in password.");
						else
						{
							//Adds username and password to end of string and rewrites users.txt
							newUserFile += user + "\t" + pass + "\n";
				        	ByteArrayInputStream inputStream = new ByteArrayInputStream(newUserFile.getBytes());
				        	mDBApi.putFileOverwrite("/users.txt", inputStream, newUserFile.length(), null);
				        	
				        	//Create folder for user
				        	mDBApi.createFolder("/" + user);
				        	String lists = "first_list\n";
				        	inputStream = new ByteArrayInputStream(lists.getBytes());
				        	mDBApi.putFile("/" + user + "/lists.txt", inputStream, lists.length(), null, null);
				        	
				        	//Creates first list
				        	String firstList = "Apple\tFood\tGrocery\t1\t0\n" +
				        					   "Toilet_Paper\tPaper_Good\tGrocery\t3\t1\n";
				        	inputStream = new ByteArrayInputStream(firstList.getBytes());
				        	mDBApi.putFile("/" + user + "/first_list.txt", inputStream, firstList.length(), null, null);
							new ListMenu(user);
							setVisible(false);
				        	dispose();
						}
					}
	  			}
	  			catch (DropboxException db) {}
  			}
  		}
	}
}
