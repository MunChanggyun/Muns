<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<link href='./fullcalendar/fullcalendar.min.css' rel='stylesheet' />
<link href='./fullcalendar/fullcalendar.print.min.css' rel='stylesheet'media='print' />
<script src='./fullcalendar/lib/moment.min.js'></script>
<script src='./fullcalendar/lib/jquery.min.js'></script>
<script src='./fullcalendar/fullcalendar.min.js'></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.min.js"></script>
<link
   href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"
   type="text/css" rel="stylesheet" />
   
<script>

 $(function() {
	
	 $('#start').datepicker({
		 dateFormat: "yy-mm-dd"
		


		
	 });
	 $('#end').datepicker({
		 dateFormat: "yy-mm-dd"
		 
	 });
	 
   $.ajax({
		url : "CalendarList.etc",
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
	            /*   $('#delete').value(idx); */
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
			defaultDate: '2017-04-22', 
			editable: true,
			selectable:true,
			selectHelper : true,
			select : function(event, jsEvent, view) {
	               //set the values and open the modal
	               $("#eventContent").dialog('open')
	                 //autoOpen:false,
	            	 /*  modal : true,
	                  draggable : true, //창 드래그 못하게
	                  resizable : false, //창 크기 고정
	                  title : '일정만들기',
	                  height : 600,
	                  width : 550,  */
					 
	               //});
	               return false;
	            },
		      events: 
		    	 
						{
							url : 'CalendarList.etc' 
						},
			  eventClick: function(calEvent, jsEvent, view) {
				  console.log(calEvent.idx);
				  
				  $("#delete").dialog('open')
		       		$('#del').val(calEvent.idx)

		      }
						
		
						
		}); 
	}
		 
 
 	$('#eventContent').dialog({
 		 autoOpen:false,
   	     modal : true,
         draggable : true, //창 드래그 못하게
         resizable : false, //창 크기 고정
         title : '일정만들기',
         height : 600,
         width : 550,  
		 
 	});
 	 $('#delete').dialog({
 		 autoOpen:false,
   	     modal : true,
         draggable : true, //창 드래그 못하게
         resizable : false, //창 크기 고정
         title : '경고창',
         height : 200,
         width : 550,  
		 
 	});
 
 
 });
</script>
<style>
body {
	margin: 40px 10px;
	padding: 0;
	font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
	font-size: 14px;
}

#calendar {
	max-width: 900px;
	margin: 0 auto;
}
</style>
</head>
<body>
	
	
 
	<div id="eventContent" >날짜 입력
			<form action="CalendarInsert.etc" method="post">
				<fieldset>
					
					 <input type="text" name="title" >	일정 내용	
					 <input type="text" name="start" id="start" value="시작날짜"> 시작 날짜
					 <input type="text" name="end" id="end" value="종료날짜"> 종료 날짜
					 <input type="submit" value="전송">
				</fieldset>
			</form>
		</div>
	
	<div id="delete">
		<form action="CalendarDeleteService.etc" method="post" name="cal">
			<fieldset>
				
				<legend>경고!! 삭제 하시겠습니까</legend>
				
					<input type="text" name="idx" id="del" value=>
					<input type="submit" value="삭제">
					
			</fieldset>
		</form>
	</div>
	
	
    
	<div id='calendar'>
	
		
	</div>

</body>

</html>