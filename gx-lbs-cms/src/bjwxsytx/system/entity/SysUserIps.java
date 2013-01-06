package bjwxsytx.system.entity;

/**
 * SysUserIps entity. @author MyEclipse Persistence Tools
 */

public class SysUserIps implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userId;
	private Long ipsId;

	// Constructors

	/** default constructor */
	public SysUserIps() {
	}

	/** full constructor */
	public SysUserIps(Long userId, Long ipsId) {
		this.userId = userId;
		this.ipsId = ipsId;
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

	public Long getIpsId() {
		return this.ipsId;
	}

	public void setIpsId(Long ipsId) {
		this.ipsId = ipsId;
	}

}