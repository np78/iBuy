import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
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
public class ListMenu extends JFrame implements ActionListener{
	private static DropboxAPI<WebAuthSession> mDBApi;
	private static LinkedList<JButton> list = new LinkedList<JButton>();
	private static JButton logout = new JButton("Log Out");
	private static String user;
	
	public ListMenu(String user) 
	{
		super("iBuy - List Menu");
		this.user = user;
	    
		//Create session
	    AppKeyPair appKeyPair = new AppKeyPair(Home.APIKey, Home.APISecret);
        WebAuthSession session = new WebAuthSession(appKeyPair, Home.ACCESS_TYPE);
        mDBApi = new DropboxAPI<WebAuthSession>(session);
        mDBApi.getSession().setAccessTokenPair(new AccessTokenPair(Home.sessionKey, Home.sessionSecret));
        
        //Reads lists.txt in User folder
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
			DropboxFileInfo info = mDBApi.getFile("/"+user+"/lists.txt", null, outputStream, null);
		} catch (DropboxException e) {}
        list.clear();
        //Dynamically adds new Button for each list
		StringTokenizer st = new StringTokenizer(new String(outputStream.toByteArray()));
		while(st.hasMoreTokens())
		{
			JButton j = new JButton(readFileName(st.nextToken()));
			j.addActionListener(this);
			list.add(j);
		}
		//Sets layout with list size in mind
		setLayout(new GridLayout(list.size()+1,1));
	    setSize(500, (200 + 100*list.size()));
		for(int i = 0; i < list.size(); i++)
		{
		    add(list.get(i));
		}
	    
	    logout.addActionListener(this);
	    add(logout);
	    
	    setVisible(true);
	}
	
	//Replaces underscores of file name with spaces
	public String readFileName(String st)
	{
		String re = "";
		for(int i = 0; i < st.length(); i++)
		{
			if(st.charAt(i) == '_')
				re += " ";
			else
				re += st.charAt(i);
		}
		return re;
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == logout)
		{
			Home home = new Home();
			setVisible(false);
			dispose();
		}
		//Checks which button is pressed and shifts to List passing the list's name
		for(int i = 0; i < list.size(); i++)
		{
			if(e.getSource() == list.get(i))
			{
				new List(user, list.get(i).getText());
				setVisible(false);
				dispose();
			}
		}
	}
}
