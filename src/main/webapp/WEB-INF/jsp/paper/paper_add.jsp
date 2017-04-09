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
							<input type="hidden" id="ids" name="ids" />
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="form-field-1"> 选题名称 </label>

								<div class="col-sm-5">
									<input type="text" placeholder="选题名称" name="title" id="title"
										class="col-xs-10 col-sm-5" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label" for="desc">人数</label>
								<div class="col-sm-1">
									<select id="num" name="num" class="selectpicker show-tick ">
									</select>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label" for="grade">年份</label>
								<div class="col-sm-1">
									<select id="grade" name="grade" class="selectpicker show-tick ">
									</select>
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-xs-12 col-sm-3 no-padding-right"
									for="classes">班级</label>

								<div class="col-xs-12 col-sm-9">
									<select id="classes" name="classes" class="multiselect"
										multiple="">
										<c:forEach var="item" items="${classes }">
											<option id="${item.id }" value="${item.id }">${item.name
												}</option>
										</c:forEach>
									</select>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="desc">描述</label>
								<div class="col-sm-5">
									<textarea class="form-control" id="desc" name="desc" rows="5"
										maxlength="50"></textarea>
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
			initPaperNum();
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

		var initClasses = function() {
			$.ajax({
				url : "${dormitory}/paper/getClasses",
				type : "post",
				dataType : "json",
				success : function(result) {
					if (result != null) {
						$.each(result, function() {
							var insertElement = "<option id='" + this.id + "'>"
									+ this.name + "</option>";
							$("#classes").append(insertElement);
						});
					}
				}
			});
		}

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
		}

		var initPaperNum = function() {
			$.ajax({
				url : "${dormitory}/paper/getPaperNum",
				type : "post",
				dataType : "json",
				success : function(result) {
					var data = result.data;
					for ( var i = 1; i <= data; i++) {
						var appDiv = "<option value='" + i + "'>" + i
								+ "</option>";
						$("#num").append(appDiv);
					}
				}
			});
		};

		var submitForm = function() {
			if (!$("#title").val()) {
				window.parent.myToastrError("请输入论文题目");
				$("#title").focus();
				return;
			}

			if (!$("#desc").val()) {
				window.parent.myToastrError("请输入论文描述");
				$("#desc").focus();
				return;
			}

			var idValue = $("#classes").val();
			var ids = "";

			$.each(idValue, function(index) {
				ids += idValue[index] + "##";
			});
			ids = ids.substring(0, ids.length - 2);
			$("#ids").val(ids);
			$.ajax({
				url : "${dormitory}/paper/save",
				type : "post",
				dataType : "json",
				data : $("#submitForm").serialize(),
				success : function(data) {
					if (data.flag) {
						var buttonType = {
							confirm : {
								label : '继续添加',
								className : 'btn-primary',
							},
							cancel : {
								label : '关闭当前页面',
								className : 'btn-default',
							},
						};
						window.parent.myConfirm("添加成功", buttonType,
								confirmFunction);
					} else {

					}
				}
			});
		};

		var confirmFunction = function(result) {
			if (result) {
				location.reload();
			} else {
				var frameId = "iframetab_teacherPaperId";
				if (window.parent.frames[frameId] != undefined) {
					refreshTab("tab_teacherPaperId");
				}
				closeTab("tab_submitPaperId");
			}
		};
	</script>
</body>
</html>
