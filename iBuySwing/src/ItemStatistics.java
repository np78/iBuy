import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.session.WebAuthSession;


public class ItemStatistics extends JFrame implements ActionListener{
	
	private LinkedList<Item> items = new LinkedList<Item>();
	private LinkedList<JTextField> names = new LinkedList<JTextField>();
	private JTextField search = new JTextField("Apple");
	private JButton searchStart = new JButton("Search By Name");
	private JButton nameSort = new JButton("Name");
	private JButton categorySort = new JButton("Category");
	private JButton storeSort = new JButton("Store");
	private JButton importanceSort = new JButton("Importance");
	private JButton crossOffSort = new JButton("Cross Off");
	private JButton listSort = new JButton("List");
	
	public ItemStatistics(String user, DropboxAPI<WebAuthSession> mDBApi)
	{
		super("iBuy - ItemStatistics");
		nameSort.addActionListener(this);
		categorySort.addActionListener(this);
		storeSort.addActionListener(this);
		importanceSort.addActionListener(this);
		crossOffSort.addActionListener(this);
		searchStart.addActionListener(this);
		listSort.addActionListener(this);
		
		StringTokenizer st = new StringTokenizer(Global.getFile(mDBApi, "/" + user + "/lists.txt"));
		items.clear();
		names.clear();
		while(st.hasMoreTokens())
		{
			String filename = st.nextToken();
			st.nextToken();
			st.nextToken();
			StringTokenizer list = new StringTokenizer(Global.getFile(mDBApi, "/" + user + "/" + filename + ".txt"));
			while(list.hasMoreTokens())
			{
				String str[] = new String[5];
				str[0] = Global.readFileName(list.nextToken());
				str[1] = Global.readFileName(list.nextToken());
				str[2] = Global.readFileName(list.nextToken());
				str[3] = Global.readFileName(list.nextToken());
				str[4] = Global.readFileName(list.nextToken());
				int importance = str[3].charAt(0) - 48;
				boolean isChecked = false;
				if(str[4].equals("true"))
					isChecked = true;
				Item item = new Item(str[0], str[1], str[2], importance, isChecked);
				items.add(item);
				names.add(new JTextField(Global.readFileName(filename)));
			}
		}
		
		//Sets layout
		setLayout(new GridLayout(2,1));
		setSize(1000, Math.min((200 + 150*items.size()), 1000));
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new GridLayout(1, 2));
		searchPanel.add(search);
		searchPanel.add(searchStart);
		add(searchPanel);
		
		//Reads the list filename's contents
		JPanel jpanel = new JPanel();
		jpanel.setLayout(new GridLayout(items.size()+1, 6));
		jpanel.add(crossOffSort);
		jpanel.add(nameSort);
		jpanel.add(categorySort);
		jpanel.add(storeSort);
		jpanel.add(importanceSort);
		jpanel.add(listSort);
		for(int i = 0; i < items.size(); i++)
		{
			Item item = items.get(i);
			jpanel.add(item.checkbox);
			jpanel.add(item.nameField);
			jpanel.add(item.categoryField);
			jpanel.add(item.storeField);
			jpanel.add(item.importanceField);
			jpanel.add(names.get(i));
		}
		add(jpanel);
		
