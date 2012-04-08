import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.session.WebAuthSession;


public class ChangeItem extends JFrame implements ActionListener{
	
	private DropboxAPI<WebAuthSession> mDBApi;
	private Item item;
	private String user, filename;
	private JButton cancel = new JButton("Cancel");
	
	public ChangeItem(String user, String filename, Item item, DropboxAPI<WebAuthSession> mDBApi)
	{
		super("Change Item Menu for " + item.name);
		this.user = user;
		this.filename = filename;
		this.item = item;
		this.mDBApi = mDBApi;
		
		setLayout(new GridLayout(1,1));
		setSize(500,300);
		cancel.addActionListener(this);
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
	}
}
