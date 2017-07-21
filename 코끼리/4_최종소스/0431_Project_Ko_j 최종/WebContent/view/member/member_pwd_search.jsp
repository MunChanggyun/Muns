<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>member pass word search</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- CSS
================================================== -->

<link href='http://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap-responsive.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/custom-styles.css">

<!-- Favicons
================================================== -->
<link rel="shortcut icon" href="<%= request.getContextPath() %>/img/titlelogo.ico">
<link rel="apple-touch-icon" href="<%= request.getContextPath() %>/img/titlelogo.ico">
<link rel="apple-touch-icon" sizes="72x72"   href="<%= request.getContextPath() %>/img/titlelogo.ico">
<link rel="apple-touch-icon" sizes="114x114" href="<%= request.getContextPath() %>/img/titlelogo.ico">


<!-- JS
================================================== -->
<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script src="<%= request.getContextPath() %>/js/bootstrap.js"></script>
<script src="<%= request.getContextPath() %>/js/jquery.custom.js"></script>
<script src="validation/jquery.validate.js"></script>

<!-- validation -->

</head>

<body>
    <div class="color-bar-1"></div>
    <div class="color-bar-2 color-bg"></div>
    
    <div class="container main-container">
    
      <div class="row header"><!-- Begin Header -->      
        <!-- Logo
        ================================================== -->
        <div class="span5 logo">
           <a href="<%= request.getContextPath() %>/view/homepage.jsp"><img src="<%= request.getContextPath() %>/img/piccolo-logo.png" alt="" /></a>
            <h5>Apart Community</h5>
        </div>
        
        <!-- Main Navigation
        ================================================== -->
        <div class="span7 navigation">
            <div class="navbar hidden-phone">
            
            <ul class="nav">
            <li class="dropdown active">
                <a class="dropdown-toggle" data-toggle="dropdown" href="<%= request.getContextPath() %>/view/homepage.jsp">Home</a>
            </li>
            <li><a href="<%= request.getContextPath() %>/view/member/login.jsp">Login</a></li>
            </ul>
            </div>
        </div>
        
     </div><!-- End Header -->      
	<!-- Page Content
    ================================================== -->
    
    	<div class="span3" align="center">
	       <div class="row"><!--Container row-->      
	           <div class="span12 contact" style="margin-top: 70px"><!--Begin page content column-->   
	              	 <h2 align="center" style="margin-top: 30px; margin-bottom:10px">비밀번호 찾기</h2><br>
	              	 <h4 align="center" style="margin-top: 10px; margin-bottom:30px">입력하신 코끼리ID 메일로 비밀번호를 전송해 드립니다.</h4>		 				                     
		                      <form action="MemberPwdSearch.member" method="post">
					                <div class="input-prepend" style="height:32px" align="center">
					                    <span class="add-on" style="height:32px"><i class="icon-user"></i></span>
					                    <input class="span4" id="member_id" name="member_id" size="16" type="text" placeholder="코끼리 ID" style="height:32px">
					                </div>
					                <div class="input-prepend"  style="height:32px; margin-top: 20px" align="center">         
					                    <span class="add-on"  style="height:32px; "><i class=" icon-lock"></i></span>
					                    <input class="span4" id="member_dong" name="member_dong" size="16" type="text" placeholder="동 입력 " style="height:32px">
					                </div>
					                <div class="input-prepend"  style="height:32px; margin-top: 20px" align="center">         
					                    <span class="add-on"  style="height:32px; "><i class=" icon-lock"></i></span>
					                    <input class="span4" id="member_ho" name="member_ho" size="16" type="text" placeholder="호 입력" style="height:32px">
					                </div>               
					                <div class="row" style="margin-top:30px">
					                     <div class="span12" align="center" >
								            <input type="submit" class="btn btn-inverse" value="확인" style="width:200px;"> <input type="reset"class="btn btn-inverse" value="취소" style="width:200px; color:#d8450b;">
								         </div>  
					                </div>
              					</form>      
	            </div> <!--End page content column-->
			</div>            
        </div><!--//container-->
</div> <!-- End Container -->
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
</body>
</html>