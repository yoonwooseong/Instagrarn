<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>
<head>
	<title>Instagrarn</title>
	<link rel="icon" type="image/png"  href="${ pageContext.request.contextPath }/resources/images/favi.png"/>
	<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/profile.css">
	
	<script type="text/javascript">
		function btn_post(){
			document.getElementById("group1").style="border-top: 1px solid rgba(var(--i1d,38,38,38),1)";
			document.getElementById("group2").style="border-top:";
			document.getElementById("user_post").style="display:block";
			document.getElementById("user_tag").style="display:none";
		}
		
		function btn_tag(){
			document.getElementById("group1").style="border-top:";
			document.getElementById("group2").style="border-top: 1px solid rgba(var(--i1d,38,38,38),1)";
			document.getElementById("user_post").style="display:none";
			document.getElementById("user_tag").style="display:block";
		}
		
		function post_click(count){
			alert(count)
			document.getElementById("post_info_"+count).style.background = "#00000070";
			
		}
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
					<div class="user_id">
					u_zin19
					</div>
					<div class="profile_edit">
						<div class="profile_edit_box">
							<a class="profile_edit_text" href="/instagrarn/account/edit">프로필 편집</a>
						</div>
					</div>
					<div class="setting">
					<img src="${ pageContext.request.contextPath }/resources/images/setting.png" alt="setting_icon"
						style="width:33px; height:33px; padding-left:8px;">
					</div>
				</div>
				<div class="profile_middle">
					<div class="post">
						<span class="profile_middle_text">게시물<span class="post_num">${post_num}</span></span>
						
					</div>
					<div class="follow">
						<span class="profile_middle_text">팔로워<span class="follow_num">12</span></span>
					</div>
					<div class="following">
						<span class="profile_middle_text">팔로워<span class="following_num">12</span></span>						
					</div>
				</div>
				<div class="profile_last">
				YUJIN
				</div>
			</div>
		</div>
		
		<div class="menu_bar">
			<div class="menu_bar_size">
				<div class="group1" id="group1" style="border-top:1px solid rgba(var(--i1d,38,38,38),1);" onclick="btn_post();">
					<div class="btn_post">
					<img src="${ pageContext.request.contextPath }/resources/images/post.png" alt="myInfo" width="12" style="margin-top:1.5px;" class="btn_img">
					<div class="btn_text">게시물</div>
					</div>
				</div>
				<div class="margin"></div>
				<div class="group2" id="group2" onclick="btn_tag();">
					<div class="btn_post">
					<img src="${ pageContext.request.contextPath }/resources/images/post_button4.png" alt="myInfo" height="15" class="btn_img">
					<div class="btn_text">태그됨</div>
					</div>
				</div>
			</div>

		</div>
		
		
      <!-- 사진 두개만 마진주고 마지막은 안주게하기 -->
		<div class="user_post1" id="user_post">
			<c:forEach var="list" items="${list}" varStatus="status">
				<c:choose>
					<c:when test="${status.count % 3 == 0}">
						<div id="post_info_img" style="float:left;" >
						<img src="resources/post/${list.img}" width="293" height="293" style="margin-bottom:23px;"/></div>
					</c:when>
					<c:otherwise>
						<div id="post_info_img" style="float:left;">
						<img src="resources/post/${list.img}" width="293" height="293" style="margin-right:13px; margin-bottom:23px;"/></div>
					</c:otherwise>
				</c:choose>			
			</c:forEach>
			
		<div class="user_post2" id="user_post">
			<c:forEach var="list" items="${list}" varStatus="status">
				<c:choose>
					<c:when test="${status.count % 3 == 0}">
						<div id="post_info_${status.count}" style="width:293px;height:293px;float:left;
						background:#00000000; margin-bottom:23px;"onclick="post_click(${status.count});"></div>
					</c:when>
					<c:otherwise>
						<div id="post_info_${status.count}" style="width:293px;height:293px;
						margin-right:13px; float:left; background:#00000000; margin-bottom:23px;"onclick="post_click(${status.count});"></div>
					</c:otherwise>
				</c:choose>
				
			</c:forEach>
		</div>
		</div>
		<div class="user_tag" id="user_tag" style="display:none;">
			아직 태그된게 없음
		</div>
	</div>
</main>

</body>
</html>
