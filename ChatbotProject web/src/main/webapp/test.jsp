
<%@page import="chatbot.Client"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="chatbot.Application"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Connection con;
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "chatbotProject";
	String pw = "gusdn1234";
	String answer ="ABC";
	String answer2 ="";
	String question = "안녕하세요!";
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(url, id, pw);
		Client client = new Client(con);
		answer=client.startClient(question);
		answer2="ABC";
	}catch(Exception e) {
		e.printStackTrace();
	}
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
		<div class="chatbotIcon">
			<i class="fa-regular fa-comment-dots fa-3x"></i>
		</div>
		<div class="contents">
			<div class="bar">
				<input type="button" class="btn" id="xbutton" value="X">
			</div>
			<div class="content">
				<div style="text-align: left">안녕?</div>
				<div style="text-align: right">안녕ㅎㅎ?</div>
				
					
						
					<div style="text-align: left"><%=answer %></div>
			</div>
			<input type="text" class="form-control"><input type="button" class="btn" value="send" id="sendButton">
		</div>
	</div>

<script>
	$(".contents").css({
		"border":"1px solid #4444",
		"display":"none"
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
		let q = $("<div>").text($(".form-control").val()+": 사용자");
		q.css({
			"text-align":"right"
		});
		$(".content").append(q);
		$(".form-control").val('');
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