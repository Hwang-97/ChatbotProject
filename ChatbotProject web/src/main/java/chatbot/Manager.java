package chatbot;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

public class Manager {
	
	private Connection con;
	private Scanner scan = new Scanner(System.in);
	
	public Manager(Connection con){
		this.con = con;
	}
	public Manager(){
		System.out.println("plese call this Method / Method name : setConnection");
	}
	public void setConnection(Connection con) {
		this.con = con;
	}
	public void startManager(){
		
		System.out.println("\tManager Page");
		
		while(true) {
			System.out.print("1 . Add answer data"
					+ "\r\n2 . Add keywords related to answer"
					+ "\r\n3 . Remove answer data"
					+ "\r\n4 . Remove keywords related to answer"
					+ "\r\n0 . return to main screen"
					+ "\r\n");
			System.out.print("Please select item : ");
			scan = new Scanner(System.in);
			int select = Integer.parseInt(scan.nextLine());
			switch(select){
				case 0 :
					return;
				case 1 :
					try {
						addAnswer();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				case 2 :
					try {
						addKeyword();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				case 3 :
					try {
						removeAnswer();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				case 4 :
					try {
						removeKeyword();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
			}
		}
	}
	private void removeKeyword() throws SQLException {
		System.out.println("아래 항목중 삭제하실 키워드가 존재하는 질문을 선택해 주세요.");
		ResultSet select = con.prepareStatement("select seq , answer from tblAnswer").executeQuery();
		while (select.next()) {
			System.out.printf("%2d\t%s\r\n", select.getInt("seq"), select.getString("answer"));
		}
		System.out.print("질문의 번호를 입력해 주세요 : ");
		int seq = Integer.parseInt(scan.nextLine());
		
		System.out.println("아래 항목중 삭제하실 키워드의 번호를 선택해 주세요");
		select = con.prepareStatement("select seq , keyword from tblkeyword where answer_seq = "+seq).executeQuery();
		while (select.next()) {
			System.out.printf("%2d\t%s\r\n", select.getInt("seq"), select.getString("keyword"));
		}
		System.out.print("키워드의 번호를 입력해 주세요 : ");
		seq = Integer.parseInt(scan.nextLine());
		con.prepareStatement("delete from tblKeyword where seq="+seq).execute();
		System.out.println("키워드가 삭제되었습니다. ");
		return;
	}
	private void removeAnswer() throws SQLException{
		System.out.println("아래 항목중 삭제하실 질문을 선택해 주세요.");
		ResultSet select = con.prepareStatement("select seq , answer from tblAnswer").executeQuery();
		while (select.next()) {
			System.out.printf("%2d\t%s\r\n", select.getInt("seq"), select.getString("answer"));
		}
		System.out.print("삭제할 질문의 번호를 입력해 주세요 : ");
		int seq = Integer.parseInt(scan.nextLine());
		con.prepareStatement("delete from tblKeyword where Answer_seq="+seq).execute();
		con.prepareStatement("delete from tblAnswer where seq="+seq).execute();
		System.out.println("질문이 삭제되었습니다. ");
		return;
	}
	private void addKeyword() throws SQLException {
		try {
			System.out.println("아래 항목중 키워드를 추가하실 답변을 선택해 주세요");
			ResultSet select = con.prepareStatement("select seq , answer from tblAnswer").executeQuery();
			while (select.next()) {
				System.out.printf("%2d\t%s\r\n", select.getInt("seq"), select.getString("answer"));
			}
			System.out.print("키워드를 추가할 질문의 번호를 입력해 주세요 : ");
			int seq = Integer.parseInt(scan.nextLine());
			System.out.print("추가하고 싶은 키워드 입력해 주세요 : ");
			String keyword = scan.nextLine();
			select.close();
			con.prepareStatement("insert into tblKeyword values(seqKeyword.nextval,'"+keyword+"',"+seq+")").execute();
			System.out.println("키워드를 추가하였습니다. ");
		}catch(SQLIntegrityConstraintViolationException e) {
			System.out.println("이미 선택된 질문에 동일한 키워드가 등록되어 있거나 번호를 잘못 입력하였습니다.");
		}
		return;
	}
	private void addAnswer() throws SQLException {
		System.out.print("추가하고 싶은 답변을 입력해 주세요 : ");
		String answer = scan.nextLine();
		try {
			con.prepareStatement("insert into tblAnswer values(seqAnswer.nextval,'"+answer+"')").execute();
			System.out.println("답변을 추가하였습니다. ");
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("이미 존재하는 답변인지 확인해 주세요.");
		}
		return;
	}

}
