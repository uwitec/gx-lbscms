package bjwxsytx.system.entity;

import java.util.Date;

/**
 * TLbsControl entity. @author MyEclipse Persistence Tools
 */

public class TLbsControl implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userId;
	private Integer everycounts;
	private Short hourLength;
	private String memo1;
	private Short flag1;
	private Date updatetime;

	// Constructors

	/** default constructor */
	public TLbsControl() {
	}

	/** full constructor */
	public TLbsControl(Long userId, Integer everycounts, Short hourLength,
			String memo1, Short flag1, Date updatetime) {
		this.userId = userId;
		this.everycounts = everycounts;
		this.hourLength = hourLength;
		this.memo1 = memo1;
		this.flag1 = flag1;
		this.updatetime = updatetime;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getEverycounts() {
		return this.everycounts;
	}

	public void setEverycounts(Integer everycounts) {
		this.everycounts = everycounts;
	}

	public Short getHourLength() {
		return this.hourLength;
	}

	public void setHourLength(Short hourLength) {
		this.hourLength = hourLength;
	}

	public String getMemo1() {
		return this.memo1;
	}

	public void setMemo1(String memo1) {
		this.memo1 = memo1;
	}

	public Short getFlag1() {
		return this.flag1;
	}

	public void setFlag1(Short flag1) {
		this.flag1 = flag1;
	}

	public Date getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

}