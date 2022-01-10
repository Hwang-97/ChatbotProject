package chatbot;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtill {
	public static Connection open() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "chatbotProject";
		String pw = "gusdn1234";
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, id, pw);
			return con;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
