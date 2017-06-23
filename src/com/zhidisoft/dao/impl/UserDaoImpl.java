package com.zhidisoft.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.zhidisoft.dao.BaseDao;
import com.zhidisoft.entity.User;
import com.zhidisoft.util.DBUtil;

public class UserDaoImpl extends BaseDao<User>{

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(User t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(User t) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * 通过用户名查询用户对象
	 * @param username 用户名
	 * @return user
	 */
	public User getByName(String username) {
		List<Map<String, String>> list = DBUtil.query("select * from tb_user where username=?", username);
		User user = null;
		if (list != null && list.size() == 1) {
			Map<String, String> map = list.get(0);
			user = new User();
			//map->user
			try {
				BeanUtils.populate(user, map);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

}
