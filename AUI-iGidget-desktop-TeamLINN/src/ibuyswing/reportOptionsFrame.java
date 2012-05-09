/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * reportOptionsFrame.java
 *
 * Created on Apr 15, 2012, 3:44:38 PM
 */
package ibuyswing;

import java.awt.Checkbox;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.session.WebAuthSession;
import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
/**
 *
 * @author Luke
 */
public class reportOptionsFrame extends javax.swing.JFrame implements ActionListener {

        private static DropboxAPI<WebAuthSession> mDBApi;
	private LinkedList<JButton> list = new LinkedList<JButton>();
	private JCheckBox itemStatistics = new JCheckBox(" Item Statistics ");
	private JCheckBox expenseTracker = new JCheckBox(" Expense Tracker ");
        private ButtonGroup group = new ButtonGroup();
	private JRadioButton byWeek = new JRadioButton("By Week");
	private JRadioButton byLastTrip = new JRadioButton("By All");
	private static String user;
        
    /** Creates new form reportOptionsFrame */
    public reportOptionsFrame(String user, DropboxAPI<WebAuthSession> mDBApi) {
        super("Options Menu");
    
        initComponents();
  
		this.user = user;
		this.mDBApi = mDBApi;
                
                itemStatistics.setBackground(new java.awt.Color(51, 51, 51));
                itemStatistics.setForeground(Color.WHITE);
                
                expenseTracker.setBackground(new java.awt.Color(51, 51, 51));
                expenseTracker.setForeground(Color.WHITE);
                
                byWeek.setBackground(new java.awt.Color(51, 51, 51));
                byWeek.setForeground(Color.WHITE);
                
                byLastTrip.setBackground(new java.awt.Color(51, 51, 51));
                byLastTrip.setForeground(Color.WHITE); 
	
		StringTokenizer st = new StringTokenizer(new String(Global.getFile(mDBApi, "/" + user + "/report.txt")));
		while(st.hasMoreTokens())
		{
			String token = st.nextToken();
			if(token.equals("true"))
				itemStatistics.setSelected(true);
			token = st.nextToken();
			if(token.equals("true"))
				expenseTracker.setSelected(true);
                        else
                        {
                            byWeek.setEnabled(false);
                            byLastTrip.setEnabled(false);
                        }
			token = st.nextToken();
			if(token.equals("true"))
				byWeek.setSelected(true);
			token = st.nextToken();
			if(token.equals("true"))
				byLastTrip.setSelected(true);
                }
		optionsPanel.setLayout(new GridLayout(4,1));
                optionsPanel.setBackground(Color.DARK_GRAY);
		optionsPanel.add(itemStatistics);
                //JLabel itemStatsLabel = new JLabel(" Item Statistics ");
                //itemStatsLabel.setBackground(Color.DARK_GRAY);
                //itemStatsLabel.setForeground(Color.WHITE);
                
		//optionsPanel.add(itemStatsLabel);
                expenseTracker.addActionListener(this);
		optionsPanel.add(expenseTracker);
                //JLabel expTrackLabel = new JLabel(" Expense Tracker ");
                //expTrackLabel.setBackground(Color.DARK_GRAY);
                //expTrackLabel.setForeground(Color.WHITE);                
		//optionsPanel.add(expTrackLabel);
                
                group.add(byWeek);
                byWeek.setHorizontalAlignment(JLabel.CENTER);
		optionsPanel.add(byWeek);
                //JLabel byWeekLabel = new JLabel(" By Week ");
                //byWeekLabel.setBackground(Color.DARK_GRAY);
               // byWeekLabel.setForeground(Color.WHITE);                
		//optionsPanel.add(byWeekLabel);
                
                group.add(byLastTrip);
                byLastTrip.setHorizontalAlignment(JLabel.CENTER);
		optionsPanel.add(byLastTrip);
                //JLabel byLastTripLabel = new JLabel(" By All ");
                //byLastTripLabel.setBackground(Color.DARK_GRAY);
                //byLastTripLabel.setForeground(Color.WHITE);
		//optionsPanel.add(byLastTripLabel);
    }
    
