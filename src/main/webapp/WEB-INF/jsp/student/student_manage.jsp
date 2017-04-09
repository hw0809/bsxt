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
				ace.settings.check('main-container', 'fixed')
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
													for="account">学号</label>
												<div class="col-sm-2">
													<input type="text" id="account" name="account"
														placeholder="学号" />
												</div>

												<label class="col-sm-1 control-label no-padding-right"
													for="name">姓名</label>
												<div class="col-sm-2">
													<input type="text" id="name" name="name" placeholder="姓名" />
												</div>

												<label class="col-sm-1 control-label no-padding-right"
													for="class">班级</label>
												<div class="col-sm-2">
													<input type="text" id="classes" name="classes"
														placeholder="班级" />
												</div>

												<div class="col-sm-2">
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
	
	<div id="dialog-message" class="hide">
		<form method="POST" enctype="multipart/form-data" id="excelForm">
			<table>
				<tr>
					<td>上传文件:</td>
					<td><input id="upfile" type="file" name="upfile">
					</td>
				</tr>
				<tr>
					<td><input type="button" value="提交" id="btn" name="btn">
					</td>
				</tr>
			</table>
		</form>
	</div><!-- #dialog-message -->	

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
	<script src="${dormitory}/assets/js/jquery-ui.min.js"></script>
	<script src="${dormitory}/assets/js/jquery-form.js"></script>

	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		var grid_selector = "#grid-table";
		var pager_selector = "#grid-pager";
		
		
		$(document).ready(function() {
			console.log("${dormitory}");
			$('#btn').click(function() {
				if (checkData()) {
					$('#excelForm').ajaxSubmit({
						url : '${dormitory}/student/uploadExcel',
						dataType : 'text',
						success : function(data) {
							console.log(data.flag);
							$("#upfile").val("");
							$("#dialog-message").hide();
							refresh();
						},
						error : function() {
							alert("导入excel出错！");
						}
					});
				}
			});
		});

		//JS校验form表单信息  
		function checkData() {
			var fileDir = $("#upfile").val();
			var suffix = fileDir.substr(fileDir.lastIndexOf("."));
			if ("" == fileDir) {
				alert("选择需要导入的Excel文件！");
				return false;
			}
			if (".xls" != suffix && ".xlsx" != suffix) {
				alert("选择Excel格式的文件导入！");
				return false;
			}
			return true;
		}

		jQuery(function($) {
			$(window).on(
					'resize.jqGrid',
					function() {
						$(grid_selector).jqGrid('setGridWidth',
								$(".page-content").width());
					});

			jQuery(grid_selector).jqGrid({
				url : "${dormitory}/student/getPageList",
				height : 250,
				datatype : "json",
				colNames : [ 'id', '学号', '姓名', '班级', '年级' ],
				colModel : [ {
					name : 'id',
					index : 'id',
					editable : true,
					hidden : true,
				}, {
					name : 'account',
					index : 'account',
					sortable : false,
					editable : true,
				}, {
					name : 'name',
					index : 'name',
					sortable : false,
					editable : true,
				}, {
					name : 'className',
					index : 'className',
					sortable : false,
					editable : true,
				}, {
					name : 'grade',
					index : 'grade',
					sortable : false,
					editable : true,
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
					console.log(555);
					/* var table = this;
					setTimeout(function() {
						updatePagerIcons(table);
					}, 0); */
				},
			});

			jQuery(grid_selector).jqGrid("navGrid", pager_selector, {
				edit : false,
				add : false,
				del : false,
				search : false
			}).jqGrid("navButtonAdd", pager_selector, {
				buttonicon : "ace-icon fa fa-plus bigger-100 purple",
				title : "新增",
				caption : "新增",
				onClickButton : function() {
					add();
				},
			}).jqGrid("navButtonAdd", pager_selector, {
				buttonicon : "ace-icon fa fa-pencil-square-o bigger-100 blue",
				title : "修改",
				caption : "修改",
				onClickButton : function() {
					edit();
				},
			}).jqGrid("navButtonAdd", pager_selector, {
				buttonicon : "ace-icon fa fa-trash-o bigger-100 orange",
				title : "删除",
				caption : "删除",
				onClickButton : function() {
					del();
				},
			}).jqGrid("navButtonAdd", pager_selector, {
				buttonicon : "ace-icon fa fa-upload bigger-100 pink",
				title : "批量添加",
				caption : "批量添加",
				onClickButton : function() {
					importExcel();
				},
			}).jqGrid("navButtonAdd", pager_selector, {
				buttonicon : "ace-icon fa fa-download bigger-100 green",
				title : "下载模板",
				caption : "下载模板",
				onClickButton : function() {
					download();
				},
			});
			$(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size

		});

		var add = function() {
			addTabs({
				id : '99',
				title : '添加学生',
				close : true,
				url : 'student/add',
				parentid : '8',
			});
		};

		var edit = function() {
			var ids = jQuery(grid_selector).jqGrid("getGridParam", "selarrrow");
			if (ids.length == 0) {
				window.parent.myToastrError("请至少选择一条数据");
				return;
			}
			;
			if (ids.length > 1) {
				window.parent.myToastrError("只能选择一条数据");
				return;
			}
			;
			var id = ids[0];
			var url = "student/eidt?id=" + id;
			addTabs({
				id : 'eidtStudentId',
				title : '修改学生',
				close : true,
				url : url,
				parentid : '8',
			});
		};

		var del = function() {
			var ids = jQuery(grid_selector).jqGrid("getGridParam", "selarrrow");
			if (ids.length == 0) {
				window.parent.myToastrError("请至少选择一条数据");
				return;
			}
			var buttonType = {
				confirm : {
					label : '确定',
					className : 'btn-primary',
				},
				cancel : {
					label : '取消',
					className : 'btn-default',
				},
			};
			window.parent.myConfirm("您是否确定删除信息", buttonType, confirmFunction);
		};

		var confirmFunction = function(result) {
			if (result) {
				var ids = jQuery(grid_selector).jqGrid("getGridParam",
						"selarrrow");
				$.ajax({
					url : "${dormitory}/student/del",
					type : "post",
					data : {
						ids : ids,
					},
					dataType : "json",
					success : function(data) {
						if (data.flag) {
							window.parent.myToastrSuccess(data.data);
							location.reload();
						} else {
							window.parent.myToastrError(data.data);
						}
						;
					},
				});
			} else {
			}
		};

		var importExcel = function() {
			var dialog = $("#dialog-message").removeClass('hide').dialog({
				modal : true,
				title : "选择文件",
				title_html : true,
				width : "400", //宽度
				height : "100", //高度
			});
		};

		var download = function() {
			window.location.href = "${dormitory}/down/student.xlsx";
		};

		var refresh = function() {
			location.reload();
		};

		//换图标,别删,删了没有前后图标
		function updatePagerIcons(table) {
			var replacement = {
				'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
				'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
				'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
				'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
			};
			$(
					'.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon')
					.each(
							function() {
								var icon = $(this);
								var $class = $.trim(icon.attr('class').replace(
										'ui-icon', ''));
								if ($class in replacement)
									icon.attr('class', 'ui-icon '
											+ replacement[$class]);
							});
		}

		var search = function() {
			jQuery(grid_selector).jqGrid("setGridParam", {
				postData : $("#searchForm").serialize(), //发送数据 
				page : 1
			}).trigger("reloadGrid"); //重新载入 
		};
	</script>
</body>
</html>
