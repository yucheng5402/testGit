package com.zhidisoft.servlet.taxer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.dao.impl.TaxOrganDaoImpl;
import com.zhidisoft.entity.TaxOrgan;

@WebServlet("/taxer/toAddTaxer.do")
public class ToAddTaxerServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		TaxOrganDaoImpl organDaoImpl = new TaxOrganDaoImpl();
		List<TaxOrgan> list = organDaoImpl.getAll();
		req.setAttribute("list", list);
		req.getRequestDispatcher("/manage/taxer/addTaxer.jsp").forward(req, resp);;
	}

	
}
