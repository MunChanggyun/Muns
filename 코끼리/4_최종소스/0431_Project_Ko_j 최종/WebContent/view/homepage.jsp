<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Apart HomePage</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- CSS
================================================== -->
<link href='http://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-responsive.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/prettyPhoto.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/flexslider.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/custom-styles.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">


<!-- Favicons
================================================== -->
<link rel="shortcut icon" href="<%= request.getContextPath() %>/img/titlelogo.ico">
<link rel="apple-touch-icon" href="<%= request.getContextPath() %>/img/titlelogo.ico">
<link rel="apple-touch-icon" sizes="72x72"   href="<%= request.getContextPath() %>/img/titlelogo.ico">
<link rel="apple-touch-icon" sizes="114x114" href="<%= request.getContextPath() %>/img/titlelogo.ico">


<!-- JS
================================================== -->
<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.prettyPhoto.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.flexslider.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.custom.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script type="text/javascript">
   $(document).ready(function () {
        $("#Calendardatepicker").datepicker({   
        });
     
       $("#btn-blog-next").click(function () {
         $('#blogCarousel').carousel('next')
       });
        $("#btn-blog-prev").click(function () {
         $('#blogCarousel').carousel('prev')
       });
   
        $("#btn-client-next").click(function () {
         $('#clientCarousel').carousel('next')
       });
        $("#btn-client-prev").click(function () {
         $('#clientCarousel').carousel('prev')
       });
        $.ajax({type:"post",
           url:'<%=request.getContextPath()%>/Certain.board?categoryCode=1',
           success:function(html){              
              $('#blogCarouse1').html($(html).find("#dataTables-example1"));              
              
           },
           error:function(html){
              alert('onFailure:'+URL);
           }
        });
        $.ajax({type:"post",
           url:'<%=request.getContextPath()%>/Certain.board?categoryCode=2',
           success:function(html){              
              $('#blogCarouse2').html($(html).find("#dataTables-example2"));             
              
           },
           error:function(html){
              alert('onFailure:'+URL);
           }
        });
   
   });
   
    $(window).load(function(){   
          $('.flexslider').flexslider({
              animation: "slide",
              slideshow: true,
              start: function(slider){
                $('body').removeClass('loading');
              }
          });  
       
    });

</script>

</head>

<body class="home">
    <!-- Color Bars (above header)-->
	<div class="color-bar-1"></div>
    <div class="color-bar-2 color-bg"></div>    
    <div class="container">
    
      <div class="row header"><!-- Begin Header -->      
	        <!-- Logo
	        ================================================== -->
	        <div class="span5 logo">
	        	<a href=""><img src="<%=request.getContextPath()%>/img/piccolo-logo.png" alt="" /></a>
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
     
     
     <!-- Begin Headline -->     
	    <div class="row headline">
	        	<!-- Slider Carousel
		        ================================================== -->
		        <div class="span8">
		            <div class="flexslider">
		              <ul class="slides">
		                <li><a href=""><img src="<%= request.getContextPath() %>/img/apartimg/apartimg1.jpg" alt="slider"/></a></li>
		                <li><a href=""><img src="<%= request.getContextPath() %>/img/apartimg/apartimg2.jpg" alt="slider"/></a></li>
		                <li><a href=""><img src="<%= request.getContextPath() %>/img/apartimg/apartimg3.jpg" alt="slider"/></a></li>		         
		              </ul>
		            </div>
		        </div>
		        <!-- Headline Text
		        ================================================== -->
		        <div class="span4">
		        	<h3>Calendar</h3>
		        	<div id="Calendardatepicker" style="text-align: center; ">
		        	</div>	           
		            <a href="<%= request.getContextPath() %>/view/calendar/calendar_list.jsp"><i class="icon-plus-sign "></i>Read More</a> 
		        </div>
	    </div>
    <!-- End Headline -->    
    
    
    <!-- Begin Bottom Section -->
    <div class="row">
    	<!-- 공지사항
        ================================================== -->
          <div class="span6" style="width: 550px; height: 500px;" align="center">   
                  <h5 class="title-bg" style="margin-bottom: 0px">공지사항  
                      <small>Notice Board</small>
                      <small>
                        <a href="<%=request.getContextPath()%>/Certain.board?categoryCode=1"><i class="icon-plus-sign "></i>Read More</a>
                      </small>                      
                  </h5>      
                  <div id="blogCarouse1" class="carousel slide ">
                     <!-- 공지사항 들어갈 자리 -->
                  </div>              
           </div>
	        
	         <!-- 소통게시판
           ================================================== -->
           <div class="span6" style="width: 550px; height: 500px" align="center">
                <h5 class="title-bg" style="margin-bottom: 0px">소통게시판  
                   <small>Community Board</small>
                   <small><a href="<%=request.getContextPath()%>/Certain.board?categoryCode=2"><i class="icon-plus-sign "></i>Read More</a></small>
                </h5>
                 <div id="blogCarouse2" class="carousel slide">
                  <!-- 소통게시판 들어갈 자리 -->
               </div>    
           </div>
        
    </div><!-- End Bottom Section -->
    
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
		                            <a href="#">Home</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
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