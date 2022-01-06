package chatbotTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
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
			String question = scan.nextLine().trim();
			
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
			
			
			rs=con.prepareStatement(String.format("select answer,cnt from vwqa where question = '%s'",question)).executeQuery();
			System.out.println(answer);
			
			
		    rs.close();
		    con.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}