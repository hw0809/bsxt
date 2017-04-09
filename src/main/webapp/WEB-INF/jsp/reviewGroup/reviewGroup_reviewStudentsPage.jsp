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
						<i class="glyphicon glyphicon-search"></i>
						<h4 class="widget-title">搜索</h4>
					</div>

					<div class="widget-body">
						<div class="widget-main no-padding">

							<form class="form-horizontal" role="form" id="searchForm">
								<fieldset>
									<div class="row">
										<div class="col-xs-12">
											<div class="form-group">

												<label class="col-sm-1 control-label no-padding-right"
													for="grade">年份</label>
												<div class="col-sm-2">
													<select id="grade" name="grade"
														class="selectpicker show-tick ">
													</select>
												</div>

											</div>
											<div class="form-group">

												<div class="col-sm-2 pull-right">
													<span class="input-group-btn">
														<button type="button" onclick="search()"
															class="btn btn-purple btn-sm">
															<span
																class="ace-icon fa fa-search icon-on-right bigger-110"></span>
															搜索
														</button> </span>
												</div>
											</div>
										</div>
									</div>
								</fieldset>
							</form>
						</div>
					</div>
				</div>

				<div class="page-content-area">
					<div class="row">
						<div class="col-xs-12">
							<table id="grid-table"></table>
							<div id="grid-pager"></div>
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

	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		var grid_selector = "#grid-table";
		var pager_selector = "#grid-pager";

		jQuery(function($) {
			initGrade();
		
			$(window).on(
					'resize.jqGrid',
					function() {
						$(grid_selector).jqGrid('setGridWidth',
								$(".page-content").width());
					});

			jQuery(grid_selector).jqGrid({
				url : "${dormitory}/reviewTeacherGroup/getReviewStudents",
				height : 250,
				datatype : "json",
				colNames : [ 'id', '学生', '查看' ],
				colModel : [ {
					name : 'id',
					index : 'id',
					editable : true,
					hidden : true,
				}, {
					name : 'name',
					index : 'name',
					sortable : false,
					width : 250,
					editable : true,
				}, {
					name : '',
					index : '',
					formatter : function(cellvalue, options, rowObject) {
						var studentId = rowObject.id;
						var temp = "<a onclick='studentDetail(" + studentId + ")'>查看</a>"; 
						return temp;
					}, 
				} ],
				viewrecords : true,
				rowNum : 10,
				rowList : [ 10, 20, 30 ],
				pager : pager_selector,
				altRows : true,
				//toppager: true,
				multiselect : true,
				//multikey: "ctrlKey",
				//multiboxonly : true,
				toolbar : [ true, "top" ],
				loadComplete : function() {
				},
			});

			jQuery(grid_selector).jqGrid("navGrid", pager_selector, {
				edit : false,
				add : false,
				del : false,
				search : false
			});
			$(window).triggerHandler('resize.jqGrid'); //trigger window resize to make the grid get the correct size
		});
		
		var studentDetail = function(data) {
			addTabs({
				id : 'getReviewStudentDetailId',
				title : '开题报告评价',
				close : true,
				url : 'reviewTeacherGroup/getReviewStudentDetail?studentId=' + data,
				parentid : 'reviewStudentsId',
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
		
		var search = function() {
			jQuery(grid_selector).jqGrid('setGridParam', {
				postData : $("#searchForm").serialize(),
				page : 1
			}).trigger("reloadGrid"); //重新载入  
		};
	</script>
</body>
</html>
