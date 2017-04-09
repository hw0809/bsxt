var addTabs = function(options) {
	/*
	 * var url = window.location.protocol + '//' + window.location.host;
	 * options.url = url + options.url;
	 */
	/*var url = window.location.protocol + '//' + window.location.host
			+ "/InformationSystem/";*/
	var url = window.location.protocol + '//' + window.location.host
	+ "/bsxt/";
	options.url = url + options.url;
	
	id = "tab_" + options.id;
	
	if (options.parentid) {
		$('.active', window.parent.document).removeClass("active");
	} else {
		$(".active").removeClass("active");
	}

	// $(".active").removeClass("active");
	// 如果TAB不存在，创建一个新的TAB
	if (!$("#" + id)[0]) {
	//if (!id) {
		// 固定TAB中IFRAME高度
		//mainHeight = $(document.body).height() - 120;
		mainHeight = $(window.parent.document.body).height();
		
		//console.log("mainHeight");
		//console.log($(window.parent.document.body).height());
		//mainHeight = $(document.body).height();
		// 创建新TAB的title
		title = '<li role="presentation" id="tab_' + id + '"><a href="#' + id
				+ '" aria-controls="' + id + '" role="tab" data-toggle="tab">'
				+ options.title;
		// 是否允许关闭
		if (options.close) {
			title += ' <i class="glyphicon glyphicon-remove" tabclose="' + id
					+ '"></i>';
		}
		title += '</a></li>';
		// 是否指定TAB内容
		if (options.content) {
			content = '<div role="tabpanel" class="tab-pane" id="' + id + '">'
					+ options.content + '</div>';
		} else {// 没有内容，使用IFRAME打开链接
			content = '<div role="tabpanel" class="tab-pane" id="'
					+ id
					+ '"><iframe src="'
					+ options.url
					+ '" width="100%" height="'
					+ mainHeight
					+ '" name="iframe'
					+ id
					+ '" id="iframe'
					+ id
					+ '" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes"></iframe></div>';
		}
		// 加入TABS
		if (options.parentid) {
			$('.nav-tabs', window.parent.document).append(title);
			$('.tab-content', window.parent.document).append(content);
			$("#tab_" + id, window.parent.document).addClass("active");
			$("#" + id, window.parent.document).addClass("active");

		} else {
			$(".nav-tabs").append(title);
			$(".tab-content").append(content);
			// 激活TAB
			$("#tab_" + id).addClass('active');
			$("#" + id).addClass("active");
		}

		// $(".tab-content").append(content);
	} else {
		$("#" + id).addClass('active');
	}
	// 激活TAB
	/*
	 * $("#tab_" + id).addClass('active'); $("#" + id).addClass("active");
	 */
};
var closeTab = function(id) {
	// 如果关闭的是当前激活的TAB，激活他的前一个TAB
	if ($('li.active', window.parent.document).attr('id') == "tab_" + id) {
		$("#tab_" + id, window.parent.document).prev().addClass('active');
		$("#" + id, window.parent.document).prev().addClass('active');
	}
	/*if ($("li.active").attr('id') == "tab_" + id) {
		$("#tab_" + id).prev().addClass('active');
		$("#" + id).prev().addClass('active');
	}*/
	// 关闭TAB
	$("#tab_" + id, window.parent.document).remove();
	$("#" + id, window.parent.document).remove();
	/*$("#tab_" + id).remove();
	$("#" + id).remove();*/
};

var refreshTab = function(id) {
	var frameId = "iframe" + id;
	window.parent.frames[frameId].location.reload();
};

$(function() {
	$('.nav-tabs:first').tabdrop();
	mainHeight = $(document.body).height() - 120;
	$('.main-left,.main-right').height(mainHeight);
	$("[addtabs]").click(function() {
		addTabs({
			id : $(this).attr("id"),
			title : $(this).attr('title'),
			close : true
		});
	});

	$(".nav-tabs").on("click", "[tabclose]", function(e) {
		id = $(this).attr("tabclose");
		closeTab(id);
	});
});