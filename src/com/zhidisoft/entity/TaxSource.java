package com.zhidisoft.entity;


/**
 * ˰Դ������Ϣ
 * @author ÷�ѽ�
 *
 */
public class TaxSource {

	private Integer id;				//����
	private Integer payerId;		//��˰��id
	private String taskName;		//��������
	private Integer taxOrganId;		//�´ﲿ��
	private Integer approverId;		//��׼��
	private Integer executeId;		//ִ����Ա
	private String executeTime;		//ִ��ʱ��
	private String taskFrom;		//������Դ
	private String taskState;		//����ִ��״��
	private String idea;			//������ۻ����
	private Integer riskLevel;		//���յȼ�
	private String recordDate;		//¼������
	private Integer recordUserId;	//¼����Ա

	public TaxSource() {
		super();
	}

	public TaxSource(Integer id, Integer payerId, String taskName, Integer taxOrganId, Integer approverId,
			Integer executeId, String executeTime, String taskFrom, String taskState, String idea, Integer riskLevel,
					 String recordDate, Integer recordUserId) {
		super();
		this.id = id;
		this.payerId = payerId;
		this.taskName = taskName;
		this.taxOrganId = taxOrganId;
		this.approverId = approverId;
		this.executeId = executeId;
		this.executeTime = executeTime;
		this.taskFrom = taskFrom;
		this.taskState = taskState;
		this.idea = idea;
		this.riskLevel = riskLevel;
		this.recordDate = recordDate;
		this.recordUserId = recordUserId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPayerId() {
		return payerId;
	}

	public void setPayerId(Integer payerId) {
		this.payerId = payerId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Integer getTaxOrganId() {
		return taxOrganId;
	}

	public void setTaxOrganId(Integer taxOrganId) {
		this.taxOrganId = taxOrganId;
	}

	public Integer getApproverId() {
		return approverId;
	}

	public void setApproverId(Integer approverId) {
		this.approverId = approverId;
	}

	public Integer getExecuteId() {
		return executeId;
	}

	public void setExecuteId(Integer executeId) {
		this.executeId = executeId;
	}

	public String getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(String executeTime) {
		this.executeTime = executeTime;
	}

	public String getTaskFrom() {
		return taskFrom;
	}

	public void setTaskFrom(String taskFrom) {
		this.taskFrom = taskFrom;
	}

	public String getTaskState() {
		return taskState;
	}

	public void setTaskState(String taskState) {
		this.taskState = taskState;
	}

	public String getIdea() {
		return idea;
	}

	public void setIdea(String idea) {
		this.idea = idea;
	}

	public Integer getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(Integer riskLevel) {
		this.riskLevel = riskLevel;
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
		return "TaxSource [id=" + id + ", payerId=" + payerId + ", taskName=" + taskName + ", taxOrganId=" + taxOrganId
				+ ", approverId=" + approverId + ", executeId=" + executeId + ", executeTime=" + executeTime
				+ ", taskFrom=" + taskFrom + ", taskState=" + taskState + ", idea=" + idea + ", riskLevel=" + riskLevel
				+ ", recordDate=" + recordDate + ", recordUserId=" + recordUserId + "]";
	}
	
	
}
