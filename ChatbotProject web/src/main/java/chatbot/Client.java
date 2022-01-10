package chatbot;

import java.sql.CallableStatement;
import java.sql.Connection;

import oracle.jdbc.OracleType;

public class Client {
	
	private Connection con;
	
	public Client(Connection con){
		this.con = con;
	}
	public Client(){
		System.out.println("plese call this Method / Method name : setConnection");
	}
	public void setConnection(Connection con) {
		this.con = con;
	}

	public String startClient(String question) {
		while(true) {
			try {
				String answer = "";
//				Scanner scan = new Scanner(System.in);
//				System.out.print("질문을 입력해 주세요 : ");
//				String question = scan.nextLine();
				
				if(question.equals("0")) {
					return "End";
				}else {
					CallableStatement cstmt = con.prepareCall("{call getAnswer(?,?)}");
					cstmt.setString(1, question);
					cstmt.registerOutParameter(2, OracleType.VARCHAR2);
					cstmt.executeUpdate();
					answer = cstmt.getString(2);
					cstmt.close();
		
					if (!answer.equals("No")) {
						return answer;
					} else {
						cstmt = con.prepareCall("{call getAnswer_keyword(?,?)}");
						cstmt.setString(1, question);
						cstmt.registerOutParameter(2, OracleType.VARCHAR2);//OracleType.VARCHAR2);
						cstmt.executeUpdate();
						answer = cstmt.getString(2);
						cstmt.close();
						if (!answer.equals("No")) {
							return answer;
						} else {
							return "No";
//							System.out.println("적절한 항목을 찾지 못했어요.. 아래 항목중 원하시는 답변을 선택해 주세요");
//							ResultSet select = con.prepareStatement("select seq , answer from tblAnswer").executeQuery();
//							while (select.next()) {
//								System.out.printf("%2d\t%s\r\n", select.getInt("seq"), select.getString("answer"));
//							}
//							System.out.print("원하시는 질문의 번호를 입력해 주세요 : ");
//							int seq = Integer.parseInt(scan.nextLine());
//							select.close();
//							if(seq==0) return "End";
//							select = con.prepareStatement("select answer from tblAnswer where seq=" + seq).executeQuery();
//							select.next();
//							System.out.println(select.getString("answer") + " 다음에는 더 완벽한 답변을 가져올께요!!");
//							con.prepareStatement(String.format("insert into tblQuestion values(seqQuestion.nextval,'%s',%d,0)",
//									question, seq)).execute();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
