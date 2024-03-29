package bjwxsytx.system.entity;

import java.util.Date;

/**
 * TCellWhite entity. @author MyEclipse Persistence Tools
 */

public class TCellWhite implements java.io.Serializable {

	// Fields

	private Long id;
	private String msisdn;
	private String memo;
	private Date createTime;

	// Constructors

	/** default constructor */
	public TCellWhite() {
	}

	/** minimal constructor */
	public TCellWhite(String msisdn) {
		this.msisdn = msisdn;
	}

	/** full constructor */
	public TCellWhite(String msisdn, String memo) {
		this.msisdn = msisdn;
		this.memo = memo;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TCellWhite [id=");
		builder.append(id);
		builder.append(", msisdn=");
		builder.append(msisdn);
		builder.append(", memo=");
		builder.append(memo);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append("]");
		return builder.toString();
	}
	
	

}