<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Koggiri Community</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>SmartEditor</title>
	<script type="text/javascript" src="<%= request.getContextPath() %>/SE2/js/HuskyEZCreator.js" charset="utf-8"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript">
		var oEditors = [];
		var num = 2;
		$(function(){
		      nhn.husky.EZCreator.createInIFrame({
		          oAppRef: oEditors,
		          elPlaceHolder: "smarteditor",
		          //SmartEditor2Skin.html 파일이 존재하는 경로
		          sSkinURI: "../../SE2/SmartEditor2Skin.html",  
		          htParams : {
		              // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
		              bUseToolbar : true,             
		              // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
		              bUseVerticalResizer : true,     
		              // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
		              bUseModeChanger : true,         
		              fOnBeforeUnload : function(){
		              }
		          }, 
		          /* fOnAppLoad : function(){
		              //기존 저장된 내용의 text 내용을 에디터상에 뿌려주고자 할때 사용
		              oEditors.getById["smarteditor"].exec("PASTE_HTML", [""]);
		          }, */
		          fCreator: "createSEditor2"
		      });
		    //저장버튼 클릭시 form 전송
		      $("#savebutton").click(function(){
		          oEditors.getById["smarteditor"].exec("UPDATE_CONTENTS_FIELD",[]);
		          $("#frm").submit();
		      });
		      $("#plusbutton").click(function(){
			  		$("#here").after("<tr><td></td><td style='padding-left: 30px;'>"
			        				+"<input type='file' name='file"+num+"' id='file"+num+"'/>"
			        				+"<input type='button' id='fileDel"+num+"' onclick='delSelf(this)' style='background-color:black;color:white;margin-left:3px;border-radius:15px;border:1px solid white;' value='x'><td></tr>");
			        num = num + 1;
			  });
		});	
		function delSelf(obj){
			console.log(obj);
	    	$(obj).parent().parent().detach();
	    	num = num - 1;
	    };
	</script>

<!-- CSS
================================================== -->
<link href='http://fonts.googleapis.com/css?family=Oswald'
   rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap-responsive.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/jquery.lightbox-0.5.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/custom-styles.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/form-elements.css">

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
         <div class="span8" style="margin-bottom:100px;">
            <!--Begin page content column-->
            <div class="container" style="width:115%;text-align:center;">
               <h2 class="title-bg">Notice Board</h2>
               <form id="frm" action="<%= request.getContextPath() %>/ReWrite.board?boardIdx=<%= request.getParameter("boardIdx")%>&categoryCode=1" method="post" enctype="multipart/form-data">
               		<table style="width:100%;">
                           <tr>
                              <td style="width:100px"><h4>제목</h4></td>
                              <td>
                                <input style="width:94%;height:30px;" type="text" id="boardTitle" name="boardTitle"/>
                              </td>
                           </tr>
                           <tr>
                              <td><h4>내용</h4></td>
                              <td>
                                 <textarea name=smarteditor id="smarteditor" rows="10" cols="100" style="width: 100%;height: 500px;"></textarea>
                              </td>
                           </tr>
                           <tr id=here>
                              <td><h4>파일첨부</h4></td>
                              <td style="padding-left: 30px;">
                                 <input type="button" class="btn btn-inverse" id="plusbutton" value="+  파일 추가" /><br>
                              </td>
                           </tr>
                           <tr id="delhere1">
                              <td></td>
                              <td style="padding-left: 30px;">
                              	<input type="file" id="file1" name="file1"/>
                              	<input type="button" id="fileDel1" style="background-color:black;color:white;margin-left:3px;border-radius:15px;border:1px solid white;" value="x" onclick="delSelf(this)">
                              </td>
                           </tr>
                           <tr>
                              <td></td>
                              <td style="padding-left: 30px;position:absolute;left:50%;">
                                 <input style="width:100px;height:40px;margin:20px" type="submit" class="btn btn-inverse" id="savebutton" value="글쓰기" /> 
                                 <input style="width:100px;height:40px;margin:20px" type="button" class="btn btn-inverse" id="canclebutton" onclick="location.href='<%= request.getContextPath() %>/DetailContents.board?boardIdx=<%= request.getParameter("boardIdx") %>&categoryCode=<%= request.getParameter("categoryCode") %>'" value="취소" />
                              </td>
                           </tr>
                        </table>
                     </form>
                  </div>
            </div>
      </div>
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