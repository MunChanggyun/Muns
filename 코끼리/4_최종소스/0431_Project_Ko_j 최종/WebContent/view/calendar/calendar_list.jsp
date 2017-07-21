<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>My Apart Calendar</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- CSS
================================================== -->
<link href='http://fonts.googleapis.com/css?family=Oswald'
   rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap-responsive.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/flexslider.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/custom-styles.css">

<!-- Favicons
================================================== -->
<link rel="shortcut icon" href="<%= request.getContextPath() %>/img/titlelogo.ico">
<link rel="apple-touch-icon" href="<%= request.getContextPath() %>/img/titlelogo.ico">
<link rel="apple-touch-icon" sizes="72x72"   href="<%= request.getContextPath() %>/img/titlelogo.ico">
<link rel="apple-touch-icon" sizes="114x114" href="<%= request.getContextPath() %>/img/titlelogo.ico">



<!-- <script src="http://code.jquery.com/jquery-1.8.3.min.js"></script> -->
<script type="text/javascript" src="<%= request.getContextPath() %>/view/calendar/fullcalendar/lib/jquery.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/view/calendar/validation/jquery.validate.js"></script>
<script src="<%= request.getContextPath() %>/js/bootstrap.js"></script>
<script src="<%= request.getContextPath() %>/js/jquery.flexslider.js"></script>
<!-- <script src="js/jquery.custom.js"></script> -->
<link href='<%= request.getContextPath() %>/view/calendar/fullcalendar/fullcalendar.min.css' rel='stylesheet' />
<link href='<%= request.getContextPath() %>/view/calendar/fullcalendar/fullcalendar.print.min.css' rel='stylesheet' media='print' />
<script src='<%= request.getContextPath() %>/view/calendar/fullcalendar/lib/moment.min.js'></script>
<script src='<%= request.getContextPath() %>/view/calendar/fullcalendar/fullcalendar.min.js'></script>
<script src="<%= request.getContextPath() %>/view/calendar/fullcalendar/lib/jquery-ui.js"></script>
<link href='<%= request.getContextPath() %>/view/calendar/fullcalendar/lib/jquery-ui.theme.css' rel='stylesheet' />
<link href='<%= request.getContextPath() %>/view/calendar/fullcalendar/lib/jquery-ui.css' rel='stylesheet' />


<script type="text/javascript">


$(function() {     

   
   $('#start').datepicker({
       dateFormat: "yy-mm-dd"
    });
    $('#end').datepicker({
       dateFormat: "yy-mm-dd"
       
    });
    
  $.ajax({
      url : "<%=request.getContextPath()%>/CalendarList.etc",
      type : "POST",
      dataType : "json", 
      success : function(data) {
          title = [];
          start = [];
          end =[];
          idx = [];
           $.each(data, function(index, obj) {
                 title = obj.title;
                 start = obj.start;
                 end = obj.end;
                 idx=obj.idx;  
               });
         console.log(idx);
         console.log(title);
         console.log(start);
           hello();      
      }
      });     
     
     function hello(){
        console.log(title);
        console.log(start);
        console.log(idx);

        
      $('#calendar').fullCalendar({
         header: {
            left: "",
            center: 'title',
            right: 'prev,next today'
         },
         defaultDate: '2017-05-02', 
         editable: true,
         selectable:true,
         selectHelper : true,
         select : function(event, jsEvent, view) {
        	 var authority = "<%= session.getAttribute("session_authority") %>";
        	 if(authority==2){
        	 	$("#myModal").dialog('open')                    
                  return false;
        	 }else{
        		 return false;
        	 }
        	 },
            events: 
                  {
                     url : '<%=request.getContextPath()%>/CalendarList.etc' 
                  },
           eventClick: function(calEvent, jsEvent, view) {
              console.log(calEvent.idx);
         	 var authority = "<%= session.getAttribute("session_authority") %>";
         	if(authority==2){
              $("#myModalDelete").dialog('open')
                   $('#del').val(calEvent.idx)
         	}else{
         		return false;
         	}
         	}
         
      }); 
   }       

   $('#myModal').dialog({
      autoOpen:false,
         modal : true,
        draggable : true, //창 드래그 못하게
        resizable : false, //창 크기 고정
        height : 350,
        width : 550,  
       
   });
   $('#deletemodalclose').click(function(){
       $('#myModalDelete').dialog('close'); 
      
   });
   $('#insertmodalclose').click(function(){
       $('#myModal').dialog('close'); 
      
   });
   
   
    $('#myModalDelete').dialog({
       autoOpen:false,
          modal : true,
        draggable : true, //창 드래그 못하게
        resizable : false, //창 크기 고정
        title : '경고창',
        height : 270,
        width : 550,  
       
   });
   
    $("#vali").validate({
              
           rules: { //규칙
                 title: {
                   required : true,
                   minlength: 1,
                   maxlength: 20
               },
               start: {
                   required : true,
                   
               },
               end: {
                   required : true
               },
                        
           },        
           messages : {//규칙체크 실패시 출력될 메시지
              title: {
                   required : "일정을 입력하세요",
                   minlength : "일정은 {0}자 이상 입력해 주십시오.",
                   maxlength : "일정은 {0}자 이상 입력할 수 없습니다."
               },
               start: {
                   required : "시작날짜를 입력하세요",
                  
               },
               end: {
                   required : "종료날짜를 입력하세요"
               },
               
           }
       });

    
    
});
</script>

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

      <!-- Calendar
    ================================================== -->
      <div class="row no-margin">
         <div class="span6" style="width: 100%">
            <h1 class="title-bg" style="text-align: center;">
               My Apart Calendar: <small>일정을 확인해보세요!</small>
            </h1>
         </div>
      </div>
      <div align="center">
      <div id='calendar' style="font-size: large; width:700px; height: 700px"></div>
	  </div>
      <!-- Modal
    ================================================== -->
      <div class="row">
         <div class="span4"></div>
         <!-- Modal -->
         <!-- 일정 등록  -->
         <div id="myModal" tabindex="-1" role="dialog"
            aria-labelledby="myModalLabel">
            <div class="modal-header">

               <h3 id="myModalLabel">일 정 등 록</h3>
            </div>
            <form action="<%=request.getContextPath() %>/CalendarInsertOk.etc" method="post" id="vali">
               <div class="modal-body">
                  <input type="text" name="title" id="title" placeholder="일정을 입력해주세요"><br>
                  <input type="text" name="start" id="start" placeholder="일정의 시작"><br>
                  <input type="text" name="end" id="end" placeholder="일정의 마지막"><br>

               </div>
               <div class="modal-footer">
                  <input type="button" class="btn" value="취소" id="insertmodalclose">
                  <input type="submit" class="btn btn-inverse" value="등록">
               </div>
            </form>
         </div>

         <!-- 일정 삭제  -->
         <div id="myModalDelete" tabindex="-1" role="dialog"
            aria-labelledby="myModalLabel">
            <div class="modal-header">

               <h3 id="myModalLabel">일 정 삭 제</h3>
            </div>
            <form action="<%=request.getContextPath() %>/CalendarDeleteService.etc" method="post">
               <div class="modal-body">

                  <input type="hidden" name="idx" id="del" value="">                  
                  <h5 id="myModalLabel">일정을 삭제 하시겠습니까?</h5>
               </div>
               <div class="modal-footer">
                 
                  <input type="button" class="btn" value="취소" id="deletemodalclose">
                  <input type="submit" class="btn btn-inverse" value="삭제">
               </div>
            </form>
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