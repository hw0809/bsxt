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
										readonly="" value="${paper.title }" class="col-xs-10 col-sm-5" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="teacher"> 教师 </label>

								<div class="col-sm-5">
									<input type="text" placeholder="teacher" name="teacher"
										readonly="" id="teacher" value="${teacher.name }"
										class="col-xs-10 col-sm-5" />
								</div>
							</div>


							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="desc">简介</label>
								<div class="col-sm-5">
									<textarea class="form-control" id="desc" name="desc" rows="5"
										readonly="" maxlength="50">${paper.desc }</textarea>
								</div>
							</div>

						</form>

						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">

								<c:choose>
									<c:when test="${thesisProposal == 1}">
										<button class="btn btn-info" type="button"
											onclick="checkThesisProposal()">查看开题报告评价</button>
									</c:when>
								</c:choose>

								<c:choose>
									<c:when test="${thesisProposal == 0}">
										<button class="btn btn-info" type="button"
											onclick="importThesisProposal()">提交开题报告</button>
									</c:when>
								</c:choose>

								<c:choose>
									<c:when test="${thesis == 1}">
										<button class="btn btn-info" type="button"
											onclick="checkThesis()">查看毕业论文评价</button>
									</c:when>
								</c:choose>

								<c:choose>
									<c:when test="${thesis == 0}">
										<button class="btn btn-info" type="button"
											onclick="importThesis()">提交毕业论文</button>
									</c:when>
								</c:choose>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 开题报告提交 -->
	<div id="dialog-thesisProposal" class="hide">
		<form method="POST" enctype="multipart/form-data"
			id="dialog-thesisProposalForm">
			<table>
				<tr>
					<td>提交开题报告:</td>
					<td><input id="thesisProposalUpfile" type="file"
						name="thesisProposalUpfile">
					</td>
				</tr>
				<tr>
					<td><input type="button" value="提交" id="thesisProposal-btn"
						name="thesisProposal-btn"></td>
				</tr>
			</table>
		</form>
	</div>

	<!-- 提交毕业论文 -->
	<div id="dialog-thesis" class="hide">
		<form method="POST" enctype="multipart/form-data"
			id="dialog-thesisForm">
			<table>
				<tr>
					<td>提交毕业论文:</td>
					<td><input id="thesisUpfile" type="file" name="thesisUpfile">
					</td>
				</tr>
				<tr>
					<td><input type="button" value="提交" id="thesis-btn"
						name="thesis-btn"></td>
				</tr>
			</table>
		</form>
	</div>

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

	<script src="${dormitory}/assets/js/jquery-ui.min.js"></script>
	<script src="${dormitory}/assets/js/jquery-form.js"></script>

	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		$(document).ready(function() {
			$('#thesisProposal-btn').click(function() {
				if (checkData(1)) {
					$('#dialog-thesisProposalForm').ajaxSubmit({
						url : '${dormitory}/paper/uploadThesisProposal',
						dataType : 'text',
						success : function(data) {
							window.parent.myToastrSuccess("提交成功");
							$("#thesisProposalUpfile").val("");
							$("#dialog-thesisProposal").hide();
							refresh();
						},
						error : function() {
							window.parent.myToastrError("WORD导入失败");
						}
					});
				}
			});

			$('#thesis-btn').click(function() {
				if (checkData(2)) {
					$('#dialog-thesisForm').ajaxSubmit({
						url : '${dormitory}/paper/uploadThesis',
						dataType : 'text',
						success : function(data) {
							window.parent.myToastrSuccess("提交成功");
							$("#thesisUpfile").val("");
							$("#dialog-thesis").hide();
							refresh();
						},
						error : function() {
							window.parent.myToastrError("WORD导入失败");
						}
					});
				}
			});

		});

		//JS校验form表单信息  
		function checkData(data) {
			var fileDir = "";
			if (data == 1) {
				fileDir = $("#thesisProposalUpfile").val();
			}
			if (data == 2) {
				fileDir = $("#thesisUpfile").val();
			}
			var suffix = fileDir.substr(fileDir.lastIndexOf("."));
			if ("" == fileDir) {
				window.parent.myToastrError("选择需要导入的Word文件!");
				return false;
			}
			if (".doc" != suffix && ".docx" != suffix) {
				window.parent.myToastrError("选择Word格式的文件导入！");
				return false;
			}
			return true;
		}

		var refresh = function() {
			location.reload();
		};

		var importThesisProposal = function() {
			var dialog = $("#dialog-thesisProposal").removeClass('hide')
					.dialog({
						modal : true,
						title : "选择开题报告",
						title_html : true,
						width : "350", //宽度
						height : "100", //高度
					});
		};

		var importThesis = function() {
			var dialog = $("#dialog-thesis").removeClass('hide').dialog({
				modal : true,
				title : "选择毕业论文",
				title_html : true,
				width : "350", //宽度
				height : "100", //高度
			});
		};

		var checkThesisProposal = function() {
			addTabs({
				id : 'checkThesisProposalId',
				title : '开题报告评价',
				close : true,
				url : 'paper/checkThesisProposal',
				parentid : 'myPaperPageId',
			});
		};

		var checkThesis = function() {
			addTabs({
				id : 'checkThesisId',
				title : '论文评价',
				close : true,
				url : 'paper/checkThesis',
				parentid : 'myPaperPageId',
			});
		};
	</script>
</body>
</html>
