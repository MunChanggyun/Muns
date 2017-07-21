<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<body>
    <!-- Page Content
    ================================================== -->     
	    <div class="well" style="width:214px; height:30px; ">
	        <a rel="tooltip" title="id" style="font-size:30px"><%=request.getAttribute("id") %></a>
            <a href="<%= request.getContextPath() %>/view/member/member_pwd_search.jsp" style="color: gray;">비밀번호 찾기</a> |
	    </div>
</body>