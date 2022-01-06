package algorithm;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class AlgorithmTest {

	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "chatbotProject";
		String pw = "gusdn1234";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,id,pw);
			String s="";
			CallableStatement cstmt= con.prepareCall("{getAnswer_keyword(?,?)}");
			cstmt.setString(1, "안녕하세요 고객센터 연락처좀(전화) 알려주시겠어요?");
			cstmt.registerOutParameter(2, Types.VARCHAR);
			cstmt.executeUpdate();
			
			System.out.println(cstmt.getString(2));
			
			/*Scanner scan = new Scanner(System.in);
			System.out.print("질문 : ");
			String question = scan.nextLine().trim();
			// 기본적으로 명확한 답변이 있다면 연결 하지만 없다면 토큰 확인으로 의도 파악 
			// 고객이 매칭 등록 가능 
			
			// 사용자가 질문하면 매칭되는 모든 데이터중 가장 선택이 많이된 답변을 불러옴
			ResultSet rs=con.prepareStatement(String.format("select answer,cnt from vwqa where question = '%s'",question)).executeQuery();
			Map<String,Integer> map = new HashMap<String,Integer>();
			while(rs.next()) {
				map.put(rs.getString("answer"), rs.getInt("cnt"));
			}
			int max=-1;
			for(String s :map.keySet()) {
				if(map.get(s)>max) {
					max = map.get(s);
				}
			}
			String answer ="";
			for(String s :map.keySet()) {
				if(map.get(s)==max) {
					answer=s;
				}
			}
			
			// 원하는 질문이 아닐떄 사용자가 생각했던 질문 선택
			rs=con.prepareStatement("select * from tblAnswer").executeQuery();
			while(rs.next()) {
				System.out.printf("%d . 질문 : %s\r\n",rs.getInt("seq"),rs.getString("answer"));
			}
			System.out.print("원하는 질문이 없을시 원하시는 질문의 번호를 입력해주세요 : ");
			int number = scan.nextInt();
			scan.nextLine();
			rs=con.prepareStatement("select * from tblAnswer where seq="+number).executeQuery();
			rs.next();
			System.out.println(rs.getString("answer"));
			
			//
			for(String s : map.keySet()) {
				if(s.equals(answer)) {
					
				}
				
			}*/
			
			
//		    rs.close();
		    con.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
