package com.zhidisoft.servlet.taxer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.base.ResponseResult;
import com.zhidisoft.dao.impl.TaxerDaoImpl;

import net.sf.json.JSONObject;

@WebServlet("/taxer/deleteTaxer.do")
public class DeleteTaxerServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//ajax请求需要指定响应数据的格式,json格式的数据
		resp.setContentType("application/json;charset=UTF-8");
		//ajax请求用的多，为了规范书写，固定一个返回格式
		ResponseResult result = new ResponseResult();
		
		//获取删除参数，这里是id
		Integer id = Integer.parseInt(req.getParameter("id"));
		String s = req.getParameter("state");
		
		TaxerDaoImpl taxerDaoImpl = new TaxerDaoImpl();

		//查询tb_tax_source是否有对应的子记录，如果有对应的子记录就不能删除，就返回一个提示信息：此办税专员有对应的任务，不能删除
		boolean isHave = taxerDaoImpl.isHaveTaxOrgan(id);
		
		if (!isHave) {
			//调用删除的方法
			boolean state = taxerDaoImpl.delete(id);
			//设置响应信息
			if (state) {
				result.setSuccess(true);
				result.setMsg("删除成功");
			} else {
				result.setSuccess(false);
				result.setMsg("删除失败");
			}
		} else {
			result.setSuccess(false);
			result.setMsg("存在对应子记录，不能删除");
		}
		
		//将响应信息转化成json格式的数据
		JSONObject js = JSONObject.fromObject(result);
		
		//用流的形式将响应信息给输出到浏览器端
		PrintWriter writer = resp.getWriter();
		writer.print(js.toString());
		writer.flush();
		writer.close();
		
	}

	
}
