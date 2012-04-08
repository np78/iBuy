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
import javax.swing.JCheckBox;
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
	
	private DropboxAPI<WebAuthSession> mDBApi;
	
	private JButton login = new JButton("Login");
	private JButton signUp = new JButton("New? Sign Up!");
	private JTextField username = new JTextField("Enter username here");
	private JPasswordField password = new JPasswordField();

	public static void main(String[] args) {
		new Home();
	}
	
	public Home()
	{
		super("iBuy - Login");
		setSize(500,300);
		mDBApi = Global.createSession();
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
	
	public void actionPerformed(ActionEvent e) {
  		if(e.getSource() == signUp)
  		{
  			new SignUp(mDBApi);
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
  				//Reads users.txt to String to StringTokenizer
	  			StringTokenizer scanner = new StringTokenizer(Global.getFile(mDBApi, "/users.txt"));
	  			//Checks if "user" is in list and if password matches
	  			while(scanner.hasMoreTokens())
	  			{
	  				String username = scanner.nextToken();
	  				String password = scanner.nextToken();
	  				if(username.equals(user) && password.equals(pass))
	  				{
	  					System.out.println("Login Complete");
	  					new MainMenu(user, mDBApi);
	  					setVisible(false);
	  					dispose();
	  				}
	  			}
  			}
  		}
	}
}
