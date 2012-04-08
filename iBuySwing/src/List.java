import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.DropboxAPI.DropboxFileInfo;
import com.dropbox.client2.exception.DropboxException;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.WebAuthSession;


public class List extends JFrame implements ActionListener{

	private DropboxAPI<WebAuthSession> mDBApi;
	private JButton change = new JButton("Change List Name");
	private LinkedList<Item> items = new LinkedList<Item>();
	private LinkedList<JButton> settings = new LinkedList<JButton>();
	private JTextField display = new JTextField();
	private JButton back = new JButton("Back");
	private String user, filename;
	
	public List(String user, String filename, DropboxAPI<WebAuthSession> mDBApi) 
	{
		super(filename);
		this.user = user;
		this.filename = filename;
		this.mDBApi = mDBApi;
		
		//Sets layout and session
		setLayout(new GridLayout(3,1));
		setSize(500,300);
		change.addActionListener(this);
		add(change);
		
		//Reads the list filename's contents
		JPanel jpanel = new JPanel();
		StringTokenizer st = new StringTokenizer(Global.getFile(mDBApi, "/" + user + "/" + Global.toFileName(filename) + ".txt"));
		items.clear();
		settings.clear();
		while(st.hasMoreTokens())
		{
			String str[] = new String[5];
			str[0] = st.nextToken();
			str[1] = st.nextToken();
			str[2] = st.nextToken();
			str[3] = st.nextToken();
			str[4] = st.nextToken();
			int importance = str[3].charAt(0) - 48;
			boolean isChecked = false;
			if(str[4].equals("true"))
				isChecked = true;
			Item item = new Item(str[0], str[1], str[2], importance, isChecked);
			items.add(item);
			JButton set = new JButton("Settings");
			set.addActionListener(this);
			settings.add(set);
		}
		jpanel.setLayout(new GridLayout(items.size() + 1, 6));
		jpanel.add(new JTextField("Cross Off"));
		jpanel.add(new JTextField("Name"));
		jpanel.add(new JTextField("Category"));
		jpanel.add(new JTextField("Store"));
		jpanel.add(new JTextField("Importance"));
		jpanel.add(new JTextField());
		for(int i = 0; i < items.size(); i++)
		{
			Item item = items.get(i);
			jpanel.add(item.checkbox);
			jpanel.add(item.nameField);
			jpanel.add(item.categoryField);
			jpanel.add(item.storeField);
			jpanel.add(item.importanceField);
			jpanel.add(settings.get(i));
		}
		add(jpanel);
		//display.setText(st);
		//add(display);
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
		if(e.getSource() == change)
  		{
  			new ChangeMenu(user, filename, mDBApi);
  			setVisible(false);
  			dispose();
  		}
		for(int i = 0; i < settings.size(); i++)
		{
			if(e.getSource() == settings.get(i))
			{
				new ChangeItem(user, filename, items.get(i), mDBApi);
				setVisible(false);
				dispose();
			}
		}
	}
}
