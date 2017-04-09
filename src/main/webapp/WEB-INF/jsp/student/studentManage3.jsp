<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="dormitory" value="${pageContext.request.contextPath}" />
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>jqGrid - Ace Admin</title>

<meta name="description"
	content="Dynamic tables and grids using jqGrid plugin" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

<!-- bootstrap & fontawesome -->
<link rel="stylesheet" href="${dormitory}/assets/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${dormitory}/assets/font-awesome/4.1.0/css/font-awesome.min.css" />

<!-- page specific plugin styles -->
<link rel="stylesheet" href="${dormitory}/assets/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${dormitory}/assets/css/datepicker.css" />
<link rel="stylesheet" href="${dormitory}/assets/css/ui.jqgrid.css" />

<!-- text fonts -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />

<!-- ace styles -->
<link rel="stylesheet" href="${dormitory}/assets/css/ace.min.css"
	id="main-ace-style" />

<link rel="stylesheet" href="${dormitory}/assets/css/ace-skins.min.css" />
<link rel="stylesheet" href="${dormitory}/assets/css/ace-rtl.min.css" />

<!-- ace settings handler -->
<script src="${dormitory}/assets/js/ace-extra.min.js"></script>

</head>

<body>

	<!-- PAGE CONTENT BEGINS -->

	<form class="cmxform" role="form" id="commitForm">
		<div class="form-group">

			<div class="col-sm-4">
				<input type="text" id="account" name="account" placeholder="学号" />
			</div>
		</div>

		<div class="form-group">

			<div class="col-sm-4">
				<input type="text" id="name" name="name" placeholder="姓名" />
			</div>
		</div>
		<div>
		<input class="submit" type="submit" value="提交">
	</div>
	</form>


	


	<!-- basic scripts -->

	<script src="${dormitory}/assets/js/jquery/jquery-2.1.1.min.js"></script>
	<script src="${dormitory}/assets/js/jquery.validate.js"></script>
	<script src="${dormitory}/assets/js/messages_zh.js"></script>

	<script src="${dormitory}/assets/bootstrap/3.2.0/js/bootstrap.min.js"></script>

	<!-- page specific plugin scripts -->
	<script
		src="${dormitory}/assets/js/date-time/bootstrap-datepicker.min.js"></script>
	<script src="${dormitory}/assets/js/jqGrid/jquery.jqGrid.src.js"></script>
	<script src="${dormitory}/assets/js/jqGrid/i18n/grid.locale-cn.js"></script>

	<!-- ace scripts -->
	<script src="${dormitory}/assets/js/ace-elements.min.js"></script>
	<script src="${dormitory}/assets/js/ace.min.js"></script>

	<script src="${dormitory}/assets/js/bootbox.js"></script>

	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		$().ready(function() {
			$("#commitForm").validate({
				rules : {
					account : "required",
					name : "required",
				},
				messages : {
					account : "请输入您的名字",
					name : "请输入您的姓氏",
				}
			});
		});
	</script>
</body>
</html>
