import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.session.WebAuthSession;


public class ChangeMenu extends JFrame implements ActionListener{
	private DropboxAPI<WebAuthSession> mDBApi;
	private JTextField entry = new JTextField();
	private JButton cancel = new JButton("Back");
	private JButton update = new JButton("Update");
	private String user, filename;
		
	public ChangeMenu(String user, String filename, DropboxAPI<WebAuthSession> mDBApi) 
	{
		super("iBuy - Change Menu");
		this.user = user;
		this.filename = filename;
		this.mDBApi = mDBApi;
		
		setLayout(new GridLayout(3,1));
		setSize(500,300);
		
		entry.setText(filename);
        cancel.addActionListener(this);
        update.addActionListener(this);
        add(entry);
        add(update);
	    add(cancel);
		
        setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cancel)
		{
			new List(user, filename, mDBApi);
			setVisible(false);
			dispose();
		}
		if(e.getSource() == update)
		{
			String newName = entry.getText();
			
			//Checks if name is taken
			String listNames = Global.getFile(mDBApi, "/" + user + "/lists.txt");
			StringTokenizer scanner = new StringTokenizer(listNames);
			boolean isTaken = false;
			while(scanner.hasMoreTokens())
  			{
  				String listname = scanner.nextToken();
  				if(listname.equals(Global.toFileName(newName)))
  				{
  					isTaken = true;
  				}
  			}
			if(isTaken)
			{
				System.out.println("Username is taken.");
			}
			else
			{
				//Read contents of file and writes to new file
				String contents = Global.getFile(mDBApi, "/" + user + "/" + Global.toFileName(filename) + ".txt");
				Global.putFile(mDBApi, "/" + user + "/" + Global.toFileName(entry.getText()) + ".txt", contents);
				
				//Delete old file
				Global.delete(mDBApi, "/" + user + "/" + Global.toFileName(filename) + ".txt");
				
				//Finds filename in list and changes it
				StringTokenizer st = new StringTokenizer(Global.getFile(mDBApi, "/" + user + "/lists.txt"));
				String newList = "";
				while(st.hasMoreTokens())
				{
					String token = st.nextToken();
					if(token.equals(Global.toFileName(filename)))
						newList += Global.toFileName(newName) + "\n";
					else
						newList += token + "\n";
				}
				Global.putFileOverwrite(mDBApi, "/" + user + "/lists.txt", newList);
				
				//Returns to List
				new List(user, newName, mDBApi);
				setVisible(false);
				dispose();
			}
		}
	}
		
}
