package com.zhidisoft.servlet.taxer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.zhidisoft.dao.impl.TaxerDaoImpl;
import com.zhidisoft.entity.Taxer;

@WebServlet("/taxer/toEditTaxer.do")
public class ToEditTaxerServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//获取id参数
		Integer id = StringUtils.isEmpty(req.getParameter("id")) ? null : Integer.parseInt(req.getParameter("id"));
		
		TaxerDaoImpl taxerDaoImpl = new TaxerDaoImpl();
		//通过id查询出指定的办税专员
		Taxer taxer = taxerDaoImpl.get(id);
		
		//将要修改发的办税专员对象保存到请求范围中
		req.setAttribute("taxer", taxer);
		
		req.getRequestDispatcher("/manage/taxer/editTaxer.jsp").forward(req, res);
	}

	
}
