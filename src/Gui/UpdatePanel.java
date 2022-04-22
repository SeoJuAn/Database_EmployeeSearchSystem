package Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UpdatePanel extends JPanel {

	String updateOption[] = { "Address", "Sex", "Salary" };
	JComboBox comboBox = new JComboBox<String>(updateOption);

	JTextField addressJTextField = new JTextField(20);

	String Sex[] = { "F", "M" };
	JComboBox SexComboBox = new JComboBox<String>(Sex);

	// 부하직원
	JTextField salaryJTextField = new JTextField(20);


	JButton updateButton=new JButton("수정");
	
	public UpdatePanel() {
		this.add(new JLabel("수정: "));

		this.add(comboBox);

		this.add(addressJTextField);
		this.add(SexComboBox);
		this.add(salaryJTextField);
		addressJTextField.setVisible(true);
		SexComboBox.setVisible(false);
		salaryJTextField.setVisible(false);

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addressJTextField.setVisible(false);
				SexComboBox.setVisible(false);
				salaryJTextField.setVisible(false);

				if (comboBox.getSelectedItem().toString().equals("Address")) {

					addressJTextField.removeAll();
					addressJTextField.setVisible(true);

				} else if (comboBox.getSelectedItem().toString().equals("Sex")) {
					SexComboBox.setVisible(true);

				} else if (comboBox.getSelectedItem().toString().equals("Salary")) {
					salaryJTextField.removeAll();
					salaryJTextField.setVisible(true);

				}
				GUI.getGUI().frame.revalidate();
			}
		});
		
		

		this.add(updateButton);
		updateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Vector> result=new ArrayList<>();
				ArrayList<String> attr=new ArrayList<>();
					
					for(int j=1;j<GUI.getGUI().tablePanel.table.getColumnCount();j++) {
						attr.add(GUI.getGUI().tablePanel.table.getModel().getColumnName(j));
					}
				
				for(int i=0;i<GUI.getGUI().tablePanel.table.getRowCount();i++){
					if(GUI.getGUI().tablePanel.table.getModel().getValueAt(i,0).equals(true)) {
						Vector row=new Vector();
						for(int j=1;j<GUI.getGUI().tablePanel.table.getColumnCount();j++) {
							row.add(GUI.getGUI().tablePanel.table.getModel().getValueAt(i,j));
						}
						result.add(row);
					}
					
				}
				GUI.getGUI().updateQ.query(result,getUpdateCondition(),attr);
				
			}
		});

	}

	
	
	public ArrayList<String> getUpdateCondition() {
		
		ArrayList<String> result = new ArrayList<String>();
		
		// [0]엔 카테고리=> 전체, 부서, 성별 등
		result.add(comboBox.getSelectedItem().toString());
		
		// [1]엔 조건=>어느부서인지? 어느성별인지?
		if (comboBox.getSelectedItem().toString().equals("Address")) {
			result.add(addressJTextField.getText());
		} else if (comboBox.getSelectedItem().toString().equals("Sex")) {
			result.add(SexComboBox.getSelectedItem().toString());
		} else if (comboBox.getSelectedItem().toString().equals("Salary")) {
			result.add(salaryJTextField.getText());

		}

		return result;
	}
}
