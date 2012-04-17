import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.DropboxAPI.DropboxFileInfo;
import com.dropbox.client2.exception.DropboxException;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.WebAuthSession;

/*
 * Home menu where user select which list they want to view
 * Future: has add, remove, and edit name buttons that affect
 * Dropbox files and 
 */
public class MainMenu extends JFrame implements ActionListener{
	private DropboxAPI<WebAuthSession> mDBApi;
	private LinkedList<JButton> list = new LinkedList<JButton>();
	private JButton logout = new JButton("Log Out");
	private JButton delete = new JButton("Delete List");
	private JButton add = new JButton("Add List");
	private JButton options = new JButton("Options");
	private JButton refresh = new JButton("Refresh");
	private String user;
	
	public MainMenu(String user, DropboxAPI<WebAuthSession> mDBApi) 
	{
		super("iBuy - Main Menu");
		this.user = user;
		this.mDBApi = mDBApi;
	    
	    //Reads users list of lists
	    StringTokenizer st = new StringTokenizer(Global.getFile(mDBApi, "/"+user+"/lists.txt"));
	    list.clear();
		while(st.hasMoreTokens())
		{
			JButton j = new JButton(Global.readFileName(st.nextToken()));
			st.nextToken();
			st.nextToken();
			j.addActionListener(this);
			list.add(j);
		}
		
		//Sets layout with list size in mind
		setLayout(new GridLayout(list.size()+5,1));
		setSize(500, Math.min((200 + 100*list.size()), 1000));
		delete.addActionListener(this);
	    add.addActionListener(this);
	    refresh.addActionListener(this);
	    add(refresh);
	    add(add);
	    add(delete);
		for(int i = 0; i < list.size(); i++)
		{
		    add(list.get(i));
		}
		options.addActionListener(this);
	    add(options);
	    logout.addActionListener(this);
	    add(logout);
	    
	    setContentPane(new JScrollPane(getContentPane()));
	    setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == logout)
		{
			new Home();
			setVisible(false);
			dispose();
		}
		if(e.getSource() == delete)
		{
			new DeleteMenu(user, mDBApi);
			setVisible(false);
			dispose();
		}
		if(e.getSource() == refresh)
		{
			new MainMenu(user, mDBApi);
			setVisible(false);
			dispose();
		}
		if(e.getSource() == add)
		{
			new AddMenu(user, mDBApi);
			setVisible(false);
			dispose();
		}
		if(e.getSource() == options)
		{
			new OptionsMenu(user, mDBApi);
			setVisible(false);
			dispose();
		}
		//Checks which button is pressed and shifts to List passing the list's name
		for(int i = 0; i < list.size(); i++)
		{
			if(e.getSource() == list.get(i))
			{
				new List(user, list.get(i).getText(), mDBApi);
				setVisible(false);
				dispose();
			}
		}
	}
}
