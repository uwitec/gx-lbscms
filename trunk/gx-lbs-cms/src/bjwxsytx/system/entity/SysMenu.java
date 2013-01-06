package bjwxsytx.system.entity;

/**
 * SysMenu entity. @author MyEclipse Persistence Tools
 */

public class SysMenu implements java.io.Serializable {

	// Fields

	private Long menuId;
	private String menuName;
	private String url;
	private Long parentId;
	private Long seq;
	private String description;
	private Boolean deleteFlag;
	private String jsname;

	// Constructors

	/** default constructor */
	public SysMenu() {
	}

	/** full constructor */
	public SysMenu(String menuName, String url, Long parentId, Long seq,
			String description, Boolean deleteFlag, String jsname) {
		this.menuName = menuName;
		this.url = url;
		this.parentId = parentId;
		this.seq = seq;
		this.description = description;
		this.deleteFlag = deleteFlag;
		this.jsname = jsname;
	}

	// Property accessors

	public Long getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getSeq() {
		return this.seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getJsname() {
		return this.jsname;
	}

	public void setJsname(String jsname) {
		this.jsname = jsname;
	}

}