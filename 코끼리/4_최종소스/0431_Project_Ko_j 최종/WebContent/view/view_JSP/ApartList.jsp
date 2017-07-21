<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Apart List Page</title>

<script type="text/javascript">
console.log('jsp까지 들어옴');

</script>

</head>
<body>

	<c:set var="apartlist" value="${requestScope.apartlist}"></c:set>
	
	<c:forEach var="list" items="${apartlist}">
	
	<li><a href="${list.apartment_url}" style="font-size: 15px"><i class="icon-plus-sign"></i>${list.apartment_name}</a></li>
	
    
    </c:forEach>


</body>
</html>