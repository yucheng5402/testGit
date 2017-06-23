<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html> 
<html lang="en"> 
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1"> 
    <title>纳税人管理</title>

    <link href="static/css/base.css" rel="stylesheet">
    <link rel="stylesheet" href="static/easyui/uimaker/easyui.css">
    <link rel="stylesheet" type="text/css" href="static/easyui/uimaker/icon.css">
    <link rel="stylesheet" href="static/css/taxpayer.css">
</head> 
<body>
 <div class="container" id="listTaxPayer">
      <!--  <table id="dg" style="width:100%;"  data-options="
                rownumbers:true,
                singleSelect:false,
                autoRowHeight:false,
                pagination:true,
                fitColumns:true,
                striped:true,
                checkOnSelect:false,
                selectOnCheck:false,
                toolbar:'#tb',
                pageSize:10">
            <thead>
                <tr>
                    <th field="payerCode">纳税人识别号</th>
                    <th field="payerName">纳税人名称</th>
                    <th field="taxOrganId">所属税务机关</th>
                    <th field="industryId">所属行业</th>
                    <th field="legalResent">法人代表</th>
                    <th field="legalResentCode">法人身份证号码</th>
                    <th field="financeName">主管财务</th>
                    <th field="financeCode">财务身份证号码</th>
                    <th field="staffName">办税人员</th>
                    <th field="createTime">录入日期</th>
                    <th width="200" field="operation">操作</th>
                </tr>
            </thead>
        </table> -->
        <!-- 添加DataGride列表显示的标签 -->
      <table id="dg"></table>
      <div id="tb" style="padding:0 30px;">
        纳税人识别号: <input class="easyui-textbox" type="text" name="payerCode" id="payerCode" style="width:166px;height:35px;line-height:35px;"/>
        纳税人名称: <input class="easyui-textbox" type="text" name="payerName" id="payerName" style="width:166px;height:35px;line-height:35px;"/>
        <a href="javascript:void(0);" id="searchBtn" class="easyui-linkbutton" iconCls="icon-search" data-options="selected:true">查询</a>
        <a href="javascript:void(0);" id="setBtn" class="easyui-linkbutton" iconCls="icon-reload">重置</a>
        <a href="javascript:void(0);" id="addBtn" class="easyui-linkbutton" iconCls="icon-add">添加纳税人</a>
      </div>
    </div>
    <script type="text/javascript" src="static/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="static/easyui/easyui-lang-zh_CN.js"></script>
           
    <script type="text/javascript">

    	$("#dg").datagrid({
    		url:"taxpayer/operation.do?state=list",//请求数据的url
    		toolbar: '#tb',
    		pagination:true,
    		rownumbers:true,
    		method:"get",
   		 	columns:[[
   				{field:'payerCode',title:'纳税人识别号',width:100},
   				{field:'payerName',title:'纳税人名称',width:100},
   				{field:'organName',title:'所属税务机关',width:100,align:'right'},
   				{field:'industryName',title:'所属行业',width:100,align:'right'},
   				{field:'legalPerson',title:'法人代表',width:100,align:'right'},
   				{field:'legalIdCard',title:'法人身份证号码',width:150,align:'right'},
   				{field:'finaceName',title:'主管财务',width:100,align:'right'},
   				{field:'finaceIdCard',title:'财务身份证号码',width:150,align:'right'},
   				{field:'taxerName',title:'办税人员',width:100,align:'right'},
   				{field:'recordDate',title:'录入日期',width:100,align:'right'},
   				{field:'operation',title:'操作',width:100,align:'left',
   					formatter: function(value,row,index){
   						return "<a href='javascript:void(0)' onclick='edit("+ row.id +")' >修改</a>";
   					}}
   		    ]]
    	})
    	
    	//查询
    	$("#searchBtn").bind("click", function(){
    		//easyui文档
    		$('#dg').datagrid('load', {
    			payerCode: $("input[name='payerCode']").val(),
    			payerName: $("input[name='payerName']").val()
    		});
    	})
    	
    	//重置
    	$("#setBtn").bind("click", function(){
    		//easyui textbox 重置 ，只能用id获取元素，不然无法实现
	    	$("#payerCode").textbox("setValue", "");
	    	$("#payerName").textbox("setValue", "");
    	})
    	
    	
    	//添加纳税人
    	$("#addBtn").bind("click", function(){
    		//js代码中实例化一个对象可以{}
    		openTopWindow({"width":850,"height":600,"url":"taxpayer/operation.do?state=toAdd","title":"添加纳税人"});
    	})
    	
    	
    	//修改纳税人
    	var edit = function(id){
    		openTopWindow({"width":850,"height":600,"url":"taxpayer/operation.do?state=toAdd&id="+ id,"title":"修改纳税人"});
    	}
    	
		function openTopWindow(options){
		    options = !options ? {} :options;
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
