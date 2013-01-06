package bjwxsytx.system.entity;

/**
 * SysRoleMenu entity. @author MyEclipse Persistence Tools
 */

public class SysRoleMenu implements java.io.Serializable {

	// Fields

	private Long id;
	private Long roleId;
	private Long menuId;

	// Constructors

	/** default constructor */
	public SysRoleMenu() {
	}

	/** full constructor */
	public SysRoleMenu(Long roleId, Long menuId) {
		this.roleId = roleId;
		this.menuId = menuId;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

}