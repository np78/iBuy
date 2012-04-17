/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * addItemFrame.java
 *
 * Created on Apr 15, 2012, 2:09:33 AM
 */
package ibuyswing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.StringTokenizer;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.session.WebAuthSession;
/**
 *
 * @author Luke
 */
public class addItemFrame1 extends javax.swing.JFrame {

        private static DropboxAPI<WebAuthSession> mDBApi;
	private static String user, filename;
	private Item item;
        
    /** Creates new form addItemFrame */
    public addItemFrame1(String user, String filename, DropboxAPI<WebAuthSession> mDBApi) {
        
                super("iBuy - Add Item");
		this.user = user;
		this.filename = filename;
		this.item = new Item("Apple", "Food", "Grocery", 1, false);
		this.mDBApi = mDBApi;
    
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

        jPanel1 = new javax.swing.JPanel();
        newItemLabel = new javax.swing.JLabel();
        canceladdItemButton = new javax.swing.JButton();
        addItemButton = new javax.swing.JButton();
        newItemTextField = new javax.swing.JTextField();
        itemNameLabel = new javax.swing.JLabel();
        instructionLabel = new javax.swing.JLabel();
        newItemFeedBackLabel = new javax.swing.JLabel();
        categoryLabel = new javax.swing.JLabel();
        categoryField = new javax.swing.JTextField();
        storeLabel = new javax.swing.JLabel();
        storeField = new javax.swing.JTextField();
        importanceLabel = new javax.swing.JLabel();
        importanceField = new javax.swing.JTextField();
        importanceScaleLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(addItemFrame1.class);
        jPanel1.setBackground(resourceMap.getColor("jPanel1.background")); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        newItemLabel.setFont(resourceMap.getFont("newItemLabel.font")); // NOI18N
        newItemLabel.setForeground(resourceMap.getColor("newItemLabel.foreground")); // NOI18N
        newItemLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        newItemLabel.setText(resourceMap.getString("newItemLabel.text")); // NOI18N
        newItemLabel.setName("newItemLabel"); // NOI18N

        canceladdItemButton.setBackground(resourceMap.getColor("canceladdItemButton.background")); // NOI18N
        canceladdItemButton.setFont(resourceMap.getFont("canceladdItemButton.font")); // NOI18N
        canceladdItemButton.setForeground(resourceMap.getColor("canceladdItemButton.foreground")); // NOI18N
        canceladdItemButton.setText(resourceMap.getString("canceladdItemButton.text")); // NOI18N
        canceladdItemButton.setActionCommand(resourceMap.getString("canceladdItemButton.actionCommand")); // NOI18N
        canceladdItemButton.setName("canceladdItemButton"); // NOI18N
        canceladdItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                canceladdItemButtonActionPerformed(evt);
            }
        });

        addItemButton.setBackground(resourceMap.getColor("addItemButton.background")); // NOI18N
        addItemButton.setFont(resourceMap.getFont("addItemButton.font")); // NOI18N
        addItemButton.setForeground(resourceMap.getColor("addItemButton.foreground")); // NOI18N
        addItemButton.setText(resourceMap.getString("addItemButton.text")); // NOI18N
        addItemButton.setActionCommand(resourceMap.getString("addItemButton.actionCommand")); // NOI18N
        addItemButton.setName("addItemButton"); // NOI18N
        addItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addItemButtonActionPerformed(evt);
            }
        });

        newItemTextField.setFont(resourceMap.getFont("newItemTextField.font")); // NOI18N
        newItemTextField.setName("newItemTextField"); // NOI18N

        itemNameLabel.setFont(resourceMap.getFont("itemNameLabel.font")); // NOI18N
        itemNameLabel.setForeground(resourceMap.getColor("itemNameLabel.foreground")); // NOI18N
        itemNameLabel.setText(resourceMap.getString("itemNameLabel.text")); // NOI18N
        itemNameLabel.setName("itemNameLabel"); // NOI18N

        instructionLabel.setFont(resourceMap.getFont("instructionLabel.font")); // NOI18N
        instructionLabel.setForeground(resourceMap.getColor("instructionLabel.foreground")); // NOI18N
        instructionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        instructionLabel.setText(resourceMap.getString("instructionLabel.text")); // NOI18N
        instructionLabel.setName("instructionLabel"); // NOI18N

        newItemFeedBackLabel.setFont(resourceMap.getFont("newItemFeedBackLabel.font")); // NOI18N
        newItemFeedBackLabel.setForeground(resourceMap.getColor("newItemFeedBackLabel.foreground")); // NOI18N
        newItemFeedBackLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        newItemFeedBackLabel.setName("newItemFeedBackLabel"); // NOI18N

        categoryLabel.setFont(resourceMap.getFont("categoryLabel.font")); // NOI18N
        categoryLabel.setForeground(resourceMap.getColor("categoryLabel.foreground")); // NOI18N
        categoryLabel.setText(resourceMap.getString("categoryLabel.text")); // NOI18N
        categoryLabel.setName("categoryLabel"); // NOI18N

        categoryField.setFont(resourceMap.getFont("categoryField.font")); // NOI18N
        categoryField.setName("categoryField"); // NOI18N

        storeLabel.setFont(resourceMap.getFont("storeLabel.font")); // NOI18N
        storeLabel.setForeground(resourceMap.getColor("storeLabel.foreground")); // NOI18N
        storeLabel.setText(resourceMap.getString("storeLabel.text")); // NOI18N
        storeLabel.setName("storeLabel"); // NOI18N

        storeField.setFont(resourceMap.getFont("storeField.font")); // NOI18N
        storeField.setName("storeField"); // NOI18N

        importanceLabel.setFont(resourceMap.getFont("importanceLabel.font")); // NOI18N
        importanceLabel.setForeground(resourceMap.getColor("importanceLabel.foreground")); // NOI18N
        importanceLabel.setText(resourceMap.getString("importanceLabel.text")); // NOI18N
        importanceLabel.setName("importanceLabel"); // NOI18N

        importanceField.setFont(resourceMap.getFont("importanceField.font")); // NOI18N
        importanceField.setName("importanceField"); // NOI18N

        importanceScaleLabel.setForeground(resourceMap.getColor("importanceScaleLabel.foreground")); // NOI18N
        importanceScaleLabel.setText(resourceMap.getString("importanceScaleLabel.text")); // NOI18N
        importanceScaleLabel.setName("importanceScaleLabel"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(newItemLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(instructionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(87, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(storeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(storeField, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(categoryLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(categoryField, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(itemNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(newItemTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(72, 72, 72))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(canceladdItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(newItemFeedBackLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(importanceLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(importanceField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(importanceScaleLabel)
                .addContainerGap(93, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(newItemLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(instructionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newItemFeedBackLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newItemTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itemNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categoryField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(categoryLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(storeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(storeLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(importanceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(importanceLabel)
                    .addComponent(importanceScaleLabel))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(canceladdItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-344)/2, (screenSize.height-376)/2, 344, 376);
    }// </editor-fold>//GEN-END:initComponents

    private void addItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemButtonActionPerformed
        // TODO add your handling code here:
        if(evt.getSource() == addItemButton)
  		{
			String name = Global.toFileName(item.nameField.getText());
			String category = Global.toFileName(item.categoryField.getText());
			String store = Global.toFileName(item.storeField.getText());
			String importance = item.importanceField.getText();
  			if(name.equals("") || category.equals("") || store.equals("") || importance.equals(""))
  				System.out.println("Please complete all fields");
  			else
  			{
  				//Append item parameters to end of list
  				String list = Global.getFile(mDBApi, "/" + user + "/" + Global.toFileName(filename) + ".txt");
  				list += name + "\t" + category + "\t" + store + "\t" + importance + "\tfalse\n";
  				Global.putFileOverwrite(mDBApi, "/" + user + "/" + Global.toFileName(filename) + ".txt", list);
  				
  				//Update list date
  				StringTokenizer st = new StringTokenizer(Global.getFile(mDBApi, "/" + user + "/lists.txt"));
				String newList = "";
				while(st.hasMoreTokens())
				{
					String token = st.nextToken();
					String token2 = st.nextToken();
					String token3 = st.nextToken();
					if(token.equals(Global.toFileName(filename)))
					{
						Date d = new Date(System.currentTimeMillis());
						newList += token + "\t" + token2 + "\t" + Global.toFileName(d.toString())  + "\n";
					}
					else
						newList += token + "\t" + token2 + "\t" + token3 + "\n";
				}
  				Global.putFileOverwrite(mDBApi, "/" + user + "/lists.txt", newList);
  				
  				//new List(user, filename, mDBApi);
  	  			//setVisible(false);
  	  			dispose();
  			}
  		}
    }//GEN-LAST:event_addItemButtonActionPerformed

    private void canceladdItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_canceladdItemButtonActionPerformed
        // TODO add your handling code here:
        if(evt.getSource() == canceladdItemButton)
  		{
  			dispose();
  		}
    }//GEN-LAST:event_canceladdItemButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addItemButton;
    private javax.swing.JButton canceladdItemButton;
    private javax.swing.JTextField categoryField;
    private javax.swing.JLabel categoryLabel;
    private javax.swing.JTextField importanceField;
    private javax.swing.JLabel importanceLabel;
    private javax.swing.JLabel importanceScaleLabel;
    private javax.swing.JLabel instructionLabel;
    private javax.swing.JLabel itemNameLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel newItemFeedBackLabel;
    private javax.swing.JLabel newItemLabel;
    private javax.swing.JTextField newItemTextField;
    private javax.swing.JTextField storeField;
    private javax.swing.JLabel storeLabel;
    // End of variables declaration//GEN-END:variables
}
