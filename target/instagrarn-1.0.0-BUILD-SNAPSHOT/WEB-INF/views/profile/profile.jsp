<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Instagrarn</title>
	<link rel="icon" type="image/png"  href="${ pageContext.request.contextPath }/resources/images/favi.png"/>
	<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/profile.css">
	
	<script type="text/javascript">

	</script>
</head>
<body>

<jsp:include page="../header.jsp"/>
<img src="resources/upload/${ vo.filename }" width="200"/>

<main class="page">
저장됨
</main>
</body>
</html>
