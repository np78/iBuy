package ibuyswing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class Item{
	public String name;
	public JLabel nameLabel = new JLabel();
	public String category;
	public JLabel categoryLabel = new JLabel();
	public String store;
	public JLabel storeLabel = new JLabel();
	public int importance;
	public JLabel importanceLabel = new JLabel();
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
        
        public void setItem(Item item) {
            setName(item.name);
            setCategory(item.category);
            setImportance(item.importance);
            setStore(item.store);
            setIsChecked(item.isChecked);
        }
	
	public void setName(String name)
	{
		this.name = name;
		//checkbox.setLabel(name);
                nameLabel.setText(name);
                nameLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                nameLabel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
	}
        public String getName()
	{
		return name;
	}
	
	public void setCategory(String category)
	{
            
		this.category = category;
		categoryLabel.setText(category);
                categoryLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                categoryLabel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
	}
        public String getCategory()
	{
		return category;
	}
	
	public void setStore(String store)
	{
		this.store = store;
		storeLabel.setText(" " + store);
                storeLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                storeLabel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
	}
        public String getStore()
	{
		return store;
	}
	
	public void setImportance(int importance)
	{
		this.importance = importance;
		importanceLabel.setText(" " + importance);
                importanceLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                importanceLabel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
	}
        public int getImportance()
	{
		return importance;
	}
	
	public void setIsChecked(boolean isChecked)
	{
		this.isChecked = isChecked;
		checkbox.setSelected(isChecked);
                checkbox.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
	}
}
