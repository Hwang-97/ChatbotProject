π«ChatbotProjectπ«

![image](https://user-images.githubusercontent.com/85034286/148794182-2e359ec5-8c55-4b5a-946b-dcf14ee25c63.png)

>  ν΄λΉ νλ‘μ νΈλ λ«ν λνλ₯Ό νλ μ±λ΄ νλ‘μ νΈ μλλ€. <br />
>  μ¬μ©μκ° μνλ λ΅λ³μ΄ μμλ λ΅λ³ λ¦¬μ€νΈμμ μνλ λ΅λ³μ μ ννλ©΄ ν΄λΉ λ°μ΄ν°λ λμ νμ¬ μ μ°¨ λ μ νν λ΅λ³μ μΆλ ₯νκ² κ΅¬ννμμ΅λλ€. <br />

<br />

# π Table Of Contents
* [π Introduction](#-introduction)
* [π Detail](#-detail)
* [π‘ Review](#-review)

<br />
<br />
<br />



# π Introduction
### 1. νλ‘μ νΈ κ°μ
* ν΄λΉ νλ‘μ νΈλ λ«ν λνλ₯Ό νλ μ±λ΄ νλ‘μ νΈ μλλ€.
* μ¬μ©μκ° μνλ λ΅λ³μ΄ μμλ λ΅λ³ λ¦¬μ€νΈμμ μνλ λ΅λ³μ μ ννλ©΄ ν΄λΉ λ°μ΄ν°λ λμ νμ¬ μ μ°¨ λ μ νν λ΅λ³μ μΆλ ₯νκ² κ΅¬ννμμ΅λλ€.
<br />

### 2. κ°λ° νκ²½
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"><img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"> 
  <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white"> 
  <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> 
  <img src="https://img.shields.io/badge/jquery-0769AD?style=for-the-badge&logo=jquery&logoColor=white"><img src="https://img.shields.io/badge/oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white"><br/><img src="https://img.shields.io/badge/eclipse ide-2C2255?style=for-the-badge&logo=eclipseide&logoColor=white"><img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
* ν΄λΉ νλ‘μ νΈλ JAVAμ ORACLE (PL/SQL)μ νμ©νμ¬ μ μλ νλ‘κ·Έλ¨ μλλ€.
* κ°λ°λκ΅¬λ‘λ ORACLE Developer , Eclipse λ₯Ό μ¬μ©νμ΅λλ€.
* GitHubλ₯Ό μ¬μ©ν΄μ νμκ΄λ¦¬λ₯Ό νμμ΅λλ€.
<br />

### 3. νλ‘μ νΈ λ΄μ©
![image](https://user-images.githubusercontent.com/85034286/148794182-2e359ec5-8c55-4b5a-946b-dcf14ee25c63.png)
* ν΄λΉνλ‘μ νΈλ λ«ν λνλ νλ μ±λ΄ νλ‘κ·Έλ¨ μλλ€.
* μνλ λ΅λ³μ΄ μμμ μ¬μ©μμκ² μ ν΄μ§ λ΅λ³ λ¦¬μ€νΈμ€ νκ°μ§λ₯Ό μ ννκ² νλ€ ν΄λΉ λ°μ΄ν° κΉμ§ λμ μν€λ λ°©μμΌλ‘ κ΅¬ννμ΅λλ€.

<br />
<br />
<br />


# π Detail
### 1. μ£Όμ μ½λ
* ν΄λΉ λ©μλλ ν΄λΌμ΄μΈνΈμκ² λ΅λ³μ λ°λ λ©μλλ©° μ΄ν MVC λμμΈ ν¨ν΄μΌλ‘ μ¬ κ΅¬νν  μμ μλλ€.
    ```java
    public void startClient() {
        while(true) {
          try {
            String answer = "";

            Scanner scan = new Scanner(System.in);
            System.out.print("μ§λ¬Έμ μλ ₯ν΄ μ£ΌμΈμ : ");
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
                  System.out.println("μ μ ν ν­λͺ©μ μ°Ύμ§ λͺ»νμ΄μ.. μλ ν­λͺ©μ€ μνμλ λ΅λ³μ μ νν΄ μ£ΌμΈμ");
                  ResultSet select = con.prepareStatement("select seq , answer from tblAnswer").executeQuery();
                  while (select.next()) {
                    System.out.printf("%2d\t%s\r\n", select.getInt("seq"), select.getString("answer"));
                  }
                  System.out.print("μνμλ μ§λ¬Έμ λ²νΈλ₯Ό μλ ₯ν΄ μ£ΌμΈμ : ");
                  int seq = Integer.parseInt(scan.nextLine());
                  select.close();
                  if(seq==0) return;
                  select = con.prepareStatement("select answer from tblAnswer where seq=" + seq).executeQuery();
                  select.next();
                  System.out.println(select.getString("answer") + " λ€μμλ λ μλ²½ν λ΅λ³μ κ°μ Έμ¬κ»μ!!");
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
* νμ 4κ°μ§ λ©μλ κ΄λ¦¬μκ° λ΅λ³ λλ κ΄λ ¨ ν€μλλ₯Ό μμ  μ­μ  μΆκ° νλ λ©μλ μλλ€.
    ```java
      private void removeKeyword() throws SQLException {
          System.out.println("μλ ν­λͺ©μ€ μ­μ νμ€ ν€μλκ° μ‘΄μ¬νλ μ§λ¬Έμ μ νν΄ μ£ΌμΈμ.");
          ResultSet select = con.prepareStatement("select seq , answer from tblAnswer").executeQuery();
          while (select.next()) {
            System.out.printf("%2d\t%s\r\n", select.getInt("seq"), select.getString("answer"));
          }
          System.out.print("μ§λ¬Έμ λ²νΈλ₯Ό μλ ₯ν΄ μ£ΌμΈμ : ");
          int seq = Integer.parseInt(scan.nextLine());

          System.out.println("μλ ν­λͺ©μ€ μ­μ νμ€ ν€μλμ λ²νΈλ₯Ό μ νν΄ μ£ΌμΈμ");
          select = con.prepareStatement("select seq , keyword from tblkeyword where answer_seq = "+seq).executeQuery();
          while (select.next()) {
            System.out.printf("%2d\t%s\r\n", select.getInt("seq"), select.getString("keyword"));
          }
          System.out.print("ν€μλμ λ²νΈλ₯Ό μλ ₯ν΄ μ£ΌμΈμ : ");
          seq = Integer.parseInt(scan.nextLine());
          con.prepareStatement("delete from tblKeyword where seq="+seq).execute();
          System.out.println("ν€μλκ° μ­μ λμμ΅λλ€. ");
          return;
      }
      private void removeAnswer() throws SQLException{
        System.out.println("μλ ν­λͺ©μ€ μ­μ νμ€ μ§λ¬Έμ μ νν΄ μ£ΌμΈμ.");
        ResultSet select = con.prepareStatement("select seq , answer from tblAnswer").executeQuery();
        while (select.next()) {
          System.out.printf("%2d\t%s\r\n", select.getInt("seq"), select.getString("answer"));
        }
        System.out.print("μ­μ ν  μ§λ¬Έμ λ²νΈλ₯Ό μλ ₯ν΄ μ£ΌμΈμ : ");
        int seq = Integer.parseInt(scan.nextLine());
        con.prepareStatement("delete from tblKeyword where Answer_seq="+seq).execute();
        con.prepareStatement("delete from tblAnswer where seq="+seq).execute();
        System.out.println("μ§λ¬Έμ΄ μ­μ λμμ΅λλ€. ");
        return;
      }
      private void addKeyword() throws SQLException {
        try {
          System.out.println("μλ ν­λͺ©μ€ ν€μλλ₯Ό μΆκ°νμ€ λ΅λ³μ μ νν΄ μ£ΌμΈμ");
          ResultSet select = con.prepareStatement("select seq , answer from tblAnswer").executeQuery();
          while (select.next()) {
            System.out.printf("%2d\t%s\r\n", select.getInt("seq"), select.getString("answer"));
          }
          System.out.print("ν€μλλ₯Ό μΆκ°ν  μ§λ¬Έμ λ²νΈλ₯Ό μλ ₯ν΄ μ£ΌμΈμ : ");
          int seq = Integer.parseInt(scan.nextLine());
          System.out.print("μΆκ°νκ³  μΆμ ν€μλ μλ ₯ν΄ μ£ΌμΈμ : ");
          String keyword = scan.nextLine();
          select.close();
          con.prepareStatement("insert into tblKeyword values(seqKeyword.nextval,'"+keyword+"',"+seq+")").execute();
          System.out.println("ν€μλλ₯Ό μΆκ°νμμ΅λλ€. ");
        }catch(SQLIntegrityConstraintViolationException e) {
          System.out.println("μ΄λ―Έ μ νλ μ§λ¬Έμ λμΌν ν€μλκ° λ±λ‘λμ΄ μκ±°λ λ²νΈλ₯Ό μλͺ» μλ ₯νμμ΅λλ€.");
        }
        return;
      }
      private void addAnswer() throws SQLException {
        System.out.print("μΆκ°νκ³  μΆμ λ΅λ³μ μλ ₯ν΄ μ£ΌμΈμ : ");
        String answer = scan.nextLine();
        try {
          con.prepareStatement("insert into tblAnswer values(seqAnswer.nextval,'"+answer+"')").execute();
          System.out.println("λ΅λ³μ μΆκ°νμμ΅λλ€. ");
        } catch (SQLIntegrityConstraintViolationException e) {
          System.out.println("μ΄λ―Έ μ‘΄μ¬νλ λ΅λ³μΈμ§ νμΈν΄ μ£ΌμΈμ.");
        }
        return;
      }
    ```
* ν€μλκ° κ°μ₯ λ§μ΄ λ±μ₯ν answer κ°μ Έμ€λ νλ‘μμ  μ§λ¬Έμ λ°κ³  ν΄λΉ μ€νΈλ§μ tblKeyword κ° λ€μ΄μλμ§ νμΈ λ€μ΄μλ€λ©΄ answer seq λ°ν
    ```
        create or replace Procedure getAnswer_keyword(
            Pquestion in varchar2,
            Panswer out varchar2
        )
        is
            vcount number:=0;
            vprecount number:=0;
            vindex number:=1;
            vkeyword varchar2(4000);
            vanswer_seq number;
            keyword1 varchar2(4000);
            keyword2 varchar2(4000);
            keyword3 varchar2(4000);
            cursor vcursor is select keyword , answer_seq from tblkeyword order by answer_seq;
        begin
            Panswer :='No';
            open vcursor;
                loop
                    fetch vcursor into vkeyword,vanswer_seq;
                    exit when vcursor%notfound;
                    if vanswer_seq=vindex then -- κ° seqμ λ§κ² νμΈν¨.
                        if REGEXP_LIKE(Pquestion, vkeyword)then
                            vcount:=vcount+1;
                        end if;
                        if vprecount < vcount then
                            select answer into Panswer from tblAnswer where vanswer_seq = seq;
                        end if;
                    else
                        vindex:=vindex+1;
                        vprecount:=vcount;
                        vcount:=0;
                        if REGEXP_LIKE(Pquestion, vkeyword)then-- else μμ μΈλ±μ€λ₯Ό μ¬λ¦¬κ³  μ€ννλ©΄ νλ²μ νμ΄ κ±΄λ λ°μ΄μ§κΈ°μ elseμμ νλ²λ§ κ²μ¬
                            vcount:=vcount+1;
                        end if;
                        if vprecount < vcount then
                            select answer into Panswer from tblAnswer where vanswer_seq = seq;
                        end if;
                    end if;
                end loop;
            close vcursor;
        Exception
            when No_Data_found
                then Panswer:='No';
        end;
        -- μνλ λ΅μ΄ μλ³μλ μ¬μ©μκ° answer μΆλ ₯
        select * from tblAnswer;
        -- μνλ λ΅μ΄ μλ³μλ μ¬μ©μκ° answerμ μ ννλ©΄ μ¬μ©μκ° λ¨κ²Όλ μ§λ¬Έκ³Ό μ νλ answer λ²νΈλ₯Ό insert νλ μΏΌλ¦¬
        insert into tblQuestion values(seqQuestion.nextval,'μ΄κ±΄ μ μμ£ ?? μ λ μΈμ¬ν κ±°μμ!',1,0);

        declare
            vm varchar2(4000);
        begin
            getAnswer('μλ',vm);
        --    getAnswer_keyword('μλνμΈμ κ³ κ°μΌν° μ°λ½μ²μ’(μ ν) μλ €μ£Όμκ² μ΄μ?',vm);
            dbms_output.put_line(vm);
        end;

        commit;

        ```
    
<br />
<br />

# π‘ Review
### 1. νκΈ°
* ν΄λΉ νλ‘μ νΈ μ§ννλ μμ μ a-jaxμ mvcλ₯Ό λͺ¨λ₯΄κ³  μ§ννλλ° ν΄λΉ κΈ°λ₯μ μΆκ°νλ€λ©΄ μ λ§ μμ±λ λμ νλ‘μ νΈλ₯Ό μ μν  μμμκ² κ°λ€.
* νλ‘μ νΈλ₯Ό μμνλ©° λκ΅¬λ λ¬΄λ£λ‘ μ¬μ©κ°λ₯ν κ°μΈ μ±λ΄μ λ§λ€κ³  μΆμλλ° DB κ΄λ ¨μΌλ‘ λΆκ°λ₯ ν΄μ μμ¬μμ΄ λ¨λλ€.

<br />
<br />

### 2. μ½λ λ¦¬λ·°
* A-jaxλ₯Ό νμ©ν΄μ μ§λ¬Έμ νμ΄μ§ μλ‘ κ³ μΉ¨μ μμ λκ²μ΄ μ’μκ² κ°λ€.

<br />
<br />
<br />
