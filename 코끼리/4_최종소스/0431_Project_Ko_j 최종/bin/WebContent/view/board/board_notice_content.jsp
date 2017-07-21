<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Koggiri Community</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<!-- CSS
================================================== -->
<link href='http://fonts.googleapis.com/css?family=Oswald'
   rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap-responsive.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/jquery.lightbox-0.5.css">
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
<!-- DATATABLE STYLE  -->
<link href="<%= request.getContextPath() %>/js/dataTables/dataTables.bootstrap.css" rel="stylesheet" />
<!-- DATATABLE SCRIPTS  -->
<script src="<%= request.getContextPath() %>/js/dataTables/jquery.dataTables.js"></script>
<script src="<%= request.getContextPath() %>/js/dataTables/dataTables.bootstrap.js"></script> 



<script type="text/javascript"> 
$(window).ready(function(){
 	    if(<%=session.getAttribute("session_authority") %>==null){
 	    	$("#dapbtn").hide();
 	    	$("#subtn").hide();
 	    	$("#sakbtn").hide();
 	    }else if(<%=session.getAttribute("session_authority") %>==1){
 	    	$("#dapbtn").hide();
 	    	$("#subtn").hide();
 	    	$("#sakbtn").hide();
 	    }else{
 	    	$("#dapbtn").show();
 	    	$("#subtn").show();
 	    	$("#sakbtn").show();
 	    }       
});
</script>

</head>

<!-- <style>
html{ text-align : center;   }
</style> -->
<body >
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
      <div class="row" style="text-align:center;align:center;">
         <!--Container row-->
         <!-- Page Sidebar
        ================================================== -->
         <div class="span3 sidebar page-sidebar">
            <!-- Begin sidebar column -->
            <!--Latest News-->
            <h2 class="title-bg">Board List</h2>
            <ul class="popular-posts">
               <li><h4><a href="<%=request.getContextPath()%>/AllList.board">ALL LIST BOARD</a></h4> 
               <li><h4><a href="<%=request.getContextPath()%>/Certain.board?categoryCode=1">NOTICE BOARD</a></h4>
               <li><h4><a href="<%=request.getContextPath()%>/Certain.board?categoryCode=2">COMMUNITY BOARD</a></h4>
               <li><h4><a href="<%= request.getContextPath() %>/Gallery.board">GALLERY BOARD</a></h4>  
            </ul>
         </div>
    <!-- End sidebar column -->
    

         <!-- Page Content
        ================================================== -->
         <div class="span8" style="margin-bottom:50px;">
            <!--Begin page content column-->
            <div class="container" style="width:100%;">
               <h2 class="title-bg">Notice Board</h2>
               <div class="panel panel-default">
                  <div class="panel-body">
                     <div class="table-responsive">
                        <div id="dataTables-example_wrapper"
                           class="dataTables_wrapper form-inline" role="grid">
                        	<div align="center" style="margin-top: 30px;">
                        		<div class="divTable" style="width: 100%;height:50%; border: 1px solid gray; border-radius: 30px">
						    		<h3>${boardDto.getBoard_title_t()}&nbsp;&nbsp;|&nbsp;&nbsp;공지사항</h3>
						    		<hr style="width: 85%; display:block; border-style: inset; border-width: 2px;" >
						    		
						    		<div class="span8" style="height:100px; font-size: 18px;">${boardDto.getBoard_content()}</div>
						    		<hr style="width: 85%; display:block; border-style: inset; border-width: 2px;" >
						    		
						    		<div style="height:100%">작성자:${boardDto.getMember_id()}&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;
						    		    작성일:${boardDto.getBoard_date()}&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;
						    		    좋아요:${boardDto.getBoard_like()}&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;
						    		    조회수:${boardDto.getBoard_hit()}</div>
						    		<div>
						    			<table>
						    				<tr>
						    					<th>첨부파일</th>
						    				</tr>
						    				<c:forEach var="file" items="${filelist}">
						    					<tr>
						    						<td><a href="<%= request.getContextPath() %>/FileDownload.etc?fileName=${file.getBoardFile_name()}&boardIdx=${boardIdx}&categoryCode=${boardDto.getBoardCategory_code()}">${file.getBoardFile_oriname()}</a></td>
						    					</tr>
						    				</c:forEach>
						    			</table>
						    		</div>
						    			
						    		<div style="float:bottom;">
						    			<button class="btn btn-inverse" type="button" onclick="location.href='LikeUp.board?boardIdx=${boardIdx}&categoryCode=${boardDto.getBoardCategory_code()}'">좋아요</button>
						    		</div>
							    	<div align="center">
										<div style="margin-top: 100px;">
							    			<hr style="width: 85%; display: block; border-style: inset; border: 2px solid ;" >
								    		<form method="post" action="<%= request.getContextPath() %>/Write.comment?boardIdx=${boardIdx}">
								    			<table>
								    				<c:forEach var="comment" items="${commentlist}">
									    				<tr> <!-- 댓글 뿌리기 -->
									    					<td><h5>${comment.getMember_id()}</h5></td>
									    					<td>${comment.getComment_content()}</td>
									    					<td>${comment.getComment_date()}</td>
									    					<td><a href="<%=request.getContextPath() %>/Delete.comment?commentIdx=${comment.getComment_id()}&boardIdx=${boardIdx}">삭제</a></td>
									    				</tr>
									    			</c:forEach>
								    				<tr>
								    					<td><h5>${sessionScope.session_id}</h5></td>
								    					<td><input type="text" id="commentContent" name="commentContent" style="width: 400px"></td>
								    					<td></td>
								    					<td><input type="submit" value="댓글 달기" size="20px"></td>
								    				</tr>
								    			</table>
							    			</form>
							 			</div>
							 		</div>
					 			</div>
							</div>
						    <div align="center" style="margin-top: 15px">
						    	<button class="btn btn-inverse" type="button" onclick="location.href='Certain.board?categoryCode=${boardDto.getBoardCategory_code()}&psize=${psize}&cpage=${cpage}'">목록으로</button> 
						    	<button class="btn btn-inverse" id="dapbtn" type="button" onclick="location.href='view/manager/board_notice_rewrite.jsp?boardIdx=${boardIdx}&categoryCode=${boardDto.getBoardCategory_code()}'">답글</button>
						    	<button class="btn btn-inverse" id="subtn" type="button" onclick="location.href='toEditPage.board?boardIdx=${boardIdx}&categoryCode=${boardDto.getBoardCategory_code()}'">수정</button>
						    	<button class="btn btn-inverse" id="sakbtn" type="button" onclick="location.href='Delete.board?boardIdx=${boardIdx}&categoryCode=${boardDto.getBoardCategory_code()}'">삭제</button>
						    </div>
                        </div>
                     </div>
                  </div>
               </div>
               <!--End page content column-->
            </div>
            <!-- End container row -->
         </div>
      </div>
      <!-- End Container -->
   </div>
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
		                            <a href="<%=request.getContextPath() %>/view/calendar/calendar_list.jsp">Calendar</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
		                            <a href="<%=request.getContextPath() %>/view/reservation/Reservation.jsp">Reservation</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
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