package bjwxsytx.system.entity;

import java.util.Date;

/**
 * OperatorLog entity. @author MyEclipse Persistence Tools
 */

public class OperatorLog implements java.io.Serializable {

	// Fields

	private Long logid;
	private Long adminid;
	private String areaid;
	private String opertype;
	private Date opertime;
	private Long objectid;
	private Boolean operflag;
	private String description;
	private String loginName;

	// Constructors

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/** default constructor */
	public OperatorLog() {
	}

	/** full constructor */
	public OperatorLog(Long adminid, String areaid, String opertype,
			Date opertime, Long objectid, Boolean operflag, String description,String loginName) {
		this.adminid = adminid;
		this.areaid = areaid;
		this.opertype = opertype;
		this.opertime = opertime;
		this.objectid = objectid;
		this.operflag = operflag;
		this.description = description;
		this.loginName = loginName;
	}

	// Property accessors

	public Long getLogid() {
		return this.logid;
	}

	public void setLogid(Long logid) {
		this.logid = logid;
	}

	public Long getAdminid() {
		return this.adminid;
	}

	public void setAdminid(Long adminid) {
		this.adminid = adminid;
	}

	public String getAreaid() {
		return this.areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public String getOpertype() {
		return this.opertype;
	}

	public void setOpertype(String opertype) {
		this.opertype = opertype;
	}

	public Date getOpertime() {
		return this.opertime;
	}

	public void setOpertime(Date opertime) {
		this.opertime = opertime;
	}

	public Long getObjectid() {
		return this.objectid;
	}

	public void setObjectid(Long objectid) {
		this.objectid = objectid;
	}

	public Boolean getOperflag() {
		return this.operflag;
	}

	public void setOperflag(Boolean operflag) {
		this.operflag = operflag;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}