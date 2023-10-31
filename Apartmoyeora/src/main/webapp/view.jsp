<%@page import="model.ComLikeDTO"%>
<%@page import="model.LikesDAO"%>
<%@page import="model.UsersDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="model.BoardDTO" %>
<%@ page import="model.BoardDAO" %>
<link rel="stylesheet" href= "css/bootstrap.css">



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 게시판 웹 사이트</title>
</head>
<body>
   
   
   <% 
   	
      UsersDTO info =(UsersDTO)session.getAttribute("info"); 
   		if (info ==null){
   			response.sendRedirect("index.jsp");
   		}
      String nickname = info.getNickname();
      BoardDAO bdao = new BoardDAO();
      LikesDAO ldao = new LikesDAO();
      int com_Num = 0;
      
      if (request.getParameter("com_Num") != null) {
         com_Num = Integer.parseInt(request.getParameter("com_Num")); // COMNUM 게시글 번호 
         //SQL ROWNUM ORDER BY COM_DATE DESC;
         
         //SELECT COM_CATEGORY, COM_NUM, COM_TITLE, NICKNAME, COM_DATE, COM_VIEWS FROM COMMUNITY ORDER BY COM_NUM DESC;
         
      }
    /*   if (com_Num == 0) {
         PrintWriter script = response.getWriter();
         script.println("<script>");
         script.println("alert('유효하지 않는 글입니다.')");
         script.println("location.href = 'community.jsp'");
         script.println("history.back()");
         script.println("</script>");
      } */
      
      BoardDTO dto = new BoardDAO().getBoardDTO(com_Num);
      
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
         <a class="navbar-brand" href="community.jsp">AMORA</a>
      </div>
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
         <ul class="nav navbar-nav">
            <li><a href="index.jsp">메인</a></li>
            <li class="active"><a href="community.jsp">게시판</a></li>
         </ul>
         <% 
            if (nickname == null) {
         %>
         <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
               <a href="#" class="dropdown-toggle"
                  data-toggle="dropdown" role="button" aria-haspopup="true"
                  aria-expanded="false">접속하기<span class="caret"></span></a>
               <ul class="dropdown-menu">
                  <li><a href="index.jsp">로그인</a></li>
                  <li><a href="join.jsp">회원가입</a></li>
               </ul>
            </li>
         </ul>
         <%       
            } else {
         %>
         <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
               <a href="#" class="dropdown-toggle"
                  data-toggle="dropdown" role="button" aria-haspopup="true"
                  aria-expanded="false">마이페이지<span class="caret"></span></a>
               <ul class="dropdown-menu">
                  <li><a href="logoutAction.jsp">로그아웃</a></li>
               </ul>
            </li>
         </ul>
         <%      
            }
         %>
         
      </div>
   </nav>
   <div class="container">
      <div class="row">
         <table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
            <thead>
               <tr>
                  <th colspan="3" style="background-color: #eeeeee; text-align: center;">게시판 글보기</th>                  
               </tr>
            </thead>
            <tbody>
               <tr>
                  <td style="width: 20%;">글제목</td>
                  <td colspan="2"><%= dto.getCom_Title().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">","&gt;").replaceAll("\n", "<br>") %></td>
               </tr>
               <tr>
               	<td>카테고리</td>
                  <td colspan="2"><%= dto.getCom_Category()%></td>	
               </tr>
               <tr>
                  <td>작성자</td>
                  <td colspan="2"><%= dto.getNickname() %></td>
               </tr>
               <tr>
                  <td>작성일자</td>
                  <td colspan="2"><%= dto.getCom_Date().substring(0, 11) + dto.getCom_Date().substring(11, 13) + "시" + dto.getCom_Date().substring(14, 16) + "분 " %></td>
               </tr>
               <tr>
                  <td>내용</td>
                  <td colspan="2" style="min-height: 200px; text-align: left;"><%= dto.getCom_Content().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">","&gt;").replaceAll("\n", "<br>") %></td>
               </tr>
            </tbody>
         </table>
		          
         <br>
         
         
         
         <!--  여기서부터는 좋아요,신고 기능입니다 -->
         	<%! public Boolean isLike(String userId, int comNum){
      LikesDAO ldao = new LikesDAO();
      return ldao.isUserLiked(userId, comNum);
  }
