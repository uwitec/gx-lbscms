package bjwxsytx.system.entity;

/**
 * SysRole entity. @author MyEclipse Persistence Tools
 */

public class SysRole implements java.io.Serializable {

	// Fields

	private Long roleId;
	private String roleName;
	private String description;
	private Boolean roleType;

	// Constructors

	/** default constructor */
	public SysRole() {
	}

	/** full constructor */
	public SysRole(String roleName, String description, Boolean roleType) {
		this.roleName = roleName;
		this.description = description;
		this.roleType = roleType;
	}

	// Property accessors

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getRoleType() {
		return this.roleType;
	}

	public void setRoleType(Boolean roleType) {
		this.roleType = roleType;
	}

}