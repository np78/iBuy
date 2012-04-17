/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * logInFrame.java
 *
 * Created on Apr 14, 2012, 4:41:05 PM
 */
package ibuyswing;

import java.awt.Container;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URL;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.jdesktop.application.Action;
import java.util.LinkedList;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.DropboxAPI.DropboxFileInfo;
import com.dropbox.client2.DropboxAPI.Entry;
import com.dropbox.client2.exception.DropboxException;
import com.dropbox.client2.session.*;
import com.dropbox.client2.session.Session.AccessType;
import com.dropbox.client2.session.WebAuthSession.WebAuthInfo;
/**
 *
 * @author Luke Tytor
 * @author Nicholas
 */
public class removeItemFrame extends javax.swing.JFrame {

    private DropboxAPI<WebAuthSession> mDBApi;
    private LinkedList<JCheckBox> checkboxes = new LinkedList<JCheckBox>();
	private JButton cancel = new JButton("Cancel");
	private JButton update = new JButton("Update");
	private String user, filename;
    
    /** Creates new form logInFrame */
    public removeItemFrame(String user, String filename, DropboxAPI<WebAuthSession> mDBApi) {
        super("iBuy - Remove Items");
		this.user = user;
		this.filename = filename;
		this.mDBApi = mDBApi;
                
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
		
	    //setContentPane(new JScrollPane(getContentPane()));
        setVisible(true);
	
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

        signInPanel = new javax.swing.JPanel();
        userNameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        signInButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        PasswordField = new javax.swing.JPasswordField();
        userNameField = new javax.swing.JTextField();
        signInLabel = new javax.swing.JLabel();
        feedBackLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(removeItemFrame.class);
        setBackground(resourceMap.getColor("Form.background")); // NOI18N
        setForeground(resourceMap.getColor("Form.foreground")); // NOI18N
        setName("Form"); // NOI18N

        signInPanel.setBackground(resourceMap.getColor("signInPanel.background")); // NOI18N
        signInPanel.setForeground(resourceMap.getColor("signInPanel.foreground")); // NOI18N
        signInPanel.setName("signInPanel"); // NOI18N

        userNameLabel.setBackground(resourceMap.getColor("userNameLabel.background")); // NOI18N
        userNameLabel.setFont(resourceMap.getFont("userNameLabel.font")); // NOI18N
        userNameLabel.setForeground(resourceMap.getColor("userNameLabel.foreground")); // NOI18N
        userNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        userNameLabel.setText(resourceMap.getString("userNameLabel.text")); // NOI18N
        userNameLabel.setName("userNameLabel"); // NOI18N

        passwordLabel.setBackground(resourceMap.getColor("passwordLabel.background")); // NOI18N
        passwordLabel.setFont(resourceMap.getFont("passwordLabel.font")); // NOI18N
        passwordLabel.setForeground(resourceMap.getColor("passwordLabel.foreground")); // NOI18N
        passwordLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        passwordLabel.setText(resourceMap.getString("passwordLabel.text")); // NOI18N
        passwordLabel.setName("passwordLabel"); // NOI18N

        signInButton.setBackground(resourceMap.getColor("signInButton.background")); // NOI18N
        signInButton.setFont(resourceMap.getFont("signInButton.font")); // NOI18N
        signInButton.setForeground(resourceMap.getColor("signInButton.foreground")); // NOI18N
        signInButton.setText(resourceMap.getString("signInButton.text")); // NOI18N
        signInButton.setActionCommand(resourceMap.getString("signInButton.actionCommand")); // NOI18N
        signInButton.setName("signInButton"); // NOI18N
        signInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signInButtonActionPerformed(evt);
            }
        });
        signInButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                signInButtonKeyPressed(evt);
            }
        });

        cancelButton.setBackground(resourceMap.getColor("cancelButton.background")); // NOI18N
        cancelButton.setFont(resourceMap.getFont("cancelButton.font")); // NOI18N
        cancelButton.setForeground(resourceMap.getColor("cancelButton.foreground")); // NOI18N
        cancelButton.setText(resourceMap.getString("cancelButton.text")); // NOI18N
        cancelButton.setActionCommand(resourceMap.getString("cancelButton.actionCommand")); // NOI18N
        cancelButton.setName("cancelButton"); // NOI18N
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        PasswordField.setText(resourceMap.getString("PasswordField.text")); // NOI18N
        PasswordField.setName("PasswordField"); // NOI18N

        userNameField.setText(resourceMap.getString("userNameField.text")); // NOI18N
        userNameField.setName("userNameField"); // NOI18N

        signInLabel.setFont(resourceMap.getFont("signInLabel.font")); // NOI18N
        signInLabel.setForeground(resourceMap.getColor("signInLabel.foreground")); // NOI18N
        signInLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        signInLabel.setText(resourceMap.getString("signInLabel.text")); // NOI18N
        signInLabel.setName("signInLabel"); // NOI18N

        feedBackLabel.setForeground(resourceMap.getColor("feedBackLabel.foreground")); // NOI18N
        feedBackLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        feedBackLabel.setText(resourceMap.getString("feedBackLabel.text")); // NOI18N
        feedBackLabel.setName("feedBackLabel"); // NOI18N

        javax.swing.GroupLayout signInPanelLayout = new javax.swing.GroupLayout(signInPanel);
        signInPanel.setLayout(signInPanelLayout);
        signInPanelLayout.setHorizontalGroup(
            signInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signInPanelLayout.createSequentialGroup()
                .addGroup(signInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(signInPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(feedBackLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(signInPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(signInLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, signInPanelLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(signInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(signInPanelLayout.createSequentialGroup()
                                .addComponent(passwordLabel)
                                .addGap(18, 18, 18)
                                .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(signInPanelLayout.createSequentialGroup()
                                .addComponent(userNameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(userNameField)))
                        .addGap(49, 49, 49))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, signInPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(signInButton, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        signInPanelLayout.setVerticalGroup(
            signInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signInPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(signInLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(signInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userNameLabel)
                    .addComponent(userNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(signInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(passwordLabel)
                    .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(feedBackLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(signInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(signInButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(signInPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(signInPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-323)/2, (screenSize.height-393)/2, 323, 393);
    }// </editor-fold>//GEN-END:initComponents

    private void signInButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signInButtonActionPerformed
        // TODO add your handling code here:
        if(evt.getSource() == signInButton)
  		{
  			logIn();
  		}
    }//GEN-LAST:event_signInButtonActionPerformed

    private void signInButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_signInButtonKeyPressed
        // TODO add your handling code here:
        if((PasswordField.isCursorSet() || userNameField.isCursorSet()) && evt.getKeyCode() == KeyEvent.VK_ENTER)
			logIn();
    }//GEN-LAST:event_signInButtonKeyPressed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
        if(evt.getSource() == cancelButton){
            dispose();
        }
    }//GEN-LAST:event_cancelButtonActionPerformed

    @Action
    public void logIn() {
        
        String user = userNameField.getText();
		String pass = PasswordField.getText();
		if(user.equals("") || pass.equals("")){
			feedBackLabel.setText("Please Complete Fields");
                }
                 else
		{
			//Reads users.txt to String to StringTokenizer
  			StringTokenizer scanner = new StringTokenizer(Global.getFile(mDBApi, "/users.txt"));
  			//Checks if "user" is in list and if password matches
  			while(scanner.hasMoreTokens())
  			{
  				String username = scanner.nextToken();
  				String password = scanner.nextToken();
  				if(username.equals(user) && password.equals(pass))
  				{
  					//new MainMenu(user, mDBApi);
  					setVisible(false);
  					dispose();
  				}
  			}
		}     
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel feedBackLabel;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JButton signInButton;
    private javax.swing.JLabel signInLabel;
    private javax.swing.JPanel signInPanel;
    private javax.swing.JTextField userNameField;
    private javax.swing.JLabel userNameLabel;
    // End of variables declaration//GEN-END:variables
}
