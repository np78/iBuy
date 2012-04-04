import java.awt.Container;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URL;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.DropboxAPI.DropboxFileInfo;
import com.dropbox.client2.DropboxAPI.Entry;
import com.dropbox.client2.exception.DropboxException;
import com.dropbox.client2.session.*;
import com.dropbox.client2.session.Session.AccessType;
import com.dropbox.client2.session.WebAuthSession.WebAuthInfo;


public class Home extends JFrame implements ActionListener {
	
	private static DropboxAPI<WebAuthSession> mDBApi;
	public static String APIKey = "nn7lnxubh1knv63";
	public static String APISecret = "l02f3ekx7lc7wx2";
	final static public AccessType ACCESS_TYPE = AccessType.APP_FOLDER;
	public static String sessionKey = "wiaqh0zpg745z2v";
	public static String sessionSecret = "5elro586b19u7n2";
	
	private static JButton login = new JButton("Login");
	private static JButton signUp = new JButton("New? Sign Up!");
	private static JTextField username = new JTextField("Enter username here");
	private static JPasswordField password = new JPasswordField();

	public static void main(String[] args) {	
        new Home();
	}
	
	public Home()
	{
		super("iBuy - Login");
		setSize(500,300);
		createSession();
  	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  	    
  	    //Buttons are predefined in class but are changed here
  	    username.addActionListener(this);
  	    password.addActionListener(this);
  	    signUp.addActionListener(this);
  	    login.addActionListener(this);
  	    
  	    //Predefined layout here
	    setLayout(new GridLayout(4,1));
	    add(username);
	    add(password);
	    add(login);
	    add(signUp);
		
        setVisible(true);
	}
	
	public static void createSession()
	{
		//Create Session
		AppKeyPair appKeyPair = new AppKeyPair(APIKey, APISecret);
        WebAuthSession session = new WebAuthSession(appKeyPair, ACCESS_TYPE);
        mDBApi = new DropboxAPI<WebAuthSession>(session);
        mDBApi.getSession().setAccessTokenPair(new AccessTokenPair(sessionKey, sessionSecret));
	}
	
	public void actionPerformed(ActionEvent e) {
  		if(e.getSource() == signUp)
  		{
  			new SignUp();
  			setVisible(false);
  			dispose();
  		}
  		if(e.getSource() == login)
  		{
  			String user = username.getText();
  			String pass = password.getText();
  			if(user.equals("") || pass.equals(""))
  				System.out.println("Please Complete Fields");
  			else
  			{
	  			try
	  			{
	  				//Reads users.txt to String to StringTokenizer
		  			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		            DropboxFileInfo info = mDBApi.getFile("/users.txt", null, outputStream, null);
		  			StringTokenizer scanner = new StringTokenizer(new String(outputStream.toByteArray()));
		  			//Checks if "user" is in list and if password matchs
		  			while(scanner.hasMoreTokens())
		  			{
		  				String username = scanner.nextToken();
		  				String password = scanner.nextToken();
		  				if(username.equals(user) && password.equals(pass))
		  				{
		  					System.out.println("Login Complete");
		  					new ListMenu(user);
		  					setVisible(false);
		  					dispose();
		  				}
		  			}
	  			}
	  			catch (DropboxException de) {
	  				System.out.println("Connnection down. Unable to Login.");
	  			}
  			}
  		}
	}
}
