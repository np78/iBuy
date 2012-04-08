import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.session.WebAuthSession;


public class OptionsMenu extends JFrame implements ActionListener{
	private DropboxAPI<WebAuthSession> mDBApi;
	private LinkedList<JButton> list = new LinkedList<JButton>();
	private JButton back = new JButton("Back");
	private String user;
	
	public OptionsMenu(String user, DropboxAPI<WebAuthSession> mDBApi) 
	{
		super("Options");
		this.user = user;
		this.mDBApi = mDBApi;
		
		back.addActionListener(this);
		setSize(500, 300);
		add(back);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == back)
		{
			new MainMenu(user, mDBApi);
			setVisible(false);
			dispose();
		}
	}
}