		setContentPane(new JScrollPane(getContentPane()));
		setVisible(true);
	}

	public void nameSort()
	{
		for(int i = 0; i < items.size(); i++)
		{
			for(int j = 1; j < items.size()-i; j++)
			{
				if(items.get(j-1).name.compareTo(items.get(j).name) > 0)
				{
					Item temp = new Item(items.get(j-1).name, items.get(j-1).category, items.get(j-1).store, items.get(j-1).importance, items.get(j-1).isChecked);
					items.get(j-1).setItem(items.get(j));
					items.get(j).setItem(temp);
					
					String temp2 = names.get(j-1).getText();
					names.get(j-1).setText(names.get(j).getText());
					names.get(j).setText(temp2);
				}
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == listSort)
		{
			for(int i = 0; i < items.size(); i++)
  			{
  				for(int j = 1; j < items.size()-i; j++)
  				{
  					if(names.get(j-1).getText().compareTo(names.get(j).getText()) > 0)
  					{
  						Item temp = new Item(items.get(j-1).name, items.get(j-1).category, items.get(j-1).store, items.get(j-1).importance, items.get(j-1).isChecked);
  						items.get(j-1).setItem(items.get(j));
  						items.get(j).setItem(temp);
  						
  						String temp2 = names.get(j-1).getText();
  						names.get(j-1).setText(names.get(j).getText());
  						names.get(j).setText(temp2);
  					}
  				}
  			}
  			repaint();
		}
		if(e.getSource() == crossOffSort)
		{
			nameSort();
			LinkedList<Item> temp = new LinkedList<Item>();
			LinkedList<JTextField> temp2 = new LinkedList<JTextField>();
  			for(int i = 0; i < items.size(); i++)
  			{
  				if(items.get(i).isChecked)
				{
  					Item old = items.get(i);
  					Item tempItem = new Item(old.name, old.category, old.store, old.importance, old.isChecked);
  					temp.add(tempItem);
  					temp2.add(new JTextField(names.get(i).getText()));
				}
  			}
  			
  			for(int j = 0; j < items.size(); j++)
  			{
  				if(!items.get(j).isChecked)
				{
	  				Item old = items.get(j);
					Item tempItem = new Item(old.name, old.category, old.store, old.importance, old.isChecked);
	  				temp.add(tempItem);
	  				temp2.add(new JTextField(names.get(j).getText()));
				}
  			}
			
  			for(int k = 0; k < items.size(); k++)
  			{
  				items.get(k).setItem(temp.get(k));
  				names.get(k).setText(temp2.get(k).getText());
  			}
  			repaint();
		}
		if(e.getSource() == importanceSort)
  		{
  			for(int i = 0; i < items.size(); i++)
  			{
  				for(int j = 1; j < items.size()-i; j++)
  				{
  					if(items.get(j-1).importance > items.get(j).importance)
  					{
  						Item temp = new Item(items.get(j-1).name, items.get(j-1).category, items.get(j-1).store, items.get(j-1).importance, items.get(j-1).isChecked);
  						items.get(j-1).setItem(items.get(j));
  						items.get(j).setItem(temp);
  						
  						String temp2 = names.get(j-1).getText();
  						names.get(j-1).setText(names.get(j).getText());
  						names.get(j).setText(temp2);
  					}
  				}
  			}
  			repaint();
  		}
		if(e.getSource() == nameSort)
  		{
			nameSort();
  			repaint();
  		}
		if(e.getSource() == storeSort)
  		{
  			for(int i = 0; i < items.size(); i++)
  			{
  				for(int j = 1; j < items.size()-i; j++)
  				{
  					if(items.get(j-1).store.compareTo(items.get(j).store) > 0)
  					{
  						Item temp = new Item(items.get(j-1).name, items.get(j-1).category, items.get(j-1).store, items.get(j-1).importance, items.get(j-1).isChecked);
  						items.get(j-1).setItem(items.get(j));
  						items.get(j).setItem(temp);
  						
  						String temp2 = names.get(j-1).getText();
  						names.get(j-1).setText(names.get(j).getText());
  						names.get(j).setText(temp2);
  					}
  				}
  			}
  			repaint();
  		}
		if(e.getSource() == categorySort)
  		{
  			for(int i = 0; i < items.size(); i++)
  			{
  				for(int j = 1; j < items.size()-i; j++)
  				{
  					if(items.get(j-1).category.compareTo(items.get(j).category) > 0)
  					{
  						Item temp = new Item(items.get(j-1).name, items.get(j-1).category, items.get(j-1).store, items.get(j-1).importance, items.get(j-1).isChecked);
  						items.get(j-1).setItem(items.get(j));
  						items.get(j).setItem(temp);
  						
  						String temp2 = names.get(j-1).getText();
  						names.get(j-1).setText(names.get(j).getText());
  						names.get(j).setText(temp2);
  					}
  				}
  			}
  			repaint();
  		}
		if(e.getSource() == searchStart && (!search.getText().equals("")))
  		{
			boolean isFound = false;
			for(int i = 0; i < items.size(); i++)
			{
				if(items.get(i).name.compareTo(search.getText()) == 0)
				{
					LinkedList<Item> temp = new LinkedList<Item>();
					LinkedList<JTextField> temp2 = new LinkedList<JTextField>();
					
  					Item old = items.get(i);
  					Item tempItem = new Item(old.name, old.category, old.store, old.importance, old.isChecked);
  					temp.add(tempItem);
  					temp2.add(new JTextField(names.get(i).getText()));

		  			
		  			for(int j = 0; j < items.size(); j++)
		  			{
		  				if(j != i)
						{
			  				old = items.get(j);
							tempItem = new Item(old.name, old.category, old.store, old.importance, old.isChecked);
			  				temp.add(tempItem);
			  				temp2.add(new JTextField(names.get(j).getText()));
						}
		  			}
					for(int k = 0; k < items.size(); k++)
		  			{
		  				items.get(k).setItem(temp.get(k));
		  				names.get(k).setText(temp2.get(k).getText());
		  			}
					isFound = true;
					break;
				}
			}
			if(isFound)
			{
				System.out.println("Item Found");
				repaint();
			}
			else
				System.out.println("Item NOT Found");
  		}
	}
}
