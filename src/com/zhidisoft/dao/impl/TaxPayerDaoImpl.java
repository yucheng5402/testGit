package com.zhidisoft.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import com.zhidisoft.dao.BaseDao;
import com.zhidisoft.entity.TaxPayer;
import com.zhidisoft.util.DBUtil;

public class TaxPayerDaoImpl extends BaseDao<TaxPayer>{

	@Override
	public List<TaxPayer> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaxPayer get(Integer id) {
		String sql = "select * from tb_tax_payer where id=?";
		List<Map<String, String>> list = DBUtil.query(sql, id);
		TaxPayer payer = null;
		if (list != null && !list.isEmpty()) {
			Map<String, String> map = list.get(0);
			payer = new TaxPayer();
			try {
				BeanUtils.populate(payer, map);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
 		return payer;
	}

	@Override
	public boolean add(TaxPayer t) {
		int count = DBUtil.add(t, "tb_tax_payer");
		return count == 1;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(TaxPayer t) {
		return DBUtil.edit(t, "tb_tax_payer", t.getId()) == 1;
	}
	
	/**
	 * 分页查询
	 * @param page 当前页码
	 * @param rows 每页显示条数
	 * @param payerCode 查询参数纳税人识别号
	 * @param payerName 查询参数纳税人名称
	 * @return
	 */
	public List<Map<String, String>> getListByPage(int page, int rows, String payerCode, String payerName) {
		//编写查询的sql语句
		String sql = "SELECT ttp.*,tto.organName organName, ti.industryName industryName "
				+ "FROM tb_tax_payer ttp "
				+ "LEFT JOIN tb_tax_organ tto "
				+ "ON ttp.taxOrganId = tto.id "
				+ "LEFT JOIN tb_industry ti "
				+ "ON ttp.industryId=ti.id "
				+ "WHERE 1=1 ";
		//拼接查询条件
		if (StringUtils.isNotEmpty(payerCode)) {
			sql += "AND ttp.payerCode='"+ payerCode +"' ";
		}
		if (StringUtils.isNotEmpty(payerName)) {
			sql += "AND ttp.payerName LIKE '%"+ payerName +"%' ";
		}
		//拼接limit子句
		sql += "limit ?,?";
		 
		//调用DBUitl的公共查询方法
		return DBUtil.query(sql, (page - 1)*rows, rows);
	}
	
	/**
	 * 查询分页数据的条数
	 * @param payerCode 查询参数纳税人识别号
	 * @param payerName 查询参数纳税人名称
	 * @return
	 */
	public int getCount(String payerCode, String payerName) {
		String sql = "SELECT ttp.*,tto.organName organName, ti.industryName industryName "
				+ "FROM tb_tax_payer ttp "
				+ "LEFT JOIN tb_tax_organ tto "
				+ "ON ttp.taxOrganId = tto.id "
				+ "LEFT JOIN tb_industry ti "
				+ "ON ttp.industryId=ti.id "
				+ "WHERE 1=1 ";
		//拼接查询条件
		if (StringUtils.isNotEmpty(payerCode)) {
			sql += "AND ttp.payerCode='"+ payerCode +"' ";
		}
		if (StringUtils.isNotEmpty(payerName)) {
			sql += "AND ttp.payerName LIKE '%"+ payerName +"%' ";
		}
		List list = DBUtil.query(sql);
		return list.size();
	}

}
