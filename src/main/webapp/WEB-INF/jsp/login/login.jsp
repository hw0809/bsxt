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
<title>Login Page - Ace Admin</title>

<meta name="description" content="User login page" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

<!-- bootstrap & fontawesome -->
<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="assets/font-awesome/4.1.0/css/font-awesome.min.css" />

<!-- text fonts -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />

<!-- ace styles -->
<link rel="stylesheet" href="assets/css/ace.min.css" />

<!--[if lte IE 9]>
			<link rel="stylesheet" href="assets/css/ace-part2.min.css" />
		<![endif]-->
<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />

<link rel="stylesheet" href="assets/css/toastr/toastr.css" />

<!--[if lte IE 9]>
		  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
		<![endif]-->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

<!--[if lt IE 9]>
		<script src="assets/js/html5shiv.js"></script>
		<script src="assets/js/respond.min.js"></script>
		<![endif]-->
</head>

<body class="login-layout">
	<div class="main-container">
		<div class="main-content">
			<div class="row">
				<div class="col-sm-10 col-sm-offset-1">
					<div class="login-container">
						<div class="center">
							<h1>
								<i class="ace-icon fa fa-leaf green"></i>
								<!-- <span class="red">Ace</span> -->
								<span class="white" id="id-text2">毕设论文管理系统</span>
							</h1>
								<h4 class="blue" id="id-company-text"><!-- &copy; --><!--  Company Name --></h4> 
						</div>

						<div class="space-6"></div>

						<div class="position-relative">
							<div id="login-box"
								class="login-box visible widget-box no-border">
								<div class="widget-body">
									<div class="widget-main">
										<h4 class="header blue lighter bigger">
											<i class="ace-icon fa fa-coffee green"></i>请输入用户名和密码
										</h4>

										<div class="space-6"></div>

										<form id="loginForm">
											<fieldset>
												<label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="text" class="form-control" placeholder="account"
														name="account" /> <i class="ace-icon fa fa-user"></i> </span> </label>
												<label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="password" class="form-control" name="password"
														placeholder="Password" /> <i class="ace-icon fa fa-lock"></i>
												</span> </label>

												<div class="space"></div>

												<div class="clearfix">

													<button type="button" onclick="submitForm()"
														class="width-35 pull-right btn btn-sm btn-primary">
														<i class="ace-icon fa fa-key"></i> <span
															class="bigger-110">Login</span>
													</button>
												</div>

												<div class="space-4"></div>
											</fieldset>
										</form>

										<div class="social-or-login center">
											<!-- <span class="bigger-110">Or Login Using</span> -->
										</div>

										<div class="space-6"></div>

									</div>
									<!-- /.widget-main -->

								</div>
								<!-- /.widget-body -->
							</div>
							<!-- /.login-box -->


						</div>
						<!-- /.position-relative -->

					</div>
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /.main-content -->
	</div>
	<!-- /.main-container -->

	<form id="form" method="post">
		<input hidden="true" id="id" name="id">
		<input hidden="true" id="account" name="account">
		<input hidden="true" id="password" name="password">
		<input hidden="true" id="loginType" name="loginType">
	</form>

	<!--[if !IE]> -->
	<script src="assets/js/jquery/jquery-2.1.1.min.js"></script>
	<!-- <![endif]-->

	<script src="assets/js/toastr/toastr.js"></script>

	<!--[if IE]>
		<script src="assets/js/jquery/jquery-1.11.1.min.js"></script>
		<![endif]-->

	<!--[if !IE]> -->
	<script type="text/javascript">
		window.jQuery
				|| document.write("<script src='assets/js/jquery.min.js'>"
						+ "<"+"/script>");
	</script>
	<!-- <![endif]-->

	<!--[if IE]>
		<script type="text/javascript">
		 window.jQuery || document.write("<script src='assets/js/jquery1x.min.js'>"+"<"+"/script>");
		</script>
		<![endif]-->

	<script type="text/javascript">
		if ('ontouchstart' in document.documentElement)
			document
					.write("<script src='assets/js/jquery.mobile.custom.min.js'>"
							+ "<"+"/script>");
	</script>

	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		jQuery(function($) {
			toastr.options = {
				"positionClass" : "toast-top-right",//弹出窗的位置
			};
		});

		var submitForm = function() {
			$.ajax({
				url : "register/checkLogin",
				data : $('#loginForm').serialize(),
				type : "POST",
				success : function(data) {
					if (data.flag) {
						$("#form").attr("action", "register/getIndexPage");
						$("#id").val(data.data.id);
						$("#account").val(data.data.account);
						$("#password").val(data.data.password);
						$("#loginType").val(data.data.loginType);
						$("#form").submit();
					} else {
						toastr.error(data.data);
					}
				}
			});
		};
	</script>
</body>
</html>

