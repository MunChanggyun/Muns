<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
    	if(request.getAttribute("check").equals("true")){
    %>
    <style>
    		#no { visibility:visible; }
            #yes { display:none; }
            #useBtn {visiblity:visible;}
    </style>
    <% 	
    	}else{
    %>
    <style>
    		#no { display:none; }
            #yes { visibility:visible; }
            #useBtn {visiblity:hidden;}
    </style>  	  	
    <% 	
    	}
    %>
<head>
<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
//사용하기 클릭 시 부모창으로 값 전달 
function sendCheckValue(){
    // 중복체크 결과인 idCheck 값을 전달한다.
    window.opener.document.frm.idDuplication.value ="idCheck";
    // 회원가입 화면의 ID입력란에 값을 전달
    window.opener.document.frm.id.value = document.checkForm.idinput.value
    
    if (opener != null) {
        opener.chkForm = null;
        self.close();
    }    
} 

</script>
<body>
    <!-- Page Content
    ================================================== -->         
    <div id="no" class="input-prepend" style=" font-size:1.5em; color:#d8450b;">
    	<p>사용가능한 아이디 입니다.</p><br>
    	<input id="useBtn" class="btn btn-inverse" type="button" value="사용하기" onclick="sendCheckValue()">
		<input id="cancelBtn" class="btn btn-inverse"  type="button" value="취소" onclick="window.close()">
	</div>
	
	<div id="yes" class="input-prepend" style=" font-size:1.5em; color:#d8450b;">
    	<p>사용불가능한 아이디 입니다.</p><br>
		<input id="cancelBtn" class="btn btn-inverse"  type="button" value="취소" onclick="window.close()">
	</div>
</body>
</html>