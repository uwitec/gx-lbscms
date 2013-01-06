package bjwxsytx.system.entity;

/**
 * TCellWhite entity. @author MyEclipse Persistence Tools
 */

public class TCellWhite implements java.io.Serializable {

	// Fields

	private Long id;
	private String msisdn;
	private String memo;

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

}