%>

<%! public Boolean isReport(String userId, int comNum){
      LikesDAO ldao = new LikesDAO();
      return ldao.isUserLiked(userId, comNum);
  }
%>

         <%
         ComLikeDTO ldto =new ComLikeDTO(info.getId(),com_Num );
         int comLikenum =ldao.getComLike_num(ldto);
         
        
         String likeServlet = "";
         String likeUrl;
         if (isLike(info.getId(), com_Num)){
        	 likeServlet = "BoardunLikeUpdateAction";
        	 likeUrl = "./images/liked.png";
         }	
        	 else{
        	 likeServlet = "BoardLikeUpdateAction";
        	 likeUrl = "./images/unliked.png";
         } 
         %>
        <%-- 좋아요 상태를 추적하기 위한 숨겨진 필드 추가 --%>
<input type="hidden" id="isLiked" value='<%= isLike(info.getId(), com_Num) ? "true" : "false" %>'>

<%-- 좋아요 버튼 이미지 --%>
<form id="like_form" action="<%= likeServlet %>" method="post">
	<input type="hidden" name="command" value="like_it">
	<input type="hidden" name="com_Num" value=<%=com_Num%>>
	<div><button type="button" onclick="toggleLike()"><img id="likeImage" src="<%= likeUrl %>" alt="좋아요!"></button></div>
	<div id="like_result">${board.like_it}</div>
</form>
<%int likes= bdao.getCom_Likes(com_Num); %>
<span>좋아요 <%=likes%></span>



         <%
         if (nickname != null && nickname.equals(dto.getNickname())) {
             %>
         		   <a href="community.jsp" class="btn btn-primary">목록</a>
                   <a href="update.jsp?com_Num=<%= com_Num %>" class="btn btn-primary">수정</a>
                   <a onclick="return confirm('정말로 삭제하시겠습니까?')" href="deleteAction?com_Num=<%= com_Num %>&com_Category=<%= dto.getCom_Category() %>" class="btn btn-primary">삭제</a>
                   <!-- 삭제의 기준이 되는 com_Num과 com_Category의 값을 무조건 받아야함,그래서 전달도 같이 해줘야함. -->
             <%
                }
             %>      
         
         <script>
function toggleLike() {
    // 좋아요 상태를 가져옵니다.
    var isLiked = document.getElementById('isLiked').value === "true";
    
    // 폼과 이미지 엘리먼트를 가져옵니다.
    var likeForm = document.getElementById('like_form');
    var likeImage = document.getElementById('likeImage');
    
    // 좋아요 상태를 변경하고 이미지를 업데이트합니다.
    if (isLiked) {
        likeImage.src = "./images/unliked.png";
        likeForm.action = "BoardunLikeUpdateAction";
        document.getElementById('isLiked').value = "false";
    } else {
        likeImage.src = "./images/liked.png";
        likeForm.action = "BoardLikeUpdateAction";
        document.getElementById('isLiked').value = "true";
    }
    
    // 폼을 제출합니다.
    likeForm.submit();
}


function toggleReport() {
    // 좋아요 상태를 가져옵니다.
    var isLiked = document.getElementById('isReported').value === "true";
    
    // 폼과 이미지 엘리먼트를 가져옵니다.
    var reportForm = document.getElementById('report_form');
    var repoartImage = document.getElementById('reportImage');
    
    // 좋아요 상태를 변경하고 이미지를 업데이트합니다.
    if (isReported) {
        likeImage.src = "./images/unReported.png";
        likeForm.action = "BoardunLikeUpdateAction";
        document.getElementById('isLiked').value = "false";
    } else {
        likeImage.src = "./images/liked.png";
        likeForm.action = "BoardLikeUpdateAction";
        document.getElementById('isLiked').value = "true";
    }
    
    // 폼을 제출합니다.
    likeForm.submit();
}
</script>






      </div>
   </div>
   <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
   <script src="js/bootstrap.js"></script>
</body>
</html>