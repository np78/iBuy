package ibuyswing;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.session.WebAuthSession;


public class ChangeItem extends JFrame implements ActionListener{
	private DropboxAPI<WebAuthSession> mDBApi;
	private JButton cancel = new JButton("Cancel");
	private JButton update = new JButton("Update");
	private String user, filename, originalName;
	private Item item;
	
	public ChangeItem(String user, String filename, Item item, DropboxAPI<WebAuthSession> mDBApi) 
	{
		super("iBuy - Add Item");
		this.user = user;
		this.filename = filename;
		this.item = item;
		this.mDBApi = mDBApi;
		setSize(500,300);
        
        update.addActionListener(this);
        cancel.addActionListener(this);
        
        setLayout(new GridLayout(6,2));
	    add(new JTextField("Name"));
	    add(item.nameField);
	    originalName = item.name;
	    add(new JTextField("Category"));
	    add(item.categoryField);
	    add(new JTextField("Store"));
	    add(item.storeField);
	    add(new JTextField("Importance (1=low and 9=high)"));
	    add(item.importanceField);
	    add(new JTextField("Cross Off"));
	    add(item.checkbox);
	    add(cancel);
	    add(update);
		
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
			String name = Global.toFileName(item.nameField.getText());
			String category = Global.toFileName(item.categoryField.getText());
			String store = Global.toFileName(item.storeField.getText());
			String importance = item.importanceField.getText();
			boolean isChecked = item.checkbox.isSelected();
  			if(name.equals("") || category.equals("") || store.equals("") || importance.equals(""))
  				System.out.println("Please complete all fields");
  			else
  			{
  				String list = Global.getFile(mDBApi, "/" + user + "/" + Global.toFileName(filename) + ".txt");
  				String checkNameList = list;
  				StringTokenizer st2 = new StringTokenizer(checkNameList);
  				int count = 0;
  				while(st2.hasMoreTokens())
  				{
  					String nameToken = st2.nextToken();
  					st2.nextToken();
  					st2.nextToken();
  					st2.nextToken();
  					st2.nextToken();
  					if(nameToken.equals(name))
  						count++;
  				}
  				if(count > 1)
  					System.out.println("New name is taken");
  				else
  				{
	  				String newList = "";
	  				StringTokenizer st = new StringTokenizer(list);	
	  				while(st.hasMoreTokens())
	  				{
	  					String nameToken = st.nextToken();
	  					String categoryToken = st.nextToken();
	  					String storeToken = st.nextToken();
	  					String importanceToken = st.nextToken();
	  					String checkToken = st.nextToken();
	  					if(nameToken.equals(originalName))
	  					{
	  						newList += name + "\t";
	  						newList += category + "\t";
	  						newList += store + "\t";
	  						newList += importance + "\t";
	  						newList += isChecked + "\n";
	  					}
	  					else
	  					{
	  						newList += nameToken + "\t";
	  						newList += categoryToken + "\t";
	  						newList += storeToken + "\t";
	  						newList += importanceToken + "\t";
	  						newList += checkToken + "\n";
	  					}
	  				}
	  				Global.putFileOverwrite(mDBApi, "/" + user + "/" + Global.toFileName(filename) + ".txt", newList);
	  				
	  				new addItemFrame(user, filename, mDBApi);
	  	  			setVisible(false);
	  	  			dispose();
  				}
  			}
  		}
	}
}