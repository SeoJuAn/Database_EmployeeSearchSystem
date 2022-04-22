package Gui;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SearchAttrCheckBoxPanel extends JPanel{
	

	SearchRangePanel searchRangePanel=new SearchRangePanel();

	
	JCheckBox nameCheckBox=new JCheckBox("Name");
	JCheckBox ssnCheckBox=new JCheckBox("Ssn");
	JCheckBox bdateCheckBox=new JCheckBox("Bdate");
	JCheckBox addressCheckBox=new JCheckBox("Address");
	JCheckBox sexCheckBox=new JCheckBox("Sex");
	JCheckBox salaryCheckBox=new JCheckBox("Salary");
	JCheckBox supervisorCheckBox=new JCheckBox("Supervisor");
	JCheckBox departmentCheckBox=new JCheckBox("Department");
	
	public JButton searchButton=new JButton("검색");
	
	GridLayout gridLayout=new GridLayout(2,1);

	public SearchAttrCheckBoxPanel() {
		nameCheckBox.setSelected(true);
		ssnCheckBox.setSelected(true);
		bdateCheckBox.setSelected(true);
		addressCheckBox.setSelected(true);
		sexCheckBox.setSelected(true);
		salaryCheckBox.setSelected(true);
		supervisorCheckBox.setSelected(true);
		departmentCheckBox.setSelected(true);
		
		this.setLayout(gridLayout);

		this.add(searchRangePanel);
	
		//모양정리용 패널
		JPanel panel=new JPanel();
		
		panel.add(new JLabel("검색 항목"));
		
		panel.add(nameCheckBox);
		panel.add(ssnCheckBox);
		panel.add(bdateCheckBox);
		panel.add(addressCheckBox);
		panel.add(sexCheckBox);
		panel.add(salaryCheckBox);
		panel.add(supervisorCheckBox);
		panel.add(departmentCheckBox);
		
		//클릭되면 검색 범위, 검색항목을  보내고 그것을 쿼리한 결과를 받아서 셋테이블.
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GUI.getGUI().findQ.query(getSearchCondition(),searchRangePanel.getSearchRange());
				
			}
		});
		
		panel.add(searchButton);
		
		this.add(panel);
	}
	
	
	//체크되어있는 attr를 리스트에 담아 반환함
	public ArrayList<String> getSearchCondition() {
		ArrayList<String> result=new ArrayList<String>();
		if(nameCheckBox.isSelected()) {
			result.add(nameCheckBox.getText());
		}
		if(ssnCheckBox.isSelected()) {
			result.add(ssnCheckBox.getText());
		}
		if(bdateCheckBox.isSelected()) {
			result.add(bdateCheckBox.getText());
		}
		if(addressCheckBox.isSelected()) {
			result.add(addressCheckBox.getText());
		}
		if(sexCheckBox.isSelected()) {
			result.add(sexCheckBox.getText());
		}
		if(salaryCheckBox.isSelected()) {
			result.add(salaryCheckBox.getText());
		}
		if(supervisorCheckBox.isSelected()) {
			result.add(supervisorCheckBox.getText());
		}
		if(departmentCheckBox.isSelected()) {
			result.add(departmentCheckBox.getText());
		}
		
		
		return result;
		
	}
}
