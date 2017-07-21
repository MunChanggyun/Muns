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
               <li>
                  <h4>
                     <a href="<%=request.getContextPath()%>/AllList.board">ALL LIST BOARD</a>
                  </h4> 
               <li>
                  <h4>
                     <a href="<%=request.getContextPath()%>/Certain.board?categoryCode=1">NOTICE BOARD</a>
                  </h4>
               <li>
                  <h4>
                     <a href="<%=request.getContextPath()%>/Certain.board?categoryCode=2">COMMUNITY BOARD</a>
                  </h4>
               <li>
                  <h4>
                     <a  href="<%= request.getContextPath() %>/Gallery.board">GALLERY BOARD</a>
                  </h4>  
            </ul>

         </div>

         <!-- End sidebar column -->
         
         <!-- Page Content
        ================================================== -->
         <div class="span9">
            <!--Begin page content column-->
            <div class="container" style="width:100%;">
               <h2 class="title-bg">
               		All Board List
               </h2>
               <div class="panel panel-default">
                  <div class="panel-body">
                     <div class="table-responsive">
                        <div id="dataTables-example_wrapper"
                           class="dataTables_wrapper form-inline" role="grid">
                           <div class="row">
                              <div class="col-sm-7">
                                 <div class="dataTables_length" id="dataTables-example_length" style="margin-left:30px;float:left;">
                                    <form action="<%=request.getContextPath()%>/AllList.board" method="post">
                  						Page Size&nbsp;&nbsp;:&nbsp;&nbsp;
                     					<select name="psize" onchange="submit()" aria-controls="dataTables-example" class="form-control input-sm">
	                        				<c:forEach var="i" begin="5" end="20" step="5">
                              					<c:choose>
                           							<c:when test="${psize == i}">
                                      					<option value='${i}' selected>${i}건</option>
                                 					</c:when>
                           							<c:otherwise>
                                          				<option value='${i}'>${i}건</option>
                                  					</c:otherwise>
                        						</c:choose>
                           					</c:forEach>
                        				</select>
                  					</form>
                                 </div>
                              </div>
                              
                           </div>
                           <div style="float:left;width:70px;background-color:white;height:18px;margin-bottom:3px;">
                           	[ 총 (<b>${totalboardCount}</b>) 건 ]                      
                           </div>
                           <table style=""
                              class="table table-striped table-bordered table-hover dataTable no-footer"
                              id="dataTables-example" aria-describedby="dataTables-example_info">
                              <thead>
                                 <tr role="row">
                                    <th class="sorting_asc" tabindex="0"
                                       aria-controls="dataTables-example" rowspan="1" colspan="1"
                                       aria-sort="ascending"
                                       aria-label="Rendering engine: activate to sort column ascending"
                                       style="width: 20px;"></th>
                                    <th class="sorting" tabindex="0"
                                       aria-controls="dataTables-example" rowspan="1" colspan="1"
                                       aria-label="Browser: activate to sort column ascending"
                                       style="width: 350px;text-align:center;">제목</th>
                                    <th class="sorting" tabindex="0"
                                       aria-controls="dataTables-example" rowspan="1" colspan="1"
                                       aria-label="Platform(s): activate to sort column ascending"
                                       style="width: 30px;text-align:center;">작성자</th>
                                    <th class="sorting" tabindex="0"
                                       aria-controls="dataTables-example" rowspan="1" colspan="1"
                                       aria-label="Platform(s): activate to sort column ascending"
                                       style="width: 80px;text-align:center;">작성일</th>
                                    <th class="sorting" tabindex="0"
                                       aria-controls="dataTables-example" rowspan="1" colspan="1"
                                       aria-label="Engine version: activate to sort column ascending"
                                       style="width: 30px;text-align:center;">조회</th>
                                    <th class="sorting" tabindex="0"
                                       aria-controls="dataTables-example" rowspan="1" colspan="1"
                                       aria-label="CSS grade: activate to sort column ascending"
                                       style="width: 30px;text-align:center;">좋아요</th>
                                       
                                 </tr>
                              </thead>
                              <tbody>
                              	<c:forEach var="board" items="${boardList}">
	                              	<tr class="gradeA odd">
	                                    <td class="sorting_1">${board.getRow_num()}</td>
	                                    <td class=" ">
	                                    	<a href="<%= request.getContextPath() %>/DetailContents.board?boardIdx=${board.getBoard_id()}&categoryCode=${board.getBoardCategory_code()}&cpage=${cpage}&psize=${psize}">
	                                    		${board.getBoard_title_h()}${board.getBoard_title_t()}
	                                    	</a>
	                                    </td>
	                                    <td class=" ">${board.getMember_id()}</td>
	                                    <td class=" ">${board.getBoard_date()}</td>
	                                    <td class="center ">${board.getBoard_hit()}</td>
	                                    <td class="center ">${board.getBoard_like()}</td>
	                                </tr>
                              	</c:forEach>
                              </tbody>
                           </table>
                           <div class="row">
                              <div class="col-sm-7" >
                                 <div class="pagination" style="margin-left:30px;">
                                    <ul>
	                                	<c:if test="${cpage > 1}">
	                                    	<li><a href="<%=request.getContextPath()%>/AllList.board?cpage=${cpage-1}&psize=${psize}">[Prev]</a>
	                                    </c:if>
	                                    <c:forEach var="pageindex" begin="1" end="${pagecount}" step="1">
	                                    	<c:choose>
		                                 		<c:when test="${cpage==pageindex}">
		                                    		<li><a style="background-color:#dcdcdc;">[${pageindex}]</a><li>
		                                 		</c:when>
		                                 		<c:otherwise>
		                                    		<li><a href="<%=request.getContextPath()%>/AllList.board?cpage=${pageindex}&psize=${psize}">[${pageindex}]</a></li>
		                                 		</c:otherwise>
	                              			</c:choose>
	                                    </c:forEach>
	                                    <c:if test="${cpage < pagecount}">
	                                       <li><a href="<%=request.getContextPath()%>/AllList.board?cpage=${cpage+1}&psize=${psize}">[Next]</a></li>
	                                    </c:if>
	                                </ul>
                                 </div>
                              </div>
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