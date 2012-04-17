package ibuyswing;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.session.WebAuthSession;


public class RemoveItem extends JFrame implements ActionListener{
	private DropboxAPI<WebAuthSession> mDBApi;
	private LinkedList<JCheckBox> checkboxes = new LinkedList<JCheckBox>();
	private JButton cancel = new JButton("Cancel");
	private JButton update = new JButton("Update");
	private String user, filename;
	
	public RemoveItem(String user, String filename, DropboxAPI<WebAuthSession> mDBApi) 
	{
		super("iBuy - Remove Items");
		this.user = user;
		this.filename = filename;
		this.mDBApi = mDBApi;
		setSize(500,300);
        
        update.addActionListener(this);
        cancel.addActionListener(this);
        
		StringTokenizer st = new StringTokenizer(Global.getFile(mDBApi, "/" + user + "/" + Global.toFileName(filename) + ".txt"));
		checkboxes.clear();
		while(st.hasMoreTokens())
		{
			String name = st.nextToken();
			st.nextToken();
			st.nextToken();
			st.nextToken();
			st.nextToken();
			JCheckBox check = new JCheckBox();
			check.setName(name);
			checkboxes.add(check);
		}
		setLayout(new GridLayout(checkboxes.size() + 2, 2));
		add(new JTextField("Select to Remove"));
		add(new JTextField("Item Name"));
		for(int i = 0; i < checkboxes.size(); i++)
		{
			add(checkboxes.get(i));
			add(new JTextField(checkboxes.get(i).getName()));
		}
	    add(cancel);
	    add(update);
		
	    setContentPane(new JScrollPane(getContentPane()));
        setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cancel)
  		{
			new addItemFrame(user, filename, mDBApi);
  			setVisible(false);
  			dispose();
  		}
		if(e.getSource() == update)
  		{
			StringTokenizer list = new StringTokenizer(Global.getFile(mDBApi, "/" + user + "/" + Global.toFileName(filename) + ".txt"));
			String newList = "";
			for(int i = 0; i < checkboxes.size(); i++)
			{
				String name = list.nextToken();
				String category = list.nextToken();
				String store = list.nextToken();
				String importance = list.nextToken();
				String isChecked = list.nextToken();
				if(!checkboxes.get(i).isSelected())
				{
					newList += name + "\t" + category + "\t" + store + "\t" + importance + "\t" + isChecked + "\n";
				}
			}
			Global.putFileOverwrite(mDBApi, "/" + user + "/" + Global.toFileName(filename) + ".txt", newList);
			
			//Update list date
			StringTokenizer st = new StringTokenizer(Global.getFile(mDBApi, "/" + user + "/lists.txt"));
			String newList2 = "";
			while(st.hasMoreTokens())
			{
				String token = st.nextToken();
				String token2 = st.nextToken();
				String token3 = st.nextToken();
				if(token.equals(Global.toFileName(filename)))
				{
					Date d = new Date(System.currentTimeMillis());
					newList2 += token + "\t" + token2 + "\t" + Global.toFileName(d.toString())  + "\n";
				}
				else
					newList2 += token + "\t" + token2 + "\t" + token3 + "\n";
			}
			Global.putFileOverwrite(mDBApi, "/" + user + "/lists.txt", newList2);
			
			new addItemFrame(user, filename, mDBApi);
  			setVisible(false);
  			dispose();
  		}
	}
}
