import javax.swing.JCheckBox;
import javax.swing.JTextField;



public class Item {
	public String name;
	public JTextField nameField = new JTextField();
	public String category;
	public JTextField categoryField = new JTextField();
	public String store;
	public JTextField storeField = new JTextField();
	public int importance;
	public JTextField importanceField = new JTextField();
	public boolean isChecked;
	public JCheckBox checkbox = new JCheckBox();
	
	public Item(String name, String category, String store, int importance, boolean isChecked)
	{
		setName(name);
		setCategory(category);
		setStore(store);
		setImportance(importance);
		setIsChecked(isChecked);
	}
	
	public void setName(String name)
	{
		this.name = name;
		nameField.setText(name);
	}
	
	public void setCategory(String category)
	{
		this.category = category;
		categoryField.setText(category);
	}
	
	public void setStore(String store)
	{
		this.store = store;
		storeField.setText(store);
	}
	
	public void setImportance(int importance)
	{
		this.importance = importance;
		importanceField.setText("" + importance);
	}
	
	public void setIsChecked(boolean isChecked)
	{
		this.isChecked = isChecked;
		checkbox.setSelected(isChecked);
	}
}
