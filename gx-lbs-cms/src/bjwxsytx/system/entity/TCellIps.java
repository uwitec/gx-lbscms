package bjwxsytx.system.entity;

/**
 * TCellIps entity. @author MyEclipse Persistence Tools
 */

public class TCellIps implements java.io.Serializable {

	// Fields

	private Long id;
	private String ip;
	private String clientid;
	private String password;
	private String memo;

	// Constructors

	/** default constructor */
	public TCellIps() {
	}

	/** minimal constructor */
	public TCellIps(String ip, String clientid, String password) {
		this.ip = ip;
		this.clientid = clientid;
		this.password = password;
	}

	/** full constructor */
	public TCellIps(String ip, String clientid, String password, String memo) {
		this.ip = ip;
		this.clientid = clientid;
		this.password = password;
		this.memo = memo;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getClientid() {
		return this.clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}