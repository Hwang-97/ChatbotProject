📫ChatbotProject📫

![image](https://user-images.githubusercontent.com/85034286/148794182-2e359ec5-8c55-4b5a-946b-dcf14ee25c63.png)

>  해당 프로젝트는 닫힌 대화를 하는 챗봇 프로젝트 입니다. <br />
>  사용자가 원하는 답변이 없을때 답변 리스트에서 원하는 답변을 선택하면 해당 데이터도 누적하여 점차 더 정확한 답변을 출력하게 구현하였습니다. <br />

<br />

# 📌 Table Of Contents
* [📖 Introduction](#-introduction)
* [🔎 Detail](#-detail)
* [💡 Review](#-review)

<br />
<br />
<br />



# 📖 Introduction
### 1. 프로젝트 개요
* 해당 프로젝트는 닫힌 대화를 하는 챗봇 프로젝트 입니다.
* 사용자가 원하는 답변이 없을때 답변 리스트에서 원하는 답변을 선택하면 해당 데이터도 누적하여 점차 더 정확한 답변을 출력하게 구현하였습니다.
<br />

### 2. 개발 환경
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"><img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"> 
  <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white"> 
  <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> 
  <img src="https://img.shields.io/badge/jquery-0769AD?style=for-the-badge&logo=jquery&logoColor=white"><img src="https://img.shields.io/badge/oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white"><br/><img src="https://img.shields.io/badge/eclipse ide-2C2255?style=for-the-badge&logo=eclipseide&logoColor=white"><img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
* 해당 프로젝트는 JAVA와 ORACLE (PL/SQL)을 활용하여 제작된 프로그램 입니다.
* 개발도구로는 ORACLE Developer , Eclipse 를 사용했습니다.
* GitHub를 사용해서 형상관리를 하였습니다.
<br />

### 3. 프로젝트 내용
![image](https://user-images.githubusercontent.com/85034286/148794182-2e359ec5-8c55-4b5a-946b-dcf14ee25c63.png)
* 해당프로젝트는 닫힌 대화는 하는 챗봇 프로그램 입니다.
* 원하는 답변이 없을시 사용자에게 정해진 답변 리스트중 한가지를 선택하게 한뒤 해당 데이터 까지 누적시키는 방식으로 구현했습니다.

<br />
<br />
<br />


# 🔎 Detail
### 1. 주요 코드
* 해당 메소드는 클라이언트에게 답변을 받는 메소드며 이후 MVC 디자인 패턴으로 재 구현할 예정입니다.
    ```java
    public void startClient() {
        while(true) {
          try {
            String answer = "";

            Scanner scan = new Scanner(System.in);
            System.out.print("질문을 입력해 주세요 : ");
            String question = scan.nextLine();

            if(question.equals("0")) {
              return;
            }else {
              CallableStatement cstmt = con.prepareCall("{call getAnswer(?,?)}");
              cstmt.setString(1, question);
              cstmt.registerOutParameter(2, OracleType.VARCHAR2);
              cstmt.executeUpdate();
              answer = cstmt.getString(2);
              cstmt.close();

              if (!answer.equals("No")) {
                System.out.println(answer);
              } else {
                cstmt = con.prepareCall("{call getAnswer_keyword(?,?)}");
                cstmt.setString(1, question);
                cstmt.registerOutParameter(2, OracleType.VARCHAR2);
                cstmt.executeUpdate();
                answer = cstmt.getString(2);
                cstmt.close();
                if (!answer.equals("No")) {
                  System.out.println(answer);
                } else {
                  System.out.println("적절한 항목을 찾지 못했어요.. 아래 항목중 원하시는 답변을 선택해 주세요");
                  ResultSet select = con.prepareStatement("select seq , answer from tblAnswer").executeQuery();
                  while (select.next()) {
                    System.out.printf("%2d\t%s\r\n", select.getInt("seq"), select.getString("answer"));
                  }
                  System.out.print("원하시는 질문의 번호를 입력해 주세요 : ");
                  int seq = Integer.parseInt(scan.nextLine());
                  select.close();
                  if(seq==0) return;
                  select = con.prepareStatement("select answer from tblAnswer where seq=" + seq).executeQuery();
                  select.next();
                  System.out.println(select.getString("answer") + " 다음에는 더 완벽한 답변을 가져올께요!!");
                  con.prepareStatement(String.format("insert into tblQuestion values(seqQuestion.nextval,'%s',%d,0)",
                      question, seq)).execute();
                }
              }
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
    }
    ```
* 하위 4가지 메소드 관리자가 답변 또는 관련 키워드를 수정 삭제 추가 하는 메소드 입니다.
    ```java
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
    ```
    
<br />
<br />

# 💡 Review
### 1. 후기
* 해당 프로젝트 진행하던 시점에 a-jax와 mvc를 모르고 진했했는데 해당 기능을 추가한다면 정말 완성도 높은 프로젝트를 제작할 수있을것 같다.
* 프로젝트를 시작하며 누구나 무료로 사용가능한 개인 책봇을 만들고 싶었는데 DB 관련으로 불가능 해서 아쉬움이 남는다.

<br />
<br />

### 2. 코드 리뷰
* A-jax를 활용해서 질문시 페이지 새로 고침을 없애는것이 좋을것 같다.

<br />
<br />
<br />
