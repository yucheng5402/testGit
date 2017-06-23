<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="static/css/base.css" rel="stylesheet">
<link rel="stylesheet" href="static/easyui/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="static/easyui/uimaker/icon.css">
<script type="text/javascript" src="static/jquery/jquery.min.js"></script>
<script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="static/easyui/easyui-lang-zh_CN.js"></script>
</head>
<body>
<form id="saveTaxerForm">
 <div class="container">
        <div class="content">
            <div title="纳税人信息" data-options="closable:false" class="basic-info">
                <div class="column"><span class="current">修改办税专员</span></div>
                <table class="kv-table">
                    <tbody>
                    <tr>
                    	<input type="hidden" name="id" value="${taxer.id }">
                        <td class="kv-label">办税专员编号</td>
                        <td class="kv-content"><input type="text" name="taxerCode" value="${taxer.taxerCode }" class="easyui-validatebox" data-options="required:true" placeholder="纳税人识别号"></td>
                        <td class="kv-label">办税专员名称</td>
                        <td class="kv-content"><input type="text" name="taxerName" value="${taxer.taxerName }" placeholder="纳税人名称"></td>
                    </tr>
                    <tr>
                        <td class="kv-label">生产经营地址</td>
                        <td class="kv-content"><input type="text" name="mobile" placeholder="生产经营地址"></td>
                        <td class="kv-label">经营地电话</td>
                        <td class="kv-content"><input type="text" name="address" placeholder="生产经营地电话"></td>
                    </tr>
                    <tr>
                        <td class="kv-label">所属税务机关</td>
                        <td class="kv-content">
                            <select name="taxOrganId">
                                <option value="-1" id="selectOrgan">请选择所属税务机关</option>     
                                <%-- <c:forEach items="${list }" var="organ">
                                	<option value="${organ.id }" <c:if test="${taxer.organId eq organ.id }"> selected</c:if>  >${organ.name }</option>
                                </c:forEach> --%>
                            </select>
                        </td>
                        <td class="kv-label">行业</td>
                        <td class="kv-content">
                            <select name="instruyId">
                                <option value="-1" id="selectIndustry">请选择纳税人行业</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">生产经营范围</td>
                        <td class="kv-content">
                            <input type="text" name="bizScope" placeholder="生产经营范围">
                        </td>
                        <td class="kv-label">票种核定</td>
                        <td class="kv-content">
                            <select name="billType" class="easyui-validatebox" validType="choose">
                                <option value="-1">请选择发票种类</option>
                                <option value="1">增值税普通发票</option>
                                <option value="2">增值税专用发票</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">法人代表人</td>
                        <td class="kv-content">
                            <input type="text" name="legalName" placeholder="法人姓名">
                        </td>
                        <td class="kv-label">法人身份证号</td>
                        <td class="kv-content">
                            <input type="text" name="legalCardId" placeholder="法人代表身份证号码">
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">主管财务</td>
                        <td class="kv-content">
                            <input type="text" name="faName" placeholder="主管财务">
                        </td>
                        <td class="kv-label">财务身份证号</td>
                        <td class="kv-content">
                            <input type="text" name="faCardId" placeholder="财务身份证号">
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">办税专员</td>
                        <td class="kv-content">
                            <select name="taxerId">
                                <option value="-1">请选择办税专员</option>
                                <option value="1">张三</option>
                                <option value="2">李四</option>
                            </select>
                        </td>
                        <td class="kv-label">录入日期</td>
                        <td class="kv-content">2017-01-20 </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="btn-selection">
                <a href="javascript:void(0);" class="easyui-linkbutton save-btn" id="saveBtn" data-options="selected:true">保存</a>
                <a href="javascript:void(0);" class="easyui-linkbutton reset-btn" data-options="selected:true">重置</a>
            </div>
        </div>
    </div>
 </form>
</body>
</html>