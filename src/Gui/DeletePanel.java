package Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class DeletePanel extends JPanel {

	JButton deleteButton=new JButton("선택한 데이터 삭제");
	public DeletePanel() {

		this.add(deleteButton);
		deleteButton.addActionListener(new ActionListener() {
			
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
				GUI.getGUI().deleteQ.query(result,attr);
				
			}
		});
	}

	
}
