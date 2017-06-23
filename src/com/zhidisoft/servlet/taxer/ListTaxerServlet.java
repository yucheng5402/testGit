package com.zhidisoft.servlet.taxer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.zhidisoft.dao.impl.TaxerDaoImpl;
import com.zhidisoft.entity.Taxer;

import net.sf.json.JSONObject;

/**
 * 获取办税专员的serlvet
 * @author lx
 *
 */
@WebServlet("/taxer/listTaxer.do")
public class ListTaxerServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json;charset=UTF-8");
		
		//当前第几页
		Integer page = StringUtils.isEmpty(req.getParameter("page")) ? 1 : Integer.parseInt(req.getParameter("page"));
		//一页显示多少条
		Integer rows = StringUtils.isEmpty(req.getParameter("rows")) ? 10 : Integer.parseInt(req.getParameter("rows"));
		
		//获取查询条件
		String taxerName = req.getParameter("taxerName");
		//查询条件
		Map<String, String> params = new HashMap<String, String>();
		params.put("taxerName", taxerName);
		
		TaxerDaoImpl taxerDaoImpl = new TaxerDaoImpl();
		//总条数
		int count = taxerDaoImpl.getCount();
		
		//调用公共分页条件查询
		List<Taxer> list = taxerDaoImpl.getResultList(Taxer.class, "tb_taxer", page, rows, params);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", count);
		result.put("rows", list);
		
		JSONObject js = JSONObject.fromObject(result);
		
		PrintWriter writer = res.getWriter();
		writer.print(js.toString());
		writer.flush();
		writer.close();
	}
	
	

}
