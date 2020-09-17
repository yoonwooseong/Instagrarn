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
<main class="page">
	<div class="main">
		<div class="base">
			<div class="profile_img_box">
				<div class="profile_img">
				<img src="${ pageContext.request.contextPath }/resources/images/IconME.png" alt="myInfo" width="150">
				</div>
			</div>
			<div class="profile_text_box">
				<div class="profile_first">
				U_Zin19
				</div>
				<div class="profile_middle">
				게시물
				</div>
				<div class="profile_last">
				YUJIN
				</div>
			</div>
		</div>
	
		<c:forEach var="list" items="${list}">
		<img src="resources/post/${list.img}" width="200"/>
		</c:forEach>
	</div>
</main>
</body>
</html>
