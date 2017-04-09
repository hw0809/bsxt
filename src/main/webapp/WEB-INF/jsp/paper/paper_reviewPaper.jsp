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
													for="account">论文题目</label>
												<div class="col-sm-2">
													<input type="text" id="title" name="title"
														placeholder="论文题目" />
												</div>

												<label class="col-sm-1 control-label no-padding-right" for="grade">年份</label>
												<div class="col-sm-2">
													<select id="grade" name="grade"
														class="selectpicker show-tick ">
													</select>
												</div>

												<!-- <label class="col-sm-1 control-label no-padding-right"
													for="class">班级</label>
												<div class="col-sm-2">
													<input type="text" id="classes" name="classes"
														placeholder="班级" />
												</div> -->
												
												<label class="col-sm-1 control-label no-padding-right"
													for="titleReview">审核</label>
												<div class="col-sm-2">
													<select id="titleReview" name="titleReview"
														class="selectpicker show-tick ">
														<option value="3">全部</option>
														<option value="0">未通过</option>
														<option value="1">通过</optoin>
													</select>
												</div>
											</div>
											<div class="form-group">

												<div class="col-sm-2 pull-right" >
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

		var departmentId = ${departmentId};

		jQuery(function($) {
			initGrade();
		
			$(window).on(
					'resize.jqGrid',
					function() {
						$(grid_selector).jqGrid('setGridWidth',
								$(".page-content").width());
					});

			jQuery(grid_selector).jqGrid({
				url : "${dormitory}/paper/getReviewPageList",
				height : 250,
				datatype : "json",
				colNames : [ 'id', '论文题目', '班级', '限制选人数', '审核' ],
				colModel : [ {
					name : 'id',
					index : 'id',
					editable : true,
					hidden : true,
				}, {
					name : 'title',
					index : 'title',
					formatter : function(cellvalue, options, rowObject) {
						var id = rowObject.id;
						var icon = "<a  onclick='getDetial(" + id + ")' >" + cellvalue + "</a>" ;
						
						return icon;
					}, 
				}, {
					name : 'className',
					index : 'className',
					sortable : false,
					editable : true,
				}, {
					name : 'num',
					index : 'num',
					sortable : false,
					editable : true,
				}, {
					name : 'titleReview',
					index : 'titleReview',
					formatter : function(cellvalue, options, rowObject) {
						var icon;
						if (cellvalue == 1) {
							icon = "<i class='ace-icon fa fa-check green'></i>";
						} else {
							icon = "未审核";
						}
						return icon;
					}, 
				}, ],
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

		var search = function() {
			jQuery(grid_selector).jqGrid('setGridParam', {
				postData : $("#searchForm").serialize(),
				page : 1
			}).trigger("reloadGrid"); //重新载入  
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
		};
		
		var getDetial = function (id) {
			var url = "paper/getDetail?id=" + id;
			addTabs({
				id: 'paperDetailId',
				title: '论文详情',
				close: true,
				url: url,
				parentid : 'teacherPaperId',
			});
			
		} 
	</script>
</body>
</html>
