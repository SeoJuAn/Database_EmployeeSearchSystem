package Gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TablePanel extends JPanel {
	
	public Object[][] tableData;
	JTable table = new JTable();
	ArrayList<String> header;


	public TablePanel() {
		this.add(table);
	}

	public void setTable(ArrayList<String> attr, Object[][] row) {

		this.removeAll();

		header = (ArrayList<String>) attr.clone();
		tableData=row;
		

		table = new JTable(row, header.toArray());

		table.getColumn("선택").setCellRenderer(tableCellRenderer);
		JCheckBox box = new JCheckBox();
		box.setHorizontalAlignment(JLabel.CENTER);
		table.getColumn("선택").setCellEditor(new DefaultCellEditor(box));

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(1000,700));
		this.add(scrollPane);
		
		
		GUI.getGUI().frame.revalidate();

	}

	DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer() {
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			JCheckBox box = new JCheckBox();
			box.setSelected(((Boolean) value).booleanValue());
			box.setHorizontalAlignment(JLabel.CENTER);
			return box;
		}

	};

}
