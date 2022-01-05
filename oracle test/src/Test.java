import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {

	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String id = "chatbotProject";
			String pw = "gusdn1234";
			try {
				
				Connection con = DriverManager.getConnection(url,id,pw);
				OracleDmlTool dml = new OracleDmlTool();
				ResultSet rs = dml.select(con, "*","seq=3", "tblTest");
				while(rs.next()) {
					System.out.println(rs.getInt("seq")+rs.getString("name")+rs.getInt("num"));
				}
				dml.update(con, id, pw);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
