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
						<div class="widget-box">
							<div class="widget-header">
								<i class="ace-icon fa fa-list-alt"></i>
								<h4 class="widget-title">论文详情信息</h4>
							</div>

							<div class="widget-body">
								<div class="widget-main">
									<form class="form-horizontal" role="form" id="commitForm">
										<input type="hidden" id="id" name="id" value = "${paper.id}" />
										<!-- <input type="hidden" id="studenRteview" name="studenRteview" /> -->
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right"
												for="account">题目</label>

											<div class="col-sm-4">
												<input type="text" id="title" name="title"
													value="${paper.title}" placeholder="题目" readonly=""
													class="form-control" />
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right"
												for="account">人数</label>

											<div class="col-sm-1">
												<input type="text" id="num" name="num" value="${paper.num}"
													placeholder="人数" readonly="" class="form-control" />
											</div>
										</div>

										<%-- <div class="form-group">
											<label class="col-sm-3 control-label no-padding-right"
												for="account">班级</label>

											<div class="col-sm-4">
												<input type="text" id="classesName" name="classesName"
													value="${classesName}" placeholder="班级" readonly=""
													class="form-control" />
											</div>
										</div> --%>

										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right"
												for="account">论文简介</label>

											<div class="col-sm-4">
												<textarea readonly="" class="form-control"
													placeholder="论文简介" id="desc" name="desc" rows="5"
													maxlength="50">${paper.desc}</textarea>
											</div>
										</div>

									</form>

								</div>
							</div>

						</div>

						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								   		<c:choose>
										   <c:when test="${student.paperReview== 0 && maxNum < paper.num}">  
											   	<button class="btn btn-info" type="button"
												onclick="submitForm(1)">
												<i class="ace-icon fa fa-check bigger-110"></i> 选定题目
												</button>
										   </c:when>
										</c:choose>
										
										<c:choose>
										   <c:when test="${student.paperReview== 1 && student.paper.id == paper.id}">  
											   	<button class="btn btn-info" type="button"
												onclick="removeForm()">
												<i class="ace-icon fa fa-check bigger-110"></i> 取消选题
												</button>
										   </c:when>
										</c:choose>
										
										<c:choose>
										   <c:when test="${student.paperReview== 2 && student.paper.id == paper.id}">  
											   	<button class="btn disabled btn-success">已选择</button>
										   </c:when>
										</c:choose>	
								&nbsp; &nbsp; &nbsp;
								<!-- <button class="btn" type="button"
									onclick="submitForm(2)">
									<i class="ace-icon fa fa-times bigger-110"></i> 不通过
								</button> -->
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
		var submitForm = function(data) {
			//$("#review").val(data);
			$.ajax({
				url : "${dormitory}/paper/studentReviewPaper",
				type : "post",
				data : $("#commitForm").serialize(),
				dataType : "json",
				success : function(result) {
					if (result.flag) {
						window.parent.myToastrSuccess("已选择,2秒后页面关闭");
						setTimeout("refreshTab('tab_allPaperPageId')",2000);
						setTimeout("closeTab('tab_studentPaperDetailId')",2000);
					}
				}
			});
		};
		
		var removeForm = function() {
			$.ajax({
				url : "${dormitory}/paper/studentRemoveReviewPaper",
				type : "post",
				data : $("#commitForm").serialize(),
				dataType : "json",
				success : function(result) {
					if (result.flag) {
						window.parent.myToastrSuccess("已取消,2秒后页面关闭");
						setTimeout("refreshTab('tab_allPaperPageId')",2000);
						setTimeout("closeTab('tab_studentPaperDetailId')",2000);
					}
				}
			});
		
		};
		
		
	</script>
</body>
</html>
