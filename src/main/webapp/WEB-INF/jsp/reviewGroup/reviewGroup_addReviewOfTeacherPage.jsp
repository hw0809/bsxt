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

<!-- ace styles -->
<link rel="stylesheet" href="${dormitory}/assets/css/ace.min.css"
	id="main-ace-style" />

<link rel="stylesheet" href="${dormitory}/assets/css/toastr/toastr.css" />

<!--[if lte IE 9]>
			<link rel="stylesheet" href="${dormitory}/assets/css/ace-part2.min.css" />
		<![endif]-->
<link rel="stylesheet" href="${dormitory}/assets/css/ace-skins.min.css" />
<link rel="stylesheet" href="${dormitory}/assets/css/ace-rtl.min.css" />

<link rel="stylesheet"
	href="${dormitory}/assets/css/bootstrap-multiselect.css" />

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
						<form id="submitForm" class="form-horizontal" role="form">

							<div class="form-group">
								<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="grade">年份</label>
								<div class="col-xs-12 col-sm-9">
									<select id="grade" name="grade" class="selectpicker show-tick ">
									</select>
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-xs-12 col-sm-3 no-padding-right"
									for="classes">评阅教师</label>

								<div class="col-xs-12 col-sm-9">
									<select id="teachers" name="teachers" class="multiselect"
										multiple="">
										<c:forEach var="item" items="${teachers }">
											<option id="${item.id }" value="${item.id }">${item.name
												}</option>
										</c:forEach>
									</select>
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-xs-12 col-sm-3 no-padding-right"
									for="classes">班级</label>

								<div class="col-xs-12 col-sm-9">
									<select id="classes" name="classes" class="multiselect"
										onchange="getStudents()">
										<c:forEach var="item" items="${classes }">
											<option id="${item.id }" value="${item.id }">${item.name
												}</option>
										</c:forEach>
									</select>
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-xs-12 col-sm-3 no-padding-right"
									for="classes">学生</label>

								<div class="col-xs-12 col-sm-9">
									<select id="students" name="students" multiple="">
									</select>
								</div>
							</div>


						</form>

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
	<script src="${dormitory}/assets/js/bootstrap-multiselect.js"></script>

	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		jQuery(function($) {
			initStudents();
			initGrade();

			$('.multiselect')
					.multiselect(
							{
								buttonClass : 'btn btn-white btn-primary',
								templates : {
									button : '<button type="button" class="multiselect dropdown-toggle" data-toggle="dropdown"></button>',
									ul : '<ul class="multiselect-container dropdown-menu"></ul>',
									filter : '<li class="multiselect-item filter"><div class="input-group"><span class="input-group-addon"><i class="fa fa-search"></i></span><input class="form-control multiselect-search" type="text"></div></li>',
									filterClearBtn : '<span class="input-group-btn"><button class="btn btn-default btn-white btn-grey multiselect-clear-filter" type="button"><i class="fa fa-times-circle red2"></i></button></span>',
									li : '<li><a href="javascript:void(0);"><label></label></a></li>',
									divider : '<li class="multiselect-item divider"></li>',
									liGroup : '<li class="multiselect-item group"><label class="multiselect-group"></label></li>'
								}
							});

		});
		
		var initStudents = function() {
			$.ajax({
				url : "${dormitory}/group/getStudents?classesId="
						+ $("#classes").val(),
				dataType : "json",
				success : function(data) {
					if (data.flag) {
						var students = data.data;
						for ( var i = 0; i < students.length; i++) {
							var insertTemp = "<option id='" + students[i].id + "' value='" + students[i].id + "'>"
									+ students[i].name + "</option>";
							$("#students").append(insertTemp);
						}
					}
				}
			});
		};

		var initGrade = function() {
			$.ajax({
				url : "${dormitory}/paper/getPaperGrade",
				type : "post",
				dataType : "json",
				success : function(result) {
					var data = result.data;
					var nowDate = new Date();
					var now = nowDate.getFullYear();
					for ( var i = data; i <= now; i++) {
						var appDiv = "<option id='" + i + "'>" + i
								+ "</option>";
						$("#grade").append(appDiv);
					}
					$("#" + now).prop("selected", "selected");
				}
			});
		};

		var submitForm = function() {
			
			if (!$("#teachers").val()) {
				window.parent.myToastrError("请选择教师");
				return;
			}
			
			if (!$("#students").val()) {
				window.parent.myToastrError("请选择学生");
				return;
			}
			
			$.ajax({
				url : "${dormitory}/reviewTeacherGroup/saveReviewTeacherGroup",
				type : "post",
				dataType : "json",
				data : $("#submitForm").serialize(),
				success : function(data) {
					if (data.flag) {
						window.parent.myToastrSuccess("添加成功");
						location.reload();
					} 
				}
			});
		};

		var getStudents = function() {
			$.ajax({
				url : "${dormitory}/reviewTeacherGroup/getStudents?classesId="
						+ $("#classes").val(),
				dataType : "json",
				success : function(data) {
					if (data.flag) {
						var students = data.data;
						for ( var i = 0; i < students.length; i++) {
							var insertTemp = "<option id='" + students[i].id + "' value='" + students[i].id + "'>"
									+ students[i].name + "</option>";
							$("#students").append(insertTemp);
						}
					}
				}
			});
		};
	</script>
</body>
</html>
