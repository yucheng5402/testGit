package com.zhidisoft.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.zhidisoft.dao.BaseDao;
import com.zhidisoft.entity.TaxOrgan;
import com.zhidisoft.util.DBUtil;

public class TaxOrganDaoImpl extends BaseDao<TaxOrgan>{

	@Override
	public List<TaxOrgan> getAll() {
		String sql ="select * from tb_tax_organ";
		List<Map<String, String>> list = DBUtil.query(sql);
		List<TaxOrgan> organs = new ArrayList<TaxOrgan>();
		if (list != null && !list.isEmpty()) {
			for (Map<String, String> map : list) {
				TaxOrgan organ = new TaxOrgan();
				try {
					BeanUtils.populate(organ, map);
				} catch (IllegalAccessException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				organs.add(organ);
			}
		}
		return organs;
	}

	@Override
	public TaxOrgan get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(TaxOrgan t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(TaxOrgan t) {
		// TODO Auto-generated method stub
		return false;
	}

}
