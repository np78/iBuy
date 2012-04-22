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
public class editItemFrame extends javax.swing.JFrame {

        private DropboxAPI<WebAuthSession> mDBApi;
	private String user, filename, originalName;
	private Item item;
        
    /** Creates new form addItemFrame */
    public editItemFrame(String user, String filename, Item item, DropboxAPI<WebAuthSession> mDBApi) {
        
                super("iBuy - Edit Item");
		this.user = user;
		this.filename = filename;
		this.item = item;
		this.mDBApi = mDBApi;
    
        initComponents();
        setVisible(true);
    }
    public void getItemInfo(){
    
            currentCategoryLabelC.setText(item.getCategory());
            currentImportanceLabelC.setText("" + item.getImportance());
            currentItemLabelC.setText(item.getName());
            currentStoreLabelC.setText(item.getStore());    
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        editItemPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        currentItemNameLabel = new javax.swing.JLabel();
        newCategoryLabel = new javax.swing.JLabel();
        currentCategoryLabel = new javax.swing.JLabel();
        newItemNameLabel = new javax.swing.JLabel();
        currentStoreLabel = new javax.swing.JLabel();
        newStoreLabel = new javax.swing.JLabel();
        currentImportanceLabel = new javax.swing.JLabel();
        newImportanceLabel = new javax.swing.JLabel();
        currentItemLabelC = new javax.swing.JLabel();
        currentCategoryLabelC = new javax.swing.JLabel();
        currentStoreLabelC = new javax.swing.JLabel();
        currentImportanceLabelC = new javax.swing.JLabel();
        newItemNameField = new javax.swing.JTextField();
        newCategoryField = new javax.swing.JTextField();
        newStoreField = new javax.swing.JTextField();
        newImportanceField = new javax.swing.JTextField();
        updateButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(editItemFrame.class);
        editItemPanel.setBackground(resourceMap.getColor("editItemPanel.background")); // NOI18N
        editItemPanel.setName("editItemPanel"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setForeground(resourceMap.getColor("jLabel1.foreground")); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        currentItemNameLabel.setFont(resourceMap.getFont("currentItemNameLabel.font")); // NOI18N
        currentItemNameLabel.setForeground(resourceMap.getColor("currentItemNameLabel.foreground")); // NOI18N
        currentItemNameLabel.setText(resourceMap.getString("currentItemNameLabel.text")); // NOI18N
        currentItemNameLabel.setName("currentItemNameLabel"); // NOI18N

        newCategoryLabel.setFont(resourceMap.getFont("newCategoryLabel.font")); // NOI18N
        newCategoryLabel.setForeground(resourceMap.getColor("newCategoryLabel.foreground")); // NOI18N
        newCategoryLabel.setText(resourceMap.getString("newCategoryLabel.text")); // NOI18N
        newCategoryLabel.setName("newCategoryLabel"); // NOI18N

        currentCategoryLabel.setFont(resourceMap.getFont("currentCategoryLabel.font")); // NOI18N
        currentCategoryLabel.setForeground(resourceMap.getColor("currentCategoryLabel.foreground")); // NOI18N
        currentCategoryLabel.setText(resourceMap.getString("currentCategoryLabel.text")); // NOI18N
        currentCategoryLabel.setName("currentCategoryLabel"); // NOI18N

        newItemNameLabel.setFont(resourceMap.getFont("newItemNameLabel.font")); // NOI18N
        newItemNameLabel.setForeground(resourceMap.getColor("newItemNameLabel.foreground")); // NOI18N
        newItemNameLabel.setText(resourceMap.getString("newItemNameLabel.text")); // NOI18N
        newItemNameLabel.setName("newItemNameLabel"); // NOI18N

        currentStoreLabel.setFont(resourceMap.getFont("currentStoreLabel.font")); // NOI18N
        currentStoreLabel.setForeground(resourceMap.getColor("currentStoreLabel.foreground")); // NOI18N
        currentStoreLabel.setText(resourceMap.getString("currentStoreLabel.text")); // NOI18N
        currentStoreLabel.setName("currentStoreLabel"); // NOI18N

        newStoreLabel.setFont(resourceMap.getFont("newStoreLabel.font")); // NOI18N
        newStoreLabel.setForeground(resourceMap.getColor("newStoreLabel.foreground")); // NOI18N
        newStoreLabel.setText(resourceMap.getString("newStoreLabel.text")); // NOI18N
        newStoreLabel.setName("newStoreLabel"); // NOI18N

        currentImportanceLabel.setFont(resourceMap.getFont("currentImportanceLabel.font")); // NOI18N
        currentImportanceLabel.setForeground(resourceMap.getColor("currentImportanceLabel.foreground")); // NOI18N
        currentImportanceLabel.setText(resourceMap.getString("currentImportanceLabel.text")); // NOI18N
        currentImportanceLabel.setName("currentImportanceLabel"); // NOI18N

        newImportanceLabel.setFont(resourceMap.getFont("newImportanceLabel.font")); // NOI18N
        newImportanceLabel.setForeground(resourceMap.getColor("newImportanceLabel.foreground")); // NOI18N
        newImportanceLabel.setText(resourceMap.getString("newImportanceLabel.text")); // NOI18N
        newImportanceLabel.setName("newImportanceLabel"); // NOI18N

        currentItemLabelC.setFont(resourceMap.getFont("currentItemLabelC.font")); // NOI18N
        currentItemLabelC.setForeground(resourceMap.getColor("currentItemLabelC.foreground")); // NOI18N
        currentItemLabelC.setText(resourceMap.getString("currentItemLabelC.text")); // NOI18N
        currentItemLabelC.setName("currentItemLabelC"); // NOI18N

        currentCategoryLabelC.setFont(resourceMap.getFont("currentCategoryLabelC.font")); // NOI18N
        currentCategoryLabelC.setForeground(resourceMap.getColor("currentCategoryLabelC.foreground")); // NOI18N
        currentCategoryLabelC.setText(resourceMap.getString("currentCategoryLabelC.text")); // NOI18N
        currentCategoryLabelC.setName("currentCategoryLabelC"); // NOI18N

        currentStoreLabelC.setFont(resourceMap.getFont("currentStoreLabelC.font")); // NOI18N
        currentStoreLabelC.setForeground(resourceMap.getColor("currentStoreLabelC.foreground")); // NOI18N
        currentStoreLabelC.setText(resourceMap.getString("currentStoreLabelC.text")); // NOI18N
        currentStoreLabelC.setName("currentStoreLabelC"); // NOI18N

        currentImportanceLabelC.setFont(resourceMap.getFont("currentImportanceLabelC.font")); // NOI18N
        currentImportanceLabelC.setForeground(resourceMap.getColor("currentImportanceLabelC.foreground")); // NOI18N
        currentImportanceLabelC.setText(resourceMap.getString("currentImportanceLabelC.text")); // NOI18N
        currentImportanceLabelC.setName("currentImportanceLabelC"); // NOI18N

        newItemNameField.setText(resourceMap.getString("newItemNameField.text")); // NOI18N
        newItemNameField.setName("newItemNameField"); // NOI18N

        newCategoryField.setName("newCategoryField"); // NOI18N

        newStoreField.setName("newStoreField"); // NOI18N

        newImportanceField.setName("newImportanceField"); // NOI18N

        updateButton.setBackground(resourceMap.getColor("updateButton.background")); // NOI18N
        updateButton.setFont(resourceMap.getFont("updateButton.font")); // NOI18N
        updateButton.setForeground(resourceMap.getColor("updateButton.foreground")); // NOI18N
        updateButton.setText(resourceMap.getString("updateButton.text")); // NOI18N
        updateButton.setActionCommand(resourceMap.getString("updateButton.actionCommand")); // NOI18N
        updateButton.setName("updateButton"); // NOI18N
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
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

        javax.swing.GroupLayout editItemPanelLayout = new javax.swing.GroupLayout(editItemPanel);
        editItemPanel.setLayout(editItemPanelLayout);
        editItemPanelLayout.setHorizontalGroup(
            editItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editItemPanelLayout.createSequentialGroup()
                .addGroup(editItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editItemPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE))
                    .addGroup(editItemPanelLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(editItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(newItemNameLabel)
                            .addComponent(currentItemNameLabel)
                            .addComponent(currentCategoryLabel)
                            .addComponent(newCategoryLabel)
                            .addComponent(currentStoreLabel)
                            .addComponent(newStoreLabel)
                            .addComponent(currentImportanceLabel)
                            .addComponent(newImportanceLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(editItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(newImportanceField)
                            .addComponent(newStoreField)
                            .addComponent(newCategoryField)
                            .addComponent(currentImportanceLabelC, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                            .addComponent(currentStoreLabelC, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                            .addComponent(currentItemLabelC, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                            .addComponent(currentCategoryLabelC, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                            .addComponent(newItemNameField)))
                    .addGroup(editItemPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        editItemPanelLayout.setVerticalGroup(
            editItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editItemPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(editItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentItemNameLabel)
                    .addComponent(currentItemLabelC))
                .addGap(18, 18, 18)
                .addGroup(editItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newItemNameLabel)
                    .addComponent(newItemNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentCategoryLabel)
                    .addComponent(currentCategoryLabelC))
                .addGap(18, 18, 18)
                .addGroup(editItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newCategoryLabel)
                    .addComponent(newCategoryField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentStoreLabel)
                    .addComponent(currentStoreLabelC))
                .addGap(18, 18, 18)
                .addGroup(editItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newStoreLabel)
                    .addComponent(newStoreField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentImportanceLabel)
                    .addComponent(currentImportanceLabelC))
                .addGap(18, 18, 18)
                .addGroup(editItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newImportanceLabel)
                    .addComponent(newImportanceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(editItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editItemPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editItemPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-344)/2, (screenSize.height-466)/2, 344, 466);
    }// </editor-fold>//GEN-END:initComponents

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        if(evt.getSource() == updateButton)
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
	  				
	  				new itemListView(user, filename, mDBApi);
	  	  			setVisible(false);
	  	  			dispose();
  				}
  			}
                }
    }//GEN-LAST:event_updateButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        if(evt.getSource() == cancelButton)
  		{
			new itemListView(user, filename, mDBApi);
  			setVisible(false);
  			dispose();
  		}
    }//GEN-LAST:event_cancelButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel currentCategoryLabel;
    private javax.swing.JLabel currentCategoryLabelC;
    private javax.swing.JLabel currentImportanceLabel;
    private javax.swing.JLabel currentImportanceLabelC;
    private javax.swing.JLabel currentItemLabelC;
    private javax.swing.JLabel currentItemNameLabel;
    private javax.swing.JLabel currentStoreLabel;
    private javax.swing.JLabel currentStoreLabelC;
    private javax.swing.JPanel editItemPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField newCategoryField;
    private javax.swing.JLabel newCategoryLabel;
    private javax.swing.JTextField newImportanceField;
    private javax.swing.JLabel newImportanceLabel;
    private javax.swing.JTextField newItemNameField;
    private javax.swing.JLabel newItemNameLabel;
    private javax.swing.JTextField newStoreField;
    private javax.swing.JLabel newStoreLabel;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
