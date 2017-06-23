package com.zhidisoft.servlet.system;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zhidisoft.base.ResponseResult;
import com.zhidisoft.dao.impl.UserDaoImpl;
import com.zhidisoft.entity.User;
import com.zhidisoft.util.EncryptUtil;

import net.sf.json.JSONObject;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/login.jsp").forward(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//设置响应的类型
		res.setContentType("application/json;charset=UTF-8");
		ResponseResult result = new ResponseResult(false, "用户名或密码错误");
		
		//获取请求参数
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		//根据username查询
		User user = userDaoImpl.getByName(username);
		
		if (user != null) {
			//password是盐值+明文密码加密
			
			//user对象中的是密文
			String validatePwd = user.getPassword();
			
			String pwd = EncryptUtil.encryptMD5(password + user.getSalt());
			
			if (validatePwd.equals(pwd)) {
				HttpSession session = req.getSession();
				session.setAttribute("user", user);
				result.setSuccess(true);
				result.setMsg("登录成功");
			}
		}
		
		//将result转化为json格式数据
		JSONObject js = JSONObject.fromObject(result);
		
		PrintWriter writer = res.getWriter();
		writer.print(js.toString());
		writer.flush();
		writer.close();
		
	}

	
}
