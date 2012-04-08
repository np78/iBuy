import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.exception.DropboxException;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.WebAuthSession;


public class DeleteMenu extends JFrame implements ActionListener{
	private DropboxAPI<WebAuthSession> mDBApi;
	private LinkedList<JButton> list = new LinkedList<JButton>();
	private JButton cancel = new JButton("Back");
	private String user;
	
	public DeleteMenu(String user, DropboxAPI<WebAuthSession> mDBApi) 
	{
		super("iBuy - Delete Menu");
		this.user = user;
		this.mDBApi = mDBApi;
		
		//Reads users list of lists
		list.clear();
	    StringTokenizer st = new StringTokenizer(Global.getFile(mDBApi, "/"+user+"/lists.txt"));
		while(st.hasMoreTokens())
		{
			JButton j = new JButton(Global.readFileName(st.nextToken()));
			st.nextToken();
			st.nextToken();
			j.addActionListener(this);
			list.add(j);
		}
		setLayout(new GridLayout(list.size()+1,1));
		setSize(500, Math.min((200 + 100*list.size()), 1000));
		for(int i = 0; i < list.size(); i++)
		{
		    add(list.get(i));
		}
        
        cancel.addActionListener(this);
	    add(cancel);
		
        setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cancel)
		{
			new MainMenu(user, mDBApi);
			setVisible(false);
			dispose();
		}
		for(int i = 0; i < list.size(); i++)
		{
			if(e.getSource() == list.get(i))
			{
				//Delete file
				Global.delete(mDBApi, "/" + user + "/" + Global.toFileName(list.get(i).getText()) + ".txt");
				StringTokenizer st = new StringTokenizer(Global.getFile(mDBApi, "/" + user + "/lists.txt"));
				String newList = "";
				//Finds filename in list and removes it
				while(st.hasMoreTokens())
				{
					String token = st.nextToken();
					if(!token.equals(Global.toFileName(list.get(i).getText())))
						newList += token + "\t" + st.nextToken() + "\t" + st.nextToken() + "\n";
					else
					{
						st.nextToken();
						st.nextToken();
					}
				}
				Global.putFileOverwrite(mDBApi, "/" + user + "/lists.txt", newList);
				
				//"Refreshes" window
				new DeleteMenu(user, mDBApi);
				setVisible(false);
				dispose();
			}
		}
	}
}
