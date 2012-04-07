import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
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


public class List extends JFrame implements ActionListener{

	private static DropboxAPI<WebAuthSession> mDBApi;
	private static JTextField display = new JTextField();
	private static JButton back = new JButton("Back");
	private static String user, filename;
	
	public List(String user, String filename, DropboxAPI<WebAuthSession> mDBApi) 
	{
		super(filename);
		this.user = user;
		this.filename = filename;
		this.mDBApi = mDBApi;
		
		//Sets layout and session
		setLayout(new GridLayout(2,1));
		setSize(500,300);
		
		//Reads the list filename's contents
		String st = Global.getFile(mDBApi, "/" + user + "/" + Global.toFileName(filename));
		display.setText(st);
		add(display);
		back.addActionListener(this);
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
