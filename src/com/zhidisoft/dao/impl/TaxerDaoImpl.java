package com.zhidisoft.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.zhidisoft.dao.BaseDao;
import com.zhidisoft.entity.Taxer;
import com.zhidisoft.util.DBUtil;

public class TaxerDaoImpl extends BaseDao<Taxer>{

	@Override
	public List<Taxer> getAll() {
		String sql = "select * from tb_taxer";
		List<Map<String, String>> list = DBUtil.query(sql);
		List<Taxer> taxers = new ArrayList<>();
		if (list != null && !list.isEmpty()) {
			for (Map<String, String> map : list) {
				Taxer taxer = new Taxer();
				try {
					BeanUtils.populate(taxer, map);
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
				taxers.add(taxer);
			}
		}
		return taxers;
	}

	@Override
	public Taxer get(Integer id) {
		Map<String, String> map = DBUtil.queryRow("select * from tb_taxer where id=?", id);
		Taxer taxer = null;
		//如果map集合为空直接放回null
		if (map !=null && !map.isEmpty()) {
			taxer = new Taxer();
			try {
				BeanUtils.populate(taxer, map);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return taxer;
	}

	@Override
	public boolean add(Taxer t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		String sql = "DELETE FROM tb_taxer WHERE id=?";
		int count = DBUtil.update(sql, id);
		return count == 1;
	}

	@Override
	public boolean update(Taxer t) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 获取数据的总条数
	 * @return
	 */
	public int getCount() {
		List<Map<String, String>> list = DBUtil.query("select count(1) c from tb_taxer");
		int count = 0;
		if (list != null && list.size() == 1) {
			count = Integer.parseInt(list.get(0).get("c"));
		}
		return count;
	}
	
	/**
	 * 分页sql
	 * @param pageNumber
	 * @param pageSize
	 * @param taxerName
	 * @return
	 */
	public List<Map<String, String>> getListByMap(int pageNumber, int pageSize, String taxerName) {
		StringBuilder sb = new StringBuilder();
		//sql语句
		sb.append("SELECT tb.*,tto.organName FROM tb_taxer tb LEFT JOIN tb_tax_organ tto ON tb.organId=tto.id where 1=1");
		//如果有查询参数就拼接在后面
		if (taxerName != null && taxerName.length() >0) {
			//办税专员名字用模糊查询
			sb.append(" AND tb.taxerName  LIKE '%"+ taxerName +"%'");
		}
		//拼上limit子句
		sb.append(" limit ?,?");
		List<Map<String, String>> list = DBUtil.query(sb.toString(), (pageNumber-1)*pageSize, pageSize);
		return list;
	};
	
	/**
	 * 是否存在子记录
	 * @return
	 */
	public boolean isHaveTaxOrgan(Integer id) {
		String sql = "SELECT COUNT(1) c FROM tb_taxer tt RIGHT JOIN tb_tax_source tts ON tt.id=tts.approverId WHERE tt.id=? GROUP BY tt.id ";
		List<Map<String, String>> list = DBUtil.query(sql, id);
		
		if (list != null && !list.isEmpty()) {
			Integer count = Integer.parseInt(list.get(0).get("c"));
			if (count > 0 ) {
				return true;
			}
		}
		return false;
	}
	
	
}
