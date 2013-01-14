package bjwxsytx.system.entity;

/**
 * SysUserWhite entity. @author MyEclipse Persistence Tools
 */

public class SysUserWhite implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userId;
	private Long white;

	// Constructors

	/** default constructor */
	public SysUserWhite() {
	}

	/** full constructor */
	public SysUserWhite(Long userId, Long white) {
		this.userId = userId;
		this.white = white;
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

	public Long getWhite() {
		return this.white;
	}

	public void setWhite(Long white) {
		this.white = white;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SysUserWhite [id=");
		builder.append(id);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", white=");
		builder.append(white);
		builder.append("]");
		return builder.toString();
	}
	
	

}