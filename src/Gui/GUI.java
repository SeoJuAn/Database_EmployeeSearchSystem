package Gui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Query.AddQuery;
import Query.DeleteQuery;
import Query.FindQuery;
import Query.UpdateQuery;

public class GUI {
	
	public static GUI GUI_INSTANCE=null;
	
	public static GUI makeGUI() {
		if (GUI_INSTANCE==null) {
			GUI_INSTANCE=new GUI();
		}
		return GUI_INSTANCE;
	}
	
	public static GUI getGUI() {
		if (GUI_INSTANCE==null) {
			GUI_INSTANCE=new GUI();
		}
		return GUI_INSTANCE;
	}
	
	public AddQuery addQ=new AddQuery();
	public DeleteQuery deleteQ=new DeleteQuery();
	public FindQuery findQ=new FindQuery();
	public UpdateQuery updateQ=new UpdateQuery();
	
	
	public MainFrame frame=new MainFrame("Information Retrival System",1500,1000);
	
	public TablePanel tablePanel=new TablePanel();
	
	public UpdatePanel updatePanel=new UpdatePanel();
	
	public DeletePanel deletePanel=new DeletePanel();
	
	public AddPanel addPanel=new AddPanel();
	
	public SearchAttrCheckBoxPanel searchAttrCheckBoxPanel=new SearchAttrCheckBoxPanel();

	public GUI() {
		
		frame.setLayout(new BorderLayout());
		
		
		frame.add(searchAttrCheckBoxPanel, BorderLayout.NORTH);
		//검색범위 -부서-성별-연봉:  입력한숫자보다연봉이높은직원검색   -생일:  1월~12월선택하면해당월이생월인직원검색    -부하직원:  입력한직원의부하직원검색
		//검색범위 서브, 입력가능 or 고정값 선택
		
		//검색항목 -attr
		
			//검색항목 검색 버튼
		
		//db테이블
		tablePanel.setBounds(100, 100, 300, 300);
		
		frame.add(tablePanel, BorderLayout.CENTER);
			//헤더, 선택 체크박스
		
		//현재 선택한 직원-필수아님
		//현재 선택된 인원수-필수아님
		

		//새로운 직원 추가
		frame.add(addPanel, BorderLayout.EAST);
		
		
		JPanel southPanel=new JPanel();
		
		southPanel.setLayout(new GridLayout(1,3));
		//수정
			//수정 항목
		
		southPanel.add(updatePanel);
		
		//직원 선택 후 삭제
		southPanel.add(deletePanel);
		
		
		frame.add(southPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}

	void refresh() {
		
	}
	
}
