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
	private Long groupUserFlag; //0:CMS管理员用户;1:EC/SI定位企业
	private String linkMobile;

	// Constructors

	/** default constructor */
	public SysUser() {
	}

	/** full constructor */
	public SysUser(String loginName, String loginPass, String userName,
			String tel, String email, String areaId, Long departmentId,
			Boolean status, Long creatorId, Boolean isDel, Date createTime,
			Long groupuserFlag, String linkMobile) {
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
		this.groupUserFlag = groupuserFlag;
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

	public Long getGroupUserFlag() {
		return this.groupUserFlag;
	}

	public void setGroupUserFlag(Long groupUserFlag) {
		this.groupUserFlag = groupUserFlag;
	}

	public String getLinkMobile() {
		return this.linkMobile;
	}

	public void setLinkMobile(String linkMobile) {
		this.linkMobile = linkMobile;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SysUser [userId=");
		builder.append(userId);
		builder.append(", loginName=");
		builder.append(loginName);
		builder.append(", loginPass=");
		builder.append(loginPass);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", tel=");
		builder.append(tel);
		builder.append(", email=");
		builder.append(email);
		builder.append(", areaId=");
		builder.append(areaId);
		builder.append(", departmentId=");
		builder.append(departmentId);
		builder.append(", status=");
		builder.append(status);
		builder.append(", creatorId=");
		builder.append(creatorId);
		builder.append(", isDel=");
		builder.append(isDel);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", groupuserFlag=");
		builder.append(groupUserFlag);
		builder.append(", linkMobile=");
		builder.append(linkMobile);
		builder.append("]");
		return builder.toString();
	}

}