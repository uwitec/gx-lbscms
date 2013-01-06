package bjwxsytx.system.entity;

import java.util.Date;

/**
 * SysUser entity. @author MyEclipse Persistence Tools
 */

public class SysUser implements java.io.Serializable {

	// Fields

	private Long userId;
	private String loginName;
	private String loginPass;
	private String userName;
	private String tel;
	private String email;
	private String areaId;
	private Long departmentId;
	private Boolean status;
	private Long creatorId;
	private Boolean isDel;
	private Date createTime;
	private Boolean groupuserFlag;
	private String linkMobile;

	// Constructors

	/** default constructor */
	public SysUser() {
	}

	/** full constructor */
	public SysUser(String loginName, String loginPass, String userName,
			String tel, String email, String areaId, Long departmentId,
			Boolean status, Long creatorId, Boolean isDel, Date createTime,
			Boolean groupuserFlag, String linkMobile) {
		this.loginName = loginName;
		this.loginPass = loginPass;
		this.userName = userName;
		this.tel = tel;
		this.email = email;
		this.areaId = areaId;
		this.departmentId = departmentId;
		this.status = status;
		this.creatorId = creatorId;
		this.isDel = isDel;
		this.createTime = createTime;
		this.groupuserFlag = groupuserFlag;
		this.linkMobile = linkMobile;
	}

	// Property accessors

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPass() {
		return this.loginPass;
	}

	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAreaId() {
		return this.areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public Long getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Long getCreatorId() {
		return this.creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public Boolean getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Boolean isDel) {
		this.isDel = isDel;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Boolean getGroupuserFlag() {
		return this.groupuserFlag;
	}

	public void setGroupuserFlag(Boolean groupuserFlag) {
		this.groupuserFlag = groupuserFlag;
	}

	public String getLinkMobile() {
		return this.linkMobile;
	}

	public void setLinkMobile(String linkMobile) {
		this.linkMobile = linkMobile;
	}

}