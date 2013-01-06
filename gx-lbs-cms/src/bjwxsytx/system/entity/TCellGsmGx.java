package bjwxsytx.system.entity;

/**
 * TCellGsmGx entity. @author MyEclipse Persistence Tools
 */

public class TCellGsmGx implements java.io.Serializable {

	// Fields

	private Long id;
	private String cellLac;
	private String cellCi;
	private String cellx;
	private String celly;
	private String centerX;
	private String centerY;

	// Constructors

	/** default constructor */
	public TCellGsmGx() {
	}

	/** minimal constructor */
	public TCellGsmGx(String cellLac, String cellCi) {
		this.cellLac = cellLac;
		this.cellCi = cellCi;
	}

	/** full constructor */
	public TCellGsmGx(String cellLac, String cellCi, String cellx,
			String celly, String centerX, String centerY) {
		this.cellLac = cellLac;
		this.cellCi = cellCi;
		this.cellx = cellx;
		this.celly = celly;
		this.centerX = centerX;
		this.centerY = centerY;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getCellx() {
		return this.cellx;
	}

	public void setCellx(String cellx) {
		this.cellx = cellx;
	}

	public String getCelly() {
		return this.celly;
	}

	public void setCelly(String celly) {
		this.celly = celly;
	}

	public String getCenterX() {
		return this.centerX;
	}

	public void setCenterX(String centerX) {
		this.centerX = centerX;
	}

	public String getCenterY() {
		return this.centerY;
	}

	public void setCenterY(String centerY) {
		this.centerY = centerY;
	}

}