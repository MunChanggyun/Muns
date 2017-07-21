<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Reservation Board</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- CSS
================================================== -->
<link href='http://fonts.googleapis.com/css?family=Oswald'
   rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap-responsive.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/jquery.lightbox-0.5.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/custom-styles.css">

<!-- Favicons
================================================== -->
<link rel="shortcut icon" href="<%= request.getContextPath() %>/img/titlelogo.ico">
<link rel="apple-touch-icon" href="<%= request.getContextPath() %>/img/titlelogo.ico">
<link rel="apple-touch-icon" sizes="72x72"   href="<%= request.getContextPath() %>/img/titlelogo.ico">
<link rel="apple-touch-icon" sizes="114x114" href="<%= request.getContextPath() %>/img/titlelogo.ico">


<!-- JS
================================================== -->
<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery.custom.js"></script>

<style type="text/css">
.table th,
.table td {
  padding: 9px;
  line-height: 20px;
  text-align: left;
  vertical-align: top;
  border-top: 1px solid #dddddd;
}
</style>

</head>

<body>
   <div class="color-bar-1"></div>
   <div class="color-bar-2 color-bg"></div>

   <div class="container main-container">

      <div class="row header">
         <!-- Begin Header -->

        <!-- Logo
        ================================================== -->
         <div class="span5 logo">
            <a href="<%= request.getContextPath() %>/view/homepage.jsp">
            <img src="<%= request.getContextPath() %>/img/piccolo-logo.png" alt="" /></a>
            <h5>Apartment Community</h5>
         </div>

    <!-- Main Navigation
	        ================================================== -->
	        <div class="span7 navigation">
	            <div class="navbar hidden-phone" style="margin-bottom: 0px;">            
		             <ul class="nav">
				            <li class="dropdown active">
				                <a class="dropdown-toggle" data-toggle="dropdown" href="homepage.jsp">Home</a>
				            </li>
				            <li class="dropdown">
				                <a class="dropdown-toggle" data-toggle="dropdown" href="">Board <b class="caret"></b></a>
				                <ul class="dropdown-menu">
				                     <li><a href="<%=request.getContextPath()%>/AllList.board">AllList Board</a></li>
				                     <li><a href="<%=request.getContextPath()%>/Certain.board?categoryCode=1">Notice Board</a></li>
				                     <li><a href="<%=request.getContextPath()%>/Certain.board?categoryCode=2">Community Board</a></li>
				                </ul>
				            </li>
				            <li><a href="<%= request.getContextPath() %>/Gallery.board">Gallery</a></li>
				            <li><a href="<%= request.getContextPath() %>/view/calendar/calendar_list.jsp">Calendar</a></li>
				            <li><a href="<%= request.getContextPath() %>/view/reservation/Reservation.jsp">Reservation</a></li>
							 <c:choose>                 
				                   <c:when test="${sessionScope.session_authority==null}">
				                    	<li><a href="<%= request.getContextPath() %>/view/member/login.jsp">Login</a></li>
				                   </c:when>
				                   <c:otherwise>
					                    <li class="dropdown active"><a href="Logout.member">Logout</a></li>
				                   </c:otherwise>	
	              	 		 </c:choose>
		             </ul>
	            </div>
	        </div>
	        <div align="right">
	            <c:choose>
			    	<c:when test="${sessionScope.session_authority!=null}">
						<li style="padding-top: 10px; list-style:none; color:#d8450b; font-size: 14px">${sessionScope.session_id}님 접속중</li>
					</c:when>
				</c:choose>
			</div>
      </div><!-- End Header -->

      <!-- Page Content
    ================================================== -->
      <div class="container">
         <div class="row">
            <h2>회원 관리 현황</h2>

            <div class="esconde" id="opdRetro" style="font-size: 16px;">
               <table class="table table-striped table-hover " style="cellpadding:500px">
                  <thead >
                     <tr class="bg-primary">
                        <!-- <th>No.</th> -->
                        <th>우편번호</th>
                        <th>동</th>
                        <th>호</th>
                        <th>아이디</th>
                        <th style="width:80px;">탈퇴</th>
                     </tr>
                  </thead>
                  <tbody>
                     <!-- para abrir em outra aba adicionar target="_blank" -->
                     <c:set var="memberlist" value="${requestScope.memberlist}"/>
                      <c:forEach var="member" items="${memberlist}">
                     <tr>
                     <!-- <td></td> -->
                        <td>${member.getMember_zipcode()}</td>
                        <td>${member.getMember_dong()}</td>
                        <td>${member.getMember_ho()}</td>
                        <td>${member.getMember_id()}</td>
                        <td><a href="MemberDelete.member?member_id=${member.getMember_id()}">탈퇴</a><td>
                        
                     </tr>
                     </c:forEach>
                  </tbody>
               </table>
            </div>
         </div>
       
         
      </div>
   </div>
   <!-- End Container -->

   <!-- Footer Area
        ================================================== -->
	<div class="footer-container"><!-- Begin Footer -->
        <div class="container">        	
		       <div class="row"><!-- Begin Sub Footer -->
		                <div class="span12 footer-col footer-sub">
		                    <div class="row no-margin">
		                        <div class="span6"><span class="left">Copyright 2017 KOGGIRI Theme. All rights reserved.</span></div>
		                        <div class="span6">
		                            <span class="right">
		                            <a href="<%=request.getContextPath()%>/view/homepage.jsp">Home</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
		                            <a href="<%=request.getContextPath()%>/AllList.board">Board</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
		                            <a href="<%=request.getContextPath()%>/Gallery.board">Gallery</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
		                            <a href="<%= request.getContextPath() %>/view/calendar/calendar_list.jsp">Calendar</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
		                            <a href="<%= request.getContextPath() %>/view/reservation/Reservation.jsp">Reservation</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
		                            <a href="<%=request.getContextPath()%>/MemberList.member">Manager</a>
		                            </span>
		                        </div>
		                    </div>
		                </div>
		       </div><!-- End Sub Footer -->		
		</div>
	</div>
      <!-- End Footer -->       
   <!-- Scroll to Top -->
   <div id="toTop" class="hidden-phone hidden-tablet">Back to Top</div>
</body>
</html>