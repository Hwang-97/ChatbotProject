package chatbot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Application {
	
	public static Connection con;
	
	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "chatbotProject";
		String pw = "gusdn1234";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, id, pw);
			while(true) {
				System.out.println("1 . Client"
						+ "\r\n2 . Manager"
						+ "\r\n0 . EXIT");
				System.out.print("Please select : ");
				Scanner scan= new Scanner(System.in);
				int select = Integer.parseInt(scan.nextLine());
				if(select==1) {
					Client client = new Client(con);
					client.startClient();
				}else if(select==2) {
					Manager manager = new Manager(con);
					manager.startManager();				
				}else if(select==0) {
					closeAndCommit();
					System.exit(0);
				}else {
					System.out.println("Wrong choice!!");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	private static void closeAndCommit() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Close all");
	}

}
