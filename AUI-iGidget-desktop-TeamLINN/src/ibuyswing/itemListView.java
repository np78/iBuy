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
import javax.swing.JOptionPane;
/**
 *
 * @author Luke
 */
public class itemListView extends javax.swing.JFrame implements ActionListener{

        private DropboxAPI<WebAuthSession> mDBApi;
	private LinkedList<Item> items = new LinkedList<Item>();
	private LinkedList<JButton> edit = new LinkedList<JButton>();
        //private LinkedList<JButton> delete = new LinkedList<JButton>();
	private String user, filename;
        
    /** Creates new form itemListView */
    public itemListView(String user, String filename, DropboxAPI<WebAuthSession> mDBApi) {
        
                super(filename);
		this.user = user;
		this.filename = filename;
		this.mDBApi = mDBApi;
                
                 
                 initComponents();
                 getItemList();
                 setVisible(true);         
    }
        public void getItemList(){
           
            listIdentifierLabel.setText("Current List: " + filename);
            StringTokenizer st = new StringTokenizer(Global.getFile(mDBApi, "/" + user + "/" + Global.toFileName(filename) + ".txt"));
		items.clear();
		edit.clear();
                //delete.clear();
		while(st.hasMoreTokens())
		{
			String str[] = new String[5];
			str[0] = Global.readFileName(st.nextToken());
			str[1] = Global.readFileName(st.nextToken());
			str[2] = Global.readFileName(st.nextToken());
			str[3] = Global.readFileName(st.nextToken());
			str[4] = Global.readFileName(st.nextToken());
                        int importance = 0;
                        if(importance == 0)
                            importance = str[3].charAt(0) - 48;  
			boolean isChecked = false;
			if(str[4].equals("true"))
				isChecked = true;
			Item item = new Item(str[0], str[1], str[2], importance, isChecked);
			items.add(item);
			JButton editButton = new JButton(" Edit ");
                        editButton.setToolTipText("Click edit this Item");
                        editButton.setBackground(new java.awt.Color(102,102,102));
                        editButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
                        editButton.setForeground(new java.awt.Color(255, 255, 255));
                        editButton.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), null));
			editButton.addActionListener(this);
			edit.add(editButton);
                        
                        /**JButton deleteButton = new JButton(" DELETE ");
                        deleteButton.setToolTipText("Click delete this item");
                        deleteButton.setBackground(new java.awt.Color(102, 102, 102));
                        deleteButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
                        deleteButton.setForeground(new java.awt.Color(255, 255, 255));
                        deleteButton.addActionListener(this);
                        delete.add(deleteButton);**/
                    }
                    itemListPanel.setLayout(new GridLayout(items.size()+1, 1));
               
                    for(int i = 0; i < items.size(); i++)
                    {
                        Item item = items.get(i);
                        JPanel j = new JPanel();
                        j.setLayout(new GridLayout(1, 6));
                        item.checkbox.addActionListener(this);
            
                        j.add(item.checkbox);
                        j.add(item.nameLabel);
                        j.add(item.categoryLabel);
                        j.add(item.storeLabel);
                        j.add(item.importanceLabel);
                        j.add(edit.get(i));
                        //j.add(delete.get(i));
                        itemListPanel.add(j);
                    }  
                    
                
                itemListPanel.doLayout();
                itemListPanel.setVisible(true);
        }   
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        listViewPanel = new javax.swing.JPanel();
        refreshButton = new javax.swing.JButton();
        searchByNameButton = new javax.swing.JButton();
        removeItemButton = new javax.swing.JButton();
        searchTextField = new javax.swing.JTextField();
        crossOffSortButton = new javax.swing.JButton();
        nameSortButton = new javax.swing.JButton();
        categorySortButton = new javax.swing.JButton();
        storeSortButton = new javax.swing.JButton();
        importanceSortButton = new javax.swing.JButton();
        BackButton = new javax.swing.JButton();
        listIdentifierLabel = new javax.swing.JLabel();
        addItemButton = new javax.swing.JButton();
        sortByLabel = new javax.swing.JLabel();
        changeListNameButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        itemListPanel = new javax.swing.JPanel();
        totalField = new javax.swing.JTextField();
        setTotal = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximizedBounds(new java.awt.Rectangle(0, 0, 0, 0));

        listViewPanel.setBackground(new java.awt.Color(51, 51, 51));
        listViewPanel.setName("listViewPanel"); // NOI18N
        listViewPanel.setPreferredSize(new java.awt.Dimension(900, 540));

        refreshButton.setBackground(new java.awt.Color(102, 102, 102));
        refreshButton.setFont(new java.awt.Font("Tahoma", 1, 18));
        refreshButton.setForeground(new java.awt.Color(255, 255, 255));
        refreshButton.setText("Refresh");
        refreshButton.setToolTipText("Click to refresh page");
        refreshButton.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), null));
        refreshButton.setName("refreshButton"); // NOI18N
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        searchByNameButton.setBackground(new java.awt.Color(102, 102, 102));
        searchByNameButton.setFont(new java.awt.Font("Tahoma", 1, 18));
        searchByNameButton.setForeground(new java.awt.Color(255, 255, 255));
        searchByNameButton.setText("Go");
        searchByNameButton.setToolTipText("Enter name of item to search for. Then click GO.");
        searchByNameButton.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), null));
        searchByNameButton.setName("searchByNameButton"); // NOI18N
        searchByNameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchByNameButtonActionPerformed(evt);
            }
        });

        removeItemButton.setBackground(new java.awt.Color(102, 102, 102));
        removeItemButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        removeItemButton.setForeground(new java.awt.Color(255, 255, 255));
        removeItemButton.setText("Remove Items");
        removeItemButton.setToolTipText("Remove set of Items");
        removeItemButton.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), null));
        removeItemButton.setName("removeItemButton"); // NOI18N
        removeItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeItemButtonActionPerformed(evt);
            }
        });

        searchTextField.setFont(new java.awt.Font("Tahoma", 0, 14));
        searchTextField.setText("Item Search");
        searchTextField.setToolTipText("Enter name of item to search for. Then click GO.");
        searchTextField.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        searchTextField.setName("searchTextField"); // NOI18N

        crossOffSortButton.setFont(new java.awt.Font("Tahoma", 1, 10));
        crossOffSortButton.setText("Crossed off");
        crossOffSortButton.setToolTipText("Sort by Crossed off items"); // NOI18N
        crossOffSortButton.setName("crossOffSortButton"); // NOI18N
        crossOffSortButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crossOffSortButtonActionPerformed(evt);
            }
        });

        nameSortButton.setFont(new java.awt.Font("Tahoma", 1, 10));
        nameSortButton.setText("Name");
        nameSortButton.setToolTipText("Sort by Item Name"); // NOI18N
        nameSortButton.setName("nameSortButton"); // NOI18N
        nameSortButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameSortButtonActionPerformed(evt);
            }
        });

        categorySortButton.setFont(new java.awt.Font("Tahoma", 1, 10));
        categorySortButton.setText("Category");
        categorySortButton.setToolTipText("Sort By Category"); // NOI18N
        categorySortButton.setName("categorySortButton"); // NOI18N
        categorySortButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categorySortButtonActionPerformed(evt);
            }
        });

        storeSortButton.setFont(new java.awt.Font("Tahoma", 1, 10));
        storeSortButton.setText("Store");
        storeSortButton.setToolTipText("Sort by Store"); // NOI18N
        storeSortButton.setName("storeSortButton"); // NOI18N
        storeSortButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                storeSortButtonActionPerformed(evt);
            }
        });

        importanceSortButton.setFont(new java.awt.Font("Tahoma", 1, 10));
        importanceSortButton.setText("Importance");
        importanceSortButton.setToolTipText("Sort By Importance"); // NOI18N
        importanceSortButton.setName("importanceSortButton"); // NOI18N
        importanceSortButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importanceSortButtonActionPerformed(evt);
            }
        });

        BackButton.setBackground(new java.awt.Color(102, 102, 102));
        BackButton.setFont(new java.awt.Font("Tahoma", 1, 18));
        BackButton.setForeground(new java.awt.Color(255, 255, 255));
        BackButton.setText("Back");
        BackButton.setToolTipText("Click for main menu");
        BackButton.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), null));
        BackButton.setName("BackButton"); // NOI18N
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });

        listIdentifierLabel.setBackground(new java.awt.Color(255, 255, 255));
        listIdentifierLabel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        listIdentifierLabel.setForeground(new java.awt.Color(255, 153, 0));
        listIdentifierLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        listIdentifierLabel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        listIdentifierLabel.setName("listIdentifierLabel"); // NOI18N

        addItemButton.setBackground(new java.awt.Color(102, 102, 102));
        addItemButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        addItemButton.setForeground(new java.awt.Color(255, 255, 255));
        addItemButton.setText("Add Item");
        addItemButton.setToolTipText("Add an Item to list"); // NOI18N
        addItemButton.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), null));
        addItemButton.setMaximumSize(new java.awt.Dimension(163, 31));
        addItemButton.setMinimumSize(new java.awt.Dimension(163, 31));
        addItemButton.setName("addItemButton"); // NOI18N
        addItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addItemButtonActionPerformed(evt);
            }
        });

        sortByLabel.setBackground(new java.awt.Color(102, 102, 102));
        sortByLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
        sortByLabel.setForeground(new java.awt.Color(255, 255, 255));
        sortByLabel.setText("Sort By:");
        sortByLabel.setName("sortByLabel"); // NOI18N

        changeListNameButton.setBackground(new java.awt.Color(102, 102, 102));
        changeListNameButton.setFont(new java.awt.Font("Tahoma", 1, 18));
        changeListNameButton.setForeground(new java.awt.Color(255, 255, 255));
        changeListNameButton.setText("Change List Name");
        changeListNameButton.setToolTipText("Change the name of this list"); // NOI18N
        changeListNameButton.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), null));
        changeListNameButton.setName("changeListNameButton"); // NOI18N
        changeListNameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeListNameButtonActionPerformed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        itemListPanel.setName("itemListPanel"); // NOI18N
        itemListPanel.setLayout(new java.awt.GridLayout(1, 0));
        jScrollPane1.setViewportView(itemListPanel);

        totalField.setFont(new java.awt.Font("Tahoma", 0, 14));
        totalField.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        totalField.setName("totalField"); // NOI18N

        setTotal.setBackground(new java.awt.Color(102, 102, 102));
        setTotal.setFont(new java.awt.Font("Tahoma", 1, 18));
        setTotal.setForeground(new java.awt.Color(255, 255, 255));
        setTotal.setText("Set Total");
        setTotal.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), null));
        setTotal.setName("setTotal"); // NOI18N
        setTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setTotalActionPerformed(evt);
            }
        });

        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout listViewPanelLayout = new javax.swing.GroupLayout(listViewPanel);
        listViewPanel.setLayout(listViewPanelLayout);
        listViewPanelLayout.setHorizontalGroup(
            listViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, listViewPanelLayout.createSequentialGroup()
                .addGroup(listViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(listViewPanelLayout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(listViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(listViewPanelLayout.createSequentialGroup()
                                .addComponent(BackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(listIdentifierLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, listViewPanelLayout.createSequentialGroup()
                                .addComponent(searchByNameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(197, 197, 197)
                                .addComponent(totalField, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(setTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(listViewPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(sortByLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(listViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, listViewPanelLayout.createSequentialGroup()
                                .addComponent(crossOffSortButton, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameSortButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(categorySortButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(storeSortButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(importanceSortButton)
                                .addGap(16, 16, 16)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, listViewPanelLayout.createSequentialGroup()
                                .addComponent(addItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(removeItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(changeListNameButton, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(64, 64, 64))
        );
        listViewPanelLayout.setVerticalGroup(
            listViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(listViewPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(listViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(refreshButton)
                    .addComponent(listIdentifierLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BackButton))
                .addGap(18, 18, 18)
                .addGroup(listViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(listViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(searchByNameButton)
                        .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(listViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(totalField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(setTotal)))
                .addGap(30, 30, 30)
                .addGroup(listViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sortByLabel)
                    .addComponent(crossOffSortButton)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameSortButton)
                    .addComponent(categorySortButton)
                    .addComponent(storeSortButton)
                    .addComponent(importanceSortButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(listViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(changeListNameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(listViewPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(listViewPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-736)/2, (screenSize.height-447)/2, 736, 447);
    }// </editor-fold>//GEN-END:initComponents
        
    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        if (evt.getSource() == refreshButton) {            
            new itemListView(user, filename, mDBApi);
                    setVisible(false);    
                    dispose();      
        }                                      
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void changeListNameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeListNameButtonActionPerformed
        if (evt.getSource() == changeListNameButton) { 
            new changeListName(user, filename, mDBApi);            
            setVisible(true);
            dispose();
            
        }                                                       
    }//GEN-LAST:event_changeListNameButtonActionPerformed

    private void importanceSortButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importanceSortButtonActionPerformed
        if (evt.getSource() == importanceSortButton) {             
            Item temp;             
            JButton temp2;             
            for (int i = 0; i < items.size(); i++) {                 
                for (int j = 1; j < items.size() - i; j++) {                     
                    if (items.get(j - 1).importance > items.get(j).importance) {                         
                        temp = items.get(j - 1);                         
                        items.set(j - 1, items.get(j));                         
                        items.set(j, temp);                          
                        temp2 = edit.get(j - 1);                         
                        edit.set(j - 1, edit.get(j));                         
                        edit.set(j, temp2);                     
                    }                
                }             
            }             
            writeListToDropbox();             
            new itemListView(user, filename, mDBApi);             
            setVisible(false);             
            dispose();         
        }     
                                                        
    }//GEN-LAST:event_importanceSortButtonActionPerformed

    private void addItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemButtonActionPerformed
        if (evt.getSource() == addItemButton) {             
            new addItemFrame(user, filename, mDBApi);             
            setVisible(true);
            dispose();
        }                                                  
    }//GEN-LAST:event_addItemButtonActionPerformed

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        if (evt.getSource() == BackButton) {             
            new mainMenuFrame(user, mDBApi);   
            setVisible(false);             
            dispose();         
        }                                   
    }//GEN-LAST:event_BackButtonActionPerformed

    private void storeSortButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_storeSortButtonActionPerformed
        if (evt.getSource() == storeSortButton) {             
            Item temp;             
            JButton temp2;             
            for (int i = 0; i < items.size(); i++) {                 
                for (int j = 1; j < items.size() - i; j++) {                     
                    if (items.get(j - 1).store.compareTo(items.get(j).store) > 0) {                         
                        temp = items.get(j - 1);                         
                        items.set(j - 1, items.get(j));                         
                        items.set(j, temp);                          
                        temp2 = edit.get(j - 1);                         
                        edit.set(j - 1, edit.get(j));                         
                        edit.set(j, temp2);                     
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
        if (evt.getSource() == categorySortButton) {            
            Item temp;             
            JButton temp2;             
            for (int i = 0; i < items.size(); i++) {                 
                for (int j = 1; j < items.size() - i; j++) {                     
                    if (items.get(j - 1).category.compareTo(items.get(j).category) > 0) {                        
                        temp = items.get(j - 1);                         
                        items.set(j - 1, items.get(j));                         
                        items.set(j, temp);                          
                        temp2 = edit.get(j - 1);                         
                        edit.set(j - 1, edit.get(j));                         
                        edit.set(j, temp2);                     
                    }                 
                }             
            }             
            writeListToDropbox();             
            new itemListView(user, filename, mDBApi);             
            setVisible(false);             
            dispose();         
        }                                                       
    }//GEN-LAST:event_categorySortButtonActionPerformed

    private void nameSortButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameSortButtonActionPerformed
        if (evt.getSource() == nameSortButton) {            
            nameSort();             
            writeListToDropbox();             
            new itemListView(user, filename, mDBApi);             
            setVisible(false);             
            dispose();         
        }         
    }//GEN-LAST:event_nameSortButtonActionPerformed

private void crossOffSortButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crossOffSortButtonActionPerformed
    if (evt.getSource() == crossOffSortButton) {             
            nameSort();             
            LinkedList<Item> temp = new LinkedList<Item>();             
                for (int i = 0; i < items.size(); i++) {                 
                    if (!items.get(i).isChecked) {                     
                        temp.add(items.get(i));                 
                    }             
                }             
            for (int j = 0; j < items.size(); j++) {                 
                if (items.get(j).isChecked) {                     
                    temp.add(items.get(j));                 
                }             
            }             
            items = temp;             
            writeListToDropbox();             
            getItemList();             
            new itemListView(user, filename, mDBApi);             
            setVisible(false);             
            dispose();         
    }                                                     
}//GEN-LAST:event_crossOffSortButtonActionPerformed

    private void removeItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeItemButtonActionPerformed
        if (evt.getSource() == removeItemButton) {             
            new deleteItemsFrame(user, filename, mDBApi);             
            setVisible(true);  
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
                        else{
				JOptionPane.showMessageDialog(this, "Item not found."); 
                                
                        }
  		}
		
    }//GEN-LAST:event_searchByNameButtonActionPerformed

    private void setTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setTotalActionPerformed
        if(!totalField.getText().equals(""))
        {
            String amount = totalField.getText();
            //Only one decimal in string
            boolean okay = true;
            if(amount.indexOf('.') != amount.lastIndexOf('.'))
            {
                okay =false;
            }
            else
            {
                //Checks if amount only contains numbers or a decimal
                for(int i = 0; i < amount.length(); i++)
                {
                    int character = amount.charAt(i);
                    if(!(character >= 48 && character <= 57 || character == 46))
                    {
                        okay = false;
                    }
                }
            }
            if(okay)
            {
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
                    if(amount.contains("$"))
                        amount = amount.substring(0, amount.indexOf('$')) + amount.substring(amount.indexOf('$')+1);

                    StringTokenizer st = new StringTokenizer(Global.getFile(mDBApi, "/" + user + "/lists.txt"));
                    String newList = "";
                    while(st.hasMoreTokens())
                    {
                        String token = st.nextToken();
                        String token2 = st.nextToken();
                        String token3 = st.nextToken();
                        if(token.equals(Global.toFileName(filename)))
                            newList += token + "\t" + amount + "\t" + token3 + "\n";
                        else
                            newList += token + "\t" + token2 + "\t" + token3 + "\n";
                    }
               Global.putFileOverwrite(mDBApi, "/" + user + "/lists.txt", newList);
               JOptionPane.showMessageDialog(this, "Total Set: " + amount);
            }
            else
                JOptionPane.showMessageDialog(this, "Please enter a valid total.");
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Please enter total.");
        }
    }//GEN-LAST:event_setTotalActionPerformed

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
					
					temp2 = edit.get(j-1);
					edit.set(j-1, edit.get(j));
					edit.set(j, temp2);
				}
			}
		}
	}
    public void writeListToDropbox()
	{
		String newList = "";
		for(int i = 0; i < items.size(); i++)
		{
                    Item item = items.get(i);
                    newList += Global.toFileName(item.name) + "\t";
                    newList += Global.toFileName(item.category) + "\t";
                    newList += Global.toFileName(item.store) + "\t";
                    newList += item.importance + "\t";
                    newList += item.checkbox.isSelected() + "\n";
                    if(item.checkbox.isSelected() != item.isChecked)
                        item.setIsChecked(item.checkbox.isSelected());
		}
		Global.putFileOverwrite(mDBApi, "/" + user + "/" + Global.toFileName(filename) + ".txt", newList);
	}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackButton;
    private javax.swing.JButton addItemButton;
    private javax.swing.JButton categorySortButton;
    private javax.swing.JButton changeListNameButton;
    private javax.swing.JButton crossOffSortButton;
    private javax.swing.JButton importanceSortButton;
    private javax.swing.JPanel itemListPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel listIdentifierLabel;
    private javax.swing.JPanel listViewPanel;
    private javax.swing.JButton nameSortButton;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton removeItemButton;
    private javax.swing.JButton searchByNameButton;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JButton setTotal;
    private javax.swing.JLabel sortByLabel;
    private javax.swing.JButton storeSortButton;
    private javax.swing.JTextField totalField;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
            boolean doCheck = true;
            for(int i = 0; i < edit.size(); i++)
            {
                    if(e.getSource() == edit.get(i))
                    {
                        doCheck = false;
                            new editItemFrame(user, filename, items.get(i), mDBApi);
                            setVisible(true);
                            dispose();
                    }
            }
            if(doCheck)
            {
                writeListToDropbox();
            }
            
    }
    
}
