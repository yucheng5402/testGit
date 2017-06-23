package com.zhidisoft.servlet.taxpayer;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import com.zhidisoft.base.ResponseResult;
import com.zhidisoft.dao.impl.IndustryDaoImpl;
import com.zhidisoft.dao.impl.TaxOrganDaoImpl;
import com.zhidisoft.dao.impl.TaxPayerDaoImpl;
import com.zhidisoft.dao.impl.TaxerDaoImpl;
import com.zhidisoft.entity.Industry;
import com.zhidisoft.entity.TaxOrgan;
import com.zhidisoft.entity.TaxPayer;
import com.zhidisoft.entity.Taxer;
import com.zhidisoft.entity.User;

import net.sf.json.JSONObject;

@WebServlet("/taxpayer/operation.do")
public class OperationTaxPayerServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//约定优于配置
		//state->是哪个操作（add,edit,delete）
		String state = req.getParameter("state");
		if ("list".equals(state)) {
			resp.setContentType("application/json;charset=utf-8");
			Integer rows = Integer.parseInt(req.getParameter("rows"));
			Integer page = Integer.parseInt(req.getParameter("page"));
			String payerCode = req.getParameter("payerCode");
			String payerName = req.getParameter("payerName");
			
			TaxPayerDaoImpl taxPayerDaoImpl = new TaxPayerDaoImpl();
			//进行分页查询
			List<Map<String, String>> list = taxPayerDaoImpl.getListByPage(page, rows, payerCode, payerName);
			//查询总条数
			int total = taxPayerDaoImpl.getCount(payerCode, payerName);
			//total(总条数),rows(查询的list集合),JSON格式的数据
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("total", total);
			result.put("rows", list);
			
			//将结果转化为JSON格式
			JSONObject js = JSONObject.fromObject(result);
			PrintWriter writer = resp.getWriter();
			writer.print(js.toString());
			writer.flush();
			writer.close();
		} else if ("toAdd".equals(state)) {
			Integer id = StringUtils.isEmpty(req.getParameter("id")) ? null :Integer.parseInt(req.getParameter("id")); 
			
			//跳转到添加页面
			TaxOrganDaoImpl taxOrganDaoImpl = new TaxOrganDaoImpl();
			IndustryDaoImpl industryDaoImpl = new IndustryDaoImpl();
			TaxerDaoImpl taxerDaoImpl = new TaxerDaoImpl();
			
			//查询所有税务机关
			List<TaxOrgan> organs = taxOrganDaoImpl.getAll();
			//查询所有行业
			List<Industry> industries = industryDaoImpl.getAll();
			//查询所有办税专员
			List<Taxer> taxers = taxerDaoImpl.getAll();
			
			//将数据放在请求范围中
			req.setAttribute("organs", organs);
			req.setAttribute("industries", industries);
			req.setAttribute("taxers", taxers);
			
			//如果id不为null代表去修改页面
			if (id != null) {
				TaxPayerDaoImpl taxPayerDaoImpl = new TaxPayerDaoImpl();
				TaxPayer taxPayer = taxPayerDaoImpl.get(id);
				//将数据放在请求范围中
				req.setAttribute("taxPayer", taxPayer);
			}
			
			
			req.getRequestDispatcher("/manage/taxpayer/addTaxpayer.jsp").forward(req, resp);
			
		} else if ("add".equals(state)) {
			resp.setContentType("application/json;charset=utf-8");
			ResponseResult result = new ResponseResult();
			
			//添加纳税人
			//获取参数集合
			Map<String, String[]> params = req.getParameterMap();
			TaxPayer payer = new TaxPayer();
			
			//将map集合中的属性复制到payer对象中
			try {
				BeanUtils.populate(payer, params);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
			//获取session中的User对象
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("user");
			//设置操作人
			payer.setUserId(user.getId());
			
			Integer id = StringUtils.isEmpty(req.getParameter("id")) ? null : Integer.parseInt(req.getParameter("id"));
			
			//调用保存的方法
			TaxPayerDaoImpl taxPayerDaoImpl = new TaxPayerDaoImpl();
			
			//如果id为null就是添加时的保存操作
			if (id == null) {
				
				boolean flag = taxPayerDaoImpl.add(payer);
				
				if (flag) {
					result.setSuccess(flag);
					result.setMsg("添加成功");
				} else {
					result.setSuccess(flag);
					result.setMsg("添加失败");
				}
			//如果id不为null就是添加时的修改操作
			} else {
				boolean flag = taxPayerDaoImpl.update(payer);
				if (flag) {
					result.setSuccess(flag);
					result.setMsg("修改成功");
				} else {
					result.setSuccess(flag);
					result.setMsg("修改失败");
				}
			}
			
			JSONObject js = JSONObject.fromObject(result);
			
			PrintWriter writer = resp.getWriter();
			writer.print(js.toString());
			writer.flush();
			writer.close();
			
		} else if ("delete".equals(state)) {
			
		}
		
	}

	
}
