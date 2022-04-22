package Query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;

import Gui.GUI;
import java.util.regex.Pattern;
//5번
public class AddQuery {

	//gui창에 보이는 순서대로 들어옵니다
	public void query(Vector<String> newRow) {
		for(String r:newRow) {
			System.out.println(r.toString());
		}

		String url = "jdbc:mysql://localhost:3306/COMPANY?serverTimezone=UTC";
		String user = "root";
		String pwd = "2580";
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, pwd);
			int additionable = 1;
			//jdbc 쿼리부분
			//인자로 받은 조건들로 쿼리 작성해서 넣으면 됩니다.
			
			//Exception handling
			boolean Fname_check = Pattern.matches("[a-zA-Z가-힣]+", newRow.get(0));
			boolean minit_check = Pattern.matches("([A-Z]|\\s?)", newRow.get(1));
			boolean Lname_check = Pattern.matches("[a-zA-Z가-힣]+", newRow.get(2));
			if(Fname_check == false ) {
				JOptionPane.showMessageDialog(null, "Fname error.","경고",JOptionPane.WARNING_MESSAGE);
				con.close();
				return;
			}
			else if( minit_check == false) {
				JOptionPane.showMessageDialog(null, "minit error. minit is upper or nothing","경고",JOptionPane.WARNING_MESSAGE);
				con.close();
				return;
			}
			else if(Lname_check == false) {
				JOptionPane.showMessageDialog(null, "Lname error.","경고",JOptionPane.WARNING_MESSAGE);
				con.close();
				return;
			}
			
			String Exception_SQL = "select ssn from employee;";
			PreparedStatement Exception_p=con.prepareStatement(Exception_SQL);
			Exception_p.clearParameters();
			ResultSet r = Exception_p.executeQuery();
			boolean ssn_check = Pattern.matches("\\d{9}$", newRow.get(3));
			if(ssn_check == false) {
				JOptionPane.showMessageDialog(null, "Ssn is not complete. (ssn has 9 length).","경고",JOptionPane.WARNING_MESSAGE);
				additionable=0;
				con.close();
				return;
			}
			boolean superssn_check = Pattern.matches("(\\d{9})?$", newRow.get(8));
			if(superssn_check == false) {
				JOptionPane.showMessageDialog(null, "Super_Ssn is strange. (Super_ssn has 9 length or nothing).","경고",JOptionPane.WARNING_MESSAGE);
				additionable=0;
				con.close();
				return;
			}
			boolean superSsnExist = false;
			while(r.next()) {
				if(r.getString(1).equals(newRow.get(3))) {
					JOptionPane.showMessageDialog(null, "Duplicated Ssn.","경고",JOptionPane.WARNING_MESSAGE);
					//System.out.println("Duplicated Ssn.");
					con.close();
					additionable=0;
					return;
					
				}
				else if(newRow.get(3).equals("")) {
					JOptionPane.showMessageDialog(null, "Ssn is null.","경고",JOptionPane.WARNING_MESSAGE);
					//System.out.println("Ssn is null");
					additionable=0;
					con.close();
					return;
				}
				if(r.getString(1).equals(newRow.get(8)) || newRow.get(8).equals("")) {
					superSsnExist = true;
				}
			}
			if(superSsnExist == false) {
				JOptionPane.showMessageDialog(null, "Super_Ssn is not exist.","경고",JOptionPane.WARNING_MESSAGE);
				//System.out.println("Super_Ssn is not exist.");
				additionable=0;
				con.close();
				return;
			}
			
			boolean birth_check = Pattern.matches("(19|20)\\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$", newRow.get(4));
			
			if(birth_check == false) {
				JOptionPane.showMessageDialog(null, "birth date error.","경고",JOptionPane.WARNING_MESSAGE);
				con.close();
				return;
			}
			
			boolean salary_check = Pattern.matches("^[0-9]+$", newRow.get(7));
			if(salary_check == false) {
				JOptionPane.showMessageDialog(null, "salary error.","경고",JOptionPane.WARNING_MESSAGE);
				con.close();
				return;
			}
			
			String Exception_Dno = "select Dnumber From DEPARTMENT";
			Statement stmt = con.createStatement();
			r = stmt.executeQuery(Exception_Dno);
			boolean existDno = false;
			while(r.next()) {
				if(r.getString(1).equals(newRow.get(9))) {
					existDno = true;
				}
			}
			if(existDno == false) {
				JOptionPane.showMessageDialog(null, "Dno is not exist.","경고",JOptionPane.WARNING_MESSAGE);
				con.close();
				return;
			}
			
		
			if(additionable==1){
				String SQL = "insert into employee values(?,?,?,?,?,?,?,?,?,?);";
				PreparedStatement p=con.prepareStatement(SQL);
				p.clearParameters();
				p.setString(1, newRow.get(0));
				p.setString(2, newRow.get(1));
				p.setString(3, newRow.get(2));
				p.setString(4, newRow.get(3));
				p.setString(5, newRow.get(4));
				p.setString(6, newRow.get(5));
				p.setString(7, newRow.get(6));
				p.setInt(8, Integer.parseInt(newRow.get(7)));
				p.setString(9, newRow.get(8));
				p.setString(10, newRow.get(9));
				p.executeUpdate();
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//GUI.getGUI().searchAttrCheckBoxPanel.searchButton.doClick();
	

	}

}
