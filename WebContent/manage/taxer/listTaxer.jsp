<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>办税专员管理</title>
<link href="static/css/base.css" rel="stylesheet">
<link href="static/easyui/uimaker/easyui.css" rel="stylesheet">
<link href="static/easyui/uimaker/icon.css" rel="stylesheet">
	
<script type="text/javascript" src="static/jquery/jquery.min.js"></script>
<script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="static/easyui/easyui-lang-zh_CN.js"></script>
</head>
<body>
<div class="container">
	<table id="dg"></table>
</div>
<div id="tb" style="padding:0 30px;">
        办税专员名称: <input type="text" class="easyui-textbox" id="taxerName" name="taxerName" style="width:166px;height:35px;line-height:35px;"/>
        <a href="javascript:void(0);" id="searchBtn" class="easyui-linkbutton" iconCls="icon-search" data-options="selected:true">查询</a>
        <a href="javascript:void(0);" id="setBtn" class="easyui-linkbutton" iconCls="icon-reload">重置</a>
        <a href="javascript:void(0);" id="addBtn" class="easyui-linkbutton" iconCls="icon-add">添加办税专员</a>
      </div>
<script type="text/javascript">
	$("#dg").datagrid({
		url:"taxer/listTaxer2.do",
		pagination:true,
		loadMsg:"数据加载中...",
		toolbar:'#tb',
		columns:[[
			{field:'taxerCode',title:'办税专员编号',width:100},
			{field:'taxerName',title:'办税专员名称',width:100},
			{field:'organName',title:'税务机关',width:100},
			{field:'mobile',title:'手机号',width:100},
			{field:'address',title:'住址',width:100},
			{field:'sex',title:'性别',width:100},
			{field:'birthday',title:'生日',width:100},
			{field:'operation',title:'操作',width:150,
				formatter: function(value,row,index){
					return "<a href='javascript:void(0)' onclick='edit("+row.id+")'>修改</a>&nbsp;"+
							"<a href='javascript:void(0)'onclick='del("+row.id+")'>删除</a>"
				}}
	    ]]
	})
	
	//给查询按钮绑定点击事件
	$("#searchBtn").bind("click", function(){
		search();
	});
	
	//查询的方法
	var search = function(){
		$('#dg').datagrid('load', {
			taxerName: $("#taxerName").val()
		});
	}
	
	//添加办税专员
	$("#addBtn").bind("click", function(){
		openTopWindow({"title":"添加办税专员", "url":"manage/taxer/toAddTaxer.do", "width":700, "height": 500});
	})
	
	//定义修改的函数
	var edit = function(id){
		openTopWindow({"title":"修改办税专员", "url":"taxer/toEditTaxer.do?id="+id, "width":700, "height": 500});
	}
	
	//删除的函数
	var del = function(id){
		$.messager.confirm('提示窗口','确认删除?',function(r){
		    if (r){
				//发送一个ajax的post请求
				//回调函数代表请求成功之后，如果返回数据格式一致，就会进入回调函数
				$.post("taxer/deleteTaxer.do?state=delete",{"id":id},function(result){
					if (result.success) {
						$.messager.alert('提示信息', result.msg);
						//刷新表格
						$('#dg').datagrid('reload');
					} else {
						$.messager.alert('提示信息', result.msg);
					}
				},"json")
		    }
		});
	}
	
	//重置
	$("#setBtn").bind("click", function(){
		$("#taxerName").textbox("setValue", "");
	})
	
	/**
     *打开在父窗口中打开window
     */
    function openTopWindow(options){
        options = !options ? {} : options;
        options.width = !options.width ? 500 : options.width;
        options.height = !options.height ? 400 : options.height;
        options.url = !options.url ? "404.html" : options.url;
        options.title = !options.title ? "" : options.title;

        parent.$("#topWindow").window({
            title : options.title,
            width: options.width,
            height: options.height,
            content : "<iframe scrolling='no' frameborder='0' border='0' height='100%' width='100%' src='"+options.url+"'></iframe>",
            modal:true,
            resizable:false,
            collapsible:false
        });
    }
</script>
</body>
</html>