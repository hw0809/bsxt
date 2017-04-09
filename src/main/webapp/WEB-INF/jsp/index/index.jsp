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
<title>广州大学华软软件学院</title>
<style>
body,html {
	height: 100%;
}

body {
	padding: 0px;
	margin: 0;
	overflow: auto;
}

.page-content {
	background-color: #ffffff;
	position: relative;
	margin: 0;
	padding: 2px, 2px, 0px, 2px
}

.tab-content {
	border: 0px solid #05d0dc;
	width: 100%;
	padding: 3px 0px;
	position: relative
}

.tab-pane {
	width: 100%;
	height: 100%;
	overflow: hidden;
	position: relative
}
</style>

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

<body class="no-skin" name="parentBody">
	<div id="navbar" class="navbar navbar-default">
		<script type="text/javascript">
			try {
				ace.settings.check('navbar', 'fixed');
			} catch (e) {
			}
		</script>

		<div class="navbar-container" id="navbar-container">
			<button type="button" class="navbar-toggle menu-toggler pull-left"
				id="menu-toggler">
				<span class="sr-only">Toggle sidebar</span> <span class="icon-bar"></span>

				<span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>

			<div class="navbar-header pull-left">
				<a href="#" class="navbar-brand"> <small> <i
						class="fa fa-leaf"></i> 广州大学华软软件学院 </small> </a>
			</div>

			<div class="navbar-buttons navbar-header pull-right"
				role="navigation">
				<ul class="nav ace-nav">

					<li class="light-blue"><a data-toggle="dropdown" href="#"
						class="dropdown-toggle"> <span class="user-info"> <small>欢迎您</small>
								${user.name} </span><!--  <i class="ace-icon fa fa-caret-down"></i> --> </a>

						<!-- <ul
							class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
							<li><a href="#"> <i class="ace-icon fa fa-cog"></i>
									Settings </a></li>

							<li><a href="profile.html"> <i
									class="ace-icon fa fa-user"></i> Profile </a></li>

							<li class="divider"></li>

							<li><a href="#"> <i class="ace-icon fa fa-power-off"></i>
									Logout </a></li>
						</ul> --></li>
				</ul>
			</div>
		</div>
		<!-- /.navbar-container -->
	</div>

	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed');
			} catch (e) {
			}
		</script>

		<div id="sidebar" class="sidebar responsive">
			<script type="text/javascript">
				try {
					ace.settings.check('sidebar', 'fixed');
				} catch (e) {
				}
			</script>

			<div class="sidebar-shortcuts" id="sidebar-shortcuts">
				<!-- <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
					<button class="btn btn-success">
						<i class="ace-icon fa fa-signal"></i>
					</button>

					<button class="btn btn-info">
						<i class="ace-icon fa fa-pencil"></i>
					</button>

					<button class="btn btn-warning">
						<i class="ace-icon fa fa-users"></i>
					</button>

					<button class="btn btn-danger">
						<i class="ace-icon fa fa-cogs"></i>
					</button>
				</div> -->

				<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
					<span class="btn btn-success"></span> <span class="btn btn-info"></span>

					<span class="btn btn-warning"></span> <span class="btn btn-danger"></span>
				</div>
			</div>
			<!-- /.sidebar-shortcuts -->

			<ul class="nav nav-list">
				<c:if test="${identity != 'adminstrator'}">
				<li class=""><a href="#" class="dropdown-toggle"> <i
						class="menu-icon fa fa-desktop"></i> <!-- <i class="glyphicon glyphicon-star"></i> -->
						<span class="menu-text"> 个人信息 </span> <b
						class="arrow fa fa-angle-down"></b> </a> <b class="arrow"></b>

					<ul class="submenu">
						<li class=""><a
							href="javascript:addTabs({id: '2',title: '查看个人信息', close: true, url: 'user/userInfo'});">
								<i class="menu-icon fa fa-caret-right"></i> 查看个人信息 </a> <b
							class="arrow"></b></li>
					</ul></li>
				</c:if>

				<c:if test="${identity == 'student'}">
					<li class=""><a href="#" class="dropdown-toggle"> <i
							class="menu-icon fa fa-list"></i> <span class="menu-text">
								选题信息 </span> <b class="arrow fa fa-angle-down"></b> </a> <b class="arrow"></b>

						<ul class="submenu">
							<li class=""><a
								href="javascript:addTabs({id: 'allPaperPageId',title: '所有选题',close: true,url: 'paper/allPaperPage'});">
									<i class="menu-icon fa fa-caret-right"></i> 所有选题 </a> <b
								class="arrow"></b></li>

							<li class=""><a
								href="javascript:addTabs({id: 'myPaperPageId',title: '我的选题',close: true,url: 'paper/myPaperPage'});">
									<i class="menu-icon fa fa-caret-right"></i> 我的选题 </a> <b
								class="arrow"></b></li>
						</ul>
					</li>
				</c:if>

				<!-- <li class=""><a href="#" class="dropdown-toggle"> <i
						class="menu-icon fa fa-tag"></i> <span class="menu-text">
							文件下载 </span> <b class="arrow fa fa-angle-down"></b> </a> <b class="arrow"></b>

					<ul class="submenu">
						<li class=""><a
							href="javascript:addTabs({id: '5',title: '文件下载',close: true,url: ''});">
								<i class="menu-icon fa fa-caret-right"></i> 文件下载 </a> <b
							class="arrow"></b></li>

					</ul></li>

				<li class=""><a href="#" class="dropdown-toggle"> <i
						class="menu-icon fa fa-file-o"></i> <span class="menu-text">
							上传文档 </span> <b class="arrow fa fa-angle-down"></b> </a> <b class="arrow"></b>

					<ul class="submenu">
						<li class=""><a href="faq.html"> <i
								class="menu-icon fa fa-caret-right"></i> FAQ </a> <b class="arrow"></b>
						</li>


						<li class=""><a href="blank.html"> <i
								class="menu-icon fa fa-caret-right"></i> Blank Page </a> <b
							class="arrow"></b></li>
					</ul></li> -->

				<c:if test="${identity == 'adminstrator'}">
					<li class=""><a href="#" class="dropdown-toggle"> <i
							class="menu-icon fa fa-list-alt"></i> <span class="menu-text">
								班级管理 </span> <b class="arrow fa fa-angle-down"></b> </a> <b class="arrow"></b>

						<ul class="submenu">
							<li class=""><a
								href="javascript:addTabs({id: 'manageClassesId',title: '班级管理',close: true,url: 'classes/getPage'});">
									<i class="menu-icon fa fa-caret-right"></i> 班级管理 </a> <b
								class="arrow"></b></li>

						</ul></li>


					<li class=""><a href="#" class="dropdown-toggle"> <i
							class="menu-icon fa fa-calendar"></i> <span class="menu-text">
								教师管理 </span> <b class="arrow fa fa-angle-down"></b> </a> <b class="arrow"></b>

						<ul class="submenu">
							<li class=""><a
								href="javascript:addTabs({id: 'manageTeacherId',title: '教师管理',close: true,url: 'teacher/getPage'});">
									<i class="menu-icon fa fa-caret-right"></i> 教师管理 </a> <b
								class="arrow"></b></li>

						</ul></li>


					<li class=""><a href="#" class="dropdown-toggle"> <i
							class="menu-icon fa fa-tachometer"></i> <span class="menu-text">
								学生管理 </span> <b class="arrow fa fa-angle-down"></b> </a> <b class="arrow"></b>

						<ul class="submenu">
							<li class=""><a
								href="javascript:addTabs({id: '8',title: '学生管理',close: true,url: 'student/getPage'});">
									<i class="menu-icon fa fa-caret-right"></i> 学生管理 </a> <b
								class="arrow"></b></li>

						</ul></li>

					<li class=""><a href="#" class="dropdown-toggle"> <i
							class="menu-icon fa fa-bookmark-o"></i> <span class="menu-text">
								管理选题 </span> <b class="arrow fa fa-angle-down"></b> </a> <b class="arrow"></b>

						<ul class="submenu">
							<li class=""><a
								href="javascript:addTabs({id: 'submitPaperId',title: '审核选题 ',close: true,url: 'paper/reviewPaperPage'});">
									<i class="menu-icon fa fa-caret-right"></i> 审核选题 </a> <b
								class="arrow"></b></li>

						</ul>
					</li>

					<li class=""><a href="#" class="dropdown-toggle"> <i
							class="menu-icon fa fa-bookmark"></i> <span class="menu-text">
								答辩管理 </span> <b class="arrow fa fa-angle-down"></b> </a> <b class="arrow"></b>

						<ul class="submenu">
							<li class=""><a
								href="javascript:addTabs({id: 'addGroupId',title: '添加答辩小组',close: true,url: 'group/addGroupPage'});">
									<i class="menu-icon fa fa-caret-right"></i> 添加答辩小组 </a> <b
								class="arrow"></b></li>

							<li class=""><a
								href="javascript:addTabs({id: 'groupPageId',title: '答辩小组',close: true,url: 'group/groupPage'});">
									<i class="menu-icon fa fa-caret-right"></i> 答辩小组 </a> <b
								class="arrow"></b></li>

							<li class=""><a
								href="javascript:addTabs({id: 'reviewOfTeacherId',title: '添加评阅教师',close: true,url: 'reviewTeacherGroup/addReviewOfTeacherPage'});">
									<i class="menu-icon fa fa-caret-right"></i> 添加评阅教师 </a> <b
								class="arrow"></b></li>

							<li class=""><a
								href="javascript:addTabs({id: 'reviewOfTeacherPageId',title: '评阅教师',close: true,url: 'reviewTeacherGroup/reviewOfTeacherPage'});">
									<i class="menu-icon fa fa-caret-right"></i> 评阅教师 </a> <b
								class="arrow"></b></li>

						</ul></li>


				</c:if>
				<c:if test="${identity == 'teacher'}">
					<li class=""><a href="#" class="dropdown-toggle"> <i
							class="menu-icon fa fa-bookmark-o"></i> <span class="menu-text">
								选题管理 </span> <b class="arrow fa fa-angle-down"></b> </a> <b class="arrow"></b>

						<ul class="submenu">
							<li class=""><a
								href="javascript:addTabs({id: 'submitPaperId',title: '提交选题 ',close: true,url: 'paper/submitPaperPage'});">
									<i class="menu-icon fa fa-caret-right"></i> 提交选题 </a> <b
								class="arrow"></b></li>

							<li class=""><a
								href="javascript:addTabs({id: 'teacherPaperId',title: '我的选题',close: true,url: 'paper/teacherPaperPage'});">
									<i class="menu-icon fa fa-caret-right"></i> 我的选题 </a> <b
								class="arrow"></b></li>

							<li class=""><a
								href="javascript:addTabs({id: 'myStudentsPaperId',title: '我的学生',close: true,url: 'paper/myStudentsPaperPage'});">
									<i class="menu-icon fa fa-caret-right"></i> 我的学生 </a> <b
								class="arrow"></b></li>
						</ul>
					</li>

					<li class=""><a href="#" class="dropdown-toggle"> <i
							class="menu-icon fa fa-bookmark-o"></i> <span class="menu-text">
								评分 </span> <b class="arrow fa fa-angle-down"></b> </a> <b class="arrow"></b>

						<ul class="submenu">
							<li class=""><a
								href="javascript:addTabs({id: 'studentsPageId',title: '答辩评分 ',close: true,url: 'group/studentsPage'});">
									<i class="menu-icon fa fa-caret-right"></i> 答辩评分 </a> <b
								class="arrow"></b></li>

							<li class=""><a
								href="javascript:addTabs({id: 'reviewStudentsId',title: '阅卷评分',close: true,url: 'reviewTeacherGroup/reviewStudentsPage'});">
									<i class="menu-icon fa fa-caret-right"></i> 阅卷评分 </a> <b
								class="arrow"></b></li>

						</ul>
					</li>
				</c:if>

			</ul>
			<!-- /.nav-list -->

			<!-- <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
				<i class="ace-icon fa fa-angle-double-left"
					data-icon1="ace-icon fa fa-angle-double-left"
					data-icon2="ace-icon fa fa-angle-double-right"></i>
			</div> -->

			<script type="text/javascript">
				try {
					ace.settings.check('sidebar', 'collapsed');
				} catch (e) {
				}
			</script>
		</div>

		<div class="main-content">
			<div class="page-content" style="padding : 0px">
				<!-- style="padding : 0px" -->
				<!-- <div class="page-content-area">  -->
				<div class="row" style="margin : 0px">
					<div class="col-xs-12" style="padding : 0px">
						<div class="tabbable">
							<ul class="nav nav-tabs" role="tablist">
							</ul>
							<div class="tab-content" id="myTab"></div>
						</div>
						<!-- PAGE CONTENT ENDS -->
					</div>
					<!--/.col -->
				</div>
				<!-- /.row -->
				<!-- </div> -->
				<!-- /.page-content-area -->
			</div>
			<!-- /.page-content -->
		</div>
		<!-- /.main-content -->

	</div>
	<!-- /.main-container -->
	<!-- basic scripts -->

	<!--[if !IE]> -->
	<script src="${dormitory}/assets/js/jquery/jquery-2.1.1.min.js"></script>
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

	<script
		src="${dormitory}/assets/js/date-time/bootstrap-datepicker.min.js"></script>
	<script src="${dormitory}/assets/js/jqGrid/jquery.jqGrid.src.js"></script>
	<script src="${dormitory}/assets/js/jqGrid/i18n/grid.locale-cn.js"></script>

	<!-- ace scripts -->
	<script src="${dormitory}/assets/js/ace-elements.min.js"></script>
	<script src="${dormitory}/assets/js/ace.min.js"></script>

	<script src="${dormitory}/assets/js/bootbox.min.js"></script>

	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		var openIndex = function() {
			addTabs({
				id : '1',
				title : '首页',
				close : false,
				url : 'common/getNoticeIndex',
			});
		};

		jQuery(function($) {
			openIndex();

			toastr.options = {
				"positionClass" : "toast-top-right",//弹出窗的位置
				"showDuration" : "100",//显示的动画时间
				"hideDuration" : "100",//消失的动画时间
				"timeOut" : "2000", //展现时间
			};
		});

		var myConfirm = function(message, buttonType, myFunction) {
			bootbox.confirm({
				message : message,
				buttons : buttonType,
				callback : myFunction
			});
		};

		var myToastrError = function(message) {
			toastr.error(message);
		};

		var myToastrSuccess = function(message) {
			toastr.success(message);
		};
	</script>
</body>
</html>

