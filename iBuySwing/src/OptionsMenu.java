import java.awt.Checkbox;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.session.WebAuthSession;


public class OptionsMenu extends JFrame implements ActionListener{
	private DropboxAPI<WebAuthSession> mDBApi;
	private LinkedList<JButton> list = new LinkedList<JButton>();
	private JCheckBox itemStatistics = new JCheckBox("Item Statistics");
	private JCheckBox expenseTracker = new JCheckBox("Expense Tracker");
	private ButtonGroup buttons = new ButtonGroup();
	private JRadioButton byWeek = new JRadioButton("    By Week");
	private JRadioButton byLastTrip = new JRadioButton("    All");
	private JButton back = new JButton("Return");
	private JButton generate = new JButton("Generate Reports Now!");
	private String user;
	
	public OptionsMenu(String user, DropboxAPI<WebAuthSession> mDBApi) 
	{
		super("Options");
		this.user = user;
		this.mDBApi = mDBApi;
		
		back.addActionListener(this);
		generate.addActionListener(this);
		setLayout(new GridLayout(3,1));
		setSize(500, 300);
		JPanel topRow = new JPanel();
		topRow.setLayout(new GridLayout(1, 2));
		topRow.add(new JTextField("Report Generation"));
		topRow.add(generate);
		add(topRow);
		
		StringTokenizer st = new StringTokenizer(new String(Global.getFile(mDBApi, "/" + user + "/report.txt")));
		while(st.hasMoreTokens())
		{
			String token = st.nextToken();
			if(token.equals("true"))
				itemStatistics.setSelected(true);
			token = st.nextToken();
			if(token.equals("true"))
				expenseTracker.setSelected(true);
			token = st.nextToken();
			if(token.equals("true"))
				byWeek.setSelected(true);
			token = st.nextToken();
			if(token.equals("true"))
				byLastTrip.setSelected(true);
		}
		
		JPanel jpanel = new JPanel();
		jpanel.setLayout(new GridLayout(4,1));
		jpanel.add(itemStatistics);
		//jpanel.add(new JTextField("Item Statistics"));
		jpanel.add(expenseTracker);
		//jpanel.add(new JTextField("Expense Tracker"));
		jpanel.add(byWeek);
		//jpanel.add(new JTextField("    By Week"));
		jpanel.add(byLastTrip);
		//jpanel.add(new JTextField("    All"));
		buttons.add(byWeek);
		buttons.add(byLastTrip);
		
		add(jpanel);
		add(back);
		setVisible(true);
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

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == generate)
		{
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
		}
		if(e.getSource() == back)
		{
			writeReportStats();
			new MainMenu(user, mDBApi);
			setVisible(false);
			dispose();
		}
	}
}
