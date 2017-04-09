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
		<div>
			1.通过简单的form表单提交方式，进行文件的上</br> 2.通过jquery.form.js插件提供的form表单一步提交功能
		</div>
		<form method="POST" enctype="multipart/form-data" id="form1"
			action="${dormitory}/uploadExcel/upload">
			<table>
				<tr>
					<td>上传文件:</td>
					<td><input id="upfile" type="file" name="upfile">
					</td>
				</tr>
				<tr>
					<td><input type="submit" value="提交"
						onclick="return checkData()">
					</td>
					<td><input type="button" value="ajax方式提交" id="btn" name="btn">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>

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
<script src="${dormitory}/assets/js/jquery-form.js"></script>

<!-- inline scripts related to this page -->
<script type="text/javascript">

 				//ajax 方式上传文件操作  
             $(document).ready(function(){  
                $('#btn').click(function(){  
                    if(checkData()){  
                        $('#form1').ajaxSubmit({    
                            url:'${dormitory}/uploadExcel/ajaxUpload',  
                            dataType: 'text',  
                            success: resutlMsg,  
                            error: errorMsg  
                        });   
                        function resutlMsg(msg){  
                            alert(msg);     
                            $("#upfile").val("");  
                        }  
                        function errorMsg(){   
                            alert("导入excel出错！");      
                        }  
                    }  
                });  
             });  
               
             //JS校验form表单信息  
             function checkData(){  
                var fileDir = $("#upfile").val();  
                var suffix = fileDir.substr(fileDir.lastIndexOf("."));  
                if("" == fileDir){  
                    alert("选择需要导入的Excel文件！");  
                    return false;  
                }  
                if(".xls" != suffix && ".xlsx" != suffix ){  
                    alert("选择Excel格式的文件导入！");  
                    return false;  
                }  
                return true;  
             }  

 //JS校验form表单信息  
             function checkData(){  
                var fileDir = $("#upfile").val();  
                var suffix = fileDir.substr(fileDir.lastIndexOf("."));  
                if("" == fileDir){  
                    alert("选择需要导入的Excel文件！");  
                    return false;  
                }  
                if(".xls" != suffix && ".xlsx" != suffix ){  
                    alert("选择Excel格式的文件导入！");  
                    return false;  
                }  
                return true;  
             }  
	</script>
</body>
</html>
