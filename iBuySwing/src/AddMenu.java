import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.session.WebAuthSession;


public class AddMenu extends JFrame implements ActionListener{
	private DropboxAPI<WebAuthSession> mDBApi;
	private JButton cancel = new JButton("Cancel");
	private JButton add = new JButton("Add");
	private JTextField name = new JTextField("Name");
	private String user;
	
	public AddMenu(String user, DropboxAPI<WebAuthSession> mDBApi) 
	{
		super("iBuy - Add Menu");
		this.user = user;
		this.mDBApi = mDBApi;
		setSize(500,300);
        
        add.addActionListener(this);
        cancel.addActionListener(this);
    
        setLayout(new GridLayout(3,1));
	    add(name);
	    add(add);
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
		if(e.getSource() == add)
  		{
			addList();
  		}
	}
	
	public void addList()
	{
		String listName = name.getText();
		if(listName.equals(""))
			System.out.println("Please Give List a Name");
		else
		{
			String listFile = Global.getFile(mDBApi, "/" + user + "/lists.txt");
			StringTokenizer st = new StringTokenizer(listFile);
            boolean isTaken = false;
            while(st.hasMoreTokens())
            {
            	if(st.nextToken().equals(Global.toFileName(listName)))
            	{
            		isTaken = true;
            		break;
            	}
            	st.nextToken();
            	st.nextToken();
            }
            if(isTaken)
            {
            	System.out.println("Name is taken");
            }
            else
            {
            	//Adds list to users list collection
            	listName = Global.toFileName(listName);
            	Date d = new Date(System.currentTimeMillis());
            	listFile += listName + "\t0.00\t" + Global.toFileName(d.toString()) + "\n";
            	Global.putFileOverwrite(mDBApi, "/" + user + "/lists.txt", listFile);
	        	
	        	//Creates new list
	        	Global.putFile(mDBApi, "/" + user + "/" + listName + ".txt", "");

	        	//Redirects to Main Menu
	        	new MainMenu(user, mDBApi);
				setVisible(false);
	        	dispose();
            }
		}
	}
}
