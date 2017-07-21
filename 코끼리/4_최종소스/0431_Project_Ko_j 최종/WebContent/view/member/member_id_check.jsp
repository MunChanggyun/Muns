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
<script src="validation/jquery.validate.js"></script>

<title>아이디 중복 체크</title>
<style type="text/css">
	#wrap {
			
        	text-align :center;
        	
        }
        
    #chk{
    		text-align :center;
    	}
        
	#cancelBtn{
            visibility:visible;
		}
        
    #useBtn{
            visibility:visible;
        }
 
</style>    
<script type="text/javascript">

    	// 회원가입창의 아이디 입력란의 값을 가져온다.
        function pValue(){
        	console.log("회원가입창의 아이디 입력란의 값을 가져온다");
            document.checkForm.idinput.value = window.opener.document.frm.id.value;
        }
        
        // 아이디 중복체크
        function idCheck(){
        	console.log("중복체크 시작");
            var id = document.getElementById("userId").value;
 
            if (!id) {
                alert("아이디를 입력하지 않았습니다.");
                return false;
            }else
            {
        		$.ajax({				
        			url:"<%=request.getContextPath()%>/MemberIdCheck.member",
        			data:{
        					id : $("#userId").val()
        				  },
        			dataType: "text",
        			success: function(data){
        				
        				console.log(data);
                        $('#checkokdiv').html(data);
        				
        				
        			}
        							
        		});
        		
            	
            } 
        }
 
            
   </script>
</head>
<body onload="pValue()" style="background-color: white;">
<div id="wrap" class="#wrap" style="background-color: white; height:100%">
	<br>
	<b style="color: gray; font-size:20px;">아이디 중복체크</b>
	<div align="center">
	<hr size="1" width="460px" color="gray">
	</div>
	<br>
	<div>
		<form name="checkForm">
			<input type="text" class="input-prepend" width="10" name="idinput" id="userId" placeholder="ID 중복확인">
			<input type="button" class="btn btn-inverse" value="중복확인" style="margin-bottom: 10px" onclick="idCheck()">
		</form>
		<div id="checkokdiv" align="center"></div>
		
	</div>
</div>

</body>
</html>