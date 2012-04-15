import java.awt.Container;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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


public class Home extends JFrame implements ActionListener, KeyListener {
	
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
  	    username.addKeyListener(this);
  	    password.addKeyListener(this);
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
  			login();
  		}
	}
	
	public void getReports(String user)
	{	
		StringTokenizer st = new StringTokenizer(Global.getFile(mDBApi, "/"+user+"/report.txt"));
		boolean[] bool = new boolean[4];
		int i = 0;
		while(st.hasMoreTokens())
		{
			String token = st.nextToken();
			if(token.equals("false"))
				bool[i] = false;
			else
				bool[i] = true;
			i++;
		}
		if(bool[0])
		{
			new ItemStatistics(user, mDBApi);
		}
		if(bool[1])
		{
			new ExpenseTracker(user, mDBApi, bool[2], bool[3]);
		}
	}
	
	public void login()
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
  					getReports(user);
  					new MainMenu(user, mDBApi);
  					setVisible(false);
  					dispose();
  				}
  			}
		}
	}

	public void keyPressed(KeyEvent e) {
		if((password.isCursorSet() || username.isCursorSet()) && e.getKeyCode() == KeyEvent.VK_ENTER)
			login();
	}

	
	public void keyReleased(KeyEvent e) {
		
	}

	public void keyTyped(KeyEvent e) {
		
	}
}
