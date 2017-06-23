package com.zhidisoft.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.zhidisoft.dao.BaseDao;
import com.zhidisoft.entity.Industry;
import com.zhidisoft.util.DBUtil;

public class IndustryDaoImpl extends BaseDao<Industry>{

	@Override
	public List<Industry> getAll() {
		String sql = "select * from tb_industry";
		List<Map<String, String>> list = DBUtil.query(sql);
		List<Industry> industries = new ArrayList<Industry>();
		if (list != null && !list.isEmpty()) {
			for (Map<String, String> map : list) {
				Industry industry = new Industry();
				try {
					BeanUtils.populate(industry, map);
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
				industries.add(industry);
			}
		}
		return industries;
	}

	@Override
	public Industry get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Industry t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Industry t) {
		// TODO Auto-generated method stub
		return false;
	}

}
