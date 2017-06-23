package com.zhidisoft.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.zhidisoft.util.DBUtil;

public abstract class BaseDao<T> {

	/**
	 * ��ѯ����
	 * @return
	 */
	public abstract List<T> getAll();
	
	/**
	 * ͨ��id��ѯ
	 * @param id
	 * @return
	 */
	public abstract T get(Integer id);
	
	/**
	 * ����
	 * @param t
	 * @return
	 */
	public abstract boolean add(T t);
	
	/**
	 * ͨ��idɾ��
	 * @param id
	 * @return
	 */
	public abstract boolean delete(Integer id);
	
	/**
	 * �޸�
	 * @param t
	 * @return
	 */
	public abstract boolean update(T t);
	
	/**
	 * ͨ�÷�ҳ��ѯ
	 * @param clz ��Ӧ�����class
	 * @param tableName ����
	 * @param pageNumber ��ǰҳ��
	 * @param pageSize һҳ��ʾ����
	 * @param map ��ѯ������map����
	 * @return ���ݵ�list����
	 */
	public List<T> getResultList(Class<T> clz, String tableName, int pageNumber, int pageSize, Map<String, String> map) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from "+ tableName +" where 1=1");
		//ƴ�Ӳ�ѯ����
		if (!map.isEmpty()) {
			for (Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
				//��ȡmap���ϵļ�
				String key = it.next();
				//��ȡֵ
				String value = map.get(key);
				
				//����sqlƴ��
				if (value != null && value.trim().length() > 0 ) {
					sql.append(" and "+ key +"='"+ value.trim() +"'");
				}
			}
		}
		//���ƴ��limit�Ӿ�
		sql.append(" limit ?,?");
		
		//����ȷ������
		T t = null;
		//��ѯ
		List<Map<String, String>> list = DBUtil.query(sql.toString(), (pageNumber - 1)*pageSize, pageSize);
		List<T> resultList = new ArrayList<T>();
		 
		if (list != null && !list.isEmpty()) {
			for (Map<String, String > resultMap : list) {
				try {
					t = clz.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					BeanUtils.populate(t, resultMap);
				} catch (IllegalAccessException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				resultList.add(t);
			}
		}
		
		return resultList;
	};
	
	
}
