package Query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import Gui.GUI;

//3번
public class DeleteQuery {

	PreparedStatement p = null;

	public void query(ArrayList<Vector> result, ArrayList<String> attr) {

		//[Joyce, A, English, 453453453, 1972-07-31, 5631 Rice, Houston, TX, F, 25000.00, 333445555, 5] 식으로 받습니다
		//맨처음 검색하고 체크한후에 삭제버튼 누르면 이쿼리가 작동됩니다.

		for (Vector r : result) {
			//System.out.println(r.toString());
		}

		for (String s : attr) {
			//System.out.println(s);
		}
		//System.out.println(attr.indexOf("Ssn"));

		String url = "jdbc:mysql://localhost:3306/COMPANY?serverTimezone=UTC";
		String user = "root";
		String pwd = "2580";
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, pwd);

			//ssn이 있을때
			if (attr.contains("Ssn")) {
				ArrayList<String> ssn = new ArrayList<String>();

				for (int i = 0; i < result.size(); i++) {
					ssn.add((String) result.get(i).get(attr.indexOf("Ssn")));
					//System.out.println((String) result.get(i).get(attr.indexOf("Ssn")));
				}


				for (String j : ssn) {
					p = con.prepareStatement("Update EMPLOYEE SET Super_ssn = NULL where Super_ssn = ?");
					p.setString(1, j.trim());
					p.executeUpdate();
					p = con.prepareStatement("Delete from EMPLOYEE where ssn = ? ");
					p.setString(1, j.trim());

					p.executeUpdate();
				}
			}else {
				JOptionPane.showMessageDialog(null, "조회된 Ssn이 없을경우 동일한 값을 가진 다른 데이터들이 지워질 수 있으므로 삭제하실수 없습니다.","경고",JOptionPane.WARNING_MESSAGE);
				
			}

			//jdbc 쿼리부분
			//인자로 받은 조건들로 쿼리 작성해서 넣으면 됩니다.

			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// GUI.getGUI().searchAttrCheckBoxPanel.searchButton.doClick();
	}

}
