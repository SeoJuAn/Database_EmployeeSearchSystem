package Query;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Gui.GUI;

//1,2번
public class FindQuery {
	//public static ArrayList<ArrayList<String>> AllRowData=new ArrayList<>();

	public void query(ArrayList<String> checkedAttr, ArrayList<String> searchRange) {
		//Bdate, Address 등 선택된 attr반환합니다 전체일경우 전체 전체 반환
		/*
		System.out.println("=============checkedAttr==================");
		for(String s:checkedAttr) {
			System.out.println(s);
		}
		
		//ex)성별, F
		// [0]엔 카테고리=> 전체, 부서, 성별 등
		// [1]엔 조건=>어느부서인지? 어느성별인지?
		System.out.println("=============searchRange==================");
		for(String s:searchRange) {
			System.out.println(s);
		}
		*/
		//연결부분
		String url = "jdbc:mysql://localhost:3306/COMPANY?serverTimezone=UTC";
		String user = "root";
		String pwd = "2580";
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, pwd);

			//jdbc 쿼리부분
			//인자로 받은 조건들로 쿼리만 작성해서 넣으면 됩니다.
			// 인자에 맞춰서 SELECT문 추가 과정, 필요한 표를 선별하기 위해서 boolean변수 사용
			String[] Attribute = {"CONCAT(E.Fname, ' ', E.minit, ' ', E.Lname) NAME", "E.Ssn", "E.Bdate", "E.Address", "E.Sex", "E.Salary", "CONCAT(S.fname, ' ', S.minit, ' ', S.Lname) Supervisor", " Dname AS DEPARTMENT"};
			String SQL = "SELECT ";
			boolean employee = false, supervisor = false, department = false;
			int att = 0;
			while(att < checkedAttr.size()) {
				switch(checkedAttr.get(att)) {
				case "Name":
					if(att == 0)
						SQL += (Attribute[0] + " ");
					else
						SQL += ("," + Attribute[0] + " ");
					employee = true;
					break;
				case "Ssn":
					if(att == 0)
						SQL += (Attribute[1] + " ");
					else
						SQL += ("," + Attribute[1] + " ");
					employee = true;
					break;
				case "Bdate":
					if(att == 0)
						SQL += (Attribute[2] + " ");
					else
						SQL += ("," + Attribute[2] + " ");
					employee = true;
					break;
				case "Address":
					if(att == 0)
						SQL += (Attribute[3] + " ");
					else
						SQL += ("," + Attribute[3] + " ");
					employee = true;
					break;
				case "Sex":
					if(att == 0)
						SQL += (Attribute[4] + " ");
					else
						SQL += ("," + Attribute[4] + " ");
					employee = true;
					break;
				case "Salary":
					if(att == 0)
						SQL += (Attribute[5] + " ");
					else
						SQL += ("," + Attribute[5] + " ");
					employee = true;
					break;
				case "Supervisor":
					if(att == 0)
						SQL += (Attribute[6] + " ");
					else
						SQL += ("," + Attribute[6] + " ");
					supervisor = true;
					break;
				case "Department":
					if(att == 0)
						SQL += (Attribute[7] + " ");
					else
						SQL += ("," + Attribute[7] + " ");
					department = true;
					break;
				}
				att++;
			}
			
			// 필요한 표 별로 나눠서 FROM절 추가
			if((supervisor == true && department == true)) {
				String from = " FROM EMPLOYEE E LEFT JOIN EMPLOYEE S ON E.Super_Ssn = S.Ssn JOIN DEPARTMENT ON E.Dno = Dnumber ";
				SQL += from;
			}
			else if(department == true && supervisor == false) {
				String from = " FROM EMPLOYEE E JOIN DEPARTMENT ON E.Dno = Dnumber ";
				SQL += from;
			}
			else if(supervisor == true && department == false) {
				String from = " FROM EMPLOYEE E LEFT JOIN EMPLOYEE S ON E.Super_Ssn = S.Ssn ";
				SQL += from;
			}
			else if(employee == false && department == false && supervisor == false) {
				JOptionPane.showMessageDialog(null, "최소 1개 이상의 속성을 체크해주세요.","경고",JOptionPane.WARNING_MESSAGE);
				con.close();
				return;
			}
			else {
				String from = "FROM EMPLOYEE E ";
				SQL += from;
			}
			
			//찾는 조건에 따라서 WHERE절 추가
			boolean findAll = false;
			boolean findSuper = false;
			switch(searchRange.get(0)) {
			case "전체":
				findAll = true;
				break;
			case "부서":
				if(department == true)
					SQL += ("WHERE Dname = ?");
				else
					SQL += ("JOIN DEPARTMENT ON E.Dno = Dnumber WHERE Dname = ?");
				break;
			case "성별":
				SQL += "WHERE E.Sex = ?";
				break;
			case "연봉":
				SQL += "WHERE E.Salary > ?";
				break;
			case "생일":
				SQL += "WHERE MONTH(E.Bdate) = ?";
				break;
			case "부하직원":
				findSuper = true;
				if(supervisor)
					SQL += "WHERE CONCAT(S.FNAME, ' ', S.MINIT, ' ', S.LNAME) LIKE ?";
				else
					SQL += ("JOIN EMPLOYEE S ON E.Super_Ssn = S.Ssn WHERE CONCAT(S.FNAME, ' ', S.MINIT, ' ', S.LNAME) LIKE ?");
				break;	
			}
			
			//System.out.println(SQL);
			
			//ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE 꼭 넣어야함
			PreparedStatement p = con.prepareStatement(SQL,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			// 전체가 아니면 그냥 진행, 조건이 있다면 거기에 맞게 ?에 문자열 입력
			if (findAll == false) {
				p.clearParameters();
				if(findSuper) {
					p.setString(1, "%" + searchRange.get(1) + "%");
				}
				else {
					p.setString(1, searchRange.get(1));
				}
			}
			
			//스키마 얻어오기 위해
			ResultSetMetaData rsmd = p.getMetaData();
			ResultSet r = p.executeQuery();


			
			
			//이 아래로는볼필요없음
			
			
			r.last();
			int rowCount=r.getRow();
			r.beforeFirst();
			int columnCount = rsmd.getColumnCount();
			
			
			//컬럼 리스트
			ArrayList<String> col = new ArrayList<>();
			//로우 리스트. 
			Object[][] rows = new Object[rowCount][columnCount+1];
			

			//AllRowData = new ArrayList<>();

			//컬럼추출(스키마)
			for (int i = 1; i <= columnCount; i++) {
				String name = rsmd.getColumnName(i);
				col.add(name);
				
				//System.out.println(name);
			}
			
			//체크박스를위해 0번째 컬럼에 선택컬럼 추가
			col.add(0, "선택");

			//로우추출
			int Count = 0;
			while (r.next()) {
				
				//체크박스를위해 0번째 컬럼에 false 대입
				rows[Count][0]=false;
				
				//ArrayList<String> AllRowDataElement=new ArrayList<String>();
				for (int colCount = 1; colCount <= columnCount; colCount++) {
					rows[Count][colCount] = r.getString(colCount);
					//AllRowDataElement.add(r.getString(colCount));
					//System.out.println(rows[Count][colCount]);
				}
				Count++;
				//AllRowData.add(AllRowDataElement);
			}
			GUI.getGUI().tablePanel.setTable(col, rows);

			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
