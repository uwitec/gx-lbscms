package bjwxsytx.system.entity;

/**
 * SysUserRole entity. @author MyEclipse Persistence Tools
 */

public class SysUserRole implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userId;
	private Long roleId;

	// Constructors

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SysUserRole [id=");
		builder.append(id);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", roleId=");
		builder.append(roleId);
		builder.append("]");
		return builder.toString();
	}

	/** default constructor */
	public SysUserRole() {
	}

	/** full constructor */
	public SysUserRole(Long userId, Long roleId) {
		this.userId = userId;
		this.roleId = roleId;
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

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}