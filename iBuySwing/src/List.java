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
import javax.swing.JScrollPane;
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
	private JButton add = new JButton("Add an Item");
	private JButton remove = new JButton("Remove Items");
	private JTextField search = new JTextField("Apple");
	private JButton searchStart = new JButton("Search By Name");
	private JButton back = new JButton("Back");
	private JButton nameSort = new JButton("Name");
	private JButton refresh = new JButton("Refresh");
	private JButton categorySort = new JButton("Category");
	private JButton storeSort = new JButton("Store");
	private JButton importanceSort = new JButton("Importance");
	private JButton crossOffSort = new JButton("Cross Off");
	private JTextField total = new JTextField("Enter Total (no $ signs)");
	private JButton setTotal = new JButton("Set Total");
	private String user, filename;
	
	public List(String user, String filename, DropboxAPI<WebAuthSession> mDBApi) 
	{
		super(filename);
		this.user = user;
		this.filename = filename;
		this.mDBApi = mDBApi;
		
		change.addActionListener(this);
		add.addActionListener(this);
		remove.addActionListener(this);
		nameSort.addActionListener(this);
		categorySort.addActionListener(this);
		storeSort.addActionListener(this);
		importanceSort.addActionListener(this);
		crossOffSort.addActionListener(this);
		searchStart.addActionListener(this);
		back.addActionListener(this);
		setTotal.addActionListener(this);
		refresh.addActionListener(this);
		
		StringTokenizer st = new StringTokenizer(Global.getFile(mDBApi, "/" + user + "/" + Global.toFileName(filename) + ".txt"));
		items.clear();
		settings.clear();
		while(st.hasMoreTokens())
		{
			String str[] = new String[5];
			str[0] = Global.readFileName(st.nextToken());
			str[1] = Global.readFileName(st.nextToken());
			str[2] = Global.readFileName(st.nextToken());
			str[3] = Global.readFileName(st.nextToken());
			str[4] = Global.readFileName(st.nextToken());
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
		
		//Sets layout
		setLayout(new GridLayout(7,1));
		setSize(1000, Math.min((200 + 150*items.size()), 1000));
		add(refresh);
		add(change);
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new GridLayout(1, 2));
		searchPanel.add(search);
		searchPanel.add(searchStart);
		add(searchPanel);
		add(add);
		add(remove);
		
		//Reads the list filename's contents
		JPanel jpanel = new JPanel();
		jpanel.setLayout(new GridLayout(items.size() + 1, 6));
		jpanel.add(crossOffSort);
		jpanel.add(nameSort);
		jpanel.add(categorySort);
		jpanel.add(storeSort);
		jpanel.add(importanceSort);
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
		add(back);
		
		setContentPane(new JScrollPane(getContentPane()));
		setVisible(true);
	}
	
	public void nameSort()
	{
		Item temp;
		JButton temp2;
		for(int i = 0; i < items.size(); i++)
		{
			for(int j = 1; j < items.size()-i; j++)
			{
				if(items.get(j-1).name.compareTo(items.get(j).name) > 0)
				{
					temp = items.get(j-1);
					items.set(j-1, items.get(j));
					items.set(j, temp);
					
					temp2 = settings.get(j-1);
					settings.set(j-1, settings.get(j));
					settings.set(j, temp2);
				}
			}
		}
	}
	
	public void writeListToDropbox()
	{
		String newList = "";
		for(int i = 0; i < items.size(); i++)
		{
			newList += Global.toFileName(items.get(i).name) + "\t";
			newList += Global.toFileName(items.get(i).category) + "\t";
			newList += Global.toFileName(items.get(i).store) + "\t";
			newList += items.get(i).importance + "\t";
			newList += items.get(i).isChecked + "\n";
		}
		Global.putFileOverwrite(mDBApi, "/" + user + "/" + Global.toFileName(filename) + ".txt", newList);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == back)
  		{
  			new MainMenu(user, mDBApi);
  			setVisible(false);
  			dispose();
  		}
		if(e.getSource() == refresh)
  		{
  			new List(user, filename, mDBApi);
  			setVisible(false);
  			dispose();
  		}
		if(e.getSource() == setTotal)
		{
			if(!total.getText().equals(""))
			{
				String amount = total.getText();
				if(amount.contains("."))
				{
					if(amount.charAt(amount.length()-1) == 46)
						amount += "00";
					else if(amount.charAt(amount.length()-2) == 46)
						amount += "0";
					else if(amount.charAt(amount.length()-3) != 46)
						amount = amount.substring(0, amount.indexOf('.') + 2);
				}
				else
					amount += ".00";
			}
		}
		if(e.getSource() == crossOffSort)
		{
			nameSort();
			LinkedList<Item> temp = new LinkedList<Item>();
  			for(int i = 0; i < items.size(); i++)
  			{
  				if(!items.get(i).isChecked)
				{
  					temp.add(items.get(i));
				}
  			}
  			for(int j = 0; j < items.size(); j++)
			{
  				if(items.get(j).isChecked)
				{
  					temp.add(items.get(j));
				}
			}
  			items = temp;
  			writeListToDropbox();
			new List(user, filename, mDBApi);
  			setVisible(false);
  			dispose();
		}
		if(e.getSource() == importanceSort)
  		{
			Item temp;
			JButton temp2;
  			for(int i = 0; i < items.size(); i++)
  			{
  				for(int j = 1; j < items.size()-i; j++)
  				{
  					if(items.get(j-1).importance > items.get(j).importance)
  					{
  						temp = items.get(j-1);
  						items.set(j-1, items.get(j));
  						items.set(j, temp);
  						
  						temp2 = settings.get(j-1);
  						settings.set(j-1, settings.get(j));
  						settings.set(j, temp2);
  					}
  				}
  			}
  			writeListToDropbox();
			new List(user, filename, mDBApi);
  			setVisible(false);
  			dispose();
  		}
		if(e.getSource() == nameSort)
  		{
			nameSort();
			writeListToDropbox();
			new List(user, filename, mDBApi);
  			setVisible(false);
  			dispose();
  		}
		if(e.getSource() == storeSort)
  		{
			Item temp;
			JButton temp2;
  			for(int i = 0; i < items.size(); i++)
  			{
  				for(int j = 1; j < items.size()-i; j++)
  				{
  					if(items.get(j-1).store.compareTo(items.get(j).store) > 0)
  					{
  						temp = items.get(j-1);
  						items.set(j-1, items.get(j));
  						items.set(j, temp);
  						
  						temp2 = settings.get(j-1);
  						settings.set(j-1, settings.get(j));
  						settings.set(j, temp2);
  					}
  				}
  			}
  			writeListToDropbox();
			new List(user, filename, mDBApi);
  			setVisible(false);
  			dispose();
  		}
		if(e.getSource() == categorySort)
  		{
			Item temp;
			JButton temp2;
  			for(int i = 0; i < items.size(); i++)
  			{
  				for(int j = 1; j < items.size()-i; j++)
  				{
  					if(items.get(j-1).category.compareTo(items.get(j).category) > 0)
  					{
  						temp = items.get(j-1);
  						items.set(j-1, items.get(j));
  						items.set(j, temp);
  						
  						temp2 = settings.get(j-1);
  						settings.set(j-1, settings.get(j));
  						settings.set(j, temp2);
  					}
  				}
  			}
  			writeListToDropbox();
			new List(user, filename, mDBApi);
  			setVisible(false);
  			dispose();
  		}
		if(e.getSource() == change)
  		{
  			new ChangeMenu(user, filename, mDBApi);
  			setVisible(false);
  			dispose();
  		}
		if(e.getSource() == add)
  		{
  			new AddItem(user, filename, mDBApi);
  			setVisible(false);
  			dispose();
  		}
		if(e.getSource() == remove)
  		{
  			new RemoveItem(user, filename, mDBApi);
  			setVisible(false);
  			dispose();
  		}
		if(e.getSource() == searchStart && (!search.getText().equals("")))
  		{
			boolean isFound = false;
			for(int i = 0; i < items.size(); i++)
			{
				if(items.get(i).name.compareTo(search.getText()) == 0)
				{
					items.addFirst(items.remove(i));
					isFound = true;
					break;
				}
			}
			if(isFound)
			{
				System.out.println("Item Found");
				writeListToDropbox();
				new List(user, filename, mDBApi);
	  			setVisible(false);
	  			dispose();
			}
			else
				System.out.println("Item NOT Found");
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
