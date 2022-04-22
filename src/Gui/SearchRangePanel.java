package Gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchRangePanel extends JPanel{
	
	String SearchOption[]= {"전체","부서","성별","연봉","생일","부하직원"};
	JComboBox comboBox=new JComboBox<String>(SearchOption);
	
	String Department[]= {"Research","Administration","Headquarters"};
	JComboBox DepartmentComboBox=new JComboBox<String>(Department);
	

	String Sex[]= {"F","M"};
	JComboBox SexComboBox=new JComboBox<String>(Sex);
	
	JTextField SalaryJTextField=new JTextField(20);
	
	String Month[]= {"1","2","3","4","5","6","7","8","9","10","11","12"};
	JComboBox BirthdayComboBox=new JComboBox<String>(Month);
	
	//부하직원
	JTextField SubordinateJTextField=new JTextField(20);
	
	public SearchRangePanel() {
		this.add(new JLabel("검색범위"));
		
		this.add(comboBox);

		
		comboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	DepartmentComboBox.setVisible(false);
		    	SexComboBox.setVisible(false);
		    	SalaryJTextField.setVisible(false);
		    	BirthdayComboBox.setVisible(false);
		    	SubordinateJTextField.setVisible(false);
		    	
		        if(comboBox.getSelectedItem().toString().equals("전체")) {
		        	
		        }else if(comboBox.getSelectedItem().toString().equals("부서")) {
		        	DepartmentComboBox.setVisible(true);
		        	
		        }else if(comboBox.getSelectedItem().toString().equals("성별")) {
		        	SexComboBox.setVisible(true);
		        	
		        }else if(comboBox.getSelectedItem().toString().equals("연봉")) {
		        	SalaryJTextField.removeAll();
		        	SalaryJTextField.setVisible(true);
		        	
		        }else if(comboBox.getSelectedItem().toString().equals("생일")) {
		        	BirthdayComboBox.setVisible(true);
		        	
		        }else if(comboBox.getSelectedItem().toString().equals("부하직원")) {
		        	SubordinateJTextField.removeAll();
		        	SubordinateJTextField.setVisible(true);
		        	
		        }
		        GUI.getGUI().frame.revalidate();
		    }
		});
		
		this.add(DepartmentComboBox);
		this.add(SexComboBox);
		this.add(SalaryJTextField);
		this.add(BirthdayComboBox);
		this.add(SubordinateJTextField);
    	DepartmentComboBox.setVisible(false);
    	SexComboBox.setVisible(false);
    	SalaryJTextField.setVisible(false);
    	BirthdayComboBox.setVisible(false);
    	SubordinateJTextField.setVisible(false);

		
	}
	
	//[0]엔 카테고리=> 전체, 부서, 성별 등
	//[1]엔 조건=>어느부서인지? 어느성별인지?
	public ArrayList<String> getSearchRange() {
		ArrayList<String> result= new ArrayList<String>();
		result.add(comboBox.getSelectedItem().toString());
		if(comboBox.getSelectedItem().toString().equals("전체")) {
			result.add("전체");
        }else if(comboBox.getSelectedItem().toString().equals("부서")) {
        	result.add(DepartmentComboBox.getSelectedItem().toString());
        	
        }else if(comboBox.getSelectedItem().toString().equals("성별")) {
        	result.add(SexComboBox.getSelectedItem().toString());
        	
        }else if(comboBox.getSelectedItem().toString().equals("연봉")) {
        	result.add(SalaryJTextField.getText());
        	
        }else if(comboBox.getSelectedItem().toString().equals("생일")) {
        	result.add(BirthdayComboBox.getSelectedItem().toString());
        	
        }else if(comboBox.getSelectedItem().toString().equals("부하직원")) {
        	result.add(SubordinateJTextField.getText());
        	
        }
		
		return result;
	}

}
