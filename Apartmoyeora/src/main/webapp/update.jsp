<%@page import="model.BoardDTO"%>
<%@page import="model.BoardDAO"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="model.UsersDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
		UsersDTO info = (UsersDTO) session.getAttribute("info");
		String id = info.getId();
		
	    
		if (id != null) {
			String nickname = (String) session.getAttribute("nickname");
		}
		if (id == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인을 하세요.')");
			script.println("location.href = 'index.jsp'");
			script.println("</script>");
		}
		int com_Num = 0;
		if (request.getParameter("com_Num") != null) {
			com_Num = Integer.parseInt(request.getParameter("com_Num"));
			
		} 
		/* if (com_Num == 0) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('유효하지 않는 글입니다.')");
			script.println("location.href = 'index.jsp'");
			script.println("history.back()");
			script.println("</script>");
			
		} */
		BoardDTO dto = new BoardDAO().getBoardDTO(com_Num);
		BoardDAO dao = new BoardDAO();
		if (!id.equals(dto.getId())) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('권한이 없습니다.')");
			script.println("location.href = 'index.jsp'");
			script.println("history.back()");
			script.println("</script>");
		}
	%>
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="main.jsp">JSP 게시판 웹 사이트</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="main.jsp">메인</a></li>
				<li class="active"><a href="index.jsp">게시판</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">회원관리<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="logoutAction.jsp">로그아웃</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</nav>
 <div class="container">
	<div class="row">
		<form method="post" action="updateAction?com_Num=<%=com_Num%>">
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
			<thead>
				<tr>
					<th colspan="2" style="background-color: #eeeeee; text-align: center;">게시판 글 수정 양식</th>						
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input type="text" class="form-control" placeholder="글 제목" name="com_Title" maxlength="50" value="<%= dto.getCom_Title() %>"></td>
				</tr>
				<tr>
					<td><textarea class="form-control" placeholder="글 내용" name="com_Content" maxlength="2048" style="height: 350px"><%= dto.getCom_Content()%></textarea></td>						
				</tr>
			</tbody>
		</table>
		<input type="submit" id="editBtn" class="btn btn-primary pull-right" value="글수정">
		</form>						
	</div>
</div>
</body>
</html>