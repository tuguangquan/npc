package com.ctgu.npc.business.sys.entity;

import com.ctgu.npc.business.common.utils.StringUtils;

import java.io.Serializable;

public class Users implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 实体编号（唯一标识）
	 */
	protected String id;

	//private Office office;	
	private String officeId;// 归属部门
	private String loginName;// 登录名
	private String password;// 密码
	private String name;	// 姓名
	private String email;	// 邮箱
	private String phone;	// 电话
	private String mobile;	// 手机
	private String photo;	// 头像
	private String loginIp; // 最后登陆IP
	private String loginDate; // 最后登陆日期
	
	private String createBy; //创建者
	private String createDate; //创建日期
	private String updateBy; //更新者
	private String updateDate; //更新日期
	private String remarks; //备注
	private String userType="1";//用户类型 0代表 1机构用户
	private String job;//职务
	private String level;//系统等级
	private String roleName; //角色名
	private String roleId; //角色Id
	
	/*
	private String loginFlag;	// 是否允许登陆 1允许 2 不允许
	private String oldLoginName;// 原登录名
	private String newPassword;	// 新密码
	private String oldLoginIp;	// 上次登陆IP
	private String oldLoginDate;	// 上次登陆日期
	private Role role;	// 根据角色查询用户条件
	*/
	
	public Users() {
	}
	
	public Users(String loginName){
		if (StringUtils.isMobile(loginName)){
			this.mobile = loginName;
		}
		else {
			this.loginName = loginName;
		}
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
/*	public Users() {
		super();
		this.loginFlag = Global.YES;
	}
	
	public Users(String id){
		super();
	}*/



}
