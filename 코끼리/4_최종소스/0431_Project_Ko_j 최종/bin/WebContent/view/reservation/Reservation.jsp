<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>KOGGIRI</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- CSS
================================================== -->
<link href='http://fonts.googleapis.com/css?family=Oswald'
	rel='stylesheet' type='text/css'>
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

<!-- Jquery -->
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet"	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">


<style>
@import url(http://fonts.googleapis.com/earlyaccess/nanumgothic.css);

.span4 h5 {
	font-family: ‘Nanum Gothic’, sans-serif;
}
@import
(
'https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.0/css/bootstrap.min.css'
)
.
funkyradio
div
{
clear:
both;

/*margin: 0 50px;*/
overflow
:
 
hidden
;


}
.funkyradio label {
	/*min-width: 400px;*/
	width: 100%;
	border-radius: 3px;
	border: 1px solid #D1D3D4;
	font-weight: normal;
}

.funkyradio input[type="radio"]:empty, .funkyradio input[type="checkbox"]:empty
	{
	display: none;
}

.funkyradio input[type="radio"]:empty ~ label, .funkyradio input[type="checkbox"]:empty 
	~ label {
	position: relative;
	line-height: 2.5em;
	text-indent: 3.25em;
	margin-top: 2em;
	cursor: pointer;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

.funkyradio input[type="radio"]:empty ~ label:before, .funkyradio input[type="checkbox"]:empty 
	~ label:before {
	position: absolute;
	display: block;
	top: 0;
	bottom: 0;
	left: 0;
	content: '';
	width: 2.5em;
	background: #D1D3D4;
	border-radius: 3px 0 0 3px;
}

.funkyradio input[type="radio"]:hover:not (:checked ) ~ label:before,
	.funkyradio input[type="checkbox"]:hover:not (:checked ) ~ label:before
	{
	content: '\2714';
	text-indent: .9em;
	color: #C2C2C2;
}

.funkyradio input[type="radio"]:hover:not (:checked ) ~ label,
	.funkyradio input[type="checkbox"]:hover:not (:checked ) ~ label {
	color: #888;
}

.funkyradio input[type="radio"]:checked ~ label:before, .funkyradio input[type="checkbox"]:checked 
	~ label:before {
	content: '\2714';
	text-indent: .9em;
	color: #333;
	background-color: #ccc;
}

.funkyradio input[type="radio"]:checked ~ label, .funkyradio input[type="checkbox"]:checked 
	~ label {
	color: #777;
}

.funkyradio input[type="radio"]:focus ~ label:before, .funkyradio input[type="checkbox"]:focus 
	~ label:before {
	box-shadow: 0 0 0 3px #999;
}

.funkyradio-success input[type="radio"]:checked ~ label:before,
	.funkyradio-success input[type="checkbox"]:checked ~ label:before {
	color: #fff;
	background-color: #d8450b;
}
</style>

<script type="text/javascript">

 
	$( function() {
       $( "#reservation_time" ).datepicker({
    	   dateFormat: "yy-mm-dd"
       });
       
       $('.span8').click(function(){
     		var form_data = {
     				reservationdate : $('#reservation_time').val(),
     				facilityname : $('#facility_id').val()
     		}
     		var time =[];		
     		$.ajax(
     				{
     					type : "post",
     					url : "../../ReservationTimeList",
     					data:form_data,
     					dataType:"json",
     					success:function(responsedata){
     						$.each(responsedata,function(index,obj){
     							if(obj.time_start==$('input[type=radio][name=time_start]:checked').val()){
     								alert("해당예약 시간에 이미 등록되어 있습니다.");
     								$('input[type=radio][name=time_start]:checked').attr("checked",false);
     								$('input[type=radio][name=time_start]:checked').attr("disabled",true);
     							}
     						}),
     						console.log("선택된값 : "+$('input[type=radio][name=time_start]:checked').val())

     					},
     					 error:function(xhr){
   		    		  }
     				
     				}
     			)
     		
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

		<!-- Page Content
    ================================================== -->

		<!-- Title Header -->
		<div class="row">
			<div class="span12">
				<h2>Facility of Reservation</h2>
			</div>
			<div class="span4">
				<h5>사용하실 시설을 고르시오</h5>

				<form action="<%=request.getContextPath()%>/Reservationinsert.etc" method="post">
					<select class="selectpicker" data-style="btn-warning" name="facility_id" id="facility_id">
						<option value="헬스장">헬스장</option>
						<option value="수영장">수영장</option>
						<option value="테니스장">테니스장</option>
						<option value="스크린 골프장">스크린 골프장</option>
						<option value="탁구장">탁구장</option>
					</select>

					<h5>예약 날짜를 고르시오</h5>
					<input type="text" id="reservation_time" name="reservation_time"><br>
			</div>
			
			<div class="span8">
				<h4>이용 가능 시간은 12:00부터 20:00까지 입니다</h4>
			</div>
			
			<div class="span8">
				<div class="span3" style="margin-right: 100px; margin-left: 0px">
					<div class="funkyradio">
						<div class="funkyradio-success">
							<input type="radio" name="time_start" id="radio1"
								value="12:00 - 13:00" /> <label for="radio1">12:00 -
								13:00</label>
						</div>
						<div class="funkyradio-success">
							<input type="radio" name="time_start" id="radio3"
								value="14:00 - 15:00" /> <label for="radio3">14:00 -
								15:00</label>
						</div>
						<div class="funkyradio-success">
							<input type="radio" name="time_start" id="radio5"
								value="16:00 - 17:00" /> <label for="radio5">16:00 -
								17:00</label>
						</div>
						<div class="funkyradio-success">
							<input type="radio" name="time_start" id="radio7"
								value="18:00 - 19:00" /> <label for="radio7">18:00 -
								19:00</label>
						</div>
					</div>
				</div>
				<div class="span4"></div>
				<div class="span3">
					<div class="funkyradio">
						<div class="funkyradio-success">
							<input type="radio" name="time_start" id="radio2"
								value="13:00 - 14:00" /> <label for="radio2">13:00 -
								14:00</label>
						</div>
						<div class="funkyradio-success">
							<input type="radio" name="time_start" id="radio4"
								value="15:00 - 16:00" /> <label for="radio4">15:00 -
								16:00</label>
						</div>
						<div class="funkyradio-success">
							<input type="radio" name="time_start" id="radio6"
								value="17:00 - 18:00" /> <label for="radio6">17:00 -
								18:00</label>
						</div>
						<div class="funkyradio-success">
							<input type="radio" name="time_start" id="radio8"
								value="19:00 - 20:00" /> <label for="radio8">19:00 -
								20:00</label>
						</div>
					</div>
				</div>
			</div>
			<p style="color: red; font-size: 15px; text-align: left">
				※ 한 사람 당 한 시간만 선택할 수 있습니다. <br> ※ 이미 선택되어 있는 시간은 선택할 수 없습니다.
			</p>
			
			<input type="hidden" id="member_id" name="member_id" value="${sessionScope.session_id}">
			<input type="submit" class="btn btn-warning" style="float: right;"value="예약하기">
			</form>	
			
			<form action="<%=request.getContextPath()%>/Reservationlist.etc" method="post">
				<input type="hidden" name="member_id" value="${sessionScope.session_id}"> 
				<input type="submit" class="btn btn-danger" value="내 예약 현황 확인">
			</form>
			
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