package Gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddPanel extends JPanel{
	
	
	
	public AddPanel() {
		
		this.setLayout(new GridLayout(20,1));
		for(int i=0;i<8;i++) {
			JLabel Label=new JLabel("");
			this.add(Label);
		}
		
		
		JLabel titleLabel=new JLabel("새로운 직원 정보 추가");
		this.add(titleLabel);
		
		JPanel panel=new JPanel();
		JLabel label=new JLabel("First Name: ");
		JTextField textField=new JTextField(20);
		panel.add(label);
		panel.add(textField);
		this.add(panel);

		JPanel panel2=new JPanel();
		JLabel label2=new JLabel("Middle init.: ");
		JTextField textField2=new JTextField(20);
		panel2.add(label2);
		panel2.add(textField2);
		this.add(panel2);
		
		JPanel panel3=new JPanel();
		JLabel label3=new JLabel("Last Name: ");
		JTextField textField3=new JTextField(20);
		panel3.add(label3);
		panel3.add(textField3);
		this.add(panel3);
		
		JPanel panel4=new JPanel();
		JLabel label4=new JLabel("Ssn: ");
		JTextField textField4=new JTextField(20);
		panel4.add(label4);
		panel4.add(textField4);
		this.add(panel4);
		
		JPanel panel5=new JPanel();
		JLabel label5=new JLabel("Birthdate: ");
		JTextField textField5=new JTextField(20);
		panel5.add(label5);
		panel5.add(textField5);
		this.add(panel5);
		
		JPanel panel6=new JPanel();
		JLabel label6=new JLabel("Address: ");
		JTextField textField6=new JTextField(20);
		panel6.add(label6);
		panel6.add(textField6);
		this.add(panel6);
		
		JPanel panel7=new JPanel();
		JLabel label7=new JLabel("Sex: ");
		String Sex[]= {"F","M"};
		JComboBox textField7=new JComboBox<String>(Sex);
		panel7.add(label7);
		panel7.add(textField7);
		this.add(panel7);
		
		JPanel panel8=new JPanel();
		JLabel label8=new JLabel("Salary: ");
		JTextField textField8=new JTextField(20);
		panel8.add(label8);
		panel8.add(textField8);
		this.add(panel8);
		
		JPanel panel9=new JPanel();
		JLabel label9=new JLabel("Super_ssn: ");
		JTextField textField9=new JTextField(20);
		panel9.add(label9);
		panel9.add(textField9);
		this.add(panel9);
		
		JPanel panel10=new JPanel();
		JLabel label10=new JLabel("Dno: ");
		JTextField textField10=new JTextField(20);
		panel10.add(label10);
		panel10.add(textField10);
		this.add(panel10);
		
		JButton button=new JButton("정보 추가하기");
		this.add(button);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Vector<String> newRow=new Vector<>();
				newRow.add(textField.getText());
				newRow.add(textField2.getText());
				newRow.add(textField3.getText());
				newRow.add(textField4.getText());
				newRow.add(textField5.getText());
				newRow.add(textField6.getText());
				newRow.add((String) textField7.getSelectedItem());
				newRow.add(textField8.getText());
				newRow.add(textField9.getText());
				newRow.add(textField10.getText());
				GUI.getGUI().addQ.query(newRow);
				
			}
		});
		
	}

}
