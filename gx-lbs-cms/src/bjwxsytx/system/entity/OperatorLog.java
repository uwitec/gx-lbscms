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
	private Short opertype;
	private Date opertime;
	private Long objectid;
	private Boolean operflag;
	private String description;

	// Constructors

	/** default constructor */
	public OperatorLog() {
	}

	/** full constructor */
	public OperatorLog(Long adminid, String areaid, Short opertype,
			Date opertime, Long objectid, Boolean operflag, String description) {
		this.adminid = adminid;
		this.areaid = areaid;
		this.opertype = opertype;
		this.opertime = opertime;
		this.objectid = objectid;
		this.operflag = operflag;
		this.description = description;
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

	public Short getOpertype() {
		return this.opertype;
	}

	public void setOpertype(Short opertype) {
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