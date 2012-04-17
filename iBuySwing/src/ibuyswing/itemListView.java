/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * itemListView.java
 *
 * Created on Apr 16, 2012, 1:55:23 PM
 */
package ibuyswing;

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
/**
 *
 * @author Luke
 */
public class itemListView extends javax.swing.JFrame implements ActionListener{

        private DropboxAPI<WebAuthSession> mDBApi;
	private LinkedList<Item> items = new LinkedList<Item>();
	private LinkedList<JButton> settings = new LinkedList<JButton>();
	private String user, filename;
        
    /** Creates new form itemListView */
    public itemListView(String user, String filename, DropboxAPI<WebAuthSession> mDBApi) {
        super(filename);
		this.user = user;
		this.filename = filename;
		this.mDBApi = mDBApi;
                
                /**StringTokenizer st = new StringTokenizer(Global.getFile(mDBApi, "/" + user + "/" + Global.toFileName(filename) + ".txt"));
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
                        
                        for(int i = 0; i < items.size(); i++)
                        {
                            items.get(i);
                            itemScrollPane.add(item.checkbox);
                            itemScrollPane.add(item.nameField);
                            itemScrollPane.add(item.categoryField);
                            itemScrollPane.add(item.storeField);
                            itemScrollPane.add(item.importanceField);
                            itemScrollPane.add(settings.get(i));
                        }
		}**/
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        listViewPanel2 = new javax.swing.JPanel();
        refreshButton = new javax.swing.JButton();
        changeListNameButton = new javax.swing.JButton();
        searchByNameButton = new javax.swing.JButton();
        addItemButton = new javax.swing.JButton();
        removeItemButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        itemScrollPane = new javax.swing.JScrollPane();
        searchTextField = new javax.swing.JTextField();
        crossOffSortButton = new javax.swing.JButton();
        nameSortButton = new javax.swing.JButton();
        categorySortButton = new javax.swing.JButton();
        storeSortButton = new javax.swing.JButton();
        importanceSortButton = new javax.swing.JButton();
        logOutButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        listViewPanel2.setBackground(new java.awt.Color(51, 51, 51));
        listViewPanel2.setName("listViewPanel2"); // NOI18N

        refreshButton.setBackground(new java.awt.Color(102, 102, 102));
        refreshButton.setFont(new java.awt.Font("Tahoma", 1, 18));
        refreshButton.setText("Refresh");
        refreshButton.setName("refreshButton"); // NOI18N
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        changeListNameButton.setText("change List name");
        changeListNameButton.setName("changeListNameButton"); // NOI18N
        changeListNameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeListNameButtonActionPerformed(evt);
            }
        });

        searchByNameButton.setText("search By name");
        searchByNameButton.setName("searchByNameButton"); // NOI18N
        searchByNameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchByNameButtonActionPerformed(evt);
            }
        });

        addItemButton.setText("Add Item");
        addItemButton.setName("addItemButton"); // NOI18N
        addItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addItemButtonActionPerformed(evt);
            }
        });

        removeItemButton.setText("Remove Item");
        removeItemButton.setName("removeItemButton"); // NOI18N
        removeItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeItemButtonActionPerformed(evt);
            }
        });

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(itemListView.class, this);
        backButton.setAction(actionMap.get("LoadMainMenuPanel")); // NOI18N
        backButton.setBackground(new java.awt.Color(102, 102, 102));
        backButton.setFont(new java.awt.Font("Tahoma", 1, 18));
        backButton.setText("Back");
        backButton.setName("backButton"); // NOI18N
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        itemScrollPane.setName("itemScrollPane"); // NOI18N

        searchTextField.setName("searchTextField"); // NOI18N

        crossOffSortButton.setText("Cross off");
        crossOffSortButton.setName("crossOffSortButton"); // NOI18N
        crossOffSortButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crossOffSortButtonActionPerformed(evt);
            }
        });

        nameSortButton.setText("Name");
        nameSortButton.setName("nameSortButton"); // NOI18N
        nameSortButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameSortButtonActionPerformed(evt);
            }
        });

        categorySortButton.setText("Category");
        categorySortButton.setName("categorySortButton"); // NOI18N
        categorySortButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categorySortButtonActionPerformed(evt);
            }
        });

        storeSortButton.setText("Store");
        storeSortButton.setName("storeSortButton"); // NOI18N
        storeSortButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                storeSortButtonActionPerformed(evt);
            }
        });

        importanceSortButton.setText("Importance");
        importanceSortButton.setName("importanceSortButton"); // NOI18N
        importanceSortButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importanceSortButtonActionPerformed(evt);
            }
        });

        logOutButton2.setBackground(new java.awt.Color(102, 102, 102));
        logOutButton2.setFont(new java.awt.Font("Tahoma", 1, 18));
        logOutButton2.setText("log out");
        logOutButton2.setName("logOutButton2"); // NOI18N
        logOutButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutButton2ActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(51, 51, 51));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36));
        jLabel1.setForeground(new java.awt.Color(255, 153, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout listViewPanel2Layout = new javax.swing.GroupLayout(listViewPanel2);
        listViewPanel2.setLayout(listViewPanel2Layout);
        listViewPanel2Layout.setHorizontalGroup(
            listViewPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(listViewPanel2Layout.createSequentialGroup()
                .addGroup(listViewPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(listViewPanel2Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(searchByNameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(listViewPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(listViewPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(listViewPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, listViewPanel2Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, listViewPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(listViewPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, listViewPanel2Layout.createSequentialGroup()
                                        .addComponent(removeItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(76, 76, 76)
                                        .addComponent(addItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(itemScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, listViewPanel2Layout.createSequentialGroup()
                                        .addComponent(crossOffSortButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nameSortButton, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(categorySortButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(storeSortButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(importanceSortButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, listViewPanel2Layout.createSequentialGroup()
                                        .addGap(197, 197, 197)
                                        .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                                        .addComponent(logOutButton2)))))
                        .addGap(26, 26, 26)))
                .addGap(14, 14, 14))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, listViewPanel2Layout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(changeListNameButton, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                .addGap(110, 110, 110))
        );
        listViewPanel2Layout.setVerticalGroup(
            listViewPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, listViewPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(listViewPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logOutButton2)
                    .addComponent(refreshButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(listViewPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(importanceSortButton)
                    .addComponent(storeSortButton)
                    .addComponent(categorySortButton)
                    .addComponent(nameSortButton)
                    .addComponent(crossOffSortButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(itemScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(listViewPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addItemButton, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(removeItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(listViewPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchByNameButton)
                    .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(changeListNameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 562, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(listViewPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 429, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(listViewPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logOutButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutButton2ActionPerformed

        if (evt.getSource() == logOutButton2) {             System.exit(0);         }     }//GEN-LAST:event_logOutButton2ActionPerformed

    private void changeListNameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeListNameButtonActionPerformed
        if (evt.getSource() == changeListNameButton) {            
            new ChangeMenu(user, filename, mDBApi);             
            setVisible(false);             
            dispose();         }    
    }//GEN-LAST:event_changeListNameButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        if(evt.getSource() == backButton)
  		{
  			new mainMenuFrame(user, mDBApi);
  			setVisible(false);
  			dispose();
  		}
    }//GEN-LAST:event_backButtonActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        if(evt.getSource() == refreshButton)
  		{
  			new itemListView(user, filename, mDBApi);
  			setVisible(false);
  			dispose();
  		}
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void crossOffSortButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crossOffSortButtonActionPerformed
        if(evt.getSource() == crossOffSortButton)
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
			new itemListView(user, filename, mDBApi);
  			setVisible(false);
  			dispose();
		}
    }//GEN-LAST:event_crossOffSortButtonActionPerformed

    private void importanceSortButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importanceSortButtonActionPerformed
        if(evt.getSource() == importanceSortButton)
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
			new itemListView(user, filename, mDBApi);
  			setVisible(false);
  			dispose();
  		}
    }//GEN-LAST:event_importanceSortButtonActionPerformed

    private void nameSortButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameSortButtonActionPerformed
        if(evt.getSource() == nameSortButton)
  		{
			nameSort();
			writeListToDropbox();
			new itemListView(user, filename, mDBApi);
  			setVisible(false);
  			dispose();
  		}
    }//GEN-LAST:event_nameSortButtonActionPerformed

    private void storeSortButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_storeSortButtonActionPerformed
        if(evt.getSource() == storeSortButton)
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
			new itemListView(user, filename, mDBApi);
  			setVisible(false);
  			dispose();
  		}
    }//GEN-LAST:event_storeSortButtonActionPerformed

    private void categorySortButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categorySortButtonActionPerformed
        if(evt.getSource() == categorySortButton)
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
			new itemListView(user, filename, mDBApi);
  			setVisible(false);
  			dispose();
  		}
    }//GEN-LAST:event_categorySortButtonActionPerformed

    private void addItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemButtonActionPerformed
        if(evt.getSource() == addItemButton)
  		{
  			new addListFrame(user, mDBApi);
  			setVisible(false);
  			dispose();
  		}
    }//GEN-LAST:event_addItemButtonActionPerformed

    private void removeItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeItemButtonActionPerformed
        if(evt.getSource() == removeItemButton)
  		{
  			new RemoveItem(user, filename, mDBApi);
  			setVisible(false);
  			dispose();
  		}
    }//GEN-LAST:event_removeItemButtonActionPerformed

    private void searchByNameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchByNameButtonActionPerformed
        if(evt.getSource() == searchByNameButton && (!searchTextField.getText().equals("")))
  		{
			boolean isFound = false;
			for(int i = 0; i < items.size(); i++)
			{
				if(items.get(i).name.compareTo(searchTextField.getText()) == 0)
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
				new itemListView(user, filename, mDBApi);
	  			setVisible(false);
	  			dispose();
			}
			else
				System.out.println("Item NOT Found");
  		}
		for(int i = 0; i < settings.size(); i++)
		{
			if(evt.getSource() == settings.get(i))
			{
				new ChangeItem(user, filename, items.get(i), mDBApi);
				setVisible(false);
				dispose();
			}
		}
	
    }//GEN-LAST:event_searchByNameButtonActionPerformed

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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addItemButton;
    private javax.swing.JButton backButton;
    private javax.swing.JButton categorySortButton;
    private javax.swing.JButton changeListNameButton;
    private javax.swing.JButton crossOffSortButton;
    private javax.swing.JButton importanceSortButton;
    private javax.swing.JScrollPane itemScrollPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel listViewPanel2;
    private javax.swing.JButton logOutButton2;
    private javax.swing.JButton nameSortButton;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton removeItemButton;
    private javax.swing.JButton searchByNameButton;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JButton storeSortButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}