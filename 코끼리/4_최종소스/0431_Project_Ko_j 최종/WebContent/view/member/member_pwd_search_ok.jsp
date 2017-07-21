<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Piccolo Theme</title>
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
<script type="text/javascript">
<%
  String id= (String)request.getAttribute("member_id");
  session.setAttribute("sessionid", id);
%>

</script>

</head>

<body>
   <div class="color-bar-1"></div>
    <div class="color-bar-2 color-bg"></div>    
    <div class="container main-container">
    
     <div class="row header"><!-- Begin Header -->      
        <!-- Logo
        ================================================== -->
        <div class="span5 logo">
           <a href="homepage.jsp"><img src="<%= request.getContextPath() %>/img/piccolo-logo.png" alt="" /></a>
            <h5>Big Things... Small Packages</h5>
        </div>
        
       <!-- Main Navigation
        ================================================== -->
        <div class="span7 navigation">
            <div class="navbar hidden-phone">            
            <ul class="nav">
            <li class="dropdown active">
                <a href="<%= request.getContextPath() %>/view/homepage.jsp">Home</a>
            </li>                  
            <li><a href="<%= request.getContextPath() %>/view/member/login.jsp">Login</a></li>
            </ul>

            </div>

        </div>
        
      </div><!-- End Header -->

    <!-- Page Content
    ================================================== -->
    
    <div align="center">
       <div class="row">
           <div class="span12 contact" style="margin-top: 70px">
   
               <h2 align="center" style="margin-top: 30px; margin-bottom:30px">새 비밀번호</h2>
   					 		  <form action="MemberPwdSearchOk.member" method="post">		
					                <div class="input-prepend"  style="height:32px; margin-top: 20px" align="center">         
					                    <span class="add-on"  style="height:32px; "><i class=" icon-lock"></i></span>
					                    <input class="span4" id="member_pw" name="member_pw" size="16" type="text" placeholder="비밀번호 입력" style="height:32px">
					                </div>
					                <div class="input-prepend"  style="height:32px; margin-top: 20px" align="center">         
					                    <span class="add-on"  style="height:32px; "><i class=" icon-lock"></i></span>
					                    <input class="span4" id="member_newpw" name="member_newpw" size="16" type="text" placeholder="비밀번호 확인" style="height:32px">
					                </div>               
					                <div class="row" style="margin-top:30px">
					                     <div class="span12" align="center" >
								            <input type="submit" class="btn btn-inverse" value="확인" style="width:200px;">
								            <input type="reset"class="btn btn-inverse" value="취소" style="width:200px; color:#d8450b;">
								         </div>  
					                </div>
              				 </form>              
                 </div> <!--End page content column-->
			</div>
        </div>
    </div>
</body>
</html>