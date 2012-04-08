import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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


public class SignUp extends JFrame implements ActionListener, KeyListener{

	private JButton signUp = new JButton("Sign Up");
	private JButton close = new JButton("Close");
	private JTextField username = new JTextField("Enter username here");
	private JPasswordField password = new JPasswordField();
	private SignUp signUpWindow;
	private DropboxAPI<WebAuthSession> mDBApi;
	
	public SignUp(DropboxAPI<WebAuthSession> mDBApi)
	{
		super("iBuy - SignUp");
		this.mDBApi = mDBApi;
		//Layout is here
  	    setLayout(new GridLayout(4,1));
	    setSize(500, 250);
	    
        //Buttons are predefined in class but are changed here
	    username.addKeyListener(this);
  	    password.addKeyListener(this);
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
			new Home();
			setVisible(false);
			dispose();
		}
  		if(e.getSource() == signUp)
  		{
  			signUp();
  		}
	}
	
	public void signUp()
	{
		String user = username.getText();
		String pass = password.getText();
		if(user.equals("") || pass.equals(""))
			System.out.println("Please Complete Fields");
		else if (user.contains(" ") || pass.contains(" "))
		{
			System.out.println("No spaces, please.");
			String trimUser = "";
			String trimPass = "";
			for(int i = 0; i < user.length(); i++)
			{
				if(user.charAt(i) != ' ')
					trimUser += user.charAt(i);
			}
			for(int j = 0; j < user.length(); j++)
			{
				if(pass.charAt(j) != ' ')
					trimPass += pass.charAt(j);
			}
			username.setText(trimUser);
			password.setText(trimPass);
		}
		else
		{
			String usersFile = Global.getFile(mDBApi, "/users.txt");
			StringTokenizer scanner = new StringTokenizer(usersFile);
			boolean usernameIsTaken = false;
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
				//Adds user
				usersFile += user + "\t" + pass + "\n";
				Global.putFileOverwrite(mDBApi, "/users.txt", usersFile);
	        	
	        	//Create folder for user and first list
				Global.makeFolder(mDBApi, "/" + user, "/" + user + "/lists.txt", "first_list\n");
	        	
	        	//Creates first list contents
	        	String firstList = "Apple\tFood\tGrocery\t1\t0\n" +
	        					   "Toilet_Paper\tPaper_Good\tGrocery\t3\t1\n";
	        	Global.putFile(mDBApi, "/" + user + "/first_list.txt", firstList);
	        	
	        	//Redirect User to Menu
				new MainMenu(user, mDBApi);
				setVisible(false);
	        	dispose();
			}
		}
	}
	
	public void keyPressed(KeyEvent e) {
		if((password.isCursorSet() || username.isCursorSet()) && e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			signUp();
		}
	}

	
	public void keyReleased(KeyEvent e) {
		
	}

	public void keyTyped(KeyEvent e) {
		
	}
}
