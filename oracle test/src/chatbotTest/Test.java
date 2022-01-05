package chatbotTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "chatbotProject";
		String pw = "gusdn1234";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,id,pw);
			
			Scanner scan = new Scanner(System.in);
			System.out.print("질문 : ");
			String question = scan.nextLine();
			
			ResultSet rs=con.prepareStatement(String.format("select answer from vwqa where question = '%s'",question)).executeQuery();	
			ResultSet sizeCheck = con.prepareStatement(String.format("select count(answer) as cnt from vwqa where question = '%s'",question)).executeQuery();
			if(sizeCheck.next()) {
				int count = sizeCheck.getInt("cnt");
				System.out.println(count);
				if(!rs.next()) {
					
				}else {
					System.out.println(rs.getString("answer"));										
				}
			}
			
			
			
			// 1. 저장된 질문이 있는지 count 를 함.
			// 2. 0일 경우 멘트 출력 "적절한 대답을 찾지 못했어요ㅠㅠ 원하는 항목을 선택해 주세요"
			// 3. 선택된 answer의 seq를 변수에 저장 질문자의 seqQuestion.nextval , 질문 , 저장된 seq 를 insert함.
			// 4. 다시 질문
			// 5. 출력된 답변이 원하는것인지 항상 묻고 업데이트 해야됨.
			
			// 정리
			
			// 1. 답변 출력 > 맘에 드는지 질문 NOK 2 OK enter
			// 2. enter > 재실행 \\ 2 > 원하는 답변 선택
			
			System.out.print("질문 : ");
			question = scan.nextLine();
			
			rs=con.prepareStatement(String.format("select answer from vwqa where question = '%s'",question)).executeQuery();	
			rs.next();
			System.out.println(rs.getString("answer"));	
		    rs.close();
		    con.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
