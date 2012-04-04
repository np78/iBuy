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
	
	public List(String user, String filename)
	{
		super(filename);
		this.user = user;
		this.filename = filename;
		setLayout(new GridLayout(2,1));
		setSize(500,300);
		createSession();
		
		//Reads the list filename's contents
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
			DropboxFileInfo info = mDBApi.getFile("/" + user + "/" + toFileName(filename), null, outputStream, null);
		} catch (DropboxException e) {}
		String st = new String(outputStream.toByteArray());
		display.setText(st);
		add(display);
		back.addActionListener(this);
		add(back);
		setVisible(true);
	}
	
	//Replaces spaces of filename with underscores
	public String toFileName(String st)
	{
		String re = "";
		for(int i = 0; i < st.length(); i++)
		{
			if(st.charAt(i) == ' ')
				re += "_";
			else
				re += st.charAt(i);
		}
		return re+".txt";
	}
	
	public static void createSession()
	{
		//Create Session
		AppKeyPair appKeyPair = new AppKeyPair(Home.APIKey, Home.APISecret);
        WebAuthSession session = new WebAuthSession(appKeyPair, Home.ACCESS_TYPE);
        mDBApi = new DropboxAPI<WebAuthSession>(session);
        mDBApi.getSession().setAccessTokenPair(new AccessTokenPair(Home.sessionKey, Home.sessionSecret));
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == back)
  		{
  			ListMenu listMenu = new ListMenu(user);
  			setVisible(false);
  			dispose();
  		}
	}
}
