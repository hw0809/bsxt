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

<link rel="stylesheet" href="${dormitory}/assets/css/toastr/toastr.css" />

<!--[if lte IE 9]>
			<link rel="stylesheet" href="${dormitory}/assets/css/ace-part2.min.css" />
		<![endif]-->
<link rel="stylesheet" href="${dormitory}/assets/css/ace-skins.min.css" />
<link rel="stylesheet" href="${dormitory}/assets/css/ace-rtl.min.css" />

<!--[if lte IE 9]>
		  <link rel="stylesheet" href="${dormitory}/assets/css/ace-ie.min.css" />
		<![endif]-->

<!-- inline styles related to this page -->

<!-- ace settings handler -->
<script src="${dormitory}/assets/js/ace-extra.min.js"></script>

<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

<!--[if lte IE 8]>
		<script src="${dormitory}/assets/js/html5shiv.min.js"></script>
		<script src="${dormitory}/assets/js/respond.min.js"></script>
		<![endif]-->
</head>

<body>

	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed');
			} catch (e) {
			}
		</script>
		<div class="main-content">
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<!-- PAGE CONTENT BEGINS -->
						<div class="widget-box">
							<div class="widget-header">
								<i class="ace-icon fa fa-list-alt"></i>
								<h4 class="widget-title">教师信息</h4>
							</div>

							<div class="widget-body">
								<div class="widget-main">

									<form class="form-horizontal" role="form" id="commitForm">
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right"
												for="account">工号</label>

											<div class="col-sm-4">
												<input type="text" id="account" name="account" value="${teacher.account}"
													placeholder="工号" class="form-control" />
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right"
												for="name">姓名</label>

											<div class="col-sm-4">
												<input type="text" id="name" name="name" placeholder="姓名" value="${teacher.name}"
													class="form-control" />
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right">系别</label>
											<div class="col-sm-4">
												<select class="form-control" id="department"
													name="departmentCode">
												</select>
											</div>
										</div>
									</form>

								</div>
							</div>
						</div>
						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button class="btn btn-info" type="button"
									onclick="submitForm()">
									<i class="ace-icon fa fa-check bigger-110"></i> 提交
								</button>

								&nbsp; &nbsp; &nbsp;
								<button class="btn" type="reset">
									<i class="ace-icon fa fa-undo bigger-110"></i> 重置
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- basic scripts -->

	<!--[if !IE]> -->
	<script src="${dormitory}/assets/js/jquery/jquery-2.1.1.min.js"></script>
	<script src="${dormitory}/assets/js/jquery.validate.js"></script>
	<script src="${dormitory}/assets/js/messages_zh.js"></script>
	<!-- <![endif]-->

	<script src="${dormitory}/assets/js/toastr/toastr.js"></script>

	<!--[if IE]>
		<script src="${dormitory}/assets/js/jquery/jquery-1.11.1.min.js"></script>
		<![endif]-->

	<!--[if !IE]> -->
	<script type="text/javascript">
		window.jQuery
				|| document
						.write("<script src='${dormitory}/assets/js/jquery.min.js'>"
								+ "<"+"/script>");
	</script>
	<!-- <![endif]-->

	<!--[if IE]>
		<script type="text/javascript">
		 window.jQuery || document.write("<script src='${dormitory}/assets/js/jquery1x.min.js'>"+"<"+"/script>");
		</script>
		<![endif]-->

	<script type="text/javascript">
		if ('ontouchstart' in document.documentElement)
			document
					.write("<script src='${dormitory}/assets/js/jquery.mobile.custom.min.js'>"
							+ "<"+"/script>");
	</script>
	<script src="${dormitory}/assets/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	<script src="${dormitory}/assets/js/bootstrap-tab.js"></script>
	<script src="${dormitory}/assets/js/bootstrap-tab-custom.js"></script>
	<script src="${dormitory}/assets/js/tabdrop.js"></script>

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
		var code = "${teacher.department.departmentCode}";

		jQuery(function($) {
			init();
			initDepartments(code);

			toastr.options = {
				"positionClass" : "toast-top-right",//弹出窗的位置
			};

			$("#commitForm").validate({
				rules : {
					account : {
						digits : true,
						required : true,
						minlength : 9,
						maxlength : 9,
					},
					name : {
						required : true,
					}
				},
			});
		});

		var submitForm = function() {
			if (!$("#account").val()) {
				window.parent.myToastrError("请输入工号");
				$("#account").focus();
				return;
			}

			if (!$("#name").val()) {
				window.parent.myToastrError("请输入姓名");
				$("#name").focus();
				return;
			}

			if ($("#commitForm").valid()) {
				$.ajax({
					url : "${dormitory}/teacher/modify",
					type : "post",
					dataType : "json",
					data : $("#commitForm").serialize(),
					success : function(data) {
						if (data.flag) {
							window.parent.myToastrSuccess("修改成功,页面关闭");
							refreshTab("tab_manageTeacherId");
							closeTab("tab_eidtTeacherId");
						} else {
							window.parent.myToastrError(data.data);
						}
					}
				});
			}
		};

		var initDepartments = function(code) {
			$.ajax({
					url : "${dormitory}/classes/departmentsList",
					type : "post",
					data : {
						code : code
					},
					dataType : "json",
					success : function(data) {
						if (data != null) {
							var option = "<option value='" + data.departmentCode + "'>"
										+ data.name;
										+ "</option>";
							$("#department").append(option);
						}
						$("#department").val(code);
					}
				});
		
		/* 	$.ajax({
					url : "${dormitory}/teacher/departmentsList",
					type : "post",
					dataType : "json",
					success : function(data) {
						$.each(data,function() {
							var option = "<option value='" + this.departmentCode + "'>"
									+ this.name
									+ "</option>";
							$("#department").append(option);
						});
						$("#department").val(code);
					}
				}); */
		};
		
		var init = function() {
			$("#account").attr("readonly", true);
		};
	</script>
</body>
</html>
