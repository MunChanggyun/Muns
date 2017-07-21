<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Piccolo Theme</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- CSS
================================================== -->
<link href='http://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap-responsive.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/jquery.lightbox-0.5.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/custom-styles.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


<!-- Favicons
================================================== -->
<link rel="shortcut icon" href="<%=request.getContextPath() %>/img/favicon.ico">
<link rel="apple-touch-icon" href="<%=request.getContextPath() %>/img/apple-touch-icon.png">
<link rel="apple-touch-icon" sizes="72x72" href="<%=request.getContextPath() %>/img/apple-touch-icon-72x72.png">
<link rel="apple-touch-icon" sizes="114x114" href="<%=request.getContextPath() %>/img/apple-touch-icon-114x114.png">

<!-- JS
================================================== -->
<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery.easing.1.3.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery.prettyPhoto.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery.quicksand.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery.custom.js"></script>

<style type="text/css">

a.no-uline{
	text-decoration: none;
	color: white;
}
</style>

<script type="text/javascript"> 
$(window).ready(function(){
 	    if(<%=session.getAttribute("session_authority") %>==null){
 	    	$("#gwritebtn").hide(); 	    	
 	    }else{
 	    	
 	    	$("#gwritebtn").show();
 	    }       
});
</script>


</head>

<body>

<c:set var="gallerylist" value="${requestScope.gallerylist}"/>
<c:set var="cpage" value="${requestScope.cp }"/>
<c:set var="pagesize" value="${requestScope.pagesize }"/>
<c:set var="totalgallerycount" value="${requestScope.totalgallerycount }"/>
<c:set var="pagecount" value="${requestScope.pagecount }"/>
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
    <div class="row">

        <!-- Gallery Items
        ================================================== --> 
        <div class="span12 gallery">
         
      <div class="span12">
      <form action="<%=request.getContextPath()%>/Gallery.board?" method="post">
                  PageSize설정: 
                     <select name="ps" onchange="submit()">
                        
                           <c:forEach var="i" begin="6" end="12" step="6">
                              <c:choose>
                           <c:when test="${pagesize == i}">
                                      <option value='${i}' selected>${i}건</option>
                                 </c:when>
                           <c:otherwise>
                                          <option value='${i}'>${i}건</option>
                                  </c:otherwise>
                        </c:choose>
                           </c:forEach>
                        </select>
                  </form>
         
         <button class="btn btn-danger" id="gwritebtn" style="float:right; margin-right: 35px;"><a class="no-uline" href="<%=request.getContextPath() %>/view/gallery/GalleryWrite.jsp" > 사진 추가하기</a> </button>
      </div>
            <div class="row clearfix">
                <ul class="gallery-post-grid holder">

                    <!-- Gallery Item 1 -->
                    
                
               <c:forEach var="gallery" items="${gallerylist }">
                  <li  class="span4 gallery-item" data-id="id-1" data-type="illustration">
                           <span >
                               <img src="upload/${gallery.getGallery_file() }" style="width: 370px; height: 300px;">
                               <span class="gallery-icons">
                                     
                                
                               </span>
                         
                           </span>
                           <span class="project-details">                   
                              <h5>${gallery.getGallery_title()}</h5>
                     
                     <small>${gallery.getContent()}<br>글쓴이:${gallery.getMember_id()} 
                     <a id="like"  style="width: 30px; float: right;margin-right: 20px;" href="<%=request.getContextPath()%>/GalleryLike.board?gallery_id=${gallery.getGallery_id()}">
                     <i class="fa fa-thumbs-o-up" style="font-size:24px" >${gallery.getGallery_like() }</i></a>
                     <a href="<%=request.getContextPath() %>/GalleryDelete.board?gallery_id=${gallery.getGallery_id()}&cpage=${cpage}&psize=${pagesize}">삭제하기</a></small>
                     
                     
                     </span>
                       </li>
               </c:forEach>

                 </ul>
                 
            <!-- Pagination -->
            <div class="pagination" style="float:right;">
               <c:if test="${cpage>1}">
                                       <a href="<%=request.getContextPath()%>/Gallery.board?cp=${cpage-1}&ps=${pagesize}">
                                       이전
                                       </a>
                                    </c:if>
                                    <c:forEach var="pageindex" begin="1" end="${pagecount }" step="1">
                                     <c:choose>
                                 <c:when test="${cpage==pageindex}">
                                    <font color='red'>[${pageindex}]</font>
                                 </c:when>
                                 <c:otherwise>
                                    <a href="<%=request.getContextPath()%>/Gallery.board?cp=${pageindex}&ps=${pagesize}">[${pageindex}]</a>
                                 </c:otherwise>
                              </c:choose>
                                    </c:forEach>
                                    <c:if test="${cpage<pagecount}">
                                       <a href="<%=request.getContextPath()%>/Gallery.board?cp=${cpage+1}&ps=${pagesize}">다음</a>
                                    </c:if> 
            </div>
   </div>
        </div><!-- End gallery list-->


    </div><!-- End container row -->
    
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

    <!-- Scroll to Top -->  
    <div id="toTop" class="hidden-phone hidden-tablet">Back to Top</div>
    
</body>
</html>
