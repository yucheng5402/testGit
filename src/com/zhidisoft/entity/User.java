package com.zhidisoft.entity;

/**
 * �û���
 * @author DELL
 *
 */
public class User {

	private Integer id;				//����
	private String username;		//�û���
	private String password;		//����
	private Integer taxerId;		//Ա�����
	private String salt;			//��ֵ
	private Integer permissionId;	//�û�Ȩ��
	private Integer state;			//�û�״̬
	private String email;			//����
	
	public User() {
		super();
	}

	public User(Integer id, String username, String password, Integer taxerId, String salt, Integer permissionId,
			Integer state, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.taxerId = taxerId;
		this.salt = salt;
		this.permissionId = permissionId;
		this.state = state;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getTaxerId() {
		return taxerId;
	}

	public void setTaxerId(Integer taxerId) {
		this.taxerId = taxerId;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", taxerId=" + taxerId
				+ ", salt=" + salt + ", permissionId=" + permissionId + ", state=" + state + ", email=" + email + "]";
	}
	
}
