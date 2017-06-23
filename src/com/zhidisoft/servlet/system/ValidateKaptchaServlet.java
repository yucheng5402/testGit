package com.zhidisoft.servlet.system;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.code.kaptcha.Constants;
import com.zhidisoft.base.ResponseResult;

import net.sf.json.JSONObject;

/**
 * 验证验证码的servlet
 * 
 * @author 梅佳杰
 *
 */
@WebServlet("/validateKaptcha")
public class ValidateKaptchaServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json;charset=UTF-8");
		// 实例化响应结果对象
		ResponseResult result = new ResponseResult();
		// 获取验证码
		String imgCode = req.getParameter("captcha");

		// 获取session
		HttpSession session = req.getSession();
		// 从session中获取验证码
		String validateCode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);

		if (imgCode.equalsIgnoreCase(validateCode)) {
			result.setSuccess(true);
			result.setMsg("验证码正确");
		} else {
			result.setSuccess(false);
			result.setMsg("验证码错误");
		}

		// 将响应结果对象转化为json格式的数据
		JSONObject js = JSONObject.fromObject(result);

		// 获取输出流，将结果以流的形式写出
		PrintWriter writer = res.getWriter();

		writer.print(js.toString());
		writer.flush();
		writer.close();
	}

}
