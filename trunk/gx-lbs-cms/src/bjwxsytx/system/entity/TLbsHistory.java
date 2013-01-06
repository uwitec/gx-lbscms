package bjwxsytx.system.entity;

import java.util.Date;

/**
 * TLbsHistory entity. @author MyEclipse Persistence Tools
 */

public class TLbsHistory implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userId;
	private String mdn;
	private Date reqTime;
	private Boolean respResult;
	private String respCode;
	private String respMemo;
	private String cellLac;
	private String cellCi;
	private String jindu;
	private String weidu;
	private String memo1;
	private String memo2;
	private Short flag1;
	private Short flag2;

	// Constructors

	/** default constructor */
	public TLbsHistory() {
	}

	/** minimal constructor */
	public TLbsHistory(String mdn) {
		this.mdn = mdn;
	}

	/** full constructor */
	public TLbsHistory(Long userId, String mdn, Date reqTime,
			Boolean respResult, String respCode, String respMemo,
			String cellLac, String cellCi, String jindu, String weidu,
			String memo1, String memo2, Short flag1, Short flag2) {
		this.userId = userId;
		this.mdn = mdn;
		this.reqTime = reqTime;
		this.respResult = respResult;
		this.respCode = respCode;
		this.respMemo = respMemo;
		this.cellLac = cellLac;
		this.cellCi = cellCi;
		this.jindu = jindu;
		this.weidu = weidu;
		this.memo1 = memo1;
		this.memo2 = memo2;
		this.flag1 = flag1;
		this.flag2 = flag2;
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

	public String getMdn() {
		return this.mdn;
	}

	public void setMdn(String mdn) {
		this.mdn = mdn;
	}

	public Date getReqTime() {
		return this.reqTime;
	}

	public void setReqTime(Date reqTime) {
		this.reqTime = reqTime;
	}

	public Boolean getRespResult() {
		return this.respResult;
	}

	public void setRespResult(Boolean respResult) {
		this.respResult = respResult;
	}

	public String getRespCode() {
		return this.respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespMemo() {
		return this.respMemo;
	}

	public void setRespMemo(String respMemo) {
		this.respMemo = respMemo;
	}

	public String getCellLac() {
		return this.cellLac;
	}

	public void setCellLac(String cellLac) {
		this.cellLac = cellLac;
	}

	public String getCellCi() {
		return this.cellCi;
	}

	public void setCellCi(String cellCi) {
		this.cellCi = cellCi;
	}

	public String getJindu() {
		return this.jindu;
	}

	public void setJindu(String jindu) {
		this.jindu = jindu;
	}

	public String getWeidu() {
		return this.weidu;
	}

	public void setWeidu(String weidu) {
		this.weidu = weidu;
	}

	public String getMemo1() {
		return this.memo1;
	}

	public void setMemo1(String memo1) {
		this.memo1 = memo1;
	}

	public String getMemo2() {
		return this.memo2;
	}

	public void setMemo2(String memo2) {
		this.memo2 = memo2;
	}

	public Short getFlag1() {
		return this.flag1;
	}

	public void setFlag1(Short flag1) {
		this.flag1 = flag1;
	}

	public Short getFlag2() {
		return this.flag2;
	}

	public void setFlag2(Short flag2) {
		this.flag2 = flag2;
	}

}