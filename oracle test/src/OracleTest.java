import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleTest {

	public static void main(String[] args) {
		//드라이버 로드
		
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement psmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String id = "chatbotProject";
			String pw = "gusdn1234";
			System.out.println("DB정상연결");
			try {
				con = DriverManager.getConnection(url,id,pw);
				System.out.println("DB계정일치");
			} catch (SQLException e) {
				System.out.println("DB계정불일치");
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			System.out.println("DB연결실패");
			e.printStackTrace();
		}
		try {
			String test = "select * from tblTest";
			psmt = con.prepareStatement(test);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getInt("seq")+rs.getString("name")+rs.getInt("num"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
