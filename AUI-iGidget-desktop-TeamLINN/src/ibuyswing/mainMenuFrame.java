/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * mainMenuFrame.java
 *
 * Created on Apr 16, 2012, 11:40:09 AM
 */
package ibuyswing;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.*;


import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.session.WebAuthSession;
import java.awt.Color;

/**
 *
 * @author Luke
 */
public class mainMenuFrame extends javax.swing.JFrame implements ActionListener{

    private static DropboxAPI<WebAuthSession> mDBApi;
    private LinkedList<JButton> list = new LinkedList<JButton>(); 
    private String user;
    
    
    /** Creates new form mainMenuFrame */
    public mainMenuFrame(String user, DropboxAPI<WebAuthSession> mDBApi) {
        super("Ibuy-Main Menu");
        
        this.user = user;
	this.mDBApi = mDBApi;
            
        initComponents();
        getButtonList();
  
	    
	setVisible(true);
    }
    private void getButtonList(){
    
        StringTokenizer st = new StringTokenizer(Global.getFile(mDBApi, "/"+user+"/lists.txt"));
	    list.clear();
		while(st.hasMoreTokens())
		{
			JButton jListsButtons = new JButton(Global.readFileName(st.nextToken()));
                        jListsButtons.setBackground(new java.awt.Color(102,102,102));
                        jListsButtons.setFont(new java.awt.Font("Tahoma 18", 1, 18));
                        jListsButtons.setForeground(new java.awt.Color(255,255,255));
                        jListsButtons.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), null));
                        jListsButtons.setToolTipText("Click to see list deatils."); 
                        
			st.nextToken();
			st.nextToken();
			jListsButtons.addActionListener(this);
			list.add(jListsButtons);
		}	
		//Sets layout with list size in mind
		listPanel.setLayout(new GridLayout(list.size(),1));
	
		for(int i = 0; i < list.size(); i++)
		{
		    listPanel.add(list.get(i));
                }
    
    }
    public void actionPerformedList(java.awt.event.ActionEvent evt){
       //System.out.println("pressed");
		//Checks which button is pressed and shifts to List passing the list's name
		for(int i = 0; i < list.size(); i++)
		{
			if(evt.getSource() == list.get(i))
			{
                            //System.out.println(list.get(i).getText());
				new itemListView(user, list.get(i).getText(), mDBApi);
				setVisible(false);
				dispose();
			}
		}
        
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainMenuPanel = new javax.swing.JPanel();
        MainMenuLabel = new javax.swing.JLabel();
        addListButton = new javax.swing.JButton();
        deleteList1 = new javax.swing.JButton();
        optionsButton1 = new javax.swing.JButton();
        logOutButton1 = new javax.swing.JButton();
        listLabelMainMenu = new javax.swing.JLabel();
        listScrollPane = new javax.swing.JScrollPane();
        listPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 102));

        mainMenuPanel.setBackground(new java.awt.Color(51, 51, 51));
        mainMenuPanel.setName("mainMenuPanel"); // NOI18N

        MainMenuLabel.setFont(new java.awt.Font("Tahoma 36", 1, 36)); // NOI18N
        MainMenuLabel.setForeground(new java.awt.Color(255, 102, 0));
        MainMenuLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MainMenuLabel.setText("Main Menu");
        MainMenuLabel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        MainMenuLabel.setName("MainMenuLabel"); // NOI18N

        addListButton.setBackground(new java.awt.Color(102, 102, 102));
        addListButton.setFont(new java.awt.Font("Tahoma 18", 1, 18));
        addListButton.setForeground(new java.awt.Color(255, 255, 255));
        addListButton.setToolTipText("Click to add a new list"); // NOI18N
        addListButton.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), null));
        addListButton.setLabel("Add New List");
        addListButton.setName("addListButton"); // NOI18N
        addListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addListButtonActionPerformed(evt);
            }
        });

        deleteList1.setBackground(new java.awt.Color(102, 102, 102));
        deleteList1.setFont(new java.awt.Font("Tahoma 18", 1, 18));
        deleteList1.setForeground(new java.awt.Color(255, 255, 255));
        deleteList1.setText("Delete List");
        deleteList1.setToolTipText("Click to delete a list"); // NOI18N
        deleteList1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), null));
        deleteList1.setName("deleteList1"); // NOI18N
        deleteList1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteList1ActionPerformed(evt);
            }
        });

        optionsButton1.setBackground(new java.awt.Color(102, 102, 102));
        optionsButton1.setFont(new java.awt.Font("Tahoma 18", 1, 18));
        optionsButton1.setForeground(new java.awt.Color(255, 255, 255));
        optionsButton1.setText("Report Options");
        optionsButton1.setToolTipText("Click to change report options"); // NOI18N
        optionsButton1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), null));
        optionsButton1.setName("optionsButton1"); // NOI18N
        optionsButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionsButton1ActionPerformed(evt);
            }
        });

        logOutButton1.setBackground(new java.awt.Color(102, 102, 102));
        logOutButton1.setFont(new java.awt.Font("Tahoma 18", 1, 18));
        logOutButton1.setForeground(new java.awt.Color(255, 255, 255));
        logOutButton1.setText("Log Out");
        logOutButton1.setToolTipText("Click to log out of session"); // NOI18N
        logOutButton1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), null));
        logOutButton1.setName("logOutButton1"); // NOI18N
        logOutButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutButton1ActionPerformed(evt);
            }
        });

        listLabelMainMenu.setFont(new java.awt.Font("Tahoma", 1, 18));
        listLabelMainMenu.setForeground(new java.awt.Color(255, 102, 0));
        listLabelMainMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        listLabelMainMenu.setText("Lists:");
        listLabelMainMenu.setName("listLabelMainMenu"); // NOI18N

        listScrollPane.setBackground(new java.awt.Color(51, 51, 51));
        listScrollPane.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 102), new java.awt.Color(153, 153, 153), null, new java.awt.Color(0, 0, 0)));
        listScrollPane.setName("listScrollPane"); // NOI18N

        listPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));
        listPanel.setName("listPanel"); // NOI18N

        javax.swing.GroupLayout listPanelLayout = new javax.swing.GroupLayout(listPanel);
        listPanel.setLayout(listPanelLayout);
        listPanelLayout.setHorizontalGroup(
            listPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 219, Short.MAX_VALUE)
        );
        listPanelLayout.setVerticalGroup(
            listPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 164, Short.MAX_VALUE)
        );

        listScrollPane.setViewportView(listPanel);

        jButton1.setBackground(new java.awt.Color(102, 102, 102));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Refresh");
        jButton1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), null));
        jButton1.setName("refresh"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainMenuPanelLayout = new javax.swing.GroupLayout(mainMenuPanel);
        mainMenuPanel.setLayout(mainMenuPanelLayout);
        mainMenuPanelLayout.setHorizontalGroup(
            mainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainMenuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainMenuPanelLayout.createSequentialGroup()
                        .addComponent(logOutButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89))
                    .addGroup(mainMenuPanelLayout.createSequentialGroup()
                        .addComponent(listLabelMainMenu)
                        .addContainerGap(339, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainMenuPanelLayout.createSequentialGroup()
                        .addGroup(mainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mainMenuPanelLayout.createSequentialGroup()
                                .addComponent(listScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(deleteList1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                    .addComponent(optionsButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                    .addComponent(addListButton, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)))
                            .addGroup(mainMenuPanelLayout.createSequentialGroup()
                                .addComponent(MainMenuLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(21, 21, 21))))
        );
        mainMenuPanelLayout.setVerticalGroup(
            mainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainMenuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainMenuPanelLayout.createSequentialGroup()
                        .addComponent(MainMenuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(listLabelMainMenu))
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainMenuPanelLayout.createSequentialGroup()
                        .addComponent(addListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(deleteList1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(optionsButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(listScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(logOutButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainMenuPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainMenuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-412)/2, (screenSize.height-383)/2, 412, 383);
    }// </editor-fold>//GEN-END:initComponents

    private void addListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addListButtonActionPerformed
        if(evt.getSource() == addListButton)
		{
			new addNewListFrame(user, mDBApi);
			setVisible(true);  
                        dispose();
		}
    }//GEN-LAST:event_addListButtonActionPerformed

    private void deleteList1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteList1ActionPerformed
        if(evt.getSource() == deleteList1){
			new deleteListFrame(user, mDBApi);
			setVisible(true);
                        dispose();
	}
    }//GEN-LAST:event_deleteList1ActionPerformed

    private void optionsButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionsButton1ActionPerformed
        if (evt.getSource() == optionsButton1) { 
                    new reportOptionsFrame(user, mDBApi).setVisible(true);
                    dispose();
        }  
    }//GEN-LAST:event_optionsButton1ActionPerformed

    private void logOutButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutButton1ActionPerformed
        if(evt.getSource() == logOutButton1){
            new logInFrame();
             setVisible(true);            
             dispose();  
        }
    }//GEN-LAST:event_logOutButton1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new mainMenuFrame(user, mDBApi);
	setVisible(false);
	dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    public void actionPerformed(ActionEvent e) {
		//Checks which button is pressed and shifts to List passing the list's name
		for(int i = 0; i < list.size(); i++)
		{
			if(e.getSource() == list.get(i))
			{
                            //System.out.println(list.get(i).getText());
				new itemListView(user, list.get(i).getText(), mDBApi);
				setVisible(false);
				dispose();
			}
		}
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel MainMenuLabel;
    private javax.swing.JButton addListButton;
    private javax.swing.JButton deleteList1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel listLabelMainMenu;
    private javax.swing.JPanel listPanel;
    private javax.swing.JScrollPane listScrollPane;
    private javax.swing.JButton logOutButton1;
    private javax.swing.JPanel mainMenuPanel;
    private javax.swing.JButton optionsButton1;
    // End of variables declaration//GEN-END:variables

    /**@Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
        System.out.println("pressed");
		//Checks which button is pressed and shifts to List passing the list's name
		for(int i = 0; i < list.size(); i++)
		{
			if(e.getSource() == list.get(i))
			{
                            System.out.println(list.get(i).getText());
				new itemListView(user, list.get(i).getText(), mDBApi);
				setVisible(true);
				dispose();
			}
		}
    }**/
}