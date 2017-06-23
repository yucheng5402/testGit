<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="<%=basePath %>">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>添加纳税人</title>
    <link rel="stylesheet" type="text/css" href="static/css/base.css" >
    <link rel="stylesheet" type="text/css" href="static/easyui/uimaker/easyui.css">
    <link rel="stylesheet" type="text/css" href="static/easyui/uimaker/icon.css">
    <link rel="stylesheet" type="text/css" href="static/css/edit.css">
</head>
<script type="text/javascript" src="static/jquery/jquery.min.js"></script>
<script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="static/js/calendar.js"></script>
<script type="text/javascript" src="static/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"></script>
<body>
<form id="addTaxPayerForm">
    <div class="container">
        <div class="content">
            <div title="纳税人信息" data-options="closable:false" class="basic-info">
                <div class="column"><span class="current">添加纳税人信息</span></div>
                <table class="kv-table">
                    <tbody>
                    <tr>
                        <td class="kv-label">纳税人识别号</td>
                        <td class="kv-content"><input type="text" name="payerCode" value="${taxPayer.payerCode }" <c:if test="${not empty taxPayer }">readonly</c:if> class="easyui-validatebox" data-options="required:true" placeholder="纳税人识别号"></td>
                        <td class="kv-label">纳税人名称</td>
                        <td class="kv-content"><input type="text" name="payerName" value="${taxPayer.payerName }" <c:if test="${not empty taxPayer }">readonly</c:if>  class="easyui-validatebox" data-options="required:true" placeholder="纳税人名称"></td>
                    </tr>
                    <tr>
                        <td class="kv-label">生产经营地址</td>
                        <td class="kv-content"><input type="text" name="bizAddress" value="${taxPayer.bizAddress }" <c:if test="${not empty taxPayer }">readonly</c:if> class="easyui-validatebox" data-options="required:true" placeholder="生产经营地址"></td>
                        <td class="kv-label">经营地电话</td>
                        <td class="kv-content"><input type="text" name="bizAddressPhone" value="${taxPayer.bizAddressPhone }" placeholder="生产经营地电话"></td>
                    </tr>
                    <tr>
                        <td class="kv-label">所属税务机关</td>
                        <td class="kv-content">
                            <select name="taxOrganId" class="easyui-validatebox" validType="choose">
                            	<!-- el表达式和jstl标签库  -->
                                <option value="-1" id="selectOrgan">请选择所属税务机关</option>    
                                <c:forEach items="${organs }" var="organ">
                                	<option value="${organ.id }" <c:if test="${organ.id eq taxPayer.taxOrganId }">selected</c:if>  >${organ.organName }</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td class="kv-label">行业</td>
                        <td class="kv-content">
                            <select name="industryId" class="easyui-validatebox" validType="choose">
                                <option value="-1" id="selectIndustry">请选择纳税人行业</option>
                                <c:forEach items="${industries }" var="industry">
                                	<option value="${industry.id }" <c:if test="${industry.id eq taxPayer.industryId }">selected</c:if>  >${industry.industryName }</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">生产经营范围</td>
                        <td class="kv-content">
                            <input type="text" name="bizScope" value="${taxPayer.bizScope }" placeholder="生产经营范围">
                        </td>
                        <td class="kv-label">票种核定</td>
                        <td class="kv-content">
                            <select name="invoiceType">
                                <option value="-1">请选择发票种类</option>
                                <option value="增值税普通发票" <c:if test="${'增值税普通发票' eq taxPayer.invoiceType }">selected</c:if> >增值税普通发票</option>
                                <option value="增值税专用发票" <c:if test="${'增值税专用发票' eq taxPayer.invoiceType }">selected</c:if>>增值税专用发票</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">法人代表人</td>
                        <td class="kv-content">
                            <input type="text" name="legalPerson" value="${taxPayer.legalPerson }" placeholder="法人姓名">
                        </td>
                        <td class="kv-label">法人身份证号</td>
                        <td class="kv-content">
                            <input type="text" name="legalIdCard" value="${taxPayer.legalIdCard }" placeholder="法人代表身份证号码">
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">主管财务</td>
                        <td class="kv-content">
                            <input type="text" name="finaceName" value="${taxPayer.finaceName }" placeholder="主管财务">
                        </td>
                        <td class="kv-label">财务身份证号</td>
                        <td class="kv-content">
                            <input type="text" name="finaceIdCard" value="${taxPayer.finaceIdCard }" placeholder="财务身份证号">
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">办税专员</td>
                        <td class="kv-content">
                            <select name="taxerName" class="easyui-validatebox" validType="choose">
                                <option value="-1">请选择办税专员</option>
                                <c:forEach items="${taxers }" var="taxer">
                                	<option value="${taxer.taxerName }" <c:if test="${taxer.taxerName eq taxPayer.taxerName }">selected</c:if>   >${taxer.taxerName }</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td class="kv-label">录入日期</td>
                        <td class="kv-content"><c:if test="${not empty taxPayer }">${taxPayer.recordDate }</c:if><c:if test="${empty taxPayer }"><fmt:formatDate value="<%=new Date() %>"/></c:if> </td>
                        <input type="hidden" name="recordDate" value='<c:if test="${not empty taxPayer }">${taxPayer.recordDate }</c:if><c:if test="${empty taxPayer }"><fmt:formatDate value="<%=new Date() %>"/></c:if>'>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="btn-selection">
                <a href="javascript:void(0);" class="easyui-linkbutton save-btn" id="save-btn" data-options="selected:true">保存</a>
                <a href="javascript:void(0);" class="easyui-linkbutton reset-btn" id="reset-btn" data-options="selected:true">重置</a>
            </div>
        </div>
    </div>
</form>
</body>
<script type="text/javascript">
	//重写一个下拉列表的验证
	$.extend($.fn.validatebox.defaults.rules, {
	    choose: {
	        validator: function(value,param){
	            return value != -1;
	        },
	        message: '请选择'
	    }
	});
	
	//保存
	$("#save-btn").bind("click", function(){
		var flag = $("#addTaxPayerForm").form("validate");
		//如果为true代表验证成功
		
		if (flag) {
			$.post("taxpayer/operation.do?state=add&id=${taxPayer.id}", $("#addTaxPayerForm").serialize(), function(result){
				if (result.success) {
					$.messager.alert('提示信息', result.msg ,'info', function(){
						parent.$("#topWindow").window("close");
						//要刷新iframe页面，用contentWindow
						parent.document.getElementById('taxpayer').contentWindow.location.reload(true);
					});
					
				} else {
					$.messager.alert('提示信息', result.msg ,'info');
				}
			}, "json")
		}
	})


	$(function(){
	/**
		ajax请求获取外键值
		$.post("getIndustry.do",{},function(data){
			var industry = $("#selectIndustry")
			$.each(data,function(index, val){
				industry.append("<option value='"+val.industryId+"'>"+val.industryName+"</option>")
			})
		},"json")
		$.post("getOrganServlet.do",{},function(data){
			var organ = $("#selectOrgan")
			$.each(data,function(index, val){
				organ.append("<option value='"+val.organId+"'>"+val.organName+"</option>")
			})
		},"json")
	**/
	
	
	
	})
</script>
</html>


