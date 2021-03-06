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
				<div class="widget-box">
					<div class="widget-header">
						<h4 class="widget-title">教师信息</h4>
					</div>
					<div class="widget-body">
						<div class="widget-main no-padding">


							<!-- PAGE CONTENT BEGINS -->
							<form id="submitForm" class="form-horizontal" role="form">
								<fieldset>
									<div class="row">
										<div class="col-xs-12">
											<input type="hidden" id="id" name="id" value="${teacher.id }" />

											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right"
													for="form-field-1"> 工号 </label>

												<div class="col-sm-4">
													<input class="form-control" type="text" placeholder="学号"
														value="${teacher.account }" readonly=""
														class="col-xs-10 col-sm-5" />
												</div>
											</div>

											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right"
													for="form-field-1"> 姓名 </label>

												<div class="col-sm-4">
													<input class="form-control" type="text" placeholder="姓名"
														readonly="" value="${teacher.name }" class="form-control" />
												</div>
											</div>


											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right"
													for="form-field-1"> 邮箱 </label>

												<div class="col-sm-4">
													<input type="text" placeholder="邮箱" name="email" id="email"
														readonly="" value="${teacher.email }" class="form-control" />
												</div>
											</div>

											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right"
													for="form-field-1"> 手机 </label>

												<div class="col-sm-4">
													<input type="text" placeholder="手机" name="tel" id="tel"
														readonly="" value="${teacher.tel }" class="form-control" />
												</div>
											</div>

											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right"
													for="desc"> 简介</label>
												<div class="col-sm-4">
													<textarea readonly="" rows="6" class="form-control" name="desc"
														placeholder="简介">${teacher.desc }</textarea>
												</div>
											</div>
										</div>
									</div>
								</fieldset>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- basic scripts -->

	<!--[if !IE]> -->
	<script src="${dormitory}/assets/js/jquery/jquery-2.1.1.min.js"></script>
	<!-- <![endif]-->

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
	<script src="${dormitory}/assets/js/jquery.validate.js"></script>
	<script src="${dormitory}/assets/js/messages_zh.js"></script>

	<script src="${dormitory}/assets/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	<script src="${dormitory}/assets/js/bootstrap-tab.js"></script>
	<script src="${dormitory}/assets/js/bootstrap-tab-custom.js"></script>
	<script src="${dormitory}/assets/js/tabdrop.js"></script>

	<!-- page specific plugin scripts -->
	<script
		src="${dormitory}/assets/js/date-time/bootstrap-datepicker.min.js"></script>

	<script
		src="${dormitory}/assets/js/date-time/bootstrap-datepicker.min.js"></script>
	<script src="${dormitory}/assets/js/jqGrid/jquery.jqGrid.src.js"></script>
	<script src="${dormitory}/assets/js/jqGrid/i18n/grid.locale-cn.js"></script>

	<!-- ace scripts -->
	<script src="${dormitory}/assets/js/ace-elements.min.js"></script>
	<script src="${dormitory}/assets/js/ace.min.js"></script>

	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		jQuery(function($) {
			$("#submitForm").validate({
				rules : {
					email : {
						email : true,
					},
					tel : {
						digits : true,
						maxlength : 11,
						minlength : 11
					}
				},
			});
		});

		var submitForm = function() {
			if ($("#submitForm").valid()) {
				$.ajax({
					type : "post",
					url : "${dormitory}/user/saveTeacher",
					data : $("#submitForm").serialize(),
					dataType : "json",
					success : function(data) {
						if (data.flag) {
							window.parent.myToastrSuccess(data.msg);
							location.reload();
						}
					}
				});
			}
		};
	</script>
</body>
</html>
