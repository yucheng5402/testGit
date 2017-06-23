package com.zhidisoft.entity;

import java.util.Date;

/**
 * ��ҵ�����
 * @author ÷�ѽ�
 *
 */
public class Industry {

	private Integer id;				//����
	private String industryName;	//��ҵ����
	private String recordDate;		//¼������
	private Integer recordUserId;	//¼��
	
	public Industry() {
		super();
	}

	public Industry(Integer id, String industryName, String recordDate, Integer recordUserId) {
		super();
		this.id = id;
		this.industryName = industryName;
		this.recordDate = recordDate;
		this.recordUserId = recordUserId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public Integer getRecordUserId() {
		return recordUserId;
	}

	public void setRecordUserId(Integer recordUserId) {
		this.recordUserId = recordUserId;
	}

	@Override
	public String toString() {
		return "Industry [id=" + id + ", industryName=" + industryName + ", recordDate=" + recordDate
				+ ", recordUserId=" + recordUserId + "]";
	}

}