    public void writeReportStats()
    {
        String newReport = "";
        if(itemStatistics.isSelected())
            newReport += "true\t";
        else
            newReport += "false\t";
        if(expenseTracker.isSelected())
            newReport += "true\t";
        else
            newReport += "false\t";
        if(byWeek.isSelected())
            newReport += "true\t";
        else
            newReport += "false\t";
        if(byLastTrip.isSelected())
            newReport += "true\t";
        else
            newReport += "false\t";
        Global.putFileOverwrite(mDBApi, "/" + user + "/report.txt", newReport);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        reportOptionsPanel = new javax.swing.JPanel();
        reportOptionsLabel = new javax.swing.JLabel();
        optionReturnButton = new javax.swing.JButton();
        optionsPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(reportOptionsFrame.class);
        reportOptionsPanel.setBackground(resourceMap.getColor("reportOptionsPanel.background")); // NOI18N
        reportOptionsPanel.setAutoscrolls(true);
        reportOptionsPanel.setName("reportOptionsPanel"); // NOI18N

        reportOptionsLabel.setFont(resourceMap.getFont("reportOptionsLabel.font")); // NOI18N
        reportOptionsLabel.setForeground(resourceMap.getColor("reportOptionsLabel.foreground")); // NOI18N
        reportOptionsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        reportOptionsLabel.setText(resourceMap.getString("reportOptionsLabel.text")); // NOI18N
        reportOptionsLabel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        reportOptionsLabel.setName("reportOptionsLabel"); // NOI18N

        optionReturnButton.setBackground(resourceMap.getColor("optionReturnButton.background")); // NOI18N
        optionReturnButton.setFont(resourceMap.getFont("optionReturnButton.font")); // NOI18N
        optionReturnButton.setForeground(resourceMap.getColor("optionReturnButton.foreground")); // NOI18N
        optionReturnButton.setText(resourceMap.getString("optionReturnButton.text")); // NOI18N
        optionReturnButton.setActionCommand(resourceMap.getString("optionReturnButton.actionCommand")); // NOI18N
        optionReturnButton.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), null));
        optionReturnButton.setName("optionReturnButton"); // NOI18N
        optionReturnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionReturnButtonActionPerformed(evt);
            }
        });
        optionReturnButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                optionReturnButtonKeyPressed(evt);
            }
        });

        optionsPanel.setName("optionsPanel"); // NOI18N

        javax.swing.GroupLayout optionsPanelLayout = new javax.swing.GroupLayout(optionsPanel);
        optionsPanel.setLayout(optionsPanelLayout);
        optionsPanelLayout.setHorizontalGroup(
            optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 133, Short.MAX_VALUE)
        );
        optionsPanelLayout.setVerticalGroup(
            optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 104, Short.MAX_VALUE)
        );

        jButton1.setFont(resourceMap.getFont("generate.font")); // NOI18N
        jButton1.setText(resourceMap.getString("generate.text")); // NOI18N
        jButton1.setName("generate"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout reportOptionsPanelLayout = new javax.swing.GroupLayout(reportOptionsPanel);
        reportOptionsPanel.setLayout(reportOptionsPanelLayout);
        reportOptionsPanelLayout.setHorizontalGroup(
            reportOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reportOptionsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(reportOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(reportOptionsPanelLayout.createSequentialGroup()
                        .addComponent(reportOptionsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reportOptionsPanelLayout.createSequentialGroup()
                        .addComponent(optionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reportOptionsPanelLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(reportOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(optionReturnButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(67, 67, 67))
        );
        reportOptionsPanelLayout.setVerticalGroup(
            reportOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportOptionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(reportOptionsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(optionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(optionReturnButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(reportOptionsPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(reportOptionsPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-323)/2, (screenSize.height-328)/2, 323, 328);
    }// </editor-fold>//GEN-END:initComponents

    private void optionReturnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionReturnButtonActionPerformed

        if(evt.getSource() == optionReturnButton)
		{
			String newReport = "";
			if(itemStatistics.isSelected())
				newReport += "true\t";
			else
				newReport += "false\t";
			if(expenseTracker.isSelected())
				newReport += "true\t";
			else
				newReport += "false\t";
			if(byWeek.isSelected())
				newReport += "true\t";
			else
				newReport += "false\t";
			if(byLastTrip.isSelected())
				newReport += "true\t";
			else
				newReport += "false\t";
			Global.putFileOverwrite(mDBApi, "/" + user + "/report.txt", newReport);
                        
			new mainMenuFrame(user, mDBApi);
			setVisible(false);
			dispose();
		}
    }//GEN-LAST:event_optionReturnButtonActionPerformed

    private void optionReturnButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_optionReturnButtonKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER);
            writeReportStats();
            new mainMenuFrame(user, mDBApi);
            setVisible(false);
    }//GEN-LAST:event_optionReturnButtonKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        writeReportStats();
        StringTokenizer st = new StringTokenizer(Global.getFile(mDBApi, "/"+user+"/report.txt"));
        boolean[] bool = new boolean[4];
        int i = 0;
        while(st.hasMoreTokens())
        {
            String token = st.nextToken();
            if(token.equals("false"))
            bool[i] = false;
            else
            bool[i] = true;
            i++;
        }
        if(bool[0])
        {
            new ItemStatistics(user, mDBApi);
        }
        if(bool[1])
        {
            new ExpenseTracker(user, mDBApi, bool[2], bool[3]);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton optionReturnButton;
    private javax.swing.JPanel optionsPanel;
    private javax.swing.JLabel reportOptionsLabel;
    private javax.swing.JPanel reportOptionsPanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == expenseTracker)
        {
            if(expenseTracker.isSelected())
            {
                byWeek.setEnabled(true);
                byLastTrip.setEnabled(true);
                if(!byWeek.isSelected() && !byLastTrip.isSelected())
                    byLastTrip.setSelected(true);
            }
            else
            {
                byWeek.setEnabled(false);
                byLastTrip.setEnabled(false);
                byWeek.setSelected(false);
                byLastTrip.setSelected(false);
            }
        }
    }
}
