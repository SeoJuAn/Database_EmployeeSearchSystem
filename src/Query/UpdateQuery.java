package Query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import Gui.GUI;

//4번
public class UpdateQuery {
	public void query(ArrayList<Vector> result,ArrayList<String> updateCondition, ArrayList<String> attr) {
		//[Joyce, A, English, 453453453, 1972-07-31, 5631 Rice, Houston, TX, F, 25000.00, 333445555, 5] 식으로 받습니다
		//맨처음 검색하고 체크한후에 수정버튼 누르면 이쿼리가 작동됩니다.
		/*for(Vector r:result) {
			System.out.println(r.toString());
		}
		//adress 뭘로 업데이트 할것인지? 
		//adress, 입력값 으로 반환
		for(String s:updateCondition) {
			System.out.println(s);
		}


		for (String s : attr) {
			System.out.println(s);
		}*/

		String url = "jdbc:mysql://localhost:3306/COMPANY?serverTimezone=UTC";
		String user = "root";
		String pwd = "2580";
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, pwd);

			//jdbc 쿼리부분
			//인자로 받은 조건들로 쿼리 작성해서 넣으면 됩니다.
			String ssn = result.get(0).get(1).toString();
			String updateCategory = updateCondition.get(0);
			String updateAttribute = updateCondition.get(1);
			
			if(updateCategory.equals("Address")) {
				String SQL = "update employee set Address=? where ssn=?;";
				PreparedStatement p=con.prepareStatement(SQL);
				p.clearParameters();
				p.setString(1, updateAttribute);
				p.setString(2, ssn);
				p.executeUpdate();
			}
			else if(updateCategory.equals("Sex")) {
				String SQL = "update employee set Sex=? where ssn=?;";
				PreparedStatement p=con.prepareStatement(SQL);
				p.clearParameters();
				p.setString(1, updateAttribute);
				p.setString(2, ssn);
				p.executeUpdate();
			}
			else if(updateCategory.equals("Salary")) {
				String SQL = "update employee set Salary=? where ssn=?;";
				PreparedStatement p=con.prepareStatement(SQL);
				p.clearParameters();
				p.setString(1, updateAttribute);
				p.setString(2, ssn);
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
