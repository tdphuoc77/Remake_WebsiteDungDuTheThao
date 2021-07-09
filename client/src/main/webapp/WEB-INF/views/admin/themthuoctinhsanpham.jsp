<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ - Quản trị</title>
<link href="<c:url value="/resources/admin/css/styles.css"/> "
	rel="stylesheet" />
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
	crossorigin="anonymous"></script>
	

</head>
<body class="sb-nav-fixed">
	<%@include file="/WEB-INF/views/layouts/quantri/header.jsp"%>
	
	<%@include file="/WEB-INF/views/quantri/themthuoctinhsanpham.jsp"%>
	
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="<c:url value="/resources/admin/js/scripts.js"/> "></script>

	
	<decorator:getProperty property="page.script"></decorator:getProperty>
<!-- 	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script> -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js"></script>
    <script src="<c:url value="/resources/phantrang/jquery.twbsPagination.js"/>"></script>
</body>
</html>