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
	
	<%@include file="/WEB-INF/views/quantri/quanlynguoidung.jsp"%>
	
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
    
<script type="text/javascript">
		var totalPages = ${abstractDTO.totalPage};
		var currentPage = ${abstractDTO.page};
		var limit = ${abstractDTO.limit};
		$(function() {
			window.pagObj = $('#pagination').twbsPagination({
				totalPages : totalPages,
				visiblePages : 10,
				startPage : currentPage,
				onPageClick : function(event, page) {
					if (currentPage != page) {
						$('#limit').val(limit);
						$('#page').val(page);
						$('#formSubmit').submit();
					}
				}
			});
		});
		
		
// thay đổi trạng thái người dùng
		
		function myFunction(id) {
            var r = confirm("bạn có muốn chặn / bỏ chặn người dùng này không ?");
            if (r == true) {
                window.location.href= "http://localhost:8080/quan-tri/quan-ly-nguoi-dung/sua-trang-thai/"+id;
            } else{
				alert("Đã hủy thay đổi")
			}
          
        }
	</script>
</body>
</html>