
<%@page import="java.sql.ResultSet"%>
<%@page import="chatbot.DBUtill"%>
<%@page import="chatbot.Client"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="chatbot.Application"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String question = request.getParameter("question");
	String answer ="";
	
	int index=1;
	Connection con=null;
	
	con = DBUtill.open();
	
	if(question!=null)k{
		Client client = new Client(con);
		answer = client.startClient(question);
		con.prepareStatement("insert into tblRegdata values(seqRegdata.nextval,'"+question+"')").executeUpdate();
		con.prepareStatement("insert into tblRegdata values(seqRegdata.nextval,'"+answer+"')").executeUpdate();
	}
	
	ResultSet rs = con.prepareStatement("select * from tblRegdata order by seq asc").executeQuery();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/inc/asset.jsp" %>
<style>


</style>
</head>
<body>
				

	
	<div class="container">
		<form action="/chatbot/form1.jsp" method="POST" >
			<div class="chatbotIcon">
				<i class="fa-regular fa-comment-dots fa-3x"></i>
			</div>
			<div class="contents">
				<div class="bar">
					<input type="button" class="btn" id="xbutton" value="X">
				</div>
				<div class="content">
					<%while(rs.next()){
						if(index%2!=1){%>
							<div style="text-align: left; ">
								<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-robot" viewBox="0 0 16 16">
								  <path d="M6 12.5a.5.5 0 0 1 .5-.5h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1-.5-.5ZM3 8.062C3 6.76 4.235 5.765 5.53 5.886a26.58 26.58 0 0 0 4.94 0C11.765 5.765 13 6.76 13 8.062v1.157a.933.933 0 0 1-.765.935c-.845.147-2.34.346-4.235.346-1.895 0-3.39-.2-4.235-.346A.933.933 0 0 1 3 9.219V8.062Zm4.542-.827a.25.25 0 0 0-.217.068l-.92.9a24.767 24.767 0 0 1-1.871-.183.25.25 0 0 0-.068.495c.55.076 1.232.149 2.02.193a.25.25 0 0 0 .189-.071l.754-.736.847 1.71a.25.25 0 0 0 .404.062l.932-.97a25.286 25.286 0 0 0 1.922-.188.25.25 0 0 0-.068-.495c-.538.074-1.207.145-1.98.189a.25.25 0 0 0-.166.076l-.754.785-.842-1.7a.25.25 0 0 0-.182-.135Z"/>
								  <path d="M8.5 1.866a1 1 0 1 0-1 0V3h-2A4.5 4.5 0 0 0 1 7.5V8a1 1 0 0 0-1 1v2a1 1 0 0 0 1 1v1a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2v-1a1 1 0 0 0 1-1V9a1 1 0 0 0-1-1v-.5A4.5 4.5 0 0 0 10.5 3h-2V1.866ZM14 7.5V13a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1V7.5A3.5 3.5 0 0 1 5.5 4h5A3.5 3.5 0 0 1 14 7.5Z"/>
								</svg>  <%=rs.getString("txt") %>
							</div><br>
						<%}else{ %>
							<div style="text-align: right;"><%=rs.getString("txt") %>   
							  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-send-check" viewBox="0 0 16 16">
								  <path d="M15.964.686a.5.5 0 0 0-.65-.65L.767 5.855a.75.75 0 0 0-.124 1.329l4.995 3.178 1.531 2.406a.5.5 0 0 0 .844-.536L6.637 10.07l7.494-7.494-1.895 4.738a.5.5 0 1 0 .928.372l2.8-7Zm-2.54 1.183L5.93 9.363 1.591 6.602l11.833-4.733Z"/>
								  <path d="M16 12.5a3.5 3.5 0 1 1-7 0 3.5 3.5 0 0 1 7 0Zm-1.993-1.679a.5.5 0 0 0-.686.172l-1.17 1.95-.547-.547a.5.5 0 0 0-.708.708l.774.773a.75.75 0 0 0 1.174-.144l1.335-2.226a.5.5 0 0 0-.172-.686Z"/>
							  </svg>
							</div><br>
						<%}
						index++;
					}%>
				</div>
				<input type="text" class="form-control" name="question"><input type="submit" class="btn" value="send" id="sendButton">
			</div>
		</form>
	</div>

<script>
	$(".form-control").focus();
	$(".form-control").scrollTop($(".form-control").height());
	$(".contents").css({
		"border":"1px solid #4444",
		//"display":"none"
	});
	$(".form-control").css({
		"height":"34px",
		"width":"241px",
		"display":"inline-block",
		"margin-left":"1px"
	});
	$("#sendButton").css({
		"text-align":"right",
		"width":"55px",
		"height":"35px",
		"display":"inline-block",
		"background-color":"#EEA032"
	});
	$("#sendButton").click(()=>{
		<%%>
		/*let q = $("<div>").text($(".form-control").val()+": 사용자");
		q.css({
			"text-align":"right"
		});
		$(".content").append(q);
		$(".form-control").val('');
		alert($('.form-control').val());*/
	});
	$("#xbutton").css({
		"margin-top":"1%",
		"margin-right":"2%",
		"background-color":"#337AB5",
		"font-weight":"1000",
		"font-size":"16px"
	});
	$("#xbutton").click(()=>{
		$(".contents").slideUp(200);
	});
	$(".bar").css({
		"width":"300px",
		"height":"40px",
		"text-align":"right",
		"background-color":"#337AB7"
	});
	$(".content").css({
		"width":"300px",
		"height":"400px",
		"overflow":"auto",
		"border-radius":"1%",
		"padding":"20px",
		"margin-botton":"0px"
	});
	
	$(".chatbotIcon").click(()=>{
		$(".contents").slideToggle(200);
	});
	$(".chatbotIcon").mouseover(()=>{
		$(".chatbotIcon>i").css({"color":"blue"})
	});
	$(".chatbotIcon").mouseout(()=>{
		$(".chatbotIcon>i").css({"color":"black"})
	});
	$(".chatbotIcon").css({
		"width":"50px",
		"height":"50px",
		"background-color" : "white",
		"textArea":"center"
	});
	
	$(".container").css({
		"width":"330px"
	});
</script>
</body>
</html>